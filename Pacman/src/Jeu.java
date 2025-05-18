import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.Timer;




public class Jeu extends JComponent {
    private final int WIDTH = 672;
    private final int HEIGHT = 864;  // les rendre utile avec getSize()

    private Maze maze_Source = new Maze();
    private int[][] maze = new int[28][36];

    private Pacman pac;

    private ArrayList<Ghost> ghosts;  //utiliser les array au lieu de 4 ghost separement
    private int score = 0;
    private int score_convertie_en_vie = 0;
    private int nb_pacgomme = 244;  //pour savoir si le niveau est terminé, il y en a 244

    private Timer tGhost;
    private Timer tPac;


    public Jeu(Pacman pac) {
        super();
        this.pac = pac;
        this.ghosts = new ArrayList<Ghost>();
        this.ghosts.add(new Ghost(Color.CYAN));
        this.ghosts.add(new Ghost(Color.RED));
        this.ghosts.add(new Ghost(Color.GREEN));
        this.ghosts.add(new Ghost(Color.PINK));
        pac.setState(new NormalState(this));
        setSize(WIDTH, HEIGHT);
        setOpaque(true);

        tPac = new Timer(250, move_Pac);
        tGhost = new Timer(250, move_Ghost);
        tPac.start();
        tGhost.start();
    }
    // tPac.stop() et tGhost.stop() dans la fonction paintComponent en dessous

    @Override
    public void paintComponent(Graphics g) {
        if (score == 0) {
            copieMaze(maze, maze_Source.getConfig());
        }

        super.paintComponent(g);
        setBackground(Color.BLACK);
        drawMaze(g);

        g.setColor(Color.WHITE);
        g.drawString("vie = " + pac.getVie(), 150, 50);
        g.drawString("Score = " + score, 300, 50);
        g.drawString("touches de deplacement: ZQSD", 450, 50);

        if(nb_pacgomme == 0){
            tGhost.stop();
            tPac.stop();
            g.setColor(Color.WHITE);
            g.fillRect(250,330,180,100);
            g.setColor(Color.BLACK);
            g.drawString("Vous avez gagné, Bravo!",270,350);


        }
        if(pac.getVie() == 0){
            tGhost.stop();
            tPac.stop();
            g.setColor(Color.WHITE);
            g.fillRect(230,330,270,100);
            g.setColor(Color.BLACK);
            g.drawString("Vous avez perdu, 0 vie, c'est regrettable",250,350);
        }

    }
    //================================== Les Fonctions du jeu =================================================

    public void structureGommeVerte(){
        for(int i = 0; i< 28; i++){
            for(int j = 0; j<36; j++){
                if(maze[i][j] != 4 && maze[i][j] != 5 && maze[i][j] != 6 && maze[i][j] != 9){
                    if(i<= 8 && i>= 6 && j<=25 && j>= 22){
                        maze[i][j] = 7;
                    }
                    if(i<= 8 && i>= 6 && j<=20 && j>= 16){
                        maze[i][j] = 7;
                    }
                    if(i<= 8 && i>= 5 && j<=6 && j> 3){
                        maze[i][j] = 7;
                    }
                    if(i<= 17 && i>= 15 && j<=8 && j>=5){
                        maze[i][j] = 7;
                    }

                }
            }
        }
    }


    public void copieMaze(int[][] maze, int[][] source) {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 36; j++) {
                maze[i][j] = source[i][j];
            }
        }
    }

    public void drawMaze(Graphics g) {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 36; j++) {

                colorerCase(g, maze, i, j);


            }
        }
        colorPacman(g);
        colorGhost(g, ghosts);            //car il n'y a pas l affichage des ghost dans drawMaze

    }

    public void colorerCase(Graphics g, int[][] config, int i, int j) {
        int r = 12;   //rayon du cercle pour colorer un cercle
        int o = 4; //le decalage de la case i,j pour positionner le cercle,c'est le point haut gauche
        if (config[i][j] == 0) g.setColor(Color.BLACK);
        else if (config[i][j] == 1) g.setColor(Color.BLUE);
        else if (config[i][j] == 2) g.setColor(Color.GRAY);
        else if (config[i][j] == 3) g.setColor(Color.PINK);

        else if (config[i][j] == 7) g.setColor(Color.GRAY); //la ligne vide qui ou on avance a l'infini

        else g.setColor(Color.GRAY);

        g.fillRect(24 * i, 24 * j, 24, 24); //colorie une case de 12x12 pixel

        if (config[i][j] == 4) {
            g.setColor(Color.BLUE);
            g.fillOval(i * 24 + o, j * 24 + o, r, r);
        } else if (config[i][j] == 5) {
            g.setColor(new Color(250, 132, 36, 255)); //du orange
            g.fillOval(i * 24 + o, j * 24 + o, r, r);

        }

        if (config[i][j] == 6) {
            g.setColor(new Color(127, 35, 212, 191)); // du violet
            g.fillOval(i * 24 + o, j * 24 + o, r, r);
        }
        //le 8 c'est la teleportation donc j'ai laissé blanc
        if (config[i][j] == 9) {
            g.setColor(Color.GREEN);
            g.fillOval(i * 24 + o, j * 24 + o, r, r);
        }

    }

    public void colorPacman(Graphics g) {
        g.setColor(pac.getColor());
        g.fillOval(pac.getPacX() * 24, pac.getPacY() * 24, 24, 24);

    }

    public void colorGhost(Graphics g, ArrayList<Ghost> ghosts) {
        for (Ghost ghost : ghosts) {

            g.setColor(ghost.getColor());
            g.fillOval(ghost.getGhostX() * 24, ghost.getGhostY() * 24, 24, 24);

        }

    }

    //====================================Fonctions pour Pac ================================
    public Pacman getPac() {
        return pac;
    }

    public void pac_mange() {
        int i = pac.getPacX();
        int j = pac.getPacY();
        switch (maze[i][j]) {
            case 4:
                score += 100;  //classique bleu
                score_convertie_en_vie+=100;
                nb_pacgomme--;
                maze[i][j] = 7;  //case vide
                break;
            case 5:
                score += 500;  //power orange
                score_convertie_en_vie+=500;
                pac.setState(new PowerState(this));
                nb_pacgomme--;
                maze[i][j] = 7;
                break;
            case 6:
                score += 300;  //invisible violet
                score_convertie_en_vie+=300;
                pac.setState(new InvisibleState(this));
                nb_pacgomme--;
                maze[i][j] = 7;
                break;
            case 9:
                structureGommeVerte();
                score += 1000; // structure verte (change la map)
                score_convertie_en_vie+=1000;
                nb_pacgomme--;
                maze[i][j] = 7;
                break;

        }
        if(score_convertie_en_vie>=5000){
            pac.augmenterVie();
            score_convertie_en_vie-=5000;
        }
    }

    //==============================Fonctions pour Ghosts=========================================

    public ArrayList<Ghost> getGhosts(){return ghosts;}

    public void collision(ArrayList<Ghost> ghosts) {

        for (Ghost ghost : ghosts) {

            if ( (pac.getPacX() == ghost.getGhostX() && pac.getPacY() == ghost.getGhostY() ) ||
                    (pac.getPacX() + pac.getPac_directionX() == ghost.getGhostX() && pac.getPacY() + pac.getPac_directionY() == ghost.getGhostY()  ) || //ici ou/et
                    (ghost.getGhostX() + ghost.getGhost_directionX() == pac.getPacX() && ghost.getGhostY() + ghost.getGhost_directionY() == pac.getPacY() ) ||
                    (ghost.getGhostX()+ghost.getGhost_directionX() == pac.getPacX()+pac.getPac_directionX() && ghost.getGhostY()+ghost.getGhost_directionY() == pac.getPacY()+pac.getPac_directionY()))

            //dans le gros -if- on veut les condition : si position(pac) == position(ghost)
            //                                         ou position_suivante(pac) == position(ghost) et position_suivante(ghost) == position(pac)
            //                                          ou position_suivante(pac) == position_suivante(ghost) , un peu bizarre donc a ameliorer


            {
                if(  "Normal".equals(pac.getStateString())  ){
                    pac.diminuerVie();
                    pac.positionDefault();

                }
                if(  "Power".equals(pac.getStateString())  ){
                    ghost.positionDefault();
                }

            }

        }



    }






    //===================================== ActionListener =========================================
    ActionListener move_Ghost = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if ("Power".equals(pac.getStateString())) {
                if (( (PowerState) pac.getState() ).getCompteur() % 2 == 0) {
                    repaint();         //repaint() 1 image sur 2


                }
                else {
                    for (Ghost ghost : ghosts) {
                        ghost.deplacement(maze);
                    }
                    repaint();

                }
            } else {
                for (Ghost ghost : ghosts) {
                    ghost.deplacement(maze);
                }
                repaint();
            }
        }
    };

    ActionListener move_Pac = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pac.deplacement(maze);
            collision(ghosts);


            pac_mange();
            pac.action();
            repaint();

        }
    };



}







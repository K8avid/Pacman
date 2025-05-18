
import java.awt.Color;
import java.lang.Math;

public class Ghost {
    private final int ghostStartX = 13;  //mettre 13
    private final int ghostStartY = 18;  //mettre 18         12,14 c etait un test

    private int ghostX = 13;
    private int ghostY = 18;

    private int ghost_directionX = 0;  // deplacement continue selon x ou y jusqua un mur
    private int ghost_directionY = -1;



    private Color color;
    private Color defaultColor;

    public Ghost(Color color){
        this.color = color;
        this.defaultColor = color;
    }

    //============================Position si touché =====================================

    public void positionDefault(){
        ghostX = ghostStartX;
        ghostY = ghostStartY;
        ghost_directionX = 0;
        ghost_directionY = -1;
    }

    //======================================== deplacement =========================================

    public void deplacement(int[][] config){
        if(isInSpawn()) sortirSpawn();
        else deplacement_aux(config);
    }

    //===============================fonctions pour deplacement====================================
    public boolean isInSpawn(){
        return (ghostX == 13 && ghostY >= 15 && ghostY <= 18);
    }

    public void sortirSpawn(){
        if(ghost_directionX == 0 && ghost_directionY == 0);

        else if(ghostX == 13 && ghostY == 18 ) ghostY -= 1;
        else if(ghostX == 13 && ghostY == 17) ghostY -=1;
        else if(ghostX == 13 && ghostY == 16) ghostY -=1;
        else if(ghostX == 13 && ghostY == 15) ghostY -=1;



    }

    public void deplacement_aux(int[][] config){

        int up = 0;         //l'axe des y est inversé donc up vaut -1 et down +1 en axe y
        int down = 0;       // en gros quand on arrive au mur (valeur case = 1) on regarde les cases autour
        int left = 0;       // on liste ce qui est accessible et on choisis au hasard
        int right = 0;

        if( config[ghostX+ghost_directionX][ghostY+ghost_directionY] == 1){

            if( config[ghostX][ghostY-1] != 1  ) up = -1;
            if(config[ghostX][ghostY+1] != 1) down = 1;
            if(config[ghostX+1][ghostY] != 1 ) right = 1;
            if(config[ghostX-1][ghostY] != 1 ) left = -1;
        }

        if(ghostX == 13 && ghostY == 14 ) down = 0; //case qui pouvait causer le retour au spawn, donc on dit qu'on ne peut pas reculer a cette case

        if(up != 0 || down != 0 || right != 0 || left != 0){ //verifie que ces trucs sont non nul sinon boucle infini
            int sortie = 0; //ici on veut recuperer une valeur du lot disponible (up, down, left, right) lot different de 0
            int n = 0;
            while(sortie == 0){
                n = (int) (Math.random()*4);
                if(n == 0) {
                    sortie = up;                           //ce processus s'arrete quand sortie est pas nul
                    ghost_directionY = sortie;
                    ghost_directionX = 0;
                }
                if(n == 1) {
                    sortie = down;
                    ghost_directionY = sortie;
                    ghost_directionX = 0;
                }
                if(n == 2) {
                    sortie = left;
                    ghost_directionY = 0;
                    ghost_directionX = sortie;
                }
                if(n == 3) {
                    sortie = right;
                    ghost_directionY = 0;
                    ghost_directionX = sortie;
                }
           }
        }

        ghostX += ghost_directionX;  //pour effectuer le deplacement
        ghostY += ghost_directionY;

    }

    //=======================================================================================================


    //=============================get===================================

    public Color getColor() {return color;}
    public Color getDefaultColor(){ return defaultColor;}

    public int getGhostX() { return ghostX;}

    public int getGhostY() { return ghostY;}

    public int getGhost_directionX(){return ghost_directionX;}

    public int getGhost_directionY() { return ghost_directionY;}


    //===============================Set====================================
    public void setColor(Color c){this.color = c;}


}

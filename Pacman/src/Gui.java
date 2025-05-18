
import javax.swing.JFrame;

public class Gui {
    private JFrame frame = new JFrame("test");
    private Pacman pac = new Pacman();
    private Jeu game = new Jeu(pac);

    public Gui(){
        MyKeyListener key = new MyKeyListener();
        key.setPacman(pac);            // keyListener dans lequel on met le perso

        frame.setContentPane(game); // contenue de la fenetre je crois, les JComponent
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setSize(672,864); //taille de map 28x12 * 36x12
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addKeyListener(key);  //initialisation du listener dans le frame



    }

}

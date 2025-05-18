import java.awt.event.*;

public class MyKeyListener extends KeyAdapter {
    private Pacman pac;
    public void setPacman(Pacman pac){this.pac = pac;}
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'z': pac.directionInput('z');
                break;
            case 's':pac.directionInput('s');
                break;
            case 'q':pac.directionInput('q');
                break;
            case 'd':pac.directionInput('d');

                break ;

            default:
                pac.directionInput('p');
        }
    }
}

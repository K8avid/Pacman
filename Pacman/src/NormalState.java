import java.awt.*;

public class NormalState implements State{
    private Jeu jeu;
    private Pacman pac;

    public NormalState(Jeu jeu){
        pac.setColor(Color.YELLOW);

    }

    @Override
    public String getStateString() {
        return "Normal";
    }
    @Override
    public void action(){}

}

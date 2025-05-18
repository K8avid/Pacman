import java.awt.Color;
public class InvisibleState implements State{

    private Jeu jeu;
    private Pacman pac;
    private int compteur = 40;


    public InvisibleState(Jeu jeu){
        pac.setColor(new Color(230, 241, 129, 191));

    }

    @Override
    public String getStateString(){ return "Invisible";}
    @Override
    public void action(){
        if(compteur > 0) compteur--;
        else {
            pac.setState(new NormalState(jeu));

        }

    }

}

import java.awt.*;

public class PowerState implements State{

    private Jeu jeu;
    private Pacman pac;
    private int compteur = 40;  // compteur de temps

    public PowerState(Jeu jeu){
        pac.setColor(new Color(250, 132, 36, 255));
    }

    @Override
    public String getStateString(){ return "Power";}
    @Override
    public void action(){
        for( Ghost ghost : jeu.getGhosts()){
            ghost.setColor(new Color(3, 7, 103,191));  //colorie les ghost en bleu foncé
        }
        if(compteur > 0) compteur--;
        else {
            pac.setState(new NormalState(jeu));
            for( Ghost ghost : jeu.getGhosts()){                  //redonne aux ghost leur couleur initial
                ghost.setColor(ghost.getDefaultColor());
            }

        }

    }

    public int getCompteur(){return compteur;}

}

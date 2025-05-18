import java.awt.Color;

public class Pacman {

    private Color color = Color.YELLOW;
    private int vie;
    private int pacX=11;
    private int pacY=23;
    private final int pacStartX=13;
    private final int pacStartY=26;

    private int pac_directionX = -1;
    private int pac_directionY = 0;

    private State state;

    public Pacman(){
        vie = 3;
    }

    public void deplacement(int[][] config){
        if(pacX == 27 && pacY == 17){
            pacX = 1;
        }
        if(pacX == 0 && pacY == 17){
            pacX = 27;
        }
        if(config[pacX+pac_directionX][pacY+pac_directionY] != 1 && config[pacX+pac_directionX][pacY+pac_directionY] != 2 && config[pacX+pac_directionX][pacY+pac_directionY] != 3 ) {
            //ici dans le if on veut eviter les cases bleu et le spawn des Ghosts donc 3 type de cases a valeur 1 ou 2 ou 3
            pacX += pac_directionX;
            pacY += pac_directionY;
        }

    }

    public void directionInput(char a){
         if( a == 's'){
            pac_directionX = 0;
            pac_directionY = 1;
        }
        if( a == 'z'){
            pac_directionX = 0;
            pac_directionY = -1;
        }
        if( a == 'q'){
            pac_directionX = -1;
            pac_directionY = 0;
        }
        if( a == 'd'){
            pac_directionX = 1;
            pac_directionY = 0;
        }
    }

    public void diminuerVie(){
        vie--;
    }
    public void augmenterVie(){
        vie++;
    }
    public void positionDefault(){
        pacX = pacStartX;
        pacY = pacStartY;
        pac_directionX = -1;
        pac_directionY = 0;
    }




    //=============================get===================================

    public Color getColor(){return color;}

    public int getPacX() { return pacX;}

    public int getPacY() { return pacY;}

    public int getVie() { return vie;}

    public int getPac_directionX() {return pac_directionX;}

    public int getPac_directionY() {return pac_directionY;}

    //==============================set===============================

    public void setColor(Color c){this.color = c;}

//==============================State================================//


    public State getState() {return state;}
    public void setState(State state){this.state = state;}
    public void action(){ state.action();}
    public String getStateString(){return state.getStateString();}

}


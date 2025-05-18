
public class Maze {
    private final int V=0; //noir
    private final int B=1; //bleu
    private final int C=2; //blanc, spawn des ghosts
    private final int E=3; //rose, entrée du spawn des ghosts
    private final int G=4; //pacgommes  bleu classique
    private final int P=5; //power = pacgomme orange
    private final int I=6; //invisible = pacgomme violet et pacman devient jaune pale
    private final int A=7; //chemin vide
    private final int W=8; //téléportation pour revenir de l'autre coté de la map
    private final int S=9; //structure = pacgomme verte
    //Contenu du labyrinthe
    private int[][] config = {
            {V,V,V,B,B,B,B,B,B,B,B,B,B,V,V,V,B,W,B,V,V,V,B,B,B,B,B,B,B,B,B,B,B,B,V,V},
            {V,V,V,B,G,G,P,G,G,G,G,G,B,V,V,V,B,A,B,V,V,V,B,G,G,G,I,B,B,G,G,G,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,V,V,V,B,A,B,V,V,V,B,G,B,B,G,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,V,V,V,B,A,B,V,V,V,B,G,B,B,G,G,G,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,V,V,V,B,A,B,V,V,V,B,G,B,B,B,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,B,B,B,B,A,B,B,B,B,B,G,B,B,B,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,B,B,B,B,B,B,A,B,B,B,B,B,G,B,B,G,B,B,B,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,B,B,B,B,B,B,A,B,B,B,B,B,G,B,B,G,B,B,B,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,G,G,G,B,B,A,A,A,A,A,A,A,A,A,G,B,B,G,G,G,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,B,A,B,B,B,B,B,A,B,B,G,B,B,G,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,B,A,B,C,C,C,B,A,B,B,G,B,B,G,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,G,G,G,G,B,B,G,A,A,A,B,C,C,C,B,A,B,B,G,G,G,G,B,B,G,G,G,G,B,V,V},
            {V,V,V,B,B,B,B,B,G,B,B,B,B,B,A,E,C,C,C,B,A,B,B,B,B,B,A,B,B,B,B,B,G,B,V,V}, // 2e sortie 16 en Y et 15 en X
            {V,V,V,B,B,B,B,B,G,B,B,B,B,B,A,E,C,C,C,B,A,B,B,B,B,B,A,B,B,B,B,B,G,B,V,V}, // 1er sortie ghost a 16 en Y et 14 en X
            {V,V,V,B,G,G,G,G,G,B,B,G,A,A,A,B,C,C,C,B,A,B,B,G,G,G,G,B,B,G,G,G,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,B,A,B,C,C,C,B,A,B,B,G,B,B,G,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,B,A,B,B,B,B,B,A,B,B,G,B,B,G,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,G,G,G,B,B,A,A,A,A,A,A,A,A,A,G,B,B,G,G,G,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,B,B,B,B,B,B,A,B,B,B,B,B,G,B,B,G,B,B,B,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,B,B,B,B,B,B,A,B,B,B,B,B,G,B,B,G,B,B,B,B,B,G,B,V,V},
            {V,V,V,B,S,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,B,B,B,B,A,B,B,B,B,B,G,B,B,B,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,V,V,V,B,A,B,V,V,V,B,G,B,B,B,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,V,V,V,B,A,B,V,V,V,B,G,B,B,G,G,G,G,B,B,G,B,V,V},
            {V,V,V,B,G,B,B,B,G,B,B,G,B,V,V,V,B,A,B,V,V,V,B,G,B,B,G,B,B,G,B,B,G,B,V,V},
            {V,V,V,B,G,G,I,G,G,G,G,G,B,V,V,V,B,A,B,V,V,V,B,G,G,G,P,B,B,G,G,G,G,B,V,V},
            {V,V,V,B,B,B,B,B,B,B,B,B,B,V,V,V,B,W,B,V,V,V,B,B,B,B,B,B,B,B,B,B,B,B,V,V},
    };
    //36 Y et 28 X



    public int[][] getConfig() { return config;}

}

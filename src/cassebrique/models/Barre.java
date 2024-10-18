package cassebrique.models;

import java.awt.*;

public class Barre extends Rectangle {

    protected int vitesse;
    public static int hauteurDefaut = 30;
    public static int largeurDefaut = 200;

    public Barre(int x, int y) {
        super(x, y, largeurDefaut, hauteurDefaut , Color.BLUE);
        this.vitesse = 5;
    }

    public void deplacementDroite() {

        this.x += this.vitesse;

        if(this.x >= 500 - largeur ){
            this.x = 500 - this.largeur;
        }
    };

    public void deplacementGauche() {
        this.x -= this.vitesse;

        if(this.x < 0 ){
            this.x = 0;
        }
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}

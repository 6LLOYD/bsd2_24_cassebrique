package cassebrique;

import cassebrique.models.Balle;
import cassebrique.models.Barre;
import cassebrique.models.Brique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CasseBrique extends Canvas implements KeyListener {

    public JFrame fenetre = new JFrame();
    public ArrayList<Balle> listeBalle = new ArrayList<>();
    public ArrayList<Brique> listeBrique = new ArrayList<>();
    public Barre barre;

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 550;

    public boolean toucheDroite = false;
    public boolean toucheGauche = false;

    public CasseBrique() throws InterruptedException {

        this.fenetre.setSize(LARGEUR, HAUTEUR);
        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0,0,LARGEUR, HAUTEUR);

        this.fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();
        panneau.add(this);
        this.fenetre.setContentPane(panneau);

        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        this.fenetre.pack();
        this.fenetre.setResizable(false);
        this.fenetre.requestFocus();
        this.fenetre.addKeyListener(this);

        this.fenetre.setVisible(true);
        this.createBufferStrategy(2);

        lancerUnePartie();
    }

    public void lancerUnePartie() throws InterruptedException {

        listeBalle = new ArrayList<>();
        //listeBalle.add(new Balle(100,100,3,4));
        //listeBalle.add(new Balle(200,100,2,3));
        //listeBalle.add(new Balle(100,200,7,4));
        listeBalle.add(new Balle(156,207,4,3));

        barre = new Barre(
                CasseBrique.LARGEUR / 2 - Barre.largeurDefaut / 2,
                CasseBrique.HAUTEUR - 100);


        listeBrique = new ArrayList<>();
        for (int indexLigne = 0; indexLigne < 5; indexLigne ++) {
            for (int indexColonne = 0; indexColonne < 7; indexColonne ++) {
                Brique brique = new Brique(
                        indexColonne * (Brique.largeurDefaut + 2),
                        indexLigne * (Brique.hauteurDefaut + 2),
                        Color.CYAN);
                listeBrique.add(brique);
            }
        }

        while(true) {

            Graphics2D dessin = (Graphics2D)this.getBufferStrategy().getDrawGraphics();

            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, LARGEUR, HAUTEUR);

            for(Balle balle : listeBalle) {
                balle.deplacer();
                if (balle.getY() == 450){
                    System.out.println(balle);
                }
                balle.dessiner(dessin);
            }

            if(toucheDroite){
                barre.deplacementDroite();
            }
            if(toucheGauche){
                barre.deplacementGauche();
            }

            barre.dessiner(dessin);

            for(Brique brique : listeBrique) {
                brique.dessiner(dessin);
            }

            dessin.dispose();
            this.getBufferStrategy().show();

            Thread.sleep(1000 / 60);
        }
    }

    //main : raccourci
    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
         toucheDroite = true;
       }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            toucheGauche = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            toucheDroite = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            toucheGauche = false;
        }
    }
}

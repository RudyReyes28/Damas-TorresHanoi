package com.ipc1.torres_hanoi.torres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DibujarTorres extends JLayeredPane implements ActionListener {

    private JButton [][] bloques;
    private int cantidadBloques;
    private BloquesTorres verBloques;

    public DibujarTorres() {
        //this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setBounds(0,0,1100,600);
        iniciar();
        llenarTorres();
    }

    public void iniciar(){
        bloques = new JButton[3][8];
        verBloques = new BloquesTorres(4);
        verBloques.llenarBloques();

        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,1100,500);
        final ImageIcon imagenTorres = new ImageIcon(getClass().getResource("/imagenesHanoi/torres.png"));
        fondo.setIcon(new ImageIcon(imagenTorres.getImage().getScaledInstance(1100,500,Image.SCALE_SMOOTH)));
        this.add(fondo,Integer.valueOf(0));

        for(int i=0; i< bloques.length;i++){
            for(int j=0; j<bloques[0].length; j++){
                bloques[i][j] = new JButton();
                bloques[i][j].setBorderPainted(false);
                bloques[i][j].setContentAreaFilled(false);
                bloques[i][j].setOpaque(false);
                bloques[i][j].addActionListener(this);
            }
        }
        posicionarBloques();
    }

    public void posicionarBloques(){

        //PARA EL BLOQUE 1
        int moverYBloque1=0;
        for(int i=0; i<bloques[0].length; i++){

            //TORRE 1
            bloques[0][i].setBounds(70,423-moverYBloque1,275,40);
            this.add(bloques[0][i],Integer.valueOf(1));

            //TORRE 2
            bloques[1][i].setBounds(416,423-moverYBloque1,275,40);
            this.add(bloques[1][i],Integer.valueOf(1));

            //TORRE 3
            bloques[2][i].setBounds(760,423-moverYBloque1,275,40);
            this.add(bloques[2][i],Integer.valueOf(1));

            moverYBloque1+=40;
        }

    }

    public void llenarTorres(){

        for(int i=0; i<bloques.length; i++){
            for(int j=0; j<bloques[0].length; j++){

                if(verBloques.getBloque(i,j)== 8){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque8.png")));
                }else if(verBloques.getBloque(i,j)== 7){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque7.png")));
                }else if(verBloques.getBloque(i,j)== 6){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque6.png")));
                }else if(verBloques.getBloque(i,j)== 5){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque5.png")));
                }else if(verBloques.getBloque(i,j)== 4){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque4.png")));
                }else if(verBloques.getBloque(i,j)== 3){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque3.png")));
                }else if(verBloques.getBloque(i,j)== 2){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque2.png")));
                }else if(verBloques.getBloque(i,j)== 1){
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque1.png")));
                }else{
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloqueVacio.png")));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

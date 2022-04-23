package com.ipc1.tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DibujarTablero extends JPanel implements ActionListener {

    private JButton [][] botonesTablero;
    private PiezasTablero damas;
    private int xInicial=0,yInicial=0, cambio;


    public DibujarTablero() {
        iniciar();

        setLayout(new BorderLayout());
        this.setVisible(true);
        aniadirBotones();
        dibujarTablero();

    }
    public void iniciar(){
        botonesTablero = new JButton[8][8];
        damas = new PiezasTablero();
        damas.llenarTablero();

        for(int i=0; i<botonesTablero.length;i++){
            for(int j=0; j<botonesTablero[0].length; j++){
                botonesTablero[i][j]=new JButton();
                botonesTablero[i][j].setBorderPainted(false);
                botonesTablero[i][j].setContentAreaFilled(false);
                botonesTablero[i][j].setOpaque(false);
                botonesTablero[i][j].addActionListener(this);
            }
        }
    }

    public void dibujarTablero(){

        for(int i=0; i<botonesTablero.length; i++){
            for(int j=0; j<botonesTablero[0].length;j++){
                if(damas.getPieza(i,j)== damas.getBlancas()){
                    botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/fichaBlanca.png")));
                }else if(damas.getPieza(i,j)== damas.getRojas()){
                    botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/fichaRoja.png")));
                }else if(damas.getPieza(i,j)== damas.getVacio()){
                    botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/espacioVacio.png")));
                }else{
                    botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/muro.png")));
                }
            }
        }
    }

    public void moverFichas(){

        if(!damas.verificarGanador()) {
            for (int i = 0; i < botonesTablero.length; i++) {
                for (int j = 0; j < botonesTablero[0].length; j++) {
                    if (damas.getPieza(i, j) == damas.getBlancas()) {
                        botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/fichaBlanca.png")));
                    } else if (damas.getPieza(i, j) == damas.getRojas()) {
                        botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/fichaRoja.png")));
                    } else if (damas.getPieza(i, j) == damas.getBlancasR()) {
                        botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/blancaReina.png")));
                    } else if (damas.getPieza(i, j) == damas.getRojasR()) {
                        botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/rojaReina.png")));
                    } else if (damas.getPieza(i, j) == damas.getVacio()) {
                        botonesTablero[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesTablero/espacioVacio.png")));
                    }
                }
            }
        }else{
            damas.llenarTablero();
            dibujarTablero();
        }
    }

    public void aniadirBotones(){
        this.setLayout(new GridLayout(8,8));
        for(int i=0; i<botonesTablero.length;i++){
            for(int j=0; j<botonesTablero[0].length; j++){

                this.add(botonesTablero[i][j]);
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<botonesTablero.length; i++){
            for(int j=0; j<botonesTablero[0].length; j++){

                if(e.getSource() == botonesTablero[i][j]){

                    if(damas.verificarFicha(damas.isTurno(),i,j)){
                        xInicial = i;
                        yInicial = j;
                        cambio=1;
                    } else if (cambio==1) {
                        if(damas.moverFicha(damas.isTurno(),xInicial,yInicial,i,j)){
                            moverFichas();
                            damas.cambiarTurno();
                        }else{
                            JOptionPane.showMessageDialog(null,"MOVIMIENTO INVALIDO");
                        }

                        cambio=0;
                    }
                }
            }
        }
    }
}

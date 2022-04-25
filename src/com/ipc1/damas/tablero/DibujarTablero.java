package com.ipc1.damas.tablero;

import com.ipc1.Utilidades.Cronometro;
import com.ipc1.damas.VentanaDamas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DibujarTablero extends JPanel implements ActionListener {

    private Cronometro cronometro;
    private Thread hiloTiempo;
    private JButton [][] botonesTablero;
    private PiezasTablero damas;
    private int xInicial=0,yInicial=0, cambio;
    private VentanaDamas ventana;


    public DibujarTablero(VentanaDamas ventana) {
        this.ventana = ventana;
        iniciar();

        //setLayout(new BorderLayout());
        this.setBounds(0,0,500,500);
        this.setVisible(true);
        aniadirBotones();
        dibujarTablero();

    }
    public void iniciar(){
        botonesTablero = new JButton[8][8];
        damas = new PiezasTablero(this);
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
        cronometro = new Cronometro(ventana.getTiempo());
        hiloTiempo = new Thread(cronometro);

        hiloTiempo.start();
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
            hiloTiempo.interrupt();
            ventana.reiniciarContadores();
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

    //TURNO TRUE = JUGADOR 1, TURNO FALSE = JUGADOR 2

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

    public void contadorPiezas(){
        if(damas.isTurno()){
            ventana.setContadorPiezas1(1);
        }else{
            ventana.setContadorPiezas2(1);
        }
    }

    public Thread getHiloTiempo(){
        return hiloTiempo;
    }
}

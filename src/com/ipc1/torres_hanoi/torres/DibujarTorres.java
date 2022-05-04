package com.ipc1.torres_hanoi.torres;

import com.ipc1.archivos.ReporteJuegos;
import com.ipc1.torres_hanoi.VentanaHanoi;
import com.ipc1.utilidades.Cronometro;
import com.ipc1.ventanas.VentanaReportesHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DibujarTorres extends JLayeredPane implements ActionListener {

    private Cronometro cronometro;
    private Thread hiloTiempo;
    private JButton[][] bloques;
    private int cantidadBloques;
    private BloquesTorres verBloques;
    private int torreInicial, moverTorre = 0;
    private VentanaHanoi ventana;
    private int modoJuego;

    public DibujarTorres(VentanaHanoi ventana, int cantidadBloques, int modoJuego) {
        this.ventana = ventana;
        //this.setLayout(new BorderLayout());
        this.cantidadBloques = cantidadBloques;
        this.modoJuego = modoJuego;
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setBounds(0, 0, 1100, 700);

        iniciar();
        llenarTorres();
    }

    public void iniciar() {
        bloques = new JButton[3][8];
        verBloques = new BloquesTorres(cantidadBloques, this);
        verBloques.llenarBloques();

        JLabel fondo = new JLabel();
        fondo.setBounds(0, 100, 1100, 500);
        final ImageIcon imagenTorres = new ImageIcon(getClass().getResource("/imagenesHanoi/torres.png"));
        fondo.setIcon(new ImageIcon(imagenTorres.getImage().getScaledInstance(1100, 500, Image.SCALE_SMOOTH)));
        this.add(fondo, Integer.valueOf(0));

        for (int i = 0; i < bloques.length; i++) {
            for (int j = 0; j < bloques[0].length; j++) {
                bloques[i][j] = new JButton();
                bloques[i][j].setBorderPainted(false);
                bloques[i][j].setContentAreaFilled(false);
                bloques[i][j].setOpaque(false);
                bloques[i][j].addActionListener(this);
            }
        }
        posicionarBloques();
    }

    public void posicionarBloques() {

        //PARA EL BLOQUE 1
        int moverYBloque1 = 0;
        for (int i = 0; i < bloques[0].length; i++) {

            //TORRE 1
            bloques[0][i].setBounds(70, 523 - moverYBloque1, 275, 40);
            this.add(bloques[0][i], Integer.valueOf(1));

            //TORRE 2
            bloques[1][i].setBounds(416, 523 - moverYBloque1, 275, 40);
            this.add(bloques[1][i], Integer.valueOf(1));

            //TORRE 3
            bloques[2][i].setBounds(760, 523 - moverYBloque1, 275, 40);
            this.add(bloques[2][i], Integer.valueOf(1));

            moverYBloque1 += 40;
        }

    }

    public void llenarTorres() {

        cronometro = new Cronometro(ventana.getTiempo());
        hiloTiempo = new Thread(cronometro);
        hiloTiempo.start();

        for (int i = 0; i < bloques.length; i++) {
            for (int j = 0; j < bloques[0].length; j++) {

                if (verBloques.getBloque(i, j) == 8) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque8.png")));
                } else if (verBloques.getBloque(i, j) == 7) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque7.png")));
                } else if (verBloques.getBloque(i, j) == 6) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque6.png")));
                } else if (verBloques.getBloque(i, j) == 5) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque5.png")));
                } else if (verBloques.getBloque(i, j) == 4) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque4.png")));
                } else if (verBloques.getBloque(i, j) == 3) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque3.png")));
                } else if (verBloques.getBloque(i, j) == 2) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque2.png")));
                } else if (verBloques.getBloque(i, j) == 1) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque1.png")));
                } else {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloqueVacio.png")));
                }
            }
        }
    }

    public void moverBloques() {

        for (int i = 0; i < bloques.length; i++) {
            for (int j = 0; j < bloques[0].length; j++) {

                if (verBloques.getBloque(i, j) == 8) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque8.png")));
                } else if (verBloques.getBloque(i, j) == 7) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque7.png")));
                } else if (verBloques.getBloque(i, j) == 6) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque6.png")));
                } else if (verBloques.getBloque(i, j) == 5) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque5.png")));
                } else if (verBloques.getBloque(i, j) == 4) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque4.png")));
                } else if (verBloques.getBloque(i, j) == 3) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque3.png")));
                } else if (verBloques.getBloque(i, j) == 2) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque2.png")));
                } else if (verBloques.getBloque(i, j) == 1) {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloque1.png")));
                } else {
                    bloques[i][j].setIcon(new ImageIcon(getClass().getResource("/imagenesHanoi/bloqueVacio.png")));
                }
            }
        }

        if (verBloques.verificarGanador()) {
            hiloTiempo.interrupt();
            JOptionPane.showMessageDialog(null, "FELICIDADES HA GANADO");
            //TENGO QUE ESCRIBIR REPORTES
            agregarReportes();
            VentanaReportesHanoi ventanaReportesHanoi = new VentanaReportesHanoi(ventana);
            ventanaReportesHanoi.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (modoJuego == 1) {
            for (int i = 0; i < bloques.length; i++) {
                for (int j = 0; j < bloques[0].length; j++) {


                    if (e.getSource() == bloques[i][j]) {

                        if (moverTorre == 0) {
                            torreInicial = i;
                            moverTorre = 1;

                        } else if (moverTorre == 1) {
                            if (verBloques.moverBloque(torreInicial, i)) {
                                ventana.setContadorMov();
                                moverBloques();
                            } else {
                                JOptionPane.showMessageDialog(null, "NO SE PUEDE REALIZAR EL MOVIMIENTO");
                            }
                            moverTorre = 0;
                        }
                    }
                }
            }
        }
    }

    public VentanaHanoi getVentana() {
        return ventana;
    }

    public BloquesTorres getVerBloques() {
        return verBloques;
    }

    public void agregarReportes(){
        if(modoJuego==1){
            String reporte =ventana.getNombreJugador()+",1,0,0,"+ventana.getContadorMov()+","+ventana.getTiempo().getText();
            ReporteJuegos reportesHanoi = new ReporteJuegos("ReporteHanoi.txt");
            reportesHanoi.escribirReporte(reporte);
        }
    }

    public Thread getHiloTiempo() {
        return hiloTiempo;
    }

    public void reiniciarPartida(){
        cronometro.reiniciarTiempo();
        verBloques.llenarBloques();
        ventana.reiniciarContador();
        llenarTorres();
        moverTorre = 0;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }
}

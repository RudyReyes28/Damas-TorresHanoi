package com.ipc1.damas.tablero;

import com.ipc1.archivos.ReporteJuegos;
import com.ipc1.utilidades.Cronometro;
import com.ipc1.damas.VentanaDamas;
import com.ipc1.ventanas.VentanaReporteDamas;

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
    private int modo;  //1 versus //2 computadora
    private int movimientosJ1, movimientosJ2;


    public DibujarTablero(VentanaDamas ventana, int modo) {
        this.ventana = ventana;
        this.modo = modo;
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
        movimientosJ1=0;
        movimientosJ2=0;
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
            hiloTiempo.interrupt();
            //REPORTES
            llenarReportes();
            VentanaReporteDamas ventanaReporteDamas = new VentanaReporteDamas(ventana);
            ventanaReporteDamas.setVisible(true);



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

                    if(modo==1) {
                        if (damas.verificarFicha(damas.isTurno(), i, j)) {
                            xInicial = i;
                            yInicial = j;
                            cambio = 1;
                        } else if (cambio == 1) {
                            if (damas.moverFicha(damas.isTurno(), xInicial, yInicial, i, j)) {
                                contadorMovimientos();
                                moverFichas();
                                damas.cambiarTurno();
                            } else {
                                JOptionPane.showMessageDialog(null, "MOVIMIENTO INVALIDO");
                            }

                            cambio = 0;
                        }
                    }else if(modo ==2){
                        modoComputadora(i,j);
                    }
                }
            }
        }
    }

    public void modoComputadora(int i, int j){
        //es turno del jugador
        if(damas.isTurno()){
            if (damas.verificarFicha(damas.isTurno(), i, j)) {
                xInicial = i;
                yInicial = j;
                cambio = 1;
            } else if (cambio == 1) {
                if (damas.moverFicha(damas.isTurno(), xInicial, yInicial, i, j)) {
                    contadorMovimientos();
                    moverFichas();
                    damas.cambiarTurno();
                } else {
                    JOptionPane.showMessageDialog(null, "MOVIMIENTO INVALIDO");
                }

                cambio = 0;
            }
        }else{
            while(!movimientoBot()){

            }
            contadorMovimientos();
            damas.cambiarTurno();
        }

    }

    public boolean movimientoBot(){
        boolean repetir = false;
        int x = (int) (Math.random()*8);
        int y = (int) (Math.random()*8);
        int xFinal = (int) (Math.random()*8);
        int yFinal = (int) (Math.random()*8);

        if(damas.moverFicha(damas.isTurno(),x,y,xFinal,yFinal)){
            moverFichas();
            return true;
        }

        return repetir;
    }

    public void contadorPiezas(){
        if(damas.isTurno()){
            ventana.setContadorPiezas1(1);
        }else{
            ventana.setContadorPiezas2(1);
        }
    }

    public void contadorMovimientos(){
        if(damas.isTurno()){
            movimientosJ1 ++;
        }else{
            movimientosJ2 ++;
        }
    }

    public Thread getHiloTiempo(){
        return hiloTiempo;
    }

    public void llenarReportes(){
        //reportes de partidas ganadas y perdidas
        String reporteJugador1 = ventana.getNombreJugador1()+",1,";
        String reporteJugador2 = ventana.getNombreJugador2()+",1,";
        if(damas.retornarGanador()){
            reporteJugador1 += "1,0,";
            reporteJugador2 += "0,1,";
        }else{
            reporteJugador1 += "0,1,";
            reporteJugador2 += "1,0,";
        }
        reporteJugador1 += movimientosJ1+","+ventana.getTiempo().getText();
        reporteJugador2 += movimientosJ2+","+ventana.getTiempo().getText();

        ReporteJuegos reporteJuegosDamas = new ReporteJuegos("ReporteDamas.txt");
        reporteJuegosDamas.escribirReporte(reporteJugador1);
        reporteJuegosDamas.escribirReporte(reporteJugador2);
    }

    public void reiniciarPartida(){
        cronometro.reiniciarTiempo();
        damas.llenarTablero();
        ventana.reiniciarContadores();
        dibujarTablero();
    }

    public PiezasTablero getDamas() {
        return damas;
    }

    public int getMovimientosJ1() {
        return movimientosJ1;
    }

    public int getMovimientosJ2() {
        return movimientosJ2;
    }

    public void setMovimientosJ1(int movimientosJ1) {
        this.movimientosJ1 = movimientosJ1;
    }

    public void setMovimientosJ2(int movimientosJ2) {
        this.movimientosJ2 = movimientosJ2;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }
}

package com.ipc1.ventanas;

import com.ipc1.archivos.ReporteJuegos;
import com.ipc1.damas.VentanaDamas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaReporteDamas extends JFrame {

    private DefaultTableModel modelo, modeloVictoria;
    private JTable tablaReportes, tablaVictorias;
    private JPanel panel;
    private JButton regresarInicio, reintentar,verListado;
    private String [][] datosReportes, datosVictorias;
    private VentanaDamas ventanaDamas;

    public VentanaReporteDamas(VentanaDamas ventanaDamas){
        this.ventanaDamas = ventanaDamas;
        this.setSize(1050,550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("REPORTES");
        this.setVisible(true);
        this.setLayout(null);

        iniciarComponentes();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void iniciarComponentes(){
        colocarPanel();
        colocarEtiquetas();
        colocarBotones();
        colocarTabla();
        colocarEventos();
        repaint();
    }

    public void colocarPanel(){
        panel = new JPanel();
        panel.setBounds(0,0,1050,550);
        panel.setLayout(null);
        this.add(panel);

    }

    public void colocarTabla(){

        modelo = new  DefaultTableModel();

        modelo.addColumn("Jugador");
        modelo.addColumn("Part. Jugadas");
        modelo.addColumn("Part. Ganadas");
        modelo.addColumn("Part. Perdidas");
        modelo.addColumn("Cant. Movimientos");
        modelo.addColumn("Prom. Movimientos");

        modeloVictoria = new DefaultTableModel();
        modeloVictoria.addColumn("Jugador");
        modeloVictoria.addColumn("Cant. Movimientos");
        modeloVictoria.addColumn("Tiempo");


        aniadirReportes();

        tablaReportes = new JTable(modelo);
        tablaReportes.setBounds(50,60,600,350);
        panel.add(tablaReportes);

        JScrollPane scroll = new JScrollPane(tablaReportes,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50,60,600,350);
        panel.add(scroll);




        tablaVictorias = new JTable(modeloVictoria);
        tablaVictorias.setBounds(700,60,300,350);
        panel.add(tablaVictorias);

        JScrollPane scrollVictorias = new JScrollPane(tablaVictorias,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollVictorias.setBounds(700,60,300,350);
        panel.add(scrollVictorias);


    }

    public void colocarEtiquetas(){
        JLabel partidaCorta = new JLabel();
        partidaCorta.setBounds(750,15,200,30);
        partidaCorta.setText("Datos Partida mas corta");
        partidaCorta.setHorizontalAlignment(SwingConstants.CENTER);
        partidaCorta.setFont(new Font("Chilanka",Font.BOLD,16));
        panel.add(partidaCorta);

        JLabel etiquetaReporte = new JLabel();
        etiquetaReporte.setBounds(180,15,300,30);
        etiquetaReporte.setText("Reportes de todas las partidas");
        etiquetaReporte.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaReporte.setFont(new Font("Chilanka",Font.BOLD,16));
        panel.add(etiquetaReporte);
    }

    public void colocarBotones(){
        regresarInicio = new JButton();
        regresarInicio.setBounds(200,460,180,30);
        regresarInicio.setText("Regresar al inicio");
        regresarInicio.setHorizontalAlignment(SwingConstants.CENTER);
        regresarInicio.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(regresarInicio);

        reintentar = new JButton();
        reintentar.setBounds(460,460,150,30);
        reintentar.setText("Jugar de nuevo");
        reintentar.setHorizontalAlignment(SwingConstants.CENTER);
        reintentar.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(reintentar);

        verListado = new JButton();
        verListado.setBounds(700,460,200,30);
        verListado.setText("Ver todos los reportes");
        verListado.setHorizontalAlignment(SwingConstants.CENTER);
        verListado.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(verListado);
    }

    public void aniadirReportes(){
        ReporteJuegos reporteJuegosDamas = new ReporteJuegos("ReporteDamas.txt");
        int cantFilas = reporteJuegosDamas.cantidadFilas("ReporteDamas.txt");

        String [][] datosTemporal = new String[cantFilas][6];

        reporteJuegosDamas.leerArchivo(datosTemporal,"ReporteDamas.txt");

        datosReportes = new String[cantFilas][6];
        datosVictorias = new String[cantFilas][3];

        int contador=0;
        int contadirMejorM = 0;

        for(int i=0; i<datosTemporal.length; i++){
            String nombreTemporal = datosTemporal[i][0];
            int cantPartidas = 0;
            int partGanadas = 0;
            int partPerdidas = 0;
            int totalMovimientos = 0;
            int mejorMovimiento = 0;
            String mejorTiempo="";
            for(int j=0; j<datosTemporal.length; j++){
                if(nombreTemporal!=null && datosTemporal[j][0]!=null) {

                    if (nombreTemporal.equals(datosTemporal[j][0])) {
                        cantPartidas += Integer.parseInt(datosTemporal[j][1]);
                        partGanadas += Integer.parseInt(datosTemporal[j][2]);
                        partPerdidas += Integer.parseInt(datosTemporal[j][3]);
                        totalMovimientos += Integer.parseInt(datosTemporal[j][4]);

                        if(datosTemporal[j][2].equals("1") ) {
                            mejorMovimiento = Integer.parseInt(datosTemporal[i][4]);
                            if (Integer.parseInt(datosTemporal[j][4]) < mejorMovimiento) ;
                            {
                                mejorMovimiento = Integer.parseInt(datosTemporal[j][4]);
                                mejorTiempo = datosTemporal[j][5];
                            }
                        }
                        datosTemporal[j][0] = null;
                    }
                }
            }

            if(nombreTemporal!=null) {
                datosReportes[contador][0] = nombreTemporal;
                datosReportes[contador][1] = String.valueOf(cantPartidas);
                datosReportes[contador][2] = String.valueOf(partGanadas);
                datosReportes[contador][3] = String.valueOf(partPerdidas);
                datosReportes[contador][4] = String.valueOf(totalMovimientos);
                datosReportes[contador][5] = String.valueOf(totalMovimientos / cantPartidas);

                if(!mejorTiempo.equals("")) {
                    datosVictorias[contadirMejorM][0] = nombreTemporal;
                    datosVictorias[contadirMejorM][1] = String.valueOf(mejorMovimiento);
                    datosVictorias[contadirMejorM][2] = mejorTiempo;
                    contadirMejorM++;
                }
                contador++;
            }

        }

        for(int i=0; i<datosReportes.length;i++){

            if(datosReportes[i][0]!=null) {
                modelo.addRow(datosReportes[i]);
            }

            if(datosVictorias[i][0]!=null){
                modeloVictoria.addRow(datosVictorias[i]);
            }
        }

    }

    public void colocarEventos(){
        ActionListener eventoRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicio ventanaInicio = new VentanaInicio();
                dispose();
                ventanaDamas.dispose();
            }
        };

        ActionListener eventoReintentar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDamas.getTablero().reiniciarPartida();
                dispose();
            }
        };

        ActionListener eventoVerListado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListadoRDamas listadoRDamas = new VentanaListadoRDamas(VentanaReporteDamas.this,true);
                listadoRDamas.setVisible(true);
            }
        };

        regresarInicio.addActionListener(eventoRegresar);
        reintentar.addActionListener(eventoReintentar);
        verListado.addActionListener(eventoVerListado);
    }
}

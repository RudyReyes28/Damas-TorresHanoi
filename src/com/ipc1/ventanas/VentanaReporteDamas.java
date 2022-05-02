package com.ipc1.ventanas;

import com.ipc1.archivos.ReporteJuegos;
import com.ipc1.damas.VentanaDamas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaReporteDamas extends JFrame {

    private JTable tablaReportes;
    private DefaultTableModel modelo;
    private JPanel panel;
    private JButton regresarInicio, reintentar;
    private String [][] datosReportes;
    private VentanaDamas ventanaDamas;

    public VentanaReporteDamas(VentanaDamas ventanaDamas){
        this.ventanaDamas = ventanaDamas;
        this.setSize(1000,550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("REPORTES");
        this.setVisible(true);
        this.setLayout(null);
        iniciarComponentes();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void iniciarComponentes(){
        colocarPanel();
        colocarEtiquetas();
        colocarBotones();
        colocarTabla();
        colocarEventos();
    }

    public void colocarPanel(){
        panel = new JPanel();
        panel.setBounds(0,0,1000,550);
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

        aniadirReportes();

        tablaReportes = new JTable(modelo);
        tablaReportes.setBounds(50,60,700,350);
        panel.add(tablaReportes);

        JScrollPane scroll = new JScrollPane(tablaReportes,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50,60,700,350);
        panel.add(scroll);



    }

    public void colocarEtiquetas(){
        JLabel partidaCorta = new JLabel();
        partidaCorta.setBounds(715,15,200,30);
        partidaCorta.setText("Datos Partida mas corta");
        partidaCorta.setHorizontalAlignment(SwingConstants.CENTER);
        partidaCorta.setFont(new Font("Chilanka",Font.BOLD,16));
        panel.add(partidaCorta);
    }

    public void colocarBotones(){
        regresarInicio = new JButton();
        regresarInicio = new JButton();
        regresarInicio.setBounds(300,460,180,30);
        regresarInicio.setText("Regresar al inicio");
        regresarInicio.setHorizontalAlignment(SwingConstants.CENTER);
        regresarInicio.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(regresarInicio);

        reintentar = new JButton();
        reintentar = new JButton();
        reintentar.setBounds(500,460,150,30);
        reintentar.setText("Jugar de nuevo");
        reintentar.setHorizontalAlignment(SwingConstants.CENTER);
        reintentar.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(reintentar);
    }

    public void aniadirReportes(){
        ReporteJuegos reporteJuegosDamas = new ReporteJuegos("ReporteDamas.txt");
        int cantFilas = reporteJuegosDamas.cantidadFilas("ReporteDamas.txt");

        String [][] datosTemporal = new String[cantFilas][6];

        reporteJuegosDamas.leerArchivo(datosTemporal,"ReporteDamas.txt");

        datosReportes = new String[cantFilas][6];

        int contador=0;

        for(int i=0; i<datosTemporal.length; i++){
            String nombreTemporal = datosTemporal[i][0];
            int cantPartidas = 0;
            int partGanadas = 0;
            int partPerdidas = 0;
            int totalMovimientos = 0;
            for(int j=0; j<datosTemporal.length; j++){
                if(nombreTemporal!=null && datosTemporal[j][0]!=null) {
                    if (nombreTemporal.equals(datosTemporal[j][0])) {
                        cantPartidas += Integer.parseInt(datosTemporal[j][1]);
                        partGanadas += Integer.parseInt(datosTemporal[j][2]);
                        partPerdidas += Integer.parseInt(datosTemporal[j][3]);
                        totalMovimientos += Integer.parseInt(datosTemporal[j][4]);

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
                contador++;
            }

        }

        for(int i=0; i<datosReportes.length;i++){

            if(datosReportes[i][0]!=null) {
                modelo.addRow(datosReportes[i]);
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

        regresarInicio.addActionListener(eventoRegresar);
        reintentar.addActionListener(eventoReintentar);
    }
}

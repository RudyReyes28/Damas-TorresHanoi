package com.ipc1.ventanas;

import com.ipc1.archivos.ReporteJuegos;
import com.ipc1.damas.VentanaDamas;
import com.ipc1.torres_hanoi.VentanaHanoi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaReportesHanoi extends JFrame {

    private JTable tablaReportes;
    private DefaultTableModel modelo;
    private JPanel panel;
    private JButton regresarInicio, reintentar;
    private String [][] datosReportes;
    private JLabel masGanado, masPerdido;
    private VentanaHanoi ventanaHanoi;
    private double tiempoPromedio =0;

    public VentanaReportesHanoi(VentanaHanoi ventanaHanoi) {
        this.ventanaHanoi = ventanaHanoi;
        this.setSize(1000,550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("REPORTES HANOI");
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
        modelo.addColumn("Part. Ganadas");
        modelo.addColumn("Part. Perdidas");
        modelo.addColumn("Part. Abandonadas");
        modelo.addColumn("Prom. Movimientos");
        modelo.addColumn("Prom. Tiempo");

        aniadirReportes();

        tablaReportes = new JTable(modelo);
        tablaReportes.setBounds(50,60,600,350);
        panel.add(tablaReportes);

        JScrollPane scroll = new JScrollPane(tablaReportes,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50,60,600,350);
        panel.add(scroll);

    }

    public void colocarEtiquetas(){
        JLabel etiqueta1 = new JLabel();
        etiqueta1.setBounds(715,60,200,30);
        etiqueta1.setText("Jugador con más victorias: ");
        etiqueta1.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta1.setFont(new Font("Chilanka",Font.BOLD,15));
        panel.add(etiqueta1);

        masGanado = new JLabel();
        masGanado.setBounds(715,92,200,30);
        //masGanado.setText("Datos Partida mas corta");
        masGanado.setOpaque(true);
        masGanado.setBackground(Color.white);
        masGanado.setHorizontalAlignment(SwingConstants.CENTER);
        masGanado.setFont(new Font("Chilanka",Font.BOLD,16));
        panel.add(masGanado);

        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(715,150,200,30);
        etiqueta2.setText("Jugador con más derrotas: ");
        etiqueta2.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta2.setFont(new Font("Chilanka",Font.BOLD,15));
        panel.add(etiqueta2);

        masPerdido = new JLabel();
        masPerdido.setBounds(715,182,200,30);
        //masPerdido.setText("Datos Partida mas corta");
        masPerdido.setOpaque(true);
        masPerdido.setBackground(Color.white);
        masPerdido.setHorizontalAlignment(SwingConstants.CENTER);
        masPerdido.setFont(new Font("Chilanka",Font.BOLD,16));
        panel.add(masPerdido);
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
        String nombreArchivo = "ReporteHanoi.txt";
        ReporteJuegos reporteJuegosHanoi = new ReporteJuegos(nombreArchivo);
        int cantFilas = reporteJuegosHanoi.cantidadFilas(nombreArchivo);

        String [][] datosTemporal = new String[cantFilas][6];

        reporteJuegosHanoi.leerArchivo(datosTemporal,nombreArchivo);

        datosReportes = new String[cantFilas][6];

        int contador=0;

        for(int i=0; i<datosTemporal.length; i++) {
            String nombreTemporal = datosTemporal[i][0];
            int partGanadas = 0;
            int partPerdidas = 0;
            int partAbandonadas = 0;
            int totalMovimientos = 0;
            tiempoPromedio = 0;
            for (int j = 0; j < datosTemporal.length; j++) {
                if (nombreTemporal != null && datosTemporal[j][0] != null) {
                    if (nombreTemporal.equals(datosTemporal[j][0])) {

                        partGanadas += Integer.parseInt(datosTemporal[j][1]);
                        partPerdidas += Integer.parseInt(datosTemporal[j][2]);
                        partAbandonadas += Integer.parseInt(datosTemporal[j][3]);
                        totalMovimientos += Integer.parseInt(datosTemporal[j][4]);
                        calcularTiempoPromedio(datosTemporal[j][5]);

                        datosTemporal[j][0] = null;
                    }
                }
            }

            if(nombreTemporal!=null){
                datosReportes[contador][0] = nombreTemporal;
                datosReportes[contador][1] = String.valueOf(partGanadas);
                datosReportes[contador][2] = String.valueOf(partPerdidas);
                datosReportes[contador][3] = String.valueOf(partAbandonadas);
                datosReportes[contador][4] = String.valueOf(totalMovimientos/(partGanadas+partPerdidas));

                double tiempoRe = Math.round((tiempoPromedio/(partGanadas+partPerdidas))*100.0)/100.0;
                datosReportes[contador][5] = String.valueOf(tiempoRe)+" min";
                contador++;
            }

        }

        for(int i=0; i<datosReportes.length;i++){

            if(datosReportes[i][0]!=null) {
                modelo.addRow(datosReportes[i]);
            }
        }

        aniadirMPJugador();
    }

    public void calcularTiempoPromedio(String tiempo){
        int cant = tiempo.length();

        if(cant==5){
            int segundos = Integer.parseInt(tiempo.substring(4,5));
            tiempoPromedio+= segundos/100f;
        }else if(cant == 6){
            String digito1 = tiempo.substring(4,5);

            if(digito1.equals(":")){
                int minuto = Integer.parseInt(tiempo.substring(2,4));
                int segundos = Integer.parseInt(tiempo.substring(5,6));
                tiempoPromedio+= minuto+(segundos/100f);
            }else{
                int minuto = Integer.parseInt(tiempo.substring(2,3));
                int segundos = Integer.parseInt(tiempo.substring(4,6));
                tiempoPromedio+= minuto+(segundos/100f);
            }
        }else if(cant==7){
            int minuto = Integer.parseInt(tiempo.substring(2,3));
            int segundos = Integer.parseInt(tiempo.substring(5,7));
            tiempoPromedio+= minuto+(segundos/100f);
        }
    }

    public void aniadirMPJugador(){
        int cantJugadores = 0;
        for(int i=0; i<datosReportes.length; i++){
            if(datosReportes[i][0]!=null){
                cantJugadores++;
            }
        }

        String mejorJugador = "";
        String peorJugador = "";

        int victorias = 0;
        int derrotas = 0;

        for(int i=0; i<cantJugadores; i++){
            if(datosReportes[i][0] !=null){
                if(victorias < Integer.parseInt(datosReportes[i][1])){
                    victorias = Integer.parseInt(datosReportes[i][1]);
                    mejorJugador = datosReportes[i][0];
                }

                if(derrotas < Integer.parseInt(datosReportes[i][2])){
                    derrotas = Integer.parseInt(datosReportes[i][2]);
                    peorJugador = datosReportes[i][0];
                }
            }
        }

        /*if(mejorJugador.equals(peorJugador)){
            if(victorias>=derrotas){
                masGanado.setText(mejorJugador);
            }else{
                masPerdido.setText(peorJugador);
            }
        }else{
        */

            masGanado.setText(mejorJugador);
            masPerdido.setText(peorJugador);
        //}
    }

    public void colocarEventos(){
        ActionListener eventoRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicio ventanaInicio = new VentanaInicio();
                dispose();
                ventanaHanoi.dispose();
            }
        };

        ActionListener eventoReintentar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaHanoi.getBloqueIndividual().setPosicionFinal(0);
                ventanaHanoi.getTorresHanoi().reiniciarPartida();
                dispose();
            }
        };

        regresarInicio.addActionListener(eventoRegresar);
        reintentar.addActionListener(eventoReintentar);
    }
}

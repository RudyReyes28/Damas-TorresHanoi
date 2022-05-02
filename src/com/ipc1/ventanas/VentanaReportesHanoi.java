package com.ipc1.ventanas;

import com.ipc1.damas.VentanaDamas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaReportesHanoi extends JFrame {

    private JTable tablaReportes;
    private DefaultTableModel modelo;
    private JPanel panel;
    private JButton regresarInicio, reintentar;
    private String [][] datosReportes;
    private JLabel masGanado, masPerdido;
    //private VentanaDamas ventanaDamas;

    public VentanaReportesHanoi() {
        //this.ventanaDamas = ventanaDamas;
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

        //aniadirReportes();

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
}

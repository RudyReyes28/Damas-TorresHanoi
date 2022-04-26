package com.ipc1.ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaReporteDamas extends JFrame {

    private JTable tablaReportes;
    private DefaultTableModel modelo;
    private JPanel panel;
    private JButton regresarInicio, reintentar;

    public VentanaReporteDamas(){
        this.setSize(1000,550);
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
        modelo.addColumn("Total Movimientos");
        modelo.addColumn("Tiempo");

        String ejemplo [] = {"Juan","10","5","5","20","45","80","0:5:10"};
        modelo.addRow(ejemplo);

        tablaReportes = new JTable(modelo);
        tablaReportes.setBounds(50,60,900,350);
        panel.add(tablaReportes);

        JScrollPane scroll = new JScrollPane(tablaReportes,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50,60,900,350);
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
}

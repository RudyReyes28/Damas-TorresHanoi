package com.ipc1.ventanas;

import com.ipc1.archivos.ReporteJuegos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaListadoRDamas extends JDialog {

    private DefaultTableModel modelo;
    private JTable tablaReportes;
    private JPanel panel;
    private JButton regresar;
    private String [][] datosReportes;

    public VentanaListadoRDamas(Frame owner, boolean modal) {
        super(owner, modal);

        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Listado Reportes de cada partida");
        iniciarComponentes();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        panel.setBounds(0,0,500,500);
        panel.setLayout(null);
        this.add(panel);
    }

    public void colocarEtiquetas(){
        JLabel partidaCorta = new JLabel();
        partidaCorta.setBounds(0,15,500,30);
        partidaCorta.setText("Listado de todas las partidas");
        partidaCorta.setHorizontalAlignment(SwingConstants.CENTER);
        partidaCorta.setFont(new Font("Chilanka",Font.BOLD,16));
        panel.add(partidaCorta);
    }

    public void colocarBotones(){
        regresar = new JButton();
        regresar.setBounds(200,400,100,30);
        regresar.setText("Regresar");
        regresar.setHorizontalAlignment(SwingConstants.CENTER);
        regresar.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(regresar);
    }

    public void colocarTabla(){
        modelo = new  DefaultTableModel();

        modelo.addColumn("Jugador");
        modelo.addColumn("Cant. Movimientos");
        modelo.addColumn("Tiempo");

        aniadirReportes();

        tablaReportes = new JTable(modelo);
        tablaReportes.setBounds(50,60,400,300);
        panel.add(tablaReportes);

        JScrollPane scroll = new JScrollPane(tablaReportes,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50,60,400,300);
        panel.add(scroll);
    }

    public void aniadirReportes(){
        ReporteJuegos reporteJuegosDamas = new ReporteJuegos("ReporteDamas.txt");
        int cantFilas = reporteJuegosDamas.cantidadFilas("ReporteDamas.txt");

        String [][] datosTemporal = new String[cantFilas][6];
        datosReportes = new String[cantFilas][3];

        reporteJuegosDamas.leerArchivo(datosTemporal,"ReporteDamas.txt");

        for(int i=0; i<datosTemporal.length;i++){
            datosReportes[i][0] = datosTemporal[i][0];
            datosReportes[i][1] = datosTemporal[i][4];
            datosReportes[i][2] = datosTemporal[i][5];

            modelo.addRow(datosReportes[i]);
        }
    }

    public void colocarEventos(){
        ActionListener eventoRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };

        regresar.addActionListener(eventoRegresar);
    }

}

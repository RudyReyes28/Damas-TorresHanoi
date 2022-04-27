package com.ipc1.ventanas;

import com.ipc1.damas.VentanaDamas;
import com.ipc1.torres_hanoi.VentanaHanoi;
import org.w3c.dom.events.EventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {
    private JLayeredPane panelVentana;
    private JLabel tituloDamas, tituloHanoi;
    private JButton jugarDamas, jugarHanoi;

    public VentanaInicio() throws HeadlessException {
        this.setSize(1000,510);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("INICIAR JUEGOS");
        this.setVisible(true);
        iniciarComponentes();
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniciarComponentes(){
        agregarPanel();
        agregarEtiquetas();
        agregarBotones();
        agregarEventos();
    }

    public void agregarPanel(){
        panelVentana = new JLayeredPane();
        panelVentana.setBounds(0, 0, 1000, 480);
        this.add(panelVentana);

        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 1000, 480);
        final ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenesVentana/imagenDamasHanoi.png"));
        fondo.setIcon(new ImageIcon(imagenFondo.getImage().getScaledInstance(1000, 480, Image.SCALE_SMOOTH)));

        panelVentana.add(fondo, Integer.valueOf(0));

    }

    public void agregarEtiquetas(){
        tituloDamas = new JLabel();
        tituloDamas.setBounds(70, 170,350,40);
        tituloDamas.setText("¡JUGAR DAMAS!");
        tituloDamas.setHorizontalAlignment(SwingConstants.CENTER);
        tituloDamas.setForeground(Color.BLUE);
        tituloDamas.setFont(new Font("Impact",Font.BOLD,30));

        tituloHanoi = new JLabel();
        tituloHanoi.setBounds(500, 170,470,40);
        tituloHanoi.setText("¡JUGAR TORRES DE HANOI!");
        tituloHanoi.setHorizontalAlignment(SwingConstants.CENTER);
        tituloHanoi.setForeground(Color.BLUE);
        tituloHanoi.setFont(new Font("Impact",Font.BOLD,30));

        panelVentana.add(tituloDamas,Integer.valueOf(1));
        panelVentana.add(tituloHanoi,Integer.valueOf(1));

    }

    public void agregarBotones(){
        final ImageIcon icono = new ImageIcon(getClass().getResource("/imagenesVentana/iconoJugar1.png"));
        jugarDamas = new JButton();
        jugarDamas.setBounds(150,270,150,66);
        jugarDamas.setBorderPainted(false);
        jugarDamas.setContentAreaFilled(false);
        jugarDamas.setHorizontalAlignment(SwingConstants.CENTER);
        jugarDamas.setIcon(new ImageIcon(icono.getImage().getScaledInstance(150, 66, Image.SCALE_SMOOTH)));


        jugarHanoi = new JButton();
        jugarHanoi.setBounds(665,270,150,66);
        jugarHanoi.setBorderPainted(false);
        jugarHanoi.setContentAreaFilled(false);
        jugarHanoi.setHorizontalAlignment(SwingConstants.CENTER);
        jugarHanoi.setIcon(new ImageIcon(icono.getImage().getScaledInstance(150, 66, Image.SCALE_SMOOTH)));


        panelVentana.add(jugarDamas,Integer.valueOf(1));
        panelVentana.add(jugarHanoi,Integer.valueOf(1));
    }

    public void agregarEventos(){
        ActionListener empezarDamas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicioDamas iniciar = new VentanaInicioDamas();
                iniciar.setVisible(true);
                dispose();
            }
        };

        ActionListener empezarHanoi = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicioHanoi iniciarHanoi = new VentanaInicioHanoi();
                iniciarHanoi.setVisible(true);
                dispose();
            }
        };

        jugarDamas.addActionListener(empezarDamas);
        jugarHanoi.addActionListener(empezarHanoi);
    }
}

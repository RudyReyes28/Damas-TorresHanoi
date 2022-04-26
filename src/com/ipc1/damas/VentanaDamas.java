package com.ipc1.damas;

import com.ipc1.damas.tablero.DibujarTablero;
import com.ipc1.ventanas.VentanaInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class VentanaDamas extends JFrame {

    private DibujarTablero tablero;
    private JPanel panelOpciones;
    private JLabel tiempo, piezasJugador1, piezasJugador2;
    private String nombreJugador1, nombreJugador2;
    private int contadorPiezas1, contadorPiezas2;
    private JMenuItem guardarPartida, regresarPrincipal;
    private int modoJuego;


    public VentanaDamas(String nombreJugador1, String nombreJugador2, int modoJuego) {
        this.nombreJugador1=nombreJugador1;
        this.nombreJugador2=nombreJugador2;
        this.modoJuego = modoJuego;
        this.setSize(700,556);
        //this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setTitle("DAMAS");
        tiempo = new JLabel();
        iniciarComponentes();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniciarComponentes(){
        iniciarJuego();
        colocarTablero();
        colocarEtiquetas();
        colocarMenu();
    }

    public void iniciarJuego(){
        tablero = new DibujarTablero(this, modoJuego);

    }

    public void colocarTablero(){
       ///this.setLayout(new BorderLayout());
        this.setLayout(null);
        this.add(tablero, BorderLayout.CENTER);
        agregarPanelOpciones();
    }

    public void agregarPanelOpciones(){
        panelOpciones = new JPanel();
        panelOpciones.setBackground(Color.CYAN);
        panelOpciones.setLayout(null);
        panelOpciones.setBounds(501,0,200,500);
        this.add(panelOpciones);
    }

    public void colocarEtiquetas(){
        JLabel etiquetaTitulo = new JLabel();
        etiquetaTitulo.setBounds(40, 10,80,40);
        etiquetaTitulo.setText("DAMAS");
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTitulo.setForeground(Color.BLACK);
        etiquetaTitulo.setFont(new Font("Chilanka",Font.BOLD,20));

        JLabel etiquetaTiempo = new JLabel();
        etiquetaTiempo.setBounds(40, 60,80,30);
        etiquetaTiempo.setText("Tiempo");
        etiquetaTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTiempo.setForeground(Color.BLACK);
        etiquetaTiempo.setFont(new Font("Arial",Font.BOLD,15));


        tiempo.setBounds(40, 90,100,30);
        tiempo.setOpaque(true);
        tiempo.setBackground(Color.white);
        tiempo.setText("0:0:0");
        tiempo.setHorizontalAlignment(SwingConstants.CENTER);
        tiempo.setForeground(Color.BLACK);
        tiempo.setFont(new Font("Arial",Font.BOLD,15));

        JLabel jugador2 = new JLabel();
        jugador2.setBounds(5, 160,210,30);
        jugador2.setText("Piezas comidas "+nombreJugador2);
        jugador2.setHorizontalAlignment(SwingConstants.LEFT);
        jugador2.setForeground(Color.BLACK);
        jugador2.setFont(new Font("Arial",Font.BOLD,15));

        piezasJugador2 = new JLabel();
        piezasJugador2.setBounds(50, 185,50,30);
        piezasJugador2.setOpaque(true);
        piezasJugador2.setBackground(Color.white);
        piezasJugador2.setText("0");
        piezasJugador2.setHorizontalAlignment(SwingConstants.CENTER);
        piezasJugador2.setForeground(Color.BLACK);
        piezasJugador2.setFont(new Font("Arial",Font.BOLD,15));

        JLabel jugador1 = new JLabel();
        jugador1.setBounds(5, 250,210,30);
        jugador1.setText("Piezas comidas "+nombreJugador1);
        jugador1.setHorizontalAlignment(SwingConstants.LEFT);
        jugador1.setForeground(Color.BLACK);
        jugador1.setFont(new Font("Arial",Font.BOLD,15));

        piezasJugador1 = new JLabel();
        piezasJugador1.setBounds(50, 275,50,30);
        piezasJugador1.setOpaque(true);
        piezasJugador1.setBackground(Color.white);
        piezasJugador1.setText("0");
        piezasJugador1.setHorizontalAlignment(SwingConstants.CENTER);
        piezasJugador1.setForeground(Color.BLACK);
        piezasJugador1.setFont(new Font("Arial",Font.BOLD,15));

        panelOpciones.add(etiquetaTitulo);
        panelOpciones.add(etiquetaTiempo);
        panelOpciones.add(tiempo);
        panelOpciones.add(jugador1);
        panelOpciones.add(piezasJugador1);
        panelOpciones.add(jugador2);
        panelOpciones.add(piezasJugador2);
    }

    public void colocarMenu(){
        JMenuBar barraMenu = new JMenuBar();
        JMenu menu = new JMenu();
        guardarPartida = new JMenuItem("Guardar Partida");
        regresarPrincipal = new JMenuItem("Regresar al Menu");
        menu.setText("Opciones");
        menu.setVisible(true);
        menu.add(guardarPartida);
        menu.add(regresarPrincipal);
        barraMenu.add(menu);
        barraMenu.setVisible(true);
        this.setJMenuBar(barraMenu);
        accionMenu();
    }

    public void accionMenu(){
        ActionListener accionGuardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Tengo que guardar");
            }
        };
        guardarPartida.addActionListener(accionGuardar);

        ActionListener accionRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablero.getHiloTiempo().interrupt();
                VentanaInicio regresarInicio = new VentanaInicio();
                dispose();
            }
        };

        regresarPrincipal.addActionListener(accionRegresar);

    }

    public void setContadorPiezas1(int contadorPiezas1) {
        this.contadorPiezas1 += contadorPiezas1;
        piezasJugador1.setText(String.valueOf(this.contadorPiezas1));
    }

    public void setContadorPiezas2(int contadorPiezas2) {
        this.contadorPiezas2 += contadorPiezas2;
        piezasJugador2.setText(String.valueOf(this.contadorPiezas2));
    }

    public void reiniciarContadores(){
        contadorPiezas1=0;
        contadorPiezas2=0;
        piezasJugador1.setText(String.valueOf(this.contadorPiezas1));
        piezasJugador2.setText(String.valueOf(this.contadorPiezas2));
    }

    public String getNombreJugador1() {
        return nombreJugador1;
    }

    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1 = nombreJugador1;
    }

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2 = nombreJugador2;
    }

    public JLabel getTiempo(){
        return tiempo;
    }

    public void setTiempo(String tiempo){
        this.tiempo.setText(tiempo);
    }
}

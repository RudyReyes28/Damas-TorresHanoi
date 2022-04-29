package com.ipc1.torres_hanoi;

import com.ipc1.torres_hanoi.torres.DibujarTorres;
import com.ipc1.torres_hanoi.modo_rapido.MoverBloquesSolucion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaHanoi extends JFrame {

    private DibujarTorres torresHanoi;
    private int cantBloques;
    private String nombreJugador;
    private JLabel tiempo;
    private JLabel cantMovimientos;
    private int contadorMov;
    private int modoJuego;
    private JButton resolverJuego;

    public VentanaHanoi(String nombreJugador, int cantBloques, int modoJuego) {
        this.nombreJugador = nombreJugador;
        this.cantBloques = cantBloques;
        this.modoJuego = modoJuego;
        this.setSize(1100,700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("TORRES DE HANOI");
        this.setLayout(null);
        tiempo = new JLabel();
        iniciar();
        aniadirTorres();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void iniciar(){
        torresHanoi = new DibujarTorres(this, cantBloques,modoJuego);

    }

    public void aniadirTorres(){
        //this.setLayout(new BorderLayout());
        this.add(torresHanoi);

        iniciarComponentes();
    }

    public void iniciarComponentes(){
        agregarEtiquetas();
        agregarBoton();
        agregarEventos();
    }

    public void agregarEtiquetas(){
        JLabel tituloTiempo = new JLabel();
        tituloTiempo.setBounds(750, 10,70,30);
        tituloTiempo.setText("Tiempo:");
        tituloTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        tituloTiempo.setForeground(Color.BLACK);
        tituloTiempo.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(tituloTiempo);


        tiempo.setBounds(820, 10,80,30);
        tiempo.setOpaque(true);
        tiempo.setBackground(Color.white);
        tiempo.setText("0:0:0");
        tiempo.setHorizontalAlignment(SwingConstants.CENTER);
        tiempo.setForeground(Color.BLACK);
        tiempo.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(tiempo);

        JLabel etiquetaMovimientos = new JLabel();
        etiquetaMovimientos.setBounds(910, 10,100,30);
        etiquetaMovimientos.setText("Movimientos:");
        etiquetaMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaMovimientos.setForeground(Color.BLACK);
        etiquetaMovimientos.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(etiquetaMovimientos);

        cantMovimientos = new JLabel();
        cantMovimientos.setBounds(1010,10,50,30);
        cantMovimientos.setOpaque(true);
        cantMovimientos.setBackground(Color.white);
        cantMovimientos.setText("0");
        cantMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
        cantMovimientos.setForeground(Color.BLACK);
        cantMovimientos.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(cantMovimientos);

        JLabel torre1 = new JLabel();
        torre1.setBounds(170,122,70,30);
        torre1.setText("Torre 1");
        torre1.setHorizontalAlignment(SwingConstants.CENTER);
        torre1.setForeground(Color.BLACK);
        torre1.setFont(new Font("Chilanka",Font.BOLD,17));
        torresHanoi.add(torre1, Integer.valueOf(1));

        JLabel torre2 = new JLabel();
        torre2.setBounds(516,122,70,30);
        torre2.setText("Torre 2");
        torre2.setHorizontalAlignment(SwingConstants.CENTER);
        torre2.setForeground(Color.BLACK);
        torre2.setFont(new Font("Chilanka",Font.BOLD,17));
        torresHanoi.add(torre2,Integer.valueOf(1));

        JLabel torre3 = new JLabel();
        torre3.setBounds(862,122,70,30);
        torre3.setText("Torre 3");
        torre3.setHorizontalAlignment(SwingConstants.CENTER);
        torre3.setForeground(Color.BLACK);
        torre3.setFont(new Font("Chilanka",Font.BOLD,17));
        torresHanoi.add(torre3,Integer.valueOf(1));

    }

    public void agregarBoton(){
        resolverJuego = new JButton();
        resolverJuego.setBounds(100,20,150,30);
        resolverJuego.setText("Solucion rapida");
        resolverJuego.setHorizontalAlignment(SwingConstants.CENTER);
        resolverJuego.setFont(new Font("Arial",Font.BOLD,15));

        torresHanoi.add(resolverJuego,Integer.valueOf(1));

        if(modoJuego!=2){
            resolverJuego.setVisible(false);
        }
    }

    public void agregarEventos(){
        ActionListener eventoResolverJuego = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MoverBloquesSolucion bloquesSolucion = new MoverBloquesSolucion(VentanaHanoi.this);
                Thread moverSolucion = new Thread(bloquesSolucion);
                moverSolucion.start();
            }
        };
        resolverJuego.addActionListener(eventoResolverJuego);
    }

    public void setContadorMov() {
        this.contadorMov += 1;
        cantMovimientos.setText(String.valueOf(contadorMov));
    }

    public void reiniciarContador(){
        this.contadorMov = 0;
        cantMovimientos.setText(String.valueOf(contadorMov));
    }

    public JLabel getTiempo() {
        return tiempo;
    }

    public DibujarTorres getTorresHanoi() {
        return torresHanoi;
    }
}

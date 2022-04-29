package com.ipc1.ventanas;

import com.ipc1.archivos.NombreUsuarios;
import com.ipc1.damas.VentanaDamas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class VentanaInicioDamas extends JFrame {

    private JPanel panel;
    private JLabel mensajeJugador1, mensajeJugador2;
    private JTextField cajaJugador1, cajaJugador2;
    private JButton guardarDatos, empezarJuego;
    private String jugador1 = "", jugador2 = "";
    private JComboBox seleccionarModo;
    private JTextArea areaNombres;
    private int modoDeJuego=0;
    int moverY30 = 30;

    private NombreUsuarios usuarios;

    public VentanaInicioDamas() {
        usuarios = new NombreUsuarios("JugadoresDamas.txt");
        this.setSize(700,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("AGREGAR DATOS DAMAS");
        iniciarComponentes();
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniciarComponentes(){
        agregarPanel();
        agregarEtiquetas();
        agregarCajasTexto();
        agregarListas();
        agregarBotones();
        agregarAreaTexto();
        agregarEventos();
    }

    public void agregarPanel(){
        panel = new JPanel();
        panel.setBounds(0,0,700,500);
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);
        this.add(panel);
    }

    public void agregarEtiquetas(){

        JLabel titulo = new JLabel();
        titulo.setBounds(0, 10,700,40);
        titulo.setText("INGRESE LOS DATOS DEL LOS JUGADORES");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Arial Black",Font.BOLD,15));

        panel.add(titulo);

        JLabel textoModo = new JLabel();
        textoModo.setBounds(20, 70,150,30);
        textoModo.setText("Modo de Juego:");
        textoModo.setHorizontalAlignment(SwingConstants.CENTER);
        textoModo.setForeground(Color.BLACK);
        textoModo.setFont(new Font("Arial Black",Font.BOLD,15));
        panel.add(textoModo);

        JLabel jugador1 = new JLabel();
        jugador1.setBounds(15, 100+moverY30,150,30);
        jugador1.setText("Jugador 1: ");
        jugador1.setHorizontalAlignment(SwingConstants.CENTER);
        jugador1.setForeground(Color.BLACK);
        jugador1.setFont(new Font("Arial Black",Font.BOLD,15));
        panel.add(jugador1);

        JLabel jugador2 = new JLabel();
        jugador2.setBounds(15, 170+moverY30,150,30);
        jugador2.setText("Jugador 2: ");
        jugador2.setHorizontalAlignment(SwingConstants.CENTER);
        jugador2.setForeground(Color.BLACK);
        jugador2.setFont(new Font("Arial Black",Font.BOLD,15));
        panel.add(jugador2);

        mensajeJugador1 = new JLabel();
        mensajeJugador1.setBounds(15, 270+moverY30,420,30);
        mensajeJugador1.setHorizontalAlignment(SwingConstants.LEFT);
        mensajeJugador1.setForeground(Color.BLACK);
        mensajeJugador1.setFont(new Font("Arial",Font.BOLD,15));
        mensajeJugador1.setVisible(false);
        panel.add(mensajeJugador1);

        mensajeJugador2 = new JLabel();
        mensajeJugador2.setBounds(15, 300+moverY30,420,30);
        mensajeJugador2.setHorizontalAlignment(SwingConstants.LEFT);
        mensajeJugador2.setForeground(Color.BLACK);
        mensajeJugador2.setFont(new Font("Arial",Font.BOLD,15));
        mensajeJugador2.setVisible(false);
        panel.add(mensajeJugador2);
    }

    public void agregarCajasTexto(){
        cajaJugador1 = new JTextField();
        cajaJugador1.setBounds(168,100+moverY30,150,30);
        panel.add(cajaJugador1);

        cajaJugador2 = new JTextField();
        cajaJugador2.setBounds(168,170+moverY30,150,30);
        panel.add(cajaJugador2);
    }

    public void agregarListas(){
        String [] modos = {"Contra jugador","Contra Computadora"};
        seleccionarModo = new JComboBox(modos);
        seleccionarModo.setBounds(185,70,200,30);
        panel.add(seleccionarModo);

    }

    public void agregarBotones(){
        guardarDatos = new JButton();
        guardarDatos.setBounds(170,210+moverY30,150,30);
        guardarDatos.setText("Guardar Datos");
        guardarDatos.setHorizontalAlignment(SwingConstants.CENTER);
        guardarDatos.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(guardarDatos);

        empezarJuego = new JButton();
        empezarJuego.setBounds(170,340+moverY30,150,30);
        empezarJuego.setText("Empezar Juego");
        empezarJuego.setHorizontalAlignment(SwingConstants.CENTER);
        empezarJuego.setFont(new Font("Arial",Font.BOLD,15));
        empezarJuego.setVisible(false);
        panel.add(empezarJuego);
    }

    public void agregarAreaTexto(){
        areaNombres = new JTextArea();
        areaNombres.setBounds(450,70,200,200);
        areaNombres.setText("JUGADORES REGISTRADOS:");
        panel.add(areaNombres);

        agregarUsuarios();
    }

    public void agregarUsuarios(){
        ArrayList<String> nombres = new ArrayList<>();
        usuarios.leerUsuarios(nombres,"JugadoresDamas.txt");
        Set<String> hashSet = new HashSet<>(nombres);

        nombres.clear();
        nombres.addAll(hashSet);

        for(int i=0; i<nombres.size(); i++){
            areaNombres.append("\n"+nombres.get(i));
        }

    }

    public void agregarEventos(){
        ActionListener eventoGuardarDatos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugador1 = cajaJugador1.getText();
                jugador2 = cajaJugador2.getText();
                modoDeJuego = seleccionarModo.getSelectedIndex()+1;

                if(jugador1.equals("") && jugador2.equals("")){
                    JOptionPane.showMessageDialog(null, "DEBE DE LLENAR LOS DATOS");
                }else{
                    usuarios.escribirUsuarios(jugador1);
                    usuarios.escribirUsuarios(jugador2);
                    mensajeJugador1.setText(jugador1+" jugará con las fichas rojas, empezará primero");
                    mensajeJugador2.setText(jugador2+" jugará con las fichas blancas, empezará segundo");
                    mensajeJugador1.setVisible(true);
                    mensajeJugador2.setVisible(true);
                    empezarJuego.setVisible(true);

                }
            }
        };

        ActionListener eventoEmpezarPartida = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaDamas damas = new VentanaDamas(jugador1,jugador2,modoDeJuego);
                damas.setVisible(true);
                dispose();
            }
        };
        guardarDatos.addActionListener(eventoGuardarDatos);
        empezarJuego.addActionListener(eventoEmpezarPartida);
    }

}

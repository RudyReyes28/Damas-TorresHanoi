package com.ipc1.ventanas;

import com.ipc1.damas.VentanaDamas;
import com.ipc1.torres_hanoi.VentanaHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicioHanoi extends JFrame {

    private JPanel panel;
    private String nombreJugador;
    private JComboBox seleccionarDiscos;
    private int discos;
    private JTextField cajaNombre;
    private JButton guardarDatos, empezarJuego;

    public VentanaInicioHanoi() {
        this.setSize(450,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("AGREGAR DATOS TORRES DE HANOI");
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
        agregarEventos();
    }

    public void agregarPanel(){
        panel = new JPanel();
        panel.setBounds(0,0,450,500);
        panel.setLayout(null);
        //panel.setBackground(Color.CYAN);
        this.add(panel);
    }

    public void agregarEtiquetas(){
        JLabel titulo = new JLabel();
        titulo.setBounds(0, 10,500,40);
        titulo.setText("INGRESE LOS DATOS DEL JUGADOR");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Arial Black",Font.BOLD,15));

        panel.add(titulo);

        JLabel jugador1 = new JLabel();
        jugador1.setBounds(12, 100,150,30);
        jugador1.setText("Jugador:");
        jugador1.setHorizontalAlignment(SwingConstants.CENTER);
        jugador1.setForeground(Color.BLACK);
        jugador1.setFont(new Font("Arial Black",Font.BOLD,15));
        panel.add(jugador1);

        JLabel etiquetaDiscos = new JLabel();
        etiquetaDiscos.setBounds(15, 170,150,30);
        etiquetaDiscos.setText("Cant. Discos");
        etiquetaDiscos.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaDiscos.setForeground(Color.BLACK);
        etiquetaDiscos.setFont(new Font("Arial Black",Font.BOLD,15));
        panel.add(etiquetaDiscos);

    }

    public void agregarCajasTexto(){
        cajaNombre = new JTextField();
        cajaNombre.setBounds(168,100,150,30);
        panel.add(cajaNombre);
    }

    public void agregarListas(){
        String [] modos = {"3","4","5","6","7","8"};
        seleccionarDiscos = new JComboBox(modos);
        seleccionarDiscos.setBounds(170,170,100,30);
        panel.add(seleccionarDiscos);
    }

    public void agregarBotones(){
        guardarDatos = new JButton();
        guardarDatos.setBounds(170,230,150,30);
        guardarDatos.setText("Guardar Datos");
        guardarDatos.setHorizontalAlignment(SwingConstants.CENTER);
        guardarDatos.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(guardarDatos);

        empezarJuego = new JButton();
        empezarJuego.setBounds(170,300,150,30);
        empezarJuego.setText("Empezar Juego");
        empezarJuego.setHorizontalAlignment(SwingConstants.CENTER);
        empezarJuego.setFont(new Font("Arial",Font.BOLD,15));
        empezarJuego.setVisible(false);
        panel.add(empezarJuego);
    }

    public void agregarEventos(){
        ActionListener eventoGuardarDatos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreJugador = cajaNombre.getText();
                discos = Integer.parseInt(seleccionarDiscos.getSelectedItem().toString());

                if(nombreJugador.equals("")){
                    JOptionPane.showMessageDialog(null, "DEBE DE LLENAR LOS DATOS");
                }else{
                    empezarJuego.setVisible(true);

                }
            }
        };

        ActionListener eventoEmpezarPartida = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaHanoi hanoi = new VentanaHanoi(nombreJugador,discos);
                hanoi.setVisible(true);
                dispose();
            }
        };
        guardarDatos.addActionListener(eventoGuardarDatos);
        empezarJuego.addActionListener(eventoEmpezarPartida);
    }
}

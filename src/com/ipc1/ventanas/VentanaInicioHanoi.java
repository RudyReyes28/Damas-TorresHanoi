package com.ipc1.ventanas;

import com.ipc1.archivos.NombreUsuarios;
import com.ipc1.damas.VentanaDamas;
import com.ipc1.torres_hanoi.VentanaHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class VentanaInicioHanoi extends JFrame {

    private JPanel panel;
    private String nombreJugador;
    private JComboBox seleccionarDiscos, seleccionarModo;
    private int discos, modoJuego;
    private JTextField cajaNombre;
    private JButton guardarDatos, empezarJuego;
    private JTextArea areaNombres;

    private NombreUsuarios usuarios;

    public VentanaInicioHanoi() {
        usuarios = new NombreUsuarios("JugadoresHanoi.txt");
        this.setSize(700,500);
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

        JLabel modoDeJuego = new JLabel();
        modoDeJuego.setBounds(15, 230,150,30);
        modoDeJuego.setText("Modo de Juego:");
        modoDeJuego.setHorizontalAlignment(SwingConstants.CENTER);
        modoDeJuego.setForeground(Color.BLACK);
        modoDeJuego.setFont(new Font("Arial Black",Font.BOLD,15));
        panel.add(modoDeJuego);

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

        String [] modoJuego = {"Normal","Solucion Rapida"};
        seleccionarModo = new JComboBox(modoJuego);
        seleccionarModo.setBounds(170,230,150,30);
        panel.add(seleccionarModo);
    }

    public void agregarBotones(){
        guardarDatos = new JButton();
        guardarDatos.setBounds(170,290,150,30);
        guardarDatos.setText("Guardar Datos");
        guardarDatos.setHorizontalAlignment(SwingConstants.CENTER);
        guardarDatos.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(guardarDatos);

        empezarJuego = new JButton();
        empezarJuego.setBounds(170,350,150,30);
        empezarJuego.setText("Empezar Juego");
        empezarJuego.setHorizontalAlignment(SwingConstants.CENTER);
        empezarJuego.setFont(new Font("Arial",Font.BOLD,15));
        empezarJuego.setVisible(false);
        panel.add(empezarJuego);

    }

    public void agregarAreaTexto(){
        areaNombres = new JTextArea();
        areaNombres.setBounds(450,100,200,200);
        areaNombres.setText("JUGADORES REGISTRADOS:");
        panel.add(areaNombres);

        agregarUsuarios();
    }

    public void agregarUsuarios(){
        ArrayList<String> nombres = new ArrayList<>();
        usuarios.leerUsuarios(nombres,"JugadoresHanoi.txt");
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
                nombreJugador = cajaNombre.getText();
                discos = Integer.parseInt(seleccionarDiscos.getSelectedItem().toString());
                modoJuego = seleccionarModo.getSelectedIndex()+1;
                if(nombreJugador.equals("")){
                    JOptionPane.showMessageDialog(null, "DEBE DE LLENAR LOS DATOS");
                }else{
                    if(modoJuego!=2) {
                        usuarios.escribirUsuarios(nombreJugador);
                    }
                    empezarJuego.setVisible(true);

                }
            }
        };

        ActionListener eventoEmpezarPartida = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaHanoi hanoi = new VentanaHanoi(nombreJugador,discos, modoJuego);
                hanoi.setVisible(true);
                dispose();
            }
        };
        guardarDatos.addActionListener(eventoGuardarDatos);
        empezarJuego.addActionListener(eventoEmpezarPartida);
    }
}

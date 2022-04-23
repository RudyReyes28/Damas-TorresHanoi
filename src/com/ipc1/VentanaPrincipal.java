package com.ipc1;

import com.ipc1.tablero.DibujarTablero;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private DibujarTablero tablero;
    public VentanaPrincipal() {
        this.setSize(500,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("DAMAS");
        iniciarJuego();
        colocarTablero();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniciarJuego(){
        tablero = new DibujarTablero();

    }

    public void colocarTablero(){
        this.setLayout(new BorderLayout());
        this.add(tablero, BorderLayout.CENTER);
    }




}

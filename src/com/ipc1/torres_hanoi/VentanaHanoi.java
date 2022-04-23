package com.ipc1.torres_hanoi;

import com.ipc1.torres_hanoi.torres.DibujarTorres;

import javax.swing.*;
import java.awt.*;

public class VentanaHanoi extends JFrame {

    private DibujarTorres torresHanoi;
    public VentanaHanoi() {
        this.setSize(1100,540);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("TORRES DE HANOI");
        iniciar();
        aniadirTorres();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void iniciar(){
        torresHanoi = new DibujarTorres();

    }

    public void aniadirTorres(){
        this.setLayout(new BorderLayout());
        this.add(torresHanoi, BorderLayout.CENTER);
    }
}

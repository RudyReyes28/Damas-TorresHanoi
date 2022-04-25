package com.ipc1;

import com.ipc1.damas.VentanaDamas;
import com.ipc1.torres_hanoi.VentanaHanoi;
import com.ipc1.ventanas.VentanaInicioDamas;

public class Jugar {

    public static void main(String[] args) {
        VentanaDamas ventana = new VentanaDamas("Rudy","Juan");
        ventana.setVisible(true);

        //VentanaHanoi hanoi = new VentanaHanoi();
        //hanoi.setVisible(true);

        //VentanaInicio inicio = new VentanaInicio();
        //VentanaInicioDamas inicioDamas = new VentanaInicioDamas();
        //inicioDamas.setVisible(true);
    }
}

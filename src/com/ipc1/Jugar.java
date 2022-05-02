package com.ipc1;

import com.ipc1.damas.VentanaDamas;
import com.ipc1.torres_hanoi.VentanaHanoi;
import com.ipc1.ventanas.VentanaInicioDamas;
import com.ipc1.ventanas.VentanaInicioHanoi;
import com.ipc1.ventanas.VentanaReporteDamas;
import com.ipc1.ventanas.VentanaReportesHanoi;

public class Jugar {

    public static void main(String[] args) {
        //VentanaDamas ventana = new VentanaDamas("Rudy","Juan",1);
        //ventana.setVisible(true);

        //VentanaHanoi hanoi = new VentanaHanoi("Rudy",5,1);
        //hanoi.setVisible(true);

        //VentanaInicio inicio = new VentanaInicio();

        //VentanaInicioDamas inicioDamas = new VentanaInicioDamas();
        //inicioDamas.setVisible(true);

        //VentanaReporteDamas reportes = new VentanaReporteDamas(ventana);
        //reportes.setVisible(true);

        //VentanaInicioHanoi inicioHanoi = new VentanaInicioHanoi();
        //inicioHanoi.setVisible(true);

        VentanaReportesHanoi reportesHanoi = new VentanaReportesHanoi();
        reportesHanoi.setVisible(true);
    }
}

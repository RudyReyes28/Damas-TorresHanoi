package com.ipc1.archivos;

import javax.swing.*;
import java.io.*;

public class GuardarPartida {
    private File guardarPartida;

    public void crearArchivo(String archivoPartida) {
        guardarPartida = new File(archivoPartida);

        try {
            guardarPartida.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarPartida(String datosPartida){
        try {
            FileWriter escribir = new FileWriter(guardarPartida, true);

            escribir.write(datosPartida + "\r\n");
            escribir.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarTiempo(String tiempo){
        try {
            FileWriter escribir = new FileWriter(guardarPartida, true);

            escribir.write("tiempo,"+tiempo + "\r\n");
            escribir.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarMovimientos(String movimientos){
        try {
            FileWriter escribir = new FileWriter(guardarPartida, true);

            escribir.write("movimiento,"+movimientos + "\r\n");
            escribir.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarDatos(String [][] datos, String archivoALeer){
        String [] fila;
        int i=0;
        String cadena;

        try {
            FileReader lector = new FileReader(archivoALeer);
            BufferedReader lectura = new BufferedReader(lector);

            cadena = lectura.readLine();

            while(cadena!=null){
                fila = cadena.split(",");
                llenarMatrizDatos(datos,fila,i);
                cadena = lectura.readLine();
                i++;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"No hay partida guardada");
        }
    }

    public void llenarMatrizDatos(String [][] matrizDatos, String [] fila, int j){
        for(int i=0; i<fila.length; i++){
            matrizDatos[j][i]= fila[i];
        }
    }
}

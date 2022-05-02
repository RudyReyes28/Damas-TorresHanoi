package com.ipc1.archivos;

import java.io.*;

public class ReporteJuegos {
    private File reportes;

    public ReporteJuegos(String archivoReporte) {
        reportes = new File(archivoReporte);

        try {
            reportes.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void escribirReporte(String datos) {
        try {
            FileWriter escribir = new FileWriter(reportes, true);

            escribir.write(datos + "\r\n");
            escribir.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int cantidadFilas(String nombreArchivo){
        int cantFilas =0;
        String cadena;
        try {
            FileReader lector = new FileReader(nombreArchivo);
            BufferedReader lectura = new BufferedReader(lector);

            cadena = lectura.readLine();

            while(cadena!=null){
                cantFilas++;
                //nombreUsuario.add(cadena);
                cadena = lectura.readLine();

            }

        } catch (IOException ignore) {

        }

        return cantFilas;
    }

    public void leerArchivo(String [][] guardarDatos,String nombreArchivo){
        int i =0;
        String cadena;
        String [] fila;
        try {
            FileReader lector = new FileReader(nombreArchivo);
            BufferedReader lectura = new BufferedReader(lector);

            cadena = lectura.readLine();

            while(cadena!=null){
                fila = cadena.split(",");
                llenarMatrizDatos(guardarDatos,fila,i);
                //nombreUsuario.add(cadena);
                cadena = lectura.readLine();
                i++;

            }

        } catch (IOException ignore) {

        }

    }

    public void llenarMatrizDatos(String [][] infoReportes, String [] fila, int j){
        for(int i=0; i<fila.length; i++){
            infoReportes[j][i]= fila[i];
        }
    }
}

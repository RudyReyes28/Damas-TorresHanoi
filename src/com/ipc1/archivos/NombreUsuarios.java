package com.ipc1.archivos;

import java.io.*;
import java.util.ArrayList;

public class NombreUsuarios {
    private File nombreUsuarios;

    public NombreUsuarios(String nombreArchivo) {
        nombreUsuarios = new File(nombreArchivo);

        try {
            nombreUsuarios.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void escribirUsuarios(String nombres){
        try {
            FileWriter escribir= new FileWriter(nombreUsuarios,true);

            escribir.write(nombres+"\r\n");
            escribir.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void leerUsuarios(ArrayList nombreUsuario, String nombreArchivo){
        String cadena;

        try {
            FileReader lector = new FileReader(nombreArchivo);
            BufferedReader lectura = new BufferedReader(lector);

            cadena = lectura.readLine();

            while(cadena!=null){
                nombreUsuario.add(cadena);
                cadena = lectura.readLine();

            }

        } catch (IOException ignore) {

        }
    }
}

package com.ipc1.utilidades;

import javax.swing.*;

public class Cronometro implements Runnable{

    private JLabel cronometro;

    public Cronometro(JLabel cronometro) {
        this.cronometro = cronometro;
    }


    @Override
    public void run() {
        int minutos=0;
        int segundos=0;
        int horas=0;
        try {
           while(true){
                if(segundos!=59) {
                    segundos++;
                }else{
                    if(minutos!=59){
                        segundos=0;
                        minutos++;
                    }else{
                        horas++;
                        minutos=0;
                        segundos=0;
                    }
                }
                String tiempo = horas+":"+minutos+":"+segundos;
                cronometro.setText(tiempo);

                Thread.sleep(999);

           }

        } catch (InterruptedException ignore) {

        }
    }
}

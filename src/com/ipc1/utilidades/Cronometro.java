package com.ipc1.utilidades;

import javax.swing.*;

public class Cronometro implements Runnable{

    private JLabel cronometro;
    private int minutos=0;
    private int segundos=0;
    private int horas=0;

    public Cronometro(JLabel cronometro) {
        this.cronometro = cronometro;
    }


    @Override
    public void run() {

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

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void reiniciarTiempo(){
        minutos = 0;
        segundos = 0;
        horas = 0;
    }
}

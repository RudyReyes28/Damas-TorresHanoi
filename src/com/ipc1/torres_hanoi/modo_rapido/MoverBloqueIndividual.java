package com.ipc1.torres_hanoi.modo_rapido;

import com.ipc1.torres_hanoi.VentanaHanoi;

public class MoverBloqueIndividual implements Runnable{

    private VentanaHanoi ventanaHanoi;
    private int posicionFinal = 0;
    private int retrocederAvanzar = 0;

    public MoverBloqueIndividual(VentanaHanoi ventanaHanoi) {
        this.ventanaHanoi = ventanaHanoi;
    }

    @Override
    public void run() {
        int [][] solucion = ventanaHanoi.getTorresHanoi().getVerBloques().getSolucion();
        int posicionTemporal = posicionFinal;
        if(retrocederAvanzar>0) {
            int pFinal = posicionTemporal+retrocederAvanzar;
            if(pFinal>= solucion.length){
                pFinal =solucion.length;
            }
            for (int i = posicionTemporal; i < pFinal; i++) {
                //System.out.println(i+"Torre incial: "+solucion[i][0]+" torre final: "+solucion[i][1]);
                if(ventanaHanoi.getTorresHanoi().getVerBloques().moverBloque(solucion[i][0], solucion[i][1])){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    ventanaHanoi.setContadorMov();
                    ventanaHanoi.getTorresHanoi().moverBloques();
                    posicionFinal++;
                }


                //posicionFinal++;
            }
        }else{
            //System.out.println(posicionTemporal);
            int pFinal = posicionTemporal+retrocederAvanzar;
            if(pFinal<0){
                pFinal =0;
            }
            for (int i = posicionTemporal-1; i >= pFinal; i--) {
                //System.out.println(i+"Torre incial: "+solucion[i][1]+" torre final: "+solucion[i][0]);
                if(ventanaHanoi.getTorresHanoi().getVerBloques().moverBloque(solucion[i][1], solucion[i][0])){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    ventanaHanoi.retrocederContador();
                    ventanaHanoi.getTorresHanoi().moverBloques();
                    posicionFinal--;
                }


                //posicionFinal--;
            }
        }

    }

    public void setRetrocederAvanzar(int retrocederAvanzar) {
        this.retrocederAvanzar = retrocederAvanzar;
    }

    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }
}

package com.ipc1.torres_hanoi.modo_rapido;

import com.ipc1.torres_hanoi.VentanaHanoi;

public class MoverBloquesSolucion implements Runnable{

    private VentanaHanoi ventanaHanoi;

    public MoverBloquesSolucion(VentanaHanoi ventanaHanoi) {
        this.ventanaHanoi = ventanaHanoi;
    }

    @Override
    public void run() {
        int [][] solucion = ventanaHanoi.getTorresHanoi().getVerBloques().getSolucion();

        for(int i=0; i<solucion.length;i++){
            //System.out.println(i+"Torre incial: "+solucion[i][0]+" torre final: "+solucion[i][1]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ventanaHanoi.getTorresHanoi().getVerBloques().moverBloque(solucion[i][0],solucion[i][1]);
            ventanaHanoi.setContadorMov();
            ventanaHanoi.getTorresHanoi().moverBloques();
        }

    }
}

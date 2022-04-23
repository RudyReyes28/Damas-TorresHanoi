package com.ipc1.tablero;

import javax.swing.*;

public class PiezasTablero {

    private int piezasTablero[][];
    private final int rojas = 1, blancas =2, rojasR=3, blancasR = 4, vacio=5;

    private boolean turno = true;//True = turno rojos, false = turno blancos

    public PiezasTablero() {
        this.piezasTablero = new int[8][8];
    }

    public void cambiarTurno(){
        if(turno){
            turno=false;
        }else{
            turno = true;
        }

    }

    public boolean verificarFicha(boolean turno, int x, int y){
        boolean seleccionFicha = false;
        if(turno){
            if(piezasTablero[x][y] == rojas || piezasTablero[x][y] == rojasR){
                seleccionFicha = true;
            }
        }else{
            if(piezasTablero[x][y] == blancas || piezasTablero[x][y] == blancasR){
                seleccionFicha = true;
            }
        }

        return seleccionFicha;
    }

    public void verificarRey(){
        for(int i=0; i<piezasTablero[0].length;i++){
            if(piezasTablero[0][i]==rojas){
                piezasTablero[0][i] = rojasR;
            }else if (piezasTablero[7][i] == blancas) {
                piezasTablero[7][i] = blancasR;
            }
        }
    }

    public boolean moverFicha(boolean turno, int xInicial, int yInicial, int xFinal, int yFinal){
        boolean movimientoCorrecto= false;

        if(turno) {
            //TURNO DE LAS ROJAS
            if (piezasTablero[xInicial][yInicial] == rojas) {
                if ((xInicial - 1) == xFinal && ((yInicial - 1) == yFinal || (yInicial + 1) == yFinal)) {
                    if (piezasTablero[xFinal][yFinal] == vacio) {
                        piezasTablero[xInicial][yInicial] = vacio;
                        piezasTablero[xFinal][yFinal] = rojas;
                        movimientoCorrecto = true;
                    }
                } else if ((xInicial - 2) == xFinal && ((yInicial - 2) == yFinal || (yInicial + 2) == yFinal)) {

                    try {
                        if (piezasTablero[xInicial - 1][yInicial - 1] == blancas || piezasTablero[xInicial - 1][yInicial - 1] == blancasR) {
                            movimientoCorrecto = movimientoPiezasBR(xInicial, yInicial, xFinal, yFinal, 2, rojas);
                        }
                    }catch (ArrayIndexOutOfBoundsException ignore){

                    }

                    try {
                        if(!movimientoCorrecto){
                            if (piezasTablero[xInicial - 1][yInicial + 1] == blancas || piezasTablero[xInicial - 1][yInicial + 1] == blancasR) {
                                movimientoCorrecto = movimientoPiezasBR(xInicial, yInicial, xFinal, yFinal, 2, rojas);
                            }
                        }
                    }catch (ArrayIndexOutOfBoundsException ignore){

                    }
                }
            }else if(piezasTablero[xInicial][yInicial] == rojasR){
                movimientoCorrecto = movimientoReina(xInicial,yInicial,xFinal,yFinal, rojasR, blancas, blancasR);
            }
        }else {
            //TURNO DE LAS BLANCAS
            if (piezasTablero[xInicial][yInicial] == blancas) {
                if((xInicial+1)==xFinal && ((yInicial-1)==yFinal || (yInicial+1)==yFinal)){
                    if(piezasTablero[xFinal][yFinal] == vacio){
                        piezasTablero[xInicial][yInicial]= vacio;
                        piezasTablero[xFinal][yFinal]= blancas;
                        movimientoCorrecto= true;
                    }
                } else if ((xInicial + 2) == xFinal && ((yInicial - 2) == yFinal || (yInicial + 2) == yFinal)) {

                    try {
                        if (piezasTablero[xInicial + 1][yInicial - 1] == rojas || piezasTablero[xInicial + 1][yInicial - 1] == rojasR) {
                            movimientoCorrecto = movimientoPiezasBR(xInicial, yInicial, xFinal, yFinal,1,blancas);
                        }
                    }catch (ArrayIndexOutOfBoundsException ignore){

                    }
                    try {
                        if (!movimientoCorrecto) {
                            if (piezasTablero[xInicial + 1][yInicial + 1] == rojas || piezasTablero[xInicial + 1][yInicial + 1] == rojasR) {
                                movimientoCorrecto = movimientoPiezasBR(xInicial, yInicial, xFinal, yFinal, 1, blancas);
                            }
                        }
                    }catch (ArrayIndexOutOfBoundsException ignore){

                    }

                }
            }else if(piezasTablero[xInicial][yInicial] == blancasR){
                movimientoCorrecto = movimientoReina(xInicial,yInicial,xFinal,yFinal, blancasR, rojas,rojasR);
            }
        }

        return movimientoCorrecto;
    }

    private boolean movimientoPiezasBR(int xInicial, int yInicial, int xFinal, int yFinal, int opcionMetodo, int colorPieza){
        boolean movimientoCorrecto = false;

        if (piezasTablero[xFinal][yFinal] == vacio) {

            piezasTablero[xInicial][yInicial] = vacio;
            if(opcionMetodo==1) {
                eliminarFichasRojas(xInicial, yInicial);
            }else{
                eliminarFichasBlancas(xInicial,yInicial);
            }
            piezasTablero[xFinal][yFinal] = colorPieza;
            movimientoCorrecto = true;
        }

        return  movimientoCorrecto;
    }

    private boolean movimientoReina(int xInicial, int yInicial, int xFinal, int yFinal, int colorPieza, int piezaEnemiga, int piezaReyEnemiga){
        boolean movimientoCorrecto = false;

        if(xInicial>xFinal){
            //VA DE ABAJO HACIA ARRIBA

            for(int i=1;i<piezasTablero.length;i++){
                if((xInicial-i)==xFinal && ((yInicial - i) == yFinal) || (xInicial-i)==xFinal && ((yInicial + i) == yFinal)){
                    if(piezasTablero[xFinal][yFinal]== vacio){
                        eliminarFichasReinasAbajoArriba(xInicial,yInicial,xFinal,piezaEnemiga,piezaReyEnemiga);
                        piezasTablero[xInicial][yInicial] = vacio;
                        piezasTablero[xFinal][yFinal] = colorPieza;
                        movimientoCorrecto = true;
                        break;
                    }
                }
            }
        }else{
            //VA DE ARRIBA HACIA ABAJO
            for(int i=1;i<piezasTablero.length;i++){
                if((xInicial+i)==xFinal && ((yInicial - i) == yFinal) || (xInicial+i)==xFinal && ((yInicial + i) == yFinal)){
                    if(piezasTablero[xFinal][yFinal]== vacio){
                        eliminarFichasReinasArribaAbajo(xInicial,yInicial,xFinal,piezaEnemiga,piezaReyEnemiga);
                        piezasTablero[xInicial][yInicial] = vacio;
                        piezasTablero[xFinal][yFinal] = colorPieza;
                        movimientoCorrecto = true;
                        break;
                    }
                }
            }
        }
        return movimientoCorrecto;
    }

    private void eliminarFichasReinasAbajoArriba(int xInicial, int yInicial, int xFinal, int enemiga, int enemigaR){
        for(int i=1; i< piezasTablero.length; i++){
            if(xInicial-i>=0 && yInicial-i>=0){
                if(xInicial-i>=xFinal) {
                    if (piezasTablero[xInicial - i][yInicial - i] == enemiga || piezasTablero[xInicial - i][yInicial - i] == enemigaR) {
                        piezasTablero[xInicial - i][yInicial - i] = vacio;
                        break;
                    }
                }
            } if(xInicial-i>=0 && yInicial+i<8){
                if(xInicial-i>=xFinal) {
                    if (piezasTablero[xInicial - i][yInicial + i] == enemiga || piezasTablero[xInicial - i][yInicial + i] == enemigaR) {
                        piezasTablero[xInicial - i][yInicial + i] = vacio;
                        break;
                    }
                }
            }
        }
    }

    private void eliminarFichasReinasArribaAbajo(int xInicial, int yInicial, int xFinal, int enemiga, int enemigaR){
        for(int i=1; i<piezasTablero.length; i++){
            if(xInicial+i<=7 && yInicial-i>=0){
                if(xInicial+i<=xFinal) {
                    if (piezasTablero[xInicial + i][yInicial - i] == enemiga || piezasTablero[xInicial + i][yInicial - i] == enemigaR) {
                        piezasTablero[xInicial + i][yInicial - i] = vacio;
                        break;
                    }
                }
            } if(xInicial+i<=7 && yInicial+i<8){
                if(xInicial+i<=xFinal) {
                    if (piezasTablero[xInicial + i][yInicial + i] == enemiga || piezasTablero[xInicial + i][yInicial + i] == enemigaR) {
                        piezasTablero[xInicial + i][yInicial + i] = vacio;
                        break;
                    }
                }
            }
        }
    }

    private void eliminarFichasBlancas(int xInicial, int yInicial){
        boolean completado = false;
        try {
            if (piezasTablero[xInicial - 1][yInicial - 1] == blancas || piezasTablero[xInicial - 1][yInicial - 1] == blancasR) {
                piezasTablero[xInicial - 1][yInicial - 1] = vacio;
                completado = true;
            }
        } catch (ArrayIndexOutOfBoundsException e){

        }
        try{
            if(!completado){
                if(piezasTablero[xInicial-1][yInicial+1]==blancas|| piezasTablero[xInicial-1][yInicial+1]==blancasR){
                    piezasTablero[xInicial-1][yInicial+1]=vacio;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ignore){

        }
    }

    private void eliminarFichasRojas(int xInicial, int yInicial){
        boolean completado = false;
        try {
            if (piezasTablero[xInicial + 1][yInicial - 1] == rojas || piezasTablero[xInicial + 1][yInicial - 1] == rojasR) {
                piezasTablero[xInicial + 1][yInicial - 1] = vacio;
                completado = true;
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }
        try {
            if(!completado){
                if(piezasTablero[xInicial+1][yInicial+1]==rojas|| piezasTablero[xInicial+1][yInicial+1]==rojasR){
                    piezasTablero[xInicial+1][yInicial+1]=vacio;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ignore){

        }
    }

    public void llenarTablero(){

        for(int i=0; i<piezasTablero.length; i++){
            for(int j =0; j<piezasTablero[0].length; j++){

                if(j%2!=0){
                    piezasTablero[0][j]= blancas;
                    piezasTablero[2][j]= blancas;
                    piezasTablero[4][j]=vacio;
                    piezasTablero[6][j]=rojas;

                }else{
                    piezasTablero[1][j]= blancas;
                    piezasTablero[3][j]=vacio;
                    piezasTablero[5][j]=rojas;
                    piezasTablero[7][j]=rojas;
                }
            }
        }
    }

    public int getPieza(int x, int y){
        verificarRey();
        return piezasTablero[x][y];
    }

    public int getRojas() {
        return rojas;
    }

    public int getBlancas() {
        return blancas;
    }

    public int getRojasR() {
        return rojasR;
    }

    public int getBlancasR() {
        return blancasR;
    }

    public int getVacio() {
        return vacio;
    }

    public boolean isTurno() {
        return turno;
    }

    public boolean verificarGanador(){
        boolean partidaGanada = false;
        int cantidadRojos=0;
        int cantidadBlancos=0;

        for(int i=0; i<piezasTablero.length; i++){
            for(int j=0; j<piezasTablero[0].length; j++){
                if(piezasTablero[i][j]==rojas || piezasTablero[i][j]==rojasR){
                    cantidadRojos++;
                }else if(piezasTablero[i][j]==blancas || piezasTablero[i][j]==blancasR){
                    cantidadBlancos++;
                }
            }
        }

        if(cantidadRojos>0 && cantidadBlancos == 0){
            JOptionPane.showMessageDialog(null, "LOS ROJOS HAN GANADO");
            partidaGanada = true;
        } else if (cantidadBlancos>0 && cantidadRojos==0) {
            JOptionPane.showMessageDialog(null, "LOS ROJOS HAN GANADO");
            partidaGanada = true;
        }

        return partidaGanada;
    }

}

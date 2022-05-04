package com.ipc1.torres_hanoi.torres;

import com.ipc1.torres_hanoi.VentanaHanoi;

public class BloquesTorres {

    private int cantBloques;
    private int [][] bloques;
    private DibujarTorres dibujarTorres;
    private int [][] solucion;
    private int contador = 0;

    private final int bloque1 =8, bloque2= 7, bloque3 = 6, bloque4 = 5, bloque5 = 4;
    private final int bloque6 = 3, bloque7 = 2, bloque8 = 1, bloqueVacio = 0;

    public BloquesTorres(int cantBloques, DibujarTorres dibujarTorres) {
        this.cantBloques = cantBloques;
        this.dibujarTorres = dibujarTorres;
        bloques = new int[3][8];
        solucion = new int[(int) (Math.pow(2,cantBloques)-1)][2];
        movimientoSolucion(cantBloques,0,1,2);

    }
    //de los mas grandes a los mas pequeños 8,7,6,5,4,3,2,1,0,(vacio)
    public void llenarBloques(){
         int bloquesMayor = 0;

         for(int j=0; j<cantBloques; j++){
             bloques[0][j]=8-bloquesMayor;
             bloquesMayor++;

             bloques[1][j] = bloqueVacio;
             bloques[2][j] = bloqueVacio;
         }

    }

    public boolean verificarGanador(){
        int bloqueMayor = 7;
        boolean ganador = false;
        //TORRE 1 O 2------ 8, 7, 6, 5, 4, 3, 2 ,1

        if(retornarPosicionBloqueUltimo(1)==cantBloques-1) {
            for (int i = 0; i < cantBloques; i++) {
                if (bloques[1][i] > bloqueMayor) {
                    ganador = true;
                    bloqueMayor--;
                } else {
                    break;
                }
            }
        }

        bloqueMayor = 7;
        if(!ganador){
            if(retornarPosicionBloqueUltimo(2)==cantBloques-1) {
                for (int i = 0; i < cantBloques; i++) {
                    if (bloques[2][i] > bloqueMayor) {
                        ganador = true;
                        bloqueMayor--;
                    } else {
                        break;
                    }
                }
            }
        }

        return ganador;
    }

    public boolean activarTruco(int torreInicial, int torreFinal){
        boolean correcto = false;

        //BUSCAR CUAL ES EL BLOQUE QUE VAMOS A MOVER
        int bloqueMover = retornarBloqueUltimo(torreInicial);
        int posicionBloqueMover = retornarPosicionBloqueUltimo(torreInicial);
        //BUSCAR CUAL ES EL BLOQUE A DONDE VAMOS A MOVERLO
        int bloqueTorre = retornarBloqueUltimo(torreFinal);
        int posicionBloqueMovidoTorre = retornarPosicionBloqueUltimo(torreFinal);

        if(bloqueTorre==0){
            if(posicionBloqueMovidoTorre+1<8){
                correcto = true;
            }
        }else if(bloqueMover<bloqueTorre){
            if(posicionBloqueMovidoTorre+1<8){
                correcto = true;
            }
        }
        return correcto;
    }

    public boolean moverBloque(int torreInicial, int torreFinal){
        boolean correcto = false;

        //BUSCAR CUAL ES EL BLOQUE QUE VAMOS A MOVER
        int bloqueMover = retornarBloqueUltimo(torreInicial);
        int posicionBloqueMover = retornarPosicionBloqueUltimo(torreInicial);
        //BUSCAR CUAL ES EL BLOQUE A DONDE VAMOS A MOVERLO
        int bloqueTorre = retornarBloqueUltimo(torreFinal);
        int posicionBloqueMovidoTorre = retornarPosicionBloqueUltimo(torreFinal);

        if(bloqueTorre==0){
            if(posicionBloqueMovidoTorre+1<8){
                bloques[torreInicial][posicionBloqueMover] = bloqueVacio;
                bloques[torreFinal][posicionBloqueMovidoTorre] = bloqueMover;
                correcto = true;
            }
        }else if(bloqueMover<bloqueTorre){
            if(posicionBloqueMovidoTorre+1<8){
                bloques[torreInicial][posicionBloqueMover] = bloqueVacio;
                bloques[torreFinal][posicionBloqueMovidoTorre+1] = bloqueMover;
                correcto = true;
            }
        }
        return correcto;
    }

    public int retornarBloqueUltimo(int torreBuscar){
        int bloqueUltimo = 0;
        for( int i=(bloques[torreBuscar].length-1); i>=0; i-- ){
            if(bloques[torreBuscar][i]!=0){
                bloqueUltimo = bloques[torreBuscar][i];
                break;
            }
        }
        return bloqueUltimo;
    }

    public int retornarPosicionBloqueUltimo(int torreBuscar){
        int bloqueUltimo = 0;
        for( int i=(bloques[torreBuscar].length-1); i>=0; i--){
            if(bloques[torreBuscar][i]!=0){
                bloqueUltimo = i;
                break;
            }
        }
        return bloqueUltimo;
    }


    public void movimientoSolucion(int cantDiscos,int torreInicio, int torreApoyo, int torreFinal){
        if(cantDiscos==1){
            //TORRE INICIO A LA TORRE DESTINO

            solucion[contador][0] = torreInicio;
            solucion[contador][1] = torreFinal;
            contador++;

        }else{
            movimientoSolucion(cantDiscos-1,torreInicio,torreFinal,torreApoyo);
            solucion[contador][0] = torreInicio;
            solucion[contador][1] = torreFinal;
            contador++;
            movimientoSolucion(cantDiscos-1,torreApoyo,torreInicio,torreFinal);
        }
    }


    public int getBloque(int torre, int nBloque){
        return bloques[torre][nBloque];
    }

    public int[][] getSolucion() {
        return solucion;
    }

    public void guardarPartida(int [][] copiaTorres){
        for(int i=0; i<bloques.length; i++){
            for(int j=0; j<bloques[0].length; j++){
                copiaTorres[i][j] = bloques[i][j];
            }
        }
    }

    public void cargarPartida(int [][] copiaTorres){
        for(int i=0; i<bloques.length; i++){
            for(int j=0; j<bloques[0].length; j++){
                bloques[i][j] = copiaTorres[i][j];
            }

            dibujarTorres.moverBloques();
        }
    }

    public String mostrarMovimientoTruco(){
        int torreI =-1;
        int torreF = -1;
        for(int i=0; i<solucion.length; i++){

                if(activarTruco(solucion[i][0],solucion[i][1])){
                    torreI = solucion[i][0];
                    torreF = solucion[i][1];
                    break;
                }

        }

        if(torreI <0 && torreF<0){
            return "No hay movimientos posibles";
        }else{
            return "Mover torre "+(torreI+1)+" a la torre "+(torreF+1);
        }
    }
}

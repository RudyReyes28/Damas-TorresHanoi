package com.ipc1.torres_hanoi.torres;

public class BloquesTorres {

    private int cantBloques;
    private int [][] bloques;

    private final int bloque1 =8, bloque2= 7, bloque3 = 6, bloque4 = 5, bloque5 = 4;
    private final int bloque6 = 3, bloque7 = 2, bloque8 = 1, bloqueVacio = 0;

    public BloquesTorres(int cantBloques) {
        bloques = new int[3][8];
        this.cantBloques = cantBloques;
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

    public boolean verificarMovimiento(int torreInicial){
        boolean posicionCorrecta = false;
        int bloqueTorre = 0;

        for(int i=0; i<bloques[0].length; i++){
            if(bloqueTorre< bloques[torreInicial][i]){
                bloqueTorre = bloques[torreInicial][i];
            }
        }

        if(bloqueTorre != 0){
            posicionCorrecta = true;
        }

        return posicionCorrecta;

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

    public int getBloque(int torre, int nBloque){
        return bloques[torre][nBloque];
    }
}

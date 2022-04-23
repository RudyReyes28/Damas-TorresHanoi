package com.ipc1.torres_hanoi.torres;

public class BloquesTorres {

    private int cantBloques;
    private int [][] bloques;

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
         }

    }

    public int getBloque(int torre, int nBloque){
        return bloques[torre][nBloque];
    }
}

#ifndef BOVINOS_H
#define BOVINOS_H

#include <stdio.h>
#include <pthread.h>
#include "Estructura.h"

void *hiloParaRegistrarBovino(void *arg){

    struct Bovino b;
    char opcion;

    printf("\nHilo #1 Registro de bovinos\n");

    do{
        printf("\nEscriba el numero del bovino: ");
        scanf("%d", &b.numeroDeBovino);

        printf("\nEscriba el peso del bovino: ");
        scanf("%f", &b.peso);

        printf("\nEscriba el lugar de procedencia: ");
        scanf(" %[^\n]", b.procedencia);

        FILE *archivo = fopen("Bovinos.dat", "ab");

        if(archivo == NULL){
            printf("\nNo se logro abrir el archivo\n");
        }else{
            fwrite(&b, sizeof(struct Bovino), 1, archivo);
            fclose(archivo);

            printf("Se agrego el bovino correctamente numero: %d \n", b.numeroDeBovino);
        }

        printf("\nQuiere agregar otro bovino?: ");
        scanf(" %c", &opcion);
    }while(opcion == 's');

    printf("\nEl hilo #1 termino\n");
    return 0;
};

#endif

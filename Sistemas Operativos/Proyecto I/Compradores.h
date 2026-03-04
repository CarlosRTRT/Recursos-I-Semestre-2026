#ifndef COMPRADORES_H
#define COMPRADORES_H

#include <stdio.h>
#include <pthread.h>
#include "Estructura.h"

void *registroDeCompradores(void *arg){
    struct Comprador c;
    char opcion;

    printf("\nHilo #2 Registro de comprador\n");

    do{
        printf("\nEscriba el numero del comprador: ");
        scanf("%d", &c.numeroDeComprador);

        printf("\nEscriba el nombre: ");
        scanf(" %[^\n]", c.nombre);

        FILE *archivo = fopen("Compradores.dat", "ab");

        if(archivo == NULL){
            printf("\nNo se logro abrir el archivo\n");
        }else{
            fwrite(&c, sizeof(struct Comprador), 1, archivo);
            fclose(archivo);
            printf("\nSe ha guardado el comprador\n");
        }

        printf("\nQuiere agregar otro comprador: ");
        scanf(" %c", &opcion);
    }while(opcion == 's');

    printf("\nEl hilo #2 ha terminado\n");
    return 0;
};

#endif

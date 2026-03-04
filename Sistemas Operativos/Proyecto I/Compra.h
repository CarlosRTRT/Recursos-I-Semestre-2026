#ifndef COMPRA_H
#define COMPRA_H

#include <stdio.h>
#include <pthread.h>
#include "Estructura.h"

float buscarPesoDeBovino(int numeroBovino){ 

    FILE *archivo = fopen("Bovinos.dat", "rb");

    if(archivo == NULL){
        printf("\nError al abrir el archivo para leer el peso del bovino\n");
        return -1;
    }

    struct Bovino b;
    float pesoEncontrado = 0;

    while((fread(&b, sizeof(struct Bovino), 1, archivo)) == 1){
        if(b.numeroDeBovino == numeroBovino){
            pesoEncontrado = b.peso;
            break;
        }
    }

    fclose(archivo);
    return pesoEncontrado;
}

void *registrarCompra(void *arg){
    struct datosDeCompra s;
    char opcion;

    printf("\nHilo #3\n");

    do{
        printf("\nEscriba el numero de bovino: ");
        scanf("%d", &s.numeroDeBovino);

        printf("\nEscriba el numero de comprador: ");
        scanf("%d", &s.numeroDeComprador);

        printf("\ningrese el precio por kilo: ");
        scanf("%2.f", &s.precioPorKilo);

        float peso = buscarPesoDeBovino(s.numeroDeBovino);

        printf("\nEstimacion del reporte: ");
        printf("\nnumero de Bovino %d", s.numeroDeBovino);
        printf("\nnumero de comprador %d", s.numeroDeComprador);
        printf("\nTotal: %.2f \n", peso * s.precioPorKilo);

        FILE *archivo = fopen("Compras.dat", "ab");

        if(archivo == NULL){
            printf("\nError al abrir el archivo compras\n");
            return NULL;
        }else{
            fwrite(&s, sizeof(struct datosDeCompra), 1, archivo);
            fclose(archivo);
            printf("\nSe ha guardado la compra en el archivo\n");
        }

        printf("\nDesea agregar otra compra?: ");
        scanf("%c", &opcion);

    }while(opcion == 's');

    printf("\nEl hilo #3 y 4 termino\n");
    return 0;
};

#endif

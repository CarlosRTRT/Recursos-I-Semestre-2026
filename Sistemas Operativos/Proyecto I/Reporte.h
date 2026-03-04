#ifndef REPORTE_H
#define REPORTE_H

#include <stdio.h>
#include <pthread.h>
#include "Estructura.h"

void buscarNombreDeComprador(int numero, char *nombreDeSalida){
    FILE *archivo = fopen("Compradores.dat", "rb");
    struct Comprador c;
    int encontrado = 0;

    if(archivo == NULL){
        printf("\nError al abrir el archivo compradores\n");
        sprintf(nombreDeSalida, "Desconocido");
        return;
    }

    while(fread(&c, sizeof(struct Comprador), 1, archivo) == 1){
        if(numero == c.numeroDeComprador){
            sprintf(nombreDeSalida, "%s", c.nombre);
            encontrado = 1;
            break;
        }
    }

    fclose(archivo);

    if(encontrado == 0){
        sprintf(nombreDeSalida, "Desconocido");
        return;
    }
}

float buscarPesoBovino(int numeroBovinoABuscar){
    FILE *archivo = fopen("Bovinos.dat", "rb");

    struct Bovino b;
    float peso = 0;

    if(archivo == NULL){
        printf("\nError al abrir el archivo de bovinos\n");
        return 0;
    }

    while(fread(&b, sizeof(struct Bovino), 1, archivo) == 1){
        if(numeroBovinoABuscar == b.numeroDeBovino){
            peso = b.peso;
            break;
        }
    }

    fclose(archivo);
    return peso;
}

void *reporte(void *arg)
{
    int numeroComprador;
    char opcion;
    char nombre[60];

    printf("\n-REPORTE DE BOVINOS POR COMPRADOR-\n");

    do
    {
        printf("\nIngrese el numero del comprador: ");
        scanf("%d", &numeroComprador);

        buscarNombreDeComprador(numeroComprador, nombre);

        printf("\nComprador: %d\n", numeroComprador);
        printf("Nombre: %s\n", nombre);
        printf("\nBovinos comprados:\n");

        FILE *archivo;
        struct datosDeCompra s;

        int cantidad = 0;
        float totalPagado = 0;

        archivo = fopen("Compras.dat", "rb");

        if (archivo == NULL)
        {
            printf("\nNo se pudo abrir el archivo Subasta.dat\n");
        }
        else
        {
            while (fread(&s, sizeof(struct datosDeCompra), 1, archivo) == 1)
            {
                if (s.numeroDeComprador == numeroComprador)
                {

                    float peso;
                    float total = buscarPesoBovino(s.numeroDeBovino) * s.precioPorKilo;

                    peso = buscarPesoBovino(s.numeroDeBovino);

                    printf("\nNumero Bovino: %d\n", s.numeroDeBovino);
                    printf("Peso: %.2f kg\n", peso);
                    printf("Precio por kilo: %.2f\n", s.precioPorKilo);
                    printf("Total pagado: %.2f\n", total);


                    cantidad++;
                    totalPagado = totalPagado + total;
                }
            }

            fclose(archivo);

            if (cantidad == 0)
            {
                printf("\nEste comprador no tiene compras.\n");
            }
            else
            {
                printf("\nCantidad de bovinos: %d\n", cantidad);
                printf("Total pagado: %.2f\n", totalPagado);
            }
        }

        printf("\nDesea consultar otro comprador?: ");
        scanf(" %c", &opcion);

    } while (opcion == 's');

    printf("\nHilo #5 terminado\n");

    return NULL;
};

#endif

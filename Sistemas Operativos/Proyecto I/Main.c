#include <stdio.h>
#include <pthread.h>

#include "Estructura.h"
#include "Bovinos.h"
#include "Compradores.h"
#include "Compra.h"
#include "Reporte.h"

int main() {

    pthread_t miHilo;

    int opcion;

    printf("SISTEMA DE SUBASTA GANADERA\n");

    do {
        printf("\nMENU PRINCIPAL\n");
        printf("1. Registrar bovino\n");
        printf("2. Registrar comprador\n");
        printf("3. Registrar compra\n");
        printf("4. Consultar reporte por comprador\n");
        printf("0. Salir\n");
        printf("Elija una opcion: ");
        scanf("%d", &opcion);

        if (opcion == 1) {
            // hilo 1
            pthread_create(&miHilo, NULL, hiloParaRegistrarBovino, NULL);
            pthread_join(miHilo, NULL);

        } else if (opcion == 2) {
            // hilo 2
            pthread_create(&miHilo, NULL, registroDeCompradores, NULL);
            pthread_join(miHilo, NULL);

        } else if (opcion == 3) {
            // hilo 3  y 4
            pthread_create(&miHilo, NULL, registrarCompra, NULL);
            pthread_join(miHilo, NULL);

        } else if (opcion == 4) {
            // hilo 5
            pthread_create(&miHilo, NULL, reporte, NULL);
            pthread_join(miHilo, NULL);

        } else if (opcion == 0) {
            printf("\nGracias\n");

        } else {
            printf("Opcion no valida\n");
        }

    } while (opcion != 0);

    return 0;
}

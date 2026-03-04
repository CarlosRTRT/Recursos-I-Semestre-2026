#ifndef ESTRUCTURA_H
#define ESTRUCTURA_H

//datos de  un Bovino

struct Bovino {
    int numeroDeBovino;
    float peso;
    char procedencia[50];
};

//datos del comprador

struct Comprador {
    int numeroDeComprador;
    char nombre[100];
};

//datos de la compra

struct datosDeCompra {
    int numeroDeBovino;
    int numeroDeComprador;
    float precioPorKilo;
};

#endif

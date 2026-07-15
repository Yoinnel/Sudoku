package com.univalle.sudoku.model;

import java.util.Random;

public class Sudoku {

    private final int[][] solucion1 = {
            {1, 2, 3, 4, 5, 6},
            {4, 5, 6, 1, 2, 3},
            {2, 3, 4, 5, 6, 1},
            {5, 6, 1, 2, 3, 4},
            {3, 4, 5, 6, 1, 2},
            {6, 1, 2, 3, 4, 5}
    };

    private final int[][] solucion2 = {
            {6, 5, 4, 3, 2, 1},
            {3, 2, 1, 6, 5, 4},
            {5, 4, 3, 2, 1, 6},
            {2, 1, 6, 5, 4, 3},
            {4, 3, 2, 1, 6, 5},
            {1, 6, 5, 4, 3, 2}
    };

    private int[][] tableroSolucion;

    private int[][] tableroJugador;

    private final Random random = new Random();

    private boolean[][] pistasIniciales = new boolean[6][6];

    public void nuevoJuego() {
        if (random.nextBoolean()) {
            tableroSolucion = solucion1;
        } else {
            tableroSolucion = solucion2;
        }
        tableroJugador = new int[6][6];
        pistasIniciales = new boolean[6][6];
        generarPistas();
    }

    private void generarPistas() {
        for (int filaBloque = 0; filaBloque < 6; filaBloque += 2) {
            for (int columnaBloque = 0; columnaBloque < 6; columnaBloque += 3) {
                colocarDosPistas(filaBloque, columnaBloque);
            }
        }
    }

    private void colocarDosPistas(int filaInicio, int columnaInicio) {
        int pistas = 0;
        while (pistas < 2) {
            int fila = filaInicio + random.nextInt(2);
            int columna = columnaInicio + random.nextInt(3);
            if (tableroJugador[fila][columna] == 0) {
                tableroJugador[fila][columna] =
                        tableroSolucion[fila][columna];
                pistasIniciales[fila][columna] = true;
                pistas++;
            }
        }
    }

    public int[][] getTableroJugador() {
        return tableroJugador;
    }

    public int[][] getTableroSolucion() {
        return tableroSolucion;
    }

    public boolean[][] getPistasIniciales() {
        return pistasIniciales;
    }

    public void actualizarCelda(int fila, int columna, int numero) {
        tableroJugador[fila][columna] = numero;
    }

    public int getValorCelda(int fila, int columna) {
        return tableroJugador[fila][columna];
    }

    public boolean tableroCompleto() {
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                if (tableroJugador[fila][columna] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int contarCasillasVacias() {
        int contador = 0;
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                if (tableroJugador[fila][columna] == 0) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public int getValorSolucion(int fila, int columna) {
        return tableroSolucion[fila][columna];
    }

    public void colocarAyuda(int fila, int columna) {
        tableroJugador[fila][columna] =
                tableroSolucion[fila][columna];
    }
}
package com.univalle.sudoku.model;

public class Validador {

    public boolean esMovimientoValido(int[][] tablero, int fila, int columna, int numero) {
        return validarNumero(numero) && validarFila(tablero, fila, columna, numero) && validarColumna(tablero, fila, columna, numero) && validarBloque(tablero, fila, columna, numero);
    }

    private boolean validarNumero(int numero) {
        return numero >= 1 && numero <= 6;
    }

    private boolean validarFila(int[][] tablero, int fila, int columna, int numero) {
        for (int i = 0; i < 6; i++) {
            if (i != columna && tablero[fila][i] == numero) {
                return false;
            }
        }
        return true;
    }

    private boolean validarColumna(int[][] tablero, int fila, int columna, int numero) {
        for (int i = 0; i < 6; i++) {
            if (i != fila && tablero[i][columna] == numero) {
                return false;
            }
        }
        return true;
    }

    private boolean validarBloque(int[][] tablero, int fila, int columna, int numero) {
        int filaInicio = (fila / 2) * 2;
        int columnaInicio = (columna / 3) * 3;
        for (int i = filaInicio; i < filaInicio + 2; i++) {
            for (int j = columnaInicio; j < columnaInicio + 3; j++) {
                if ((i != fila || j != columna) && tablero[i][j] == numero) {
                    return false;
                }
            }
        }
        return true;
    }
}
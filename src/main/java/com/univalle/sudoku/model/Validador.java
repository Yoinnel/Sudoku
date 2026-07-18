/**
 * Esta clase contiene la lógica encargada de validar los
 * movimientos realizados por el jugador de acuerdo con
 * las reglas del Sudoku.
 *
 * @author Esteban Granada Salamanca
 * @author Yoinnel Gabriel Martinez Brito
 *
 * @version 1.0
 * @since 2026
 */


package com.univalle.sudoku.model;

public class Validador {

    /**
     * Verifica si el movimiento realizado por el jugador
     * cumple con todas las reglas del Sudoku.
     *
     * @param tablero Matriz que representa el tablero actual.
     * @param fila Fila donde se desea ingresar el número.
     * @param columna Columna donde se desea ingresar el número.
     * @param numero Número que será validado.
     * @return true si el movimiento es válido; false en caso contrario.
     */
    public boolean esMovimientoValido(int[][] tablero, int fila, int columna, int numero) {
        return validarNumero(numero) && validarFila(tablero, fila, columna, numero) && validarColumna(tablero, fila, columna, numero) && validarBloque(tablero, fila, columna, numero);
    }

    /**
     * Comprueba que el número ingresado
     * se encuentre dentro del rango permitido.
     *
     * @param numero Número ingresado por el jugador.
     * @return true si el número está entre 1 y 6.
     */
    private boolean validarNumero(int numero) {
        return numero >= 1 && numero <= 6;
    }

    /**
     * Verifica que el número no se encuentre repetido
     * en la fila correspondiente.
     *
     * @param tablero Matriz del tablero actual.
     * @param fila Fila que será validada.
     * @param columna Columna de la celda evaluada.
     * @param numero Número ingresado.
     * @return true si no existe otro número igual en la fila.
     */


    private boolean validarFila(int[][] tablero, int fila, int columna, int numero) {
        for (int i = 0; i < 6; i++) {
            if (i != columna && tablero[fila][i] == numero) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica que el número no se encuentre repetido
     * en la columna correspondiente.
     *
     * @param tablero Matriz del tablero actual.
     * @param fila Fila de la celda evaluada.
     * @param columna Columna que será validada.
     * @param numero Número ingresado.
     * @return true si no existe otro número igual en la columna.
     */
    private boolean validarColumna(int[][] tablero, int fila, int columna, int numero) {
        for (int i = 0; i < 6; i++) {
            if (i != fila && tablero[i][columna] == numero) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica que el número no se encuentre repetido
     * dentro del bloque de 2x3 correspondiente.
     *
     * @param tablero Matriz del tablero actual.
     * @param fila Fila de la celda evaluada.
     * @param columna Columna de la celda evaluada.
     * @param numero Número ingresado.
     * @return true si el bloque cumple con las reglas del Sudoku.
     */
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
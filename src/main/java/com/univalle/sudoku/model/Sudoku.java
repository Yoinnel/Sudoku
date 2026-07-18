/**
 * Esta clase contiene la lógica principal del juego Sudoku.
 * Se encarga de generar el tablero, almacenar la solución,
 * administrar las pistas iniciales y controlar el estado
 * de la partida.
 *
 * @author Esteban Granada Salamanca
 * @author Yoinnel Gabriel Martinez Brito
 *
 * @version 1.0
 * @since 2026
 */

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

    /**
     * Inicia una nueva partida de Sudoku.
     * Selecciona aleatoriamente una de las soluciones disponibles,
     * reinicia el tablero del jugador y genera las pistas iniciales.
     */
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

    /**
     * Genera las pistas iniciales del tablero.
     * Recorre cada bloque de 2x3 y coloca dos números
     * aleatorios como pistas para iniciar la partida.
     */
    private void generarPistas() {
        for (int filaBloque = 0; filaBloque < 6; filaBloque += 2) {
            for (int columnaBloque = 0; columnaBloque < 6; columnaBloque += 3) {
                colocarDosPistas(filaBloque, columnaBloque);
            }
        }
    }

    /**
     * Coloca dos pistas aleatorias dentro de un bloque específico
     * del tablero.
     *
     * @param filaInicio Fila donde inicia el bloque.
     * @param columnaInicio Columna donde inicia el bloque.
     */
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

    /**
     * Obtiene el tablero actual del jugador.
     *
     * @return Matriz que representa el tablero del jugador.
     */
    public int[][] getTableroJugador() {
        return tableroJugador;
    }

    /**
     * Obtiene el tablero con la solución del Sudoku.
     *
     * @return Matriz que contiene la solución del juego.
     */
    public int[][] getTableroSolucion() {
        return tableroSolucion;
    }

    /**
     * Obtiene las posiciones correspondientes a las pistas iniciales.
     *
     * @return Matriz de valores booleanos que indica las pistas iniciales.
     */
    public boolean[][] getPistasIniciales() {
        return pistasIniciales;
    }

    /**
     * Actualiza el valor de una celda del tablero del jugador.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     * @param numero Número que se asignará a la celda.
     */
    public void actualizarCelda(int fila, int columna, int numero) {
        tableroJugador[fila][columna] = numero;
    }

    /**
     * Obtiene el valor almacenado en una celda del tablero.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     * @return Valor almacenado en la posición indicada.
     */
    public int getValorCelda(int fila, int columna) {
        return tableroJugador[fila][columna];
    }

    /**
     * Verifica si el tablero ha sido completado.
     *
     * @return true si no existen casillas vacías; false en caso contrario.
     */
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

    /**
     * Cuenta la cantidad de casillas vacías que existen en el tablero.
     *
     * @return Número de casillas vacías.
     */
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

    /**
     * Obtiene el valor correcto de una celda según el tablero solución.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     * @return Valor correcto correspondiente a la posición indicada.
     */
    public int getValorSolucion(int fila, int columna) {
        return tableroSolucion[fila][columna];
    }

    /**
     * Coloca en el tablero del jugador el valor correcto
     * de una celda como ayuda.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     */
    public void colocarAyuda(int fila, int columna) {
        tableroJugador[fila][columna] =
                tableroSolucion[fila][columna];
    }
}
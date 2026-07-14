package com.univalle.sudoku.controller;
import com.univalle.sudoku.model.Sudoku;
import com.univalle.sudoku.model.Validador;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;



public class SudokuController {

    @FXML private TextField tf00;
    @FXML private TextField tf01;
    @FXML private TextField tf02;
    @FXML private TextField tf03;
    @FXML private TextField tf04;
    @FXML private TextField tf05;

    @FXML private TextField tf10;
    @FXML private TextField tf11;
    @FXML private TextField tf12;
    @FXML private TextField tf13;
    @FXML private TextField tf14;
    @FXML private TextField tf15;

    @FXML private TextField tf20;
    @FXML private TextField tf21;
    @FXML private TextField tf22;
    @FXML private TextField tf23;
    @FXML private TextField tf24;
    @FXML private TextField tf25;

    @FXML private TextField tf30;
    @FXML private TextField tf31;
    @FXML private TextField tf32;
    @FXML private TextField tf33;
    @FXML private TextField tf34;
    @FXML private TextField tf35;

    @FXML private TextField tf40;
    @FXML private TextField tf41;
    @FXML private TextField tf42;
    @FXML private TextField tf43;
    @FXML private TextField tf44;
    @FXML private TextField tf45;

    @FXML private TextField tf50;
    @FXML private TextField tf51;
    @FXML private TextField tf52;
    @FXML private TextField tf53;
    @FXML private TextField tf54;
    @FXML private TextField tf55;


    private TextField[][] celdas = new TextField[6][6];
    private Sudoku sudoku;
    private Validador validador;
    private Random random = new Random();

    private void cargarTablero( ) {

        int[][] tablero = sudoku.getTableroJugador();

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                celdas[fila][columna].setStyle("");

                if (tablero[fila][columna] != 0) {

                    celdas[fila][columna].setText(
                            String.valueOf(tablero[fila][columna]));

                    celdas[fila][columna].setEditable(false);

                } else {

                    celdas[fila][columna].setText("");
                    celdas[fila][columna].setEditable(true);

                }

            }

        }


    }


    @FXML
    public void initialize() {

        celdas[0][0] = tf00;
        celdas[0][1] = tf01;
        celdas[0][2] = tf02;
        celdas[0][3] = tf03;
        celdas[0][4] = tf04;
        celdas[0][5] = tf05;

        celdas[1][0] = tf10;
        celdas[1][1] = tf11;
        celdas[1][2] = tf12;
        celdas[1][3] = tf13;
        celdas[1][4] = tf14;
        celdas[1][5] = tf15;

        celdas[2][0] = tf20;
        celdas[2][1] = tf21;
        celdas[2][2] = tf22;
        celdas[2][3] = tf23;
        celdas[2][4] = tf24;
        celdas[2][5] = tf25;

        celdas[3][0] = tf30;
        celdas[3][1] = tf31;
        celdas[3][2] = tf32;
        celdas[3][3] = tf33;
        celdas[3][4] = tf34;
        celdas[3][5] = tf35;

        celdas[4][0] = tf40;
        celdas[4][1] = tf41;
        celdas[4][2] = tf42;
        celdas[4][3] = tf43;
        celdas[4][4] = tf44;
        celdas[4][5] = tf45;

        celdas[5][0] = tf50;
        celdas[5][1] = tf51;
        celdas[5][2] = tf52;
        celdas[5][3] = tf53;
        celdas[5][4] = tf54;
        celdas[5][5] = tf55;

        UnaryOperator<TextFormatter.Change> filtro = change -> {

            String texto = change.getControlNewText();

            if (texto.matches("[1-6]?")) {
                return change;
            }

            return null;

        };

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                celdas[fila][columna].setTextFormatter(
                        new TextFormatter<>(filtro));

            }

        }

        sudoku = new Sudoku();
        validador = new Validador();
        sudoku.nuevoJuego();

        cargarTablero();




    }

    @FXML
    private void validarCelda(KeyEvent event) {


        TextField celda = (TextField) event.getSource();

        String id = celda.getId();

        int fila = Character.getNumericValue(id.charAt(2));
        int columna = Character.getNumericValue(id.charAt(3));

        String texto = celda.getText();

        if (texto.isEmpty()) {

            celda.setStyle("");

            sudoku.actualizarCelda(fila, columna, 0);

            return;

        }

        // Solo un carácter
        if (texto.length() > 1) {
            celda.clear();
            return;
        }

        // Debe ser un número
        if (!texto.matches("[1-6]")) {
            celda.clear();
            return;
        }

        int numero = Integer.parseInt(texto);



        sudoku.actualizarCelda(fila, columna, numero);



        for (int i = 0; i < 6; i++) {


            for (int j = 0; j < 6; j++) {

                celdas[fila][columna].setStyle("");

                System.out.print(sudoku.getTableroJugador()[i][j] + " ");

            }

            System.out.println();

        }



        boolean valido = validador.esMovimientoValido(
                sudoku.getTableroJugador(),
                fila,
                columna,
                numero);

        if (valido) {

            celda.setStyle("");

            if (sudoku.tableroCompleto()) {

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                Stage stage = (Stage) tf00.getScene().getWindow();
                alerta.initOwner(stage);

                alerta.setTitle("¡Felicitaciones!");

                alerta.setHeaderText("Has completado el Sudoku.");

                alerta.setContentText("Excelente trabajo.");

                alerta.showAndWait();

            }

        } else {

            celda.setStyle("-fx-border-color: red; -fx-border-width: 2;");

            sudoku.actualizarCelda(fila, columna, 0);

        }

    }

    @FXML
    private void nuevoJuego() {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) tf00.getScene().getWindow();
        alerta.initOwner(stage);

        alerta.setTitle("Nuevo Juego");

        alerta.setHeaderText("¿Deseas iniciar una nueva partida?");

        alerta.setContentText("Se perderá el progreso actual.");

        if (alerta.showAndWait().get() == ButtonType.OK) {

            sudoku.nuevoJuego();

            cargarTablero();

        }

    }

    @FXML
    private void ayuda() {

        if (sudoku.contarCasillasVacias() <= 1) {

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            Stage stage = (Stage) tf00.getScene().getWindow();
            alerta.initOwner(stage);

            alerta.setTitle("Ayuda");

            alerta.setHeaderText("No es posible usar la ayuda");

            alerta.setContentText("Debes completar la última casilla por tu cuenta.");

            alerta.showAndWait();

            return;

        }

        int fila;
        int columna;

        do {

            fila = random.nextInt(6);
            columna = random.nextInt(6);

        } while (sudoku.getTableroJugador()[fila][columna] != 0);

        sudoku.colocarAyuda(fila, columna);

        int numero = sudoku.getValorSolucion(fila, columna);

        celdas[fila][columna].setText(String.valueOf(numero));

        celdas[fila][columna].setEditable(false);

        celdas[fila][columna].setStyle(
                "-fx-background-color: lightgreen;");

    }
}
/**
 * Game "SUDOKU"
 * Desarrollado usando JavaFX, IntelliJ Idea y SceneBuilder
 * Languages used: Java
 * <p>
 * Description of the game:
 *<p>
 * @author Estaban Granada Salamanca
 * @author Yoinnel Gabriel Martinez Brito
 *<p>
 * @version 1.0
 * @since 2026
 */

package com.univalle.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {

    /**
     *
     * @param stage El escenario principal de la aplicación sobre el cual se colocan las escenas .fxml
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("tablero.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Sudoku Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
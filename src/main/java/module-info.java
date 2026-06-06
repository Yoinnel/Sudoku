module com.univalle.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.univalle.sudoku to javafx.fxml;
    exports com.univalle.sudoku;
}
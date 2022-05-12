module ru.gb.lesson7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gb.lesson7 to javafx.fxml;
    exports ru.gb.lesson7;
}
package de.medieninformatik.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Controller {

    @FXML
    public TextArea eingabeText;

    @FXML
    public void onSave(ActionEvent actionEvent) {
        try {

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(null);

            if(file != null) {
                Files.writeString(file.toPath(), eingabeText.getText());
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }

    @FXML
    public void onOpen(ActionEvent actionEvent) {

        try {

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                eingabeText.setText(Files.readString(file.toPath()));
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}

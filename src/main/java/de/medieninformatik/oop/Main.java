package de.medieninformatik.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code Main} class contains the main() method which represents the main execution point of each program.
 *
 * <p>ATTENTION: This program ist just an example!
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
        primaryStage.setTitle("Einfacher Texteditor");
        primaryStage.setScene(new Scene(root, 500, 450));
        primaryStage.show();
    }

    /**
     * This method is the main execution point of each program.
     *
     * @param args Represents a String array which contains parameters to configure the program execution.
     */
    public static void main (String[] args) {
        launch(args);
    }
}

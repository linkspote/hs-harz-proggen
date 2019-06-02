package de.medieninformatik.oop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * This controller is used to provide the methods for the events the user can trigger by clicking on one of the
 * menu items. It also contains a method for error handling via alert windows.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Controller {

    // Textarea element of the GUI representing the whole "editor"
    @FXML
    public TextArea taInput;

    // Variable to check file name change
    private String sFileName;

    /**
     * This method gets executed when the user either uses the corresponding menu item or the keyboard combination
     * Ctrl+O to open a text file. The content of the file then gets loaded into a text area so that it can be edited.
     *
     * @param actionEvent Represents an event which got fired up by a previously performed action
     */
    @FXML
    public void onOpen(ActionEvent actionEvent) {
        // Get parent window
        Window wOwner = ((MenuItem) actionEvent.getTarget()).getParentPopup().getOwnerWindow();

        // Tries to open and read a specific file
        try {
            // Initialize a new file chooser
            FileChooser fcOpen = new FileChooser();
            // Restrict usable file extensions
            fcOpen.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Textdokumente", "*.txt"));

            // Get file via file chooser and block parent window to prevent input manipulation
            File fileOpen = fcOpen.showOpenDialog(wOwner);

            // Set initial value file name variable for later comparison
            sFileName = fileOpen.getName();

            // Check if the chosen file is not null and can be red
            if (fileOpen != null && fileOpen.canRead()) {
                // Read content of file, must use UTF-8 encoding
                taInput.setText(Files.readString(fileOpen.toPath(), StandardCharsets.UTF_8));
            }
        // Catches the error/s
        } catch (Exception e) {
            // Shows an alert window of the error type which includes the stack trace
            showError(wOwner, e);
        }
    }

    /**
     * This method gets executed when the user either uses the corresponding menu item or the keyboard combination
     * Ctrl+Shift+S to save a text file. The content of the file then gets written to a new text file.
     *
     * @param actionEvent Represents an event which got fired up by a previously performed action
     */
    @FXML
    public void onSave(ActionEvent actionEvent) {
        // Get parent window
        Window wOwner = ((MenuItem) actionEvent.getTarget()).getParentPopup().getOwnerWindow();

        // Tries to save and therefore write a specific file
        try {
            // Initialize a new file chooser
            FileChooser fcSave = new FileChooser();
            // Restrict usable file extensions
            fcSave.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Textdokumente", "*.txt"));
            // Adjust the window title of the "open" dialog
            fcSave.setTitle("Speichern");

            // Get file via file chooser and block parent window to prevent input manipulation
            File fileSave = fcSave.showOpenDialog(wOwner);

            // TODO: Add file name check to prevent overwriting the opened file

            // Check if the chosen file is not null and can be written
            if(fileSave != null && fileSave.canWrite()) {
                // Write file using UTF-8 encoding
                Files.writeString(fileSave.toPath(), taInput.getText(), StandardCharsets.UTF_8);
            }
        // Catches the error/s
        } catch (Exception e) {
            // Shows an alert window of the error type which includes the stack trace
            showError(wOwner, e);
        }
    }

    /**
     * This method gets executed when the user either uses the corresponding menu item or the keyboard combination
     * Ctrl+Q to close the application.
     *
     * @param actionEvent Represents an event which got fired up by a previously performed action
     */
    @FXML
    public void onClose(ActionEvent actionEvent) {
        // Closes the application and stops the JVM
        Platform.exit();
    }

    /**
     * Creates an alert window of the error type which contains an error message and also the whole stack trace which
     * can be copied and pasted.
     *
     * @param p_wOwner        Represents the parent window
     * @param p_eAnyException Represents any exception
     */
    public void showError(Window p_wOwner, Exception p_eAnyException) {
        // Initialize an alert of the error type
        Alert alert = new Alert(Alert.AlertType.ERROR);

        // Set the window title and the headline displayed beneath the typical cross symbol
        alert.setTitle("Es ist ein Fehler aufgetreten");
        alert.setHeaderText(p_eAnyException.getMessage());

        // Create a vertical box, a label for the stack trace text area and the text area for the stack trace
        VBox vbAlertContent = new VBox();
        Label lblStackTrace = new Label("Stack Trace:");
        TextArea taStackTrace = new TextArea();

        // Convert the exception's stack trace to a string and set it as content of the text area
        StringWriter swStackTrace = new StringWriter();
        p_eAnyException.printStackTrace(new PrintWriter(swStackTrace));
        taStackTrace.setText(swStackTrace.toString());

        // Add the label and the text area to the vertical box
        vbAlertContent.getChildren().addAll(lblStackTrace, taStackTrace);

        // Set content of the alert's dialog part
        alert.getDialogPane().setContent(vbAlertContent);

        // Set the owner (parent) of the alert and finally show it
        alert.initOwner(p_wOwner);
        alert.show();
    }
}

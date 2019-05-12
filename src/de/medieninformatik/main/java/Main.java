import javafx.application.Application;

/**
 * The {@code Main} class contains the main() method which represents the main execution point of each program.
 *
 * <p>ATTENTION: This program ist just an example!
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Main {
    /**
     * This method is the main execution point of each program.
     *
     * @param args Represents a String array which contains parameters to configure the program execution.
     */
    public static void main (String[] args) {
        // Bug in JavaFX: Falls Subklasse von Application nicht an launch übergeben wird
        // und dar Code kein eigenes Modul ist, erfolgt ein Runtime-Fehler.
        Application.launch(KruskalApp.class, args);
    }
}

import javafx.application.Application;

/**
 * The {@code Main} class contains the main() method which represents the main execution point of each program.
 */
public class Main {
    /**
     * This method is the main execution point of each program. It is used to launch an Application using the
     * <code>KruskalApp</code> class.
     *
     * @param args Represents a String array which contains parameters to configure the program execution.
     */
    public static void main (String[] args) {
        // Bug in JavaFX: Falls Subklasse von Application nicht an launch übergeben wird
        // und dar Code kein eigenes Modul ist, erfolgt ein Runtime-Fehler.
        Application.launch(KruskalApp.class, args);
    }
}

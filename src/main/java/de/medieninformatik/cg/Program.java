package de.medieninformatik.cg;

import javax.swing.*;

/**
 * The {@code Program} class contains the main() method which is responsible for creating the application window and
 * providing its functionality.
 *
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Program {
    /**
     * Creates a new instance of the interactive ear cutting application and defines some settings of the application
     * window.
     *
     * @param args Represents a String array which contains parameters to configure the polygon points TODO: Implement command line parameters (maybe)
     */
    public static void main (String[] args) {
        EarCutting ec = new EarCutting();

        JFrame frame = new JFrame("Interactive Ear Cutting");
        frame.add(ec);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ec.requestFocus();
    }
}

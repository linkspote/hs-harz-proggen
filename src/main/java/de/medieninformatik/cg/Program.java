package de.medieninformatik.cg;

import javax.swing.*;

/**
 * The {@code Main} class contains the main() method which represents the main execution point of each program.
 *
 * <p>ATTENTION: This program ist just an example!
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Program {
    /**
     * This method is the main execution point of each program.
     *
     * @param args Represents a String array which contains parameters to configure the program execution.
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

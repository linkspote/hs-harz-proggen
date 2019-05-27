package de.medieninformatik.oop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        // Init new observable list
        ObservableList<String> observableList = FXCollections.observableArrayList();

        // TODO: Check which one should be used!
        //ListPropertyBase<String> myListProperty = new MyListProperty<>(observableList, "");
        MyListProperty<String> myListProperty = new MyListProperty<>(observableList, "");

        // Add change listener to observable list
        myListProperty.addListener(new MyChangeListener<>());

        //
        observableList.add("Erstes Element");
        System.out.println("Die Liste enthaelt zurzeit: " + observableList + "\n");

        //
        observableList.add("Zweites Element");
        System.out.println("Die Liste enthaelt zurzeit: " + observableList + "\n");
    }
}

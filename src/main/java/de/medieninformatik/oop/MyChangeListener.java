package de.medieninformatik.oop;

import javafx.collections.ListChangeListener;

/**
 *
 * @param <T>
 */
public class MyChangeListener<T> implements ListChangeListener<T> {

    /**
     *
     * @param change
     */
    @Override
    public void onChanged(Change<? extends T> change) {
        System.out.println("Etwas hat sich geaendert!");
        System.out.println("Aktuelle Groesse der Liste: " + change.getList().size());
    }
}

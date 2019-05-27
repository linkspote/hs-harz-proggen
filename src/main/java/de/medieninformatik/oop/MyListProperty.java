package de.medieninformatik.oop;

import javafx.beans.property.ListPropertyBase;
import javafx.collections.ObservableList;

/**
 *
 * @param <T>
 */
public class MyListProperty<T> extends ListPropertyBase<T> {
    //
    private ObservableList<T> bean;
    private String name;

    /**
     *
     * @param bean
     * @param name
     */
    public MyListProperty(ObservableList<T> bean, String name) {
        super(bean);
        this.bean = bean;
        this.name = name;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<T> getBean() {
        return this.bean;
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }
}

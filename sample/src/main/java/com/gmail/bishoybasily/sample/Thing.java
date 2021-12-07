package com.gmail.bishoybasily.sample;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.gmail.bishoybasily.recyclerview.item.Item;
import com.gmail.bishoybasily.recyclerview.item.ItemLoader;

/**
 * Created by bishoy on 1/2/18.
 */

public class Thing extends BaseObservable implements Item {

    public String name;

    public Thing(String name) {
        this.name = name;
    }

    @Bindable
    public boolean selected;

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thing thing = (Thing) o;

        return name.equals(thing.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    static class Loader extends Thing implements ItemLoader {

        public Loader(String name) {
            super(name);
        }
    }

}

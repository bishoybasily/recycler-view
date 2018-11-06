package com.gmail.bishoybasily.sample;

import com.gmail.bishoybasily.recyclerview.RecyclerViewAdapter;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by bishoy on 1/2/18.
 */

public class Thing extends BaseObservable implements RecyclerViewAdapter.Item {

    public String name;

    @Bindable
    public boolean selected;

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
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
}

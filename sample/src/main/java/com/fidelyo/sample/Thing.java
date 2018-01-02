package com.fidelyo.sample;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fidelyo.recyclerview.RecyclerViewAdapter;

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

}

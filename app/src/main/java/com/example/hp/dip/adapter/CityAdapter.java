package com.example.hp.dip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.City;

public class CityAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private City[] cities;

    public CityAdapter(Context context,City[] cities) {
        this.cities = cities;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cities.length;
    }

    @Override
    public Object getItem(int i) {
        return cities[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = layoutInflater.inflate(R.layout.selector,viewGroup,false);
        }
        ((TextView)view.findViewById(R.id.selectTitle)).setText(cities[i].getValueRu());
        ((TextView)view.findViewById(R.id.selectCircle)).setText(cities[i].getValueRu().substring(0,1));
        return view;
    }
}

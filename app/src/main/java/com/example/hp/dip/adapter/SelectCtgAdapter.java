package com.example.hp.dip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Selectors;
import com.example.hp.dip.model.SelectorsCity;

public class SelectCtgAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private SelectorsCity[] selectors;
    public SelectCtgAdapter(Context context) {
        this.selectors = SelectorsCity.values();
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return selectors.length;
    }

    @Override
    public Object getItem(int i) {
        return selectors[i];
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
        ((TextView)view.findViewById(R.id.selectTitle)).setText(selectors[i].getResId());
        if (selectors[i]==SelectorsCity.HOTEL){
            ((TextView)view.findViewById(R.id.selectCircle)).setText("H");
        }
        else if (selectors[i]==SelectorsCity.RESTORAN){
            ((TextView)view.findViewById(R.id.selectCircle)).setText("R");
        }
        else if (selectors[i]==SelectorsCity.MUSEI){
            ((TextView)view.findViewById(R.id.selectCircle)).setText("M");
        }
        return view;
    }
}

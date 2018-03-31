package com.example.hp.dip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Selectors;

public class SelectAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private Selectors[] selectors;
    public SelectAdapter(Context context) {
        this.selectors = Selectors.values();
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
        ((TextView)view.findViewById(R.id.selectTitle)).setText(selectors[i].getResID());
        if(selectors[i]==Selectors.CITY){
            ((TextView)view.findViewById(R.id.selectCircle)).setText("Г");
        }else if (selectors[i]==Selectors.HISTORICAL_OBJECTS){
            ((TextView)view.findViewById(R.id.selectCircle)).setText("ИО");
        }
        else if (selectors[i]==Selectors.REST_ZONE){
            ((TextView)view.findViewById(R.id.selectCircle)).setText("ЗО");
        }
        else if (selectors[i]==Selectors.SANATORIUM){
            ((TextView)view.findViewById(R.id.selectCircle)).setText("С");
        }
        return view;
    }
}

package com.example.hp.dip.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Region;
import com.example.hp.dip.task.ImageLoaderForView;
import com.example.hp.dip.util.Util;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RecyclerViewHolder> {

    Region[] regions;

    public RegionAdapter(Region[] regions) {
        this.regions = regions;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView title, subtitle, circle;


        RecyclerViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_title);
            subtitle = (TextView) itemView.findViewById(R.id.list_subtitle);
            circle = itemView.findViewById(R.id.list_circle);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {
        final Region region = regions[i];
        viewHolder.title.setText(Util.getLangIsRu() ? region.getValueRu() : region.getValueKz());
        viewHolder.subtitle.setText(String.valueOf(region.getCountSight()));
        viewHolder.circle.setText(((region.getValueKz()!=null&&region.getValueKz().length()>2) && (region.getValueRu()!=null&&region.getValueRu().length()>2)) ? (Util.getLangIsRu() ? region.getValueRu() : region.getValueKz()).substring(0,2).toUpperCase() : (Util.getLangIsRu() ? region.getValueRu() : region.getValueKz()));
        new ImageLoaderForView(viewHolder.circle).execute(region.getImageUrl());
    }
    @Override
    public int getItemCount() {
        return regions.length;
    }
}
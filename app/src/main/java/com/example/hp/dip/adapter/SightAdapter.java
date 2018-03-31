package com.example.hp.dip.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Sight;
import com.example.hp.dip.task.ImageLoaderForView;
import com.example.hp.dip.util.Util;

public class SightAdapter extends RecyclerView.Adapter<SightAdapter.RecyclerViewHolder> {
    private Sight[] sights;
    private boolean isAll;
    public SightAdapter(Sight[] sights, boolean isAll) {
        this.sights = sights;
        this.isAll = isAll;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView title, region;
        private ImageView image;
        RecyclerViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.s_list_title);
            region = (TextView) itemView.findViewById(R.id.s_list_region);
            image = itemView.findViewById(R.id.s_list_image);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_sight, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {
        final Sight sight = sights[i];
        viewHolder.title.setText(Util.getLangIsRu() ? sight.getValueRu() : sight.getValueKz());
        new ImageLoaderForView(viewHolder.image).execute(sight.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return sights.length;
    }
}

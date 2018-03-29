package com.example.hp.dip.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Coment;

public class ComentAdapter extends RecyclerView.Adapter<ComentAdapter.RecyclerViewHolder> {

    Coment[] coments;

    public ComentAdapter(Coment[] coments) {
        this.coments = coments;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView text,date;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.comentText);
            date = (TextView) itemView.findViewById(R.id.comentDate);
        }
    }

    @Override
    public ComentAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coment_list, viewGroup, false);
        return new ComentAdapter.RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComentAdapter.RecyclerViewHolder viewHolder, int i) {
        final Coment coment = coments[i];
        viewHolder.text.setText(coment.getMessage());
        viewHolder.date.setText(coment.getDate());
    }
    @Override
    public int getItemCount() {
        return coments.length;
    }
}

package com.example.hp.dip.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.example.hp.dip.adapter.RegionAdapter;
import com.example.hp.dip.model.Region;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ExecuteRegion extends AsyncTask<String,Void,Region[]> {

    private Context context;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public ExecuteRegion(Context context,ProgressBar progressBar, RecyclerView recyclerView) {
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Region[] regions) {
        super.onPostExecute(regions);
        if(regions.length==0){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setAdapter(new RegionAdapter(regions));
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected Region[] doInBackground(String... params) {
        Region[] responce = null;
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            responce = restTemplate.getForObject(params[0],Region[].class);
        }catch (Exception ex){
            responce = new Region[0];
            ex.printStackTrace();
        }
        return responce;
    }
}
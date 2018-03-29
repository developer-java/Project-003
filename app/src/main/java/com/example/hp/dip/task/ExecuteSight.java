package com.example.hp.dip.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import com.example.hp.dip.adapter.SightAdapter;
import com.example.hp.dip.model.Sight;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ExecuteSight extends AsyncTask<String,Void,Sight[]> {

    private RecyclerView recyclerView;
    private boolean isAll;
    private Context context;

    public ExecuteSight(Context context, RecyclerView recyclerView, boolean isAll) {
        this.recyclerView = recyclerView;
        this.isAll = isAll;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Sight[] sights) {
        super.onPostExecute(sights);
        recyclerView.setAdapter(new SightAdapter(sights,isAll));
    }

    @Override
    protected Sight[] doInBackground(String... params) {
        Sight[] responce = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            responce = restTemplate.getForObject(params[0],Sight[].class);
        }catch (Exception ex){
            responce = new Sight[0];
            ex.printStackTrace();
        }
        return responce;
    }
}
package com.example.hp.dip.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.hp.dip.R;
import com.example.hp.dip.adapter.CityAdapter;
import com.example.hp.dip.model.City;
import com.example.hp.dip.model.Region;
import com.example.hp.dip.task.ExecuteRegion;
import com.example.hp.dip.util.Util;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

public class SelectCity extends AppCompatActivity {


    private ListView listView;
    public static City[] _cityes;
    public static Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        Toolbar toolbar = (Toolbar)findViewById(R.id.selectCityToolbar);
        toolbar.setBackgroundColor(Color.parseColor(Util.getStaticColor()));
        toolbar.setTitle(R.string.selector1);
        toolbar.setTitleTextColor(Color.WHITE);
        Long id = getIntent().getLongExtra("REG_ID",0);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        listView = (ListView)findViewById(R.id.cityListView);
        try {
            _cityes = new ExecuteCity(this).execute(Util._API_CITY + id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SelectCity.this,SelectCategoryByCity.class);
                Util.selectedCity = _cityes[i];
                startActivity(intent);
            }
        });
    }



    public class ExecuteCity extends AsyncTask<String,Void,City[]>{

        private Context context;

        public ExecuteCity(Context context) {
            this.context = context;
        }

        @Override
        protected void onPostExecute(City[] cities) {
            super.onPostExecute(cities);
            CityAdapter adapter = new CityAdapter(context,cities);
            listView.setAdapter(adapter);
        }

        @Override
        protected City[] doInBackground(String... strings) {
            City[] responce = null;
            try{
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                responce = restTemplate.getForObject(strings[0],City[].class);
            }catch (Exception ex){
                responce = new City[0];
                ex.printStackTrace();
            }
            return responce;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

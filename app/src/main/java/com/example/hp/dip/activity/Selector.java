package com.example.hp.dip.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.hp.dip.R;
import com.example.hp.dip.adapter.SelectAdapter;
import com.example.hp.dip.model.Region;
import com.example.hp.dip.util.Util;

public class Selector extends AppCompatActivity {
    public static String  ID = "ID";
    private Region selectRegion;
    String[] items = new String[]{"Города","Отели", "Санатории","Зоны отдыха"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        selectRegion = MainActivity.responce[getIntent().getIntExtra(ID,0)];
        Toolbar toolbar = (Toolbar)findViewById(R.id.selectToolbar);
        toolbar.setBackgroundColor(Color.parseColor(Util.getStaticColor()));
        toolbar.setTitle(selectRegion.getValueRu());
        toolbar.setTitleTextColor(Color.WHITE);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        SelectAdapter adapter = new SelectAdapter(this);
        listView = (ListView)findViewById(R.id.selector);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        Intent intent = new Intent(Selector.this, SelectCity.class);
                        intent.putExtra("REG_ID",selectRegion.getId());
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(Selector.this, SightActivity.class);
                        intent.putExtra("REG_ID",selectRegion.getId());
                        intent.putExtra("URL", Util._API_SIGHT_CATEGORY_URL+"SANATORIUM/"+selectRegion.getId());
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(Selector.this, SightActivity.class);
                        intent.putExtra("REG_ID",selectRegion.getId());
                        intent.putExtra("URL", Util._API_SIGHT_CATEGORY_URL+"REST_ZONE/"+selectRegion.getId());
                        startActivity(intent);
                        break;
                    }
                    case 3:{
                        Intent intent = new Intent(Selector.this, SightActivity.class);
                        intent.putExtra("REG_ID",selectRegion.getId());
                        intent.putExtra("URL", Util._API_SIGHT_CATEGORY_URL+"HISTORICAL_OBJECTS/"+selectRegion.getId());
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
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

package com.example.hp.dip.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.hp.dip.R;
import com.example.hp.dip.adapter.SelectAdapter;
import com.example.hp.dip.adapter.SelectCtgAdapter;
import com.example.hp.dip.model.Region;
import com.example.hp.dip.util.Util;

public class SelectCategoryByCity extends AppCompatActivity {
    public static String  ID = "ID";
    private Region selectRegion = Util.selectedRegion;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category_by_city);
        Toolbar toolbar = (Toolbar)findViewById(R.id.selectCtgToolbar);
        toolbar.setBackgroundColor(Color.parseColor(Util.getStaticColor()));
        toolbar.setTitle(selectRegion.getValueRu());
        toolbar.setTitleTextColor(Color.WHITE);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        SelectCtgAdapter adapter = new SelectCtgAdapter(this);
        listView = (ListView)findViewById(R.id.selectorCtg);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        Intent intent = new Intent(SelectCategoryByCity.this, SightActivity.class);
                        intent.putExtra("REG_ID",selectRegion.getId());
                        intent.putExtra("URL", Util._API_SIGHT_CATEGORY_URL+"HOTEL/city/"+Util.selectedCity.getId());
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(SelectCategoryByCity.this, SightActivity.class);
                        intent.putExtra("REG_ID",selectRegion.getId());
                        intent.putExtra("URL", Util._API_SIGHT_CATEGORY_URL+"MUSEI/city/"+Util.selectedCity.getId());
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(SelectCategoryByCity.this, SightActivity.class);
                        intent.putExtra("REG_ID",selectRegion.getId());
                        intent.putExtra("URL", Util._API_SIGHT_CATEGORY_URL+"RESTORAN/city/"+Util.selectedCity.getId());
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

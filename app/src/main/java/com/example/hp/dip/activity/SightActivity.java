package com.example.hp.dip.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Region;
import com.example.hp.dip.model.Sight;
import com.example.hp.dip.task.ExecuteSight;
import com.example.hp.dip.task.ImageLoaderForView;
import com.example.hp.dip.util.RecyclerClickListener;
import com.example.hp.dip.util.Util;

public class SightActivity extends AppCompatActivity {

    public final static String ID = "ID";
    public static Region selectedRegion;
    public static Sight[] sights;
    private RecyclerView sightRecycler;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageRegion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight);
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            String color = Util.getStaticColor();
            setSupportActionBar(toolbar);
            selectedRegion = MainActivity.responce[getIntent().getIntExtra(ID, 0)];
            collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.sight_collapsingToolbarLayout);
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor(color));
            imageRegion = (ImageView)findViewById(R.id.regionImage);
            collapsingToolbarLayout.setTitle(selectedRegion.getValueRu());
            new ImageLoaderForView(imageRegion).execute(selectedRegion.getImageUrl());
            sightRecycler = (RecyclerView) findViewById(R.id.sightRecycler);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            sightRecycler.setLayoutManager(llm);
            sightRecycler.setHasFixedSize(true);
            sightRecycler.setNestedScrollingEnabled(true);
            sights = new ExecuteSight(this,sightRecycler,false).execute(Util._API_SIGHT_LIST_URL + selectedRegion.getId()).get();
            sightRecycler.addOnItemTouchListener(
                    new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(SightActivity.this, ViewActivity.class);
                            intent.putExtra(ViewActivity.ID, position);
                            intent.putExtra(ViewActivity.ALL, false);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    SightActivity.this,
                                    new Pair<View, String>(view.findViewById(R.id.s_list_image),
                                            getString(R.string.transition_name_circle)),
                                    new Pair<View, String>(view.findViewById(R.id.s_list_title),
                                            getString(R.string.transition_name_name))
                            );
                            ActivityCompat.startActivity(SightActivity.this, intent, options.toBundle());
                        }
                    }));
        }
         catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    /**
     * Функция выполняется при нажатии кнопки назад в телефоне
     * делает он finish() то есть закрывает текущий активити
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}

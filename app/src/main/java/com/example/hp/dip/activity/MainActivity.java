package com.example.hp.dip.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Region;
import com.example.hp.dip.model.Sight;
import com.example.hp.dip.task.ExecuteRegion;
import com.example.hp.dip.util.RecyclerClickListener;
import com.example.hp.dip.util.Util;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * created by Magzhan
 *
 * MainActivity этот активити точнее класс отвечает за страницу activity_main.xml (Список областей)
 * {@link Region} это модель для областей, а Region[] это массив всех областей
 * {@link RecyclerView} это компанент работает как Лист и можно присваивать ему Адаптер
 * {@link ProgressBar} это прогресс бар который принимает активное состояние только при получении информации из сервера)))
 *  onCreate() основной запускаемый метод, как void main(String[] args)
 *  Привязка xml Компонента с java обьектом происходит в 40 строке, где R.id.regionProgressBar это айди который мы указали в xml файле для прогрессбара
 *  В строке 42-45 настраиваем наш Layout для анимации
 *  В строке 47
 *  {@link Sight.Category} 36 строка Sigth это модлель Достопримичательностей создано мною для удобства
 *  Category это внутренный класс(Enum) это категория
 *  создаем массив категории и присваиваем ему все значения из Енама Category
 *  в Category есть значения:
 *  ALL(R.string.categoryItem0),
 *  HOTEL(R.string.categoryItem1),
 *  RESTAURANT(R.string.categoryItem2),
 *  MUSEUM(R.string.categoryItem3),
 *  MONUMENT(R.string.categoryItem4),
 *  HISTORICAL_OBJECTS(R.string.categoryItem5),
 *  RELIGIOUS_OBJECTS(R.string.categoryItem6),
 *  REST_ZONE(R.string.categoryItem7),
 *  SANATORIUM(R.string.categoryItem8);
 *  SATATORIUM (Вот здесь написана айди строковых ресурсов) их можешь посмотреть в файле res/values/string.xml
 */
public class MainActivity extends AppCompatActivity {

    public static Region[] responce;
    private RecyclerView recyclerView;
    private ProgressBar regionProgressBar;
    private Sight.Category[] categories = Sight.Category.values();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            recyclerView = (RecyclerView) findViewById(R.id.regionRecycler);
            regionProgressBar = (ProgressBar) findViewById(R.id.regionProgressBar);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(llm);
            recyclerView.setHasFixedSize(false);
            recyclerView.setNestedScrollingEnabled(true);
            responce = new ExecuteRegion(this,regionProgressBar,recyclerView).execute(Util._API_REGION_LIST_URL).get();
            drawer();
            recyclerView.addOnItemTouchListener(
                    new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            Intent intent = new Intent(MainActivity.this, SightActivity.class);
                            intent.putExtra(SightActivity.ID, position);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    MainActivity.this,
                                    new Pair<View, String>(view.findViewById(R.id.list_circle),
                                            getString(R.string.transition_name_circle)),
                                    new Pair<View, String>(view.findViewById(R.id.list_title),
                                            getString(R.string.transition_name_name)),
                                    new Pair<View, String>(view.findViewById(R.id.list_subtitle),
                                            getString(R.string.transition_name_phone))
                            );
                            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
                        }
                    }));
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * Все что здесь находится я уже обяснил в {@link About}
     */
    private void drawer() {
        String color = Util.getStaticColor();
        Toolbar toolbar = (Toolbar)findViewById(R.id.mainActToolbar);
        toolbar.setBackgroundColor(Color.parseColor(color));
        toolbar.setTitle(R.string.select_region);
        toolbar.setTitleTextColor(Color.WHITE);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Drawer.Result drawer = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withSliderBackgroundColor(Color.parseColor(color))
                .addDrawerItems(
                        new SecondaryDrawerItem()
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withName(R.string.view_region)
                                .withIdentifier(1)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withName(categories[0].getResId())
                                .withIdentifier(2)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withName(categories[1].getResId())
                                .withIdentifier(3)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withName(categories[2].getResId())
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withIdentifier(4)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withName(categories[3].getResId())
                                .withIdentifier(5)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withName(categories[4].getResId())
                                .withIdentifier(6)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withName(categories[5].getResId())
                                .withIdentifier(7)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withName(categories[6].getResId())
                                .withIdentifier(8)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withName(categories[7].getResId())
                                .withIdentifier(9)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withName(categories[8].getResId())
                                .withIdentifier(10)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.contact)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withTextColor(Color.WHITE)
                                .withIdentifier(11),
                        new SecondaryDrawerItem()
                                .withName(R.string.about)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withTextColor(Color.WHITE)
                                .withIdentifier(12),
                        new SecondaryDrawerItem()
                                .withTextColor(Color.WHITE)
                                .withName(R.string.exit)
                                .withIdentifier(13)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        Util.DRAW_SELECTED_ITEM = position;
                        switch (position){
                            case 0:{
                            }case 1:{
                                goToActivity(SightAllActivity.class,categories[0]);
                                break;
                            }case 3:{
                                goToActivity(SightAllActivity.class, categories[1]);
                                break;
                            }case 4:{
                                goToActivity(SightAllActivity.class, categories[2]);
                                break;
                            }case 5:{
                                goToActivity(SightAllActivity.class,categories[3]);
                                break;
                            }case 6:{
                                goToActivity(SightAllActivity.class,categories[4]);
                                break;
                            }case 7:{
                                goToActivity(SightAllActivity.class,categories[5]);
                                break;
                            }case 8:{
                                goToActivity(SightAllActivity.class,categories[6]);
                                break;
                            }case 9:{
                                goToActivity(SightAllActivity.class,categories[7]);
                                break;
                            }case 10:{
                                goToActivity(SightAllActivity.class,categories[8]);
                                break;
                            }case 12:{
                                goToActivity(Contacts.class);
                                break;
                            }case 13:{
                                goToActivity(About.class);
                                break;
                            }case 14:{
                                System.exit(0);
                                break;
                            }
                        }
                    }
                })
                .withSelectedItem(Util.DRAW_SELECTED_ITEM)
                .build();
    }

    public void goToActivity(Class c){
        startActivity(new Intent(this,c));
        finish();
    }
    public void goToActivity(Class c, Sight.Category ...params){
        Intent intent = new Intent(this,c);
        for(int i=0;i<params.length;i++){
            intent.putExtra("param"+i,params[i].toString());
        }
        startActivity(intent);
        finish();
    }
}

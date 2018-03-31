package com.example.hp.dip.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Sight;
import com.example.hp.dip.util.Util;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


/**
 * Все что здесь находится я уже обяснил в {@link About}
 */
public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        drawer();
    }
    private Sight.Category[] categories = Sight.Category.values();
    private void drawer() {
        String color = Util.getStaticColor();
        Toolbar toolbar = (Toolbar)findViewById(R.id.contactToolbar);
        toolbar.setBackgroundColor(Color.parseColor(color));
        toolbar.setTitle(R.string.select_region);
        toolbar.setTitleTextColor(Color.WHITE);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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
                                .withName(R.string.view_all)
                                .withIdentifier(2)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withName(categories[0].getResId())
                                .withIdentifier(3)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withName(categories[1].getResId())
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withIdentifier(4)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new SecondaryDrawerItem()
                                .withName(categories[2].getResId())
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withIdentifier(5)
                                .withBadgeTextColor(Color.parseColor(color))
                                .withTextColor(Color.WHITE),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.contact)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withTextColor(Color.WHITE)
                                .withIdentifier(6),
                        new SecondaryDrawerItem()
                                .withName(R.string.about)
                                .withSelectedTextColor(Color.parseColor(Util.getStaticColor()))
                                .withTextColor(Color.WHITE)
                                .withIdentifier(7),
                        new SecondaryDrawerItem()
                                .withTextColor(Color.WHITE)
                                .withName(R.string.exit)
                                .withIdentifier(8)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        Util.DRAW_SELECTED_ITEM = position;
                        switch (position){
                            case 0:{
                                goToActivity(MainActivity.class);
                                break;
                            }case 1:{
                                goToActivity(SightAllActivity.class,"ALL");
                                break;
                            }case 3:{
                                goToActivity(SightAllActivity.class, categories[0]);
                                break;
                            }case 4: {
                                goToActivity(SightAllActivity.class, categories[1]);
                                break;
                            }case 5: {
                                goToActivity(SightAllActivity.class, categories[2]);
                                break;
                            }case 7:{
                                goToActivity(Contacts.class);
                                break;
                            }case 8:{
                                goToActivity(About.class);
                                break;
                            }case 9:{
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
    public void goToActivity(Class c, String ...params){
        Intent intent = new Intent(this,c);
        for(int i=0;i<params.length;i++){
            intent.putExtra("param"+i,params[i]);
        }
        startActivity(intent);
        finish();
    }
}
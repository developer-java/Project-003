package com.example.hp.dip.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.adapter.ComentAdapter;
import com.example.hp.dip.model.Coment;
import com.example.hp.dip.model.Sight;
import com.example.hp.dip.model.SightC;
import com.example.hp.dip.task.ImageLoaderForView;
import com.example.hp.dip.util.Util;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Этот активити отвечает за подробрый просмотр достопримичательностей
 * Внимательно прочитай вот здесь
 * В строке 64 мы принимаем параметр от активити котоый мы пришли, так как на этот активити можно попасть из 2х других активити:
 * из ViewActivity и ViewAllActivity
 * и мы должны знать откуда брать информацию который хотим вывести, по этому этот активити принимает 2 параметра
 * число и логическое ДА или НЕТ
 * число означает позицию элемента
 * логическое значение определчет от какой активити мы перешли если ViewActivity то false, а если ViewAllActivity то true
 * Сответсвенно берем нам нужную инфомацию в строке 64
 */
public class ViewActivity extends ActionBarActivity {

    public static final String ID = "ID";
    public static final String ALL = "ALL";
    private Sight selectedSight;
    private TextView title, desc, ratting, isPayment, price, contanct, workTime, address;
    private ImageView image;
    private boolean isC = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar)findViewById(R.id.viewTollbar);  // Toolbar я уже говорил о нем
        toolbar.setBackgroundColor(Color.parseColor(Util.getStaticColor()));
        toolbar.setTitleTextColor(Color.WHITE);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(getIntent().getStringExtra(ALL).equals("2")){
            selectedSight = SightAllActivity.sights[getIntent().getIntExtra(ID,0)];
            toolbar.setTitle(selectedSight.getValueRu());
        }else if(getIntent().getStringExtra(ALL).equals("3")){
            selectedSight = SightActivity.sights[getIntent().getIntExtra(ID,0)];
            toolbar.setTitle(selectedSight.getValueRu());
        }
        title = (TextView)findViewById(R.id.viewTitle);
        address = (TextView)findViewById(R.id.viewAddress);
        desc = (TextView)findViewById(R.id.viewDesc);
        contanct = (TextView)findViewById(R.id.viewContact);
        isPayment = (TextView)findViewById(R.id.viewIsPayment);
        price = (TextView)findViewById(R.id.viewPrice);
        ratting = (TextView)findViewById(R.id.viewRatting);
        workTime = (TextView)findViewById(R.id.viewWorkTime);
        image = (ImageView) findViewById(R.id.viewImage);
        title.setText(Util.getLangIsRu() ? selectedSight.getValueRu() : selectedSight.getValueKz());
        address.setText(Util.getLangIsRu() ? selectedSight.getAddressRu() : selectedSight.getAddressKz());
        desc.setText("\t\t\t"+(Util.getLangIsRu()?selectedSight.getDescriptionRu() : selectedSight.getDescriptionKz()));
        contanct.setText(selectedSight.getContact());
        isPayment.setText(selectedSight.isPayment() ? R.string.isPayTrue : R.string.isPayFalse);
        price.setText(selectedSight.isPayment() ? selectedSight.getPrice()+" KZT" : "0 KZT");
        ratting.setText(selectedSight.getRatting()+"/10");
        workTime.setText(selectedSight.getWorkTime());
        new ImageLoaderForView(image).execute(selectedSight.getImageUrl());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

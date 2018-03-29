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
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.adapter.ComentAdapter;
import com.example.hp.dip.model.Coment;
import com.example.hp.dip.model.Sight;
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
    private EditText editText;
    private RecyclerView recyclerView;
    private Coment[] coments;
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
        if(getIntent().getBooleanExtra(ALL, false)){
            selectedSight = SightAllActivity.sights[getIntent().getIntExtra(ID,0)];
        }else{
            selectedSight = SightActivity.sights[getIntent().getIntExtra(ID,0)];
        }
        toolbar.setTitle(selectedSight.getValueRu());
        title = (TextView)findViewById(R.id.viewTitle);
        address = (TextView)findViewById(R.id.viewAddress);
        desc = (TextView)findViewById(R.id.viewDesc);
        contanct = (TextView)findViewById(R.id.viewContact);
        isPayment = (TextView)findViewById(R.id.viewIsPayment);
        price = (TextView)findViewById(R.id.viewPrice);
        ratting = (TextView)findViewById(R.id.viewRatting);
        workTime = (TextView)findViewById(R.id.viewWorkTime);
        image = (ImageView) findViewById(R.id.viewImage);
        editText = (EditText)findViewById(R.id.message);
        recyclerView = (RecyclerView) findViewById(R.id.comentRecycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(true);

        title.setText(Util.getLangIsRu() ? selectedSight.getValueRu() : selectedSight.getValueKz());
        address.setText(Util.getLangIsRu() ? selectedSight.getAddressRu() : selectedSight.getAddressKz());
        desc.setText("\t\t\t"+(Util.getLangIsRu()?selectedSight.getDescriptionRu() : selectedSight.getDescriptionKz()));
        contanct.setText(selectedSight.getContact());
        isPayment.setText(selectedSight.isPayment() ? R.string.isPayTrue : R.string.isPayFalse);
        price.setText(selectedSight.isPayment() ? selectedSight.getPrice()+" KZT" : "0 KZT");
        ratting.setText(selectedSight.getRatting()+"/10");
        workTime.setText(selectedSight.getWorkTime());
        listRefresh(recyclerView);
        new ImageLoaderForView(image).execute(selectedSight.getImageUrl());
        new GetComent(recyclerView).execute(selectedSight.getId());
    }

    public void listRefresh(RecyclerView recyclerView){

    }

    public void addComent(View view) throws ExecutionException, InterruptedException {
        if(!editText.getText().toString().isEmpty()){
            Coment coment = new Coment();
            coment.setDate(new SimpleDateFormat("dd.mm.yyyy hh:mm").format(new Date()));
            coment.setMessage(editText.getText().toString());
            coment.setSightId(selectedSight.getId());
            new SendComent(recyclerView).execute(coment);
            editText.setText("");
        }
    }


    public class SendComent extends AsyncTask<Coment, Void, Coment[]> {
        private RecyclerView recyclerView;

        public SendComent(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override
        protected void onPostExecute(Coment[] coment) {
            super.onPostExecute(coment);
            recyclerView.setAdapter(new ComentAdapter(coment));
        }

        @Override
        protected Coment[] doInBackground(Coment... coments) {
            Coment c = coments[0];
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Coment[] coment = restTemplate.getForObject(Util._API_URL+"/dip/coment/"+c.getSightId()+"/"+c.getMessage()+"/"+c.getDate()+"/",Coment[].class);
            return coment;
        }
    }
    public class GetComent extends AsyncTask<Long, Void, Coment[]> {
        private RecyclerView recyclerView;

        public GetComent(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override
        protected void onPostExecute(Coment[] coment) {
            super.onPostExecute(coment);
            recyclerView.setAdapter(new ComentAdapter(coment));
        }

        @Override
        protected Coment[] doInBackground(Long... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Coment[] coment = restTemplate.getForObject(Util._API_URL+"/dip/coment/"+params[0],Coment[].class);
            return coment;
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

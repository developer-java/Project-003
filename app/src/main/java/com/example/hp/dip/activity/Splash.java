package com.example.hp.dip.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.hp.dip.R;
import com.example.hp.dip.model.Sight;
import com.example.hp.dip.util.Util;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Splash это тот активити который запускается первым и проверяет все
 * если ли соединение с интернетом
 * есть ли соединение с сервером
 */
public class Splash extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.splashProgress);  // Создаем прогресс бар и соединяем ее с Xml представлением
        textView = (TextView)findViewById(R.id.splashText); // Точно также создаем TextView простой текст на экране и связываем с xml
        textView.setText(R.string.splashLoad); // приваиваем текст из string.xml
        progressBar.setVisibility(View.VISIBLE); // запускаем прогресс бар
        new TestingConnection(this).execute(); // ЗАпускаем тест всех соединении в заднем плане передаем ему контекст
    }

    public class TestingConnection extends AsyncTask<Void,Integer,Integer> {

        private Context context;

        public TestingConnection(Context context) {
            this.context = context;
        }

        /**
         * Это я обьяснил в {@link About}
         */
        public void goToActivity(){
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
        }

        /**
         * Эта Функция Выполняется после завершении всех проверок
         * и проверечный метод называется doInBackground выполняется он 2ом  потоке и возврашает в onPostExecute() int число
         * этот число 200,500,-1
         * 200 = Успешно
         * 500 = Нет доступа к серверу
         * -1 = Нет соединении с интернетом
         *
         */
        @Override
        protected void onPostExecute(Integer status) {
            progressBar.setVisibility(View.INVISIBLE);
            if(status == 200){
                goToActivity();
            }
        }

        /**
         * Этот метод выполняется перед началом процесса
         * И присваивает в текстВию текст R.string.splashTesttionConnection из string.xml
         */
        @Override
        protected void onPreExecute() {
            textView.setText(R.string.splashTesttionConnection);
            super.onPreExecute();
        }


        /**
         * При выполнении процесса в doInBackground если мы вызовим метод publishProgress то вызовится этот метод принимает он число
         * от 1 - 4
         *  какое число за что отвечает можешь посмотреть в конструкции if в строке 92
         */
        @Override
        protected void onProgressUpdate(Integer... param) {
            int a = param[0];
            if(a==1){
                textView.setText(R.string.splashTesttionConnectionSuccess);
            }else if (a==2){
                textView.setText(R.string.splashTesttionService);
            }else if (a==3){
                textView.setText(R.string.splashTesttionServiceSuccess);
            }else if(a==-1){
                textView.setText(R.string.splashStatus500);
            }else if (a==4){
                textView.setText(R.string.splashStatus0);
            }
            super.onProgressUpdate(param);
        }

        /**
         *Этот метод выпоняяется на 2ом потоке и не имеет доступ к View элементам
         * View элементы это те элементы который мы видим на экране на этом Активити есть 2 элмента
         * ProgressBar
         * TextView
         * вот для того что бы изменять их значение при выполнении потока нужно вызывать метод publishProgress
         * Он вызововит метод onProgressUpdate который имеет доступ к View элментам
         *
         * так вот мы передаем в publishProgress числа от 1 - 4 при разных условиях
         */
        @Override
        protected Integer doInBackground(Void... voids) {
            int status;
            try {
                Thread.sleep(1500);
                if(checkInternetConnection(context)){
                    publishProgress(1);
                    Thread.sleep(1000);
                    publishProgress(2);
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    restTemplate.getForObject(Util._API_SIGHT_LIST_URL,Sight[].class);
                    publishProgress(3);
                    status = 200;
                }else {
                    Thread.sleep(1500);
                    publishProgress(4);
                    return -1;
                }
            }catch (Exception ex){
                try{
                    Thread.sleep(1500);
                }catch (Exception e){ }
                publishProgress(-1);
                status = 500;
            }
            return status;
        }

        /**
         *Проверяяет соединение с интернетом
         * если есть возвращает true
         * иначе false
         */
        public boolean checkInternetConnection(Context context) {
            ConnectivityManager con_manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return (con_manager.getActiveNetworkInfo() != null
                    && con_manager.getActiveNetworkInfo().isAvailable()
                    && con_manager.getActiveNetworkInfo().isConnected());
        }
    }
}

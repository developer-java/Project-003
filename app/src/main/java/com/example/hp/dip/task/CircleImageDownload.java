package com.example.hp.dip.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;
import com.example.hp.dip.R;

import java.io.InputStream;
import java.net.URL;

public class CircleImageDownload extends AsyncTask<String, Void, Bitmap> {
    private View image;
    public CircleImageDownload(View image) {
        this.image = image;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        image.setBackgroundResource(R.drawable.loading);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        image.setBackground(new BitmapDrawable(bitmap));
    }
    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlOfImage = urls[0];
        Bitmap logo = null;
        try{
            InputStream is = new URL(urlOfImage).openStream();
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){
            e.printStackTrace();
        }
        return logo;
    }
}

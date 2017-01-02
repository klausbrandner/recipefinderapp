package com.a5_designs.recipefinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by macbrandy on 02/01/2017.
 */

public class ImageManager extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;
    Map<String, Bitmap> imageMap;

    public ImageManager(ImageView bmImage) {
        this.bmImage = bmImage;
        this.imageMap = new HashMap<String, Bitmap>();
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;

        if(imageMap.containsKey(urldisplay)){
            mIcon11 = imageMap.get(urldisplay);
        }else {
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                imageMap.put(urldisplay, mIcon11);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}

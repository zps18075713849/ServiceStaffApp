package com.haitian.servicestaffapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.text.Html;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class NetworkImageGetter implements Html.ImageGetter {
    @Override
    public Drawable getDrawable(String source) {
        // TODO Auto-generated method stub
        LevelListDrawable d = new LevelListDrawable();
        new LoadImage().execute(source, d);
        return d;
    }

    private final class LoadImage extends AsyncTask<Object, Void, Bitmap> {
        private LevelListDrawable mDrawable;
        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable
                        .setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                // mDrawable.setBounds(0, 0,
                // getWindowManager().getDefaultDisplay().getWidth(),
                // bitmap.getHeight());
                mDrawable.setLevel(1);
//                CharSequence t = holder.shouye_list_price.getText();
//                holder.shouye_list_price.setText(t);
            }
        }
    }

}
/**** 异步加载图片 **/

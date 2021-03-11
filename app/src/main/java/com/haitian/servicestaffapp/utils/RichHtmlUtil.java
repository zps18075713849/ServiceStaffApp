package com.haitian.servicestaffapp.utils;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 加载富文本工具类
 * Created by jamez on 2016-01-25.
 */
public class RichHtmlUtil {
    TextView tv;

    public RichHtmlUtil(TextView txt_content, String s, final String infointro, final int widthType) {
        tv = txt_content;
        final MyHandler myHandler = new MyHandler(tv);
        Thread t = new Thread(new Runnable() {
            Message msg = Message.obtain();

            @Override
            public void run() {
                // TODO Auto-generated method stub
                /**
                 * 要实现图片的显示需要使用Html.fromHtml的一个重构方法：public static Spanned
                 * fromHtml (String source, Html.ImageGetterimageGetter,
                 * Html.TagHandler
                 * tagHandler)其中Html.ImageGetter是一个接口，我们要实现此接口，在它的getDrawable
                 * (String source)方法中返回图片的Drawable对象才可以。
                 */
                Html.ImageGetter imageGetter = new Html.ImageGetter() {

                    @Override
                    public Drawable getDrawable(String source) {
                        // TODO Auto-generated method stub
                        URL url;
                        Drawable drawable = null;
                        try {
                            url = new URL(source);
                            drawable = Drawable.createFromStream(url.openStream(), null);
                            int iPicWidth = drawable.getIntrinsicWidth();
                            int iPicHeight = drawable.getIntrinsicHeight();

                            int newwidth = 0;
                            if (widthType == 0)//列表页面，图片宽度只显示为textview宽度的4分之一
                                newwidth = tv.getMeasuredWidth() / 4;
                            else //详细页面，图片宽度显示为整个TextView的宽度
                                newwidth = tv.getMeasuredWidth();
                            float newheight = iPicHeight * (newwidth / iPicWidth);//图片宽度和高度等比缩放
                            drawable.setBounds(0, 0, newwidth, (int) newheight);
                        } catch (MalformedURLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return drawable;
                    }
                };
                CharSequence test = Html.fromHtml(infointro, imageGetter, null);
                msg.what = 0x101;
                msg.obj = test;
                myHandler.sendMessage(msg);
            }
        });
        t.start();
    }


    /*
     * Handler
     * 类应该应该为static类型，否则有可能造成泄露。在程序消息队列中排队的消息保持了对目标Handler类的应用。如果Handler是个内部类，那
     * 么它也会保持它所在的外部类的引用。为了避免泄露这个外部类，应该将Handler声明为static嵌套类，并且使用对外部类的弱应用。
     */
    private static class MyHandler extends Handler {
        TextView tv;

        //WeakReference<NewsDetailActivity> mActivity;
        public MyHandler(TextView tv) {
            this.tv = tv;
            // TODO Auto-generated constructor stub
            //mActivity = new WeakReference<NewsDetailActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //NewsDetailActivity theActivity = mActivity.get();
            if (msg.what == 0x101) {
                tv.setText((CharSequence) msg.obj);
                LogUtil.e("tl_user", "0x101");
            }
            super.handleMessage(msg);
        }
    }
}
package com.haitian.servicestaffapp.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;

public class ToastUitl {
 
 
    private static Toast toast;
    private static Toast toast2;
 
    /**
     * 初始化Toast(消息，时间)
     */
    private static Toast initToast(CharSequence message, int duration) {
        if (toast == null) {
            toast = Toast.makeText(DoctorBaseAppliction.getContext(), message, duration);
        } else {
            //设置文字
            toast.setText(message);
            //设置存续期间
            toast.setDuration(duration);
        }
        return toast;
    }
 
    /**
     * 短时间显示Toast(消息 String等)
     */
    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_SHORT).show();
    }
 
 
    /**
     * 短时间显示Toast（资源id)
     */
    public static void showShort(int strResId) {
        initToast(DoctorBaseAppliction.getContext().getResources().getText(strResId), Toast.LENGTH_SHORT).show();
    }
 
    /**
     * 长时间显示Toast(消息 String等)
     */
    public static void showLong(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG).show();
    }
 
    /**
     * 长时间显示Toast（资源id)
     */
    public static void showLong(int strResId) {
        initToast(DoctorBaseAppliction.getContext().getResources().getText(strResId), Toast.LENGTH_LONG).show();
    }
 
    /**
     * 自定义显示Toast时间(消息 String等，时间)
     */
    public static void show(CharSequence message, int duration) {
        initToast(message, duration).show();
    }
 
    /**
     * 自定义显示Toast时间(消息 资源id，时间)
     */
    public static void show(int strResId, int duration) {
        initToast(DoctorBaseAppliction.getContext().getResources().getText(strResId), duration).show();
    }
 
    /**
     * 显示有image的toast 这是个view
     */
    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (toast2 == null) {
            toast2 = new Toast(DoctorBaseAppliction.getContext());
        }
        View view = LayoutInflater.from(DoctorBaseAppliction.getContext()).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast2.setView(view);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.show();
        return toast2;
 
    }
}

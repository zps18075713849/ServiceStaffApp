package com.haitian.servicestaffapp.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;

public class CreateUserDialog2 extends Dialog {

    /**
     * 上下文对象 *
     */
    Activity context;

    private TextView quxiao_tv;
    private TextView queren_tv;

    private View.OnClickListener mClickListener;
    private AdapterView.OnItemSelectedListener mOnItemSelectedListener;

    public CreateUserDialog2(Activity context) {
        super(context);
        this.context = context;
    }

    public CreateUserDialog2(Activity context, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.create_user_dialog);

        quxiao_tv = (TextView) findViewById(R.id.quxiao_tv);
        queren_tv = (TextView) findViewById(R.id.queren_tv);

        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = this.getWindow();
        dialogWindow.setBackgroundDrawableResource(R.drawable.del_comment);
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.4
//        p.width = (int) (d.getWidth() * 0.7); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象

        // 为按钮绑定点击事件监听器
        quxiao_tv.setOnClickListener(mClickListener);
        queren_tv.setOnClickListener(mClickListener);
        this.setCancelable(true);
    }
}

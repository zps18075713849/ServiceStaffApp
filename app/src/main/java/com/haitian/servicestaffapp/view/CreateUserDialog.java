package com.haitian.servicestaffapp.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;


public class CreateUserDialog extends Dialog {

    /**
     * 上下文对象 *
     */
    Activity context;

    private Button btn_save;

    private View.OnClickListener mClickListener;
    public TextView select_time;
    public EditText text_info;
    private boolean isVisible;
    private Button quxiao_btn;

    public CreateUserDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public CreateUserDialog(Activity context, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        this.mClickListener = clickListener;
    }


    public CreateUserDialog(Activity context, boolean isVisible, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        this.isVisible = isVisible;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.patient_app);

        // 根据id在布局中找到控件对象
        LinearLayout time_ll = (LinearLayout) findViewById(R.id.time_ll);
        btn_save = (Button) findViewById(R.id.btn_save_pop);
        btn_save = (Button) findViewById(R.id.btn_save_pop);
        select_time = findViewById(R.id.select_time);
        quxiao_btn = findViewById(R.id.quxiao_btn);
        text_info = (EditText) findViewById(R.id.beizhu_et);

        if(isVisible){
            time_ll.setVisibility(View.VISIBLE);
        }else{
            time_ll.setVisibility(View.GONE);
        }

        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = this.getWindow();
        dialogWindow.setBackgroundDrawableResource(R.drawable.fangan_bg);
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.6); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        // 为按钮绑定点击事件监听器
        select_time.setOnClickListener(mClickListener);
        btn_save.setOnClickListener(mClickListener);
        quxiao_btn.setOnClickListener(mClickListener);
        this.setCancelable(true);
    }
}

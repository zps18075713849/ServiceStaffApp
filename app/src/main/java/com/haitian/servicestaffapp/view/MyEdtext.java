package com.haitian.servicestaffapp.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyEdtext extends EditText {
    private Pattern emoji;
    private Context mContext;
    private InputFilter[] emojiFilters;

    public MyEdtext(Context context) {
        super(context);
        setleng();
        init(context);
    }

    public MyEdtext(Context context, AttributeSet attrs) {
        super(context, attrs);
        setleng();
        mContext = context;
        init(context);
    }

    private void init(final Context context) {
        emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);//emoji表情的正则
        //创建一个新的过滤规则
        InputFilter emojiFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);   //对输入edittext的输入内容进行比对
                if (emojiMatcher.find()) {          //如果返回时true 进行Toast并返回空字符串 来替换原来的字符串  如果为false 直接返回null 返回null是直接不做处理用接收到的值进行展示
                    Toast.makeText(context,"不能输入表情", Toast.LENGTH_SHORT).show();
                    return "";
                }
                return null;
            }
        };
        //将过滤规则加入过滤器中 
        emojiFilters[emojiFilters.length-1] = emojiFilter;
        setFilters(emojiFilters);
    }

 //将Edittext原有的限制添加上去
  private void setleng() {
        InputFilter[] filters = getFilters();
        emojiFilters=new InputFilter[getFilters().length+1];
        for (int i = 0; i <emojiFilters.length-1; i++) {
            emojiFilters[i]=filters[i];
        }
    }
}
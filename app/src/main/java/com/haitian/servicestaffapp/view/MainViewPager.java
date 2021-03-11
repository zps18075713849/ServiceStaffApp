package com.haitian.servicestaffapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MainViewPager extends ViewPager {
    private boolean noScroll = true;

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent arg0) {
        return this.noScroll ? false : super.onTouchEvent(arg0);
    }

    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return this.noScroll ? false : super.onInterceptTouchEvent(arg0);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
}


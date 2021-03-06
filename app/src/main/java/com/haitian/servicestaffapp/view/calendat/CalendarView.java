package com.haitian.servicestaffapp.view.calendat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.haitian.servicestaffapp.utils.LogUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarView extends LinearLayout implements View.OnTouchListener,
        Animation.AnimationListener, GestureDetector.OnGestureListener {

    /**
     * 点击日历
     */
    public interface OnCalendarViewListener {
        void onCalendarItemClick(CalendarView view, Date date);
    }

    /** 顶部控件所占高度 */
    private final static int TOP_HEIGHT = 40;
    /** 日历item中默认id从0xff0000开始 */
    private final static int DEFAULT_ID = 0xff0000;

    // 判断手势用
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    // 屏幕宽度和高度
    private int screenWidth;

    // 动画
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    private ViewFlipper viewFlipper;
    private GestureDetector mGesture = null;

    /** 上一月 */
    private GridView gView1;
    /** 当月 */
    private GridView gView2;
    /** 下一月 */
    private GridView gView3;

    boolean bIsSelection = false;// 是否是选择事件发生
    private Calendar calStartDate = Calendar.getInstance();// 当前显示的日历
    private Calendar calSelected = Calendar.getInstance(); // 选择的日历
    private CalendarGridViewAdapter gAdapter;
    private CalendarGridViewAdapter gAdapter1;
    private CalendarGridViewAdapter gAdapter3;
    private boolean isFirst = true;
    private LinearLayout mMainLayout;
    private TextView mTitle; // 显示年月

    private int iMonthViewCurrentMonth = 0; // 当前视图月
    private int iMonthViewCurrentYear = 0; // 当前视图年

    private static final int caltitleLayoutID = 66; // title布局ID
    private static final int calLayoutID = 55; // 日历布局ID
    private Context mContext;
    private List<String> mDayTimeDatas;
    /** 标注日期 */
    private final List<Date> markDates;

    private OnCalendarViewListener mListener;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        markDates = new ArrayList<Date>();
        mDayTimeDatas = new ArrayList<String>();
        init();
    }

    // 初始化相关工作
    protected void init() {
        // 得到屏幕的宽度
        screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;

        // 滑动的动画
        slideLeftIn = new TranslateAnimation(screenWidth, 0, 0, 0);
        slideLeftIn.setDuration(400);
        slideLeftIn.setAnimationListener(this);
        slideLeftOut = new TranslateAnimation(0, -screenWidth, 0, 0);
        slideLeftOut.setDuration(400);
        slideLeftOut.setAnimationListener(this);
        slideRightIn = new TranslateAnimation(-screenWidth, 0, 0, 0);
        slideRightIn.setDuration(400);
        slideRightIn.setAnimationListener(this);
        slideRightOut = new TranslateAnimation(0, screenWidth, 0, 0);
        slideRightOut.setDuration(400);
        slideRightOut.setAnimationListener(this);

        // 手势操作
        mGesture = new GestureDetector(mContext, this);

        // 获取到当前日期
        UpdateStartDateForMonth();
        // 绘制界面
        setOrientation(LinearLayout.HORIZONTAL);
        mMainLayout = new LinearLayout(mContext);
        LayoutParams main_params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mMainLayout.setLayoutParams(main_params);
        mMainLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        mMainLayout.setOrientation(LinearLayout.VERTICAL);
        addView(mMainLayout);

        // 顶部控件
        generateTopView();

        // 中间显示星期
        generateWeekGirdView();

        // 底部显示日历
        viewFlipper = new ViewFlipper(mContext);
        RelativeLayout.LayoutParams fliper_params = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        fliper_params.addRule(RelativeLayout.BELOW, caltitleLayoutID);
        mMainLayout.addView(viewFlipper, fliper_params);
        generateClaendarGirdView();

        // 最下方的一条线条
        LinearLayout br = new LinearLayout(mContext);
        br.setBackgroundColor(Color.argb(0xff, 0xe3, 0xee, 0xf4));
        LayoutParams params_br = new LayoutParams(
                LayoutParams.MATCH_PARENT, 3);
//        mMainLayout.addView(br, params_br);
    }

    /** 生成顶部控件 */
    @SuppressWarnings("deprecation")
    private void generateTopView() {
        // 顶部显示上一个下一个，以及当前年月
        RelativeLayout top = new RelativeLayout(mContext);
        top.setBackgroundColor(Color.WHITE);
        LayoutParams top_params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                ViewUtil.dip2px(mContext, TOP_HEIGHT));
        top.setLayoutParams(top_params);
        mMainLayout.addView(top);
        // 左方按钮、中间日期显示、右方按钮
        mTitle = new TextView(mContext);
        RelativeLayout.LayoutParams title_params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mTitle.setLayoutParams(title_params);
        mTitle.setTextColor(Color.BLACK);
        mTitle.setTextSize(18);
        mTitle.setFocusableInTouchMode(true);
//        mTitle.setMarqueeRepeatLimit(-1);
        mTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTitle.setSingleLine(true);
        mTitle.setGravity(Gravity.CENTER);
        mTitle.setHorizontallyScrolling(true);
        mTitle.setText("2014年9月");
        top.addView(mTitle);

        // 左方按钮
        ImageButton mLeftView = new ImageButton(mContext);
        StateListDrawable stateListDrawableL = new StateListDrawable();
        Drawable lDrawableNor = new BitmapDrawable(mContext.getResources(),
                BitmapFactory.decodeStream(CalendarView.class
                        .getResourceAsStream("image/left_arrow.png")));
        Drawable lDrawablePre = new BitmapDrawable(mContext.getResources(),
                BitmapFactory.decodeStream(CalendarView.class
                        .getResourceAsStream("image/left_arrow_pre.png")));
        stateListDrawableL.addState(
                new int[] { -android.R.attr.state_pressed }, lDrawableNor);
        stateListDrawableL.addState(new int[] { android.R.attr.state_pressed },
                lDrawablePre);
        mLeftView.setBackgroundDrawable(stateListDrawableL);

        RelativeLayout.LayoutParams leftPP = new RelativeLayout.LayoutParams(
                ViewUtil.dip2px(mContext, 25), ViewUtil.dip2px(mContext, 22));
        leftPP.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftPP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        leftPP.setMargins(20, 0, 0, 0);
        mLeftView.setLayoutParams(leftPP);
        mLeftView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewFlipper.setInAnimation(slideRightIn);
                viewFlipper.setOutAnimation(slideRightOut);
                viewFlipper.showPrevious();
                setPrevViewItem();
            }
        });
        top.addView(mLeftView);

        // 右方按钮
        ImageButton mRightView = new ImageButton(mContext);
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable rDrawableNor = new BitmapDrawable(mContext.getResources(),
                BitmapFactory.decodeStream(CalendarView.class
                        .getResourceAsStream("image/right_arrow.png")));
        Drawable rDrawablePre = new BitmapDrawable(mContext.getResources(),
                BitmapFactory.decodeStream(CalendarView.class
                        .getResourceAsStream("image/right_arrow_pre.png")));
        stateListDrawable.addState(new int[] { -android.R.attr.state_pressed },
                rDrawableNor);
        stateListDrawable.addState(new int[] { android.R.attr.state_pressed },
                rDrawablePre);
        mRightView.setBackgroundDrawable(stateListDrawable);

        RelativeLayout.LayoutParams rightPP = new RelativeLayout.LayoutParams(
                ViewUtil.dip2px(mContext, 25), ViewUtil.dip2px(mContext, 22));
        rightPP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightPP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rightPP.setMargins(0, 0, 20, 0);
        mRightView.setLayoutParams(rightPP);
        mRightView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewFlipper.setInAnimation(slideLeftIn);
                viewFlipper.setOutAnimation(slideLeftOut);
                viewFlipper.showNext();
                setNextViewItem();
            }
        });
        top.addView(mRightView);
    }

    /** 生成中间显示week */
    private void generateWeekGirdView() {
        GridView gridView = new GridView(mContext);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(params);
        gridView.setNumColumns(7);// 设置每行列数
        gridView.setMinimumHeight(20);// 设置每行列数
        gridView.setGravity(Gravity.CENTER_VERTICAL);// 位置居中
//        gridView.setVerticalSpacing(10);// 垂直间隔
//        gridView.setHorizontalSpacing(1);// 水平间隔
//        gridView.setBackgroundColor(Color.argb(0xff, 0xe3, 0xee, 0xf4));
//        gridView.setBackgroundResource(R.drawable.calendar_bg);
        gridView.setBackgroundColor(Color.WHITE);
        int i = screenWidth / 7;
        int j = screenWidth - (i * 7);
        int x = j / 2;
        gridView.setPadding(x, 0, 0, 0);// 居中
        WeekGridAdapter weekAdapter = new WeekGridAdapter(mContext);
        gridView.setAdapter(weekAdapter);// 设置菜单Adapter
        mMainLayout.addView(gridView);
    }

    /** 生成底部日历 */
    private void generateClaendarGirdView() {
        Calendar tempSelected1 = Calendar.getInstance(); // 临时
        Calendar tempSelected2 = Calendar.getInstance(); // 临时
        Calendar tempSelected3 = Calendar.getInstance(); // 临时
        tempSelected1.setTime(calStartDate.getTime());
        tempSelected2.setTime(calStartDate.getTime());
        tempSelected3.setTime(calStartDate.getTime());

        gView1 = new CalendarGridView(mContext);
        tempSelected1.add(Calendar.MONTH, -1);
        gAdapter1 = new CalendarGridViewAdapter(mContext, tempSelected1,
                markDates,mDayTimeDatas);
        gView1.setAdapter(gAdapter1);// 设置菜单Adapter
        gView1.setId(calLayoutID);

        gView2 = new CalendarGridView(mContext);
        gAdapter = new CalendarGridViewAdapter(mContext, tempSelected2,
                markDates,mDayTimeDatas);
        gView2.setAdapter(gAdapter);// 设置菜单Adapter
        gView2.setId(calLayoutID);

        gView3 = new CalendarGridView(mContext);
        tempSelected3.add(Calendar.MONTH, 1);
        gAdapter3 = new CalendarGridViewAdapter(mContext, tempSelected3,
                markDates,mDayTimeDatas);
        gView3.setAdapter(gAdapter3);// 设置菜单Adapter
        gView3.setId(calLayoutID);

        gView2.setOnTouchListener(this);
        gView1.setOnTouchListener(this);
        gView3.setOnTouchListener(this);

        if (viewFlipper.getChildCount() != 0) {
            viewFlipper.removeAllViews();
        }

        viewFlipper.addView(gView2);
        viewFlipper.addView(gView3);
        viewFlipper.addView(gView1);

        String title = calStartDate.get(Calendar.YEAR)
                + "年"
                + NumberHelper.LeftPad_Tow_Zero(calStartDate
                .get(Calendar.MONTH) + 1) + "月";
        mTitle.setText(title);
    }

    // 上一个月
    private void setPrevViewItem() {
        iMonthViewCurrentMonth--;// 当前选择月--
        // 如果当前月为负数的话显示上一年
        if (iMonthViewCurrentMonth == -1) {
            iMonthViewCurrentMonth = 11;
            iMonthViewCurrentYear--;
        }
        calStartDate.set(Calendar.DAY_OF_MONTH, 1); // 设置日为当月1日
        calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth); // 设置月
        calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear); // 设置年
    }
    int a = 1;
    // 下一个月
    private void setNextViewItem() {
//        if (a == 1){
//            if (iMonthViewCurrentMonth == 12) {
//                iMonthViewCurrentMonth = 0;
//                iMonthViewCurrentYear++;
//            }
//            calStartDate.set(Calendar.DAY_OF_MONTH, 1);
//            calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
//            calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
//            a++;
//        }else {
//            iMonthViewCurrentMonth++;
//            if (iMonthViewCurrentMonth == 12) {
//                iMonthViewCurrentMonth = 0;
//                iMonthViewCurrentYear++;
//            }
//            calStartDate.set(Calendar.DAY_OF_MONTH, 1);
//            calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
//            calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
//        }

        iMonthViewCurrentMonth++;
        if (iMonthViewCurrentMonth == 12) {
            iMonthViewCurrentMonth = 0;
            iMonthViewCurrentYear++;
        }
        calStartDate.set(Calendar.DAY_OF_MONTH, 1);
        LogUtil.e("--------------下个月"+iMonthViewCurrentMonth);
        calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
        calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);

    }

    // 下一个月
    private void setNextViewItem2(int iMonthViewCurrentMonth) {
        iMonthViewCurrentMonth++;
        if (iMonthViewCurrentMonth == 12) {
            iMonthViewCurrentMonth = 0;
            iMonthViewCurrentYear++;
        }
        calStartDate.set(Calendar.DAY_OF_MONTH, 1);
        calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
        calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
    }

    // 根据改变的日期更新日历
    // 填充日历控件用
    private void UpdateStartDateForMonth() {
        calStartDate.set(Calendar.DATE, 1); // 设置成当月第一天
        iMonthViewCurrentYear = calStartDate.get(Calendar.YEAR);// 得到当前日历显示的年
        if(isFirst){
            int iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);// 得到当前日历显示的月
            setNextViewItem2(iMonthViewCurrentMonth);
            isFirst = false;
        }

        iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH)-1;// 得到当前日历显示的月

        // 星期一是2 星期天是1 填充剩余天数
        int iDay = 0;
        int iFirstDayOfWeek = Calendar.MONDAY;
        int iStartDay = iFirstDayOfWeek;
        if (iStartDay == Calendar.MONDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
            if (iDay < 0)
                iDay = 6;
        }
        if (iStartDay == Calendar.SUNDAY) {
            iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
            if (iDay < 0)
                iDay = 6;
        }
        calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);
    }

    /**
     * 设置标注的日期
     *
     * @param markDates
     */
    public void setMarkDates(List<Date> markDates, List<String> mDayTimeDatas) {
        this.markDates.clear();
        this.markDates.addAll(markDates);
        this.mDayTimeDatas.addAll(mDayTimeDatas);
        gAdapter.notifyDataSetChanged();
        gAdapter1.notifyDataSetChanged();
        gAdapter3.notifyDataSetChanged();
    }

    /**
     * 设置点击日历监听
     *
     * @param listener
     */
    public void setOnCalendarViewListener(OnCalendarViewListener listener) {
        this.mListener = listener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGesture.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                viewFlipper.setInAnimation(slideLeftIn);
                viewFlipper.setOutAnimation(slideLeftOut);
                viewFlipper.showNext();
                setNextViewItem();
                Log.e("tag","向左手势");
                return true;

            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                viewFlipper.setInAnimation(slideRightIn);
                viewFlipper.setOutAnimation(slideRightOut);
                viewFlipper.showPrevious();
                setPrevViewItem();
                Log.e("tag","向有手势");
                return true;

            }
        } catch (Exception e) {
            // nothing
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO trimble 禁止点击
        // 得到当前选中的是第几个单元格
//        int pos = gView2.pointToPosition((int) e.getX(), (int) e.getY());
//        LinearLayout txtDay = (LinearLayout) gView2.findViewById(pos
//                + DEFAULT_ID);
//        txtDay.setBackgroundResource(R.drawable.yuan_calend);
//        if (txtDay != null) {
//            if (txtDay.getTag() != null) {
//                Date date = (Date) txtDay.getTag();
//                calSelected.setTime(date);
//
//                gAdapter.setSelectedDate(calSelected);
//                gAdapter.notifyDataSetChanged();
//
//                gAdapter1.setSelectedDate(calSelected);
//                gAdapter1.notifyDataSetChanged();
//
//                gAdapter3.setSelectedDate(calSelected);
//                gAdapter3.notifyDataSetChanged();
//                if (mListener != null){
////                    mListener.onCalendarItemClick(this, date);
//            }
//            }
//        }
        return false;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub
        generateClaendarGirdView();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}

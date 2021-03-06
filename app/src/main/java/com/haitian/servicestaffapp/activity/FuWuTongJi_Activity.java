package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.base.BaseActivity2;
import com.haitian.servicestaffapp.base.FuWuTongJiZengZhang_Bean;
import com.haitian.servicestaffapp.bean.HeartRateBean;
import com.haitian.servicestaffapp.bean.TongJiGongDanCount_Bean;
import com.haitian.servicestaffapp.bean.ZheXianTu_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.DateUtils;
import com.haitian.servicestaffapp.utils.HcUtils;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.utils.Tools;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.formatter.LineChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleLineChartValueFormatter;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import static com.haitian.servicestaffapp.utils.DateUtils.FORMAT_5;
import static com.haitian.servicestaffapp.utils.DateUtils.getDayTime;
import static com.haitian.servicestaffapp.utils.DateUtils.getMonthTime;
import static com.haitian.servicestaffapp.utils.DateUtils.getYearTime;


public class FuWuTongJi_Activity extends BaseActivity2  {

    private ImageView mBack;
    private TextView mTitle_tv;
    private TextView mStarttime_tv;
    private TextView mEndtime_tv;
    private RadioGroup mRadiogroup;
    private RadioButton mQuanbu_rb;
    private RadioButton mShangzhou_rb;
    private RadioButton mShangyue_rb;
    private RadioButton mShangjidu_rb;
    private RadioButton mShangbannian_rb;
    private TextView mGongdan_count;
    private TextView mGongdan_tv;
    private LineChartView mLineChart;

    private boolean hasAxesY = true; //????????????Y??????
    private String axesYName = "axesYName";//Y????????????
    private String axesXName = "axesXName";
    private boolean hasLines = true;//?????????????????????
    private boolean hasPoints = true;//????????????????????????
    private ValueShape shape = ValueShape.CIRCLE;//?????????????????????,??????????????? ???????????? ???ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND???
    private boolean isFilled = false;//?????????????????????X??????????????????
    private boolean isCubic = true;//?????????????????????????????????????????????
    private boolean hasLabels = true;//??????????????????????????????
    private boolean hasLabelForSelected = true;//????????????????????????????????????????????????hasLabels(true);????????????
    private boolean hasTiltedLabels = false;  //X?????????????????????????????????????????????true???????????????
    private String lineColor = "#00DAFF";//????????????(#FF0000??????)
    private int textColor = Color.BLACK;//??????????????????
    private String[] mLabelsX;
    private float[] mValuesY;

    private int totalDays;//?????????????????????????????????
    private int totalDays_huxi;//?????????????????????????????????
    private float minY = 0f;//Y??????????????????
    private float maxY = 50f;//Y??????????????????


    private List<HeartRateBean> heartList = new ArrayList<>();

    List<PointValue> mPointValues = new ArrayList<PointValue>();
    List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();
    private TextView mShangzhouzengzhang_tv;
    private TextView mShangyuezengzhang_tv;
    private TextView mShangjiduzengzhang_tv;
    private ArrayList<String> yuefenlist = new ArrayList<>();
    private ArrayList<String> dancountlist = new ArrayList<>();
    private String mStartTime;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
//        Resources res = super.getResources();
//        Configuration config = new Configuration();
//        config.setToDefaults();
//        res.updateConfiguration(config, res.getDisplayMetrics());
        return R.layout.activity_fu_wu_tong_ji_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mTitle_tv = findViewById(R.id.title_tv);

        mBack.setVisibility(View.VISIBLE);
        mTitle_tv.setText("????????????");
        mTitle_tv.setVisibility(View.VISIBLE);

        mStarttime_tv = findViewById(R.id.starttime_tv);
        mEndtime_tv = findViewById(R.id.endtime_tv);

        mRadiogroup = findViewById(R.id.radiogroup);
        //??????
        mQuanbu_rb = findViewById(R.id.quanbu_rb);
        //??????
        mShangzhou_rb = findViewById(R.id.shangzhou_rb);
        //??????
        mShangyue_rb = findViewById(R.id.shangyue_rb);
        //?????????
        mShangjidu_rb = findViewById(R.id.shangjidu_rb);
        //?????????
        mShangbannian_rb = findViewById(R.id.shangbannian_rb);


        mGongdan_count = findViewById(R.id.gongdan_count);
        mGongdan_tv = findViewById(R.id.gongdan_tv);
//        mGongdan_count.setTextSize(28);
//        mGongdan_tv.setTextSize(28);
        mLineChart = findViewById(R.id.line);

        mShangzhouzengzhang_tv = findViewById(R.id.shangzhouzengzhang_tv);
        mShangyuezengzhang_tv = findViewById(R.id.shangyuezengzhang_tv);
        mShangjiduzengzhang_tv = findViewById(R.id.shangjiduzengzhang_tv);


        initTimePicker();


        //X????????????
        mLabelsX = new String[12];
        //??????????????????
        mValuesY = new float[12];

        mLabelsX[0] = "1???";
        mLabelsX[1] = "2???";
        mLabelsX[2] = "3???";
        mLabelsX[3] = "4???";
        mLabelsX[4] = "5???";
        mLabelsX[5] = "6???";
        mLabelsX[6] = "7???";
        mLabelsX[7] = "8???";
        mLabelsX[8] = "9???";
        mLabelsX[9] = "10???";
        mLabelsX[10] = "11???";
        mLabelsX[11] = "12???";

        mValuesY[0] = Float.valueOf("0.0");
        mValuesY[1] = Float.valueOf("0.0");
        mValuesY[2] = Float.valueOf("0.0");
        mValuesY[3] = Float.valueOf("0.0");
        mValuesY[4] = Float.valueOf("0.0");
        mValuesY[5] = Float.valueOf("0.0");
        mValuesY[6] = Float.valueOf("0.0");
        mValuesY[7] = Float.valueOf("0.0");
        mValuesY[8] = Float.valueOf("0.0");
        mValuesY[9] = Float.valueOf("0.0");
        mValuesY[10] = Float.valueOf("0.0");
        mValuesY[11] = Float.valueOf("0.0");



    }

    @Override
    protected void initData() {
        super.initData();
        //???????????? ?????????
        requestZengZhang();

        //?????????
        requestZheXian();
    }

    //?????????
    private void requestZheXian() {
        final Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));

        OkHttpUtil.getInteace().doPost(Constants.TONGJIZHEXIANTU, map, FuWuTongJi_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                LogUtil.e("????????????????????????" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                LogUtil.e("????????????????????????" + json);
                Gson gson = new Gson();
                final ZheXianTu_Bean bean = gson.fromJson(json, ZheXianTu_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041) {
                            List<ZheXianTu_Bean.DataBean> data = bean.getData();
                            for (int i = 0; i < data.size(); i++) {

                                if (data.get(i).get_$1()!=0){
                                    yuefenlist.add("1???");
                                    dancountlist.add(data.get(i).get_$1()+"");
                                }

                                if (data.get(i).get_$2()!=0){
                                    yuefenlist.add("2???");
                                    dancountlist.add(data.get(i).get_$2()+"");
                                }
                                if (data.get(i).get_$3()!=0){
                                    yuefenlist.add("3???");
                                    dancountlist.add(data.get(i).get_$3()+"");
                                }
                                if (data.get(i).get_$4()!=0){
                                    yuefenlist.add("4???");
                                    dancountlist.add(data.get(i).get_$4()+"");
                                }
                                if (data.get(i).get_$5()!=0){
                                    yuefenlist.add("5???");
                                    dancountlist.add(data.get(i).get_$5()+"");
                                }
                                if (data.get(i).get_$6()!=0){
                                    yuefenlist.add("6???");
                                    dancountlist.add(data.get(i).get_$6()+"");
                                }
                                if (data.get(i).get_$7()!=0){
                                    yuefenlist.add("7???");
                                    dancountlist.add(data.get(i).get_$7()+"");
                                }
                                if (data.get(i).get_$8()!=0){
                                    yuefenlist.add("8???");
                                    dancountlist.add(data.get(i).get_$8()+"");
                                }
                                if (data.get(i).get_$9()!=0){
                                    yuefenlist.add("9???");
                                    dancountlist.add(data.get(i).get_$9()+"");
                                }
                                if (data.get(i).get_$10()!=0){
                                    yuefenlist.add("10???");
                                    dancountlist.add(data.get(i).get_$10()+"");
                                }
                                if (data.get(i).get_$11()!=0){
                                    yuefenlist.add("11???");
                                    dancountlist.add(data.get(i).get_$11()+"");
                                }
                                if (data.get(i).get_$12()!=0){
                                    yuefenlist.add("12???");
                                    dancountlist.add(data.get(i).get_$12()+"");
                                }

                            }

                            for (int i = 0; i < yuefenlist.size(); i++) {
                                LogUtil.e("?????????"+yuefenlist.get(i));
                                LogUtil.e("?????????"+dancountlist.get(i));

                                if (yuefenlist.get(i).equals("1???")){
                                    mValuesY[0] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("2???")){
                                    mValuesY[1] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("3???")){
                                    mValuesY[2] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("4???")){
                                    mValuesY[3] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("5???")){
                                    mValuesY[4] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("6???")){
                                    mValuesY[5] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("7???")){
                                    mValuesY[6] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("8???")){
                                    mValuesY[7] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("9???")){
                                    mValuesY[8] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("10???")){
                                    mValuesY[9] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("11???")){
                                    mValuesY[10] = Float.valueOf(dancountlist.get(i));
                                }else if (yuefenlist.get(i).equals("12???")){
                                    mValuesY[11] = Float.valueOf(dancountlist.get(i));
                                }


                            }



                        }else {
                            Toast.makeText(mContext, "?????????????????????", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        getAxisXYLables();//??????x????????????
                        getAxisPoints();//???????????????
                        initLineChart();//?????????
                    }
                });
            }
        });

    }

    //???????????? ?????????
    private void requestZengZhang() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""));

        OkHttpUtil.getInteace().doPost(Constants.TONGJIZENGZHANG, map, FuWuTongJi_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("??????????????????????????????" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("??????????????????????????????" + json);
                Gson gson = new Gson();
                final FuWuTongJiZengZhang_Bean bean = gson.fromJson(json, FuWuTongJiZengZhang_Bean.class);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041) {
                            mShangzhouzengzhang_tv.setText(bean.getData().getShangzhou());
                            mShangyuezengzhang_tv.setText(bean.getData().getShangyue());
                            mShangjiduzengzhang_tv.setText(bean.getData().getShangjidu());

                            //????????????????????????
                            requestTongJiAllCount(1);       //1?????? 2?????? 3?????? 4????????? 5?????????

                        } else {
                            mShangzhouzengzhang_tv.setText("+--%");
                            mShangyuezengzhang_tv.setText("+--%");
                            mShangjiduzengzhang_tv.setText("+--%");
                        }
                    }
                });

            }
        });
    }

    //????????????????????????
    private void requestTongJiAllCount(int i) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""));
        map.put("biaoshi", i);

        OkHttpUtil.getInteace().doPost(Constants.TONGJIALL, map, FuWuTongJi_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("?????????????????????????????????" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("?????????????????????????????????" + json);
                Gson gson = new Gson();
                final TongJiGongDanCount_Bean bean = gson.fromJson(json, TongJiGongDanCount_Bean.class);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041) {
                            mGongdan_count.setText(bean.getData() + "");
                        } else {
                            mGongdan_count.setText("--");
                        }
                    }
                });

            }
        });
    }

    private void requestTongJiAllTimeCount(String startTime,String endTime) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""));
        map.put("biaoshi", 1);
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        OkHttpUtil.getInteace().doPost(Constants.TONGJIALL, map, FuWuTongJi_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("?????????????????????????????????" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("?????????????????????????????????" + json);
                Gson gson = new Gson();
                final TongJiGongDanCount_Bean bean = gson.fromJson(json, TongJiGongDanCount_Bean.class);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041) {
                            mGongdan_count.setText(bean.getData() + "");
                        } else {
                            mGongdan_count.setText("--");
                        }
                    }
                });

            }
        });
    }


    private void getAxisPoints() {
        for (int i = 0; i < mValuesY.length; i++) {
            mPointValues.add(new PointValue(i, mValuesY[i]));

        }
        for (int i = 0; i < mPointValues.size(); i++) {
            LogUtil.e(i + "", mPointValues.get(i).toString());
        }
    }

    private void getAxisXYLables() {
        LogUtil.e("lablsx???" + mLabelsX.length);

        for (int i = 0; i < mLabelsX.length; i++) {
            LogUtil.e("---------" + mLabelsX[i]);
            mAxisXValues.add(new AxisValue(i).setLabel(mLabelsX[i]));
        }
//        for (float i = minY; i <= maxY; i+=1) {
        mAxisYValues.add(new AxisValue(0f).setLabel(0f + ""));
        mAxisYValues.add(new AxisValue(5f).setLabel(5f + ""));
        mAxisYValues.add(new AxisValue(10f).setLabel(10f + ""));
        mAxisYValues.add(new AxisValue(15f).setLabel(15f + ""));
        mAxisYValues.add(new AxisValue(20f).setLabel(20f + ""));
        mAxisYValues.add(new AxisValue(25f).setLabel(25f + ""));
        mAxisYValues.add(new AxisValue(30f).setLabel(30f + ""));
        mAxisYValues.add(new AxisValue(35f).setLabel(35f + ""));
        mAxisYValues.add(new AxisValue(40f).setLabel(40f + ""));
        mAxisYValues.add(new AxisValue(45f).setLabel(45f + ""));
        mAxisYValues.add(new AxisValue(50f).setLabel(50f + ""));

//        }
    }

    private void initLineChart() {


//        List<String> list1 = new ArrayList<>();
//        List<PointValue> mPointValues1 = new ArrayList<>();
//
//        for (int i = 0; i < 16; i++) {
//            list1.add(String.valueOf(10 * Math.random()));
//        }
//
//        for (int i = 0; i < list1.size(); i++) {
//            float values1= Float.parseFloat(list1.get(i));
//            mPointValues1.add(new PointValue(i, values1));
//        }

//        Line line1 = new Line(mPointValues1);

        Line line = new Line(mPointValues).setColor(Color.parseColor(lineColor));  //???????????????
        List<Line> lines = new ArrayList<Line>();
        LineChartValueFormatter chartValueFormatter = new SimpleLineChartValueFormatter(2);
        line.setFormatter(chartValueFormatter);//?????????????????????2??????
        line.setShape(shape);//????????????????????????????????????
        line.setCubic(isCubic);//?????????????????????????????????????????????
        line.setFilled(isFilled);//???????????????????????????
        line.setHasLabels(hasLabels);//???????????????????????????????????????
        line.setHasLabelsOnlyForSelected(hasLabelForSelected);//??????????????????????????????
        line.setHasLines(hasLines);//??????????????????????????????false ??????????????????????????????
        line.setHasPoints(hasPoints);//??????????????????
        line.setStrokeWidth(1);//????????????
        line.setPointRadius(2);//???????????????

//        line1.setFormatter(chartValueFormatter);//?????????????????????2??????
//        line1.setShape(shape);//????????????????????????????????????
//        line1.setCubic(isCubic);//?????????????????????????????????????????????
//        line1.setFilled(isFilled);//???????????????????????????
//        line1.setHasLabels(hasLabels);//???????????????????????????????????????
//        line1.setHasLabelsOnlyForSelected(hasLabelForSelected);//??????????????????????????????
//        line1.setHasLines(hasLines);//??????????????????????????????false ??????????????????????????????
//        line1.setHasPoints(hasPoints);//??????????????????
//        line1.setStrokeWidth(1);//????????????
//        line1.setPointRadius(2);//???????????????
//        line1.setColor(Color.parseColor("#00DAFF"));
        lines.add(line);
//        lines.add(line1);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //?????????X
        Axis axisX = new Axis(); //X???
        axisX.setValues(mAxisXValues);
        axisX.setHasTiltedLabels(hasTiltedLabels);  //X?????????????????????????????????????????????true???????????????
        axisX.setTextColor(textColor);  //??????????????????
//        axisX.setName(axesXName);
        axisX.setMaxLabelChars(0);
        axisX.setTextSize(12);//??????????????????
        data.setAxisXBottom(axisX);//x ????????????

        //?????????Y
        if (hasAxesY) {
            Axis axisY = new Axis();
            axisY.setHasLines(false);
//            axisY.setName(axesYName);
            axisY.setValues(mAxisYValues);
            data.setAxisYLeft(axisY);
        }

        //??????????????????????????????????????????????????????
        mLineChart.setInteractive(true);
        mLineChart.setZoomEnabled(true);
        mLineChart.setZoomType(ZoomType.HORIZONTAL);
        mLineChart.setMaxZoom((float) 1);//??????????????????
        mLineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        mLineChart.setLineChartData(data);
        mLineChart.setVisibility(View.VISIBLE);

        //??????????????????
        mLineChart.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                Toast.makeText(FuWuTongJi_Activity.this, "" + value.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });

        Viewport v = new Viewport(mLineChart.getMaximumViewport());
        v.bottom = minY;
        v.top = maxY;
        //??????Y????????????,??????????????????,Y?????????????????????????????????????????????????????????,?????????????????????
        mLineChart.setMaximumViewport(v);

        //???2??????????????????????????????lineChart.setMaximumViewport(v);??????????????????,?????????????????????????????????????????????????????????????????????
        v.left = totalDays - 7;
        v.right = totalDays - 1;
        mLineChart.setCurrentViewport(v);
    }


    @Override
    protected void initListener() {
        super.initListener();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mStarttime_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEndtime_tv.setText("");
                HcUtils.hideKeyboard(FuWuTongJi_Activity.this);
                pvTime.show(mStarttime_tv);
            }
        });

        mEndtime_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startHintTime = mStarttime_tv.getHint().toString().trim();
                mStartTime = mStarttime_tv.getText().toString().trim();
                if (startHintTime.equals("??????????????????")&& mStartTime.isEmpty()){
                    Toast.makeText(mContext, "?????????????????????", Toast.LENGTH_SHORT).show();
                    return;
                }
                HcUtils.hideKeyboard(FuWuTongJi_Activity.this);
                pvTime.show(mEndtime_tv);
            }
        });

        mEndtime_tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });


        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.quanbu_rb: {
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.white));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mStarttime_tv.setText("");
                        mEndtime_tv.setText("");
                        requestTongJiAllCount(1);
                        break;
                    }
                    case R.id.shangzhou_rb: {
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mStarttime_tv.setText("");
                        mEndtime_tv.setText("");
                        requestTongJiAllCount(2);

                        break;
                    }

                    case R.id.shangyue_rb: {
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mStarttime_tv.setText("");
                        mEndtime_tv.setText("");
                        requestTongJiAllCount(3);

                        break;
                    }

                    case R.id.shangjidu_rb: {
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mStarttime_tv.setText("");
                        mEndtime_tv.setText("");
                        requestTongJiAllCount(4);

                        break;
                    }

                    case R.id.shangbannian_rb: {
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));
                        mStarttime_tv.setText("");
                        mEndtime_tv.setText("");
                        requestTongJiAllCount(5);

                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });


    }


    /**
     * ?????????????????????
     */
    private void initTimePicker() {
        String yearTime = getYearTime(new Date(System.currentTimeMillis()));
        String monthTime = getMonthTime(new Date(System.currentTimeMillis()));
        String dayTime = getDayTime(new Date(System.currentTimeMillis()));
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(Integer.parseInt(yearTime), Integer.parseInt(monthTime) - 1, Integer
                .parseInt(dayTime));
        pvTime = new TimePickerView.Builder(this, new TimePickerView
                .OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                String found_date = DateUtils.dateToString(date, FORMAT_5);
                ((TextView) v).setText(found_date);
                if (v.getId() == R.id.endtime_tv){
                    LogUtil.e("????????????");
                    mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                    mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));


                    mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                    mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                    mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                    mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                    mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                    mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                    mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                    mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                    requestTongJiAllTimeCount(mStartTime,found_date);

                }
            }
        })
                //?????????????????? ????????????????????????????????????????????????
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("???", "???", "???", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setCancelColor(getResources().getColor(R.color.main_color))
                .setSubmitColor(getResources().getColor(R.color.main_color))
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //????????????????????????
                .setDecorView(null)
                .build();
    }

    @Override
    public Context context() {
        return null;
    }


}

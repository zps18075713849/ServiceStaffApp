package com.haitian.servicestaffapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.CodeMessageBean;
import com.haitian.servicestaffapp.bean.DataSettingBean;
import com.haitian.servicestaffapp.bean.Register_Bean;
import com.haitian.servicestaffapp.bean.Register_BuMen_Bean;
import com.haitian.servicestaffapp.bean.Register_FuwuType_Bean;
import com.haitian.servicestaffapp.okutils.DoctorNetService;
import com.haitian.servicestaffapp.okutils.NetWorkRequestInterface;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.CompressImageUtil;
import com.haitian.servicestaffapp.utils.HcUtils;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.utils.ToastUtils;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import me.nereo.multi_image_selector.bean.Image;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.haitian.servicestaffapp.utils.HcUtils.getAppProcessName;

public class DataSetting_Activity extends BaseActivity {


    private ImageView mBack;
    private TextView mTitle_right_tv;
    private TextView mTitle_tv;
    private LinearLayout mId_card_zheng_ll;
    private LinearLayout mId_card_fan_ll;
    private ImageView mId_card_zheng_iv;
    private ImageView mId_card_fan_iv;
    private static final int REQUEST_CODE_TAKE_PICTURE = 2001;
    private static final int REQUEST_CODE_TAKE_PICTURE1 = 2002;
    private String pathImage;
    private int type;  //0 身份证正面   1  反面
    private TextView mMobile_tv;
    private Button mSave_btn;
    private TextView mService_tv;
    private TextView mGongsi_tv;
    private OptionsPickerView pvOptions;
    private List<String> mFuWuItem;
    private List<String> mCardItem;
    private Register_FuwuType_Bean mFuWuType_Bean;
    private Register_BuMen_Bean mBuMen_Bean;
    private String suoshuid = "";
    private String fuwuid = "";
    private String mIdcard_zheng = "";
    private String mIdcard_fan = "";


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_data_setting_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mTitle_right_tv = findViewById(R.id.title_right_tv);
        mTitle_tv = findViewById(R.id.title_tv);

        mBack.setVisibility(View.VISIBLE);
        mTitle_right_tv.setVisibility(View.VISIBLE);
        mTitle_tv.setVisibility(View.VISIBLE);
        mTitle_tv.setText("个人资料");
        mTitle_right_tv.setText("编辑");

        //身份证正面
        mId_card_zheng_ll = findViewById(R.id.id_card_zheng_ll);
        //身份证反面
        mId_card_fan_ll = findViewById(R.id.id_card_fan_ll);
        //身份证正面照片
        mId_card_zheng_iv = findViewById(R.id.id_card_zheng_iv);
        //身份证反面照片
        mId_card_fan_iv = findViewById(R.id.id_card_fan_iv);
        mMobile_tv = findViewById(R.id.mobile_tv);
        mSave_btn = findViewById(R.id.save_btn);
        mService_tv = findViewById(R.id.service_tv);
        mGongsi_tv = findViewById(R.id.gongsi_tv);



        mService_tv.setEnabled(false);
        mGongsi_tv.setEnabled(false);
        mId_card_fan_ll.setEnabled(false);
        mId_card_zheng_ll.setEnabled(false);

        mCardItem = new ArrayList<>();
        mFuWuItem = new ArrayList<>();
        requestData();
    }

    //18241087377
    private void requestData() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""));

        OkHttpUtil.getInteace().doPost(Constants.USERDATA, map, DataSetting_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("个人信息获取失败：" + e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("个人信息获取成功：" + json);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            DataSettingBean bean = gson.fromJson(json, DataSettingBean.class);
                            if (bean.getCode() == 20041) {
                                mMobile_tv.setText(bean.getData().getUser().getUser_name());
                                mService_tv.setText(bean.getData().getLeixing().getFuwutype());

                                mGongsi_tv.setText(bean.getData().getBumen().getBumen().getAgencyName());


                                suoshuid = bean.getData().getBumen().getBumen().getId()+"";
                                fuwuid = bean.getData().getLeixing().getId()+"";


                                String shenfenzheng = bean.getData().getUser().getShenfenzheng();
                                String[] split = shenfenzheng.split(",");
                                if (split.length == 2) {
                                    mIdcard_zheng = split[0];
                                    mIdcard_fan = split[1];

                                    mId_card_zheng_iv.setVisibility(View.VISIBLE);
                                    mId_card_fan_iv.setVisibility(View.VISIBLE);
                                    Glide.with(mContext).load(split[0]).into(mId_card_zheng_iv);
                                    Glide.with(mContext).load(split[1]).into(mId_card_fan_iv);
                                }


                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
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

        mTitle_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSave_btn.setVisibility(View.VISIBLE);
                mTitle_right_tv.setVisibility(View.GONE);
//                String mobile = mMobile_ed.getText().toString().trim(); Instrumentation Native quit  Allowed  queue  dispatch Proxy
                mService_tv.setEnabled(true);
                mGongsi_tv.setEnabled(true);
                mId_card_fan_ll.setEnabled(true);
                mId_card_zheng_ll.setEnabled(true);
            }
        });
        mSave_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    requestUpdateData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mService_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(DataSetting_Activity.this);
                initOptionPickerFuWuId(mService_tv);
                requestFuWuType();
            }
        });
        mGongsi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(DataSetting_Activity.this);
                initOptionPickerBuMenSuoShu(mGongsi_tv);
                requestSuoShu();
            }
        });
        //身份证正面点击事件
        mId_card_zheng_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 0;
                if (ContextCompat.checkSelfPermission(DataSetting_Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {   //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在 onRequestPermissionsResult 中使用到
                    ActivityCompat.requestPermissions(DataSetting_Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_TAKE_PICTURE1);
                } else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    showSelect();
                }
            }
        });

        //身份证反面点击事件
        mId_card_fan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                if (ContextCompat.checkSelfPermission(DataSetting_Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {   //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在 onRequestPermissionsResult 中使用到
                    ActivityCompat.requestPermissions(DataSetting_Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_TAKE_PICTURE1);
                } else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    showSelect();
                }
            }
        });

    }

    private void requestUpdateData() {
        showWaitDialog();
        String mobile = mMobile_tv.getText().toString().trim();
        String service = mService_tv.getText().toString().trim();
        String gongsi = mGongsi_tv.getText().toString().trim();

        if (service.equals("")) {
            hideWaitDialog();
            Toast.makeText(mContext, "请选择您的服务类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (gongsi.equals("")) {
            hideWaitDialog();
            Toast.makeText(mContext, "请选择您的所属公司", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mIdcard_zheng.equals("") || mIdcard_zheng == null) {
            hideWaitDialog();
            Toast.makeText(mContext, "请上传您的身份证正面照", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mIdcard_fan.equals("") || mIdcard_fan == null) {
            hideWaitDialog();
            Toast.makeText(mContext, "请上传您的身份证反面照", Toast.LENGTH_SHORT).show();
            return;
        }

//        File file_zheng = new File(mIdcard_zheng);
//        File file_fan = new File(mIdcard_fan);



            mId_card_fan_iv.setDrawingCacheEnabled(true);
            mId_card_zheng_iv.setDrawingCacheEnabled(true);
            Bitmap drawingCache_fan = mId_card_fan_iv.getDrawingCache();
            Bitmap drawingCache_zheng = mId_card_zheng_iv.getDrawingCache();

            File file_zheng = saveBitmapFile(drawingCache_zheng,Environment.getExternalStorageDirectory() + "/xxDoctor/" + System.currentTimeMillis()+1000 + ".png");
            File file_fan = saveBitmapFile(drawingCache_fan,Environment.getExternalStorageDirectory() + "/xxDoctor/" + System.currentTimeMillis() + ".png");

            mId_card_zheng_iv.setDrawingCacheEnabled(false);
            mId_card_fan_iv.setDrawingCacheEnabled(false);


            MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
            RequestBody filebody_zheng = MultipartBody.create(MEDIA_TYPE_PNG, file_zheng);
            RequestBody filebody_fan = MultipartBody.create(MEDIA_TYPE_PNG, file_fan);
            MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
            //参数以添加header方式将参数封装，否则上传参数为空
            // 设置请求体
            multiBuilder.setType(MultipartBody.FORM);
            multiBuilder.addFormDataPart("shenfenzhenglist", file_zheng.getName(), filebody_zheng);
            multiBuilder.addFormDataPart("shenfenzhenglist", file_fan.getName(), filebody_fan);


            // 封装请求参数,这里最重要
            HashMap<String, String> params = null;
            try {
                params = new HashMap<>();
                params.put("user_type", fuwuid);
                params.put("user_department", suoshuid);
                LogUtil.e("--------" + fuwuid);
                LogUtil.e("--------" + suoshuid);
                params.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""));
            } catch (Exception e) {
                e.printStackTrace();
            }

            //参数以添加header方式将参数封装，否则上传参数为空
            if (params != null && !params.isEmpty()) {
                for (String key : params.keySet()) {
                    multiBuilder.addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                            RequestBody.create(null, params.get(key)));
                }
            }

            RequestBody multiBody = multiBuilder.build();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(Constants.UPDATEUSERDATA).post(multiBody).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    hideWaitDialog();
                    LogUtil.e("修改失败：" + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    hideWaitDialog();
                    LogUtil.e("修改成功");
                    try {
                        if (response.isSuccessful()) {
                            final String str = response.body().string();
                            Gson gson = new Gson();
                            final CodeMessageBean bean = gson.fromJson(str, CodeMessageBean.class);

                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    LogUtil.e("code："+bean.getCode());
                                    if (bean.getCode() == 20021) {
                                        Toast.makeText(DataSetting_Activity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(DataSetting_Activity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });
                        } else {
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(DataSetting_Activity.this, "修改失败！", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            });
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });







    }



    private void initOptionPickerFuWuId(final TextView view) {//条件选择器初始化
        //设置滚轮文字大小
        //默认选中项
        //设置两横线之间的间隔倍数
        //设置外部遮罩颜色
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = mFuWuItem.get(options1);
                view.setText(tx);
                fuwuid = mFuWuType_Bean.getData().get(options1).getId() + "";
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCancelColor(getResources().getColor(R.color.liji_c_blue))
                .setSubmitColor(getResources().getColor(R.color.liji_c_blue))
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }

    private void initOptionPickerBuMenSuoShu(final TextView view) {//条件选择器初始化
        //设置滚轮文字大小
        //默认选中项
        //设置两横线之间的间隔倍数
        //设置外部遮罩颜色
        //设置滚轮文字大小
//默认选中项
//设置两横线之间的间隔倍数
//设置外部遮罩颜色
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = mCardItem.get(options1);
                view.setText(tx);
                suoshuid = mBuMen_Bean.getData().get(options1).getId() + "";
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCancelColor(getResources().getColor(R.color.liji_c_blue))
                .setSubmitColor(getResources().getColor(R.color.liji_c_blue))
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }


    private void requestFuWuType() {
        showWaitDialog();
        mFuWuItem.clear();
        Map<String, Object> map = new HashMap<>();
        OkHttpUtil.getInteace().doPost(Constants.REGISTER_FUWU, map, DataSetting_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("服务类型失败：" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("服务类型成功：" + json);
                Gson gson = new Gson();
                mFuWuType_Bean = gson.fromJson(json, Register_FuwuType_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (mFuWuType_Bean.getCode() == 20041) {
                                for (int i = 0; i < mFuWuType_Bean.getData().size(); i++) {
                                    mFuWuItem.add(mFuWuType_Bean.getData().get(i).getFuwutype());
                                }
                                pvOptions.setPicker(mFuWuItem);
                                pvOptions.show();
                            } else {
                                Toast.makeText(mContext, "服务类型获取失败", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void requestSuoShu() {
        showWaitDialog();
        mCardItem.clear();
        Map<String, Object> map = new HashMap<>();
        OkHttpUtil.getInteace().doPost(Constants.REGISTER_BUMEN, map, DataSetting_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("部门所属失败：" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("部门所属成功：" + json);
                Gson gson = new Gson();
                mBuMen_Bean = gson.fromJson(json, Register_BuMen_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mBuMen_Bean.getCode() == 20041) {
                            for (int i = 0; i < mBuMen_Bean.getData().size(); i++) {
                                mCardItem.add(mBuMen_Bean.getData().get(i).getAgencyName());
                            }
                            pvOptions.setPicker(mCardItem);
                            pvOptions.show();
                        } else {
                            Toast.makeText(mContext, "部门所属获取失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }

    private void showSelect() {
        ActionSheet.createBuilder(DataSetting_Activity.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍摄照片", "手机相册")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        if (index == 0) {
                            try {
                                startcamera();
                            } catch (Exception e) {
                                Toast.makeText(mContext, "相机未启动，请检查权限或真机使用", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        } else {
                            startPhotoAlbum();
                        }
                    }
                })
                .show();
    }

    //打开相机
    private void startcamera() {
        if (Build.VERSION.SDK_INT >= 23) {
            int request = ContextCompat.checkSelfPermission(DataSetting_Activity.this, Manifest.permission.CAMERA);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(DataSetting_Activity.this, new String[]{Manifest.permission.CAMERA}, 123);
                return;//
            } else {
                String b = null;
                try {
                    b = Environment.getExternalStorageDirectory().getCanonicalPath() + "/xxDoctor/";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File file2 = new File(b);
                LogUtil.e("file2：--------" + file2);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                //权限同意，不需要处理,去掉用拍照的方法
                // Toast.makeText(this,"权限同意",Toast.LENGTH_SHORT).show();
                //1.打开相机  MediaStore.ACTION_IMAGE_CAPTURE 打开相机的动作
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //将图片存入sdcard  1.存入   2.在Sdcard创建文件
                it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    pathImage = Environment.getExternalStorageDirectory().getCanonicalPath() + "/xxDoctor/" + System.currentTimeMillis() + ".png";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pathImage)));
                //启动
                startActivityForResult(it, REQUEST_CODE_TAKE_PICTURE);
            }
        } else {
            //低于23 不需要特殊处理，去掉用拍照的方法
        }
    }
    //打开相册
    private void startPhotoAlbum() {
        //相册导入
        Intent intent5 = new Intent(this, MultiImageSelectorActivity.class);
        // whether show camera
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
        // max select image amount
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        // default select images (support array list)
        ArrayList<String> imageList = new ArrayList<>();
        intent5.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, imageList);
        startActivityForResult(intent5, REQUEST_CODE_TAKE_PICTURE1);
    }

    @SuppressLint("WrongConstant")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //图片
        super.onActivityResult(requestCode, resultCode, data);
        String v = Environment.getExternalStorageDirectory() + "/xxDoctor/";
        File file = new File(v);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (requestCode == REQUEST_CODE_TAKE_PICTURE1 && resultCode == this.RESULT_OK) {
            List<String> resultList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            for (int i = 0; i < resultList.size(); i++) {
                String a = resultList.get(i);
                LogUtil.e("tttttttttt", "压缩之前: " + a);
                Bitmap bitmap = CompressImageUtil.getimage(a);
                String galleryPath = Environment.getExternalStorageDirectory() + "/xxDoctor/" + System.currentTimeMillis() + ".png";
                File b = saveBitmapFile(bitmap, galleryPath);

                //相册
                if (type == 0) {
                    //身份证正面
                    mIdcard_zheng = galleryPath;
                    mId_card_zheng_iv.setVisibility(View.VISIBLE);
                    Glide.with(DataSetting_Activity.this).load(galleryPath).into(mId_card_zheng_iv);
                } else if (type == 1) {
                    //身份证反面
                    mIdcard_fan = galleryPath;
                    mId_card_fan_iv.setVisibility(View.VISIBLE);
                    Glide.with(DataSetting_Activity.this).load(galleryPath).into(mId_card_fan_iv);
                }
            }
        }

        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == DataSetting_Activity.this.RESULT_OK) {
            //点击完成后调取的裁剪功能  com.android.camera.action.CROP裁剪的Action
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到图片   1.得到Sdcard的下存的图片，如果有就拿到，如果没有就创建
            it.setDataAndType(Uri.fromFile(new File(pathImage)), "image/*");
            //设置是否支持裁剪
            it.putExtra("CROP", true);
            //设置裁剪框的比例
            it.putExtra("aspaceX", 1);
            it.putExtra("aspaceY", 1);
            //设置图片输入的大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //让图片返回intent接受
            it.putExtra("return-data", true);
            //启动
            startActivityForResult(it, 200);
        } //3.将图片设置展示


        if (requestCode == 200 && resultCode == DataSetting_Activity.this.RESULT_OK) {
//            Uri uri = data.getData();
//            String path = uri.getPath();
//            Log.e("上传头像11111", "onActivityResult: " + path);
//            File filePath = new File(path);
//            if (!filePath.exists()) {
//                filePath.mkdirs();
//            }
//            CompressImageUtil.compressImage(this, filePath.getPath());

            Bitmap bitmap = data.getParcelableExtra("data");
            File file1 = null;
            try {
                file1 = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory().getCanonicalPath() + "/xxDoctor/" + System.currentTimeMillis() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("上传头像22222", "onActivityResult: " + file1.getPath());
            if (!file1.exists()) {
                file1.mkdirs();
            }

            //相机
            if (type == 0) {
                //身份证正面
                mIdcard_zheng = file1.getPath();
                mId_card_zheng_iv.setVisibility(View.VISIBLE);
                Glide.with(DataSetting_Activity.this).load(file1.getPath()).into(mId_card_zheng_iv);
            } else if (type == 1) {
                //身份证反面
                mIdcard_fan = file1.getPath();
                mId_card_fan_iv.setVisibility(View.VISIBLE);
                Glide.with(DataSetting_Activity.this).load(file1.getPath()).into(mId_card_fan_iv);
            }


            //上传照片到服务器
//            upLoadPic(file);
        }
    }

    public static File saveBitmapFile(Bitmap bitmap, String filepath) {
        File file = new File(filepath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }





    // affiliate dept role
    @Override
    public Context context() {
        return null;
    }
}

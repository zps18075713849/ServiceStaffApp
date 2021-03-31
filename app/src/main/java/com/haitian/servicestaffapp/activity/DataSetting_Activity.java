package com.haitian.servicestaffapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import com.haitian.servicestaffapp.utils.HcUtils;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.utils.ToastUtils;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String suoshuid = "3";
    private String fuwuid = "2";
    private String mIdcard_zheng;
    private String mIdcard_fan;

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

        mCardItem = new ArrayList<>();
        mFuWuItem = new ArrayList<>();
        requestData();
    }

    private void requestData() {
        Map<String,Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));
        DoctorNetService.requestDataSetting(Constants.USERDATA, map, new NetWorkRequestInterface() {
            @Override
            public void onError(Throwable throwable) {
                LogUtil.e("---------");
            }

            @Override
            public void onNext(Object info) {
                DataSettingBean codeMessageBean = (DataSettingBean) info;
                try {
                    LogUtil.e("---------"+codeMessageBean.getCode());
                    if(codeMessageBean.getCode()==20041){
                        try {
                            mMobile_tv.setText(codeMessageBean.getData().getUser().getUser_name());
                            mService_tv.setText(codeMessageBean.getData().getLeixing().getFuwutype());
                            mGongsi_tv.setText(codeMessageBean.getData().getBumen().getSupplier_name());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
//                String mobile = mMobile_ed.getText().toString().trim(); Instrumentation Native quit  Allowed  queue  dispatch Proxy
                mService_tv.setClickable(true);
                mGongsi_tv.setClickable(true);
                mId_card_fan_ll.setClickable(true);
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
        mId_card_zheng_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 0;
                if (ContextCompat.checkSelfPermission(DataSetting_Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    showSelect();
                } else {
                    //否则去请求相机权限
                    ToastUtils.showMessage(DataSetting_Activity.this, "请打开相机权限");
                    ActivityCompat.requestPermissions(DataSetting_Activity.this, new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        mId_card_fan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                if (ContextCompat.checkSelfPermission(DataSetting_Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    showSelect();
                } else {
                    //否则去请求相机权限
                    ToastUtils.showMessage(DataSetting_Activity.this, "请打开相机权限");
                    ActivityCompat.requestPermissions(DataSetting_Activity.this, new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
        mService_tv.setClickable(false);
        mGongsi_tv.setClickable(false);
        mId_card_fan_ll.setClickable(false);
    }

    private void requestUpdateData() {

        File file_zheng = new File(mIdcard_zheng);
        File file_fan = new File(mIdcard_fan);

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
            LogUtil.e("--------"+fuwuid);
            LogUtil.e("--------"+suoshuid);
            params.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));
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
                                if (bean.getCode() == 20011) {
                                    Toast.makeText(DataSetting_Activity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(DataSetting_Activity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
                    }else {
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
                fuwuid = mFuWuType_Bean.getData().get(options1).getId()+"";
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
                suoshuid = mBuMen_Bean.getData().get(options1).getId()+"";
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


    private void requestFuWuType(){
        showWaitDialog();
        mFuWuItem.clear();
        Map<String, Object> map = new HashMap<>();
        OkHttpUtil.getInteace().doPost(Constants.REGISTER_FUWU, map, DataSetting_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("服务类型失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("服务类型成功："+json);
                Gson gson = new Gson();
                mFuWuType_Bean = gson.fromJson(json, Register_FuwuType_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (mFuWuType_Bean.getCode() == 20041){
                                for (int i = 0; i < mFuWuType_Bean.getData().size(); i++) {
                                    mFuWuItem.add(mFuWuType_Bean.getData().get(i).getFuwutype());
                                }
                                pvOptions.setPicker(mFuWuItem);
                                pvOptions.show();
                            }else {
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

    private void requestSuoShu(){
        showWaitDialog();
        mCardItem.clear();
        Map<String, Object> map = new HashMap<>();
        OkHttpUtil.getInteace().doPost(Constants.REGISTER_BUMEN, map, DataSetting_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("部门所属失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("部门所属成功："+json);
                Gson gson = new Gson();
                mBuMen_Bean = gson.fromJson(json, Register_BuMen_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mBuMen_Bean.getCode() == 20041){
                            for (int i = 0; i < mBuMen_Bean.getData().size(); i++) {
                                mCardItem.add(mBuMen_Bean.getData().get(i).getAgencyName());
                            }
                            pvOptions.setPicker(mCardItem);
                            pvOptions.show();
                        }else {
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
                            startcamera();
                        } else {
                            startPhotoAlbum();
                        }
                    }
                })
                .show();
    }

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
                    b = Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(DataSetting_Activity.this) + "/xxhold/";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File file2 = new File(b);
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
                    pathImage = Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(DataSetting_Activity.this) + "/xxhold/" + System.currentTimeMillis() + ".png";
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

    private void startPhotoAlbum() {
        //调用相册
        //Intent.ACTION_PICK打开相册 
        Intent it = new Intent(Intent.ACTION_PICK);
        //设置图片的格式
        it.setType("image/*");
        //启动
        startActivityForResult(it, REQUEST_CODE_TAKE_PICTURE1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //2.判断请求码和结果码  结果码系统提供 RESULT_OK
        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
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

        if (requestCode == REQUEST_CODE_TAKE_PICTURE1 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
            //得到图片
            Uri data2 = data.getData();
            //调取裁剪功能
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到图片设置格式
            it.setDataAndType(data2, "image/*");
            //设置是否支持裁剪
            it.putExtra("CROP", true);
            //设置宽高比
            it.putExtra("aspectX", 10);
            it.putExtra("aspectY", 10);
            //设置输出图片的大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //返回
            it.putExtra("return-data", true);
            startActivityForResult(it, 201);
        }
        if (requestCode == 200 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
//            Uri uri = data.getData();
//            String path = uri.getPath();
//            Log.e("上传头像11111", "onActivityResult: " + path);
//            File filePath = new File(path);
//            if (!filePath.exists()) {
//                filePath.mkdirs();
//            }
//            CompressImageUtil.compressImage(this, filePath.getPath());

            Bitmap bitmap = data.getParcelableExtra("data");
            File file = null;
            try {
                file = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(DataSetting_Activity.this) + "/xxhold/" + System.currentTimeMillis() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("上传头像22222", "onActivityResult: " + file.getPath());
            if (!file.exists()) {
                file.mkdirs();
            }
//            upLoadPic(file);
            if (type == 0){
                Glide.with(DataSetting_Activity.this).load(file.getPath()).into(mId_card_zheng_iv);
                mId_card_zheng_iv.setVisibility(View.VISIBLE);
                mIdcard_zheng = file.getPath();
            }else if (type == 1){
                Glide.with(DataSetting_Activity.this).load(file.getPath()).into(mId_card_fan_iv);
                mId_card_fan_iv.setVisibility(View.VISIBLE);
                mIdcard_fan = file.getPath();
            }
        }
        if (requestCode == 201 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            File file = null;
            try {
                file = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(DataSetting_Activity.this) + "/xxhold/" + System.currentTimeMillis() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("上传头像22222", "onActivityResult: " + file.getPath());
//            Log.e("上传头像22222", "onActivityResult8888: " + file);
            if (!file.exists()) {
                file.mkdirs();
            }
//            upLoadPic(file);
            if (type == 0){
                Glide.with(DataSetting_Activity.this).load(file.getPath()).into(mId_card_zheng_iv);
                mId_card_zheng_iv.setVisibility(View.VISIBLE);
                mIdcard_zheng = file.getPath();
            }else if (type == 1){
                Glide.with(DataSetting_Activity.this).load(file.getPath()).into(mId_card_fan_iv);
                mId_card_fan_iv.setVisibility(View.VISIBLE);
                mIdcard_fan = file.getPath();
            }
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

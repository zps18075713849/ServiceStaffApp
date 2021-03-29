package com.haitian.servicestaffapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.AddDataSetting_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.Register_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.CompressImageUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.utils.ToastUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.haitian.servicestaffapp.utils.HcUtils.getAppProcessName;

public class Reigster_UploadCertificate_Activity extends BaseActivity {

    private LinearLayout mCertificate_line;
    private ImageView mCertificate_img;
    private ImageView mBack_img;
    private Button mRight_btn;
    private static final int REQUEST_CODE_TAKE_PICTURE = 2001;
    private static final int REQUEST_CODE_TAKE_PICTURE1 = 2002;
    private String pathImage;

    private String mMobile;
    private String mPassword;
    private String mMes_code;
    private String mName;
    private String mFuwu_typeid;
    private String mFuwu_suoshuid;
    private String mYanzheng_code;
    private String mIdcard_zheng;
    private String mIdcard_fan;
    private String mIdcard_shouchi;
    private ArrayList<String> mNewPic;
    private RecyclerView mRecy_id;
    private AddDataSetting_Adapter mAdapter;
    private List<File> mZhengShuList = new ArrayList<>();
    MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_reigster__upload_certificate_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mCertificate_line = findViewById(R.id.certificate_line);
        mCertificate_img = findViewById(R.id.certificate_img);
        mBack_img = findViewById(R.id.back_img);

        mRight_btn = findViewById(R.id.right_btn);
        mRecy_id = findViewById(R.id.recy_id);


        try {
            mMobile = getIntent().getStringExtra("mobile");
            mPassword = getIntent().getStringExtra("password");
            mMes_code = getIntent().getStringExtra("mes_code");
            mName = getIntent().getStringExtra("name");
            mFuwu_typeid = getIntent().getStringExtra("fuwu_typeid");
            mFuwu_suoshuid = getIntent().getStringExtra("suoshuid");
            mYanzheng_code = getIntent().getStringExtra("yanzhengid");
            mIdcard_zheng = getIntent().getStringExtra("idcard_zheng");
            mIdcard_fan = getIntent().getStringExtra("idcard_fan");
            mIdcard_shouchi = getIntent().getStringExtra("idcard_shouchi");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        mNewPic = new ArrayList<>();
        mAdapter = new AddDataSetting_Adapter(Reigster_UploadCertificate_Activity.this, mNewPic);
        mRecy_id.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBack_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCertificate_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Reigster_UploadCertificate_Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {   //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在 onRequestPermissionsResult 中使用到
                    ActivityCompat.requestPermissions(Reigster_UploadCertificate_Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_TAKE_PICTURE1);
                } else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    if (4 - mAdapter.getItemCount() == 0) {
                        Toast.makeText(mContext, "最多上传4张图片", Toast.LENGTH_SHORT).show();
                    } else {
                        showSelect();
                    }
                }
            }
        });

        mRight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaitDialog();
                mZhengShuList.clear();

                if (mNewPic.size() == 0) {
                    Toast.makeText(mContext, "请上传您的执业证书", Toast.LENGTH_SHORT).show();
                    return;
                }
//                try {
//                    LogUtil.e("服务id：" + mFuwu_typeid);
//                    LogUtil.e("所属id：" + mFuwu_suoshuid);
//                    LogUtil.e("手机号：" + mMobile);
//                    LogUtil.e("密码：" + mPassword);
//                    LogUtil.e("验证码：" + mMes_code);
//                    LogUtil.e("名字：" + mName);
//                    LogUtil.e("验证码id：" + mYanzheng_code);
//                    LogUtil.e("身份证正面：" + mIdcard_zheng);
//                    LogUtil.e("身份证反面：" + mIdcard_fan);
//                    LogUtil.e("手持身份证：" + mIdcard_shouchi);
//
//                    for (int i = 0; i < mNewPic.size(); i++) {
//                        LogUtil.e("第" + i + "张职业证书：" + mNewPic.get(i));
//                        mZhengShuList.add(new File(mNewPic.get(i)));
//                    }
//
//                    OkHttpClient client = new OkHttpClient();
//                    // form 表单形式上传
//                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
//                    RequestBody body1 = RequestBody.create(MEDIA_TYPE_PNG, new File(mIdcard_zheng));
//                    RequestBody body2 = RequestBody.create(MEDIA_TYPE_PNG, new File(mIdcard_fan));
//                    RequestBody body3 = RequestBody.create(MEDIA_TYPE_PNG, new File(mIdcard_shouchi));
//
////                    for (int i = 0; i < mZhengShuList.size(); i++) {
////                        requestBody.addFormDataPart("zhiyezhengshuList", mZhengShuList.get(i).getName(), RequestBody.create(MEDIA_TYPE_PNG, mZhengShuList.get(i)));
////                    }
//                    requestBody.addFormDataPart("shenfenzhenglist", new File(mIdcard_zheng).getName(), body1);
////                            .addFormDataPart("shenfenzhenglist", new File(mIdcard_fan).getName(), body2)
////                            .addFormDataPart("shouchi", new File(mIdcard_shouchi).getName(), body3);
////                            .addFormDataPart("user_card_name", mName)
////                            .addFormDataPart("user_name", mMobile)
////                            .addFormDataPart("user_pwd", mPassword)
////                            .addFormDataPart("user_type", mFuwu_typeid)
////                            .addFormDataPart("user_department", mFuwu_suoshuid)
////                            .addFormDataPart("messageId", mYanzheng_code)
////                            .addFormDataPart("messgae", mMes_code);
//
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("user_card_name", mName);
//                    map.put("user_name", mMobile);
//                    map.put("user_pwd", mPassword);
//                    map.put("user_type", mFuwu_typeid);
//                    map.put("user_department", mFuwu_suoshuid);
//                    map.put("messageId", mYanzheng_code);
//                    map.put("messgae", mMes_code);
//
////                    FormBody.Builder builder = new FormBody.Builder();
////                    for (String key : map.keySet()) {
////                        //追加表单信息
////                        builder.add(key, map.get(key));
////
////                    }
//
//                    for (String key : map.keySet()) {
//                        requestBody.addPart(
//                                Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
//                                RequestBody.create(null, map.get(key)));
//                    }
//
//
////                    RequestBody formBody = builder.build();
//
//                    Request request = new Request.Builder().url(Constants.REGISTER).post(requestBody.build()).build();
//
//                    Call call = client.newCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            hideWaitDialog();
//                            //请求失败的处理
//                            Log.i("lfq", "onFailure");
//
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            hideWaitDialog();
//                            if (response.isSuccessful()) {
//                                final String str = response.body().string();
//
//                                Log.i("lfq", response.message() + " , body " + str);
//                                Gson gson = new Gson();
//                                final Register_Bean bean = gson.fromJson(str, Register_Bean.class);
//
//                                Handler handler = new Handler(Looper.getMainLooper());
//                                handler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        if (bean.getCode() == 20011){
//                                            Toast.makeText(Reigster_UploadCertificate_Activity.this, "注册成功！", Toast.LENGTH_SHORT).show();
//                                            Intent intent = new Intent(Reigster_UploadCertificate_Activity.this, Login_Activity.class);
//                                            startActivity(intent);
//                                            finish();
//                                        }else {
//                                            Toast.makeText(Reigster_UploadCertificate_Activity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
//                                            return;
//                                        }
//                                    }
//                                });
//
//                            }
//
//                        }
//                    });
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


                File file_zheng = new File(mIdcard_zheng);
                File file_fan = new File(mIdcard_fan);
                File file_shouchi = new File(mIdcard_shouchi);

                MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
                RequestBody filebody_zheng = MultipartBody.create(MEDIA_TYPE_PNG, file_zheng);
                RequestBody filebody_fan = MultipartBody.create(MEDIA_TYPE_PNG, file_fan);
                RequestBody filebody_shouchi = MultipartBody.create(MEDIA_TYPE_PNG, file_shouchi);
                MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
                //参数以添加header方式将参数封装，否则上传参数为空
                // 设置请求体
                multiBuilder.setType(MultipartBody.FORM);
                //这里是 封装上传图片参数
                multiBuilder.addFormDataPart("shenfenzhenglist", file_zheng.getName(), filebody_zheng);
                multiBuilder.addFormDataPart("shenfenzhenglist", file_fan.getName(), filebody_fan);
                multiBuilder.addFormDataPart("shouchi", file_fan.getName(), filebody_shouchi);
                for (int i = 0; i < mZhengShuList.size(); i++) {
                    multiBuilder.addFormDataPart("zhiyezhengshuList", mZhengShuList.get(i).getName(), RequestBody.create(MEDIA_TYPE_PNG, mZhengShuList.get(i)));
                }


                // 封装请求参数,这里最重要
                HashMap<String, String> params = new HashMap<>();
                params.put("user_card_name", mName);
                params.put("user_name", mMobile);
                params.put("user_pwd", mPassword);
                params.put("user_type", mFuwu_typeid);
                params.put("user_department", mFuwu_suoshuid);
                params.put("messageId", mYanzheng_code);
                params.put("messgae", mMes_code);

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
                Request request = new Request.Builder().url(Constants.REGISTER).post(multiBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        hideWaitDialog();
                        LogUtil.e("注册失败：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        hideWaitDialog();
                        LogUtil.e("注册成功");
                        if (response.isSuccessful()) {
                            final String str = response.body().string();
                            Gson gson = new Gson();
                            final Register_Bean bean = gson.fromJson(str, Register_Bean.class);

                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (bean.getCode() == 20011) {
                                        Toast.makeText(Reigster_UploadCertificate_Activity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Reigster_UploadCertificate_Activity.this, Login_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Reigster_UploadCertificate_Activity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });
                        }
                    }
                });

            }
        });
    }


    private void showSelect() {
        ActionSheet.createBuilder(Reigster_UploadCertificate_Activity.this, getSupportFragmentManager())
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
            int request = ContextCompat.checkSelfPermission(Reigster_UploadCertificate_Activity.this, Manifest.permission.CAMERA);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(Reigster_UploadCertificate_Activity.this, new String[]{Manifest.permission.CAMERA}, 123);
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

    private void startPhotoAlbum() {
        //相册导入
        Intent intent5 = new Intent(this, MultiImageSelectorActivity.class);
        // whether show camera
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
        // max select image amount
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 4 - mAdapter.getItemCount());
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
                mNewPic.add(b.getPath());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(Reigster_UploadCertificate_Activity.this, 4);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                mRecy_id.setLayoutManager(gridLayoutManager);
                mAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == Reigster_UploadCertificate_Activity.this.RESULT_OK) {
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


        if (requestCode == 200 && resultCode == Reigster_UploadCertificate_Activity.this.RESULT_OK) {
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

            mNewPic.add(file1.getPath());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(Reigster_UploadCertificate_Activity.this, 4);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            mRecy_id.setLayoutManager(gridLayoutManager);
            mAdapter.notifyDataSetChanged();
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

    @Override
    public Context context() {
        return null;
    }
}

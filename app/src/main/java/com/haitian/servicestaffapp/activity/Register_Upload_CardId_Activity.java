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
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.utils.CompressImageUtil;
import com.haitian.servicestaffapp.utils.LogUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static com.haitian.servicestaffapp.utils.HcUtils.getAppProcessName;

public class Register_Upload_CardId_Activity extends BaseActivity {

    private LinearLayout mId_card_fan_ll;
    private LinearLayout mId_shouchi;
    private LinearLayout mId_card_zheng_ll;
    private ImageView mId_card_zheng_iv;
    private ImageView mId_card_fan_iv;
    private ImageView mShouchi_img;
    private ImageView mBack_img;
    private Button mNext_btn;
    private static final int REQUEST_CODE_TAKE_PICTURE = 2001;
    private static final int REQUEST_CODE_TAKE_PICTURE1 = 2002;
    private String pathImage;
    private String personlIcon;
    private int type;   //0 身份证正面   1  反面  2  手持
    private String mMobile;
    private String mPassword;
    private String mMes_code;
    private String mName;
    private String mFuwu_typeid;
    private String mFuwu_suoshuid;
    private String mYanzheng_code;
    private String mIdCard_Zheng = "";
    private String mIdCard_Fan = "";
    private String mIdCard_ShouChi = "";

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register__upload__card_id_;
    }

    @Override
    protected void initViews() {
        super.initViews();

        //退出
        mBack_img = findViewById(R.id.back_img);
        mNext_btn = findViewById(R.id.next_btn);

        //身份证正面
        mId_card_zheng_ll = findViewById(R.id.id_card_zheng_ll);
        //身份证反面
        mId_card_fan_ll = findViewById(R.id.id_card_fan_ll);
        //手持
        mId_shouchi = findViewById(R.id.id_shouchi);

        //正面照片展示
        mId_card_zheng_iv = findViewById(R.id.id_card_zheng_iv);
        //反面照片展示
        mId_card_fan_iv = findViewById(R.id.id_card_fan_iv);
        //手持照片
        mShouchi_img = findViewById(R.id.shouchi_img);

        try {
            mMobile = getIntent().getStringExtra("mobile");
            mPassword = getIntent().getStringExtra("password");
            mMes_code = getIntent().getStringExtra("mes_code");
            mName = getIntent().getStringExtra("name");
            mFuwu_typeid = getIntent().getStringExtra("fuwu_typeid");
            mFuwu_suoshuid = getIntent().getStringExtra("suoshuid");
            mYanzheng_code = getIntent().getStringExtra("yanzhengid");

            LogUtil.e("服务id："+mFuwu_typeid);
            LogUtil.e("所属id："+mFuwu_suoshuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        mId_card_zheng_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 0;

                if (ContextCompat.checkSelfPermission(Register_Upload_CardId_Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {   //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在 onRequestPermissionsResult 中使用到
                    ActivityCompat.requestPermissions(Register_Upload_CardId_Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_TAKE_PICTURE1);
                } else { //权限已经被授予，在这里直接写要执行的相应方法即可
                        showSelect();
                }

//                if (ContextCompat.checkSelfPermission(Register_Upload_CardId_Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    showSelect();
//                } else {
//                    //否则去请求相机权限
//                    ToastUtils.showMessage(Register_Upload_CardId_Activity.this, "请打开相机权限");
//                    ActivityCompat.requestPermissions(Register_Upload_CardId_Activity.this, new String[]{Manifest.permission.CAMERA}, 100);
//                }
            }
        });

        mId_card_fan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;


                if (ContextCompat.checkSelfPermission(Register_Upload_CardId_Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {   //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在 onRequestPermissionsResult 中使用到
                    ActivityCompat.requestPermissions(Register_Upload_CardId_Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_TAKE_PICTURE1);
                } else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    showSelect();
                }


//                if (ContextCompat.checkSelfPermission(Register_Upload_CardId_Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    showSelect();
//                } else {
//                    //否则去请求相机权限
//                    ToastUtils.showMessage(Register_Upload_CardId_Activity.this, "请打开相机权限");
//                    ActivityCompat.requestPermissions(Register_Upload_CardId_Activity.this, new String[]{Manifest.permission.CAMERA}, 100);
//                }
            }
        });

        mId_shouchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;


                if (ContextCompat.checkSelfPermission(Register_Upload_CardId_Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {   //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在 onRequestPermissionsResult 中使用到
                    ActivityCompat.requestPermissions(Register_Upload_CardId_Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_TAKE_PICTURE1);
                } else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    showSelect();
                }


//                if (ContextCompat.checkSelfPermission(Register_Upload_CardId_Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    showSelect();
//                } else {
//                    //否则去请求相机权限
//                    ToastUtils.showMessage(Register_Upload_CardId_Activity.this, "请打开相机权限");
//                    ActivityCompat.requestPermissions(Register_Upload_CardId_Activity.this, new String[]{Manifest.permission.CAMERA}, 100);
//                }
            }
        });

        //下一步
        mNext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIdCard_Zheng.equals("")){
                    Toast.makeText(mContext, "请上传您的身份证正面照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mIdCard_Fan.equals("")){
                    Toast.makeText(mContext, "请上传您的身份证反面照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mIdCard_ShouChi.equals("")){
                    Toast.makeText(mContext, "请上传您的手持身份证照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Register_Upload_CardId_Activity.this, Reigster_UploadCertificate_Activity.class);
                intent.putExtra("mobile",mMobile);
                intent.putExtra("password",mPassword);
                intent.putExtra("mes_code",mMes_code);
                intent.putExtra("name",mName);
                intent.putExtra("fuwu_typeid",mFuwu_typeid);
                intent.putExtra("suoshuid",mFuwu_suoshuid);
                intent.putExtra("yanzhengid",mYanzheng_code);
                intent.putExtra("idcard_zheng",mIdCard_Zheng);
                intent.putExtra("idcard_fan",mIdCard_Fan);
                intent.putExtra("idcard_shouchi",mIdCard_ShouChi);
                startActivity(intent);
            }
        });

    }

    private void showSelect() {
        ActionSheet.createBuilder(Register_Upload_CardId_Activity.this, getSupportFragmentManager())
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
            int request = ContextCompat.checkSelfPermission(Register_Upload_CardId_Activity.this, Manifest.permission.CAMERA);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(Register_Upload_CardId_Activity.this, new String[]{Manifest.permission.CAMERA}, 123);
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

                if (type == 0){
                    Glide.with(Register_Upload_CardId_Activity.this).load(b.getPath()).into(mId_card_zheng_iv);
                    mId_card_zheng_iv.setVisibility(View.VISIBLE);
                    mIdCard_Zheng = b.getPath();
                }else if (type == 1){
                    Glide.with(Register_Upload_CardId_Activity.this).load(b.getPath()).into(mId_card_fan_iv);
                    mId_card_fan_iv.setVisibility(View.VISIBLE);
                    mIdCard_Fan = b.getPath();
                }else if (type == 2){
                    Glide.with(Register_Upload_CardId_Activity.this).load(b.getPath()).into(mShouchi_img);
                    mShouchi_img.setVisibility(View.VISIBLE);
                    mIdCard_ShouChi = b.getPath();
                }

            }
        }

        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == Register_Upload_CardId_Activity.this.RESULT_OK) {
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


        if (requestCode == 200 && resultCode == Register_Upload_CardId_Activity.this.RESULT_OK) {
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
            if (type == 0){
                Glide.with(Register_Upload_CardId_Activity.this).load(file1.getPath()).into(mId_card_zheng_iv);
                mId_card_zheng_iv.setVisibility(View.VISIBLE);
                mIdCard_Zheng = file1.getPath();
            }else if (type == 1){
                Glide.with(Register_Upload_CardId_Activity.this).load(file1.getPath()).into(mId_card_fan_iv);
                mId_card_fan_iv.setVisibility(View.VISIBLE);
                mIdCard_Fan = file1.getPath();
            }else if (type == 2){
                Glide.with(Register_Upload_CardId_Activity.this).load(file1.getPath()).into(mShouchi_img);
                mShouchi_img.setVisibility(View.VISIBLE);
                mIdCard_ShouChi = file1.getPath();
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

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //2.判断请求码和结果码  结果码系统提供 RESULT_OK
//        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
//            //点击完成后调取的裁剪功能  com.android.camera.action.CROP裁剪的Action
//            Intent it = new Intent("com.android.camera.action.CROP");
//            //得到图片   1.得到Sdcard的下存的图片，如果有就拿到，如果没有就创建
//            it.setDataAndType(Uri.fromFile(new File(pathImage)), "image/*");
//            //设置是否支持裁剪
//            it.putExtra("CROP", true);
//            //设置裁剪框的比例
//            it.putExtra("aspaceX", 1);
//            it.putExtra("aspaceY", 1);
//            //设置图片输入的大小
//            it.putExtra("outputX", 250);
//            it.putExtra("outputY", 250);
//            //让图片返回intent接受
//            it.putExtra("return-data", true);
//            //启动
//            startActivityForResult(it, 200);
//        } //3.将图片设置展示
//
//        if (requestCode == REQUEST_CODE_TAKE_PICTURE1 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
//            //得到图片
//            Uri data2 = data.getData();
//            //调取裁剪功能
//            Intent it = new Intent("com.android.camera.action.CROP");
//            //得到图片设置格式
//            it.setDataAndType(data2, "image/*");
//            //设置是否支持裁剪
//            it.putExtra("CROP", true);
//            //设置宽高比
//            it.putExtra("aspectX", 10);
//            it.putExtra("aspectY", 10);
//            //设置输出图片的大小
//            it.putExtra("outputX", 250);
//            it.putExtra("outputY", 250);
//            //返回
//            it.putExtra("return-data", true);
//            startActivityForResult(it, 201);
//        }
//        if (requestCode == 200 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
////            Uri uri = data.getData();
////            String path = uri.getPath();
////            Log.e("上传头像11111", "onActivityResult: " + path);
////            File filePath = new File(path);
////            if (!filePath.exists()) {
////                filePath.mkdirs();
////            }
////            CompressImageUtil.compressImage(this, filePath.getPath());
//
//            Bitmap bitmap = data.getParcelableExtra("data");
//            File file = null;
//            try {
//                file = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(Register_Upload_CardId_Activity.this) + "/xxhold/" + System.currentTimeMillis() + ".png");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Log.e("上传头像111111", "onActivityResult: " + file.getPath());
//            if (!file.exists()) {
//                file.mkdirs();
//            }
////            upLoadPic(file);
//            if (type == 0){
//                Glide.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mId_card_zheng_iv);
//                mId_card_zheng_iv.setVisibility(View.VISIBLE);
//                mIdCard_Zheng = file.getPath();
//            }else if (type == 1){
//                Glide.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mId_card_fan_iv);
//                mId_card_fan_iv.setVisibility(View.VISIBLE);
//                mIdCard_Fan = file.getPath();
//            }else if (type == 2){
//                Glide.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mShouchi_img);
//                mShouchi_img.setVisibility(View.VISIBLE);
//                mIdCard_ShouChi = file.getPath();
//            }
//        }
//        if (requestCode == 201 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
//            Bitmap bitmap = data.getParcelableExtra("data");
//            File file = null;
//            try {
//                file = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(Register_Upload_CardId_Activity.this) + "/xxhold/" + System.currentTimeMillis() + ".png");
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Log.e("上传头像22222", "onActivityResult: " + file.getPath());
//            if (!file.exists()) {
//                file.mkdirs();
//            }
////            upLoadPic(file);
//            if (type == 0){
//                Glide.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mId_card_zheng_iv);
////                Picasso.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mId_card_zheng_iv);
//                mId_card_zheng_iv.setVisibility(View.VISIBLE);
//                mIdCard_Zheng = file.getPath();
//
//            }else if (type == 1){
//                Glide.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mId_card_fan_iv);
////                Picasso.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mId_card_fan_iv);
//                mId_card_fan_iv.setVisibility(View.VISIBLE);
//                mIdCard_Fan = file.getPath();
//
//            }else if (type == 2){
////                Picasso.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mShouchi_img);
//
//                Glide.with(Register_Upload_CardId_Activity.this).load(file.getPath()).into(mShouchi_img);
//                mShouchi_img.setVisibility(View.VISIBLE);
//                mIdCard_ShouChi = file.getPath();
//
//            }
//        }
//    }

    @Override
    public Context context() {
        return null;
    }
}

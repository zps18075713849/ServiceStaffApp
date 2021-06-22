package com.haitian.servicestaffapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.activity.AboutAs_Activity;
import com.haitian.servicestaffapp.activity.DataSetting_Activity;
import com.haitian.servicestaffapp.activity.MainActivity;
import com.haitian.servicestaffapp.activity.MineSetting_Activity;
import com.haitian.servicestaffapp.activity.Register_Upload_CardId_Activity;
import com.haitian.servicestaffapp.activity.Reigster_UploadCertificate_Activity;
import com.haitian.servicestaffapp.activity.UpdatePassWord_Activity;
import com.haitian.servicestaffapp.activity.UpdateZiTiSize_Activity;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.UpdateVersion_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.utils.ToastUtils;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.haitian.servicestaffapp.utils.HcUtils.getAppProcessName;

public class Setting_Fragment extends BaseFragment {

    private ImageView mPhoto_img;
    private TextView mIdcard_tv;
    private RelativeLayout mMinedatasetting_line;
    private RelativeLayout mUpdate_password_line;
    private RelativeLayout mUpdateSize_line;
    private RelativeLayout mMine_setting_line;
    private RelativeLayout mAboutAs_line;
    private RelativeLayout mBanben_line;
    private PopupWindow mPopupWindow;
    private RelativeLayout mRelative_id;

    private static final int REQUEST_CODE_TAKE_PICTURE = 2001;
    private static final int REQUEST_CODE_TAKE_PICTURE1 = 2002;
    private String pathImage;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.setting_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mPhoto_img = view.findViewById(R.id.photo_img);
        mIdcard_tv = view.findViewById(R.id.idcard_tv);
        //个人资料
        mMinedatasetting_line = view.findViewById(R.id.minedatasetting_line);
        //修改密码
        mUpdate_password_line = view.findViewById(R.id.update_password_line);
        //修改字体大小
        mUpdateSize_line = view.findViewById(R.id.updateSize_line);
        //我的设置
        mMine_setting_line = view.findViewById(R.id.mine_Setting_line);
        //联系我们
        mAboutAs_line = view.findViewById(R.id.aboutAs_line);
        //版本更新
        mBanben_line = view.findViewById(R.id.banben_line);
        //主布局
        mRelative_id = view.findViewById(R.id.relative_id);

        String nickname = DoctorBaseAppliction.spUtil.getString(Constants.USER_CARD_NAME, "");
        mIdcard_tv.setText(nickname+"");
    }

    @Override
    protected void initListener() {
        super.initListener();
        //个人资料
        mMinedatasetting_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataSetting_Activity.class);
                startActivity(intent);
            }
        });

        //修改字体大小
        mUpdateSize_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateZiTiSize_Activity.class);
                startActivity(intent);
            }
        });

        //修改密码
        mUpdate_password_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdatePassWord_Activity.class);
                startActivity(intent);
            }
        });

        //联系我们
        mAboutAs_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutAs_Activity.class);
                startActivity(intent);
            }
        });

        //我的设置
        mMine_setting_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MineSetting_Activity.class);
                startActivity(intent);
            }
        });

        //检查更新
        mBanben_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.gengxin_popup, null);
//                mPopupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//                mPopupWindow.setBackgroundDrawable(null);
//                mPopupWindow.setOutsideTouchable(true);
//
//                //主界面linealayout的id，并给自己定义的popwindow xml文件backgrount    android:background="#33000000"
//                mPopupWindow.showAtLocation(mRelative_id, Gravity.CENTER, 0, 0);
//
//                inflate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mPopupWindow.dismiss();
//                    }
//                });
//
//                //自己定义的popwondow布局里面的按钮id
//                Button bt = inflate.findViewById(R.id.gengxin_btn);
//                //自己定义的popwondow里面的按钮  点击隐藏popwondow
//                bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mPopupWindow.dismiss();
//                    }
//                });

                //请求版本更新接口
                requestUpdateVersion();

            }
        });

        //修改头像
        mPhoto_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    showSelect();
//                } else {
//                    //否则去请求相机权限
//                    ToastUtils.showMessage(getActivity(), "请打开相机权限");
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
//                }
            }
        });
    }

    private void requestUpdateVersion() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("type","waiter");
        map.put("version","1.0");

        OkHttpUtil.getInteace().doPost(Constants.UPDATEVERSION, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("版本更新接口失败："+e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("版本更新接口成功："+json);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            UpdateVersion_Bean bean = gson.fromJson(json, UpdateVersion_Bean.class);
                            if (bean.getCode() == 20041){
                                Toast.makeText(getContext(), bean.getMessage()+"", Toast.LENGTH_SHORT).show();
                                return;
                            }else if (bean.getCode() == 20040){
                                Toast.makeText(getContext(), "请下载安装新版本", Toast.LENGTH_SHORT).show();
                                String url = bean.getData().getUrl();
                                if (url!=null){
                                    if (!url.equals("")){
                                        boolean apk = url.contains("apk");
                                        if (apk){
                                            Uri uri = Uri.parse(url);
                                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(getContext(), "文件类型异常", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
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


    private void showSelect() {
        ActionSheet.createBuilder(getActivity(),getFragmentManager())
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
            int request = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 123);
                return;//
            } else {
                String b = null;
                try {
                    b = Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(getContext()) + "/xxhold/";
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
                    pathImage = Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(getContext()) + "/xxhold/" + System.currentTimeMillis() + ".png";
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
                file = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(getContext()) + "/xxhold/" + System.currentTimeMillis() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("上传头像22222", "onActivityResult: " + file.getPath());
            if (!file.exists()) {
                file.mkdirs();
            }
//            upLoadPic(file);
            RequestOptions options = RequestOptions.circleCropTransform()
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .skipMemoryCache(true);
            Glide.with(getContext()).load(file.getPath()).apply(options).into(mPhoto_img);

            mPhoto_img.setVisibility(View.VISIBLE);
        }
        if (requestCode == 201 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            File file = null;
            try {
                file = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(getContext()) + "/xxhold/" + System.currentTimeMillis() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("上传头像22222", "onActivityResult: " + file.getPath());
//            Log.e("上传头像22222", "onActivityResult8888: " + file);
            if (!file.exists()) {
                file.mkdirs();
            }
//            upLoadPic(file);

            RequestOptions options = RequestOptions.circleCropTransform()
                            .placeholder(R.mipmap.ic_launcher)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .skipMemoryCache(true);
                    Glide.with(getContext()).load(file.getPath()).apply(options).into(mPhoto_img);

            mPhoto_img.setVisibility(View.VISIBLE);
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


}

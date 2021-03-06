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
        //????????????
        mMinedatasetting_line = view.findViewById(R.id.minedatasetting_line);
        //????????????
        mUpdate_password_line = view.findViewById(R.id.update_password_line);
        //??????????????????
        mUpdateSize_line = view.findViewById(R.id.updateSize_line);
        //????????????
        mMine_setting_line = view.findViewById(R.id.mine_Setting_line);
        //????????????
        mAboutAs_line = view.findViewById(R.id.aboutAs_line);
        //????????????
        mBanben_line = view.findViewById(R.id.banben_line);
        //?????????
        mRelative_id = view.findViewById(R.id.relative_id);

        String nickname = DoctorBaseAppliction.spUtil.getString(Constants.USER_CARD_NAME, "");
        mIdcard_tv.setText(nickname+"");
    }

    @Override
    protected void initListener() {
        super.initListener();
        //????????????
        mMinedatasetting_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataSetting_Activity.class);
                startActivity(intent);
            }
        });

        //??????????????????
        mUpdateSize_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateZiTiSize_Activity.class);
                startActivity(intent);
            }
        });

        //????????????
        mUpdate_password_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdatePassWord_Activity.class);
                startActivity(intent);
            }
        });

        //????????????
        mAboutAs_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutAs_Activity.class);
                startActivity(intent);
            }
        });

        //????????????
        mMine_setting_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MineSetting_Activity.class);
                startActivity(intent);
            }
        });

        //????????????
        mBanben_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.gengxin_popup, null);
//                mPopupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//                mPopupWindow.setBackgroundDrawable(null);
//                mPopupWindow.setOutsideTouchable(true);
//
//                //?????????linealayout???id????????????????????????popwindow xml??????backgrount    android:background="#33000000"
//                mPopupWindow.showAtLocation(mRelative_id, Gravity.CENTER, 0, 0);
//
//                inflate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mPopupWindow.dismiss();
//                    }
//                });
//
//                //???????????????popwondow?????????????????????id
//                Button bt = inflate.findViewById(R.id.gengxin_btn);
//                //???????????????popwondow???????????????  ????????????popwondow
//                bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mPopupWindow.dismiss();
//                    }
//                });

                //????????????????????????
                requestUpdateVersion();

            }
        });

        //????????????
        mPhoto_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    showSelect();
//                } else {
//                    //???????????????????????????
//                    ToastUtils.showMessage(getActivity(), "?????????????????????");
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
                LogUtil.e("???????????????????????????"+e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("???????????????????????????"+json);
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
                                Toast.makeText(getContext(), "????????????????????????", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(getContext(), "??????????????????", Toast.LENGTH_SHORT).show();
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
                .setCancelButtonTitle("??????")
                .setOtherButtonTitles("????????????", "????????????")
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
            if (request != PackageManager.PERMISSION_GRANTED)//?????????????????????????????????
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
                //??????????????????????????????,????????????????????????
                // Toast.makeText(this,"????????????",Toast.LENGTH_SHORT).show();
                //1.????????????  MediaStore.ACTION_IMAGE_CAPTURE ?????????????????????
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //???????????????sdcard  1.??????   2.???Sdcard????????????
                it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    pathImage = Environment.getExternalStorageDirectory().getCanonicalPath() + "/Android/data" + "/" + getAppProcessName(getContext()) + "/xxhold/" + System.currentTimeMillis() + ".png";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pathImage)));
                //??????
                startActivityForResult(it, REQUEST_CODE_TAKE_PICTURE);
            }
        } else {
            //??????23 ????????????????????????????????????????????????
        }
    }

    private void startPhotoAlbum() {
        //????????????
        //Intent.ACTION_PICK????????????
        Intent it = new Intent(Intent.ACTION_PICK);
        //?????????????????????
        it.setType("image/*");
        //??????
        startActivityForResult(it, REQUEST_CODE_TAKE_PICTURE1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //2.???????????????????????????  ????????????????????? RESULT_OK
        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
            //????????????????????????????????????  com.android.camera.action.CROP?????????Action
            Intent it = new Intent("com.android.camera.action.CROP");
            //????????????   1.??????Sdcard???????????????????????????????????????????????????????????????
            it.setDataAndType(Uri.fromFile(new File(pathImage)), "image/*");
            //????????????????????????
            it.putExtra("CROP", true);
            //????????????????????????
            it.putExtra("aspaceX", 1);
            it.putExtra("aspaceY", 1);
            //???????????????????????????
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //???????????????intent??????
            it.putExtra("return-data", true);
            //??????
            startActivityForResult(it, 200);
        } //3.?????????????????????

        if (requestCode == REQUEST_CODE_TAKE_PICTURE1 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
            //????????????
            Uri data2 = data.getData();
            //??????????????????
            Intent it = new Intent("com.android.camera.action.CROP");
            //????????????????????????
            it.setDataAndType(data2, "image/*");
            //????????????????????????
            it.putExtra("CROP", true);
            //???????????????
            it.putExtra("aspectX", 10);
            it.putExtra("aspectY", 10);
            //???????????????????????????
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //??????
            it.putExtra("return-data", true);
            startActivityForResult(it, 201);
        }
        if (requestCode == 200 && resultCode == Register_Upload_CardId_Activity.RESULT_OK) {
//            Uri uri = data.getData();
//            String path = uri.getPath();
//            Log.e("????????????11111", "onActivityResult: " + path);
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
            Log.e("????????????22222", "onActivityResult: " + file.getPath());
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
            Log.e("????????????22222", "onActivityResult: " + file.getPath());
//            Log.e("????????????22222", "onActivityResult8888: " + file);
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
        File file = new File(filepath);//???????????????????????????
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

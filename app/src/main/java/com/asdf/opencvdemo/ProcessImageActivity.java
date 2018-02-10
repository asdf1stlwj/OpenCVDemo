package com.asdf.opencvdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.asdf.list.CommandConstants;
import com.asdf.utils.OpenCVJniHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProcessImageActivity extends Activity implements View.OnClickListener {

    private String TAG="CVSAMPLE";
    private final int REQUEST_GET_IMAGE=1;
    private final int MAX_SIZE=1024;
    Button btn_process,btn_sel;
    ImageView iv_test;
    Bitmap selectedBitmap;
    String commond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_image);
        commond=getIntent().getStringExtra("command");
        btn_process = (Button) findViewById(R.id.btn_process);
        btn_sel= (Button) findViewById(R.id.btn_sel);
        btn_process.setTag("PROCESS");
        btn_sel.setTag("SELECT");
        iv_test= (ImageView) findViewById(R.id.iv_test);
        btn_process.setOnClickListener(this);
        btn_sel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Object obj=view.getTag();
        if (obj instanceof String){
            if (obj.equals("SELECT")){
                selectImage();
            }else if (obj.equals("PROCESS")){
                if (selectedBitmap==null){
                    Toast.makeText(this,"请选择一张图片",Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap temp=selectedBitmap.copy(selectedBitmap.getConfig(),true);
                if (commond.equals(CommandConstants.TEST_ENV_COMMAND)){
                    temp= bitmapToGray(temp);
                }else if (commond.equals(CommandConstants.PIXEL_INVERT_COMMAND)){

                }
                if (temp!=null){
                    iv_test.setImageBitmap(temp);
                }else {
                    Toast.makeText(this,"图片转换出错",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void selectImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Brower Image..."),REQUEST_GET_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_GET_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                BitmapFactory.decodeStream(inputStream,null,options);
                int width=options.outWidth;
                int height=options.outHeight;
                int samepleSize=1;
                int max=Math.max(width,height);
                if (max>MAX_SIZE){
                    int nwidth=width/2;
                    int nheight=height/2;
                    while (nwidth/samepleSize > MAX_SIZE || nheight/samepleSize>MAX_SIZE){
                        samepleSize*=2;
                    }
                }
                options.inSampleSize=samepleSize;
                options.inJustDecodeBounds=false;
                selectedBitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null,options);
                iv_test.setImageBitmap(selectedBitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage() );
            }
        }else {
            Toast.makeText(this,"获取图片失败,请重新选择",Toast.LENGTH_SHORT).show();
        }
    }

    public Bitmap bitmapToGray(Bitmap bitmap){
        if (bitmap==null){
            return null;
        }
        int w=bitmap.getWidth();
        int h=bitmap.getHeight();
        int[] pix=new int[w*h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        //灰度算法
        int[] resultPixes = OpenCVJniHelper.jni_toGray(pix, w, h);
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        result.setPixels(resultPixes, 0, w, 0, 0, w, h);
        return result;
    }
}

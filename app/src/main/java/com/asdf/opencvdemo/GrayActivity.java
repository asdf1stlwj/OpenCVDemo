package com.asdf.opencvdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GrayActivity extends Activity {
    Button btn_togray;
    ImageView iv_test;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("opencv_java");
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gray);
        btn_togray= (Button) findViewById(R.id.btn_togray);
        iv_test= (ImageView) findViewById(R.id.iv_test);
        btn_togray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap=((BitmapDrawable)iv_test.getDrawable()).getBitmap();
                int w=bitmap.getWidth();
                int h=bitmap.getHeight();
                int[] pix=new int[w*h];
                bitmap.getPixels(pix, 0, w, 0, 0, w, h);
                //灰度算法
                int[] resultPixes = OpenCVHelper.toGray(pix, w, h);

                Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                result.setPixels(resultPixes, 0, w, 0, 0, w, h);
                iv_test.setImageBitmap(result);
            }
        });
//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }

//    /**
//     * A native method that is implemented by the 'native-lib' native library,
//     * which is packaged with this application.
//     */
//    public native String stringFromJNI();
}

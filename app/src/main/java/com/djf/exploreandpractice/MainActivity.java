package com.djf.exploreandpractice;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private TextView sample_text;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // Example of a call to a native method
        sample_text.setText(stringFromJNI());


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // 创建一张空白图片
        Bitmap baseBitmap = Bitmap.createBitmap(iv.getWidth(), iv.getHeight(), Bitmap.Config.ARGB_8888);
        iv.setImageBitmap(baseBitmap);

        // 创建一张画布
        Canvas canvas = new Canvas(baseBitmap);
        // 画布背景为灰色
        canvas.drawColor(Color.WHITE);
        // 创建画笔
        Paint paint = new Paint();
        // 画笔颜色为红色
        paint.setColor(Color.YELLOW);
        // 宽度5个像素
        paint.setStrokeWidth(5);
        // 先将灰色背景画上
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);
       
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private void initView() {
        sample_text = (TextView) findViewById(R.id.sample_text);
        iv = (ImageView) findViewById(R.id.iv);
    }
}

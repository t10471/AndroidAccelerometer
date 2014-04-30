package com.example.accelerometer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

@SuppressLint("DrawAllocation")
public class MyView extends View implements SensorEventListener {
    private float[] ac = new float[3];
    private String s;
    private float max;
    private String mText;
    private Paint mPaint;
    private int mTextWidth;
    private int mTextHeight;
    
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Activity activity = (Activity)this.getContext();
        WindowManager wm = (WindowManager)activity.getSystemService(Activity.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();        
        mText = "";
        mPaint = new Paint();
        mPaint.setTextSize(30);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        
        mTextWidth = disp.getWidth();
        mTextHeight = 12;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("センサー名       "+s, 0, 100, mPaint);
        canvas.drawText("最大値            "+String.valueOf(max), 0, 200, mPaint);
        canvas.drawText("x方向 加速度 "+String.valueOf(ac[0]), 0, 300, mPaint);
        canvas.drawText("y方向 加速度 "+String.valueOf(ac[1]), 0, 400, mPaint);
        canvas.drawText("z方向 加速度 "+String.valueOf(ac[2]), 0, 500, mPaint);
    }
    
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    
    @Override
    public void onSensorChanged(SensorEvent event) {
        ac[0] = event.values[0];
        ac[1] = event.values[1];
        ac[2] = event.values[2];
        s = event.sensor.getName();
        max = event.sensor.getMaximumRange();
        this.invalidate();
    }
    
}

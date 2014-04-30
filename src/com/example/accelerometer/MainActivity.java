package com.example.accelerometer;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
    
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private MyView myView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager mActiviyManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        
        // 現在稼働中のプロセスをLISTで取得
        List<RunningAppProcessInfo> processList = mActiviyManager.getRunningAppProcesses();
        for (RunningAppProcessInfo process : processList) {
            Log.i("aaaa", "pid:" + process.pid);
            Log.i("aaaa", "processName:" + process.processName);
        }
        setContentView(R.layout.activity_main);
        
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(myView, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        myView = (MyView)this.findViewById(R.id.myView1);
    }
    
    protected void onResume() {
        super.onResume();
        
        if (mAccelerometer != null)
            mSensorManager.registerListener(myView, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        
    }
    
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(myView);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

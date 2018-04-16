package com.example.saravanakumar.handlerandthread;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int INTERVAL = 1000 * 60; //1 minutes
    private Handler mHandler;
    private Runnable mHandlerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        mHandlerTask = new Runnable()
        {
            @Override
            public void run() {
                doSomething();
                mHandler.postDelayed(mHandlerTask, INTERVAL);
            }
        };
    }

    private void doSomething(){
        Toast.makeText(this,"Task Running in handler",Toast.LENGTH_SHORT).show();
        Log.i("Task","Running in background");
    }

    private void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    private void stopRepeatingTask()
    {
        mHandler.removeCallbacks(mHandlerTask);
    }

    @Override protected void onStart() {
        Log.d("OnStart","called");
        super.onStart();
    }

    @Override protected void onStop() {
        Log.d("OnStop","called");
        super.onStop();
    }

    @Override protected void onDestroy() {
        Log.d("OnDestroy","called");
        stopRepeatingTask();
        super.onDestroy();
    }

    @Override protected void onPause() {
        Log.d("OnPause","called");
        super.onPause();
    }

    @Override protected void onResume() {
        Log.d("OnResume","called");
        startRepeatingTask();
        super.onResume();
    }
}

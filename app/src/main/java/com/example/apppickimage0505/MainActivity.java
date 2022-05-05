package com.example.apppickimage0505;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    TextView mTxtPoint;
    ImageView mImgRandom,mImgPick;
    String[] mArrNameImages;
    int mResourceIdRandom;
    Random mRandom;
    MyCountDownTimer mMyCountDownTimer;
    long totalTime=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        event();

    }

    private void event() {

        //Listener
        mMyCountDownTimer.getInstance().countDown(5000, 1000, new MyCountDownTimer.OnListenerMyCountDownTimer() {
            @Override
            public void onTick(long currentTime) {
                mProgressBar.setProgress((int) currentTime/1000);
                //Toast.makeText(MainActivity.this, currentTime+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                mProgressBar.setProgress(0);
                //Log.d("BBB","on finish");
                //Toast.makeText(MainActivity.this, "onFinish", Toast.LENGTH_SHORT).show();
            }
        });

        //Handle
        randomImage();
    }

    private void init()
    {
        mRandom=new Random();
        mProgressBar=findViewById(R.id.progressBarTime);
        mTxtPoint=findViewById(R.id.textViewPoint);
        mImgRandom=findViewById(R.id.imgRandom);
        mImgPick=findViewById(R.id.imgPick);

        mProgressBar.setMax((int) totalTime/1000);
        mProgressBar.setProgress((int) totalTime/1000);
    }

    private void randomImage()
    {
        mArrNameImages=getResources().getStringArray(R.array.arr_image);
        int index=mRandom.nextInt(mArrNameImages.length);
        mResourceIdRandom=getResources().getIdentifier(mArrNameImages[index],"drawable",getPackageName());
        mImgRandom.setImageResource(mResourceIdRandom);
    }
}
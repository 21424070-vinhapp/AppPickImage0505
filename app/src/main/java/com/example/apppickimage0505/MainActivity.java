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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        randomImage();
        //Test
        mMyCountDownTimer.getInstance().countDown(5000, 1000, new MyCountDownTimer.OnListenerMyCountDownTimer() {
            @Override
            public void onTick(long currentTime) {
                Toast.makeText(MainActivity.this, currentTime+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                //Log.d("BBB","on finish");
                Toast.makeText(MainActivity.this, "onFinish", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init()
    {
        mRandom=new Random();
        mProgressBar=findViewById(R.id.progressBarTime);
        mTxtPoint=findViewById(R.id.textViewPoint);
        mImgRandom=findViewById(R.id.imgRandom);
        mImgPick=findViewById(R.id.imgPick);
    }

    private void randomImage()
    {
        mArrNameImages=getResources().getStringArray(R.array.arr_image);
        int index=mRandom.nextInt(mArrNameImages.length);
        mResourceIdRandom=getResources().getIdentifier(mArrNameImages[index],"drawable",getPackageName());
        mImgRandom.setImageResource(mResourceIdRandom);
    }
}
package com.example.apppickimage0505;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppickimage0505.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    String[] mArrNameImages;
    int mResourceIdRandom;
    Random mRandom;
    MyCountDownTimer mMyCountDownTimer;
    long totalTime=5000;
    int mPoint=0;
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
                mainBinding.progressBarTime.setProgress((int) currentTime/1000);
                //Toast.makeText(MainActivity.this, currentTime+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                mainBinding.progressBarTime.setProgress(0);
                //Log.d("BBB","on finish");
                //Toast.makeText(MainActivity.this, "onFinish", Toast.LENGTH_SHORT).show();
            }
        });

        //Handle
        randomImage();


        mainBinding.imgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

    private void init()
    {
        mRandom=new Random();
        mainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        //set point
        mainBinding.textViewPoint.setText(mPoint+"");
        //set max progressbar
        mainBinding.progressBarTime.setMax((int) totalTime/1000);
        mainBinding.progressBarTime.setProgress((int) totalTime/1000);
    }

    private void randomImage()
    {
        mArrNameImages=getResources().getStringArray(R.array.arr_image);
        int index=mRandom.nextInt(mArrNameImages.length);
        mResourceIdRandom=getResources().getIdentifier(mArrNameImages[index],"drawable",getPackageName());
        mainBinding.imgRandom.setImageResource(mResourceIdRandom);
    }

    //get data from activity main 2
    private ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== Activity.RESULT_OK)
                    {
                        Intent intent=result.getData();
                        int resourcePick=intent.getIntExtra("resourceID",-1);
                        mainBinding.imgPick.setImageResource(resourcePick);

                        if(resourcePick==mResourceIdRandom)
                        {
                            mPoint+=1;
                            mainBinding.textViewPoint.setText(mPoint+"");
                            //neu diem tang len thi se chuyen sang hinh tiep theo
                            Toast.makeText(MainActivity.this, "Chuan bi cho hinh tiep theo", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    randomImage();
                                    event();
                                }
                            },1500);
                        }
                        else
                        {
                            mainBinding.progressBarTime.setMax(0);
                            //mProgressBar.setProgress(0);
                            mainBinding.textViewPoint.setText(0+"");
                            mainBinding.imgPick.setImageResource(R.drawable.nophoto);
                            Toast.makeText(MainActivity.this, "Diem cua ban la: "+mPoint+" diem", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "BAN CHUA CHON HINH", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

}
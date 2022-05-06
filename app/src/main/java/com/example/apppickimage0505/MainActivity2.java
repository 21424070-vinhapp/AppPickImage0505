package com.example.apppickimage0505;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.text.CollationElementIterator;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {

    TableLayout mTableLayout;
    String[] mArrNameImages;
    int mPositon=0;
    int mIdResourceImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTableLayout=findViewById(R.id.tableLayout);

        mArrNameImages=getResources().getStringArray(R.array.arr_image);

        Collections.shuffle(Arrays.asList(mArrNameImages));

        //so dong: 6
        //so cot: 3

        for(int i=0;i<6;i++)
        {
            TableRow tableRow=new TableRow(this);
            for(int y=0;y<3;y++)
            {
                mPositon=3*i+y;
                mIdResourceImage=getResources().getIdentifier(mArrNameImages[mPositon],"drawable",getPackageName());
                ImageView imageView=new ImageView(this);
                imageView.setTag(mIdResourceImage);
                imageView.setImageResource(mIdResourceImage);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MainActivity2.this, imageView.getTag()+"", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        intent.putExtra("resourceID",(int)imageView.getTag());
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                tableRow.addView(imageView);
                //mPositon+=1;
            }
            mTableLayout.addView(tableRow);
        }


    }
}
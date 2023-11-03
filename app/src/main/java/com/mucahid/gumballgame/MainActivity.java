package com.mucahid.gumballgame;
import static com.mucahid.gumballgame.R.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;

    int score;
    int time;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    Handler handler;
    Runnable runnable;

    ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        // initialize
        score =0;
        timeText=(TextView) findViewById(id.timeText);
        scoreText=(TextView) findViewById(id.scoreText);
        imageArray = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        imageView1=findViewById(id.imageView1);
        imageView2=findViewById(id.imageView2);
        imageView3=findViewById(id.imageView3);
        imageView4=findViewById(id.imageView4);
        imageView5=findViewById(id.imageView5);
        imageView6=findViewById(id.imageView6);
        imageView7=findViewById(id.imageView7);
        imageView8=findViewById(id.imageView8);
        imageView9=findViewById(id.imageView9);

        imageArray=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();



        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time : "+l/1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("TIME OFF");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ? ");
                alert.setMessage("Are you sure to restart game ? ");
                alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // restart
                        Intent intent =getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score: "+score);

    }
    public void hideImages(){

        handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,250);
            }
        };
        handler.post(runnable);



    }
}
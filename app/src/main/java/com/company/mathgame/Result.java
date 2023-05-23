package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {


    TextView result;
    Button playAgain;
    Button exit;

  int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=findViewById(R.id.result);
        playAgain=findViewById(R.id.buttonPlayAgain);
        exit=findViewById(R.id.buttonExit);

        Intent intent=getIntent();
         score=intent.getIntExtra("Score",0);
         result.setText("Your Score :"+score);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // first parameter in which page intent will work
                // second parameter which page this will open
                Intent intent=new Intent(Result.this,MainActivity.class);
                startActivity(intent);
                // i am going to call finsh method so when this intent opens another page the finish method will close this page
                //so this is very important because when we pass using intent from one activity to another we should close the older activity
                //otherwise so many activitys will stay open
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();



            }
        });

    }
}
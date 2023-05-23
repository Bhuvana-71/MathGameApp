package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class mul extends AppCompatActivity {
TextView score;
TextView life;
TextView time;

TextView question;
EditText answer;
Button ok;
Button next;
Random r=new Random();
int userAnswer;
int correctAnswer;
CountDownTimer timer;
int userLife=3;
int userScore=0;
private static final long START_TIME_IN_MILLS=10000;
long TIME_LEFT_IN_MILLS=START_TIME_IN_MILLS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul);


        score=findViewById(R.id.textViewScorem);
        life=findViewById(R.id.textViewLifem);
        time=findViewById(R.id.textViewTimerm);

        question=findViewById(R.id.textViewquestionm);
        answer=findViewById(R.id.editTextAnswerm);
        ok=findViewById(R.id.buttonokm);
        next=findViewById(R.id.buttonnextm);


        gameContinue();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
                userAnswer=Integer.valueOf(answer.getText().toString());
                if(userAnswer==correctAnswer)
                {
                    userScore+=10;
                    score.setText(""+userScore);
                    question.setText("Congratulations Its Correct Answer");

                }
                else
                {
                    userLife=userLife-1;
                    life.setText(""+userLife);
                    question.setText("Sorry Wrong Answer");

                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
                if(userLife<=0)
                {
                    Intent intent=new Intent(mul.this,Result.class);
                    intent.putExtra("Score",userScore);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    answer.setText("");
                    gameContinue();
                }
            }
        });







    }
    public void gameContinue()
    {

        int num1=r.nextInt(100);
        int num2=r.nextInt(100);
        correctAnswer=num1*num2;
        question.setText(num1+"*"+num2);
        startTimer();


    }
    public void startTimer()
    {
        timer=new CountDownTimer(TIME_LEFT_IN_MILLS,1000) {
            @Override
            public void onTick(long l) {
                TIME_LEFT_IN_MILLS=l;
                updateText();
            }

            @Override
            public void onFinish() {
                pauseTimer();
                resetTimer();
                updateText();
                question.setText("Sorry Time is up");
                userLife=userLife-1;
                life.setText(""+userLife);

            }
        }.start();
    }
    public void pauseTimer()
    {
        timer.cancel();

    }
    public void updateText()
    {
        int time_left=(int)(TIME_LEFT_IN_MILLS/1000);
        String value=String.format(Locale.getDefault(),"%02d",time_left);
        time.setText(value);
    }
    public   void resetTimer()
    {
        TIME_LEFT_IN_MILLS=START_TIME_IN_MILLS;
        updateText();
    }

}
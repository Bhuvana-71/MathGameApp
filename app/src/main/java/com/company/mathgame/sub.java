package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;


public class sub extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;
    TextView answer;
Button ok;
Button next;
Random r=new Random();
int userAnswer;
int correctAnswer;
int userScore=0;
int userlife=3;
public static final long START_TIMER_IN_MILLIS=10000;
long time_left_in_millis=START_TIMER_IN_MILLIS;
CountDownTimer timer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        score=findViewById(R.id.textviewscoree);
      life=findViewById(R.id.textViewLife);
      time=findViewById(R.id.textViewTimer);
      question=findViewById(R.id.textViewQuestion);
      answer=findViewById(R.id.edittextAnswer);
      ok=findViewById(R.id.buttonOks);
      next=findViewById(R.id.buttonNextS);

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
                  question.setText("Sorry Wrong Answer");
                  userlife-=1;
                  life.setText(""+userlife);

              }
          }
      });
      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              resetTimer();
              if(userlife<=0)
              {
                  Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_LONG).show();
                  Intent intent=new Intent(sub.this,Result.class);
                  intent.putExtra("Score",userScore);
                  startActivity(intent);
                  finish();

              }
              else {
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
        correctAnswer=num1-num2;

        question.setText(num1+"-"+num2);
        startTimer();

    }
    public void startTimer()
    {
        timer=new CountDownTimer( time_left_in_millis,1000) {
            @Override
            public void onTick(long l) {

                time_left_in_millis=l;
                updateText();

            }

            @Override
            public void onFinish() {

                pauseTimer();
                resetTimer();
                updateText();
                userlife=userlife-1;
                life.setText(""+userlife);
                question.setText("Time is Up!!");




            }
        }.start();
    }
    public void updateText()
    {
        int val=(int)(time_left_in_millis/1000);
        String timeLeft=String.format(Locale.getDefault(),"%02d",val);
        time.setText(timeLeft);
    }
    public void resetTimer()
    {
        time_left_in_millis=START_TIMER_IN_MILLIS;
        updateText();
    }
    public void pauseTimer()
    {
        timer.cancel();
    }
}


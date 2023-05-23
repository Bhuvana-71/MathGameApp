package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView  score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;
    int userAnswer;
    int number1;
    int number2;
    int correctAnswer;
    int userScore=0;
    int userLife=3;
Random r=new Random();


CountDownTimer timer;




private static final long START_TIMER_IN_MILLIS=10000;
Boolean timer_running;
long time_Left_in_millis=START_TIMER_IN_MILLIS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        score=findViewById(R.id.textViewscore);
        life=findViewById(R.id.textViewlife);
        time=findViewById(R.id.textViewtime);
        question=findViewById(R.id.textViewquestion);
        answer=findViewById(R.id.edittextanswer);
        ok=findViewById(R.id.buttonok);
        next=findViewById(R.id.buttonnext);
        life.setText(userLife+"");

   gameContinue();



      ok.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              pauseTimer();

              userAnswer=Integer.valueOf(answer.getText().toString());
              if(userAnswer==correctAnswer)
              {
                  question.setText("Congratulations Its Correct Answer");
                  userScore+=10;
                  score.setText(""+userScore);
              }
              else
              {
                  question.setText("Sorry Wrong Answer...");
                  userLife=userLife-1;
                  life.setText(""+userLife);
              }


          }
      });
      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              resetTimer();

              answer.setText("");

              if(userLife<=0)
              {
                  Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_LONG).show();
                  Intent intent =new Intent(Game.this,Result.class);
                  intent.putExtra("Score",userScore);
                  startActivity(intent);
                  finish();

              }
              else
              {
                  gameContinue();
              }

          }
      });







    }
    public void gameContinue()
    {


        number1=r.nextInt(100);
        number2=r.nextInt(100);
        correctAnswer=number1+number2;
        question.setText(number1+"+"+number2);

startTimer();


    }
    public void  startTimer()
    {
        timer=new CountDownTimer(time_Left_in_millis,1000) {
            @Override
            public void onTick(long l) {
                          time_Left_in_millis=l;
                          updateText();
            }

            @Override
            public void onFinish() {
                          timer_running=false;
                          pauseTimer();
                          updateText();
                          resetTimer();
                          userLife=userLife-1;
                          life.setText(userLife+"");
                          question.setText("Sorry! Time is up!");
            }
        }.start();
        timer_running=true;
    }
    public void updateText()
    {
        int second=(int)(time_Left_in_millis/1000);
        String time_left=String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void resetTimer()
    {
        time_Left_in_millis=START_TIMER_IN_MILLIS;
        updateText();
    }
    public void pauseTimer()
    {
        timer.cancel();
        timer_running=false;
    }



}
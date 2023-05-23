package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button addition;
    Button subtraction;
    Button multi;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addition =findViewById(R.id.buttonadd);
        subtraction=findViewById(R.id.buttonsub);
        multi=findViewById(R.id.buttonmul);


        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game.class);
                startActivity(intent);
                finish();
            }
        });
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,sub.class);
                startActivity(intent);
                finish();
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,mul.class);
                startActivity(intent);
                finish();



            }
        });





    }
}
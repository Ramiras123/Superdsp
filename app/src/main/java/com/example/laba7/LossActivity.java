package com.example.laba7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LossActivity extends AppCompatActivity {
   // private TextView moves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loss);
     //   moves = (TextView)findViewById(R.id.textView5);
      //  moves.setText(new Integer(getIntent().getIntExtra("count",0)).toString());
    }

    public void onClick_back(View view) {
        Intent intent = new Intent(LossActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
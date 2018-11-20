package com.example.laba7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {
    private TextView moves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        moves = (TextView)findViewById(R.id.textView5);
        moves.setText(new Integer(getIntent().getIntExtra("count",0)).toString());
    }

    public void onClick_back(View view) {
        Intent intent = new Intent(WinActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
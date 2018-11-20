package com.example.laba7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class OctopusGUIActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener{
    private int count;
    private Octopus octo;
    private final int rows = 7;
    private final int cols = 7;
    private Button state[][];
    private TextView moves;
    private TextView moves1;
    private TextView moves2;
    private TextView moves3;
    private String texter;
    private SoundPool soundpool;
    private int sound1;
    private String[][] numState;
    private int status = 0;
    private CountDownTimer timer;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_octopus_gui);
      //  WebView wv = (WebView) findViewById(R.id.webView2);
      //  wv.loadUrl("file:///android_asset/giff.gif");
        init();
        count = 0;
        octo = new Octopus(this);
        numState =  octo.getNewState();
        initState(numState);
        soundpool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        soundpool.setOnLoadCompleteListener(this);
        id = soundpool.load(this,R.raw.pussybomb, 1);
        timer();
        timer.start();
    }
    public  void timer()
    {

       timer =new CountDownTimer(60000,1000){
            public void onTick(long millis)
            {
                moves2.setText(String.valueOf(millis/1000));
            }
            @SuppressLint("MissingPermission")
            public void onFinish() {
                cancel();
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(700);
                Intent intent = new Intent(OctopusGUIActivity.this,LossActivity.class);
                intent.putExtra("count",count);
                startActivity(intent);

            }

        };



    }
    public void onClick_Back(View view) {
        Intent intent = new Intent(OctopusGUIActivity.this,MainActivity.class);
        startActivity(intent);
    }
  private void initState(String[][] numState){
        texter = Octopus.slov;
        moves1.setBackgroundColor(Color.BLACK);
        moves1.setText(texter);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                state[i][j].setText(numState[i][j]);
            }
        }
        moves.setText(new Integer(count).toString());
        if (checkState(Octopus.validState)){
            Intent intent = new Intent(OctopusGUIActivity.this,WinActivity.class);
            intent.putExtra("count",count);
            startActivity(intent);
        }
    }

  private boolean checkState(String[][] numState){
        boolean valid = true;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String num = state[i][j].getText().toString();
                if (num.compareTo(numState[i][j]) != 0 )
                 {
                    state[i][j].setBackgroundColor(Color.WHITE);
                    state[i][j].setTextColor(Color.BLACK);
                    valid = false;
                } else {
                    state[i][j].setBackgroundColor(Color.TRANSPARENT);
                }

            }
        }
        if (valid)
        {
            if (Octopus.lvl == 5)
            {
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v1.vibrate(3000);
                return true;
            }
            timer.cancel();
            Octopus.lvl++;
            moves3.setText(String.valueOf(Octopus.lvl+1));
            numState =  octo.getNewState();
            initState(numState);
            timer();
            status = 0;
            timer.start();

            valid = false;

        }
        return valid;
    }

    private void move(int row, int col){
        char my;
        my = texter.charAt(status);
        final SpannableStringBuilder text = new SpannableStringBuilder(texter);
        ForegroundColorSpan style = new ForegroundColorSpan(Color.GREEN);
        ForegroundColorSpan style1 = new ForegroundColorSpan(Color.RED);
        if (my == ' ')
        {
            my = '_';

        }
        if (String.valueOf(my).compareTo(state[row][col].getText().toString()) == 0)
        {
            count++;
            state[row][col].setText(Octopus.empty);
            text.setSpan(style,0,status+1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            if (my == '_')
            {
                texter = texter.substring(status);
                moves1.setText(texter);
                status = 0;
            } else
            moves1.setText(text);
            status++;
        } else
        {
            text.setSpan(style,0,status, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            text.setSpan(style1,status,status+1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            moves1.setText(text);
            count = 0;
            soundpool.play(id, 1,1,1,0,2);


        }
        moves.setText(new Integer(count).toString());

       if (checkState(Octopus.validState)) {
               Intent intent = new Intent(OctopusGUIActivity.this, WinActivity.class);
               intent.putExtra("count", count);
               startActivity(intent);
           }

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b11: move(0,0);
                break;
            case R.id.b12: move(0,1);
                break;
            case R.id.b13: move(0,2);
                break;
            case R.id.b14: move(0,3);
                break;
            case R.id.b15: move(0,4);
                break;
            case R.id.b16: move(0,5);
                break;
            case R.id.b17: move(0,6);
                break;
            case R.id.b21: move(1,0);
                break;
            case R.id.b22: move(1,1);
                break;
            case R.id.b23: move(1,2);
                break;
            case R.id.b24: move(1,3);
                break;
            case R.id.b25: move(1,4);
                break;
            case R.id.b26: move(1,5);
                break;
            case R.id.b27: move(1,6);
                break;
            case R.id.b31: move(2,0);
                break;
            case R.id.b32: move(2,1);
                break;
            case R.id.b33: move(2,2);
                break;
            case R.id.b34: move(2,3);
                break;
            case R.id.b35: move(2,4);
                break;
            case R.id.b36: move(2,5);
                break;
            case R.id.b37: move(2,6);
                break;
            case R.id.b41: move(3,0);
                break;
            case R.id.b42: move(3,1);
                break;
            case R.id.b43: move(3,2);
                break;
            case R.id.b44: move(3,3);
                break;
            case R.id.b45: move(3,4);
                break;
            case R.id.b46: move(3,5);
                break;
            case R.id.b47: move(3,6);
                break;
            case R.id.b51: move(4,0);
                break;
            case R.id.b52: move(4,1);
                break;
            case R.id.b53: move(4,2);
                break;
            case R.id.b54: move(4,3);
                break;
            case R.id.b55: move(4,4);
                break;
            case R.id.b56: move(4,5);
                break;
            case R.id.b57: move(4,6);
                break;
            case R.id.b61: move(5,0);
                break;
            case R.id.b62: move(5,1);
                break;
            case R.id.b63: move(5,2);
                break;
            case R.id.b64: move(5,3);
                break;
            case R.id.b65: move(5,4);
                break;
            case R.id.b66: move(5,5);
                break;
            case R.id.b67: move(5,6);
                break;
            case R.id.b71: move(6,0);
                break;
            case R.id.b72: move(6,1);
                break;
            case R.id.b73: move(6,2);
                break;
            case R.id.b74: move(6,3);
                break;
            case R.id.b75: move(6,4);
                break;
            case R.id.b76: move(6,5);
                break;
            case R.id.b77: move(6,6);
                break;
        }
    }

    private void init(){
        state = new Button[rows][cols];
        state[0][0] = (Button)findViewById(R.id.b11);
        state[0][1] = (Button)findViewById(R.id.b12);
        state[0][2] = (Button)findViewById(R.id.b13);
        state[0][3] = (Button)findViewById(R.id.b14);
        state[0][4] = (Button)findViewById(R.id.b15);
        state[0][5] = (Button)findViewById(R.id.b16);
        state[0][6] = (Button)findViewById(R.id.b17);
        state[1][0] = (Button)findViewById(R.id.b21);
        state[1][1] = (Button)findViewById(R.id.b22);
        state[1][2] = (Button)findViewById(R.id.b23);
        state[1][3] = (Button)findViewById(R.id.b24);
        state[1][4] = (Button)findViewById(R.id.b25);
        state[1][5] = (Button)findViewById(R.id.b26);
        state[1][6] = (Button)findViewById(R.id.b27);
        state[2][0] = (Button)findViewById(R.id.b31);
        state[2][1] = (Button)findViewById(R.id.b32);
        state[2][2] = (Button)findViewById(R.id.b33);
        state[2][3] = (Button)findViewById(R.id.b34);
        state[2][4] = (Button)findViewById(R.id.b35);
        state[2][5] = (Button)findViewById(R.id.b36);
        state[2][6] = (Button)findViewById(R.id.b37);
        state[3][0] = (Button)findViewById(R.id.b41);
        state[3][1] = (Button)findViewById(R.id.b42);
        state[3][2] = (Button)findViewById(R.id.b43);
        state[3][3] = (Button)findViewById(R.id.b44);
        state[3][4] = (Button)findViewById(R.id.b45);
        state[3][5] = (Button)findViewById(R.id.b46);
        state[3][6] = (Button)findViewById(R.id.b47);
        state[4][0] = (Button)findViewById(R.id.b51);
        state[4][1] = (Button)findViewById(R.id.b52);
        state[4][2] = (Button)findViewById(R.id.b53);
        state[4][3] = (Button)findViewById(R.id.b54);
        state[4][4] = (Button)findViewById(R.id.b55);
        state[4][5] = (Button)findViewById(R.id.b56);
        state[4][6] = (Button)findViewById(R.id.b57);
        state[5][0] = (Button)findViewById(R.id.b61);
        state[5][1] = (Button)findViewById(R.id.b62);
        state[5][2] = (Button)findViewById(R.id.b63);
        state[5][3] = (Button)findViewById(R.id.b64);
        state[5][4] = (Button)findViewById(R.id.b65);
        state[5][5] = (Button)findViewById(R.id.b66);
        state[5][6] = (Button)findViewById(R.id.b67);
        state[6][0] = (Button)findViewById(R.id.b71);
        state[6][1] = (Button)findViewById(R.id.b72);
        state[6][2] = (Button)findViewById(R.id.b73);
        state[6][3] = (Button)findViewById(R.id.b74);
        state[6][4] = (Button)findViewById(R.id.b75);
        state[6][5] = (Button)findViewById(R.id.b76);
        state[6][6] = (Button)findViewById(R.id.b77);
        moves = (TextView)findViewById(R.id.textView2);
        moves1 = (TextView)findViewById(R.id.textView6);
        moves2 = (TextView)findViewById(R.id.textView8);
        moves3 = (TextView)findViewById(R.id.textView18);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
    }
}

package com.example.laba7;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.RawRes;
import android.text.Layout;
import android.util.Pair;
import android.view.ViewDebug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Random;

public class Octopus {
    private final int rows = 7;
    private final int cols = 7;
    public static int lvl = 0;
    public final static String empty = " ";
    public static String slov ="hello";
    Context ctx;
    public static String validState[][]={
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty}
    };

    private String curState[][]={
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty},
            {empty,empty,empty,empty,empty,empty,empty}
    };

    public Octopus(Context context){
        ctx = context;
     }
    private void readFile()
    {
        try
        {
            AssetManager str = ctx.getAssets();
            InputStreamReader stream1 = new InputStreamReader(str.open("texter1"));
            BufferedReader buffer = new BufferedReader(stream1);
            String strLine;
            //slov = buffer.readLine().toLowerCase();
           int i = -1;
           while (((strLine = buffer.readLine()) != null) && (lvl != i))
            {
                slov = strLine.toLowerCase();
                i++;
            }


          buffer.close();
          stream1.close();
        } //
         catch (IOException e){
          //  throw new RuntimeException(e);
        }
    }

    public String[][] getNewState() {
        readFile();
        Random random = new Random();
        int[] array = new int[49];
        boolean numberAlreadyExists;
        for (int i = 1; i < array.length;) {
            numberAlreadyExists = false;
            int newRandomValue = random.nextInt(49);
            for (int j = 0; j < i; j++) {
                if (array[j] == newRandomValue) {
                    numberAlreadyExists = true;
                    break;
                }
            }
            if (!numberAlreadyExists) {
                array[i] = newRandomValue;
                i++;
            }
        }
        char my;
        int i1 = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (array[i1] >= slov.length())
                {
                    curState[i][j] = empty;
                } else {
                    my = slov.charAt(array[i1]);
                    if (my == ' ')
                    {
                        curState[i][j] = "_";
                    } else
                    curState[i][j] = String.valueOf(my);
                }
                i1++;
            }
        }
        return curState;
    }
}

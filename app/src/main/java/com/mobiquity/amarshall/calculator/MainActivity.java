package com.mobiquity.amarshall.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    public static final String TAG = "tag";

    Button_Handler button_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity: onCreate");
        setContentView(R.layout.activity_main);

        button_handler = new Button_Handler(this, this);


    }

    @Override
    protected void onStart() {
        Log.d(TAG, "MainActivity: onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "MainActivity: onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "MainActivity: onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "MainActivity: onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "MainActivity: onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "MainActivity: onDestroy");
        super.onDestroy();
    }


}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

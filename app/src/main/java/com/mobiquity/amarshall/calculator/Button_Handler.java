package com.mobiquity.amarshall.calculator;


import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Button_Handler implements View.OnClickListener, View.OnTouchListener {

    /** Used for LogCat Tags */
    public final String TAG = "tag";

    /** Used to keep a reference to our MainActivity */
    private MainActivity main_activity;
    private Context main_activity_context;

    /** Holds the values for the calculation */
    private double result;

    /** The String that is displayed in the Display */
    private String display_string;

    /** Boolean used for triggering a clear */
    private boolean long_pressed = false;

    /** MainActivity Buttons */
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_del;
    private Button button_plus;
    private Button button_minus;
    private Button button_multiply;
    private Button button_divide;
    private Button button_equals;
    private Button button_decimal;

    /** MainActivity TextView */
    private TextView textView_display;

    /**
     * This constructor is used when we are handling button pushed from the MainActivity.
     *
     * @param activity_in The MainActivity
     * @param context_in  The MainActivity context
     */
    public Button_Handler(MainActivity activity_in, Context context_in) {

        main_activity = activity_in;
        main_activity_context = context_in;

        // Special case - get the TextView in the button Handler - AHHH!
        textView_display = (TextView) main_activity.findViewById(R.id.textView_display);

        // Set our display string to 0
        display_string = "0";

        set_up_buttons();

    }

    /**
     * Wires up the buttons and sets their onClickListener.
     */
    private void set_up_buttons() {

        // Gets handles to buttons
        button_0 = (Button) main_activity.findViewById(R.id.button_0);
        button_1 = (Button) main_activity.findViewById(R.id.button_1);
        button_2 = (Button) main_activity.findViewById(R.id.button_2);
        button_3 = (Button) main_activity.findViewById(R.id.button_3);
        button_4 = (Button) main_activity.findViewById(R.id.button_4);
        button_5 = (Button) main_activity.findViewById(R.id.button_5);
        button_6 = (Button) main_activity.findViewById(R.id.button_6);
        button_7 = (Button) main_activity.findViewById(R.id.button_7);
        button_8 = (Button) main_activity.findViewById(R.id.button_8);
        button_9 = (Button) main_activity.findViewById(R.id.button_9);
        button_del = (Button) main_activity.findViewById(R.id.button_delete);
        button_plus = (Button) main_activity.findViewById(R.id.button_plus);
        button_minus = (Button) main_activity.findViewById(R.id.button_minus);
        button_multiply = (Button) main_activity.findViewById(R.id.button_multiply);
        button_divide = (Button) main_activity.findViewById(R.id.button_divide);
        button_equals = (Button) main_activity.findViewById(R.id.button_equals);
        button_decimal = (Button) main_activity.findViewById(R.id.button_decimal);

        // Wire up the onClickListener
        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_equals.setOnClickListener(this);
        button_decimal.setOnClickListener(this);

        // Set onClickListener
        button_del.setOnTouchListener(this);


    }

    private void add_to_display(String symbol_in) {

        display_string = display_string + symbol_in;

        Log.d(TAG, "Value in display: " + display_string);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_0:
                Log.d(TAG, "Button 0 pushed");

                break;

            case R.id.button_1:
                Log.d(TAG, "Button 1 pushed");

                break;

            case R.id.button_2:
                Log.d(TAG, "Button 2 pushed");

                break;

            case R.id.button_3:
                Log.d(TAG, "Button 3 pushed");

                break;

            case R.id.button_4:
                Log.d(TAG, "Button 4 pushed");

                break;

            case R.id.button_5:
                Log.d(TAG, "Button 5 pushed");

                break;

            case R.id.button_6:
                Log.d(TAG, "Button 6 pushed");

                break;

            case R.id.button_7:
                Log.d(TAG, "Button 7 pushed");

                break;

            case R.id.button_8:
                Log.d(TAG, "Button 8 pushed");

                break;

            case R.id.button_9:
                Log.d(TAG, "Button 9 pushed");

                break;

            case R.id.button_delete:
                Log.d(TAG, "Button DELETE pushed");

                break;

            case R.id.button_plus:
                Log.d(TAG, "Button PLUS pushed");

                break;

            case R.id.button_minus:
                Log.d(TAG, "Button MINUS pushed");

                break;

            case R.id.button_multiply:
                Log.d(TAG, "Button MULTIPLY pushed");

                break;

            case R.id.button_divide:
                Log.d(TAG, "Button DIVIDE pushed");

                break;

            case R.id.button_equals:
                Log.d(TAG, "Button EQUALS pushed");

                break;

            case R.id.button_decimal:
                Log.d(TAG, "Button DECIMAL pushed");

                break;

        }

    }

    final Handler handler = new Handler();
    Runnable mLongPressed = new Runnable() {
        public void run() {
            Log.i(TAG, "Long press!");

            button_del.setText("CLR");

            long_pressed = true;

        }
    };


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.i(TAG, "Delete / Clear key was pushed.");

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                handler.postDelayed(mLongPressed, 750);
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_MOVE:
                handler.removeCallbacks(mLongPressed);
                break;

            case MotionEvent.ACTION_UP:
                handler.removeCallbacks(mLongPressed);

                button_del.setText("DEL");
                Log.i(TAG, "Normal Delete Pushed");

                if (long_pressed) {
                    long_pressed = false;
                    textView_display.setText("0");
                }

                break;

        }


        return false;


    }
}
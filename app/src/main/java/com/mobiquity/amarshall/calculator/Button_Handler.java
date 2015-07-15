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
    private final String TAG = "tag";

    /** Used to identify the math function */
    public static final int PLUS = 0;
    public static final int MINUS = 1;
    public static final int MULTIPLY = 2;
    public static final int DIVIDE = 3;

    /** Used to keep a reference to our MainActivity */
    private MainActivity main_activity;
    private Context main_activity_context;

    /** Holds the values for the calculation */
    private int result;

    /** Indicated which function we are in */
    private int selected_function;

    /** The String that is displayed in the Display */
    private String display_string;
    private int displayed_num;

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

        // Set out selected function as -1
        selected_function = -1;

        // Set result to 0
        result = 0;

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

    private void delete_1_symbol() {

        // Check to see if this is the first number enter
        if (display_string.compareTo("0") == 0) {
            display_string = "0";
        } else if (display_string.length() == 1) {
            display_string = "0";
        } else {
            display_string = display_string.substring(0, display_string.length() - 1);
        }

        displayed_num = Integer.parseInt(display_string);

        textView_display.setText(display_string);

        Log.d(TAG, "Value in display: " + display_string);

        Log.d(TAG, "Number in display: " + Double.parseDouble(display_string));

        //TODO: Error checking

    }

    /**
     * Adds a symbol to the display, converting it to a number.
     *
     * @param symbol_in The symbol to add to the display.
     */
    private void add_to_display(String symbol_in) {

        // Check to see if this is the first number enter
        if (display_string.compareTo("0") == 0) {
            display_string = symbol_in;
        } else if (display_string.compareTo("Err") == 0) {
            display_string = symbol_in;
        } else {
            display_string = display_string + symbol_in;
        }

        textView_display.setText(display_string);

        displayed_num = Integer.parseInt(display_string);

        Log.d(TAG, "Value in display: " + display_string);

        Log.d(TAG, "Number in display: " + displayed_num);

        //TODO: Error checking

    }

    /**
     * Clears everything.
     */
    private void clear() {

        Log.d(TAG, "Clearing everything");
        display_string = "0";
        displayed_num = 0;
        result = 0;
        textView_display.setText(display_string);

        selected_function = -1;

        black_out_function_buttons();
    }

    /**
     * Makes all the function buttons black again.
     */
    private void black_out_function_buttons() {

        // Make all the function buttons black
        button_plus.setTextColor(main_activity.getResources().getColor(R.color.black));
        button_minus.setTextColor(main_activity.getResources().getColor(R.color.black));
        button_multiply.setTextColor(main_activity.getResources().getColor(R.color.black));
        button_divide.setTextColor(main_activity.getResources().getColor(R.color.black));

    }

    private void set_function(int function_in) {

//        // Check to see if we need to compute
//        if (selected_function != -1) {
//            compute();
//        }

        black_out_function_buttons();

        result = displayed_num;

        switch (function_in) {

            case PLUS:

                button_plus.setTextColor(main_activity.getResources().getColor(R.color.purple));

                selected_function = PLUS;

                break;

            case MINUS:

                button_minus.setTextColor(main_activity.getResources().getColor(R.color.blue));

                selected_function = MINUS;

                break;

            case MULTIPLY:

                button_multiply.setTextColor(main_activity.getResources().getColor(R.color.green));

                selected_function = MULTIPLY;

                break;

            case DIVIDE:

                button_divide.setTextColor(main_activity.getResources().getColor(R.color.yellow));

                selected_function = DIVIDE;

                break;

        }

        // Clear the display, getting ready for a new number
        result = displayed_num;

        display_string = "0";
        displayed_num = 0;

        textView_display.setText(display_string);

    }

    private void compute() {

        switch (selected_function) {

            case PLUS:

                result = result + displayed_num;

                break;

            case MINUS:

                result = result - displayed_num;

                break;

            case MULTIPLY:

                result = result * displayed_num;

                break;

            case DIVIDE:

                if (displayed_num != 0) {
                    result = result / displayed_num;
                }

                else {
                    result = 0;
                    displayed_num = 0;
                    display_string = "Err";
                    textView_display.setText(display_string);
                    selected_function = -1;
                    return;
                }


                break;

            default:
                // No function was selected aka -1
        }

        display_string = "" + result;

        displayed_num = result;

        textView_display.setText(display_string);

        selected_function = -1;

        black_out_function_buttons();


    }

    /**
     * Handles button pushes.
     *
     * @param v The view that was pushed.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_0:
                Log.d(TAG, "Button 0 pushed");

                add_to_display("0");

                break;

            case R.id.button_1:
                Log.d(TAG, "Button 1 pushed");

                add_to_display("1");

                break;

            case R.id.button_2:
                Log.d(TAG, "Button 2 pushed");

                add_to_display("2");

                break;

            case R.id.button_3:
                Log.d(TAG, "Button 3 pushed");

                add_to_display("3");

                break;

            case R.id.button_4:
                Log.d(TAG, "Button 4 pushed");

                add_to_display("4");

                break;

            case R.id.button_5:
                Log.d(TAG, "Button 5 pushed");

                add_to_display("5");

                break;

            case R.id.button_6:
                Log.d(TAG, "Button 6 pushed");

                add_to_display("6");

                break;

            case R.id.button_7:
                Log.d(TAG, "Button 7 pushed");

                add_to_display("7");

                break;

            case R.id.button_8:
                Log.d(TAG, "Button 8 pushed");

                add_to_display("8");

                break;

            case R.id.button_9:
                Log.d(TAG, "Button 9 pushed");

                add_to_display("9");

                break;

            case R.id.button_delete:
                Log.d(TAG, "Button DELETE pushed");

                break;

            case R.id.button_plus:
                Log.d(TAG, "Button PLUS pushed");

                set_function(PLUS);

                break;

            case R.id.button_minus:
                Log.d(TAG, "Button MINUS pushed");

                set_function(MINUS);

                break;

            case R.id.button_multiply:
                Log.d(TAG, "Button MULTIPLY pushed");

                set_function(MULTIPLY);

                break;

            case R.id.button_divide:
                Log.d(TAG, "Button DIVIDE pushed");

                set_function(DIVIDE);

                break;

            case R.id.button_equals:
                Log.d(TAG, "Button EQUALS pushed");

                black_out_function_buttons();

                compute();

                break;

            case R.id.button_decimal:
                Log.d(TAG, "Button DECIMAL pushed");

                break;

        }

    }

    /**
     * Handles the long press runnable.
     */
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


                if (long_pressed) {
                    long_pressed = false;
                    clear();
                } else {
                    Log.i(TAG, "Normal Delete Pushed");
                    delete_1_symbol();
                }

                break;

        }


        return false;


    }
}
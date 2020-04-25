package com.example.samplecalculator;


import java.text.DecimalFormat;
import java.util.Stack;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    /*
     * I tried to implement a stack to hold the values in order to display it on the history view
     * I'm having issues parsing the values out in order to store them
     * For that reason this feature isn't fully working
     * */
    Stack<String> stack = new Stack<String>();

    //    The  gear icon on the top to load the settings page
    ImageButton btnSettings;

    //    Buttons needed to create the rainbow settings effect
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, btnAdd, btnSub, btnDiv, btnMult, btnEqual, btnClear;
    private Button btnDec, btnNeg, btnMC, btnMPlus, btnMSub, btnMR, btnBS, btnCos, btnSin, btnTan, btnSquare;

    //    Variables for the textviews, etc.
    private TextView calcDisplay;
    public TextView historyView;
    private Boolean userInput = false;
    private CalculatorFunctions CalcBrain;
    private static final String DIGITS = "0123456789.";
    DecimalFormat df = new DecimalFormat("@####");

    //@SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalcBrain = new CalculatorFunctions();
        calcDisplay = (TextView) findViewById(R.id.textView1);
        historyView = (TextView) findViewById(R.id.infoTextView);


        /*
         * Control the settings button
         * Starts the new intent which loads in the settings screen
         * */
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

//        Sets the decimal amount
        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        /*
         * Checks if the orientation is landscape
         * Adds the onClickListeners to the buttons
         * The app will crash if these are left out - looks for the buttons that dont exist
         * Assign onClickListeners to all of the buttons on the screen
         * */
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            findViewById(R.id.button0).setOnClickListener(this);
            findViewById(R.id.button1).setOnClickListener(this);
            findViewById(R.id.button2).setOnClickListener(this);
            findViewById(R.id.button3).setOnClickListener(this);
            findViewById(R.id.button4).setOnClickListener(this);
            findViewById(R.id.button5).setOnClickListener(this);
            findViewById(R.id.button6).setOnClickListener(this);
            findViewById(R.id.button7).setOnClickListener(this);
            findViewById(R.id.button8).setOnClickListener(this);
            findViewById(R.id.button9).setOnClickListener(this);
            findViewById(R.id.buttonAdd).setOnClickListener(this);
            findViewById(R.id.buttonSubtract).setOnClickListener(this);
            findViewById(R.id.buttonMultiply).setOnClickListener(this);
            findViewById(R.id.buttonDivide).setOnClickListener(this);
            findViewById(R.id.buttonToggleSign).setOnClickListener(this);
            findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
            findViewById(R.id.buttonEquals).setOnClickListener(this);
            findViewById(R.id.buttonClear).setOnClickListener(this);
            findViewById(R.id.buttonClearMemory).setOnClickListener(this);
            findViewById(R.id.buttonAddToMemory).setOnClickListener(this);
            findViewById(R.id.buttonSubtractFromMemory).setOnClickListener(this);
            findViewById(R.id.buttonRecallMemory).setOnClickListener(this);
            findViewById(R.id.buttonBackspace).setOnClickListener(this);
            findViewById(R.id.buttonSin).setOnClickListener(this);
            findViewById(R.id.buttonCos).setOnClickListener(this);
            findViewById(R.id.buttonTan).setOnClickListener(this);
            findViewById(R.id.buttonSquare).setOnClickListener(this);
            findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        } else {
            findViewById(R.id.button0).setOnClickListener(this);
            findViewById(R.id.button1).setOnClickListener(this);
            findViewById(R.id.button2).setOnClickListener(this);
            findViewById(R.id.button3).setOnClickListener(this);
            findViewById(R.id.button4).setOnClickListener(this);
            findViewById(R.id.button5).setOnClickListener(this);
            findViewById(R.id.button6).setOnClickListener(this);
            findViewById(R.id.button7).setOnClickListener(this);
            findViewById(R.id.button8).setOnClickListener(this);
            findViewById(R.id.button9).setOnClickListener(this);
            findViewById(R.id.buttonAdd).setOnClickListener(this);
            findViewById(R.id.buttonSubtract).setOnClickListener(this);
            findViewById(R.id.buttonMultiply).setOnClickListener(this);
            findViewById(R.id.buttonDivide).setOnClickListener(this);
            findViewById(R.id.buttonToggleSign).setOnClickListener(this);
            findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
            findViewById(R.id.buttonEquals).setOnClickListener(this);
            findViewById(R.id.buttonClear).setOnClickListener(this);
            findViewById(R.id.buttonClearMemory).setOnClickListener(this);
            findViewById(R.id.buttonAddToMemory).setOnClickListener(this);
            findViewById(R.id.buttonSubtractFromMemory).setOnClickListener(this);
            findViewById(R.id.buttonRecallMemory).setOnClickListener(this);
            findViewById(R.id.buttonBackspace).setOnClickListener(this);

        }


        /*
         * Assign all the buttons - to be used in the color change settings
         * This is sloppy I put it in after the fact - didnt fully think it through LOL
         * */
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        btnAdd = findViewById(R.id.buttonAdd);
        btnSub = findViewById(R.id.buttonSubtract);
        btnDiv = findViewById(R.id.buttonDivide);
        btnMult = findViewById(R.id.buttonMultiply);
        btnEqual = findViewById(R.id.buttonEquals);
        btnDec = findViewById(R.id.buttonDecimalPoint);
        btnNeg = findViewById(R.id.buttonToggleSign);
        btnMC = findViewById(R.id.buttonClearMemory);
        btnMPlus = findViewById(R.id.buttonAddToMemory);
        btnMSub = findViewById(R.id.buttonSubtractFromMemory);
        btnMR = findViewById(R.id.buttonRecallMemory);
        btnBS = findViewById(R.id.buttonBackspace);
        btnClear = (Button) findViewById(R.id.buttonClear);
        btnCos = findViewById(R.id.buttonCos);
        btnSin = findViewById(R.id.buttonSin);
        btnTan = findViewById(R.id.buttonTan);
        btnSquare = findViewById(R.id.buttonSquare);


        /*
         * Controls the backspace button
         * I made a separate button to control backspace
         * If you want to incorporate this into the clear button just change btnBS to btnClear
         * */
        btnBS.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calcDisplay.getText().length() > 0) {
                    CharSequence currentText = calcDisplay.getText();
                    calcDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    calcDisplay.setText("");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();
        if (DIGITS.contains(buttonPressed)) { // digit was pressed
            if (userInput) {
                if (buttonPressed.equals(".") && calcDisplay.getText().toString().contains(".")) {
                } else {
                    calcDisplay.append(buttonPressed);
                    stack.add(buttonPressed);
                }
            } else {
                if (buttonPressed.equals(".")) {
                    calcDisplay.setText(0 + buttonPressed);
                    stack.add(buttonPressed);
                } else {
                    calcDisplay.setText(buttonPressed);
                    stack.add(buttonPressed);
                }
                userInput = true;
            }
            historyView.setText(null);
        } else { // operation was pressed
            if (userInput) {
                CalcBrain.setOperand(Double.parseDouble(calcDisplay.getText().toString()));
                //historyView.append(CalcBrain.getOperand());
                //stack.add(CalcBrain.inputOp);
                userInput = false;
            }
            stack.add(CalcBrain.getOperand());
            CalcBrain.instantCalculatorOperation(buttonPressed);
            calcDisplay.setText(df.format(CalcBrain.getResult()));
            historyView.setText(null);
        }
        System.out.println(stack.toString());
        historyView.setText(null);
        historyView.setText(stack.toString());
    }

    /*
     * Uses the values from the settings activity to make specific changes
     * The values are stored inside shared preferences
     * I was going to do this with a ternary operator but for what ever reason it didnt work with button color options
     * */
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences result = getSharedPreferences("PREFS", MODE_PRIVATE);
        Boolean changeVal = result.getBoolean("switch1", false);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (changeVal) {
                button0.setTextColor(Color.RED);
                button1.setTextColor(Color.MAGENTA);
                button2.setTextColor(getResources().getColor(R.color.pastelBlue));
                button3.setTextColor(Color.MAGENTA);
                button4.setTextColor(getResources().getColor(R.color.pastelGreen));
                button5.setTextColor(Color.RED);
                button6.setTextColor(getResources().getColor(R.color.pastelGreen));
                button7.setTextColor(getResources().getColor(R.color.pastelYellow));
                button8.setTextColor(Color.BLUE);
                button9.setTextColor(getResources().getColor(R.color.pastelYellow));
                btnAdd.setTextColor(Color.RED);
                btnSub.setTextColor(Color.BLUE);
                btnDiv.setTextColor(getResources().getColor(R.color.pastelPurple));
                btnMult.setTextColor(getResources().getColor(R.color.pastelGreen));
                btnEqual.setTextColor(getResources().getColor(R.color.pastelBlue));
                btnDec.setTextColor(getResources().getColor(R.color.pastelGreen));
                btnNeg.setTextColor(getResources().getColor(R.color.pastelBlue));
                btnClear.setTextColor(getResources().getColor(R.color.pastelGreen));
                btnBS.setTextColor(Color.RED);
                btnMC.setTextColor(getResources().getColor(R.color.darkRed));
                btnMPlus.setTextColor(getResources().getColor(R.color.red));
                btnMSub.setTextColor(getResources().getColor(R.color.orange));
                btnMR.setTextColor(Color.YELLOW);
                btnCos.setTextColor(getResources().getColor(R.color.burntOrange));
                btnSin.setTextColor(getResources().getColor(R.color.megansNavy));
                btnTan.setTextColor(getResources().getColor(R.color.lilacPurple));
                btnSquare.setTextColor(getResources().getColor(R.color.Brown));
            } else {
                button0.setTextColor(Color.BLACK);
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.BLACK);
                button3.setTextColor(Color.BLACK);
                button4.setTextColor(Color.BLACK);
                button5.setTextColor(Color.BLACK);
                button6.setTextColor(Color.BLACK);
                button7.setTextColor(Color.BLACK);
                button8.setTextColor(Color.BLACK);
                button9.setTextColor(Color.BLACK);
                btnAdd.setTextColor(Color.BLACK);
                btnSub.setTextColor(Color.BLACK);
                btnDiv.setTextColor(Color.BLACK);
                btnMult.setTextColor(Color.BLACK);
                btnEqual.setTextColor(Color.BLACK);
                btnDec.setTextColor(Color.BLACK);
                btnNeg.setTextColor(Color.BLACK);
                btnClear.setTextColor(Color.BLACK);
                btnBS.setTextColor(Color.BLACK);
                btnMC.setTextColor(Color.BLACK);
                btnMPlus.setTextColor(Color.BLACK);
                btnMSub.setTextColor(Color.BLACK);
                btnMR.setTextColor(Color.BLACK);
                btnCos.setTextColor(Color.BLACK);
                btnSin.setTextColor(Color.BLACK);
                btnTan.setTextColor(Color.BLACK);
                btnSquare.setTextColor(Color.BLACK);
            }
        } else {

        }
        if (changeVal) {
            button0.setTextColor(Color.RED);
            button1.setTextColor(Color.MAGENTA);
            button2.setTextColor(getResources().getColor(R.color.pastelBlue));
            button3.setTextColor(Color.MAGENTA);
            button4.setTextColor(getResources().getColor(R.color.pastelGreen));
            button5.setTextColor(Color.RED);
            button6.setTextColor(getResources().getColor(R.color.pastelGreen));
            button7.setTextColor(getResources().getColor(R.color.pastelYellow));
            button8.setTextColor(Color.BLUE);
            button9.setTextColor(getResources().getColor(R.color.pastelYellow));
            btnAdd.setTextColor(Color.RED);
            btnSub.setTextColor(Color.BLUE);
            btnDiv.setTextColor(getResources().getColor(R.color.pastelPurple));
            btnMult.setTextColor(getResources().getColor(R.color.pastelGreen));
            btnEqual.setTextColor(getResources().getColor(R.color.pastelBlue));
            btnDec.setTextColor(getResources().getColor(R.color.pastelGreen));
            btnNeg.setTextColor(getResources().getColor(R.color.pastelBlue));
            btnClear.setTextColor(getResources().getColor(R.color.pastelGreen));
            btnBS.setTextColor(Color.RED);
            btnMC.setTextColor(getResources().getColor(R.color.darkRed));
            btnMPlus.setTextColor(getResources().getColor(R.color.red));
            btnMSub.setTextColor(getResources().getColor(R.color.orange));
            btnMR.setTextColor(Color.YELLOW);
        } else {
            button0.setTextColor(Color.BLACK);
            button1.setTextColor(Color.BLACK);
            button2.setTextColor(Color.BLACK);
            button3.setTextColor(Color.BLACK);
            button4.setTextColor(Color.BLACK);
            button5.setTextColor(Color.BLACK);
            button6.setTextColor(Color.BLACK);
            button7.setTextColor(Color.BLACK);
            button8.setTextColor(Color.BLACK);
            button9.setTextColor(Color.BLACK);
            btnAdd.setTextColor(Color.BLACK);
            btnSub.setTextColor(Color.BLACK);
            btnDiv.setTextColor(Color.BLACK);
            btnMult.setTextColor(Color.BLACK);
            btnEqual.setTextColor(Color.BLACK);
            btnDec.setTextColor(Color.BLACK);
            btnNeg.setTextColor(Color.BLACK);
            btnClear.setTextColor(Color.BLACK);
            btnBS.setTextColor(Color.BLACK);
            btnMC.setTextColor(Color.BLACK);
            btnMPlus.setTextColor(Color.BLACK);
            btnMSub.setTextColor(Color.BLACK);
            btnMR.setTextColor(Color.BLACK);
        }

    }

    /*
     * Saves the values stored in memory for when the screen orientation changes
     * */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // Save variables on screen orientation change
        outState.putDouble("OPERAND", CalcBrain.getResult());
        outState.putDouble("MEMORY", CalcBrain.getMemory());
    }

    /*
     * Saves the values stored in memory for when the screen orientation changes
     * */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState); // Restore variables on screen orientation change
        CalcBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
        CalcBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
        calcDisplay.setText(df.format(CalcBrain.getResult()));
    }
}
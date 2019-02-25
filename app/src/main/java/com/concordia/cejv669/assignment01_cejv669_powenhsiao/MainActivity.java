package com.concordia.cejv669.assignment01_cejv669_powenhsiao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Float mValue1, mValue2, TempValue, resultValue, tempResult;
    Boolean boolPlus = false, boolMinus = false, boolMultiple = false,
            boolDivided = false, boolEqual = false, boolArithmetic = false,
            boolTempResult = false;
    ArrayList<String> myHistoryList = new ArrayList<>();
    EditText result;
    TextView lblTempResult;
    String strTempResult, tempNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.basic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;

        switch (item.getItemId()) {
            case R.id.menu_about:
                i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.menu_history:
                Intent iHistory = new Intent(getApplicationContext(), HistoryCalculator.class);

                iHistory.putStringArrayListExtra("myArrayList", myHistoryList);

                startActivity(iHistory);
                break;
        }
        return true;
    }

    public void numberClick(int number) {
        result = findViewById(R.id.txtResult);
        String resultNumber = result.getText().toString();

        if (resultNumber.equals("0")) {
            result.setText(number + "");
        } else {
            if (boolArithmetic == true || boolEqual == true) {
                
                tempNumber = number + "";

                result.setText(tempNumber + "");
               // tempNumber = result.getText().toString();
                boolEqual = false;
            } else {

                result.setText(result.getText().toString() + number);
            }
        }
    }

    public void arithmeticClick(String symbol) {
        boolArithmetic = true;

        // in case click arithmetic after click equal
        if (boolEqual == true) {
            lblTempResult.setText(mValue1 + " " + symbol + " ");
            strTempResult = lblTempResult.getText().toString();
            boolTempResult = true;
        } else {
            // if use arithmetic more than 2 numbers
            if (boolTempResult == true) {
                mValue2 = Float.parseFloat(result.getText().toString());
                calulate(mValue1, mValue2);
                result.setText(tempResult + "");
                mValue1 = tempResult;

                lblTempResult.setText(strTempResult + mValue2 + " " + symbol + " ");
                strTempResult = lblTempResult.getText().toString();
            } else {
                mValue1 = Float.parseFloat(result.getText().toString());
                lblTempResult.setText(mValue1 + " " + symbol + " ");
                strTempResult = lblTempResult.getText().toString();
                boolTempResult = true;
            }
        }
    }

    public float calulate(float num1, float num2) {
        if (boolPlus == true) {
            tempResult = num1 + num2;
            result.setText(tempResult + "");
            boolPlus = false;
        }
        if (boolMinus == true) {
            tempResult = num1 - num2;
            result.setText(tempResult + "");
            boolMinus = false;
        }
        if (boolMultiple == true) {
            tempResult = num1 * num2;
            result.setText(tempResult + "");
            boolMultiple = false;
        }
        if (boolDivided == true) {
            tempResult = num1 / num2;
            result.setText(tempResult + "");
            boolDivided = false;
        }
        boolTempResult = true;

        return tempResult;
    }

    public void btnClick(View view) {
        lblTempResult = findViewById(R.id.lblTempResult);
        result = findViewById(R.id.txtResult);
        Button btDot = findViewById(R.id.btnDot);

        switch (view.getId()) {
            // for numbers and dots
            case (R.id.btnZero):
                numberClick(0);
                break;
            case (R.id.btnOne):
                numberClick(1);
                break;
            case (R.id.btnTwo):
                numberClick(2);
                break;
            case (R.id.btnThree):
                numberClick(3);
                break;
            case (R.id.btnFour):
                numberClick(4);
                break;
            case (R.id.btnFive):
                numberClick(5);
                break;
            case (R.id.btnSix):
                numberClick(6);
                break;
            case (R.id.btnSeven):
                numberClick(7);
                break;
            case (R.id.btnEight):
                numberClick(8);
                break;
            case (R.id.btnNine):
                numberClick(9);
                break;


            case (R.id.btnDot):
                result.setText(result.getText().toString() + btDot.getText().toString());
                break;

            // for the calculator

            case (R.id.btnPlus):
                arithmeticClick("+");
                boolPlus = true;
                boolMinus = false;
                boolMultiple = false;
                boolDivided = false;
                break;
            case (R.id.btnMinus):
                arithmeticClick("-");
                boolMinus = true;
                boolPlus = false;
                boolMultiple = false;
                boolDivided = false;
                break;
            case (R.id.btnMultiple):
                arithmeticClick("*");
                boolMultiple = true;
                boolPlus = false;
                boolMinus = false;
                boolDivided = false;
                break;
            case (R.id.btnDivided):
                arithmeticClick("/");
                boolDivided = true;
                boolPlus = false;
                boolMinus = false;
                boolMultiple = false;
                break;


            case (R.id.btnEqual):
                boolEqual = true;
                mValue2 = Float.parseFloat(result.getText().toString());

                calulate(mValue1, mValue2);

                resultValue = tempResult;

                boolArithmetic = false;
                boolTempResult = false;

                myHistoryList.add(String.valueOf(lblTempResult.getText().toString() + mValue2 + " = " + resultValue + "\n"));

                lblTempResult.setText("");
                break;

            // extra button

            case (R.id.btnClear):
                lblTempResult.setText(null);
                result.setText(0 + "");
                break;
            case (R.id.btnPlusMinus):
                TempValue = -Float.parseFloat(result.getText().toString());
                result.setText(TempValue.toString());
                break;
            case (R.id.btnPercentage):
                TempValue = Float.parseFloat(result.getText().toString()) / 100;
                result.setText(TempValue.toString());
                break;
            case (R.id.btnDelete):
                result.setText(0 + "");
                break;
        }

    }
}

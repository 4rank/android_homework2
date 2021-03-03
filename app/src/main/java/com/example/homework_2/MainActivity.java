package com.example.homework_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView oper;
    TextView res;
    TextView main;
    TextView ts;
    EditText op1;
    EditText op2;
    Button sum;
    Button can;
    Button mult;
    Button div;
    Button end;
    Button sqr;
    Button step;
    Button clear;
    Button but_ts;
    RadioGroup r_g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getApplication().setTheme(R.style.Theme_Homework_2_dark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main=findViewById(R.id.text_main);
        ts=findViewById(R.id.text_size);
        oper = findViewById(R.id.operation);
        res = findViewById(R.id.result);
        op1 = findViewById(R.id.editText_oper1);
        op2 = findViewById(R.id.editText_oper2);
        sum = findViewById(R.id.button_sum);
        can = findViewById(R.id.button_can);
        mult = findViewById(R.id.button_mult);
        div = findViewById(R.id.button_div);
        end = findViewById(R.id.button_end);
        sqr = findViewById(R.id.button_sqr);
        step = findViewById(R.id.button_step);
        clear = findViewById(R.id.button_clear);
        r_g = findViewById(R.id.radioGroup);
        but_ts=findViewById(R.id.button_ts);
        sum.setOnClickListener(this);
        can.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);
        end.setOnClickListener(this);
        sqr.setOnClickListener(this);
        step.setOnClickListener(this);
        but_ts.setOnClickListener(this);
        clear.setOnClickListener(this);
        op1.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                return true;
            }
            return false;
        });
        op2.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                return true;
            }
            return false;
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_ts) {
            int checked = r_g.getCheckedRadioButtonId();
            if (checked == R.id.radio_1) {
                multTextSize(3);
            }
            if (checked == R.id.radio_2) {
                multTextSize(2);
            }
            if (checked == R.id.radio_3) {
                multTextSize(1);
            }
        }
        if (v.getId() == R.id.button_clear) {
            oper.setText("");
            res.setText("");
            op1.getText().clear();
            op2.getText().clear();
            op1.requestFocus();
        }
        if (TextUtils.isEmpty(op1.getText().toString())) {
            return;
        }
        double oper1 = Double.parseDouble(op1.getText().toString());
        if (v.getId() == R.id.button_sum) {
            oper.setText("+");
            op2.requestFocus();
        }
        if (v.getId() == R.id.button_can) {
            oper.setText("-");
            op2.requestFocus();
        }
        if (v.getId() == R.id.button_mult) {
            oper.setText("*");
            op2.requestFocus();
        }
        if (v.getId() == R.id.button_div) {
            oper.setText("/");
            op2.requestFocus();
        }
        if (v.getId() == R.id.button_sqr) {
            double result = Math.sqrt(oper1);
            oper.setText("");
            op2.getText().clear();
            res.setText("" + result);
        }
        if (v.getId() == R.id.button_step) {
            double result = oper1*oper1;
            oper.setText("");
            op2.getText().clear();
            res.setText("" + result);
        }
        if (TextUtils.isEmpty(op2.getText().toString())) {
            return;
        }
        double oper2 = Double.parseDouble(op2.getText().toString());
        if (v.getId() == R.id.button_end) {
            double result = 0.0;
            String operation = (String) oper.getText();
            if (TextUtils.isEmpty(oper.getText().toString())) {
                return;
            }
            if (operation.equals("+")) {
                result = oper1 + oper2;
            }
            if (operation.equals("-")) {
                result = oper1 - oper2;
            }
            if (operation.equals("*")) {
                result = oper1 * oper2;
            }
            if (operation.equals("/")) {
                result = oper1 / oper2;
            }
            res.setText("" + result);
        }
    }
    private void multTextSize(float mult) {
        float sizeDefault = 10;
        float sizeMult = sizeDefault * mult;
        main.setTextSize(sizeMult);
        ts.setTextSize(sizeMult);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }
}

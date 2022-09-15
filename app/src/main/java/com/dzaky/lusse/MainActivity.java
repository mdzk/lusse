package com.dzaky.lusse;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtAlas, edtTinggi;
    private Button btnCalculate;
    private TextView tvResult;
    private TextView titleDescription;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        getWindow().setDecorFitsSystemWindows(false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAlas = findViewById(R.id.edt_alas);
        edtTinggi = findViewById(R.id.edt_tinggi);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        titleDescription = findViewById(R.id.title_description);
        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputAlas = edtAlas.getText().toString().trim();
            String inputTinggi = edtTinggi.getText().toString().trim();

            boolean isEmptyFields = false;

            if(TextUtils.isEmpty(inputTinggi)) {
                isEmptyFields = true;
                edtTinggi.setError("Field ini tidak boleh kosong");
            }

            if(TextUtils.isEmpty(inputAlas)) {
                isEmptyFields = true;
                edtAlas.setError("Field ini tidak boleh kosong");
            }

            if(!isEmptyFields) {
                Double luas = Double.parseDouble(inputAlas)
                        * Double.parseDouble(inputTinggi);

                tvResult.setText(String.valueOf(luas));
                if(luas > 0) {
                    titleDescription.setText(String.valueOf("Hasil luas jajar genjang"));
                }
                if(getCurrentFocus() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }

        }
    }
}
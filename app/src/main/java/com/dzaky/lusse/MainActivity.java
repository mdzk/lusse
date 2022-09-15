package com.dzaky.lusse;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtAlas, edtTinggi;
    private Button btnCalculate;
    private ImageButton btnResetHitung;
    private TextView titleDescription, tvResult;
    private Double luas ;

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
        btnResetHitung = findViewById(R.id.btn_reset_hitung);
        tvResult = findViewById(R.id.tv_result);
        titleDescription = findViewById(R.id.title_description);

        btnResetHitung.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_reset_hitung) {
            edtTinggi.getText().clear();
            edtAlas.getText().clear();
            tvResult.setText("");
            titleDescription.setText("Selamat datang di lusse! \uD83D\uDC4B");
        }

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
                luas = Double.parseDouble(inputAlas)
                        * Double.parseDouble(inputTinggi);

                tvResult.setText(String.valueOf(luas));
                if(luas > 0) {
                    titleDescription.setText("Hasil luas jajar genjang");
                }
                if(getCurrentFocus() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }

        }
    }
}
package com.dzaky.lusse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtSisiA, edtSisiB, edtTinggi;
    private Button btnCalculate;
    private TextView tvResult;
    private TextView hasilLuasTrapesium;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSisiA = findViewById(R.id.edt_sisia);
        edtSisiB = findViewById(R.id.edt_sisib);
        edtTinggi = findViewById(R.id.edt_tinggi);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        hasilLuasTrapesium = findViewById(R.id.hasilLuasTrapesium);
        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputTinggi = edtTinggi.getText().toString().trim();
            String inputSisiA = edtSisiA.getText().toString().trim();
            String inputSisiB = edtSisiB.getText().toString().trim();

            boolean isEmptyFields = false;

            if(TextUtils.isEmpty(inputTinggi)) {
                isEmptyFields = true;
                edtTinggi.setError("Field ini tidak boleh kosong");
            }

            if(TextUtils.isEmpty(inputSisiA)) {
                isEmptyFields = true;
                edtSisiA.setError("Field ini tidak boleh kosong");
            }

            if(TextUtils.isEmpty(inputSisiB)) {
                isEmptyFields = true;
                edtSisiB.setError("Field ini tidak boleh kosong");
            }

            if(!isEmptyFields) {
                Double jumlahSisi = Double.parseDouble(inputSisiA)
                        + Double.parseDouble(inputSisiB);
                Double luas = Double.parseDouble(String.valueOf(jumlahSisi))
                        * Double.parseDouble(inputTinggi) / 2;

                tvResult.setText(String.valueOf(luas));
                if(luas > 0) {
                    hasilLuasTrapesium.setText(String.valueOf("Hasil luas trapesium"));
                }
            }

        }
    }
}
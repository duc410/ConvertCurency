package com.example.convertcurency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

enum Currency {
    CNY(0), RUB(1), USD(2),EUR(3),JPY(4),VND(5);
    private final int value;
    private Currency(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


public class MainActivity extends AppCompatActivity {
    double[][] convertExchange ={
            {1,8.48,0.14,0.14,19.60,3484.66},
            {0.12,1,0.017,0.017,2.31,411.03},
            {7.12,60.30,1,0.96,139.46,24785.00},
            {7.39,62.56,1.04,1,144.85,25717.36},
            {0.051,0.43,0.0072,0.0069,1,177.60},
            {0.00029,0.0024,0.000040,0.000039,0.0056,1},
    };

    Button chooseCurrency,convert,clear;
    TextView textViewFrom,textViewTo;
    EditText editTextFrom,editTextTo;
    String fromCurrency,toCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseCurrency=findViewById(R.id.btnChooseCurrency);
        convert=findViewById(R.id.btnConvert);
        clear=findViewById(R.id.btnClear);
        textViewFrom=findViewById(R.id.textViewFrom);
        textViewTo=findViewById(R.id.textViewTo);
        editTextFrom=findViewById(R.id.editTextFrom);
        editTextTo=findViewById(R.id.editTextTo);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd!=null){
            fromCurrency=bd.getString("From");
            toCurrency=bd.getString("To");
            textViewFrom.setText("From "+fromCurrency);
            textViewTo.setText("To "+toCurrency);
        }else{
            fromCurrency="USD";
            toCurrency="VND";
            textViewFrom.setText("From "+fromCurrency);
            textViewTo.setText("To "+toCurrency);
        }


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from=editTextFrom.getText().toString();
                if(!from.isEmpty()){
                    double input=Double.parseDouble(from)  ;
                    int tmp1=Currency.valueOf(fromCurrency).getValue();
                    int tmp2=Currency.valueOf(toCurrency).getValue();
                    double result=input*convertExchange[tmp1][tmp2];
                    editTextTo.setText(String.valueOf(result));
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextFrom.setText("");
                editTextTo.setText("");
            }
        });

        chooseCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ChooseCurrencyActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        editTextFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //For Interface
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //For Interface
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String from=editTextFrom.getText().toString();
                if(!from.isEmpty()){
                    double input=Double.parseDouble(from)  ;
                    int tmp1=Currency.valueOf(fromCurrency).getValue();
                    int tmp2=Currency.valueOf(toCurrency).getValue();
                    double result=input*convertExchange[tmp1][tmp2];
                    editTextTo.setText(String.valueOf(result));
                }
            }
        });
    }
}
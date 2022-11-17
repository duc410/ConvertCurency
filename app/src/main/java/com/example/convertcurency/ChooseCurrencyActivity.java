package com.example.convertcurency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChooseCurrencyActivity extends AppCompatActivity {
    RadioGroup radioFromGroup, radioToGroup;
    Button btnOk,btnCancel;

    RadioButton radioFromButton,radioToButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_curency_acivity);

        radioFromGroup =findViewById(R.id.radioFrom);
        radioToGroup =findViewById(R.id.radioTo);
        btnOk=findViewById(R.id.btnOk);
        btnCancel=findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromId= radioFromGroup.getCheckedRadioButtonId();
                int toId= radioToGroup.getCheckedRadioButtonId();
                if(fromId!=-1&&toId!=-1){
                    String from,to;
                    radioFromButton=findViewById(fromId);
                    radioToButton=findViewById(toId);
                    from=radioFromButton.getText().toString();
                    to=radioToButton.getText().toString();
                    Intent intent=new Intent(ChooseCurrencyActivity.this, MainActivity.class);
                    intent.putExtra("From",from);
                    intent.putExtra("To",to);
                    ChooseCurrencyActivity.this.startActivity(intent);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioFromGroup.clearCheck();
                radioToGroup.clearCheck();
            }
        });
    }
}
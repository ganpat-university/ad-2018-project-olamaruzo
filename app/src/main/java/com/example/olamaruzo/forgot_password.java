package com.example.olamaruzo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgot_password extends AppCompatActivity {

    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        done=(Button)findViewById(R.id.button5);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text1=findViewById(R.id.editText5);
                if(text1.getText().toString().trim().length()<10 ||
                        (text1.getText().toString().charAt(0)!='9' && text1.getText().toString().charAt(0)!='8' &&
                                text1.getText().toString().charAt(0)!='7' && text1.getText().toString().charAt(0)!='6')){
                    Toast.makeText(forgot_password.this, "Please Enter a proper contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

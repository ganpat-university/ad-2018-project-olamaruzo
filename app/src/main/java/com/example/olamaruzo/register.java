package com.example.olamaruzo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class register extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button=(Button)findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check=findViewById(R.id.checkBox2);
                if(check.isChecked()){
                    Intent intent=new Intent(register.this,registration.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(register.this, "You need to agree to all terms and conditions before registration", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

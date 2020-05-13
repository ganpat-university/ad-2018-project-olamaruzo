
package com.example.olamaruzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class registration extends AppCompatActivity {
    Button submit,clear;
    RadioGroup designation;
    RadioButton selected;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        submit=(Button)findViewById(R.id.button2);
        clear=(Button)findViewById(R.id.button3);
        designation=(RadioGroup)findViewById(R.id.radioGroup);

        db= FirebaseFirestore.getInstance();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text1=findViewById(R.id.editText9);
                EditText text2=findViewById(R.id.editText10);
                EditText text3=findViewById(R.id.editText4);
                EditText text4=findViewById(R.id.editText7);
                EditText text5=findViewById(R.id.editText3);
                EditText text6=findViewById(R.id.editText6);
                EditText text7=findViewById(R.id.editText8);
                EditText text8=findViewById(R.id.radioGroup);
                if(text3.getText().toString().trim().length()==0){
                    Toast.makeText(registration.this,"First name cannot be left blank",Toast.LENGTH_SHORT).show();
                }
                else if(text4.getText().toString().trim().length()==0){
                    Toast.makeText(registration.this, "Last name cannot be left blank", Toast.LENGTH_SHORT).show();
                }
                else if(text5.getText().toString().trim().length()<10 ||
                        (text5.getText().toString().charAt(0)!='9' && text5.getText().toString().charAt(0)!='8' &&
                                text5.getText().toString().charAt(0)!='7' && text5.getText().toString().charAt(0)!='6')){
                    Toast.makeText(registration.this, "Please Enter a proper contact number", Toast.LENGTH_SHORT).show();
                }
                else if(!text6.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
                    Toast.makeText(registration.this,"Please enter a valid e-mail id",Toast.LENGTH_SHORT).show();
                }
                else if(text7.getText().toString().trim().length()<12){
                    Toast.makeText(registration.this,"Please enter proper aadhar number",Toast.LENGTH_SHORT).show();
                }
                else if(text1.getText().toString().trim().length()==0 || text2.getText().toString().trim().length()==0){
                    Toast.makeText(registration.this, "Password fields cannot be left blank", Toast.LENGTH_SHORT).show();
                }
                else if(!text1.getText().toString().equals(text2.getText().toString())){
                    Toast.makeText(registration.this, "Please enter password properly", Toast.LENGTH_SHORT).show();
                }
                else if(designation.getCheckedRadioButtonId()==-1){
                    //Toast.makeText(registration.this,"Please select your designation",Toast.LENGTH_SHORT).show();
                    Toast.makeText(registration.this,"Please select your designation",Toast.LENGTH_SHORT).show();
                }
                else{
                    selected=(RadioButton)findViewById(designation.getCheckedRadioButtonId());
                    Student student = new Student(text1.getText().toString(),text2.getText().toString(),text3.getText().toString(),
                            text4.getText().toString(),text5.getText().toString(),text6.getText().toString(),text7.getText().toString(),
                            text8.getText().toString());
                    db.collection("StudentInformation").document(text1.getText().toString()).set(student)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getApplicationContext(),"Data Stored Successfully",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        {
                                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
            }

        });




        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text1=findViewById(R.id.editText9);
                EditText text2=findViewById(R.id.editText10);
                EditText text3=findViewById(R.id.editText4);
                EditText text4=findViewById(R.id.editText7);
                EditText text5=findViewById(R.id.editText3);
                EditText text6=findViewById(R.id.editText6);
                EditText text7=findViewById(R.id.editText8);
                text1.getText().clear();
                text2.getText().clear();
                text3.getText().clear();
                text4.getText().clear();
                text5.getText().clear();
                text6.getText().clear();
                text7.getText().clear();
                designation.clearCheck();
            }
        });
    }

}

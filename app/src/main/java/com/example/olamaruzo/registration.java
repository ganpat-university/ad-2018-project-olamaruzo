
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
                EditText pass=findViewById(R.id.editText9);
                EditText cnfpass=findViewById(R.id.editText10);
                EditText fname=findViewById(R.id.editText4);
                EditText lname=findViewById(R.id.editText7);
                EditText contact=findViewById(R.id.editText3);
                EditText email=findViewById(R.id.editText6);
                EditText aadhar=findViewById(R.id.editText8);
                designation=(RadioGroup) findViewById(R.id.radioGroup);
                if(fname.getText().toString().trim().length()==0){
                    Toast.makeText(registration.this,"First name cannot be left blank",Toast.LENGTH_SHORT).show();
                }
                else if(lname.getText().toString().trim().length()==0){
                    Toast.makeText(registration.this, "Last name cannot be left blank", Toast.LENGTH_SHORT).show();
                }
                else if(contact.getText().toString().trim().length()<10 ||
                        (contact.getText().toString().charAt(0)!='9' && contact.getText().toString().charAt(0)!='8' &&
                                contact.getText().toString().charAt(0)!='7' && contact.getText().toString().charAt(0)!='6')){
                    Toast.makeText(registration.this, "Please Enter a proper contact number", Toast.LENGTH_SHORT).show();
                }
                else if(!email.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
                    Toast.makeText(registration.this,"Please enter a valid e-mail id",Toast.LENGTH_SHORT).show();
                }
                else if(aadhar.getText().toString().trim().length()<12){
                    Toast.makeText(registration.this,"Please enter proper aadhar number",Toast.LENGTH_SHORT).show();
                }
                else if(pass.getText().toString().trim().length()==0 || cnfpass.getText().toString().trim().length()==0){
                    Toast.makeText(registration.this, "Password fields cannot be left blank", Toast.LENGTH_SHORT).show();
                }
                else if(!pass.getText().toString().equals(cnfpass.getText().toString())){
                    Toast.makeText(registration.this, "Please enter password properly", Toast.LENGTH_SHORT).show();
                }
                else if(designation.getCheckedRadioButtonId()==-1){
                    //Toast.makeText(registration.this,"Please select your designation",Toast.LENGTH_SHORT).show();
                    Toast.makeText(registration.this,"Please select your designation",Toast.LENGTH_SHORT).show();
                }
                else{
                    selected=(RadioButton)findViewById(designation.getCheckedRadioButtonId());
                    UserDataModel carrier = new UserDataModel(fname.getText().toString(),lname.getText().toString(),
                            contact.getText().toString(),email.getText().toString(),aadhar.getText().toString(),pass.getText().toString(),
                            selected.getText().toString());
                    db.collection("CarrierInformation").document(fname.getText().toString()+" "+lname.getText().toString()+" "+contact.getText().toString()+" ").set(carrier)
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
                EditText pass=findViewById(R.id.editText9);
                EditText cnfpass=findViewById(R.id.editText10);
                EditText fname=findViewById(R.id.editText4);
                EditText lname=findViewById(R.id.editText7);
                EditText contact=findViewById(R.id.editText3);
                EditText email=findViewById(R.id.editText6);
                EditText aadhar=findViewById(R.id.editText8);
                pass.getText().clear();
                cnfpass.getText().clear();
                fname.getText().clear();
                lname.getText().clear();
                contact.getText().clear();
                email.getText().clear();
                aadhar.getText().clear();
                designation.clearCheck();
            }
        });
    }

}

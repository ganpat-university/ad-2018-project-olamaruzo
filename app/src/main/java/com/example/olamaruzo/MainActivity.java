package com.example.olamaruzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class MainActivity extends AppCompatActivity {

    Button login;
    TextView reg,fp;
    FirebaseFirestore db;
    public boolean isvalid(String str){
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        Matcher m = p.matcher(str);
        return (m.find() && m.group().equals(str));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.button);
        fp=findViewById(R.id.textView4);
        reg=findViewById(R.id.textView2);
        db=FirebaseFirestore.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text1=findViewById(R.id.editText);
                EditText text2=findViewById(R.id.editText2);
                if(!text1.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") && !isvalid(text1.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter a proper Contact number or E-mail ID", Toast.LENGTH_SHORT).show();
                }
                else if(text2.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this, "Password cannot be left blank", Toast.LENGTH_SHORT).show();
                }
                else{
                    UserDataModel user=new UserDataModel(text1.getText().toString(),text2.getText().toString());
                    db.collection("users").document(text1.getText().toString())
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                }
            }
        });
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this,forgot_password.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
    }
}
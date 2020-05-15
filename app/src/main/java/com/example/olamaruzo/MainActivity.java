package com.example.olamaruzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class MainActivity extends AppCompatActivity {

    public static final String MSG = "com.android.olamaruzo.MainActivity";
    SharedPreferences pref;
    Button login;
    TextView reg,fp;
    FirebaseFirestore db;
    // String ab,bc;
    public boolean isvalid(String str){
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        Matcher m = p.matcher(str);
        return (m.find() && m.group().equals(str));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref=getSharedPreferences("Login",MODE_PRIVATE);
        if(pref.contains("fname") && pref.contains("Email") && pref.contains("Cost")){
            startActivity(new Intent(MainActivity.this,home_page.class));
        }
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
                    //Authentication Code Here
                    Intent intent = new Intent(MainActivity.this, home_page.class);
                    SharedPreferences.Editor edit= pref.edit();
                    //Please use fetched fname instead of "testing user" and cost should also be fetched
                    edit.putString("fname","testing user");
                    edit.putString("Email","test123@gmail.com");
                    edit.putString("Cost","0");
                    edit.commit();
                    startActivity(intent);
                    /* ab = text1.getText().toString();
                    bc = text2.getText().toString();
                    CollectionReference def = (CollectionReference) FirebaseFirestore.getInstance().collection("CarrierInformation").whereEqualTo("pass",bc);
                    def.get().addOnCompleteListener(new OnCompleteListener<QueryDocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {
                                QueryDocumentSnapshot document1 = task.getResult();
                                if(document1.exists())
                                {
                                    CollectionReference ref = (CollectionReference) FirebaseFirestore.getInstance().collection("CarrierInformation").whereEqualTo("email",ab);

                                    ref.get().addOnCompleteListener(new OnCompleteListener<QueryDocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if(task.isSuccessful())
                                            {
                                                QueryDocumentSnapshot document = task.getResult();
                                                if(document.exists())
                                                {
                                                    Toast.makeText(getApplicationContext(),"You're a Registered User", Toast.LENGTH_LONG);
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }); *\



                    /*UserDataModel user=new UserDataModel(text1.getText().toString(),text2.getText().toString());
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
                            });*/

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
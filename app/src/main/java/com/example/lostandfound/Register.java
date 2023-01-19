package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
   private EditText editTextTextPersonName,editTextNumber3,editTextTextPersonName2,editTextNumber2,editTextTextPersonName3,editTextPhone,editTextTextEmailAddress2;
    Button button3;
    private ProgressDialog Progress;

    private FirebaseAuth mAuth;

    private DatabaseReference databaseRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2=findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3=findViewById(R.id.editTextTextPersonName3);
        editTextNumber2=findViewById(R.id.editTextNumber2);
        editTextNumber3=findViewById(R.id.editTextNumber3);
        editTextPhone=findViewById(R.id.editTextPhone);
        editTextTextEmailAddress2=findViewById(R.id.editTextTextEmailAddress2);
        button3=findViewById(R.id.button3);
        Progress = new ProgressDialog(this);
        databaseRef = FirebaseDatabase.getInstance().getReference();

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String TAG = "marakhacchi";
                String email =  editTextTextEmailAddress2.getText().toString().trim();
                String number =  editTextNumber2.getText().toString().trim();
                String number2 =  editTextNumber3.getText().toString().trim();
                final String name = editTextTextPersonName.getText().toString().trim();
                final String sname = editTextTextPersonName2.getText().toString().trim();
                final String address = editTextTextPersonName3.getText().toString().trim();
                final String phone =editTextPhone.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Progress.setMessage("registering...");
                Progress.show();
                if (number.length() ==7) {
                    Toast.makeText(getApplicationContext(), "Password is too short. Enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    Progress.dismiss();
                    return;
                }
                if (!number.contentEquals(editTextNumber2.getText())){
                    Toast.makeText(getApplicationContext(), "password Mismatch!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!TextUtils.isEmpty(email ) && !TextUtils.isEmpty(number)) {
                    mAuth.createUserWithEmailAndPassword(email, number).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Progress.dismiss();
                                FirebaseUser user1 = mAuth.getCurrentUser();
                                UserInformation user2 = new UserInformation(name, sname, address, phone, "","");
                                databaseRef.child("Users").child(user1.getUid()).setValue(user2);


                                sendToMain();
                            }
                            else {

                                Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                //toastmessage("Registration failed. Please Try again.");
                            }

                        }
                    });
                }
            }

        });
    }
    //sending to main
    public void sendToMain(){
        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
        Intent mainIntent = new Intent(Register.this,Dashboard.class);
        startActivity(mainIntent);
        finish();
    }
}

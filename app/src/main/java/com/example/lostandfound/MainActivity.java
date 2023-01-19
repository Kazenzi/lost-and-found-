package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button button,button2;
    EditText editTextTextEmailAddress,editTextNumber;
    private FirebaseAuth fireAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fireAuth = FirebaseAuth.getInstance();

        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        editTextNumber=findViewById(R.id.editTextNumber);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        progressDialog = new ProgressDialog(this);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
                String email_login = editTextTextEmailAddress.getText().toString();
                String pass_login = editTextNumber.getText().toString();
                if(!TextUtils.isEmpty(email_login) && !TextUtils.isEmpty(pass_login)){         //checking if elements are empty
                    fireAuth.signInWithEmailAndPassword(email_login, pass_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendToUI();
                            }else {
                                progressDialog.dismiss();
                                toastmessage("Can't Login. TRY AGAIN WITH CORRECT INFORMATION");
                            }
                        }
                    });
                }
                else{
                    toastmessage("Please Enter Email and Password");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendtoRegister();
            }
        });
    }

    private void sendToUI() {

        Intent mainIntent = new Intent(MainActivity.this, Dashboard.class);
        startActivity(mainIntent);
        finish();
    }
    public void sendtoRegister(){
        Intent regIntent = new Intent(MainActivity.this, Register.class);
        startActivity(regIntent);
    }

    //toastin message
    public  void toastmessage(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

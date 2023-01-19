package com.example.lostandfound;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
  private   EditText editTextTextPersonName,editTextTextPersonName2,editTextNumber2,editTextTextPersonName3,editTextPhone,editTextTextEmailAddress2;
    Button button3 ,button,button2 ;
    private ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    firebaseAuth=FirebaseAuth.getInstance();

    editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
    editTextTextPersonName2=findViewById(R.id.editTextTextPersonName2);
    editTextTextPersonName3=findViewById(R.id.editTextTextPersonName3);
    editTextNumber2=findViewById(R.id.editTextNumber2);
    editTextPhone=findViewById(R.id.editTextPhone);
    editTextTextEmailAddress2=findViewById(R.id.editTextTextEmailAddress2);
    button3=findViewById(R.id.button3);

    ProgressDialog=new ProgressDialog(this);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
  }
}

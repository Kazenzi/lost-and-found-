package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addlostitem extends AppCompatActivity {
    EditText editTextTextPersonName4,editTextTextPersonName5,editTextTextMultiLine;
    Button button4;
   // private DatabaseReference mDatabase;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private  DatabaseReference root=db.getReference().child("Users");
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlostitem);
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        editTextTextPersonName4=findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5=findViewById(R.id.editTextTextPersonName5);
        editTextTextMultiLine=findViewById(R.id.editTextTextMultiLine);
        button4=findViewById(R.id.button4);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemname=editTextTextPersonName4.getText().toString();
                String place=editTextTextPersonName5.getText().toString();
                String des=editTextTextMultiLine.getText().toString();
                //root.child("01").setValue(itemname);
                HashMap<String,String> UserMap=new HashMap<>();
                UserMap.put("itemname",itemname);
                UserMap.put("place",place);
                UserMap.put("des",des);

                root.push().setValue(UserMap);
                Intent intent=new Intent(addlostitem.this,Dashboard.class);
                startActivity(intent);



            }
        });


    }

}
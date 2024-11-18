package com.example.androidhealthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnAddToCart,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackageName=findViewById(R.id.textViewLDPackageName);
        tvTotalCost=findViewById(R.id.textViewLDTotalCost);
        edDetails=findViewById(R.id.editTextLDTextMultiLine);
        btnAddToCart=findViewById(R.id.buttonLDAddToCart);
        btnBack=findViewById(R.id.buttonLDBack);

        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        tvTotalCost.setText(intent.getStringExtra("text2"));
        edDetails.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String Username= sharedPreferences.getString("Username","").toString();
                String product= tvPackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());


                Database db=new Database(getApplicationContext(),"androidhealthcareapp",null,1);

                if(db.checkCart(Username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addCart(Username,product,price,"Lab");
                    Toast.makeText(getApplicationContext(),"Record Inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });
    }
}
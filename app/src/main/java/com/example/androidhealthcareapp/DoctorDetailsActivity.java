package com.example.androidhealthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1=
            {
                    {"Doctor Name: Pardeep Jain", "Hospital Address: Gandhi Road", "Exp: 10yrs", "Mobile No:9898989890", "600"},
                    {"Doctor Name: Dhiraan", "Hospital Address: Vishal Road", "Exp: 15yrs", "Mobile No: 7890989898", "900"},
                    {"Doctor Name: Arvind Jain", "Hospital Address: Baraut", "Exp: 8yrs", "Mobile No:8898989898", "300"},
                    {"Doctor Name: Deepak Sharma", "Hospital Address: Modinagar","Exp: 5yrs", "Mobile No: 9898000000", "500"},
                    {"Doctor Name: Ashok Dakter", "Hospital Address: Delhi", "Exp: 6yrs", "Mobile No:7798989898", "800"},
            };
    private String[][] doctor_details2=
            {
                    {"Doctor Name: Parul Jain", "Hospital Address: Gandhinagar", "Exp: 10yrs", "Mobile No:9898989890", "600"},
                    {"Doctor Name: Dhruve", "Hospital Address: baghpat", "Exp: 15yrs", "Mobile No: 7890989898", "900"},
                    {"Doctor Name: Ashok Jain", "Hospital Address: Baraut", "Exp: 8yrs", "Mobile No:8898989898", "300"},
                    {"Doctor Name: Deepak Sharma", "Hospital Address: Meerut","Exp: 5yrs", "Mobile No: 9898000000", "500"},
                    {"Doctor Name: Amit jain", "Hospital Address: Delhi", "Exp: 6yrs", "Mobile No:7798989898", "800"},
            };
    private String[][] doctor_details3=
            {
                    {"Doctor Name: Mamta Jain", "Hospital Address: GandhiGround", "Exp: 10yrs", "Mobile No:9898989890", "600"},
                    {"Doctor Name: Deepak kumar", "Hospital Address: mandi Road", "Exp: 15yrs", "Mobile No: 7890989898", "900"},
                    {"Doctor Name: Arvind Kumar", "Hospital Address: Badot", "Exp: 8yrs", "Mobile No:8898989898", "300"},
                    {"Doctor Name: Deepak ram", "Hospital Address: sujnagar","Exp: 5yrs", "Mobile No: 9898000000", "500"},
                    {"Doctor Name: Kunal", "Hospital Address: NewDelhi", "Exp: 6yrs", "Mobile No:7798989898", "800"},
            };
    private String[][] doctor_details4=
            {
                    {"Doctor Name: Priya", "Hospital Address: mumbai", "Exp: 10yrs", "Mobile No:9898989890", "600"},
                    {"Doctor Name: Dhin", "Hospital Address: Gurana Road", "Exp: 15yrs", "Mobile No: 7890989898", "900"},
                    {"Doctor Name: Payal Jain", "Hospital Address: Baraut", "Exp: 8yrs", "Mobile No:8898989898", "300"},
                    {"Doctor Name: Kartik Sharma", "Hospital Address: Modinagar","Exp: 5yrs", "Mobile No: 9898000000", "500"},
                    {"Doctor Name: Ashok Ananda", "Hospital Address: Muradnagar", "Exp: 6yrs", "Mobile No:7798989898", "800"},
            };
    private String[][] doctor_details5=
            {
                    {"Doctor Name: Pankaj Jain", "Hospital Address: Namgla", "Exp: 10yrs", "Mobile No:9898989890", "600"},
                    {"Doctor Name: Diya matre", "Hospital Address: VishalMart", "Exp: 15yrs", "Mobile No: 7890989898", "900"},
                    {"Doctor Name: Sarthak Jain", "Hospital Address: Baraut", "Exp: 8yrs", "Mobile No:8898989898", "300"},
                    {"Doctor Name: Deepu Sharma", "Hospital Address: Goa","Exp: 5yrs", "Mobile No: 9898000000", "500"},
                    {"Doctor Name: k.raman ", "Hospital Address: Mandi", "Exp: 6yrs", "Mobile No:7798989898", "800"},
            };
    TextView tv;
    Button btn;
    String[][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv= findViewById(R.id.textView_PackageName);
        btn=findViewById(R.id.buttonBack);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physicians")==0) {
            doctor_details = doctor_details1;
        }
        else
        if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
        list =new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            item=new HashMap<String, String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst=findViewById(R.id.editTextTextMultiLine);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}
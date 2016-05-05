package com.example.rjt.baseballcards;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button search;
    private EditText date;
    //private EditText grade;
    private EditText team;
    private EditText name;
    private Spinner spinner;
    String grade;
    String fields;
    boolean chck;
    boolean andCheck;
    String[] Grades= new String[]
            {
                  "",  "fair", "near mint", "mint","good", "poor"
            };

    // private Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.search);
        date = (EditText) findViewById(R.id.date);
        //grade = (EditText) findViewById(R.id.gradetype);
        team = (EditText) findViewById(R.id.team);
        name = (EditText) findViewById(R.id.name);
        spinner=(Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String>adapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, Grades);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);





        chck = true;
        andCheck = false;
        //buy=(Button)findViewById(R.id.buy);
        search.setOnClickListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {
        switch (position) {
            case 0:
                grade="";
                break;
            case 1:
                grade="fair";
                break;
            case 2:
                grade="near mint";
                break;
            case 3:
                 grade="mint";
                  break;
            case 4:
                grade="good";
                break;
            case 5:
                grade="poor";
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    public void onClick(View v) {

        String and = "";

        String query = "SELECT * from card_detail WHERE ";

        if (v == search) {


            //String fquery;
            if (date.getText().toString().length() > 0) {
                chck = true;
                fields = date.getText().toString();
                query = query.concat(and + "crd_dt =\"" + fields + "\" ");
                   and = "AND ";


            }
            if (grade.length()>0)
            {
                chck = true;
                //fields = grade.getText().toString();
                query = query.concat(and + "condition =\"" + grade + "\" ");
                   and = "AND ";

            }
            if (team.getText().toString().length() > 0) {
                chck = true;
                fields = team.getText().toString();
                query = query.concat(and + "team =\"" + fields + "\" ");
                   and = "AND ";

            }
            if (name.getText().toString().length() > 0) {
                chck = true;
                fields = name.getText().toString();
                query = query.concat(and + "name =\"" + fields + "\" ");
                    and = "AND ";

            }


            if (query.length()<33){
                chck = false;
                Toast.makeText(MainActivity.this, "Please enter the values",
                        Toast.LENGTH_LONG).show();
            }


            if (chck) {
                Intent inti = new Intent(MainActivity.this, searchresult.class);

                inti.putExtra("query", query);
                startActivity(inti);


            }




        }




    }

}



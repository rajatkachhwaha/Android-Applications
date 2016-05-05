package com.example.rjt.baseballcards;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class buyinfo extends AppCompatActivity implements View.OnClickListener {
    Button confirm;
    Button back;
    String name;
    String date;
    String condition;
    String team;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyinfo);

        confirm=(Button)findViewById(R.id.confirmbtn);
        back=(Button)findViewById(R.id.bckbtn);

        confirm.setOnClickListener(this);
        back.setOnClickListener(this);

        Intent intent= getIntent();

         name=intent.getStringExtra("name");
         date=intent.getStringExtra("date");
        condition=intent.getStringExtra("condition");
         team=intent.getStringExtra("team");




    }

    public void doit()
    {
        EditText owner_name=(EditText)findViewById(R.id.ownername);
        EditText street_address=(EditText)findViewById(R.id.staddress);
        EditText city=(EditText)findViewById(R.id.city);
        EditText state=(EditText)findViewById(R.id.state);
        EditText zipcode=(EditText)findViewById(R.id.zipcode);


        String ownername= owner_name.getText().toString();
        String staddr=street_address.getText().toString();
        String ownercity=city.getText().toString();
        String ownerstate=state.getText().toString();
        String ownercode=zipcode.getText().toString();

        SQLiteDatabase db;

        db=openOrCreateDatabase("BasketballCard.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);
        String query="insert into owner_detail (u_no,ds,name,st_add,city,state,zip_code,amt_paid,status) values ((select u_no from card_detail where name= \""
            +name
            +"\"), Date('now'),\""
            +ownername
            +"\",\""
            + staddr
            +"\",\""
            +ownercity
            +"\",\""
            +ownerstate
            +"\",\""
            +ownercode
            +"\",(select sp from card_detail where name=\""
            +name
            +"\" ),\"sold\")";

        String delquery="delete from card_detail where name = \""+ name +"\"";

        System.out.println(query);
        System.out.println(delquery);

        db.execSQL(query);
        db.execSQL(delquery);
    }

    public void onClick(View v)
    {
        if (v==confirm)
        {

           doit();

            Intent inti = new Intent(buyinfo.this, MainActivity.class);

            startActivity(inti);


            //startActivity(new Intent(buyinfo.this,MainActivity.class));
        }
        else if (v==back)
        {
            finish();
        }
    }


    public void complete_transaction()
    {

    }
}

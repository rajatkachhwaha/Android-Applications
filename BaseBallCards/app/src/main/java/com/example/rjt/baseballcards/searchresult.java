package com.example.rjt.baseballcards;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class searchresult extends AppCompatActivity implements /* OnClickListener,*/ AdapterView.OnItemClickListener {


   // Button buy;
   // Button back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        ListView list = (ListView) findViewById(R.id.listView);

        Intent intent= getIntent();
     //   buy=(Button)findViewById(R.id.buybtn);
       // back=(Button)findViewById(R.id.backbtn);

        //buy.setOnClickListener(this);
        //back.setOnClickListener(this);

       String query= intent.getStringExtra("query");

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        System.out.println(query);
        Cursor testdata = mDbHelper.getTestData(query);
        System.out.println(testdata.getCount());
        mDbHelper.close();

        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, testdata, 0);
        list.setAdapter(todoAdapter);

        list.setOnItemClickListener(this);




    }


//
//    public void onClick(View v)
//    {
//        if(v==buy)
//        {
//            //startActivity(new Intent(searchresult.this,buyinfo.class));
////            Intent k= new Intent(this, buyinfo.class);
////            startActivity(k);
//        }
//        else if (v==back)
//        {
//            finish();
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id)
    {

        //startActivity(new Intent(searchresult.this,buyinfo.class));
        //Cursor crsr = (Cursor)adapterView.getItemAtPosition(pos);

        TextView txt1=(TextView)view.findViewById(R.id.textView2);
        String text1=txt1.getText().toString();

        TextView txt2=(TextView)view.findViewById(R.id.textView3);
        String text2=txt2.getText().toString();

        TextView txt3=(TextView)view.findViewById(R.id.textView4);
        String text3=txt3.getText().toString();

        TextView txt4=(TextView)view.findViewById(R.id.textView5);
        String text4=txt4.getText().toString();


        Intent inti = new Intent(searchresult.this, buyinfo.class);

        inti.putExtra("name", text1);
        inti.putExtra("date", text2);
        inti.putExtra("condition", text3);
        inti.putExtra("team", text4);

       // Toast.makeText(getApplicationContext(),"value is : "+text1 +text2 +text3 +text4,Toast.LENGTH_LONG).show();

        startActivity(inti);







    }

    public class TodoCursorAdapter extends CursorAdapter {
        public TodoCursorAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, 0);
        }

        // The newView method is used to inflate a new view and return it,
        // you don't bind any data to the view at this point.
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        // The bindView method is used to bind all data to a given view
        // such as setting the text on a TextView.
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Find fields to populate in inflated template





            TextView tv2 = (TextView) view.findViewById(R.id.textView2);
            TextView tv3 = (TextView) view.findViewById(R.id.textView3);

            TextView tv4 = (TextView) view.findViewById(R.id.textView4);

            TextView tv5 = (TextView) view.findViewById(R.id.textView5);
            // Extract properties from cursor
            String s2 = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String s3 = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String s4 = cursor.getString(cursor.getColumnIndexOrThrow("condition"));
            String s5 = cursor.getString(cursor.getColumnIndexOrThrow("team"));

            // Populate fields with extracted properties

//            final SpannableStringBuilder nm= new SpannableStringBuilder("Name: "+s2);
//
//            final StyleSpan bss= new StyleSpan(Typeface.BOLD);
//            nm.setSpan(bss,0,4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            tv2.setText(s2);

//            final SpannableStringBuilder dt= new SpannableStringBuilder("Date: "+s3);
//
//            final StyleSpan dss= new StyleSpan(Typeface.BOLD);
//            dt.setSpan(dss,0,4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


            tv3.setText(s3);

//            final SpannableStringBuilder gd= new SpannableStringBuilder("Grade: "+s4);
//
//            final StyleSpan gss= new StyleSpan(Typeface.BOLD);
//            gd.setSpan(gss,0,4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            tv4.setText(s4);

//            final SpannableStringBuilder tm= new SpannableStringBuilder("Team: "+s5);
//
//            final StyleSpan tss= new StyleSpan(Typeface.BOLD);
//            tm.setSpan(tss,0,4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);



            tv5.setText(s5);
        }
    }

//    public class Listviewlisen extends ListActivity
//    {
//        public void onList
//    }


}

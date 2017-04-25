package com.example.rjt.faster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

 public static TextView greeting;

    DatabaseReference databaseReference, Dbref;

    Marker m=new Marker();
    public  ArrayList<Marker> markerobject;
    int t=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        greeting=(TextView)findViewById(R.id.textView2);

      //  datainput();
        readdata();










        if (AccessToken.getCurrentAccessToken() == null) {

            LoginScreen();
        }
        else
        {  Intent intent= new Intent(this, MapsActivity.class);
            startActivity(intent);
            //String s=getIntent().getStringExtra("Greet");
            //greeting.setText(s);

        }


     }



     private void LoginScreen(){
         Intent intent = new Intent(this, FacebookLoginActivity.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
         startActivity(intent);
     }

    public void logout(View view) {
        LoginManager.getInstance().logOut();

    }

    public void datainput(){

        m.setLatitude("10");
        m.setName("lowell");
        m.setLongitude("20");
        databaseReference= FirebaseDatabase.getInstance().getReference("message/user");
        databaseReference.setValue(m);

    }

    public void readdata(){
        markerobject= new ArrayList<Marker>();
        Dbref=FirebaseDatabase.getInstance().getReference("markers");

        Dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                   Marker mr= dataSnapshot1.getValue(Marker.class);

                   markerobject.add(mr);
                   Log.w("arraylist object",markerobject.get(t).getName());
                   t=t+1;

            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}

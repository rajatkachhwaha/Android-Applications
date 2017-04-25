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
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        if (AccessToken.getCurrentAccessToken() == null) {
            LoginScreen();
        }
        else
        {  Intent intent= new Intent(this, MapsActivity.class);
            startActivity(intent);
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

}

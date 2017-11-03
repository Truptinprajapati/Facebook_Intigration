package com.example.vaksys_android.trupti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        intializecontrols();
        loginwithfb();

    }

    private void intializecontrols(){
        callbackManager=CallbackManager.Factory.create();
        textView= (TextView) findViewById(R.id.textview);
        loginButton= (LoginButton) findViewById(R.id.login_button);

    }

    private void loginwithfb(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                textView.setText("login success\n"+loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                textView.setText("login cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                textView.setText("login error:"+error.getMessage());
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

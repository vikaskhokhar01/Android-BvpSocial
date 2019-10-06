package com.vikas.bvpsocial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity {

    EditText emaillogin,passwordlogin;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        emaillogin=findViewById(R.id.emaillogin);
        passwordlogin=findViewById(R.id.passwordlogin);
        login=findViewById(R.id.login);

        if(ParseUser.getCurrentUser()!=null)
        {
            //ParseUser.getCurrentUser().logOut();
            transmed();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog=new ProgressDialog(Login.this);
                progressDialog.setMessage("Setting up");
                progressDialog.show();

                ParseUser.logInInBackground(emaillogin.getText().toString(),
                        passwordlogin.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user!=null && e==null)
                                {
                                    FancyToast.makeText(Login.this,"Successfully Logged in",
                                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                    transmed();
                                }
                                else
                                {
                                    FancyToast.makeText(Login.this,e.getMessage(),
                                            FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                                }
                                progressDialog.dismiss();
                            }
                        });

            }
        });

    }
    public void tapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)
                    getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void transmed()
    {
        Intent intent=new Intent(Login.this,SocialMedia.class);
        startActivity(intent);
    }
}

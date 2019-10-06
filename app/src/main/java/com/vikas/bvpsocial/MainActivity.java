package com.vikas.bvpsocial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {

    EditText usernamesignup, emailsignup,passwordsignup;
    Button signup, loginhuh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign Up");

        usernamesignup=findViewById(R.id.usernamesignup);
        passwordsignup = findViewById(R.id.passwordsignup);
        emailsignup=findViewById(R.id.emailsignup);

        signup=findViewById(R.id.signup);
        loginhuh=findViewById(R.id.loginhuh);

        if(ParseUser.getCurrentUser()!=null)
        {
            //ParseUser.getCurrentUser().logOut();
            transitionmediaactivity();
        }

        loginhuh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernamesignup.getText().toString().equals("")
                || passwordsignup.getText().toString().equals("")
                || emailsignup.getText().toString().equals(""))
                {
                    FancyToast.makeText(MainActivity.this,"Username, Password, Email is required!",
                            FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }
            else {
                    ParseUser appuser = new ParseUser();
                    appuser.setUsername(usernamesignup.getText().toString());
                    appuser.setPassword(passwordsignup.getText().toString());
                    appuser.setEmail(emailsignup.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Wait a little");
                    progressDialog.show();

                    appuser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, "Successfully signed up",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitionmediaactivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

    }
    public void rootlayouttapped(View view) {
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
    private void transitionmediaactivity()
    {
        Intent intent=new Intent(MainActivity.this,SocialMedia.class);
        startActivity(intent);
    }
}

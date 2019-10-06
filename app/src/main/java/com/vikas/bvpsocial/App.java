package com.vikas.bvpsocial;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KkPB24Qk1oTI9LFRAYUPAW5nSjJaq8dIMo77FEVu")
                // if defined
                .clientKey("RzHshBrcvIQQ2BN8oOsPbiQKqZ1wBZGYilELJlqJ")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}

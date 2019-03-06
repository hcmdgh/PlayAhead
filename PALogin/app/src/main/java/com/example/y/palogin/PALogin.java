package com.example.y.palogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mob.MobSDK;
import com.mob.ums.OperationCallback;
import com.mob.ums.User;
import com.mob.ums.gui.UMSGUI;


public class PALogin extends Activity {

    OperationCallback<User> callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palogin);
        MobSDK.init(this);

        Button palogin=(Button) findViewById(R.id.bt_palogin);
        Button showProfilePage=(Button) findViewById(R.id.bt_showProfilePage);

        palogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UMSGUI.showLogin(callback);
            }
        });

        showProfilePage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UMSGUI.showProfilePage();
            }
        });

    }

}

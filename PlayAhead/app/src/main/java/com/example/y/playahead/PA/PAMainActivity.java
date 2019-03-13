package com.example.y.playahead.PA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.y.playahead.R;
import com.mob.MobSDK;
import com.mob.ums.gui.UMSGUI;


public class PAMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pa_activitymain);
        MobSDK.init(this);

        ImageButton ibt_sd=(ImageButton)findViewById(R.id.ibt_sd);
        ImageButton ibt_2048=(ImageButton)findViewById(R.id.ibt_2048);
        ImageButton ibt_szhrd=(ImageButton)findViewById(R.id.ibt_szhrd);
        Button bt_setting=(Button) findViewById(R.id.bt_setting);

        ibt_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PAMainActivity.this,PALoading.class);
                intent.putExtra("game",1);
                startActivity(intent);
            }
        });
        ibt_2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PAMainActivity.this, PALoading.class);
                intent.putExtra("game", 2);
                startActivity(intent);
            }
        });
        ibt_szhrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PAMainActivity.this,PALoading.class);
                intent.putExtra("game",3);
                startActivity(intent);
            }
        });




        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(PAMainActivity.this,PASetting.class);
//                startActivity(intent);
                UMSGUI.showProfilePage();
            }
        });
    }
}

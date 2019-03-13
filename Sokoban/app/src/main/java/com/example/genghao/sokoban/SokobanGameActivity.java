package com.example.genghao.sokoban;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SokobanGameActivity extends Activity {
    private Sokoban mSokoban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sokoban_activity_game);

        mSokoban = findViewById(R.id.sokoban);
        mSokoban.mTvStep = findViewById(R.id.tv_step);
        View mBtnBack = findViewById(R.id.btn_back);
        View mBtnReset = findViewById(R.id.btn_reset);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSokoban.back();
            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSokoban.reset();
            }
        });
    }
}

package com.example.genghao.sokoban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class SokobanLevelChooseActivity extends Activity {
    private TextView[] mTvLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sokoban_activity_level_choose);

        initButtons();
    }

    private void initButtons() {
        GridLayout gridLayout = findViewById(R.id.GridLayout1);
        mTvLevels = new TextView[40];
        for (int i = 0; i < 40; ++i) {
            mTvLevels[i] = new TextView(this);
            mTvLevels[i].setOnClickListener(new LevelListener(i));
//            mTvLevels[i].setBackgroundResource(R.drawable.pic_blue_ball);
//            mTvLevels[i].setText(i + 1 + "");
//            mTvLevels[i].setTextSize(24);
//            mTvLevels[i].setGravity(Gravity.CENTER);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 5, 1f);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 5, 1f);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            layoutParams.height = 0;
            layoutParams.width = 0;
//            layoutParams.setMargins(10, 10, 10, 10);
            gridLayout.addView(mTvLevels[i], layoutParams);
        }
        SokobanGameData.readData(mTvLevels);
    }

    private class LevelListener implements View.OnClickListener {
        private int level;

        public LevelListener(int level) {
            this.level = level;
        }

        @Override
        public void onClick(View v) {
            Sokoban.level_to_load = level;
            Intent intent = new Intent(SokobanLevelChooseActivity.this, SokobanGameActivity.class);
            startActivityForResult(intent, 20);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 20) {
            SokobanGameData.refreshTvs(mTvLevels);
        }
    }
}



package com.example.genghao.sokoban;

import android.content.Context;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SokobanGameData {
    public static final byte SOLVED = 65;
    public static final byte UNSOLVED = 66;
    public static boolean[] solved = new boolean[40];

    private static byte[] readFromFile(Context context) {
        byte[] data = new byte[40];
        try {
            FileInputStream inputStream = context.openFileInput("data.txt");
            int size = inputStream.read(data);
            if (size < 40) {
                throw new RuntimeException("读取数据错误！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static boolean writeToFile(Context context, byte[] data) {
        try {
            FileOutputStream outputStream = context.openFileOutput("data.txt", Context.MODE_PRIVATE);
            outputStream.write(data);
            outputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void readData(TextView[] mTvLevels) {
        if (mTvLevels.length != 40) {
            throw new RuntimeException("按钮不匹配！");
        }
        Context context = mTvLevels[0].getContext();
        byte[] data = readFromFile(context);
        for (int i = 0; i < 40; ++i) {
            if (data[i] == SOLVED) {
                solved[i] = true;
            } else {
                solved[i] = false;
            }
        }
        refreshTvs(mTvLevels);
    }

    public static void writeData(Context context) {
        byte[] data = new byte[40];
        for (int i = 0; i < 40; ++i) {
            if (solved[i]) {
                data[i] = SOLVED;
            } else {
                data[i] = UNSOLVED;
            }
        }
        writeToFile(context, data);
    }

    public static void refreshTvs(TextView[] mTvLevels) {
        for (int i = 0; i < 40; ++i) {
            if (solved[i]) {
                mTvLevels[i].setBackgroundResource(R.drawable.sokoban_pic_solved);
            } else {
                mTvLevels[i].setBackground(null);
            }
        }
    }
}

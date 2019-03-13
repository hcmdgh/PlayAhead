package com.example.genghao.sokoban;

// 储存每个点的坐标，用于广度优先搜索
public class SokobanPoint {
    public int x, y, last;

    public SokobanPoint(int x, int y, int last) {
        this.x = x;
        this.y = y;
        this.last = last;
    }
}
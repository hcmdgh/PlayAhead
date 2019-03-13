package com.example.genghao.sokoban;

// 每一步的快照
public class SokobanSnapshot {
    public Status[][] map;
    public int step;
    public int player_x, player_y;

    public SokobanSnapshot(Status[][] map, int step, int player_x, int player_y) {
        this.step = step;
        this.player_x = player_x;
        this.player_y = player_y;
        int length = map.length;
        this.map = new Status[length][];
        for (int i = 0; i < length; ++i) {
            this.map[i] = map[i].clone();
        }
    }
}
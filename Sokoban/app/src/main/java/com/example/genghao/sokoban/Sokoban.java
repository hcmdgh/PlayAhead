package com.example.genghao.sokoban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Sokoban extends View {
    public static int level_to_load;
    private int player_x, player_y;
    private int width, height;
    private Status[][] map;
    private static final int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    private int step;
    private Context gameActivity;
    public TextView mTvStep;
    // 记录每一步的信息
    Stack<SokobanSnapshot> snapshots = new Stack<>();
    // 游戏是否结束（游戏胜利）
    private boolean gameFinished = false;

    // 点击某一位置触发的事件
    public void onClick(int dest_x, int dest_y) {
        if (dest_x < 0 || dest_y < 0 || dest_x >= height || dest_y >= width) {
            return;
        }
        if (dest_x == player_x && dest_y == player_y) {
            return;
        }
        Status stat = map[dest_x][dest_y];
        ArrayList<SokobanPoint> path;
        boolean reachable = false;
        switch (stat) {
            case space:
            case dest:
                path = moveTo(dest_x, dest_y);
                if (!path.isEmpty()) {
                    Log.d("debug", "能走！");
                    // TODO:走路效果
                    snapshots.push(new SokobanSnapshot(map, step, player_x, player_y));
                    player_x = dest_x;
                    player_y = dest_y;
                    step += path.size() - 1;
                    invalidate();
                    reachable = true;
                }
                break;
            case box:
            case box_dest:
                int box_next_x, box_next_y;
                if (dest_x == player_x + 1 && dest_y == player_y) {
                    box_next_x = dest_x + 1;
                    box_next_y = dest_y;
                } else if (dest_x == player_x && dest_y == player_y + 1) {
                    box_next_x = dest_x;
                    box_next_y = dest_y + 1;
                } else if (dest_x == player_x && dest_y == player_y - 1) {
                    box_next_x = dest_x;
                    box_next_y = dest_y - 1;
                } else if (dest_x == player_x - 1 && dest_y == player_y) {
                    box_next_x = dest_x - 1;
                    box_next_y = dest_y;
                } else {
                    break;
                }
                Status box_next = map[box_next_x][box_next_y];
                if (box_next == Status.space || box_next == Status.dest) {
                    snapshots.push(new SokobanSnapshot(map, step, player_x, player_y));
                    if (stat == Status.box) {
                        map[dest_x][dest_y] = Status.space;
                    } else {
                        map[dest_x][dest_y] = Status.dest;
                    }
                    if (box_next == Status.space) {
                        map[box_next_x][box_next_y] = Status.box;
                    } else {
                        map[box_next_x][box_next_y] = Status.box_dest;
                    }
                    player_x = dest_x;
                    player_y = dest_y;
                    step += 1;
                    invalidate();
                    reachable = true;

                    // 判断是否完成了整个关卡
                    boolean finished = true;
                    for (int i = 0; finished && i < height; ++i) {
                        for (int j = 0; finished && j < width; ++j) {
                            if (map[i][j] == Status.box) {
                                finished = false;
                            }
                        }
                    }
                    if (finished) {
                        gameFinished = true;
                        if (!SokobanGameData.solved[level_to_load]) {
                            SokobanGameData.solved[level_to_load] = true;
                            SokobanGameData.writeData(getContext());
                        }
                        invalidate();
                    }
                }
                break;
        }
        if (!reachable) {
            SokobanMyToast.show(gameActivity, "此处不可达！");
        }
    }

    // 移动小人到某个位置，返回走的路径，如果走不到则返回空数组
    private ArrayList<SokobanPoint> moveTo(int dest_x, int dest_y) {
        boolean[][] visited = new boolean[height][width];
        visited[player_x][player_y] = true;
        ArrayList<SokobanPoint> trace = new ArrayList<>();
        trace.add(new SokobanPoint(player_x, player_y, -1));
        int begin = 0, length = 1;
        int next_x, next_y;
        ArrayList<SokobanPoint> path = new ArrayList<>();
        while (begin < length) {
            for (int i = begin; i < length; ++i) {
                int x = trace.get(i).x;
                int y = trace.get(i).y;
                for (int j = 0; j < 4; ++j) {
                    next_x = x + directions[j][0];
                    next_y = y + directions[j][1];
                    if (!visited[next_x][next_y] && (map[next_x][next_y] == Status.space || map[next_x][next_y] == Status.dest)) {
                        trace.add(new SokobanPoint(next_x, next_y, i));
                        visited[next_x][next_y] = true;
                        if (next_x == dest_x && next_y == dest_y) {
                            int index = trace.size() - 1;
                            while (index > -1) {
                                path.add(trace.get(index));
                                index = trace.get(index).last;
                            }
                            Collections.reverse(path);
                            return path;
                        }
                    }
                }
            }
            begin = length;
            length = trace.size();
        }
        return path;
    }

    // 加载指定关卡
    public void loadLevel(int level) {
        String[] data = SokobanLevels.levels[level].split("\n");
        height = data.length;
        width = -1;
        step = 0;
        for (String aData : data) {
            if (aData.length() > width) {
                width = aData.length();
            }
        }
        map = new Status[height][width];
        player_x = player_y = -1;
        for (int i = 0; i < height; ++i) {
            int j = 0;
            while (j < data[i].length() && data[i].charAt(j) == ' ') {
                map[i][j++] = Status.undefined;
            }
            for (; j < width; ++j) {
                if (j >= data[i].length()) {
                    map[i][j] = Status.undefined;
                } else {
                    switch (data[i].charAt(j)) {
                        case '#':
                            map[i][j] = Status.wall;
                            break;
                        case ' ':
                            map[i][j] = Status.space;
                            break;
                        case '$':
                            map[i][j] = Status.box;
                            break;
                        case '.':
                            map[i][j] = Status.dest;
                            break;
                        case '*':
                            map[i][j] = Status.box_dest;
                            break;
                        case '@':
                            map[i][j] = Status.space;
                            player_x = i;
                            player_y = j;
                            break;
                        case '+':
                            player_x = i;
                            player_y = j;
                            map[i][j] = Status.dest;
                            break;
                        default:
                            throw new RuntimeException("错误，数据不合法！");
                    }
                }
            }
        }
        if (player_x < 0 || player_y < 0) {
            throw new RuntimeException("错误，没有人的位置！");
        }
    }

    public Sokoban(Context context) {
        this(context, null);
    }

    public Sokoban(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Sokoban(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gameActivity = getContext();
        initCanvas();
        reset();
    }

    private Paint paint;
    private Bitmap pic_box;
    private Bitmap pic_dest;
    private Bitmap pic_wall;
    private Bitmap pic_space;
    private Bitmap pic_box_dest;
    private Bitmap pic_player;
    private Bitmap pic_win;

    // TODO: bug
    private int grid_width;
    private Rect[][] dstRect;

    // 初始化画布
    private void initCanvas() {
        paint = new Paint();
        paint.setAntiAlias(true);

        pic_box = BitmapFactory.decodeResource(getResources(), R.drawable.sokoban_box);
        pic_dest = BitmapFactory.decodeResource(getResources(), R.drawable.sokoban_dest);
        pic_wall = BitmapFactory.decodeResource(getResources(), R.drawable.sokoban_wall);
        pic_space = BitmapFactory.decodeResource(getResources(), R.drawable.sokoban_space);
        pic_box_dest = BitmapFactory.decodeResource(getResources(), R.drawable.sokoban_box_dest);
        pic_player = BitmapFactory.decodeResource(getResources(), R.drawable.sokoban_player);
        pic_win = BitmapFactory.decodeResource(getResources(), R.drawable.sokoban_pic_win);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("debug", "画布onDraw");
        Rect srcRect;
        Bitmap picture;
        if (dstRect == null) {
            grid_width = getWidth() / width;
            dstRect = new Rect[height][width];
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    dstRect[i][j] = new Rect(j * grid_width, i * grid_width, j * grid_width + grid_width, i * grid_width + grid_width);
                }
            }
        }
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (player_x == i && player_y == j) {
                    picture = pic_player;
                } else {
                    switch (map[i][j]) {
                        case box:
                            picture = pic_box;
                            break;
                        case wall:
                            picture = pic_wall;
                            break;
                        case space:
                            picture = pic_space;
                            break;
                        case dest:
                            picture = pic_dest;
                            break;
                        case box_dest:
                            picture = pic_box_dest;
                            break;
                        default:
                            picture = null;
                    }
                }
                if (picture != null) {
                    srcRect = new Rect(0, 0, picture.getWidth(), picture.getHeight());
                    canvas.drawBitmap(picture, srcRect, dstRect[i][j], paint);
                }
            }
        }
        mTvStep.setText("" + step);
        if (gameFinished) {
            Rect winSrc = new Rect(0, 0, pic_win.getWidth(), pic_win.getHeight());
            Rect winDest = new Rect(0, 0, getWidth(), getWidth());
            canvas.drawBitmap(pic_win, winSrc, winDest, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!gameFinished) {
                float pos_x = event.getX();
                float pos_y = event.getY();
                int row = (int) (pos_y / grid_width);
                int col = (int) (pos_x / grid_width);
                Log.d("debug", row + "," + col);
                onClick(row, col);
            } else {
                if (level_to_load < 39) {
                    ++level_to_load;
                    ((SokobanGameActivity) gameActivity).recreate();
                } else {
                    ((SokobanGameActivity) gameActivity).finish();
                }
            }
        }
        return true;
    }

    // 撤销
    public void back() {
        if (snapshots.empty()) {
            SokobanMyToast.show(gameActivity, "已经回到了第一步！");
        } else {
            SokobanSnapshot lastStep = snapshots.pop();
            step = lastStep.step;
            int length = lastStep.map.length;
            map = new Status[length][];
            for (int i = 0; i < length; ++i) {
                map[i] = lastStep.map[i].clone();
            }
            player_x = lastStep.player_x;
            player_y = lastStep.player_y;
            invalidate();
        }
    }

    // 重置
    public void reset() {
        while (!snapshots.empty()) {
            snapshots.pop();
        }
        loadLevel(level_to_load);
        invalidate();
    }
}

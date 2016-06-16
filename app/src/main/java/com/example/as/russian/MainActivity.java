package com.example.as.russian;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private gamebox gamebox;
    private blockthread bth;
    private GestureDetector ges = new GestureDetector(this);
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamebox = (gamebox)findViewById(R.id.gamebox);
        bth = new blockthread(gamebox);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(MainActivity.this,"Game over",Toast.LENGTH_SHORT).show();
            }
        };
        bth.setHandler(handler);
        bth.start();
    }
    public boolean onTouchEvent(MotionEvent event) {
        return ges.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent me) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v1, float v2) {
        if(e2.getY() - e1.getY() >100 && Math.abs(v2) > 20) {
            while (!bth.collision(gamebox, bth.b.bb)) {
                bth.b.move(bth.b, 0);
            }
            bth.b.move(bth.b, 3);
        }
        gamebox.postInvalidate();
        return false;
    }
    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent me) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        int height = metric.heightPixels;
        if(me.getY()>3*height/4) {
            if (me.getX() < width / 2) {
                bth.b.move(bth.b, 1);
                if (bth.collision(gamebox, bth.b.bb) || getbound(bth.b, 0) < 0) {
                    bth.b.move(bth.b, 2);
                }
                gamebox.postInvalidate();
            } else if (me.getX() > width / 2) {
                bth.b.move(bth.b, 2);
                if (bth.collision(gamebox, bth.b.bb) || getbound(bth.b, 1) > gamebox.column-1) {
                    bth.b.move(bth.b, 1);
                }
                gamebox.postInvalidate();
            }
        }else if(me.getY()<3*height/4){
            bth.b.turn(bth.b);
            if (bth.collision(gamebox, bth.b.bb)){
                bth.b.turn(bth.b);
                bth.b.turn(bth.b);
                bth.b.turn(bth.b);
            }
            while (getbound(bth.b, 0) < 0) {
                bth.b.move(bth.b, 2);
            }
            while (getbound(bth.b, 1) > gamebox.column-1) {
                bth.b.move(bth.b, 1);
            }
            gamebox.postInvalidate();
        }
        return false;
    }
    public int getbound(blocks b,int n){
        point[] p = b.bb;
        int minx = 5;
        int maxx = 5;
        for(int i = 0;i<4;i++){
            if(p[i].x<minx){
                minx = p[i].x;
            }
            if(p[i].x>maxx){
                maxx = p[i].x;
            }
        }
        if(n == 0)return minx;
        else if(n == 1)return maxx;
        return 0;
    }
}


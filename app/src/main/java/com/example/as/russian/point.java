package com.example.as.russian;

/**
 * Created by as on 2016/6/2.
 */
public class point {
    int x;
    int y;
    public point (int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        point p = (point)o;
        if(p.x == x && p.y == y){
            return true;
        }else{
            return false;
        }
    }
}

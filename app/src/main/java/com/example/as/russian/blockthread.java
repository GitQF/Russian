package com.example.as.russian;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;

/**
 * Created by as on 2016/6/3.
 */
public class blockthread extends Thread{
    blocks b = blocks.getrandomblock();
    gamebox gamebox;
    Handler handler;
    public blockthread(gamebox gamebox){
        this.gamebox = gamebox;
        gamebox.setBlock(b);
    }
    public void setHandler(Handler handler){
        this.handler = handler;
    }
    public void run(){
        while (true) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            b.move(b,0);
            stopblock(gamebox,b.bb);
            gamebox.postInvalidate();
            if(gameover(gamebox)){
                Message msg = new Message();
                handler.sendMessage(msg);
                break;
            }
        }
    }
    public boolean collision(gamebox gamebox,point[] block){
        for(int i = 0;i<4;i++){
            for(int j = 0;j<gamebox.stablock.size();j++){
                if(block[i].equals(gamebox.stablock.get(j))){
                    return true;
                }
            }
        }
        if(b.loc.y >= gamebox.getrow()){
             return true;
        }
        return false;
    }
    public void stopblock(gamebox gamebox,point[] block){
        boolean flag = false;
        if(b.loc.y == gamebox.getrow()){
            flag = true;
        }
        for(int i = 0;i<4;i++){
            for(int j = 0;j<gamebox.stablock.size();j++){
                if(block[i].equals(gamebox.stablock.get(j))){
                    flag = true;
                    break;
                }
            }
        }
        if(flag) {
            b.move(b,3);
            for (int i = 0; i < 4; i++) {
                gamebox.stablock.add(b.bb[i]);
            }
            remove();
            b = blocks.getrandomblock();
            gamebox.setBlock(b);
        }
    }
    private boolean gameover(gamebox gamebox){
        for(int i = 0;i<gamebox.stablock.size();i++){
            if(gamebox.stablock.get(i).y<0){
                return true;
            }
        }
        return false;
    }
    private void remove(){
        int n = 0;
        for(int i = gamebox.getrow()-1;i>=0;i--){
            for(int j = 0;j<gamebox.column;j++){
                if(!contain(j,i)){
                    break;
                }
                if(j == gamebox.column-1){
                    n++;
                    for(int k = 0;k<gamebox.stablock.size();k++){
                        if(gamebox.stablock.get(k).y == i){
                            gamebox.stablock.remove(k);
                            k--;
                        }
                    }
                }
            }
        }
        for(int i = 0;i<gamebox.stablock.size();i++){
            gamebox.stablock.get(i).y+=n;
        }
    }
    private boolean contain(int x,int y){
        for(int i= 0;i<gamebox.stablock.size();i++){
            if(gamebox.stablock.get(i).x == x && gamebox.stablock.get(i).y == y){
                return true;
            }
        }
        return false;
    }
}

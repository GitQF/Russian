package com.example.as.russian;

/**
 * Created by as on 2016/6/2.
 */
public class blocks {
    point loc;
    int type;
    int dir;
    point[] bb;
    public blocks(point loc,int type,int dir){
        this.loc = loc;
        this.type = type;
        this.dir = dir;
        this.bb = getblock(loc,type,dir);
    }
        public void move(blocks block,int dir){          // 0 down, 1 left, 2right
        switch(dir){
            case 0:
                block.loc.y++;
                break;
            case 1:
                block.loc.x--;
                break;
            case 2:
                block.loc.x++;
                break;
            case 3:
                block.loc.y--;
                break;
        }
            block.bb = getblock(block.loc,block.type,block.dir);
    }
    public void turn(blocks block){
        if(block.dir == 3){
            block.dir = 0;
        }else{
            block.dir++;
        }
        block.bb = getblock(block.loc,block.type,block.dir);
    }
    public static blocks getrandomblock(){
        int rantype = (int)((3.999)*Math.random());
        int randir = (int)((3.999)*Math.random());
        return new blocks(new point(6,-1),rantype,randir);
    }
    public point[] getblock(point p,int type,int dir){
        switch (type){
            case 0:                 //长条
                if(dir%2 == 0)return new point[]{p,new point(p.x-1,p.y),new point(p.x+1,p.y),new point(p.x+2,p.y)};
                if(dir%2 == 1)return new point[]{p,new point(p.x,p.y-1),new point(p.x,p.y-2),new point(p.x,p.y-3)};
                break;
            case 1:                 //方块
                return new point[]{p,new point(p.x+1,p.y),new point(p.x,p.y-1),new point(p.x+1,p.y-1)};
            case 2:                 //L
                if(dir == 0)return new point[]{p,new point(p.x+1,p.y),new point(p.x,p.y-1),new point(p.x,p.y-2)};//up
                if(dir == 1)return new point[]{p,new point(p.x,p.y-1),new point(p.x+1,p.y-1),new point(p.x+2,p.y-1)};//left
                if(dir == 2)return new point[]{p,new point(p.x,p.y-1),new point(p.x,p.y-2),new point(p.x-1,p.y-2)};//down
                if(dir == 3)return new point[]{p,new point(p.x-1,p.y),new point(p.x-2,p.y),new point(p.x,p.y-1)};//right
                break;
            case 3:                 //丁
                if(dir == 0)return new point[]{p,new point(p.x+1,p.y),new point(p.x-1,p.y),new point(p.x,p.y-1)};
                if(dir == 1)return new point[]{p,new point(p.x,p.y-1),new point(p.x,p.y-2),new point(p.x+1,p.y-1)};
                if(dir == 2)return new point[]{p,new point(p.x,p.y-1),new point(p.x-1,p.y-1),new point(p.x+1,p.y-1)};
                if(dir == 3)return new point[]{p,new point(p.x,p.y-1),new point(p.x,p.y-2),new point(p.x-1,p.y-1)};
                break;
        }
        return null;
    }
}

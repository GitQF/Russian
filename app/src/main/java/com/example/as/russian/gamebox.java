package com.example.as.russian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by as on 2016/6/2.
 */
public class gamebox extends View {
    blocks b;
    ArrayList<point> stablock = new ArrayList<point>();
    int column = 12;
    int line = 4;
    private Paint paint;

    public gamebox(Context context){
        super(context);
    }
    public gamebox(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }
    public void setBlock(blocks b){
        this.b = b;
        b.bb = b.getblock(b.loc,b.type,b.dir);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int l = (getWidth()-40-line*(column-1))/column;
        int row = (getHeight()-40)/(l+line);
        int aa = (getHeight()-40)%(l+line);
        paint = new Paint();
        paint.setColor(0xCC21C9B0);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(5,5,getWidth()-5,getHeight()-5,paint);
        paint.setColor(0x6621C9B0);
        paint.setStyle(Paint.Style.FILL);
        for(int i = 0;i<column;i++){
            for(int j = 0;j<row;j++){
                canvas.drawRect(20+i*(l+line),aa+20+j*(l+line),20+i*(l+line)+l,aa+20+j*(l+line)+l,paint);
            }
        }
        paint.setColor(0x77000000);
        for(int i = 0;i<b.bb.length;i++){
            canvas.drawRect(20+b.bb[i].x*(l+line),aa+20+b.bb[i].y*(l+line),20+b.bb[i].x*(l+line)+l,aa+20+b.bb[i].y*(l+line)+l,paint);
        }
        for(int i = 0;i<stablock.size();i++){
            canvas.drawRect(20+stablock.get(i).x*(l+line),aa+20+stablock.get(i).y*(l+line),20+stablock.get(i).x*(l+line)+l,aa+20+stablock.get(i).y*(l+line)+l,paint);
        }
    }
    public int getrow(){
        int l = (getWidth()-40-line*(column-1))/column;
        int row = (getHeight()-40)/(l+line);
        return row;
    }
}

package tw.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import java.util.HashMap;
import java.util.LinkedList;

public class Sign extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private Paint paint;

    public Sign(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.GRAY);
        lines = new LinkedList<>();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(10);
//        canvas.drawCircle(100,100,40, paint);

        for(LinkedList<HashMap<String,Float>> line:lines) {
            for(int i=1; i<line.size(); i++) {
                HashMap<String,Float> p0 = line.get(i-1);
                HashMap<String,Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"), paint);
            }
        }


    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.v("Lin", "down");
//            setFirstPoint(event);
//        }
//        else if (event.getAction() == MotionEvent.ACTION_UP){
//            Log.v("Lin", "UP");
//        }
//        else if(event.getAction() == MotionEvent.ACTION_MOVE) {
//            Log.v("Lin", "MOVE");
//            setMovePoint(event);
//        }
//
//        this.getParent().requestDisallowInterceptTouchEvent(true);
//
//        //super.onTouchEvent(event);
//        return true;
//    }

    public void setFirstPoint(MotionEvent event){
        LinkedList<HashMap<String,Float>> line = new LinkedList<>();
        float ex = event.getX();
        float ey = event.getY();
        HashMap<String,Float> point = new HashMap<>();
        point.put("x", ex);
        point.put("y", ey);
        line.add(point);

        lines.add(line);
    }
    public void setMovePoint(MotionEvent event){
        float ex = event.getX();
        float ey = event.getY();
        HashMap<String,Float> point = new HashMap<>();
        point.put("x", ex);
        point.put("y", ey);
        lines.getLast().add(point);

        invalidate();
    }

    public void clear() {
        lines.clear();
        invalidate();
    }
    public void undo() {
        if(lines.size()>0) {
            lines.removeLast();
            invalidate();
        }
    }

}

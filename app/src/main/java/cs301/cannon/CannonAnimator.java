package cs301.cannon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

import static java.lang.Math.*;

/** This class defines the animation cannon which is movable upon
 *  user touch interaction and spawns a cannonball animation
 *  object when clicked
 *
 * Created by travanti16 on 11/1/2015.
 */
public class CannonAnimator implements Animator{
    public int xPos = 300;
    public int yPos = 120;
    public final int width = 400;
    public boolean destroyed = false;
    private int angle = 0;

    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return Color.rgb(180, 200, 255);
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        if(destroyed)
            return true;
        else
            return false;
    }

    @Override
    public void tick(Canvas canvas) {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        int xOff = canvas.getWidth()/4;
        int yOff = canvas.getHeight()-10;
        //int topLeft = (int) ((sin(angle)*width)+(canvas.getWidth()/4));
        blackPaint.setStrokeWidth(3);
        Rect r = new Rect(200, 1100, 300, 1200);
        //r.offsetTo(xPos, yPos);
        r.set((int) (250+70.71*cos(angle+3.14)),(int) (1150+70.71*sin(angle+3.14)),(int) (250+70.71*cos(angle)), (int) (1150+70.71*sin(angle)));

        canvas.drawRect(r, blackPaint);


    }

    @Override
    public void onTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            float xPress = event.getX();
            float yPress = event.getY();
            xPos = (int) xPress;
            yPos = (int) yPress;
            angle = (int) atan((xPos - 250) / (yPos - 1150));




        }


    }
}

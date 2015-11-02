package cs301.cannon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import static java.lang.Math.atan;

/**
 * Created by travanti16 on 11/1/2015.
 */
public class CannonBallAnimator implements Animator {
    private int count = 0;
    boolean toShoot = false;
    public float xPos = 0;
    public float yPos = 0;
    private double angle = 0;
    private int velocity = 1;


    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return 0;
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {
        if(toShoot) {
            count++;

            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            canvas.drawCircle(250, 1150, 20, paint);
        }



    }

    @Override
    public void onTouch(MotionEvent event) {
        toShoot = true;
        count = 0;
        float xPress = event.getX();
        float yPress = event.getY();
        xPos =  xPress;
        yPos =  yPress;
        double dX = xPos - 250;
        double dY = yPos - 1150; //might need absolute value because of coordinate system
        angle = atan(dY / dX);

    }
}

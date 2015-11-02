package cs301.cannon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    public float xPos = 0;
    public float yPos = 0;
//    public final int width = 400;
    public boolean destroyed = false;
//    public boolean hasClicked = false;
    private double angle = 0;
    public float xBall = 250;
    public float yBall = 1150;
    boolean toShoot = false;
    private int velocity = 65;
    public final int GRAVITY = -1;
    private int count = 0;
//    public int littleX1 = 0;
//    public int littleY1 = 0;
//    public int littleX2 = 0;
//    public int littleY2 = 0;


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
        if(toShoot) {
            count++;

            double yVelocity = velocity*sin(angle) - GRAVITY*count;
            double xVelocity = velocity*cos(angle);
            xBall = (float) (250 + xVelocity * count);
            yBall = (float) (1150 + yVelocity * count - (0.5*GRAVITY*count*count));
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            canvas.drawCircle(xBall, yBall, 20, paint);
        }

        //int xOff = canvas.getWidth()/4;
        //int yOff = canvas.getHeight()-10;
        //int topLeft = (int) ((sin(angle)*width)+(canvas.getWidth()/4));
        //blackPaint.setStrokeWidth(3);
        Rect r = new Rect(200, 1100, 400, 1200); //generate simple cannon

            //r.set(250 - littleX1, 1150 - littleY1, 250 - littleX2, 1150 - littleY2);
            canvas.save(); //saves the current state of the canvas
            double degreesAngle = Math.toDegrees(angle); //convert angle for rotate
        canvas.rotate((float) degreesAngle, 250, 1150);
        canvas.drawRect(r, blackPaint);
        canvas.restore(); //restore the canvas so nothing is all messed up

    }

    @Override
    public void onTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            toShoot = true;
//          hasClicked = true;
            count = 0;
            float xPress = event.getX();
            float yPress = event.getY();
            xPos =  xPress;
            yPos =  yPress;
            double dX = xPos - 250;
            double dY = yPos - 1150; //might need absolute value because of coordinate system
            angle = atan(dY / dX);
            if (xPos <= 250) {
                angle = angle + 3.14;
//                littleX1 = (int) (71 * cos(angle+3.14));
//                littleY1 = (int) (71 * sin(angle+3.14));
//                littleX2 = (int) (71 * cos(angle));
//                littleY2 = (int) (71 * sin(angle));
            }
//            else{
//                littleX1 = (int) (-71 * cos(angle+3.14));
//                littleY1 = (int) (-71 * sin(angle+3.14));
//                littleX2 = (int) (-71 * cos(angle));
//                littleY2 = (int) (-71 * sin(angle));
//            }



        }


    }
}

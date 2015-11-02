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
    public final int X_CANNON = 250;
    public final int Y_CANNON = 1150;
//    public final int width = 400;
    public boolean destroyed = false;
//    public boolean hasClicked = false;
    private double angle = 0;
    public float xBall = 250;
    public float yBall = 1150;
    boolean toShoot = false;
    private int velocity = 90;
    public final int GRAVITY = -1;
    private int count = 0;
    private int countTarget = 0;
    public float xTarg1 = 500;
    public float yTarg1 = 600;
    public float xTarg2 = 500;
    public float yTarg2 = 350;
    public float Targ1V = 30;
    public float Targ2V = -30;
    private boolean isTarget1Destroyed = false;
    private boolean isTarget2Destroyed = false;
    private boolean isCanonDestroyed = false;

    private double yVelocity = 0;
    private double xVelocity = 0;
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

        //incrament Target counter
        //counters are seperated because cannon is used to switch canon pos.
        countTarget++;

        //define canon paint color
        final float textSize = 36f;
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextSize(textSize);

        //define canonballPainColor
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        //after checking that the screen has been touched at all shoot the cannon
        //by spawning a canonball
        if(toShoot && !isCanonDestroyed) {
            count++;

            yVelocity = velocity*sin(angle) - GRAVITY*count;
            xVelocity = velocity*cos(angle);
            xBall = (float) (X_CANNON + xVelocity * count);
            yBall = (float) (Y_CANNON + yVelocity * count - (0.5*GRAVITY*count*count));
            canvas.drawCircle(xBall, yBall, 20, paint);
        }
        //define paint for targets
        Paint paintTarget = new Paint();
        paintTarget.setColor(Color.MAGENTA);

        Paint paintTarget2 = new Paint();
        paintTarget2.setColor(Color.WHITE);

        //Draw the targets at their respective locations after cheking that they haven't been hit
        if(!isTarget1Destroyed)
        {
            canvas.drawCircle(xTarg1, yTarg1, 50, paintTarget); //layer circles to make target
            canvas.drawCircle(xTarg1, yTarg1, 30, paintTarget2);
            canvas.drawCircle(xTarg1, yTarg1, 10, paintTarget);
            xTarg1 = xTarg1 + Targ1V;
        }
        else
        {
            float text1X = xTarg1; //save local version to protect target's value
            float text1Y = yTarg1;
            canvas.drawText("Target 1 Destoryed", text1X - 40, text1Y - 40, blackPaint); //make hit message
        }

        if(!isTarget2Destroyed)
        {
            canvas.drawCircle(xTarg2, yTarg2, 50, paintTarget); //layer circles to make target
            canvas.drawCircle(xTarg2, yTarg2, 30, paintTarget2);
            canvas.drawCircle(xTarg2, yTarg2, 10, paintTarget);
            xTarg2 = xTarg2 + Targ2V;
        }
        else
        {
            float text2X = xTarg2; //save local version to protect target's value
            float text2Y = yTarg2;
            canvas.drawText("Target 2 Destoryed", text2X - 40, text2Y - 40, blackPaint); //make hit message
        }

        //incrament the position of the target
        if(xTarg1 <= 0 || xTarg1 >= canvas.getWidth())
        {
            Targ1V = Targ1V * -1;
        }
        if(xTarg2 <= 0 || xTarg2 >= canvas.getWidth())
        {
            Targ2V = Targ2V * -1;
        }


        if(!isTarget1Destroyed && xBall > xTarg1 - 50 && xBall < xTarg1 + 50 && yBall < yTarg1 +50 && yBall > yTarg1 - 50)
        {
            isTarget1Destroyed = true;
            toShoot = false;
        }
        if(!isTarget2Destroyed && xBall > xTarg2 - 50 && xBall < xTarg2 + 50 && yBall < yTarg2 + 50 && yBall > yTarg2 - 50)
        {
            isTarget2Destroyed = true;
            toShoot = false;
        }
        if(!isCanonDestroyed && xBall > X_CANNON - 75 && xBall < X_CANNON + 75 && yBall < 1150 + 100 && yBall > 1150 - 100 && count > 20)
        {
            isCanonDestroyed = true;
            toShoot = false;
        }

        Rect r = new Rect(X_CANNON - 50, Y_CANNON - 50, 400, 1200); //generate simple cannon

        if(!isCanonDestroyed) {
            canvas.save(); //saves the current state of the canvas
            double degreesAngle = Math.toDegrees(angle); //convert angle for rotate
            canvas.rotate((float) degreesAngle, X_CANNON, Y_CANNON);
            canvas.drawRect(r, blackPaint);
            canvas.restore(); //restore the canvas so nothing is all messed up
        }
        else
        {
            canvas.drawText("Cannon Destoryed", X_CANNON - 100, Y_CANNON, blackPaint);
        }

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
            double dX = xPos - X_CANNON;
            double dY = yPos - Y_CANNON; //might need absolute value because of coordinate system
            angle = atan(dY / dX);
            if (xPos <= X_CANNON) {
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

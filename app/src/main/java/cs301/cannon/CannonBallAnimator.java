package cs301.cannon;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by travanti16 on 11/1/2015.
 */
public class CannonBallAnimator implements Animator {
    private int count = 0;

    @Override
    public int interval() {
        return 0;
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


    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}

package GunView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FieldView extends View {

    private Bitmap fieldBitmap;
    private Bitmap robotBitmap;
    private float robotX;
    private float robotY;
    private float robotH = 45f;
    private float robotW = 35f;
    private Paint robotPaint;
    private Paint fieldPaint;

    public FieldView(Context context) {
        super(context);
        init();
    }

    public FieldView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init();
    }
    private void init(){
        fieldPaint = new Paint();
        fieldPaint.setColor(Color.rgb(200,230,200));
        fieldPaint.setStyle(Paint.Style.FILL);

        robotPaint = new Paint();
        robotPaint.setColor(Color.rgb(0,230,200));
        robotPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int w = 360;
        int h = 360;

        canvas.drawRect(0,0,w,h,fieldPaint);

        canvas.drawRect(robotX-robotW/2,robotY-robotH/2,robotX+robotW/2,robotY-robotH/2,robotPaint);
    }
    public void moveRobot(float dx , float dy){
        robotX += dx;
        robotY +=dy;
        invalidate();
    }
}

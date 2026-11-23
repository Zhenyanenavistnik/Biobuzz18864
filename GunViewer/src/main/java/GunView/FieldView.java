package GunView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class FieldView extends View {

    private Paint fieldPaint;
    private Paint gridPaint;
    private Paint robotPaint;
    private Paint nosePaint;

    private float robotX = 200f;
    private float robotY = 200f;
    private float robotRadius = 30f;
    private float robotAngle = 0f; // направление «носа» в градусах

    public FieldView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);

        fieldPaint = new Paint();
        fieldPaint.setColor(Color.rgb(30, 120, 30)); // зелёное поле
        fieldPaint.setStyle(Paint.Style.FILL);

        gridPaint = new Paint();
        gridPaint.setColor(Color.rgb(60, 160, 60));
        gridPaint.setStrokeWidth(1f);
        gridPaint.setStyle(Paint.Style.STROKE);

        robotPaint = new Paint();
        robotPaint.setColor(Color.rgb(200, 40, 40)); // красный робот
        robotPaint.setStyle(Paint.Style.FILL);
        robotPaint.setAntiAlias(true);

        nosePaint = new Paint();
        nosePaint.setColor(Color.WHITE);
        nosePaint.setStrokeWidth(4f);
        nosePaint.setStyle(Paint.Style.STROKE);
        nosePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. Поле — прямоугольник на весь View
        canvas.drawRect(0, 0, getWidth(), getHeight(), fieldPaint);

        // 2. Сетка каждые 100 px
        int step = 100;
        for (int x = 0; x < getWidth(); x += step) {
            canvas.drawLine(x, 0, x, getHeight(), gridPaint);
        }
        for (int y = 0; y < getHeight(); y += step) {
            canvas.drawLine(0, y, getWidth(), y, gridPaint);
        }

        // 3. Робот — круг
        canvas.drawCircle(robotX, robotY, robotRadius, robotPaint);

        // 4. «Нос» — линия из центра круга в направлении угла
        double rad = Math.toRadians(robotAngle);
        float noseX = robotX + (float) (Math.cos(rad) * robotRadius);
        float noseY = robotY + (float) (Math.sin(rad) * robotRadius);
        canvas.drawLine(robotX, robotY, noseX, noseY, nosePaint);
    }

    // Перетаскивание мышью/пальцем
    private float lastTouchX, lastTouchY;
    private boolean dragging = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                dragging = true;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (dragging) {
                    robotX += event.getX() - lastTouchX;
                    robotY += event.getY() - lastTouchY;
                    lastTouchX = event.getX();
                    lastTouchY = event.getY();
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
                dragging = false;
                return true;
        }
        return super.onTouchEvent(event);
    }

    // Управление стрелками
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        float step = 20f;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:  robotX -= step; break;
            case KeyEvent.KEYCODE_DPAD_RIGHT: robotX += step; break;
            case KeyEvent.KEYCODE_DPAD_UP:    robotY -= step; break;
            case KeyEvent.KEYCODE_DPAD_DOWN:  robotY += step; break;
            default: return super.onKeyDown(keyCode, event);
        }
        invalidate();
        return true;
    }

    public void moveRobot(float dx, float dy) {
        robotX += dx;
        robotY += dy;
        invalidate();
    }
}
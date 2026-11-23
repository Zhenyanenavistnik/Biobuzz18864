package GunView;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;

public class FloatingService extends Service {

    private WindowManager windowManager;
    private FieldView robotFieldView;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        robotFieldView = new FieldView(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                600,                       // ширина окна в px
                600,                       // высота окна в px
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 100;
        params.y = 100;

        windowManager.addView(robotFieldView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (robotFieldView != null && windowManager != null) {
            windowManager.removeView(robotFieldView);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
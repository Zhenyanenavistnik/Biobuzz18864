package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.teamcode.Utils.Vectors.Vector2d;

public class Utils {

    public static boolean samePos(Vector2d a, Vector2d b) {
        return a != null && b != null
                && Math.abs(a.x - b.x) < 1e-6
                && Math.abs(a.y - b.y) < 1e-6;
    }
    public static double cut(double min,double max,double value){
        return Math.max(Math.min(max,value),min);
    }
}

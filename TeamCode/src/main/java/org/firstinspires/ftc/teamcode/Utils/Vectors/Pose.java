package org.firstinspires.ftc.teamcode.Utils.Vectors;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Pose {
    public  double h;
    public  Vector2d vector;

    public Pose(double h, Vector2d vector) {
        this.h = h;
        this.vector = new Vector2d(vector);
    }

    public Pose(double h, double x, double y) {
        this.h = h;
        this.vector = new Vector2d(x,y) ;
    }

    public void plus(Pose b){
        h += b.h;
        vector.plus(b.vector);
    }

    public void minus(Pose b){
        h -= b.h;
        vector.minus(b.vector);
    }

    public void multiply(double b){
        h *= b;
        vector.multiply(b);
    }
    public static Pose fromRR(Pose2D pose2D){
        return new Pose(pose2D.getHeading(AngleUnit.RADIANS),pose2D.getX(DistanceUnit.CM),pose2D.getY(DistanceUnit.CM));
    }
}

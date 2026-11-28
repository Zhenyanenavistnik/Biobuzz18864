package org.firstinspires.ftc.teamcode.Utils.Vectors;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/*
    x
    |
y---|---
    |
*/

public class Vector2d {

    public double y;
    public double x;

    public void rotate(double rad){
        double x1 = x * cos(rad) - y * sin(rad);
        double y1 = x * sin(rad) + y * cos(rad);
        x = x1;
        y = y1;
    }

    public double dot(Vector2d b){
        return x*b.x + y*b.y;
    }

    public void plus(Vector2d b) {
        x += b.x;
        y += b.y;
    }


    public void minus(Vector2d b) {
        x -= b.x;
        y -= b.y;
    }

    public void multiply(double b) {
        x *= b;
        y *= b;
    }

    public Vector2d() {
        x = 0;
        y = 0;
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d vector2d) {
        x = vector2d.x;
        y = vector2d.y;
    }

    public double length(){
        return Math.sqrt(x*x+y*y);
    }

    public double lengthSquare(){
        return x*x + y*y;
    }

    public void norm(){
        double h = Math.atan2(y,x);
        x = cos(h);
        y = sin(h);
    }

    public double getAngle(){
        return Math.atan2(y,x);
    }

}

package org.firstinspires.ftc.teamcode.Modules.RobotModules;

import static org.firstinspires.ftc.teamcode.Config.StartConfig.gunConfig;
import static org.firstinspires.ftc.teamcode.Utils.Utils.samePos;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.ServoMotor;
import org.firstinspires.ftc.teamcode.Modules.Module;
import org.firstinspires.ftc.teamcode.Service.RobotInfo;
import org.firstinspires.ftc.teamcode.Utils.Utils;
import org.firstinspires.ftc.teamcode.Utils.Vectors.Pose;
import org.firstinspires.ftc.teamcode.Utils.Vectors.Vector2d;
import org.opencv.core.Mat;

import java.util.Vector;

public class GunModule implements Module {

    ServoMotor servoMotor;

    Servo gunServo;


    private Vector2d lastPos;
    private Pose curPos;
    private Vector2d goalPos;

    private final double con = Math.sqrt(9.8/(2*gunConfig.zDist));
    private final double h = 4*gunConfig.zDist*gunConfig.zDist;

    private Double turn;
    private double gunTurn;

    private double v1;
    private double v2;

    private double angle;
    private double servoAngle;

    private boolean upd;

    @Override
    public void init() {
        curPos = RobotInfo.getInstance().robotPos;
        goalPos = RobotInfo.getInstance().goalPose;
        turn = RobotInfo.getInstance().gunAngle;

        lastPos = new Vector2d(curPos.vector);

        servoMotor =  DevicePool.getInstance().servoMotor;
        gunServo = DevicePool.getInstance().gunServo;

    }

    @Override
    public void update() {
        if(!Utils.samePos(lastPos,curPos.vector)){
            upd = true;
            lastPos = new Vector2d(curPos.vector);

            turn = Math.atan2(goalPos.y-curPos.vector.y,goalPos.x-curPos.vector.x);
            /*
            if(turn<0){
                turn += 2*Math.PI;
            }

             */

            Vector2d lV = new Vector2d(curPos.vector);
            lV.minus(goalPos);
            double l2 = lV.x* lV.x+lV.y*lV.y;
            double hyp = Math.sqrt(l2+h);

            v1 = hyp * con;

            angle = 0.5 * Math.acos((l2 - h)/(l2 + h));

        }else {
            upd = false;
        }
    }
    @Override
    public void lateUpdate(){
        if(upd){
            v2 = v1/(2*Math.PI* gunConfig.radFlyWheal);// об/с

            servoAngle = Utils.cut(gunConfig.angelMin,gunConfig.angelMax,angle);
            servoAngle = (servoAngle- gunConfig.angelMin)/ (gunConfig.angelMax- gunConfig.angelMin);

            double error = turn - curPos.h;
            gunTurn = Math.atan2((Math.sin(error)),Math.cos(error));

            servoMotor.setPos(gunTurn);
        }
    }
    @Override
    public void deviceSetUpdate(){
        servoMotor.update();
        gunServo.setPosition(servoAngle);
    }


}

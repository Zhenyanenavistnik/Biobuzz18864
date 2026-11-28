package org.firstinspires.ftc.teamcode.Hardware.Devices;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.Service.RobotInfo;
import org.firstinspires.ftc.teamcode.Utils.Vectors.Pose;

public class PinPointPlus {

    private final GoBildaPinpointDriver pinpointDriver;

    public PinPointPlus(GoBildaPinpointDriver pinpointDriver) {
        this.pinpointDriver = pinpointDriver;
    }

    public void init(){
        pinpointDriver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpointDriver.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.REVERSED);
        pinpointDriver.setOffsets(12.589,0, DistanceUnit.METER);
        pinpointDriver.setPosition(new Pose2D(DistanceUnit.METER, RobotInfo.getInstance().startPose.vector.x, RobotInfo.getInstance().startPose.vector.y,
                AngleUnit.RADIANS,RobotInfo.getInstance().startPose.h));
        pinpointDriver.recalibrateIMU();
    }

    public void update(){
        pinpointDriver.update();
    }
    public Pose getPose() {
        return Pose.fromRR(pinpointDriver.getPosition());
    }

    public Pose getVel(){
        return new Pose(pinpointDriver.getHeadingVelocity(UnnormalizedAngleUnit.RADIANS),
                pinpointDriver.getVelX(DistanceUnit.METER),
                pinpointDriver.getVelY(DistanceUnit.METER));
    }

    public void setPos(double x, double y, double h){
        pinpointDriver.setPosition(new Pose2D(DistanceUnit.METER, x, y,
                AngleUnit.RADIANS,h));
    }



}

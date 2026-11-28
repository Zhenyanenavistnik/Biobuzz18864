package org.firstinspires.ftc.teamcode.Service;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Utils.Vectors.Pose;
import org.firstinspires.ftc.teamcode.Utils.Vectors.Vector2d;

public class RobotInfo {
    private static final RobotInfo Instance = new RobotInfo();
    public static RobotInfo getInstance(){
        return Instance;
    }

    public Pose robotPos;
    public Double gunAngle;

    public Vector2d goalPose;

    public Pose startPose = new Pose(0,0,0);
}

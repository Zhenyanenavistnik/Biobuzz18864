package org.firstinspires.ftc.teamcode.Modules.RobotModules;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;
import org.firstinspires.ftc.teamcode.Modules.Module;
import org.firstinspires.ftc.teamcode.Utils.Vectors.Pose;

public class DriveTrainModule implements Module {

    DcMototPlus RF;
    DcMototPlus RB;
    DcMototPlus LF;
    DcMototPlus LB;

    public Pose target;

    @Override
    public void init() {
        RF = DevicePool.getInstance().RF;
        RB = DevicePool.getInstance().RB;
        LF = DevicePool.getInstance().LF;
        LB = DevicePool.getInstance().LB;
    }

    @Override
    public void update() {

    }

    private void motorSet(double forward,double horizontal,double turn){

        double deadZone = 0.2;
        if (Math.abs(forward) < deadZone) forward = 0;
        if (Math.abs(horizontal) < deadZone) horizontal = 0;
        if (Math.abs(turn) < deadZone) turn = 0;

        if(forward == 0 && horizontal == 0 && turn == 0) {
            stopAll();
            return;
        }

        double del = Math.max(Math.abs(forward) + Math.abs(horizontal) + Math.abs(turn), 1);

        double LFpower = (forward + horizontal + turn)/del;
        double RFpower = (forward - horizontal - turn)/del;
        double LBpower = (forward - horizontal + turn)/del;
        double RBpower = (forward + horizontal - turn)/del;

        RF.setPower(RFpower);
        RB.setPower(RBpower);
        LF.setPower(LFpower);
        LB.setPower(LBpower);

    }

    private void stopAll(){
        RF.setPower(0);
        RB.setPower(0);
        LF.setPower(0);
        LB.setPower(0);
    }
}

package org.firstinspires.ftc.teamcode.OpModes.Tests;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

public class TestGun extends BaseOpMode {

    DcMototPlus gunMotor;

    @Override
    protected void initRun() {
        gunMotor = DevicePool.getInstance().gunMotorP;
    }

    @Override
    protected void loopRun() {
        if(gamepad1.aWasPressed()){
            gunMotor.setPower(1);
        }
        if(gamepad1.bWasPressed()){
            gunMotor.setPower(0);
        }
    }
}

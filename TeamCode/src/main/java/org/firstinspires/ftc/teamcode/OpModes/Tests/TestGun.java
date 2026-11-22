package org.firstinspires.ftc.teamcode.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

@TeleOp(name = "GunTest", group = "Tests")
public class TestGun extends BaseOpMode {

    DcMototPlus gunMotor;
    DcMototPlus gunMotorB;

    @Override
    protected void initRun() {
        gunMotor = DevicePool.getInstance().gunMotorP;
        gunMotorB = DevicePool.getInstance().gunMotorPB;
    }

    @Override
    protected void loopRun() {
        if(gamepad1.aWasPressed()){
            gunMotor.setPower(1);
            gunMotorB.setPower(1);
        }
        if(gamepad1.bWasPressed()){
            gunMotor.setPower(0);
            gunMotorB.setPower(0);
        }
    }
}

package org.firstinspires.ftc.teamcode.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;
import org.firstinspires.ftc.teamcode.Hardware.Devices.ServoMotor;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

@TeleOp(name = "ServoMotorTest", group = "Tests")
public class ServoMotorTest extends BaseOpMode {

    ServoMotor servoMotor;

    double turn;

    @Override
    protected void initRun() {servoMotor =  DevicePool.getInstance().servoMotor;}
    @Override
    protected void loopRun() {

        servoMotor.update();

        telemetry.addData("pid",servoMotor.pidU);
        telemetry.addData("pos",servoMotor.getPos());
        telemetry.addData("targetPos",Math.toDegrees(servoMotor.targetPos));
        telemetry.addData("power",servoMotor.poweeeer);
        telemetry.update();

        if(gamepad1.aWasPressed()){
            turn +=15;
            servoMotor.setPos(turn);
        }
        if(gamepad1.bWasPressed()){
            turn -=15;
            servoMotor.setPos(turn);
        }
    }
}

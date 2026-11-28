package org.firstinspires.ftc.teamcode.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

@TeleOp(name = "ServoTuner", group = "Tests")
public class ServoTuner extends BaseOpMode {

    Servo servo;

    @Override
    protected void initRun() {
        servo = DevicePool.getInstance().gunServo;
    }

    @Override
    protected void loopRun() {
        if(gamepad1.aWasPressed()){
            servo.setPosition(servo.getPosition()+0.05);
        }
        if(gamepad1.bWasPressed()){
            servo.setPosition(servo.getPosition()-0.05);
        }
        telemetry.addData("pid",servo.getPosition());
    }
}

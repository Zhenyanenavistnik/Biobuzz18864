package org.firstinspires.ftc.teamcode.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

@TeleOp(name = "EncoderTest", group = "Tests")
public class EncoderTest extends BaseOpMode {
    DcMototPlus dcMotorEx;

    @Override
    protected void initRun() {
        dcMotorEx = DevicePool.getInstance().gunMotorP;
        dcMotorEx.reset();
    }

    @Override
    protected void loopRun() {
        telemetry.addData("tick",dcMotorEx.getCurrent());
    }
}

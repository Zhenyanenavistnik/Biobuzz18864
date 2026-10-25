package org.firstinspires.ftc.teamcode.Hardware.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class DcMototPlus {

    private int dir =1;
    private final DcMotorEx dcMotorEx;

    public DcMototPlus(DcMotorEx dcMotorEx) {
        this.dcMotorEx = dcMotorEx;
    }

    public double getPosition() {
        return dir*dcMotorEx.getCurrentPosition();
    }

    public void setPower(double power) {
        dcMotorEx.setPower(dir*power);
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        dcMotorEx.setDirection(direction);
    }

    public double getVel() {
        return dir*dcMotorEx.getVelocity();
    }

    public double getCurrent() {
        return dcMotorEx.getCurrent(CurrentUnit.AMPS);
    }

    public void reset() {
        dcMotorEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        dcMotorEx.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior m) {
        dcMotorEx.setZeroPowerBehavior(m);
    }

}


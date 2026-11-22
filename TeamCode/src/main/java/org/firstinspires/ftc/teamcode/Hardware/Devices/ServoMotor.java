package org.firstinspires.ftc.teamcode.Hardware.Devices;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Utils.PID.Pid;
import org.firstinspires.ftc.teamcode.Utils.PID.PidStatus;

public class ServoMotor {

    public double poweeeer;

    private double center;
    private double pow ;

    public double pidU;
    private Pid pid;

    private int dir = 1;

    private double pos;
    public double targetPos = 0;

    private final CRServo servo;

    private final DcMotorEx dcMotorEx;

    public ServoMotor(CRServo servo, DcMotorEx dcMotorEx, PidStatus status,double center) {
        this.center = center;
        this.servo = servo;
        this.dcMotorEx = dcMotorEx;

        pid = new Pid(status);

        pow = 1-center;
    }
    public void setDir(int dir) {
        this.dir = dir;
    }
    public double getPos(){
        return Math.toDegrees(pos);
    }
    public void reset() {
        dcMotorEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        dcMotorEx.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setPower(double power){
        servo.setPower(power);
    }

    public void setPos(double degrees){
        targetPos = Math.toRadians(degrees);
    }

    public void update(){
        pos = ((double) (dir * dcMotorEx.getCurrentPosition()) /2400)*2 * Math.PI;
        pid.setTarget(targetPos);
        pid.setPos(pos);
        pid.update();

        pidU = pid.getU();

        double power = center - pid.getU()*pow;

        poweeeer = power;

        servo.setPower(power);
    }

}

/*
так, мне нужно, чтобы я задавал позицию в градусах, а он перемещался в эту позицию
что мне нужно дя этого?
1) знать его настоящую позицию
  нужно узнать тики энкодера и настроить, чтобы отображались радианы/градусы при повороте энкодера
2)нужен пид для задания мощи, чтобы он видел ошибку и исправлял ее
 */

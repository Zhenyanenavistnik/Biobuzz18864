package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Config.StartConfig.gunConfig;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Config.StartConfig;
import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;
import org.firstinspires.ftc.teamcode.Hardware.Devices.PinPointPlus;
import org.firstinspires.ftc.teamcode.Hardware.Devices.ServoMotor;
import org.firstinspires.ftc.teamcode.Utils.PID.PidStatus;

public class DevicePool {

    private HardwareMap hardwareMap;
    private static final  DevicePool Instance = new DevicePool();
    public static DevicePool getInstance(){
        return Instance;
    }

    //       DEVICES          //

    public DcMototPlus gunMotorP;

    public DcMototPlus gunMotorPB;

    public DcMototPlus RF;
    public DcMototPlus RB;
    public DcMototPlus LF;
    public DcMototPlus LB;

    public ServoMotor servoMotor;

    public Servo gunServo;

    public PinPointPlus odo;





    public void init(HardwareMap map){
        hardwareMap = map;

        servoMotor = createServoMotor("servoMotor","ServoEncoder", gunConfig.servoPid, gunConfig.servoCenter);

        gunMotorP = createDcMotorPlus("gunMotorP");

        gunMotorPB = createDcMotorPlus("gunMotorPB");

        gunServo = createServo("gunServo");

        odo = createOdo("PinPoint");

        /*
        RF = createDcMotorPlus("RF");
        RB = createDcMotorPlus("RB");
        LF = createDcMotorPlus("LF");
        LB = createDcMotorPlus("LB");

         */

        gunServo.scaleRange(0,1);

        gunMotorP.setDir(-1);

        servoMotor.setDir(-1);

        gunServo.setDirection(Servo.Direction.FORWARD);

        gunMotorP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        /*
        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

         */

        gunMotorP.reset();
        gunMotorPB.reset();

        servoMotor.reset();
        /*
        RB.reset();
        RF.reset();
        LB.reset();
        LF.reset();

         */

        gunServo.setPosition(0.5);
    }

    private DcMototPlus createDcMotorPlus(String name){
        return new DcMototPlus(hardwareMap.get(DcMotorEx.class, name));
    }

    private ServoMotor createServoMotor(String ServoName, String EncoderName, PidStatus status,double center){
        return new ServoMotor(hardwareMap.get(CRServo.class,ServoName),hardwareMap.get(DcMotorEx.class,EncoderName),status,center);
    }
    private Servo createServo(String name){
        return hardwareMap.get(Servo.class, name);
    }

    private PinPointPlus createOdo(String name){
        return new PinPointPlus(hardwareMap.get(GoBildaPinpointDriver.class,name));
    }
}

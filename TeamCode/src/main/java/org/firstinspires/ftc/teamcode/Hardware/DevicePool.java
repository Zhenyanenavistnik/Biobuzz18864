package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.Devices.DcMototPlus;

public class DevicePool {

    private HardwareMap hardwareMap;
    private static final  DevicePool Instance = new DevicePool();
    public static DevicePool getInstance(){
        return Instance;
    }

    //       DEVICES          //

    public DcMototPlus gunMotorP;

    public void init(HardwareMap map){
        hardwareMap = map;

        gunMotorP = createDcMotorPlus("gunMotorP");

        gunMotorP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gunMotorP.reset();
    }

    private DcMototPlus createDcMotorPlus(String name){
        return new DcMototPlus(hardwareMap.get(DcMotorEx.class, name));
    }
}

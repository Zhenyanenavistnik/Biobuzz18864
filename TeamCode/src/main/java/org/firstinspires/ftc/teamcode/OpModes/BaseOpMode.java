package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Service.Service;

public abstract class BaseOpMode extends LinearOpMode {

    protected Service service;
    private void initOpMode() {
        DevicePool.getInstance().init(hardwareMap);
    }
    protected void initConfig(){}
    protected void modulesReplace(){}
    protected void initRun(){}
    protected void lastRun(){}
    protected abstract void  loopRun();

    private void robotInit(){
        service = new Service();
        service.factoryInit();
        service.modulesInit();
    }


    @Override
    public void runOpMode() {
        initConfig();
        initOpMode();
        robotInit();
        modulesReplace();
        initRun();
        waitForStart();
        while (opModeIsActive()) {
            loopRun();
            service.update();
        }
        lastRun();
    }
}

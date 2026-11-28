package org.firstinspires.ftc.teamcode.Modules.RobotModules;

import org.firstinspires.ftc.teamcode.Hardware.DevicePool;
import org.firstinspires.ftc.teamcode.Hardware.Devices.PinPointPlus;
import org.firstinspires.ftc.teamcode.Modules.Module;
import org.firstinspires.ftc.teamcode.Service.RobotInfo;

public class Localizer implements Module {

    PinPointPlus odo;
    @Override
    public void init() {
        odo = DevicePool.getInstance().odo;
        odo.init();
    }

    @Override
    public void update() {
        odo.update();
        RobotInfo.getInstance().robotPos = odo.getPose();
    }
}

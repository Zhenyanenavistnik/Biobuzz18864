package org.firstinspires.ftc.teamcode.Config;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Utils.PID.PidStatus;

@Config
public class StartConfig {
    public static class GunConfig {
        public PidStatus servoPid = new PidStatus(0.5,0,0.00,0,0,0,0);
        public double servoCenter = -0.1875;
        public double cof = 2.5;
    }
    public static GunConfig gunConfig = new GunConfig();
}

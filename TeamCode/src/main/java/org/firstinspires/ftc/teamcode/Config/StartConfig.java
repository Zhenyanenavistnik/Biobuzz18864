package org.firstinspires.ftc.teamcode.Config;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Utils.PID.PidStatus;

@Config
public class StartConfig {
    public static class GunConfig {
        public PidStatus servoPid = new PidStatus(0.5,0,0.00,0,0,0,0);
        public double servoCenter = -0.1875;
        public double cof = 2.5;

        public double motorMax = 3000;
        public double zDist = 1.725;//m
        public double radFlyWheal = 0.085;//m
        public double angelMin = 45;
        public double angelMax = 67;
    }
    public static GunConfig gunConfig = new GunConfig();
}

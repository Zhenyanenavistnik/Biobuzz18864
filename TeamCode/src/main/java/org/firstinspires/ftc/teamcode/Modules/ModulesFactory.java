package org.firstinspires.ftc.teamcode.Modules;

import org.firstinspires.ftc.teamcode.Modules.RobotModules.GunModule;

import java.util.ArrayList;
import java.util.Arrays;

public class ModulesFactory {
    private ArrayList<Module> modules = new ArrayList<>();
    public ArrayList<Module> getModules() {
        return modules;
    }

    public void init(){
        modules.addAll(Arrays.asList(create()));
    }

    public Module[] create(){
        return new Module[]{
                new GunModule()
        };
    }
}

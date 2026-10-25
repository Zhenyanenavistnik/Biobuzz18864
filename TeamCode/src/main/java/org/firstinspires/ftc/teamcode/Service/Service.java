package org.firstinspires.ftc.teamcode.Service;

import org.firstinspires.ftc.teamcode.Modules.Module;
import org.firstinspires.ftc.teamcode.Modules.ModulesFactory;

public class Service {
    private ModulesFactory factory;

    public void factoryInit(){
        factory = new ModulesFactory();
        factory.init();
    }

    public void modulesInit(){
        for (Module module : factory.getModules()) {
            module.init();
        }
    }

    public void update(){
        for (Module module : factory.getModules()) {
            module.deviceReadUpdate();
        }

        for (Module module : factory.getModules()) {
            module.update();
        }

        for (Module module : factory.getModules()) {
            module.lateUpdate();
        }

        for (Module module : factory.getModules()) {
            module.deviceSetUpdate();
        }
    }
}

package org.firstinspires.ftc.teamcode.Modules;

public interface Module {
    default void deviceReadUpdate(){}
    default void deviceSetUpdate(){}
    default void update(){}
    default void lateUpdate(){}
    default void init(){}
}

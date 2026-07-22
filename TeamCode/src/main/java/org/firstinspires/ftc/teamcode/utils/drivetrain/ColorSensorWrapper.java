package org.firstinspires.ftc.teamcode.utils.drivetrain;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class ColorSensorWrapper {

    public static double minR = 0, maxR = 0;
    public static double minG = 0, maxG = 0;
    public static double minB = 0, maxB = 0;

    //cached color sensor values
    private double red, green, blue;

    Telemetry telemetry;
    private final ColorSensor colorSensor;

    public ColorSensorWrapper(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        this.colorSensor = hardwareMap.get(ColorSensor.class, "BlockColorSensor");
    }

    public void update(){
        red = colorSensor.red();
        blue = colorSensor.blue();
        green = colorSensor.green();

        telemetry.addLine("---COLOR SENSOR---");
        telemetry.addData("red ", red);
        telemetry.addData("green ", green);
        telemetry.addData("blue ", blue);
    }
}

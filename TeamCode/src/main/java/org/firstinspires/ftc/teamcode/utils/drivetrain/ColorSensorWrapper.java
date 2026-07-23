package org.firstinspires.ftc.teamcode.utils.drivetrain;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class ColorSensorWrapper {

    public static double minR = 0.3, maxR = 0.37;
    public static double minG = 0.46, maxG = 0.54;
    public static double minB = 0.16, maxB = 0.18;

    //cached color sensor values
    private double red, green, blue;
    private double sum;

    Telemetry telemetry;
    private final ColorSensor colorSensor;

    public ColorSensorWrapper(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.colorSensor = hardwareMap.get(ColorSensor.class, "BlockColorSensor");
    }

    public void update() {
        red = colorSensor.red();
        blue = colorSensor.blue();
        green = colorSensor.green();
        sum = red + green + blue;

        red /= sum;
        blue /= sum;
        green /= sum;

        telemetry.addLine("---COLOR SENSOR---");
        telemetry.addData("red ", red);
        telemetry.addData("green ", green);
        telemetry.addData("blue ", blue);
    }

    public boolean ballDetected() {
        return inRange(red, minR, maxR) && inRange(green, minG, maxG) && inRange(blue, minB, maxB);
    }

    private boolean inRange(double n, double lower, double upper) {
        return n >= lower && n <= upper;
    }
}

package org.firstinspires.ftc.teamcode.opmode;

import android.graphics.Color;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utils.drivetrain.ColorSensorWrapper;

@TeleOp (name = "ColorSensorTuningTele")
public class ColorSensorTuningTele extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        ColorSensorWrapper colorSensor = new ColorSensorWrapper(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()){
            colorSensor.update();

            telemetry.addData("ball detected: ", colorSensor.ballDetected());
            telemetry.update();
        }
    }
}

package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.lift.Lift;
import org.firstinspires.ftc.teamcode.utils.drivetrain.BatteryVoltageFilter;
import org.firstinspires.ftc.teamcode.utils.drivetrain.MecanumDrive;

public class Day3BrainSTEMRobot {

    private BatteryVoltageFilter batteryVoltageFilter;
    public final MecanumDrive drive;
    public final Lift lift;
    public final Arm arm;

    public Day3BrainSTEMRobot(HardwareMap hardwareMap, Telemetry telemetry, Pose2d initialPose) {
        batteryVoltageFilter = new BatteryVoltageFilter(hardwareMap);
        drive = new MecanumDrive(hardwareMap, initialPose);
        lift = new Lift(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
    }

    public void update() {
        batteryVoltageFilter.update();
        double currentVoltage = batteryVoltageFilter.getVoltage();

        lift.setBatteryVoltage(currentVoltage);
        arm.setBatteryVoltage(currentVoltage);

        drive.pinpoint().update();
        lift.update();
        arm.update();
    }
}

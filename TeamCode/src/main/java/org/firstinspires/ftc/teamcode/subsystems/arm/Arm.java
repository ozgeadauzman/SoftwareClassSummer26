package org.firstinspires.ftc.teamcode.subsystems.arm;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm {

    public static double kG = 0, kS = 0;
    public static double kP = 0, kI = 0, kD = 0;
    private static double radiansPerEncoder = 0;

    private final Telemetry telemetry;
    private final DcMotorEx armMotor;
    private final PIDController pid;

    private double targetAngle;
    private double targetDir;
    private final double armLength = 5;


    private double gravityPower, frictionPower, pidPower, totalPower;


    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        armMotor = hardwareMap.get(DcMotorEx.class, "arm");
        pid = new PIDController(kP, kI, kD);
    }

    public void update() {
        double currentPosition = armMotor.getCurrentPosition();
        double currentAngle = currentPosition*radiansPerEncoder;

        pid.setPID(kP, kI, kD);

        targetDir = Math.signum(targetAngle);

        pidPower = pid.calculate(targetAngle, currentAngle);
        gravityPower = kG * Math.cos(currentAngle) * armLength;
        frictionPower = targetDir * kS;

        totalPower = pidPower+gravityPower+frictionPower;

        armMotor.setPower(totalPower);

        telemetry.addLine("---ARM---");
        telemetry.addData("arm target angle: ", targetAngle);
        telemetry.addData("arm current angle: ", currentAngle);
        telemetry.addData("arm target dir: ", targetDir);
        telemetry.addData("arm motor power: ", armMotor.getPower());

    }

    public void setTargetAngle(double targetAngle) {
        this.targetAngle = targetAngle;
    }
}

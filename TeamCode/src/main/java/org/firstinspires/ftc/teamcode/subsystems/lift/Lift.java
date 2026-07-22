package org.firstinspires.ftc.teamcode.subsystems.lift;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Lift {

    private static double kP = 0, kI = 0, kD = 0;
    private static double kS = 0;

    private static PIDController pid;

    private final Telemetry telemetry;
    private final DcMotorEx liftMotor;

    private double targetPos;
    private double targetDir;

    private double gravityPower, frictionPower, pidPower, totalPower;

    private InterpLUT liftLUT = new InterpLUT();
    private double[] liftPositions = {100, 200, 300, 400};
    private double[] kGValues = {0.2, 0.3, 0.4, 0.3};

    public Lift(HardwareMap hardwareMap, Telemetry telemetry) {
        liftMotor = hardwareMap.get(DcMotorEx.class, "lift");
        this.telemetry = telemetry;
        pid = new PIDController(kP, kI, kD);

        //interpolated lookup table
        for (int i=0; i<4; i++) {
            liftLUT.add(liftPositions[i], kGValues[i]);
        }
        liftLUT.createLUT();
    }

    public void update() {
        double currentPos = liftMotor.getCurrentPosition();
        pid.setPID(kP, kI, kD);
        targetDir = Math.signum(targetPos);

        //get kG from table
        double kG = liftLUT.get(currentPos);

        //control algorithm
        pidPower = pid.calculate(targetPos, currentPos);
        frictionPower = kS * targetDir;
        totalPower = kG + pidPower + frictionPower;
        // can also do Range.clip between -1 and 1 for totalPower

        liftMotor.setPower(totalPower);

        telemetry.addLine("---LIFT---");
        telemetry.addData("lift target pos: ", targetPos);
        telemetry.addData("lift current pos: ", currentPos);
        telemetry.addData("lift target dir: ", targetDir);
        telemetry.addData("lift power: ", liftMotor.getPower());
    }

    public void setTargetPos(double targetPos) {
        this.targetPos = targetPos;
    }

}

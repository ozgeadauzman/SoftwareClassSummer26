package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class TurretEmpty {

    public static double radiansPerEncoder = 0;
    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;

    static double bangBangPower = 0;

    private final DcMotorEx turretMotor;

    private double targetDirection;
    private double currentAngle;
    private double targetAngle;

    private PIDController pid;

    enum TurretState {
        OFF, POINT_AT_ANGLE, SWING_PAST_ANGLE
    }
    private TurretState currentTurretState;

    private final Telemetry telemetry;


    public TurretEmpty(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        turretMotor = hardwareMap.get(DcMotorEx.class, "turret");
        resetTurretEncoder();
        pid = new PIDController(kP, kI, kD);
        setTurretState(TurretState.OFF);
    }


    public void update() {
        currentAngle = turretMotor.getCurrentPosition();
        pid.setPID(kP, kI, kD);

        switch(currentTurretState) {
            case OFF:
                turretMotor.setPower(0.0);
                break;
            case POINT_AT_ANGLE:
                double power = pid.calculate(targetAngle, currentAngle);
                turretMotor.setPower(power);
                break;
//            case SWING_PAST_ANGLE:
//                turretMotor.setPower(bangBangPower);
//
//                setTurretState(TurretState.OFF);
//                break;
        }

        telemetry.addLine("TURRET--------");
        telemetry.addData("turret power:", turretMotor.getPower());
        telemetry.addData("turret state:", currentTurretState);
        telemetry.addData("turret position:", turretMotor.getCurrentPosition());
        telemetry.addData("turret angle:", getCurrentAngle());

    }


    public TurretState getTurretState(){
        return currentTurretState;
    }

    public void setTurretState(TurretState turretState){
        currentTurretState = turretState;
    }

    public double getCurrentAngle(){
        return currentAngle;
    }

    public void setTargetAngle(double angle){
        targetAngle = angle;
    }

    public void resetTurretEncoder(){
        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentAngle = 0.0;
        turretMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


}

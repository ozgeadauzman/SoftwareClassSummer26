package org.firstinspires.ftc.teamcode.subsystems.collector;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class CollectorTemplate {

    // declare your static variables below
    private final DcMotorEx intakeMotor;

    double intakePower = 0.5;
    double outtakePower = -0.5;

    enum IntakeState {
        OFF,
        INTAKE,
        OUTTAKE
    }
    private IntakeState currentIntakeState;

    private final Telemetry telemetry;

    public CollectorTemplate(HardwareMap hardwareMap, Telemetry telemetry) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        this.telemetry = telemetry;
    }

    public void update() {

        switch (currentIntakeState) {
            case OFF:
                if (intakeMotor.getPower() != 0.0) {
                    intakeMotor.setPower(0.0);
                }
                break;
            case INTAKE:
                if (intakeMotor.getPower() != intakePower) {
                    intakeMotor.setPower(intakePower);
                }
                break;
            case OUTTAKE:
                if (intakeMotor.getPower() != outtakePower) {
                    intakeMotor.setPower(outtakePower);
                }
                break;
        }

        telemetry.addLine("---COLLECTOR---");
        telemetry.addData("intake power:", intakeMotor.getPower());
        telemetry.addData("intake state", currentIntakeState);
    }

    // finish these functions below
    public IntakeState getIntakeState() {
        return currentIntakeState;
    }
    public void setIntakeState(IntakeState intakeState) {
        currentIntakeState = intakeState;
    }

}
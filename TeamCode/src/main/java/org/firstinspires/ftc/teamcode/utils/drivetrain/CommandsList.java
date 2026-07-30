package org.firstinspires.ftc.teamcode.utils.drivetrain;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.ftccommon.CommandList;

import org.firstinspires.ftc.teamcode.subsystems.arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.collector.CollectorTemplate;
import org.firstinspires.ftc.teamcode.subsystems.lift.Lift;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretEmpty;

public class CommandsList {

    public static InstantCommand collectorIntake(CollectorTemplate collector) {
        return new InstantCommand(() -> collector.setIntakeState(CollectorTemplate.IntakeState.INTAKE));
    }

    public static InstantCommand collectorOff(CollectorTemplate collector) {
        return new InstantCommand(() -> collector.setIntakeState(CollectorTemplate.IntakeState.OFF));
    }

    public static InstantCommand turretToCenter(TurretEmpty turret) {
        return new InstantCommand(() -> {
            turret.setTurretState(TurretEmpty.TurretState.POINT_AT_ANGLE);
            turret.setTargetAngle(0);
        });
    }

    public static InstantCommand liftToTargetPosition(Lift lift, double position) {
        return new InstantCommand(() -> lift.setTargetPos(position));
    }

    public static InstantCommand armToTargetAngle(Arm arm, double angleRad) {
        return new InstantCommand(() -> arm.setTargetAngle(angleRad));
    }

    public static InstantCommand armTo90Degrees(Arm arm) {
        return new InstantCommand(() -> arm.setTargetAngle(Math.toRadians(90)));
    }
}

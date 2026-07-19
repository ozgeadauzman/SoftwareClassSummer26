package org.firstinspires.ftc.teamcode.subsystems.collector;

import android.nfc.tech.MifareUltralight;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name="CollectorTestTele")
public abstract class CollectorTestOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.setMsTransmissionInterval(20);

        CollectorTemplate collector = new CollectorTemplate(hardwareMap, telemetry);

        telemetry.addLine("ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.right_trigger > 0.1) {
                collector.setIntakeState(CollectorTemplate.IntakeState.INTAKE);
            } else if (gamepad1.left_trigger > 0.1) {
                collector.setIntakeState(CollectorTemplate.IntakeState.OUTTAKE);
            } else {
                collector.setIntakeState(CollectorTemplate.IntakeState.OFF);
            }

            collector.update();
            telemetry.update();
        }

    }
}


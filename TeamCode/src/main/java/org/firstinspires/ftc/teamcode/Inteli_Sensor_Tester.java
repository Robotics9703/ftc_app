package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


@Autonomous(name="Inteli_Sensor_Tester", group="Linear Opmode")
public class Inteli_Sensor_Tester extends LinearOpMode {

    private Servo testServo = null;

    private TouchSensor touchSensor;
    private OpticalDistanceSensor odsSensor;
    private ColorSensor colorSensor;


    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize
        testServo = hardwareMap.servo.get("testServo");
        touchSensor = hardwareMap.touchSensor.get("touch sensor");
        odsSensor = hardwareMap.opticalDistanceSensor.get("ODS");
        colorSensor = hardwareMap.colorSensor.get("color sensor");

        float hsvValues[] = {0F, 0F, 0F};

        final float values[] = hsvValues;

        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);

        boolean bPrevState = false;
        boolean bCurrState = false;

        boolean bLedOn = true;

        colorSensor.enableLed(bLedOn);

        waitForStart();

        while (opModeIsActive()) {

            bCurrState = gamepad1.x;

            if ((bCurrState == true) && (bCurrState != bPrevState)) {

                bLedOn = !bLedOn;
                //colorSensor.enableLed(bLedOn);
            }

            bPrevState = bCurrState;

            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });



            double counter = 0;

            // send the info back to driver station using telemetry function.
            telemetry.addData("Raw", odsSensor.getRawLightDetected());
            telemetry.addData("Normal", odsSensor.getLightDetected());

            telemetry.addData("Amount Pressed:", String.valueOf(counter));// send the info back to driver station using telemetry function.

            if (touchSensor.isPressed()) {
                telemetry.addData("Touch:", "Is Pressed");
                counter = counter + 1;
            } else {
                telemetry.addData("Touch:", "Is Not Pressed");
            }
            telemetry.update();
        }
    }
}

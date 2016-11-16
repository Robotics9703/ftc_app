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

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F,0F,0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);

        // bPrevState and bCurrState represent the previous and current state of the button.
        boolean bPrevState = false;
        boolean bCurrState = false;

        // bLedOn represents the state of the LED.
        boolean bLedOn = true;


        // Set the LED in the beginning
        colorSensor.enableLed(bLedOn);

        waitForStart();

        while (opModeIsActive())

            // check the status of the x button on either gamepad.
            bCurrState = gamepad1.x;

            // check for button state transitions.
            if ((bCurrState == true) && (bCurrState != bPrevState))  {

                // button is transitioning to a pressed state. So Toggle LED
                bLedOn = !bLedOn;
                colorSensor.enableLed(bLedOn);
            }

            // update previous state variable.
            bPrevState = bCurrState;

            // convert the RGB values to HSV values.
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

         //   telemetry.update();


            double counter = 0;

            // send the info back to driver station using telemetry function.
            telemetry.addData("Raw", odsSensor.getRawLightDetected());
            telemetry.addData("Normal", odsSensor.getLightDetected());

            telemetry.update();
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

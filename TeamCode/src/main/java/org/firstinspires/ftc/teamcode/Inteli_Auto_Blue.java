package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


@Autonomous(name="Inteli_Auto_Blue", group="Linear Opmode")
public class Inteli_Auto_Blue extends LinearOpMode {

    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor ballM = null;
    private DcMotor pivot = null;
    private DcMotor flapLeft = null;
    private DcMotor flapRight = null;
    private Servo leftArm = null;
    private Servo rightArm = null;

    private TouchSensor touchSensor;
    private OpticalDistanceSensor odsSensor;
    private ColorSensor colorSensor;


    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize
        motorLeft = hardwareMap.dcMotor.get("DriveLeft");
        motorRight = hardwareMap.dcMotor.get("DriveRight");
        ballM = hardwareMap.dcMotor.get("Ball");
        pivot = hardwareMap.dcMotor.get("Pivot");
        flapLeft = hardwareMap.dcMotor.get("flapLeft");
        flapRight = hardwareMap.dcMotor.get("flapRight");
        leftArm = hardwareMap.servo.get("left arm");
        rightArm = hardwareMap.servo.get("right arm");
        touchSensor = hardwareMap.touchSensor.get("touch sensor");
        odsSensor = hardwareMap.opticalDistanceSensor.get("ODS");
        colorSensor = hardwareMap.colorSensor.get("color sensor");

        double Full_Power = -1;
        double Reverse_power = 1;
        double OutS = 0.2;
        double InS = 0.6;

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

        while (opModeIsActive()) {

            do {
            Forward(Full_Power);
        } while(odsSensor.getLightDetected() > 0.5);

            if (odsSensor.getLightDetected() > 0.5){
                StopDriving();
                LeftT(500);
                if (touchSensor.isPressed()){
                    if(colorSensor.red()>= colorSensor.blue()){
                    leftArm.setPosition(0.1);
                    }
                    if(colorSensor.blue()>= colorSensor.red()){
                        leftArm.setPosition(0.8);
                    }
                }
            }

            if(odsSensor.getLightDetected() < 0.5) {
                do {
                    Forward(Full_Power / 2);
                } while (touchSensor.isPressed());
                if (touchSensor.isPressed()) {
                    if (colorSensor.red() >= colorSensor.blue()) {
                        leftArm.setPosition(0.1);
                    }
                    if (colorSensor.blue() >= colorSensor.red()) {
                        leftArm.setPosition(0.8);
                    }
                }
            }






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

            telemetry.update();


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

            //Void Library
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //ForwardT (power, time);
            //BackwardT (power, time);
            //LeftT (time);
            //RightT (time);
            //StopDriving ();
            //BallOut (time);
            //BallIn (time);
            //LeftArm ();
            //RightArm ();
            //LockHook (power, distance(1 rev = 1440));
            //PivotUp (time);
            //PivotDown (time);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        public void ForwardT ( double power, int time)throws InterruptedException {
            motorLeft.setPower(power);
            motorRight.setPower(power);
            Thread.sleep(time);
        }

        public void Forward ( double power)throws InterruptedException {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

        public void BackwardT ( double power, long time)throws InterruptedException {
            motorLeft.setPower(power);
            motorRight.setPower(power);
            Thread.sleep(time);
        }

        public void LeftT ( long time)throws InterruptedException {
            motorLeft.setPower(0);
            motorRight.setPower(1);
            Thread.sleep(time);
        }

        public void RightT ( long time)throws InterruptedException {
            motorLeft.setPower(1);
            motorRight.setPower(0);
            Thread.sleep(time);
        }

        public void StopDriving () {
            motorLeft.setPower(0);
            motorRight.setPower(0);
        }

        public void BallsOut ( long time)throws InterruptedException {
            ballM.setPower(-1);
            Thread.sleep(time);
        }

        public void BallsIn ( long time)throws InterruptedException {
            ballM.setPower(1);
            Thread.sleep(time);
        }

        public void LeftArm ()throws InterruptedException {
            double OutS = 0.2;
            double InS = 0.6;
            leftArm.setPosition(OutS);
            Thread.sleep(1000);
            leftArm.setPosition(InS);
            Thread.sleep(1000);
            leftArm.setPosition(OutS);
            Thread.sleep(1000);
            leftArm.setPosition(InS);
        }

    public void RightArm ()throws InterruptedException {
        double OutS = 0.2;
        double InS = 0.6;
        rightArm.setPosition(OutS);
        Thread.sleep(1000);
        rightArm.setPosition(InS);
        Thread.sleep(1000);
        rightArm.setPosition(OutS);
        Thread.sleep(1000);
        rightArm.setPosition(InS);
    }
}

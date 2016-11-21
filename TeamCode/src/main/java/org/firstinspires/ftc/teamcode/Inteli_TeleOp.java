package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp (name="Inteli_TeleOp", group="Linear Opmode")
public class Inteli_TeleOp extends LinearOpMode {
    /* Declare here any fields you might find useful. */
    //Declare motors
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor ballM1 = null;
    private DcMotor ballM2 = null;
    private DcMotor pivot = null;
    private DcMotor flapLeft = null;
    private DcMotor flapRight = null;
    private Servo leftArm = null;
    private Servo rightArm = null;

    private TouchSensor touchSensor;
    private OpticalDistanceSensor odsSensor;
    private ColorSensor colorSensor;


    public void runOpMode() throws InterruptedException

    {
        //Initialize
        motorLeft = hardwareMap.dcMotor.get("DriveLeft");
        motorRight = hardwareMap.dcMotor.get("DriveRight");
        ballM1 = hardwareMap.dcMotor.get("ballM1");
        ballM2 = hardwareMap.dcMotor.get("ballM2");
        pivot = hardwareMap.dcMotor.get("Pivot");
        flapLeft = hardwareMap.dcMotor.get("flapLeft");
        flapRight = hardwareMap.dcMotor.get("flapRight");
        leftArm = hardwareMap.servo.get("left arm");
        rightArm = hardwareMap.servo.get("right arm");
        touchSensor = hardwareMap.touchSensor.get("touch sensor");
        odsSensor = hardwareMap.opticalDistanceSensor.get("ODS");
        colorSensor = hardwareMap.colorSensor.get("color sensor");

        // Wait for the game to start
        waitForStart();

        // send the info back to driver station using telemetry function.


        // Go go gadget robot!
        while (opModeIsActive())
        {
                // The game pad state has changed. Do something with that!
                // Game pad 1
                    // Tank Drive!!!!
                motorLeft.setPower(gamepad1.left_stick_y);
                motorRight.setPower(gamepad1.right_stick_y);
                    // Climbers!!!!
                if (gamepad1.a)
                {
                    leftArm.setPosition(0.8);
                }
                if (gamepad1.y)
                {
                    leftArm.setPosition(0.7);
                }


                // Game pad 2
                    //Lift!!!!
            ballM1.setPower(gamepad2.right_stick_y);
            ballM2.setPower(gamepad2.right_stick_y);

                    // Flaps!!!!
                flapLeft.setPower(gamepad2.left_trigger);
                if (gamepad2.left_bumper) {
                    flapLeft.setPower(-1);
                }
                else if (gamepad2.left_bumper) {
                    flapLeft.setPower(0);
                }
                flapRight.setPower(gamepad2.right_trigger);
                if (gamepad2.right_bumper) {
                    flapLeft.setPower(-1);
                }
                else if (gamepad2.right_bumper) {
                    flapLeft.setPower(0);
                }

                    //Pivot!!!!
                pivot.setPower(gamepad2.left_stick_y);
                }
            telemetry.update();
            idle();
        }
    }

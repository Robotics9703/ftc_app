package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp (name="Inteli_TeleOp", group="Linear Opmode")
public class Inteli_TeleOp extends LinearOpMode {
    /* Declare here any fields you might find useful. */
    //Declare motors
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor tape = null;
    DcMotor pivot = null;
    DcMotor flapLeft = null;
    DcMotor flapRight = null;
    Servo climber = null;
    TouchSensor touchSensor = null;

    public void runOpMode() throws InterruptedException

    {       
        //Initialize motors
        motorLeft = hardwareMap.dcMotor.get("DriveLeft");
        motorRight = hardwareMap.dcMotor.get("DriveRight");
        tape = hardwareMap.dcMotor.get("Tape");
        pivot = hardwareMap.dcMotor.get("Pivot");
        flapLeft = hardwareMap.dcMotor.get("flapLeft");
        flapRight = hardwareMap.dcMotor.get("flapRight");

        climber = hardwareMap.servo.get("Climber");
        touchSensor = hardwareMap.touchSensor.get("touchSensor");

        tape.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.REVERSE);

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
                    climber.setPosition(0.8);
                }
                if (gamepad1.y)
                {
                    climber.setPosition(0.7);
                }


                // Game pad 2
                    //Lift!!!!
                tape.setPower(gamepad2.right_stick_y);

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

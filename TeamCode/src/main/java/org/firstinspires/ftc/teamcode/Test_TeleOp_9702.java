package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp (name="Test_Tele_OP_9702", group="Linear Opmode")
public class Test_TeleOp_9702 extends LinearOpMode {
    /* Declare here any fields you might find useful. */
    //Declare motors
    DcMotor motorLeft = null;
    DcMotor motorRight = null;


    public void runOpMode() throws InterruptedException

    {
        //Initialize motors
        motorLeft = hardwareMap.dcMotor.get("DriveLeft");
        motorRight = hardwareMap.dcMotor.get("DriveRight");

        motorRight.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start
        waitForStart();

        // send the info back to driver station using telemetry function.


        while (opModeIsActive())
        {
            // The game pad state has changed. Do something with that!
            // Game pad 1
            // Tank Drive!!!!
            motorLeft.setPower(gamepad1.left_stick_y);
            motorRight.setPower(gamepad1.right_stick_y);
            // Ball Shooters


        }
        telemetry.update();
        idle();
    }
}

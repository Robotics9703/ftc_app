package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Disabled
@Autonomous(name="Auto_9702", group="Linear Opmode")
public class Auto_9702 extends LinearOpMode {

    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor BallShooterLeft = null;
    DcMotor BallShooterRight = null;
    DcMotor BallGetter = null;


    TouchSensor touchSensor;



    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize motors
        motorLeft = hardwareMap.dcMotor.get("DriveLeft");
        motorRight = hardwareMap.dcMotor.get("DriveRight");
        BallShooterLeft = hardwareMap.dcMotor.get("BallShooterLeft");
        BallShooterRight = hardwareMap.dcMotor.get("BallShooterRight");
        BallGetter = hardwareMap.dcMotor.get("BallGetter");


        //climber = hardwareMap.servo.get("Climber");

        touchSensor = hardwareMap.touchSensor.get("touch sensor");

        //int counter = 0;

        double Full_Power = -1;
        double Reverse_power = 1;
        //double DownS = 0.2;
        //double UpS = 0.6;


        // tape.setDirection(DcMotor.Direction.REVERSE);
        // motorRight.setDirection(DcMotor.Direction.REVERSE);

// Wait for the game to start

        waitForStart();

        // send the info back to driver station using telemetry function.
        if (touchSensor.isPressed()) {
            telemetry.addData("Touch", "Is Pressed");
        } else {
            telemetry.addData("Touch", "Is Not Pressed");
        }

        idle();


        ForwardT(Full_Power, 5500);
        StopDriving();

        ForwardT(Reverse_power, 1000);
        StopDriving();

        BackwardT(Full_Power, 5500);

    }
    //Void Library
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ForwardT (power, time);
    //BackwardT (power, time);
    //LeftT (time);
    //RightT (time);
    //StopDriving ();
    //TapeOut (time);
    //TapeIn (time);
    //DumpClimbers ();
    //LockHook (power, distance(1 rev = 1440));
    //PivotUp (time);
    //PivotDown (time);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void ForwardT(double power, long time) throws InterruptedException {
        motorLeft.setPower(power);
        motorRight.setPower(power);
        Thread.sleep(time);
    }


    private void ForwardT(double power, int time) throws InterruptedException {
        motorLeft.setPower(power);
        motorRight.setPower(power);
        Thread.sleep(time);
    }

    public void BackwardT(double power, long time) throws InterruptedException {
        motorLeft.setPower(power);
        motorRight.setPower(power);
        Thread.sleep(time);
    }

    public void LeftT(long time) throws InterruptedException {
        motorLeft.setPower(0);
        motorRight.setPower(1);
        Thread.sleep(time);
    }

    public void RightT(long time) throws InterruptedException {
        motorLeft.setPower(1);
        motorRight.setPower(0);
        Thread.sleep(time);
    }

    public void StopDriving() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }


}






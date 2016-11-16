package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Disabled
@Autonomous(name="Inteli_Auto_Red", group="Linear Opmode")
public class Inteli_Auto_Red extends LinearOpMode {

    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor tape = null;
    DcMotor pivot = null;
    DcMotor flapLeft = null;
    DcMotor flapRight = null;


    TouchSensor touchSensor;

    Servo climber = null;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize motors
        motorLeft = hardwareMap.dcMotor.get("DriveLeft");
        motorRight = hardwareMap.dcMotor.get("DriveRight");
        tape = hardwareMap.dcMotor.get("Tape");
        pivot = hardwareMap.dcMotor.get("Pivot");
        flapLeft = hardwareMap.dcMotor.get("flapLeft");
        flapRight = hardwareMap.dcMotor.get("flapRight");

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
        DumpClimbers();
        ForwardT(Reverse_power, 1000);
        StopDriving();
        PivotUp(3000);
        pivot.setPower(0);
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

    public void TapeOut(long time) throws InterruptedException {
        tape.setPower(-1);
        Thread.sleep(time);
    }

    public void TapeIn(long time) throws InterruptedException {
        tape.setPower(1);
        Thread.sleep(time);
    }

    public void DumpClimbers() throws InterruptedException {
        double DownS = 0.2;
        double UpS = 0.6;

        climber.setPosition(DownS);
        Thread.sleep(1000);
        climber.setPosition(UpS);
        Thread.sleep(1000);
        climber.setPosition(DownS);
        Thread.sleep(1000);
        climber.setPosition(UpS);
    }

    public void PivotUp(long time) throws InterruptedException {
        pivot.setPower(1);
        Thread.sleep(time);
    }

    public void PivotDown(long time) throws InterruptedException {
        pivot.setPower(-1);
        Thread.sleep(time);
    }
}






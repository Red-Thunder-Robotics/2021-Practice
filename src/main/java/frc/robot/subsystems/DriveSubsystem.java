/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

   public CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR_CAN, MotorType.kBrushless);
   public CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR_CAN, MotorType.kBrushless);
   public CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR_CAN, MotorType.kBrushless);
   public CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR_CAN, MotorType.kBrushless);

   ADXRS450_Gyro gyro = new ADXRS450_Gyro();

   // Group motors

   public SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeft, backLeft);
   public SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRight, backRight);
   
   // Defining Differential Drive

   public DifferentialDrive differentialRocketLeagueDrive = new DifferentialDrive(leftGroup, rightGroup);


  public void RocketLeagueDrive(double speed, double turn, double stop){
    double turning = 0.0;
    double moving = 0.0;
    double driveSpeed = 0.65; // Max drivespeed
    double rotateSpeed = 0.50; // How fast the bot turns while moving forward/backward
    double swivel = 0.45;  //How fast the bot pivots in place
    if(Math.abs(stop) > 0.5){
      frontLeft.setIdleMode(IdleMode.kBrake);
      frontRight.setIdleMode(IdleMode.kBrake);
      backLeft.setIdleMode(IdleMode.kBrake);
      backRight.setIdleMode(IdleMode.kBrake);
    } else if(speed >= 0.10 || speed <= -0.10){
      frontLeft.setIdleMode(IdleMode.kCoast);
      frontRight.setIdleMode(IdleMode.kCoast);
      backLeft.setIdleMode(IdleMode.kCoast);
      backRight.setIdleMode(IdleMode.kCoast);

        moving = driveSpeed * speed;
        if(Math.abs(turn) > 0.10){
          turning = rotateSpeed * turn;
        }
      } else if(Math.abs(turn) > 0.10){
        frontLeft.setIdleMode(IdleMode.kCoast);
        frontRight.setIdleMode(IdleMode.kCoast);
        backLeft.setIdleMode(IdleMode.kCoast);
        backRight.setIdleMode(IdleMode.kCoast);

        turning = swivel * Math.pow(turn, 3);
      }

    differentialRocketLeagueDrive.arcadeDrive(moving, turning);

  }

  public void getRightMotorSpeed(){

    SmartDashboard.putData(rightGroup);
  }

  //AUTONOMOUS COMMANDS

  public void resetGyro(){
    gyro.reset();
  }

  public  double getGyro(){

    return gyro.getAngle();

  }

  public void barrelRacing(){
    gyro.getAngle();

  }

  public DriveSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

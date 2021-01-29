/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.List;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPipelineResult;
import org.photonvision.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
 
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

   // Create encoder from Spark MAX

  public CANEncoder leftEncoder = frontLeft.getEncoder();
  public CANEncoder rightEncoder = frontRight.getEncoder();

   // Photon Camera
  PhotonCamera camera = new PhotonCamera("photonvision");
  PIDController controller = new PIDController(.1, 0, 0);
  double range = 10.0;  // Degrees of range that it treats as being "on target"

  

  

 

  // static final double kCameraHeight = 0.51; // meters
  // static final double kCameraPitch = 0.436; // radians
  // static final double kTargetHeight = 2.44; // meters

  // Get distance to target.
  //  double distanceMeters = PhotonUtils.calculateDistanceToTargetMeters(
  //    kCameraHeight, kTargetHeight, kCameraPitch, Math.toRadians(camera.getFirstTargetPitch()));

  // DRIVE TRAIN

  public void RocketLeagueDrive(double speed, double turn, double stop){
    double turning = 0.0;
    double moving = 0.0;
    double driveSpeed = 0.75; // Max drivespeed
    double rotateSpeed = 0.55; // How fast the bot turns while moving forward/backward
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

  

  //AUTONOMOUS METHODS

  public void setBrakeMode(){
    frontLeft.setIdleMode(IdleMode.kBrake);
    backLeft.setIdleMode(IdleMode.kBrake);
    frontRight.setIdleMode(IdleMode.kBrake);
    backRight.setIdleMode(IdleMode.kBrake);
  }

  public void setCoastMode(){
    frontLeft.setIdleMode(IdleMode.kCoast);
    backLeft.setIdleMode(IdleMode.kCoast);
    frontRight.setIdleMode(IdleMode.kCoast);
    backRight.setIdleMode(IdleMode.kCoast);
  }

  public double getleftEncoder(){

    leftEncoder.setPositionConversionFactor(Constants.ENCODER_CONVERSION);

    return leftEncoder.getPosition();
  }

  public double getrightEncoder(){

    rightEncoder.setPositionConversionFactor(Constants.ENCODER_CONVERSION);
    return rightEncoder.getPosition();
  }

  public void resetGyro(){
    gyro.reset();
  }

  public  double getGyro(){

    return gyro.getAngle();

  }

  //  AUTONOMOUS COMMAND METHODS

  public void barrelRacing(){
    gyro.getAngle();

  }

  public void galactic(){
    double rotationSpeed;
    double forwardSpeed = 0.0;
    resetGyro();


    //PhotonPipelineResult result = camera.getLatestResult();
    var result = camera.getLatestResult();

    boolean hasTargets = result.hasTargets();
    List<PhotonTrackedTarget> targets = result.getTargets();
    PhotonTrackedTarget target = result.getBestTarget();
    Transform2d pose = target.getCameraToTarget();
    setBrakeMode();

    if(result.hasTargets()){
     rotationSpeed = controller.calculate(result.getBestTarget().getYaw(), 0);
      
    } else{
      rotationSpeed = 0.0;
    }
    
    SmartDashboard.putNumber("RotationSpeed", rotationSpeed);
    SmartDashboard.putNumber("ForwardSpeed", forwardSpeed);
    differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);
  }

  // public boolean targetObtained(){

  //   if(target.getYaw() < range && target.getYaw() > - range){
  //     return true;
  //   } else{
  //     return false;
  //   }
    
  // }
  

  // public double getYaw(){
  //   return target.getYaw();
  // }

  // public double getPitch(){
  //   return target.getPitch();
  // }

  // public double getSkew(){
  //   return target.getSkew();
  // }

  // public double getArea(){
  //   return target.getArea();
  // }

  public DriveSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

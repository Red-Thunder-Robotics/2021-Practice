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
import org.photonvision.PhotonTrackedTarget;
import org.photonvision.PhotonUtils;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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
  PIDController controller = new PIDController(.05, 0, 0);

  double range = 4.0;  // Degrees of range that it treats as being "on target"

  static final double kCameraHeight = 0.2794; // meters
  static final double kCameraPitch = 0.0; // radians
  static final double kTargetHeight = 0.0889; // meters

  double cell0;
  double cell1;

  

  

 

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
      setBrakeMode();
    } else if(speed >= 0.10 || speed <= -0.10){
        setCoastMode();

        moving = driveSpeed * speed;
        if(Math.abs(turn) > 0.10){
          turning = rotateSpeed * turn;
        }
      } else if(Math.abs(turn) > 0.10){
          setCoastMode();

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

  public void resetEncoders(){
    leftEncoder.setPosition(0.0);
    rightEncoder.setPosition(0.0);
  }

  public void resetGyro(){
    gyro.reset();
  }

  public double getGyro(){

    return gyro.getAngle();

  }

  //  AUTONOMOUS COMMAND METHODS

  public void barrelRacing(){
    gyro.getAngle();

  }



  public String galacticTurn1(){
    String turn1 = "ERROR"; 
    var result = camera.getLatestResult();
    List<PhotonTrackedTarget> targets = result.getTargets();
    
    if(targets.size()> 1){
      if(result.hasTargets()){
        double cell0 = targets.get(0).getYaw();
        double cell1 = targets.get(1).getYaw();
        if(cell1 > cell0 ){  //Cell1 is to the right of cell0
          turn1 = "RIGHT";  // true = turn right
        } else if(cell1 < cell0){ // cell1 is to the left of cell0
          turn1 = "LEFT"; // false = turn left
        }
      }

    } 
    return turn1;

  }

  public String galacticTurn2(){
    String turn2 = "ERROR";
    var result = camera.getLatestResult();
    List<PhotonTrackedTarget> targets = result.getTargets();


    if(targets.size() > 1){
      if(result.hasTargets()){
        double cell1 = targets.get(1).getYaw();
        double cell2 = targets.get(2).getYaw();
        if(cell2 > cell1){ //cell2 is to the right of cell1
          turn2 = "RIGHT";  // true = turn right
        } else if(cell2 < cell1){ //cell2 is to the left cell1
          turn2 = "LEFT"; // false = turn left
        }
      }
    }
    return turn2;
  }

  public void galPivot(String turn1, String turn2){
    double rotationSpeed = 0.0;
    double forwardSpeed = 0.0;
    var result = camera.getLatestResult();
    List<PhotonTrackedTarget> targets = result.getTargets();

    if(targets.get(0).getYaw() > Math.abs(range)){
        if(result.hasTargets() && turn1 != "ERROR" && turn2 != "Error"){ // While it has targets, no errors
          rotationSpeed = 0.25*Math.sin((Math.PI/80)*targets.get(0).getYaw()); // Rotate first to 0, always the closest target
          forwardSpeed = 0.0;
      } else{
          rotationSpeed = 0.0;
          forwardSpeed = 0.0;
      }
       
    } else{
      rotationSpeed = 0.0;
      forwardSpeed = 0.0;
    }
    differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);

  }

  public double getGALYaw(){
    var result = camera.getLatestResult();
    List<PhotonTrackedTarget> targets = result.getTargets();

    if(result.hasTargets()){
      return targets.get(0).getYaw();
    } else{
      return 0.0;
    }
  }

  public void galOrient(){
    double error = gyro.getAngle();
    double kp = 1.0;

    differentialRocketLeagueDrive.tankDrive(kp* error, kp * error);

  }

  public double getGalDistance(){
    var result = camera.getLatestResult();
    double distanceMeters = PhotonUtils.calculateDistanceToTargetMeters(
      kCameraHeight, kTargetHeight, kCameraPitch, Math.toRadians(result.getBestTarget().getPitch()));
    return distanceMeters * 39.372; // returns distance to target in feet
  }

  public void galDrive(double distance){
    double kp = 1;
    double error = -gyro.getRate();
    double leftspeed;
    double rightspeed;

    if(getrightEncoder() < distance){
      leftspeed = .5 + kp * error;
      rightspeed = .5 + kp* error;
      
    } else{
      leftspeed = 0.0;
      rightspeed = 0.0;
  
    }

    differentialRocketLeagueDrive.tankDrive(leftspeed, rightspeed);
  }

  public void galTurn(String turn1, String turn2){

  }
    
       
  
  // public void galactic(String turn1, String turn2){
  //   double rotationSpeed = 0.0;
  //   double forwardSpeed = 0.0;
  //   double heading;
  //   var result = camera.getLatestResult();
  //   List<PhotonTrackedTarget> targets = result.getTargets();
  //   setBrakeMode();

  //   while(targets.get(0).getYaw() > Math.abs(range)){ // While it is not pointed at the first target
  //     if(result.hasTargets() && turn1 != "ERROR" && turn2 != "Error"){ // While it has targets, no errors
  //       rotationSpeed = 0.25*Math.sin((Math.PI/80)*targets.get(0).getYaw()); // Rotate first
  //       forwardSpeed = 0.0;
  //     } else{
  //       rotationSpeed = 0.0;
  //       forwardSpeed = 0.0;
  //     }
  //     differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);

  //   } 

  //   heading = gyro.getAngle(); // Sets "heading" as the direction of the first target
  //   double distanceMeters = PhotonUtils.calculateDistanceToTargetMeters(
  //     kCameraHeight, kTargetHeight, kCameraPitch, Math.toRadians(result.getBestTarget().getPitch()));
  //   double distanceFeet = distanceMeters * 3.281; // Get distance to cell 0

  //   while (getrightEncoder() < distanceFeet) {  // Travels, using encoders, the distance to cell0
  //     if(gyro.getAngle() > heading){ // If too far right
  //       rotationSpeed = -.1;
  //     } else if(gyro.getAngle() < heading){ // If too far left
  //       rotationSpeed = .1;
  //     }

  //     forwardSpeed = 0.60;
  //     differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);

  //   }

  //   while(Math.abs(gyro.getAngle()) > 2){ // Reset the bot to point zero
  //       rotationSpeed = 0.25*Math.sin((Math.PI/80) *gyro.getAngle()); 
  //       forwardSpeed = 0.0;
  //       differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);
      
  //   }

  //   if(turn1 == "RIGHT"){
      
  //   }else {

  //   }



  // }


  public DriveSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

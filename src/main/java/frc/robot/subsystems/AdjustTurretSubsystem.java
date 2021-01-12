/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class AdjustTurretSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

  public TalonSRX hoodAdjustMotor = new TalonSRX(Constants.HOOD_ADJUST_MOTOR_CAN); 
  public TalonSRX turret = new TalonSRX(Constants.SHOOTER_X_ADJUST);

  Boolean onTarget;

  


  public AdjustTurretSubsystem() {

  }
  


  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");



  public void LimelightDashboard() {
    // Acquire values from the network table and label them as variables
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");

    // read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double target = tv.getDouble(0.0);

    // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Valid Target", target);

  }

  public double getTX() {
    NetworkTableEntry tx = table.getEntry("tx");
    return tx.getDouble(0.0);
  }

  public double getTY() {
    NetworkTableEntry ty = table.getEntry("ty");
    return ty.getDouble(0.0);
  }

  public double getTA() {
    NetworkTableEntry ta = table.getEntry("ta");
    return ta.getDouble(0.0);
  }

  public double getTV() {
    NetworkTableEntry tv = table.getEntry("tv");
    return tv.getDouble(0.0);
  }

  public void setTurretSpeed(double speed){
    //Create a turret motor
    turret.set(ControlMode.PercentOutput, speed);
  }

  public void hasXTarget(){

    double tx = getTX();
    boolean target;

    if(tx > 1  || tx <-1){
      target = true;
    } else {
      target = false;
    }
    SmartDashboard.putBoolean("Has Target?", target);

  }


  //ADJUSTMENT METHODS






  public void adjustXShooter() {
    double tx = getTX();
    //double Kp = 0.01f;  Currently not using Kp, could in future
    double min_Command = 0.08f;

    if (getTV() == 1) {
      double heading_error = -tx;
      double Turret_adjust = 0.0f;

      if (tx > 12.0) {
        Turret_adjust = Math.sin((Math.PI/40)*heading_error) - min_Command;
      } else if(tx < -12.0){
          Turret_adjust = Math.sin((Math.PI/40)*heading_error) + min_Command;
      }

      setTurretSpeed(-Turret_adjust);

      SmartDashboard.putNumber("TV", getTV());

      
      
      } else {
        setTurretSpeed(0.0);
      }
    }
    

    // Talon encoder has 4096 encoder units per rotation
    public void adjustYShooter(){

      hoodAdjustMotor.set(ControlMode.Position, 100);
      hoodAdjustMotor.getSelectedSensorPosition();

      /*

      if(getTV() == 1){
        if(hoodEncoder.getPosition() > ((((-27 * getTA()) + 560)) + 15)){
          setCoast();
          hoodAdjustMotor.set(-0.04);
        }else if(hoodEncoder.getPosition() < ((((-27 * getTA()) + 560)) - 15)){
          setCoast();
          hoodAdjustMotor.set(0.05);
        }else{
          hoodAdjustMotor.set(0);
          setBrake();
        }
  
    
      }else{
        setBrake();
      }

      */
    }  
 
    

// Uses the test controller to manually control the turret

 public void testAdjustXShooter(Double rotation) {

  setTurretSpeed(rotation);
  SmartDashboard.putNumber("Manual turret x speed", getTX());

 }

 public void testAdjustYHood(Double rotation){

  hoodAdjustMotor.set(ControlMode.PercentOutput, rotation);
  SmartDashboard.putNumber("Hood adjust motor encoder value", hoodAdjustMotor.getSelectedSensorPosition());
 }


 public void displayOnTarget(){ //Displays when the turret is on target

  if(getTX() < 3 && getTX() > -3){
    if(hoodAdjustMotor.getBusVoltage() == 0){
      onTarget = true;
    } else {
      onTarget = false;
    }
  }
   SmartDashboard.putBoolean("Turret on Target", onTarget);

 }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
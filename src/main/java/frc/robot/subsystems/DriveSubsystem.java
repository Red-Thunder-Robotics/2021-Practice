/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers.CalendarDeserializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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
  
   //group the motors

  public SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeft, backLeft);
  public SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRight, backRight);

   public DifferentialDrive differentialRocketLeaugeDrive = new DifferentialDrive(leftGroup,rightGroup);




  public void RocketLeaugeDrive(double speed, double turn){
    differentialRocketLeaugeDrive.arcadeDrive(speed, turn);
  }
  public DriveSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

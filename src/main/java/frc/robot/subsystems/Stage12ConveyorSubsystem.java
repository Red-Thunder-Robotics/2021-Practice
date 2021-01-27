/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class Stage12ConveyorSubsystem extends SubsystemBase {
  /**
   * Creates a new ConveyorSubsystem.
   */
  public VictorSPX stage1Motor = new VictorSPX(Constants.STAGE_1_MOTOR);
  public VictorSPX stage2Motor = new VictorSPX(Constants.STAGE_2_MOTOR);

  public Stage12ConveyorSubsystem() {

  }
  public void stage1Conveyor(double speed){
    double speedConstant = 0.90;
    stage1Motor.set(ControlMode.PercentOutput, speedConstant*speed);

  }

  public void stage2Conveyor(double speed){
    double speedConstant = 0.75;
    stage2Motor.set(ControlMode.PercentOutput, speedConstant*speed);

  }

 



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

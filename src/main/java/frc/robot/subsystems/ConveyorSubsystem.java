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


public class ConveyorSubsystem extends SubsystemBase {
  /**
   * Creates a new ConveyorSubsystem.
   */

  public VictorSPX conveyorMotor = new VictorSPX(Constants.CONVEYOR_MOTOR);

  public ConveyorSubsystem() {

  }

  public void upConveyor(){
    conveyorMotor.set(ControlMode.PercentOutput, .4);
  }

  public void downConveyor(){
    conveyorMotor.set(ControlMode.PercentOutput, -.4);
  }

  public void stopConveyor(){
    conveyorMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

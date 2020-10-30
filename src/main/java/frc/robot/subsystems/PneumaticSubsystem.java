/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticSubsystem extends SubsystemBase {
  /**
   * Creates a new PneumaticSubsystem.
   */

  public Solenoid Sol = new Solenoid(Constants.PCM_CAN_ID, Constants.TRANSMISSION_SOLENOID);

  public PneumaticSubsystem() {

  }

  public void setHighGear(){
    Sol.set(true);
  }

  public void setLowGear(){
    Sol.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

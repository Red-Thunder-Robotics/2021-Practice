/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous.Galactic;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Stage12ConveyorSubsystem;

public class GAL_Conveyor extends CommandBase {

  Stage12ConveyorSubsystem stage12ConveyorSubsystem;
  Double speed;
  /**
   * Creates a new GAL_Conveyor.
   */
  public GAL_Conveyor(Stage12ConveyorSubsystem subsystem, Double conveyorSpeed) {
    stage12ConveyorSubsystem = subsystem;
    conveyorSpeed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    stage12ConveyorSubsystem.stage1Conveyor(speed);
    stage12ConveyorSubsystem.stage2Conveyor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

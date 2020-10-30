/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {

  private final DriveSubsystem driveSubsystem;
  private final DoubleSupplier movingForward;
  private final DoubleSupplier movingBackward;
  private final DoubleSupplier turning;
  /**
   * Creates a new DriveCommand.
   */
  public DriveCommand(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier backward, DoubleSupplier rotation) {
    driveSubsystem = subsystem;
    movingForward = forward;
    movingBackward = backward;
    turning = rotation;
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      double xMotion = movingForward.getAsDouble() - movingBackward.getAsDouble(); 

      driveSubsystem.RocketLeagueDrive(xMotion, turning.getAsDouble());

      driveSubsystem.getRightMotorSpeed();

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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AdjustTurretSubsystem;

// Plug test controller into port 5, use left Y axis to drive the turret


public class TestTurretAdjustCommand extends CommandBase {

  private final AdjustTurretSubsystem adjustTurretSubsystem;
  private final DoubleSupplier manualRotation;
  private final DoubleSupplier hoodAdjust;
  /**
   * Creates a new TestTurretAdjustCommand.
   */
  public TestTurretAdjustCommand(AdjustTurretSubsystem subsystem, DoubleSupplier rotation, DoubleSupplier hood) {
    adjustTurretSubsystem = subsystem;
    manualRotation = rotation;
    hoodAdjust = hood;
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
    adjustTurretSubsystem.testAdjustXShooter(manualRotation.getAsDouble());
    adjustTurretSubsystem.testAdjustYHood(hoodAdjust.getAsDouble());
    adjustTurretSubsystem.LimelightDashboard();
    adjustTurretSubsystem.displayOnTarget();
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

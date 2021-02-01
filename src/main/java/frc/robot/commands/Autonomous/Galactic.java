/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Stage12ConveyorSubsystem;

public class Galactic extends CommandBase {

  private final DriveSubsystem driveSubsystem;
  private final Stage12ConveyorSubsystem stage12ConveyorSubsystem;
  private final String Turn1;
  private final String Turn2;
  /**
   * Creates a new GalacticB2.
   */
  public Galactic(DriveSubsystem subsystem, Stage12ConveyorSubsystem subsystem2, 
    String turn1, String turn2) {
    driveSubsystem = subsystem;
    stage12ConveyorSubsystem = subsystem2;
    Turn1 = turn1;
    Turn2 = turn2;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    addRequirements(subsystem2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveSubsystem.resetGyro();
    Shuffleboard.getTab("Autonomous").add("Turn 1", Turn1);
    Shuffleboard.getTab("Autonomous").add("Turn 2", Turn2);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    stage12ConveyorSubsystem.stage1Conveyor(0.0);
    //driveSubsystem.galactic(Turn1, Turn2);
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

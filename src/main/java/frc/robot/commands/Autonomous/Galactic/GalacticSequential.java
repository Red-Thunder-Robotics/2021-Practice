/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous.Galactic;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Stage12ConveyorSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class GalacticSequential extends SequentialCommandGroup {
  /**
   * Creates a new GalacticSequential.
   */
  public GalacticSequential(DriveSubsystem drive, Stage12ConveyorSubsystem conveyor, String turn1, String turn2) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new GAL_Conveyor(conveyor, 1.0),
      new GAL_Conveyor(conveyor, 0.0)
    );
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous.Galactic;

import edu.wpi.first.wpilibj2.command.InstantCommand;
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
  public GalacticSequential(DriveSubsystem drive, Stage12ConveyorSubsystem conveyor, String turn1, String turn2, Double distance) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new InstantCommand(() -> drive.resetGyro()), // Sets gyro to zero at beginning
      new GAL_Pivot(drive, turn1, turn2), // Turn to cell0
      new InstantCommand(() -> conveyor.stage1Conveyor(1)), // Turn on conveyor
      new InstantCommand(() -> conveyor.stage2Conveyor(1)), // Turn on conveyor
      new InstantCommand(() -> drive.resetEncoders()), // Reset encoders to use in next step
      new GAL_Drive(drive, distance), // Drive to cell0
      new InstantCommand(() -> conveyor.stage1Conveyor(0)), // Turn off conveyor 
      new InstantCommand(() -> conveyor.stage2Conveyor(0)) // Turn off conveyor
      // Rotate to zero degrees
      // Turn based on turn1

      //Rotate to cell 0
      //Start conveyor
      //Drive to cell 1
      //Stop conveyor
      //Rotate to zero degrees
      //Turn based on turn2

      //Rotate to object
      //Start conveyor
      //Drive to cell 2
      //Stop conveyor
      //Rotate to zero degrees
      //Drive to end zone ???
    );
  }
}

// public void galactic(String turn1, String turn2){
  //   double rotationSpeed = 0.0;
  //   double forwardSpeed = 0.0;
  //   double heading;
  //   var result = camera.getLatestResult();
  //   List<PhotonTrackedTarget> targets = result.getTargets();
  //   setBrakeMode();

  //   while(targets.get(0).getYaw() > Math.abs(range)){ // While it is not pointed at the first target
  //     if(result.hasTargets() && turn1 != "ERROR" && turn2 != "Error"){ // While it has targets, no errors
  //       rotationSpeed = 0.25*Math.sin((Math.PI/80)*targets.get(0).getYaw()); // Rotate first
  //       forwardSpeed = 0.0;
  //     } else{
  //       rotationSpeed = 0.0;
  //       forwardSpeed = 0.0;
  //     }
  //     differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);

  //   } 

  //   heading = gyro.getAngle(); // Sets "heading" as the direction of the first target
  //   double distanceMeters = PhotonUtils.calculateDistanceToTargetMeters(
  //     kCameraHeight, kTargetHeight, kCameraPitch, Math.toRadians(result.getBestTarget().getPitch()));
  //   double distanceFeet = distanceMeters * 3.281; // Get distance to cell 0

  //   while (getrightEncoder() < distanceFeet) {  // Travels, using encoders, the distance to cell0
  //     if(gyro.getAngle() > heading){ // If too far right
  //       rotationSpeed = -.1;
  //     } else if(gyro.getAngle() < heading){ // If too far left
  //       rotationSpeed = .1;
  //     }

  //     forwardSpeed = 0.60;
  //     differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);

  //   }

  //   while(Math.abs(gyro.getAngle()) > 2){ // Reset the bot to point zero
  //       rotationSpeed = 0.25*Math.sin((Math.PI/80) *gyro.getAngle()); 
  //       forwardSpeed = 0.0;
  //       differentialRocketLeagueDrive.arcadeDrive(forwardSpeed, rotationSpeed);
      
  //   }

  //   if(turn1 == "RIGHT"){
      
  //   }else {

  //   }



  // }
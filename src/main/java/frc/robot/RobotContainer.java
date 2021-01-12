/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.AdjustTurretSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ConveyorUpCommand;
import frc.robot.commands.ConveyorDownCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.PneumaticCommand;
import frc.robot.commands.TestTurretAdjustCommand;
import frc.robot.commands.AdjustTurretCommand;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_drivesubsystem = new DriveSubsystem();
  private final PneumaticSubsystem m_pneumaticsubsystem = new PneumaticSubsystem();
  private final ConveyorSubsystem m_conveyorsubsystem = new ConveyorSubsystem();
  private final AdjustTurretSubsystem m_adjustturretsubsystem = new AdjustTurretSubsystem();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  //Create the controllers
  GenericHID driverController = new XboxController(Constants.DRIVE_CONTROLLER);

  GenericHID opjoystick = new Joystick(Constants.OPERATOR_CONTROLLER);

  GenericHID testing = new Joystick(Constants.TESTING_CONTROLLER);


  // xBox Buttons

  Button A_Button = new JoystickButton(driverController, 1);
  Button B_Button = new JoystickButton(driverController, 2);
  Button X_Button = new JoystickButton(driverController, 3);
  Button Y_Button = new JoystickButton(driverController, 4);
  Button LB_Button = new JoystickButton(driverController, 5);
  Button RB_Button = new JoystickButton(driverController, 6);
  Button Select_Button = new JoystickButton(driverController, 7);
  Button Start_Button = new JoystickButton(driverController, 8);
  Button Left_Stick_Button = new JoystickButton(driverController, 9);
  Button Right_Stick_Button = new JoystickButton(driverController, 10);

  //Joystick Buttons

  Button Trigger = new JoystickButton(opjoystick, 1);
  Button Back_Button_Joystick = new JoystickButton(opjoystick, 2);
  Button Left_Button_Joystick = new JoystickButton(opjoystick, 3);
  Button Right_Button_Joystick = new JoystickButton(opjoystick, 4);
  Button Right_Top_Right_Button = new JoystickButton(opjoystick, 5);
  Button Right_Top_Middle_Button = new JoystickButton(opjoystick, 6);
  Button Right_Top_Left_Button = new JoystickButton(opjoystick, 7);
  Button Right_Bottom_Left_Button = new JoystickButton(opjoystick, 8);
  Button Right_Bottom_Middle_Button = new JoystickButton(opjoystick, 9);
  Button Right_Bottom_Right_Buttom = new JoystickButton(opjoystick, 10);
  Button Left_Top_Left_Button = new JoystickButton(opjoystick, 11);
  Button Left_Top_Middle_Button = new JoystickButton(opjoystick, 12);
  Button Left_Top_Right_Button = new JoystickButton(opjoystick, 13);
  Button Left_Bottom_Right_Button = new JoystickButton(opjoystick, 14);
  Button Left_Bottom_Middle_Button = new JoystickButton(opjoystick, 15);
  Button Left_Bottom_Left_Button = new JoystickButton(opjoystick, 16);

  // xBox test controller buttons

  Button A_button = new JoystickButton(testing, 1);
  Button B_button = new JoystickButton(testing, 2);
  Button X_button = new JoystickButton(testing, 3);
  Button Y_button = new JoystickButton(testing, 4);
  Button LB_button = new JoystickButton(testing, 5);
  Button RB_button = new JoystickButton(testing, 6);
  Button Select_button = new JoystickButton(testing, 7);
  Button Start_button = new JoystickButton(testing, 8);
  Button Left_Stick_button = new JoystickButton(testing, 9);
  Button Right_Stick_button = new JoystickButton(testing, 10);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drivesubsystem.setDefaultCommand(new DriveCommand(m_drivesubsystem, 
                                                          () -> driverController.getRawAxis(Constants.DRIVE_RIGHT_TRIGGER), 
                                                          () -> driverController.getRawAxis(Constants.DRIVE_LEFT_TRIGGER),
                                                          () -> driverController.getRawAxis(Constants.DRIVE_RIGHT_X_AXIS)));
    m_adjustturretsubsystem.setDefaultCommand(new TestTurretAdjustCommand(m_adjustturretsubsystem,
                                                          () -> testing.getRawAxis(Constants.TEST_LEFT_Y_AXIS)));



    //Creates tab for Autonomous on shuffleboard and selects what is available
    Shuffleboard.getTab("Autonomous").add(m_chooser);
                                                   
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    A_Button.whenPressed(new PneumaticCommand(m_pneumaticsubsystem));
    LB_Button.whileHeld(new ConveyorUpCommand(m_conveyorsubsystem));
    RB_Button.whileHeld(new ConveyorDownCommand(m_conveyorsubsystem));
    //Make button for Shooting
    X_Button.whileHeld(new AdjustTurretCommand(m_adjustturretsubsystem));
    

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}

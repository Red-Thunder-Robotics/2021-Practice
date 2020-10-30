/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // ALL CAPS

    // Drive motor ports
    public static final int FRONT_LEFT_MOTOR_CAN = 2;
    public static final int FRONT_RIGHT_MOTOR_CAN = 4;
    public static final int BACK_LEFT_MOTOR_CAN = 1;
    public static final int BACK_RIGHT_MOTOR_CAN = 3;

    // Controller ID's and axes

    public static final int DRIVE_CONTROLLER = 0;

    public static final int DRIVE_LEFT_X_AXIS = 0;
    public static final int DRIVE_LEFT_Y_AXIS = 1;
    public static final int DRIVE_LEFT_TRIGGER = 2;
    public static final int DRIVE_RIGHT_TRIGGER = 3;
    public static final int DRIVE_RIGHT_X_AXIS = 4;
    public static final int DRIVE_RIGHT_Y_AXIS = 5;
}

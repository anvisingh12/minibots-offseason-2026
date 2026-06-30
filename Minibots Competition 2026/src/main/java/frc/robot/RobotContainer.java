// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoCommands;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OIConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();

    // Default command: drive with joysticks whenever teleop is active
    m_driveSubsystem.setDefaultCommand(
        new RunCommand(
            () -> m_driveSubsystem.driveCartesian(
                -m_driverController.getLeftY(),  // forward/back
                 m_driverController.getLeftX(),  // strafe
                 m_driverController.getRightX()  // rotate
            ),
            m_driveSubsystem
        )
    );
  }

  private void configureBindings() {
    // Example: hold left bumper for slow mode
    // m_driverController.leftBumper().whileTrue(new RunCommand(
    //     () -> m_driveSubsystem.driveCartesian(
    //         -m_driverController.getLeftY()  * 0.4,
    //          m_driverController.getLeftX()  * 0.4,
    //          m_driverController.getRightX() * 0.4
    //     ), m_driveSubsystem
    // ));
  }

  public Command getAutonomousCommand() {
    return AutoCommands.basicAuto(m_driveSubsystem);
  }
}
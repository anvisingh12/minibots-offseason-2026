package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

public class AutoCommands {
    public static Command basicAuto(DriveSubsystem drive) {
        return new SequentialCommandGroup(
            // distance = number of encoder ticks
            // speed (actually motor power %)= number between 0 and 1
            DriveCommands.driveRight(drive, 5.0, 0.5),
            DriveCommands.driveForward(drive, 3.0, 0.5)
        );
    }
}
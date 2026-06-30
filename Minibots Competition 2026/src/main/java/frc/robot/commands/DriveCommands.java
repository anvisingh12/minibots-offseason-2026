package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommands {

    private DriveCommands() {}

    private static class DriveCommand extends Command {
        private final DriveSubsystem m_drive;
        private final double m_ySpeed;
        private final double m_xSpeed;
        private final double m_zRotation;
        private final double m_distance;

        public DriveCommand(DriveSubsystem drive, double ySpeed, double xSpeed, double zRotation, double distance) {
            m_drive = drive;
            m_ySpeed = ySpeed;
            m_xSpeed = xSpeed;
            m_zRotation = zRotation;
            m_distance = distance;
            addRequirements(drive);
        }

        @Override
        public void initialize() {
            m_drive.resetEncoders();
        }

        @Override
        public void execute() {
            m_drive.driveCartesian(m_ySpeed, m_xSpeed, m_zRotation);
        }

        @Override
        public boolean isFinished() {
            return m_drive.getAverageEncoderPosition() >= m_distance;
        }

        @Override
        public void end(boolean interrupted) {
            m_drive.stop();
        }
    }

    public static Command driveForward(DriveSubsystem drive, double distance, double speed) {
        return new DriveCommand(drive, speed, 0, 0, distance);
    }

    public static Command driveBackward(DriveSubsystem drive, double distance, double speed) {
        return new DriveCommand(drive, -speed, 0, 0, distance);
    }

    public static Command driveRight(DriveSubsystem drive, double distance, double speed) {
        return new DriveCommand(drive, 0, speed, 0, distance);
    }

    public static Command driveLeft(DriveSubsystem drive, double distance, double speed) {
        return new DriveCommand(drive, 0, -speed, 0, distance);
    }

}
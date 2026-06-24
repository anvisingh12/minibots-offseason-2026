package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

    private final SparkMax m_frontLeft  = new SparkMax(DriveConstants.kFrontLeftMotorPort,  MotorType.kBrushed);
    private final SparkMax m_frontRight = new SparkMax(DriveConstants.kFrontRightMotorPort, MotorType.kBrushed);
    private final SparkMax m_rearLeft   = new SparkMax(DriveConstants.kRearLeftMotorPort,   MotorType.kBrushed);
    private final SparkMax m_rearRight  = new SparkMax(DriveConstants.kRearRightMotorPort,  MotorType.kBrushed);

    private final MecanumDrive m_drive;

    public DriveSubsystem() {
        // Configure right side motors as inverted
        SparkMaxConfig rightConfig = new SparkMaxConfig();
        rightConfig.inverted(true).idleMode(IdleMode.kBrake)
            .encoder.countsPerRevolution(538);

        SparkMaxConfig leftConfig = new SparkMaxConfig();
        leftConfig.inverted(false).idleMode(IdleMode.kBrake)
            .encoder.countsPerRevolution(538);;

        m_frontLeft.configure(leftConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        m_rearLeft.configure(leftConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        m_frontRight.configure(rightConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        m_rearRight.configure(rightConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        m_drive = new MecanumDrive(m_frontLeft, m_rearLeft, m_frontRight, m_rearRight);
    }

    public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
        m_drive.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    public void stop() {
        m_drive.stopMotor();
    }
}
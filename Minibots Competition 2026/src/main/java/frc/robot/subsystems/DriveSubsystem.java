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

    private final SparkMax m_frontLeft  = new SparkMax(DriveConstants.kFrontLeftMotorPort,  MotorType.kBrushless);
    private final SparkMax m_frontRight = new SparkMax(DriveConstants.kFrontRightMotorPort, MotorType.kBrushless);
    private final SparkMax m_rearLeft   = new SparkMax(DriveConstants.kRearLeftMotorPort,   MotorType.kBrushless);
    private final SparkMax m_rearRight  = new SparkMax(DriveConstants.kRearRightMotorPort,  MotorType.kBrushless);

    private final MecanumDrive m_drive;

    public DriveSubsystem() {
        // Configure right side motors as inverted
        SparkMaxConfig rightConfig = new SparkMaxConfig();
        rightConfig.inverted(true).idleMode(IdleMode.kBrake);

        SparkMaxConfig leftConfig = new SparkMaxConfig();
        leftConfig.inverted(false).idleMode(IdleMode.kBrake);

        m_frontLeft.configure(leftConfig,
            ResetMode.kNoResetSafeParameters,
            PersistMode.kNoPersistParameters);
        m_rearLeft.configure(leftConfig,
            ResetMode.kNoResetSafeParameters,
            PersistMode.kNoPersistParameters);
        m_frontRight.configure(rightConfig,
            ResetMode.kNoResetSafeParameters,
            PersistMode.kNoPersistParameters);
        m_rearRight.configure(rightConfig,
            ResetMode.kNoResetSafeParameters,
            PersistMode.kNoPersistParameters);

        m_drive = new MecanumDrive(m_frontLeft, m_rearLeft, m_frontRight, m_rearRight);
    }

    public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
        m_drive.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    public void stop() {
        m_drive.stopMotor();
    }
}
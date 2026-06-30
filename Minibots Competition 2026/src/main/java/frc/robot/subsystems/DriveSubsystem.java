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
    private final SparkMax m_backLeft   = new SparkMax(DriveConstants.kRearLeftMotorPort,   MotorType.kBrushed);
    private final SparkMax m_backRight  = new SparkMax(DriveConstants.kRearRightMotorPort,  MotorType.kBrushed);

    private final MecanumDrive m_drive;

    public DriveSubsystem() {
        SparkMaxConfig rightConfig = new SparkMaxConfig();
        rightConfig.inverted(true).idleMode(IdleMode.kBrake)
            .encoder.countsPerRevolution(538);

        SparkMaxConfig leftConfig = new SparkMaxConfig();
        leftConfig.inverted(false).idleMode(IdleMode.kBrake)
            .encoder.countsPerRevolution(538);;

        m_frontLeft.configure(leftConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        m_backLeft.configure(leftConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        m_frontRight.configure(rightConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        m_backRight.configure(rightConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        m_drive = new MecanumDrive(m_frontLeft, m_backLeft, m_frontRight, m_backRight);
    }

    public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
        m_drive.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    public void stop() {
        m_drive.stopMotor();
    }

    // resets all encoders to position 0
    public void resetEncoders(){
        m_frontLeft.getEncoder().setPosition(0); 
        m_frontRight.getEncoder().setPosition(0); 
        m_backLeft.getEncoder().setPosition(0); 
        m_backRight.getEncoder().setPosition(0); 
    }

    // allows you to estimate how much you've driven, I think
    public double getAverageEncoderPosition(){    
        return (Math.abs(m_frontLeft.getEncoder().getPosition()) +
            Math.abs(m_frontRight.getEncoder().getPosition()) +
            Math.abs(m_backLeft.getEncoder().getPosition()) +
            Math.abs(m_backRight.getEncoder().getPosition())) / 4.0;
    } 
}


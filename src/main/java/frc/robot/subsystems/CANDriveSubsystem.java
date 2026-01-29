// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;


public class CANDriveSubsystem extends SubsystemBase {
  //private final SparkMax leftLeader;
  //private final SparkMax leftFollower;
  //private final SparkMax rightLeader;
  //private final SparkMax rightFollower;
private final TalonFX leftLeader = new TalonFX(1);
private final TalonFX rightLeader = new TalonFX(2);
private final TalonFX leftFollower = new TalonFX(3);
private final TalonFX rightFollower = new TalonFX(4);
  private final DifferentialDrive drive;


  public CANDriveSubsystem() {
    configureLeftLeader();
    configureRightLeader();
    configureLeftFollower();
    configureRightFollower();

    // set up differential drive class
    // drive = new DifferentialDrive(leftLeader, rightLeader);
    drive = new DifferentialDrive(leftLeader::set, rightLeader::set);
    
  }

  @Override
  public void periodic() {
  }

  public void driveArcade(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }

  public void configureLeftLeader() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    config.CurrentLimits.SupplyCurrentLimit = DRIVE_MOTOR_CURRENT_LIMIT;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;
    leftLeader.getConfigurator().apply(config);
    leftLeader.setExpiration(250);
  }

  public void configureRightLeader() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    config.CurrentLimits.SupplyCurrentLimit = DRIVE_MOTOR_CURRENT_LIMIT;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;
    config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    rightLeader.getConfigurator().apply(config);
    rightLeader.setExpiration(250);
  }

  public void configureLeftFollower() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    config.CurrentLimits.SupplyCurrentLimit = DRIVE_MOTOR_CURRENT_LIMIT;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;

    leftFollower.setControl(new Follower(leftLeader.getDeviceID(), MotorAlignmentValue.Aligned));
    leftFollower.getConfigurator().apply(config);
    leftFollower.setExpiration(250);
  }

  public void configureRightFollower() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    config.CurrentLimits.SupplyCurrentLimit = DRIVE_MOTOR_CURRENT_LIMIT;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;
    config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    rightFollower.setControl(new Follower(rightLeader.getDeviceID(), MotorAlignmentValue.Aligned));
    rightFollower.getConfigurator().apply(config);
    rightFollower.setExpiration(250);
  }
}

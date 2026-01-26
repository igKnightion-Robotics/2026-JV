// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

//import com.revrobotics.spark.SparkBase.PersistMode;
//import com.revrobotics.spark.SparkBase.ResetMode;
//import com.revrobotics.spark.SparkLowLevel.MotorType;
//import com.revrobotics.spark.SparkMax;
//import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;

import java.util.function.DoubleSupplier;

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
    // create brushed motors for drive
    //leftLeader = new SparkMax(LEFT_LEADER_ID, MotorType.kBrushed);
    //leftFollower = new SparkMax(LEFT_FOLLOWER_ID, MotorType.kBrushed);
    //rightLeader = new SparkMax(RIGHT_LEADER_ID, MotorType.kBrushed);
    //rightFollower = new SparkMax(RIGHT_FOLLOWER_ID, MotorType.kBrushed);
  
    MotorOutputConfigs motorOutput = new MotorOutputConfigs();
    // motorOutput.setInvert(InvertedValue.CounterClockwise_Positive);
    motorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    // motorOutput.setNeutralMode(NeutralModeValue.Brake);
      motorOutput.NeutralMode = NeutralModeValue.Brake;
    leftLeader.getConfigurator().apply(motorOutput);
   rightLeader.getConfigurator().apply(motorOutput);
    leftFollower.getConfigurator().apply(motorOutput);
   rightFollower.getConfigurator().apply(motorOutput);

    // set up differential drive class
    // drive = new DifferentialDrive(leftLeader, rightLeader);
    drive = new DifferentialDrive(leftLeader::set, rightLeader::set);
    // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    leftLeader.setExpiration(250);
    rightLeader.setExpiration(250);
    leftFollower.setExpiration(250);
    rightFollower.setExpiration(250);

    // Create the configuration to apply to motors. Voltage compensation
    // helps the robot perform more similarly on different
    // battery voltages (at the cost of a little bit of top speed on a fully charged
    // battery). The current limit helps prevent tripping
    // breakers.
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    config.CurrentLimits.SupplyCurrentLimit = DRIVE_MOTOR_CURRENT_LIMIT;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;

    // Set configuration to follow each leader and then apply it to corresponding
    // follower. Resetting in case a new controller is swapped
    // in and persisting in case of a controller reset due to breaker trip
    leftFollower.setControl(new Follower(leftLeader.getDeviceID(), MotorAlignmentValue.Aligned));
    // leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftFollower.getConfigurator().apply(config);
    rightFollower.setControl(new Follower(rightLeader.getDeviceID(), MotorAlignmentValue.Aligned));
    rightFollower.getConfigurator().apply(config);

    // Remove following, then apply config to right leader
    config.disableFollowerMode();
    rightLeader.getConfigurator().apply(config);
    // Set config to inverted and then apply to left leader. Set Left side inverted
    // so that postive values drive both sides forward
    config.inverted(true);
    leftLeader.getConfigurator().apply(config);
  }

  @Override
  public void periodic() {
  }

  public void driveArcade(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }

}

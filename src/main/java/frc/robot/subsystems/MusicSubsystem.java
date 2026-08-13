// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Add your docs here. */
public class MusicSubsystem extends SubsystemBase{

    private final TalonFX musicMotor1;
    private final TalonFX musicMotor2;
    private final TalonFX musicMotor3;
    private final TalonFX musicMotor4;
    private final TalonFX musicMotor5;
    private final TalonFX musicMotor6;

    private final Orchestra orchestra;

    public MusicSubsystem() {
        // Load the music file

        musicMotor1 = new TalonFX(6);
        musicMotor2 = new TalonFX(5);
        musicMotor3 = new TalonFX(4);
        musicMotor4 = new TalonFX(3);
        musicMotor5 = new TalonFX(2);
        musicMotor6 = new TalonFX(1);

        orchestra = new Orchestra();

        orchestra.addInstrument(musicMotor1);
        orchestra.addInstrument(musicMotor2);
        orchestra.addInstrument(musicMotor3);
        orchestra.addInstrument(musicMotor4);
        orchestra.addInstrument(musicMotor5);
        orchestra.addInstrument(musicMotor6);






        orchestra.loadMusic("td8.chrp");
    }

    public void playMusic() {
        // Play the music
        orchestra.play();
    }

    public void stopMusic() {
        // Stop the music
        orchestra.stop();
    }
}


/** Controls music playback through a Falcon 500. */
// public class MusicSubsystem extends SubsystemBase {

//     private final TalonFX musicMotor1;
//     private final TalonFX musicMotor2;
//     private final Orchestra orchestra;

// public MusicSubsystem() {
//     System.out.println("=== MUSIC SUBSYSTEM STARTING ===");

//     musicMotor1 = new TalonFX(6);
//     musicMotor2 = new TalonFX(5);

//     orchestra = new Orchestra();

//     var addStatus = orchestra.addInstrument(musicMotor1);
//     var addStatus = orchestra.addInstrument(musicMotor2);
//     System.out.println("ORCHESTRA ADD: " + addStatus);

//     var loadStatus = orchestra.loadMusic("drifteffect.chrp");
//     System.out.println("ORCHESTRA LOAD: " + loadStatus);

//     System.out.println("=== MUSIC SUBSYSTEM READY ===");
// }

//     public void playMusic() {
//     System.out.println("PLAY MUSIC BUTTON PRESSED");

//     var playStatus = orchestra.play();

//     System.out.println("ORCHESTRA PLAY: " + playStatus);
// }
    

//     public void stopMusic() {

//         System.out.println("STOP MUSIC BUTTON PRESSED");

//         StatusCode stopStatus = orchestra.stop();

//         System.out.println("ORCHESTRA STOP: " + stopStatus);
//     }
// }

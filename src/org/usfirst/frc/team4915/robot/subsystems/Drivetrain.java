package org.usfirst.frc.team4915.robot.subsystems;

import org.usfirst.frc.team4915.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drivetrain subsystem that holds and isolates everything relating to motors
 */
public class Drivetrain extends Subsystem {

    // Port motors
    private CANTalon m_portFollowerMotor;
    private CANTalon m_portMasterMotor;

    // Starboard motors
    private CANTalon m_starboardFollowerMotor;
    private CANTalon m_starboardMasterMotor;
    
    public Drivetrain() {
        // Create new CANTalons for all our drivetrain motors
        m_portFollowerMotor = new CANTalon(RobotMap.DRIVE_TRAIN_MOTOR_PORT_FOLLOWER);
        m_portMasterMotor = new CANTalon(RobotMap.DRIVE_TRAIN_MOTOR_PORT_MASTER);
        m_starboardFollowerMotor = new CANTalon(RobotMap.DRIVE_TRAIN_MOTOR_STARBOARD_FOLLOWER);
        m_starboardMasterMotor = new CANTalon(RobotMap.DRIVE_TRAIN_MOTOR_STARBOARD_MASTER);

        // Set the Master motor to a control mode and make the follower a follower
        m_portMasterMotor.changeControlMode(TalonControlMode.PercentVbus);
        m_portFollowerMotor.changeControlMode(TalonControlMode.Follower);
        m_portFollowerMotor.set(m_portMasterMotor.getDeviceID()); // Sets the master motor for the follower

        // Sets the Master motor to a control mode and make a follower a follower
        m_starboardMasterMotor.changeControlMode(TalonControlMode.PercentVbus);
        m_starboardFollowerMotor.changeControlMode(TalonControlMode.Follower);
        m_starboardFollowerMotor.set(m_starboardMasterMotor.getDeviceID()); // Sets the master motor for the follower
    }

    public void driveDirect() {
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


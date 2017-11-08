package org.firebears.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;

/**
 * Command to lower the floor, and close the servo.
 */
public class LiftFloorCommand extends Command {
	double speed;

    public LiftFloorCommand(double s) {
        requires(Robot.hopper);

    	if (s < 0.0){
			speed = Math.abs(s);
		} else {
			speed = s;
		}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hopper.lowerDumperServo();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hopper.goUp(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.hopper.stopHopper();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
	@Override
	public String toString() {
		return "LiftFloorCommand(" + speed + ")";
	}
}

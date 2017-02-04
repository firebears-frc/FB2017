// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;
import org.firebears.RobotMap;

import java.io.*;

/**
 *
 */
public class StartMotionRecordCommand extends Command {
	static boolean recording = false;
	File f;
	PrintWriter w;
	long startTime;

	public StartMotionRecordCommand() {

	}

	protected void initialize() {
		try {
			recording = true;
			startTime = System.currentTimeMillis();
			f = new File("/tmp/Recording.csv");
//			f = File.createTempFile("Recording", ".csv");
			w = new PrintWriter(f);
		} catch (IOException i) {
			i.printStackTrace();
			recording = false;
		}

	}

	protected void execute() {
		long time = System.currentTimeMillis() - startTime;
		double forwardAmount = Robot.chassis.getDriveY();
		double strafeAmount = Robot.chassis.getDriveX();
		double rotateAmount = Robot.chassis.getDriveRotation();
		
		
		w.printf("%d,%.2f,%.2f,%.2f,%n", time, forwardAmount, strafeAmount, rotateAmount);
		
			}

	protected boolean isFinished() {
		return !recording;
	}

	protected void end() {
		w.close();
	}

	protected void interrupted() {
		w.close();
	}
}

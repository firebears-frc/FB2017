package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import java.io.*;
import java.util.Scanner;

import org.firebears.Robot;

/**
 *
 */
public class PlayRecordingCommand extends Command {
	File f;
	Scanner scanner;
	long time;
	long startTime;
	double forwardAmount;
	double strafeAmount;
	double rotateAmount;
	InputStream stream;
	boolean hasMore;

	public PlayRecordingCommand() {
		requires(Robot.chassis);

	}
	
	public boolean readLine() {
		if (!scanner.hasNext()) {
			return false;
		}
		time = scanner.nextLong();
		forwardAmount = scanner.nextDouble();
		strafeAmount = scanner.nextDouble();
		rotateAmount = scanner.nextDouble();
		scanner.nextLine();
		return true;
		
	}

	protected void initialize() {
		try {
			startTime = System.currentTimeMillis();
			f = new File("/tmp/Recording.csv");
			stream = new FileInputStream(f);
			scanner = new Scanner(stream);
			hasMore = readLine();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	protected void execute() {
		long currentTime = System.currentTimeMillis() - startTime;
		if (currentTime > time) {
			Robot.chassis.drive(strafeAmount, forwardAmount, rotateAmount);
			hasMore = readLine();
		}
	}

	protected boolean isFinished() {
		return !hasMore;
	}

	protected void end() {
		try {
			stream.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	protected void interrupted() {
		try {
			stream.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}

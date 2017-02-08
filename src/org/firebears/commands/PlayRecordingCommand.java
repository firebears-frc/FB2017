package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import java.io.*;
import java.util.Scanner;

import org.firebears.Robot;

/**
 *
 */
public class PlayRecordingCommand extends Command {
	InputStream stream;
	Scanner scanner;
	long time;
	long startTime;
	double forwardAmount;
	double strafeAmount;
	double rotateAmount;
	boolean hasMore;
	final String fileName;

	public PlayRecordingCommand() {
		this("/tmp/Recording.csv");
	}

	public PlayRecordingCommand(String name) {
		requires(Robot.chassis);
		fileName = name;
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
			if (fileName.startsWith("/tmp/") || fileName.startsWith("/home/lvuser/")) {
				File f = new File(fileName);
				stream = new FileInputStream(f);
			} else {
				stream = ClassLoader.getSystemResourceAsStream(fileName);
			}
			scanner = (new Scanner(stream)).useDelimiter(",");
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
		scanner.close();
	}

	protected void interrupted() {
		scanner.close();
	}
}
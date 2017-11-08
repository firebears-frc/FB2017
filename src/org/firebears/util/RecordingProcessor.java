package org.firebears.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Read a recording file in and write out a modified recording file.
 */
public class RecordingProcessor implements Closeable {

	private final InputStream inStream;
	private final PrintStream outStream;
	private final Scanner scanner;

	public RecordingProcessor(String inFileName, String outFileName) throws IOException {
		if (inFileName.startsWith("recording")) {
			inStream = ClassLoader.getSystemResourceAsStream(inFileName);
		} else {
			File f = new File(inFileName);
			inStream = new FileInputStream(f);
		}
		scanner = (new Scanner(inStream)).useDelimiter(",");
		outStream = (outFileName!=null) ? new PrintStream(new File(outFileName)) : System.out;
	}

	/**
	 * Read in one line from the input stream.
	 * 
	 * @return whether there are more lines to be processed.
	 */
	protected boolean readLine(Line line) {
		if (!scanner.hasNext()) {
			return false;
		}
		line.time = scanner.nextLong();
		line.forwardAmount = scanner.nextDouble();
		line.strafeAmount = scanner.nextDouble();
		line.rotateAmount = scanner.nextDouble();
		scanner.nextLine();
		return true;
	}

	/**
	 * Only print lines if they are different from the previous line.
	 * The resulting recording should behave the same, but be shorter than the original.
	 * 
	 * @return whether there are more lines to be processed.
	 */
	public boolean processNextline() {
		boolean hasMore = readLine(line);
		boolean shouldPrint = (previousLine==null) || (! line.equals(previousLine)) || (!hasMore);
		// TODO:  more processing could go here
		if (shouldPrint) {
			outStream.println(line);
			Line temp = previousLine;
			previousLine = line;
			line = (temp != null) ? temp : new Line();
		}
		return hasMore;
	}
	
	Line line = new Line();
	Line previousLine = null;

	@Override
	public void close() throws IOException {
		scanner.close();
		inStream.close();
	}

	private class Line {
		private long time;
		private double forwardAmount;
		private double strafeAmount;
		private double rotateAmount;
		public String toString() {
			return String.format("%d,%.2f,%.2f,%.2f,", time, forwardAmount, strafeAmount, rotateAmount);
		}
		public boolean equals(Object obj) {
			if (obj == null) { return false; }
			if (! (obj instanceof Line)) { return false; }
			Line other = (Line)obj;
			return (this.forwardAmount == other.forwardAmount) 
					&& (this.strafeAmount == other.strafeAmount)
					&& (this.rotateAmount == other.rotateAmount);
		}
	}
	
	public static void main(String[] args) throws IOException {
		String inFileName = (args.length > 0) ? args[0] : "recording/auto_gear_left_1.csv";
		String outFileName = (args.length > 1) ? args[1] : null;
		try (RecordingProcessor processor = new RecordingProcessor(inFileName, outFileName)) {
			while (processor.processNextline())
				;
		}
	}

}

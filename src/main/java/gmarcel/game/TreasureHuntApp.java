package gmarcel.game;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import gmarcel.model.Adventurer;
import gmarcel.model.TreasureMap;

/**
 * 
 * This is the main class of the treasure hunt application.
 * 
 */
public class TreasureHuntApp {

	/**
	 * This is the main method the the treasure hunt application.
	 * 
	 * Three arguments are required to execute the application correctly: - the
	 * path to the text file from which the treasure map is loaded - the path to
	 * the text file from which the adventurers are loaded - the path to the
	 * text file to be saved which contains the output
	 * 
	 * If the application is launched incorrectly, then the usage static method
	 * is called to explain how to use the application.
	 * 
	 * As long as the adventurers on the board have remaining actions to do,
	 * they will do them.
	 * 
	 * NOTE: If the file to be saved already exists, it is deleted.
	 * 
	 * @param args
	 *            The required parameters (path_to_treasure_map_file,
	 *            path_to_adventurer_file, path_to_saving_file
	 */
	public static void main(String[] args) {

		if (args.length != 3) {
			System.err.println(usage());
			System.exit(1);
		}

		if (!isFileValid(args[0])) {
			System.err.println("File " + args[0]
					+ " does not exists or can't be read.");
			System.exit(1);
		} else if (!isFileValid(args[0])) {
			System.err.println("File " + args[1]
					+ " does not exists or can't be read.");
			System.exit(1);
		}

		TreasureMap treasureMap = null;

		// Load files
		try {
			treasureMap = TreasureHuntFileLoader.load(args[0], args[1]);
		} catch (IOException | ParseException e) {
			System.err.println("Error while reading input files: "
					+ e.getMessage());
		}

		// Start treasure hunt
		while (treasureMap.hasRemainingAction()) {
			for (Adventurer adventurer : treasureMap.getAdventurers()) {
				treasureMap.doAction(adventurer);
			}
		}

		try {
			TreasureHuntFileWriter.writeAdventurer(
					treasureMap.getAdventurers(), args[2]);
		} catch (IOException e) {
			System.err.println("Error while writing to output file.");
		}

		System.exit(0);

	}

	/**
	 * This method checks that the given application arguments are correct.
	 * 
	 * @param args
	 *            Arguments of the TreasureHunt application
	 */
	private static boolean isFileValid(String path) {

		File f = new File(path);

		if (!f.exists()) {
			return false;
		} else if (!f.canRead()) {
			return false;
		}

		return true;

	}

	/**
	 * 
	 * Returns the string explaining how to use the application correctly.
	 * 
	 * @return The string explaining how to use the application
	 */
	private static String usage() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("This application launches a treasure hunt.");
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("To execute the application, three arguments are needed:");
		sb.append(System.lineSeparator());
		sb.append("- arg0: the path to the text file configuring the the treasure map (the board)");
		sb.append(System.lineSeparator());
		sb.append("- arg1: the path to the text file configuring the adventurers' initial positions.");
		sb.append(System.lineSeparator());
		sb.append("- arg2: the path to the text file saved containing the adventurers' moves");
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());

		return sb.toString();

	}

}

package gmarcel.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import gmarcel.model.Action;
import gmarcel.model.Adventurer;
import gmarcel.model.Land;
import gmarcel.model.Mountain;
import gmarcel.model.Orientation;
import gmarcel.model.TreasureMap;

/**
 * 
 * This class contains a number of utility static methods used by the treasure
 * hunt application to load data from files.
 * 
 */
public class TreasureHuntFileLoader {

	/**
	 * Loads a treasure map two files.
	 * 
	 * @param treasureMapFileName
	 *            The file containing the treasure map configuration
	 * @param adventurerFileName
	 *            The file containing the adventurers configuration
	 * @return The treasure map
	 * @throws IOException
	 *             If an error occurs while reading either file
	 * @throws ParseException
	 *             If the text file a line returns an unsupported line
	 */
	public static TreasureMap load(String treasureMapFileName,
			String adventurerFileName) throws IOException, ParseException {

		TreasureMap treasureMap = null;
		Map<Adventurer, int[]> adventurers = null;

		treasureMap = loadTreasureMap(treasureMapFileName);
		adventurers = loadAdventurers(adventurerFileName);
		
		if (adventurers.isEmpty()) {
			throw new ParseException("Adventurer file is empty.", 0);
		}

		for (Map.Entry<Adventurer, int[]> entry : adventurers.entrySet()) {
			int x = entry.getValue()[0];
			int y = entry.getValue()[1];
			treasureMap.setAdventurer(entry.getKey(), x, y);
		}

		return treasureMap;

	}

	/**
	 * Generates adventurers from a file.
	 * 
	 * @param filename
	 *            The name of the file to read
	 * @return A map which keys are the adventurers and which values are their
	 *         positions
	 * @throws IOException
	 *             If an error occurs while reading the file or if reading
	 *             permission is denied
	 * @throws ParseException
	 *             If the text file a line returns an unsupported line
	 */
	public static Map<Adventurer, int[]> loadAdventurers(String filename)
			throws IOException, ParseException {

		Map<Adventurer, int[]> adventurers = new HashMap<Adventurer, int[]>();
		File f = new File(filename);
		String line = null;

		try (BufferedReader reader = Files.newBufferedReader(f.toPath())) {

			// Read next lines
			while ((line = reader.readLine()) != null) {

				Adventurer adventurer = null;

				// Name of adventurer
				String name = line.split(" ")[0];

				// Position of adventurer
				int x = Integer.parseInt(line.split(" ")[1].split("-")[0]);
				int y = Integer.parseInt(line.split(" ")[1].split("-")[1]);
				int[] position = { x, y };

				// Orientation of adventurer
				Orientation orientation = null;
				switch (line.split(" ")[2]) {
				case "E":
					orientation = Orientation.EAST;
					break;
				case "W":
					orientation = Orientation.WEST;
					break;
				case "N":
					orientation = Orientation.NORTH;
					break;
				case "S":
					orientation = Orientation.SOUTH;
					break;
				default:
					throw new ParseException("Oriention: " + line.split(" ")[2]
							+ " not supported.", 0);
				}

				// Create adventurer
				adventurer = new Adventurer(name, orientation);

				// Actions of adventurer
				String[] actionsStr = line.split(" ")[3].split("");
				for (int i = 0; i < actionsStr.length; i++) {
					switch (actionsStr[i]) {
					case "A":
						adventurer.appendAction(Action.FORWARD);
						break;
					case "G":
						adventurer.appendAction(Action.TURN_LEFT);
						break;
					case "D":
						adventurer.appendAction(Action.TURN_RIGHT);
						break;
					default:
						throw new IllegalArgumentException("Action "
								+ actionsStr[i] + " not supported");
					}
				}

				// Add adventurer and their position
				adventurers.put(adventurer, position);
			}
		}

		return adventurers;
	}

	/**
	 * Generates a treasure map from the content of a file.
	 * 
	 * @param filename
	 *            The name of the file to read
	 * @return The treasure map generated
	 * @throws IOException
	 *             If an error occurs while reading the file
	 * @throws IllegalStateException
	 *             If the given file is empty
	 * @throws IllegalArgumentException
	 *             If a field in the file is incorrect
	 */
	public static TreasureMap loadTreasureMap(String filename)
			throws IOException {

		File f = new File(filename);
		String line = null;
		TreasureMap treasureMap = null;

		try (BufferedReader reader = Files.newBufferedReader(f.toPath())) {

			// First line must define the board size
			line = reader.readLine();
			if (line == null) {
				throw new IllegalStateException("File " + filename
						+ " is empty.");
			} else if (!line.split(" ")[0].equals("C")) {
				// First line must give the treasure map size (C width height)
				throw new IllegalArgumentException(
						"First line of map file should be: C width height.");
			} else {
				// Create treasure map board
				int width = Integer.parseInt(line.split(" ")[1]);
				int height = Integer.parseInt(line.split(" ")[2]);

				if (width <= 0 || height <= 0) {
					throw new IllegalArgumentException(
							"Wrong size given for the treasure map (" + width
									+ ", " + height + ").");
				}

				treasureMap = new TreasureMap(width, height);
			}

			// Read next lines
			while ((line = reader.readLine()) != null) {

				String[] lineContent = line.split(" ");

				if (lineContent[0].equals("T")) {
					// Land cell
					if (lineContent.length != 3) {
						throw new IllegalArgumentException(
								"Wrong number of elements in treasure configuration.");
					} else if (lineContent[1].split("-").length != 2) {
						throw new IllegalArgumentException(
								"Wrong position format for treasure configuration.");
					}
					int x = Integer.parseInt(lineContent[1].split("-")[0]);
					int y = Integer.parseInt(lineContent[1].split("-")[1]);
					int nbTreasures = Integer.parseInt(lineContent[2]);
					treasureMap.setCell(new Land(nbTreasures), x, y);

				} else if (lineContent[0].equals("M")) {
					// Mountain cell
					if (lineContent.length != 2) {
						throw new IllegalArgumentException(
								"Wrong number of elements for mountain configuration.");
					} else if (lineContent[1].split("-").length != 2) {
						throw new IllegalArgumentException(
								"Wrong position format for mountain configuration.");
					}
					int x = Integer.parseInt(lineContent[1].split("-")[0]);
					int y = Integer.parseInt(lineContent[1].split("-")[1]);
					treasureMap.setCell(new Mountain(), x, y);
				}

			}
		}

		return treasureMap;
	}
}

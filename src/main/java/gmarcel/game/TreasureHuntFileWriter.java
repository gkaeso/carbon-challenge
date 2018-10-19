package gmarcel.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import gmarcel.model.Adventurer;

/**
 * 
 * This class contains a number of utility static methods used by the treasure
 * hunt application to write into text files.
 * 
 */
public class TreasureHuntFileWriter {

	/**
	 * Writes a given adventurer elements into a file.
	 * 
	 * @param adventurer
	 *            The adventurer whose attributes are to be written
	 * @param filename
	 *            The name of the file to write or create. The name should
	 *            include the path to the file
	 * @throws IOException
	 *             If an I/O error occurs while creating or writing into the
	 *             file; or if the file write permission if denied
	 */
	public static void writeAdventurer(List<Adventurer> adventurers,
			String filename) throws IOException {

		List<String> strings = new ArrayList<String>();

		for (Adventurer adv : adventurers) {
			String line = adv.getName() + " "
					+ adv.getOrientation().getShortname() + " " + adv.getX()
					+ "-" + adv.getY() + " " + adv.getNbTreasures()
					+ System.lineSeparator();
			strings.add(line);
		}

		// Open file
		File f = new File(filename);
		if (f.exists() && !f.canWrite()) {
			throw new IOException("File " + filename
					+ " write permission denied.");
		}

		// Write
		try (BufferedWriter writer = Files.newBufferedWriter(f.toPath(),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING)) {
			for (String s : strings) {
				writer.write(s);
			}
		}

	}
}

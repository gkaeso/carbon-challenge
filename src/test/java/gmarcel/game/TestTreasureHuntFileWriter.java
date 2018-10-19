package gmarcel.game;

import gmarcel.model.Action;
import gmarcel.model.Adventurer;
import gmarcel.model.Orientation;
import gmarcel.model.TreasureMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Test;

public class TestTreasureHuntFileWriter {

	@Test
	public void testWriteAdventurerToFile() {

		TreasureMap treasureMap = new TreasureMap(5, 5);
		Adventurer adventurer = new Adventurer("Indiana", Orientation.EAST);
		adventurer.appendAction(Action.FORWARD);
		adventurer.appendAction(Action.TURN_RIGHT);
		adventurer.appendAction(Action.FORWARD);
		adventurer.appendAction(Action.TURN_LEFT);
		treasureMap.setAdventurer(adventurer, 1, 1);

		String filename = "src/test/resources/gmarcel/game/out.txt";
		File f = new File(filename);
		if (f.exists()) {
			f.delete();
		}
		while (treasureMap.hasRemainingAction()) {
			treasureMap.doAction(adventurer);
		}

		try {
			TreasureHuntFileWriter.writeAdventurer(
					treasureMap.getAdventurers(), filename);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (BufferedReader reader = Files.newBufferedReader(f.toPath())) {
			String line = null;
			line = reader.readLine();
			Assert.assertEquals("Indiana E 2-2 0", line);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test(expected = IOException.class)
	public void testWriteAdventurerToFileException() throws IOException {

		TreasureMap treasureMap = new TreasureMap(5, 5);
		Adventurer adventurer = new Adventurer("Indiana", Orientation.EAST);
		adventurer.appendAction(Action.FORWARD);
		adventurer.appendAction(Action.TURN_RIGHT);
		adventurer.appendAction(Action.FORWARD);
		adventurer.appendAction(Action.TURN_LEFT);
		treasureMap.setAdventurer(adventurer, 1, 1);

		String filename = "src/test/resources/files/out_no_write.txt";

		TreasureHuntFileWriter.writeAdventurer(treasureMap.getAdventurers(),
				filename);

	}

}

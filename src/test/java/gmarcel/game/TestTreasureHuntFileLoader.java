package gmarcel.game;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import gmarcel.model.Action;
import gmarcel.model.Adventurer;
import gmarcel.model.Cell;
import gmarcel.model.Orientation;
import gmarcel.model.TreasureMap;

public class TestTreasureHuntFileLoader {

	@Test
	public void testLoadTreasureMap() {

		TreasureMap treasureMap = null;
		try {
			treasureMap = TreasureHuntFileLoader
					.loadTreasureMap("src/test/resources/gmarcel/game/test-map.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Cell[][] cells = treasureMap.getCells();
		Assert.assertEquals(6 + 2, cells.length);
		Assert.assertEquals(5 + 2, cells[0].length);
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (i == 4 && j == 2) {
					Assert.assertFalse(cells[i][j].isOccupied());
					Assert.assertEquals(1, cells[i][j].pickUpTreasure());
				}
				if (i == 1 && j == 4) {
					Assert.assertFalse(cells[i][j].isOccupied());
					Assert.assertEquals(3, cells[i][j].pickUpTreasure());
				} else if (i == 5 && j == 3) {
					Assert.assertTrue(cells[i][j].isOccupied());
					Assert.assertEquals(0, cells[i][j].pickUpTreasure());
				}
			}
		}

	}

	@Test(expected = IOException.class)
	public void testLoadTreasureMapException() throws IOException,
			ParseException {
		TreasureHuntFileLoader.loadAdventurers("wrong_filename.txt");
	}

	@Test
	public void testLoadAdventurer() throws ParseException {

		Map<Adventurer, int[]> advsMap = null;
		try {
			advsMap = TreasureHuntFileLoader
					.loadAdventurers("src/test/resources/gmarcel/game/test-adv.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Entry<Adventurer, int[]> entry : advsMap.entrySet()) {

			List<Action> actions = entry.getKey().getRemainingActions();

			if (entry.getKey().getName().equals("Indiana")) {
				Assert.assertEquals(1, entry.getValue()[0]);
				Assert.assertEquals(3, entry.getValue()[1]);
				Assert.assertEquals(Orientation.EAST, entry.getKey()
						.getOrientation());
				for (int i = 0; i < actions.size(); i++) {
					switch (i) {
					case 0:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 1:
						Assert.assertEquals(Action.TURN_LEFT, actions.get(i));
						break;
					case 2:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 3:
						Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
						break;
					case 4:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 5:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 6:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 7:
						Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
						break;
					case 8:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 9:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					}
				}
			} else if (entry.getKey().getName().equals("Jones")) {
				Assert.assertEquals(4, entry.getValue()[0]);
				Assert.assertEquals(4, entry.getValue()[1]);
				Assert.assertEquals(Orientation.SOUTH, entry.getKey()
						.getOrientation());
				for (int i = 0; i < actions.size(); i++) {
					switch (i) {
					case 0:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 1:
						Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
						break;
					case 2:
						Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
						break;
					case 3:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 4:
						Assert.assertEquals(Action.TURN_LEFT, actions.get(i));
						break;
					}
				}
			} else if (entry.getKey().getName().equals("Indie")) {
				Assert.assertEquals(1, entry.getValue()[0]);
				Assert.assertEquals(4, entry.getValue()[1]);
				Assert.assertEquals(Orientation.NORTH, entry.getKey()
						.getOrientation());
				for (int i = 0; i < actions.size(); i++) {
					switch (i) {
					case 0:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 1:
						Assert.assertEquals(Action.TURN_LEFT, actions.get(i));
						break;
					case 2:
						Assert.assertEquals(Action.FORWARD, actions.get(i));
						break;
					case 3:
						Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
						break;
					case 4:
						Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
						break;
					case 5:
						Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
						break;
					}
				}
			}
		}

	}

	@Test(expected = IOException.class)
	public void testLoadAdventurersException() throws IOException,
			ParseException {
		TreasureHuntFileLoader.loadAdventurers("wrong_filename.txt");
	}

}

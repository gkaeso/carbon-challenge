package gmarcel.model;

import org.junit.Assert;
import org.junit.Test;

public class TestTreasureMap {

	@Test
	public void testMoveForward() {

		TreasureMap treasureMap = new TreasureMap(4, 3);
		Adventurer adv1 = new Adventurer("Indiana", Orientation.EAST);
		Adventurer adv2 = new Adventurer("Indiana", Orientation.WEST);
		treasureMap.setAdventurer(adv1, 1, 1);
		treasureMap.setAdventurer(adv2, 4, 1);

		// FORWARD
		treasureMap.moveForward(adv1);
		treasureMap.moveForward(adv2);
		Assert.assertEquals(2, adv1.getX());
		Assert.assertEquals(1, adv1.getY());
		Assert.assertEquals(Orientation.EAST, adv1.getOrientation());
		Assert.assertEquals(3, adv2.getX());
		Assert.assertEquals(1, adv2.getY());
		Assert.assertEquals(Orientation.WEST, adv2.getOrientation());

		// adv1's move blocked because adv2 is already on the cell
		treasureMap.moveForward(adv1);
		Assert.assertEquals(2, adv1.getX());
		Assert.assertEquals(1, adv1.getY());
		Assert.assertEquals(Orientation.EAST, adv1.getOrientation());
	}

	@Test
	public void testHasRemainingAction() {

		TreasureMap treasureMap = new TreasureMap(4, 3);
		Adventurer adv1 = new Adventurer("Indiana", Orientation.EAST);
		adv1.appendAction(Action.FORWARD);
		Adventurer adv2 = new Adventurer("Indiana", Orientation.EAST);
		adv2.appendAction(Action.FORWARD);
		adv2.appendAction(Action.FORWARD);
		Adventurer adv3 = new Adventurer("Indiana", Orientation.EAST);
		adv3.appendAction(Action.FORWARD);
		adv3.appendAction(Action.FORWARD);
		adv3.appendAction(Action.FORWARD);
		treasureMap.setAdventurer(adv1, 1, 1);
		treasureMap.setAdventurer(adv2, 1, 2);
		treasureMap.setAdventurer(adv3, 1, 3);

		Assert.assertTrue(treasureMap.hasRemainingAction());

		treasureMap.doAction(adv1);
		treasureMap.doAction(adv2);
		treasureMap.doAction(adv3);
		Assert.assertTrue(treasureMap.hasRemainingAction());

		treasureMap.doAction(adv1);
		treasureMap.doAction(adv2);
		treasureMap.doAction(adv3);
		Assert.assertTrue(treasureMap.hasRemainingAction());

		treasureMap.doAction(adv1);
		treasureMap.doAction(adv2);
		treasureMap.doAction(adv3);
		Assert.assertFalse(treasureMap.hasRemainingAction());

	}

	@Test
	public void testDoAction() {

		TreasureMap treasureMap = new TreasureMap(3, 3);
		treasureMap.setCell(new Land(5), 2, 2);
		Adventurer adventurer = new Adventurer("Indiana", Orientation.EAST);
		adventurer.appendAction(Action.FORWARD);
		adventurer.appendAction(Action.TURN_RIGHT);
		adventurer.appendAction(Action.FORWARD);
		adventurer.appendAction(Action.FORWARD);
		adventurer.appendAction(Action.TURN_LEFT);
		adventurer.appendAction(Action.FORWARD);
		treasureMap.setAdventurer(adventurer, 1, 1);

		// INITIAL POSITION
		Assert.assertEquals(1, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(Orientation.EAST, adventurer.getOrientation());
		Assert.assertEquals(0, adventurer.getNbTreasures());

		// FORWARD
		treasureMap.doAction(adventurer);
		Assert.assertEquals(2, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(Orientation.EAST, adventurer.getOrientation());
		Assert.assertEquals(0, adventurer.getNbTreasures());

		// TURN RIGHT
		treasureMap.doAction(adventurer);
		Assert.assertEquals(2, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(Orientation.SOUTH, adventurer.getOrientation());
		Assert.assertEquals(0, adventurer.getNbTreasures());

		// FORWARD
		treasureMap.doAction(adventurer);
		Assert.assertEquals(2, adventurer.getX());
		Assert.assertEquals(2, adventurer.getY());
		Assert.assertEquals(Orientation.SOUTH, adventurer.getOrientation());
		Assert.assertEquals(5, adventurer.getNbTreasures());

		// FORWARD
		treasureMap.doAction(adventurer);
		Assert.assertEquals(2, adventurer.getX());
		Assert.assertEquals(3, adventurer.getY());
		Assert.assertEquals(Orientation.SOUTH, adventurer.getOrientation());
		Assert.assertEquals(5, adventurer.getNbTreasures());

		// TURN LEFT
		treasureMap.doAction(adventurer);
		Assert.assertEquals(2, adventurer.getX());
		Assert.assertEquals(3, adventurer.getY());
		Assert.assertEquals(Orientation.EAST, adventurer.getOrientation());
		Assert.assertEquals(5, adventurer.getNbTreasures());

		// FORWARD
		treasureMap.doAction(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(3, adventurer.getY());
		Assert.assertEquals(Orientation.EAST, adventurer.getOrientation());
		Assert.assertEquals(5, adventurer.getNbTreasures());

		// END (no remaining action)
		treasureMap.doAction(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(3, adventurer.getY());
		Assert.assertEquals(Orientation.EAST, adventurer.getOrientation());
		Assert.assertEquals(5, adventurer.getNbTreasures());

	}

	@Test
	public void testNextAction() {

		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.TURN_LEFT);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.TURN_RIGHT);
		adv.appendAction(Action.FORWARD);

		TreasureMap treasureMap = new TreasureMap(2, 2);
		treasureMap.setAdventurer(adv, 1, 1);

		Assert.assertEquals(Action.FORWARD, treasureMap.nextAction(adv));
		Assert.assertEquals(Action.FORWARD, treasureMap.nextAction(adv));
		Assert.assertEquals(Action.TURN_LEFT, treasureMap.nextAction(adv));
		Assert.assertEquals(Action.FORWARD, treasureMap.nextAction(adv));
		Assert.assertEquals(Action.TURN_RIGHT, treasureMap.nextAction(adv));
		Assert.assertEquals(Action.FORWARD, treasureMap.nextAction(adv));
		Assert.assertEquals(Action.END, treasureMap.nextAction(adv));

	}

	@Test
	public void testSetAdventurer() {

		TreasureMap treasureMap = new TreasureMap(2, 2);
		treasureMap.setCell(new Land(5), 2, 1);
		Adventurer adv1 = new Adventurer("Indiana", Orientation.NORTH);
		Adventurer adv2 = new Adventurer("Indiana", Orientation.NORTH);
		treasureMap.setAdventurer(adv1, 1, 1); // On normal land
		treasureMap.setAdventurer(adv2, 2, 1); // On land with treasure

		for (int i = 0; i < treasureMap.getCells().length; i++) {
			for (int j = 0; j < treasureMap.getCells()[i].length; j++) {
				if (i == 1 && j == 1) {
					Assert.assertFalse(treasureMap.getCells()[i][j].hasTreasure());
					Assert.assertEquals(0, adv1.getNbTreasures());
					Assert.assertTrue(treasureMap.getCells()[i][j].isOccupied());
				} else if (i == 2 && j == 1) {
					// Adventurer picked up the treasure
					Assert.assertFalse(treasureMap.getCells()[i][j].hasTreasure());
					Assert.assertEquals(5, adv2.getNbTreasures());
					Assert.assertTrue(treasureMap.getCells()[i][j].isOccupied());
				} else if ((i == 1 && j == 2) || (i == 2 && j == 2)) {
					// Land with no adventurer
					Assert.assertFalse(treasureMap.getCells()[i][j].hasTreasure());
					Assert.assertEquals(5, adv2.getNbTreasures());
					Assert.assertFalse(treasureMap.getCells()[i][j].isOccupied());
				} else {
					// Border cells
					Assert.assertFalse(treasureMap.getCells()[i][j].hasTreasure());
					Assert.assertTrue(treasureMap.getCells()[i][j].isOccupied());
				}
			}
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAdventurerOnMountainException() {

		// Test setAdventurer() if adventurer is set on mountain
		TreasureMap treasureMap = new TreasureMap(2, 2);
		treasureMap.setCell(new Mountain(), 1, 1);
		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		treasureMap.setAdventurer(adv, 1, 1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAdventurerOnOtherAdventurerException() {

		// Test setAdventurer() if adventurer is set on other adventurer
		TreasureMap treasureMap = new TreasureMap(2, 2);
		treasureMap.setCell(new Mountain(), 1, 1);
		Adventurer adv1 = new Adventurer("Indiana", Orientation.NORTH);
		Adventurer adv2 = new Adventurer("Indiana", Orientation.NORTH);
		treasureMap.setAdventurer(adv1, 1, 1);
		treasureMap.setAdventurer(adv2, 1, 1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAdventurerOutOfMapException() {

		// Test setAdventurer() if adventurer is set out of treasure map
		TreasureMap treasureMap = new TreasureMap(2, 2);
		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		treasureMap.setAdventurer(adv, 0, 0);

	}

}

package gmarcel.model;

import org.junit.Assert;
import org.junit.Test;

import gmarcel.model.Adventurer;
import gmarcel.model.Land;
import gmarcel.model.Mountain;
import gmarcel.model.Orientation;
import gmarcel.model.TreasureMap;

public class TestTreasureHuntSprintOne {

	@Test
	public void testTurnLeft() {

		Adventurer adventurer = new Adventurer("Indiana", Orientation.EAST);
		TreasureMap treasureMap = new TreasureMap(3, 3);
		treasureMap.setAdventurer(adventurer, 1, 1);

		treasureMap.turnLeft(adventurer);
		Assert.assertEquals(Orientation.NORTH, adventurer.getOrientation());
		treasureMap.turnLeft(adventurer);
		Assert.assertEquals(Orientation.WEST, adventurer.getOrientation());
		treasureMap.turnLeft(adventurer);
		Assert.assertEquals(Orientation.SOUTH, adventurer.getOrientation());
		treasureMap.turnLeft(adventurer);
		Assert.assertEquals(Orientation.EAST, adventurer.getOrientation());

	}

	@Test
	public void testTurnRight() {

		Adventurer adventurer = new Adventurer("Indiana", Orientation.EAST);
		TreasureMap treasureMap = new TreasureMap(3, 3);
		treasureMap.setAdventurer(adventurer, 1, 1);

		treasureMap.turnRight(adventurer);
		Assert.assertEquals(Orientation.SOUTH, adventurer.getOrientation());
		treasureMap.turnRight(adventurer);
		Assert.assertEquals(Orientation.WEST, adventurer.getOrientation());
		treasureMap.turnRight(adventurer);
		Assert.assertEquals(Orientation.NORTH, adventurer.getOrientation());
		treasureMap.turnRight(adventurer);
		Assert.assertEquals(Orientation.EAST, adventurer.getOrientation());

	}

	@Test
	public void testMoveForward() {

		Adventurer adventurer = new Adventurer("Indiana", Orientation.WEST);
		TreasureMap treasureMap = new TreasureMap(3, 3);
		treasureMap.setCell(new Land(2), 3, 1);
		treasureMap.setCell(new Land(1), 3, 3);
		treasureMap.setCell(new Mountain(), 2, 2);
		treasureMap.setAdventurer(adventurer, 1, 1);

		// Move to West blocked (out of bounds)
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(1, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(0, adventurer.getNbTreasures());

		// Move to North blocked (out of bounds)
		treasureMap.turnRight(adventurer);
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(1, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(0, adventurer.getNbTreasures());

		// Move to East
		treasureMap.turnRight(adventurer);
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(2, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(0, adventurer.getNbTreasures());

		// Move to South blocked (mountain)
		treasureMap.turnRight(adventurer);
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(2, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(0, adventurer.getNbTreasures());

		// Move to East
		treasureMap.turnLeft(adventurer);
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(2, adventurer.getNbTreasures());

		// Move to East blocked (out of bounds)
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(1, adventurer.getY());
		Assert.assertEquals(2, adventurer.getNbTreasures());

		// Move to South
		treasureMap.turnRight(adventurer);
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(2, adventurer.getY());
		Assert.assertEquals(2, adventurer.getNbTreasures());

		// Move to West blocked (mountain)
		treasureMap.turnRight(adventurer);
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(2, adventurer.getY());
		Assert.assertEquals(2, adventurer.getNbTreasures());

		// Move to South
		treasureMap.turnLeft(adventurer);
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(3, adventurer.getY());
		Assert.assertEquals(3, adventurer.getNbTreasures());

		// Move to South blocked (out of bounds)
		treasureMap.moveForward(adventurer);
		Assert.assertEquals(3, adventurer.getX());
		Assert.assertEquals(3, adventurer.getY());
		Assert.assertEquals(3, adventurer.getNbTreasures());

	}

}

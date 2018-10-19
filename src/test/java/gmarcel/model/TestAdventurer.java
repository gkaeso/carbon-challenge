package gmarcel.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestAdventurer {

	@Test
	public void testAppendAction() {

		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.TURN_LEFT);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.TURN_RIGHT);
		adv.appendAction(Action.FORWARD);

		List<Action> actions = adv.getRemainingActions();
		for (int i = 0; i < actions.size(); i++) {
			if (i == 0) {
				Assert.assertEquals(Action.FORWARD, actions.get(i));
			} else if (i == 1) {
				Assert.assertEquals(Action.FORWARD, actions.get(i));
			} else if (i == 2) {
				Assert.assertEquals(Action.TURN_LEFT, actions.get(i));
			} else if (i == 3) {
				Assert.assertEquals(Action.FORWARD, actions.get(i));
			} else if (i == 4) {
				Assert.assertEquals(Action.TURN_RIGHT, actions.get(i));
			} else if (i == 5) {
				Assert.assertEquals(Action.FORWARD, actions.get(i));
			}
		}

	}

	@Test
	public void testPopNextAction() {

		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.TURN_LEFT);
		adv.appendAction(Action.FORWARD);
		adv.appendAction(Action.TURN_RIGHT);
		adv.appendAction(Action.FORWARD);

		for (int i = 0; i < adv.getRemainingActions().size() + 1; i++) {
			// size() + 1 --> to test when no action remains
			if (i == 0) {
				Assert.assertEquals(Action.FORWARD, adv.popNextAction());
			} else if (i == 1) {
				Assert.assertEquals(Action.FORWARD, adv.popNextAction());
			} else if (i == 2) {
				Assert.assertEquals(Action.TURN_LEFT, adv.popNextAction());
			} else if (i == 3) {
				Assert.assertEquals(Action.FORWARD, adv.popNextAction());
			} else if (i == 4) {
				Assert.assertEquals(Action.TURN_RIGHT, adv.popNextAction());
			} else if (i == 5) {
				Assert.assertEquals(Action.FORWARD, adv.popNextAction());
			} else {
				Assert.assertEquals(Action.END, adv.popNextAction());
			}
		}

	}

	@Test
	public void testAddTreasure() {

		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		Assert.assertEquals(0, adv.getNbTreasures());
		adv.addTreasure(3);
		Assert.assertEquals(3, adv.getNbTreasures());
		adv.addTreasure(5);
		Assert.assertEquals(8, adv.getNbTreasures());
		adv.addTreasure(2);
		Assert.assertEquals(10, adv.getNbTreasures());

	}

	@Test
	public void testTurnLeft() {

		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		adv.turnLeft();
		Assert.assertEquals(Orientation.WEST, adv.getOrientation());
		adv.turnLeft();
		Assert.assertEquals(Orientation.SOUTH, adv.getOrientation());
		adv.turnLeft();
		Assert.assertEquals(Orientation.EAST, adv.getOrientation());
		adv.turnLeft();
		Assert.assertEquals(Orientation.NORTH, adv.getOrientation());

	}

	@Test
	public void testTurnRight() {

		Adventurer adv = new Adventurer("Indiana", Orientation.NORTH);
		adv.turnRight();
		Assert.assertEquals(Orientation.EAST, adv.getOrientation());
		adv.turnRight();
		Assert.assertEquals(Orientation.SOUTH, adv.getOrientation());
		adv.turnRight();
		Assert.assertEquals(Orientation.WEST, adv.getOrientation());
		adv.turnRight();
		Assert.assertEquals(Orientation.NORTH, adv.getOrientation());

	}

}

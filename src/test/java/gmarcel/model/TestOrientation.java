package gmarcel.model;

import org.junit.Assert;
import org.junit.Test;

public class TestOrientation {

	@Test
	public void testOrientation() {

		// Test EAST
		Assert.assertEquals("E", Orientation.EAST.getShortname());
		Assert.assertEquals(Orientation.NORTH, Orientation.EAST.turnLeft());
		Assert.assertEquals(Orientation.SOUTH, Orientation.EAST.turnRight());

		// Test WEST
		Assert.assertEquals("W", Orientation.WEST.getShortname());
		Assert.assertEquals(Orientation.SOUTH, Orientation.WEST.turnLeft());
		Assert.assertEquals(Orientation.NORTH, Orientation.WEST.turnRight());

		// Test NORTH
		Assert.assertEquals("N", Orientation.NORTH.getShortname());
		Assert.assertEquals(Orientation.WEST, Orientation.NORTH.turnLeft());
		Assert.assertEquals(Orientation.EAST, Orientation.NORTH.turnRight());

		// Test SOUTH
		Assert.assertEquals("S", Orientation.SOUTH.getShortname());
		Assert.assertEquals(Orientation.EAST, Orientation.SOUTH.turnLeft());
		Assert.assertEquals(Orientation.WEST, Orientation.SOUTH.turnRight());

	}

}

package gmarcel.model;

import org.junit.Assert;
import org.junit.Test;

public class TestMountain {

	@Test
	public void testMountain() {

		Cell mountain = new Mountain();

		Assert.assertEquals(false, mountain.hasTreasure());
		Assert.assertEquals(0, mountain.pickUpTreasure()); // Before picking up
		Assert.assertEquals(0, mountain.pickUpTreasure()); // After picking up
		Assert.assertEquals(true, mountain.isOccupied());
		mountain.setOccupied(false); // Does nothing
		Assert.assertEquals(true, mountain.isOccupied());

	}

}

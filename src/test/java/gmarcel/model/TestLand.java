package gmarcel.model;

import org.junit.Assert;
import org.junit.Test;

public class TestLand {

	@Test
	public void testLand() {

		Cell land1 = new Land(0);
		Cell land2 = new Land(5);

		// Land 1
		Assert.assertEquals(false, land1.hasTreasure());
		Assert.assertEquals(0, land1.pickUpTreasure()); // Before picking up
		Assert.assertEquals(0, land1.pickUpTreasure()); // After picking up
		Assert.assertEquals(false, land1.isOccupied());
		land1.setOccupied(true);
		Assert.assertEquals(true, land1.isOccupied());

		// Land 2
		Assert.assertEquals(true, land2.hasTreasure());
		Assert.assertEquals(5, land2.pickUpTreasure()); // Before picking up
		Assert.assertEquals(0, land2.pickUpTreasure()); // After picking up
		Assert.assertEquals(false, land2.isOccupied());
		land2.setOccupied(true);
		Assert.assertEquals(true, land2.isOccupied());

	}

}

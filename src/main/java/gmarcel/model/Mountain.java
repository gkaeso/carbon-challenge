package gmarcel.model;

/**
 * 
 * This class implements the Cell interface to represent a mountain.
 * 
 * A mountain is a board cell which cannot be accessed (always occupied). It
 * does not have a treasure.
 * 
 * @see Cell
 * 
 */
public class Mountain implements Cell {

	@Override
	public void setOccupied(boolean occupied) {
		// Do nothing. A mountain cannot be accessible for adventurers.
	}

	@Override
	public boolean isOccupied() {
		// A mountain should always be inaccessible for adventurers.
		return true;
	}

	@Override
	public boolean hasTreasure() {
		// A mountain cannot have a treasure as it is not accessible.
		return false;
	}

	@Override
	public int pickUpTreasure() {
		// A mountain cannot have a treasure as it is not accessible.
		return 0;
	}

}

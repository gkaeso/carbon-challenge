package gmarcel.model;

/**
 * 
 * This class implements the Cell interface to represent a land.
 * 
 * A land is a board cell which can be accessed and may have treasures. If a
 * cell is occupied, it cannot be accessed until it is not occupied any longer.
 * When the treasures of a land are picked, there is not treasures on the land
 * any more afterwards.
 * 
 * @see Cell
 * 
 */
public class Land implements Cell {

	/** Number of treasures on the cell. */
	private int nbTreasures;

	/** Flag indicating if the cell is occupied. */
	private boolean occupied;

	/**
	 * Constructs a Land with a given number of treasures. Default land is not
	 * occupied.
	 * 
	 * @param nbTreasures
	 *            The number of treasures on the land
	 */
	public Land(int nbTreasures) {
		this.nbTreasures = nbTreasures;
		this.occupied = false;
	}

	@Override
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	@Override
	public boolean isOccupied() {
		return this.occupied;
	}

	@Override
	public boolean hasTreasure() {
		return nbTreasures > 0;
	}

	@Override
	public int pickUpTreasure() {
		int treasures = this.nbTreasures;
		this.nbTreasures = 0;
		return treasures;
	}

}

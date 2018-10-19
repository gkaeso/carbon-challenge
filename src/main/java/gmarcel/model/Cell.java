package gmarcel.model;

/**
 * 
 * This interface represents a board cell; typically a treasure map cell.
 * 
 * A cell is represented by two typical elements: a flag and a number of
 * treasures. The flag indicates whether the cell is already occupied (hence not
 * accessible).
 * 
 */
public interface Cell {

	/**
	 * Sets the flag indicating whether the cell is occupied or not.
	 * 
	 * @param occupied
	 *            The new "occupied flag" of the cell
	 */
	void setOccupied(boolean occupied);

	/**
	 * Checks if the cell is occupied.
	 * 
	 * @return true if the cell is occupied, else false
	 */
	boolean isOccupied();

	/**
	 * Checks if the cell has a treasure.
	 * 
	 * @return true if the cell has a treasure, else false
	 */
	boolean hasTreasure();

	/**
	 * Picks up the treasures on the cell and returns the number of treasures
	 * picked.
	 * 
	 * @return The number of treasures picked up
	 */
	int pickUpTreasure();

}

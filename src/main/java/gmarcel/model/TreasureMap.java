package gmarcel.model;

/**
 * 
 * This class represents a board: the treasure map.
 * 
 * A treasure map contains a board, represented by a matrix of cells, 
 * and a list of adventurers which can move across the it.
 * 
 * The treasure map controls all its adventurers. It is in charge of moving adventurers and
 * picking up treasures. Adventurers can neither move out of the board or onto mountain cells.
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class TreasureMap {

	/** Matrix of the cells defining the treasure map. */
	private final Cell[][] cells;

	/** List of the adventurers on the treasure map. */
	private final List<Adventurer> adventurers;

	/**
	 * Constructs a treasure map with a given width and height.
	 * 
	 * @param width
	 *            The width of the treasure map
	 * @param height
	 *            The height of the treasure map
	 */
	public TreasureMap(int width, int height) {
		super();
		this.cells = initTreasureMap(width, height);
		this.adventurers = new ArrayList<>();
	}

	/**
	 * Returns the matrix of cells in the treasure map
	 * 
	 * @return The matrix of cells in the treasure map
	 */
	public Cell[][] getCells() {
		return cells;
	}

	/**
	 * Sets a new cell at the given position.
	 * 
	 * @param cell
	 *            The new cell to be put in the cell matrix
	 * @param x
	 *            The horizontal position of the new cell
	 * @param y
	 *            The vertical position of the new cell
	 */
	public void setCell(Cell cell, int x, int y) {
		this.cells[x][y] = cell;
	}

	/**
	 * Returns the list of adventurers in the treasure map.
	 * 
	 * @return The list of adventurers in the treasure map
	 */
	public List<Adventurer> getAdventurers() {
		return this.adventurers;
	}

	/**
	 * Sets a new adventurer at a given position on the treasure map.
	 * 
	 * @param adventurer
	 *            The adventurer to be added in the treasure map
	 * @param x
	 *            The horizontal position of the new adventurer
	 * @param y
	 *            The vertical position of the new adventurer
	 * @throws IllegalArgumentException
	 *             If the adventurer's position is already occupied in the
	 *             treasure map
	 */
	// This method is only used when the treasure map is loaded.
	public void setAdventurer(Adventurer adventurer, int x, int y) {

		// Adventurer's initial position should not be on an occupied cell.
		if (this.cells[x][y].isOccupied()) {
			throw new IllegalArgumentException(
					"Adventurer " + adventurer.getName() + " can't be put at position " + x + ", " + y);
		}
		adventurer.setX(x);
		adventurer.setY(y);
		this.pickUpTreasureIfPossible(adventurer, x, y);
		this.cells[x][y].setOccupied(true);
		this.adventurers.add(adventurer);
	}

	/**
	 * Returns the next action of a given adventurer.
	 * 
	 * @param adventurer
	 *            The adventurer whose next action is returned
	 * @return The next action of the adventurer
	 */
	public Action nextAction(Adventurer adventurer) {
		return adventurer.popNextAction();
	}

	/**
	 * Does and returns the next action of a given adventurer.
	 * 
	 * @return The action done
	 * @throws IllegalAccessException
	 *             If action is not supported
	 */
	public Action doAction(Adventurer adventurer) {

		Action nextAction = this.nextAction(adventurer);
		switch (nextAction) {
		case FORWARD:
			this.moveForward(adventurer);
			break;
		case TURN_LEFT:
			this.turnLeft(adventurer);
			break;
		case TURN_RIGHT:
			this.turnRight(adventurer);
			break;
		case END:
			// Do nothing. Adventurer stands still.
			break;
		default:
			throw new IllegalArgumentException("Action" + nextAction + " not supported");
		}
		return nextAction;
	}

	/**
	 * Checks if any of the adventurers on the treasure map has a remaining
	 * action to do.
	 * 
	 * @return true if any adventurer has a remaining action to do, else false
	 */
	public boolean hasRemainingAction() {

		for (Adventurer adventurer : this.getAdventurers()) {
			if (!adventurer.getRemainingActions().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Moves the adventurer forward (one cell step) and loots the next cell if a
	 * treasure exists.
	 * 
	 * @param adventurer
	 *            The adventurer to be moved forward
	 */
	public void moveForward(Adventurer adventurer) {
		int forwardedX = getForwardedX(adventurer);
		int forwardedY = getForwardedY(adventurer);

		if (this.cells[forwardedX][forwardedY].isOccupied()) {
			// Do nothing. The move is blocked.
		} else {
			this.cells[adventurer.getX()][adventurer.getY()].setOccupied(false);
			this.cells[forwardedX][forwardedY].setOccupied(true);
			adventurer.setX(forwardedX);
			adventurer.setY(forwardedY);
			this.pickUpTreasureIfPossible(adventurer, forwardedX, forwardedY);
		}
	}

	/**
	 * Turns the adventurer's orientation to the left.
	 * 
	 * @param adventurer
	 *            The adventurer to be turned left.
	 */
	public void turnLeft(Adventurer adventurer) {
		adventurer.turnLeft();
	}

	/**
	 * Turns the adventurer's orientation to the right.
	 * 
	 * @param adventurer
	 *            The adventurer to be turned right.
	 */
	public void turnRight(Adventurer adventurer) {
		adventurer.turnRight();
	}

	/**
	 * Initialises the treasure map. All the cells are Land objects with no
	 * treasure. The border cells are set as occupied.
	 * 
	 * @param width
	 *            The width of the treasure map
	 * @param height
	 *            The height of the treasure map
	 * @return The matrix of the cells in the treasure map
	 */
	private Cell[][] initTreasureMap(int width, int height) {
		/*
		 * Create a matrix bigger than the actual treasure map. Extra rows (top,
		 * bottom) and columns (left, right) are used as borders and cannot be
		 * accessed.
		 */
		Cell[][] cells = new Cell[width + 2][height + 2];

		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {

				// Real cells
				cells[x][y] = new Land(0);

				// Border cells
				if (x == 0 || x == cells.length - 1 || y == 0 || y == cells[x].length - 1) {
					cells[x][y].setOccupied(true);
				}
			}
		}
		return cells;
	}

	/**
	 * Allows the adventurer to pick up a treasure on their current position
	 * only if the cell has a treasure
	 * 
	 * @param adventurer
	 *            The adventurer to pick the treasure
	 * @param x
	 *            The horizontal position of adventurer
	 * @param y
	 *            The vertical position of the adventurer
	 */
	private void pickUpTreasureIfPossible(Adventurer adventurer, int x, int y) {
		if (this.cells[x][y].hasTreasure()) {
			adventurer.addTreasure(this.cells[x][y].pickUpTreasure());
		}
	}

	/**
	 * Returns the given adventurer's next horizontal position if the they move
	 * forward.
	 * 
	 * @param adventurer
	 *            The adventurer whose next position must be checked
	 * @return The adventurer's next horizontal position
	 * @throws IllegalArgumentException
	 *             If the adventurer's orientation is not supported
	 */
	private int getForwardedX(Adventurer adventurer) {
		switch (adventurer.getOrientation()) {
		case EAST:
			return adventurer.getX() + 1;
		case WEST:
			return adventurer.getX() - 1;
		case NORTH:
		case SOUTH:
			return adventurer.getX();
		default:
			throw new IllegalArgumentException("Orientation " + adventurer.getOrientation() + " is not supported");
		}
	}

	/**
	 * Returns the given adventurer's next vertical position if the they move
	 * forward.
	 * 
	 * @param adventurer
	 *            The adventurer whose next position must be checked
	 * @return The adventurer's next vertical position
	 * @throws IllegalArgumentException
	 *             If the adventurer's orientation is not supported
	 */
	private int getForwardedY(Adventurer adventurer) {
		switch (adventurer.getOrientation()) {
		case SOUTH:
			return adventurer.getY() + 1;
		case NORTH:
			return adventurer.getY() - 1;
		case EAST:
		case WEST:
			return adventurer.getY();
		default:
			throw new IllegalArgumentException("Orientation " + adventurer.getOrientation() + " is not supported");
		}
	}

}

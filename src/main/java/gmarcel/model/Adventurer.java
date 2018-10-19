package gmarcel.model;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * This class represents an adventurer.
 * 
 * An adventurer is able to move across a board with a list of actions and an
 * orientation. When possible, they can pick up treasures from the board cell
 * they are in. Adventurers have a position given by 2D Cartesian coordinates.
 * 
 */
public class Adventurer {

	/** Name of the adventurer. */
	private final String name;

	/** Horizontal position of the adventurer. */
	private int x;

	/** Vertical position of the adventurer. */
	private int y;

	/** Orientation of the adventurer. */
	private Orientation orientation;

	/** Number of treasures picked up by the adventurer. */
	private int nbTreasures;

	/** List of the remaining actions the adventurer has to do. */
	private List<Action> remainingActions;

	/**
	 * Constructs an Adventurer with a given name and orientation. Default
	 * number of treasures is 0.
	 * 
	 * @param name
	 *            The name of the adventurer
	 * @param orientation
	 *            The starting orientation of the adventurer
	 */
	public Adventurer(String name, Orientation orientation) {
		this.name = name;
		this.orientation = orientation;
		this.nbTreasures = 0;
		this.remainingActions = new LinkedList<>();
	}

	/**
	 * Returns the name of the adventurer.
	 * 
	 * @return The name of the adventurer
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the horizontal position of the adventurer.
	 * 
	 * @return The horizontal position of the adventurer
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the vertical position of the adventurer.
	 * 
	 * @return The vertical position of the adventurer
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the current orientation of the adventurer.
	 * 
	 * @return The orientation of the adventurer
	 */
	public Orientation getOrientation() {
		return this.orientation;
	}

	/**
	 * Returns the number of treasures picked by the adventurer.
	 * 
	 * @return The number of treasures picked by the adventurer
	 */
	public int getNbTreasures() {
		return nbTreasures;
	}

	/**
	 * Returns the list of the adventurer's next moves.
	 * 
	 * @return The adventurer's next moves
	 */
	public List<Action> getRemainingActions() {
		return this.remainingActions;
	}

	/**
	 * Adds a new action to the adventurer
	 * 
	 * @param action
	 *            The action to be added
	 */
	public void appendAction(Action action) {
		this.remainingActions.add(action);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adventurer [name=");
		builder.append(name);
		builder.append(", x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", orientation=");
		builder.append(orientation);
		builder.append(", nbTreasures=");
		builder.append(nbTreasures);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Sets the horizontal position of the adventurer. Argument x value
	 * increases from left to right.
	 * 
	 * @param x
	 *            The new horizontal position of the Adventurer
	 */
	// Package visibility: only TreasureMap should access this method.
	void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the vertical position of the adventurer. Argument y value increases
	 * downwards.
	 * 
	 * @param y
	 *            The new vertical position of the adventurer
	 */
	// Package visibility: only TreasureMap should access this method.
	void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns and removes from list the next action to do.
	 * 
	 * @return The next action to do
	 */
	// Package visibility: only TreasureMap should access this method.
	Action popNextAction() {
		if (this.remainingActions.size() != 0) {
			return this.remainingActions.remove(0);
		} else {
			return Action.END;
		}
	}

	/**
	 * Adds given number of treasures to the adventurer's treasures.
	 * 
	 * @param NbTreasures
	 *            Number of treasures to add to the adventurer
	 */
	// Package visibility: only TreasureMap should access this method.
	void addTreasure(int NbTreasures) {
		this.nbTreasures += NbTreasures;
	}

	/**
	 * Turn the adventurer's current position of 90° to the left.
	 */
	// Package visibility: only TreasureMap should access this method.
	void turnLeft() {
		this.orientation = this.orientation.turnLeft();
	}

	/**
	 * Turn the adventurer's current position of 90° to the right.
	 */
	// Package visibility: only TreasureMap should access this method.
	void turnRight() {
		this.orientation = this.orientation.turnRight();
	}

}

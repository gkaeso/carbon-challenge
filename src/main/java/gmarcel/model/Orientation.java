package gmarcel.model;

/**
 * 
 * This enumeration represents the orientations that adventurers may have.
 * 
 * Orientations are typically used by adventurers to choose the direction they
 * move towards.
 * 
 */
public enum Orientation {

	/**
	 * Orientation is towards North.
	 */
	NORTH("N") {

		@Override
		Orientation turnLeft() {
			return WEST;
		}

		@Override
		Orientation turnRight() {
			return EAST;
		}

	},
	/**
	 * Orientation is towards South.
	 */
	SOUTH("S") {

		@Override
		Orientation turnLeft() {
			return EAST;
		}

		@Override
		Orientation turnRight() {
			return WEST;
		}

	},
	/**
	 * Orientation is towards East.
	 */
	EAST("E") {

		@Override
		Orientation turnLeft() {
			return NORTH;
		}

		@Override
		Orientation turnRight() {
			return SOUTH;
		}

	},
	/**
	 * Orientation is towards West.
	 */
	WEST("W") {

		@Override
		Orientation turnLeft() {
			return SOUTH;
		}

		@Override
		Orientation turnRight() {
			return NORTH;
		}

	};

	private final String shortname;

	private Orientation(String shortname) {
		this.shortname = shortname;
	}

	public String getShortname() {
		return this.shortname;
	}

	/**
	 * Returns the new orientation after having turned left.
	 * 
	 * @return The new orientation after having turned left
	 */
	abstract Orientation turnLeft();

	/**
	 * Returns the new orientation after having turned right.
	 * 
	 * @return The new orientation after having turned right
	 */
	abstract Orientation turnRight();

}

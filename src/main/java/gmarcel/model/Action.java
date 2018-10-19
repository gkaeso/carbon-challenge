package gmarcel.model;

/**
 * 
 * This enumeration represents the list of the actions that adventurers may
 * have.
 * 
 * Actions are typically used by adventurers to move on the board.
 * 
 */
public enum Action {

	/**
	 * Action of moving forward.
	 */
	FORWARD,
	/**
	 * Action of turning left.
	 */
	TURN_LEFT,
	/**
	 * Action of turning right.
	 */
	TURN_RIGHT,
	/**
	 * In case no action remains;
	 */
	END;

}

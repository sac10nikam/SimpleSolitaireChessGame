import java.util.ArrayList;

/**
 * @author Ryan Lisnoff, rsl4750@rit.edu
 * @author Zachary Friss zkf5289@rit.edu 
 * 
 * Puzzle - interface for a general puzzle
 *         problem Has the ability to access the start and goal configurations,
 *         can also generate neighbor configs.
 */

//Anything that extends Puzzle must extend it as Puzzle<ConfigType>
//More than likely, neighbor will be an ArrayList<ConfigType>
public abstract class Puzzle<E> {

	protected Integer goal;

	protected E start;

	/**
	 * getGoal gets the end config for the puzzle
	 * 
	 * @return an int which represents the goal config
	 */
	public Integer getGoal() {
		
		return goal;
	}

	/**
	 * Each kind of Puzzle will know a separate way to return its neighbors
	 */
	public abstract ArrayList<E> getNeighbors(E config);

	/**
	 * getStart gets the starting configuration for the puzzle
	 * 
	 * @return an int which represents the start config
	 */
	public E getStart() {
		
		return start;
	}
	
	/**
	 * Compares a given config with the puzzle's personal goal config
	 * @param config - Type E, a puzzle's style of config
	 * @return boolean - true if the config is the same as the goal, or within.
	 */
	public abstract boolean isGoal(E config);
}

/**
 * $Id: Puzzle.java,v 1.1 2013/05/03 00:02:06 p243-06k Exp $
 * 
 * $Log: Puzzle.java,v $
 * Revision 1.1  2013/05/03 00:02:06  p243-06k
 * Push to team account CVS
 *
 * Revision 1.1  2013/05/01 01:57:56  zkf5289
 * Initial Push
 *
 * Revision 1.1  2013/04/18 15:57:48  p243-06k
 * Added to Repository
 *
 * Revision 1.6.2.1  2013/04/17 21:57:12  p243-06k
 * Got Water to work with smaller values, working on potential HashSet solution
 *
 * Revision 1.6  2013/04/16 20:55:56  p243-06k
 * Updated Formating of code and updated descriptions.
 * Revision 1.5 2013/04/16 20:43:06 p243-06k Updated
 * Solver to be more generic.
 * 
 * Revision 1.4 2013/04/12 18:22:26 p243-06k Updated Code to work with having
 * Object cast types.
 * 
 * Revision 1.3 2013/04/12 16:51:30 p243-06k Updated author tags
 * 
 * Revision 1.2 2013/04/12 16:43:38 p243-06k Start of Project 2
 * 
 */

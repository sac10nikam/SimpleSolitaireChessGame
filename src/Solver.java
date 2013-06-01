import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Ryan Lisnoff rsl4750@rit.edu
 * @author Zachary Friss zkf5289@rit.edu 
 * 
 * Solver is a naive BFS Puzzle solver To
 *         be as generic as possible, all puzzles will use the Puzzle interface
 */
public class Solver<E> {
	private Puzzle<E> myPuzzle;
	
	/**
	 * Constructs a solver object
	 * @param thePuzzle any kind of valid Puzzle
	 */
	public Solver(Puzzle<E> thePuzzle)
	{
		myPuzzle = thePuzzle;
	}
	
	/**
	 * @param args command line args, unused.
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub

	}

	/**
	 * solve() utilizes a BFS along with an ArrayList to return the steps needed
	 * to solve a puzzle The ArrayList current lists the current steps taken,
	 * kind of like the dictionary in cs242
	 * 
	 * @param curConf
	 *            is a general puzzle with it's starting config
	 * @return an ArrayList with the steps needed to solve the puzzle
	 */
	public ArrayList<E> solve() {
		/*
		 * 1) Get starting step
		 * 2) Enqueue starting step
		 * 3) Place starting step in visited list
		 * -Loop- while queue not empty
		 * 4) Dequeue as current step
		 * 5) Check if goal step
		 * 6) Add all neighbors to queue
		 * -Loop back-
		 * 7) If found solution, return solution
		 */
		HashMap<E,E> visited = new HashMap<E,E>();
		ArrayList<E> queue = new ArrayList<E>();
		boolean found = false;
		E curStep = myPuzzle.getStart();
		queue.add(curStep);
		visited.put(curStep, null);
		while(!queue.isEmpty())
		{
			curStep = queue.get(0);
			queue.remove(0);
			if(myPuzzle.isGoal(curStep))
			{
				found = true;
				break;
			}
			for(E neighbor : myPuzzle.getNeighbors(curStep))
			{
				if(!visited.containsKey(neighbor))
				{
					visited.put(neighbor, curStep);
					queue.add(neighbor);
				}
			}
			
		}
		if(found)
		{
			ArrayList<E> reverseSteps = new ArrayList<E>();
			ArrayList<E> steps = new ArrayList<E>();
			reverseSteps.add(curStep);
			while(visited.get(curStep) != null)
			{
				reverseSteps.add(visited.get(curStep));
				curStep = visited.get(curStep);
			}
			for(int i = reverseSteps.size()-1; i >= 0; i--)
			{
				steps.add(reverseSteps.get(i));
			}
			return steps;
		}
		return null; //No solution found
	}

}
/**
 * $Id: Solver.java,v 1.1 2013/05/03 00:02:09 p243-06k Exp $
 * 
 * $Log: Solver.java,v $
 * Revision 1.1  2013/05/03 00:02:09  p243-06k
 * Push to team account CVS
 *
 * Revision 1.1  2013/05/01 01:57:56  zkf5289
 * Initial Push
 *
 * Revision 1.1  2013/04/18 15:57:47  p243-06k
 * Added to Repository
 *
 * Revision 1.6.2.3  2013/04/18 14:59:38  p243-06k
 * All comments have been added
 *
 * Revision 1.6.2.2  2013/04/17 23:12:52  p243-06k
 * Water now works with any number of buckets, quantities, etc
 *
 * Goal -> To fully flesh out the comments
 *
 * Revision 1.6.2.1  2013/04/17 21:57:13  p243-06k
 * Got Water to work with smaller values, working on potential HashSet solution
 *
 * Revision 1.6  2013/04/16 20:55:58  p243-06k
 * Updated Formating of code and updated descriptions.
 * Revision 1.5 2013/04/16 20:43:07 p243-06k Updated
 * Solver to be more generic.
 * 
 * Revision 1.4 2013/04/12 18:22:26 p243-06k Updated Code to work with having
 * Object cast types.
 * 
 * Revision 1.3 2013/04/12 16:51:31 p243-06k Updated author tags Revision 1.2
 * 2013/04/12 16:43:38 p243-06k Start of Project 2
 * 
 */

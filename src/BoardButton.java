import javax.swing.JButton;


/*
 * BoardButton.java
 *
 * Version:
 * $Id: BoardButton.java,v 1.1 2013/05/03 00:02:08 p243-06k Exp $
 * Revisions:
 * $Log: BoardButton.java,v $
 * Revision 1.1  2013/05/03 00:02:08  p243-06k
 * Push to team account CVS
 *
 * Revision 1.1  2013/05/02 05:55:40  zkf5289
 * Solver now works and started on GUI
 *
 */

/**
 * @author Friss
 *
 */

public class BoardButton extends JButton {

private Tuple pos;
	
	public BoardButton(Tuple cpos){
		this.pos = cpos;
	}
	
	public Tuple getPos(){
		return pos;
		
	}
	
}

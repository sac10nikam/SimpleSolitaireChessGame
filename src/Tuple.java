
/*
 * Tuple.java
 *
 * Version:
 * $Id: Tuple.java,v 1.1 2013/05/03 00:02:06 p243-06k Exp $
 * Revisions:
 * $Log: Tuple.java,v $
 * Revision 1.1  2013/05/03 00:02:06  p243-06k
 * Push to team account CVS
 *
 * Revision 1.2  2013/05/02 03:47:03  zkf5289
 * Chess Pieces should be finished and get all valid neighbors. Solver is not working correctly.
 *
 * Revision 1.1  2013/05/01 05:05:54  zkf5289
 * Made most of the chess pieces.
 *
 */

/**
 * @author Friss
 *
 */

public class Tuple implements Comparable<Tuple> { 
  public final int x; 
  public final int y; 
 
  public Tuple(int x, int y) { 
    this.x = x; 
    this.y = y; 
  }
/* (non-Javadoc)
 * @see java.lang.Comparable#compareTo(java.lang.Object)
 */
@Override
public int compareTo(Tuple t) {

	if (this.x == t.x && this.y == t.y) {
		
			return 0;
		
	}else{
	return -1;
	}
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {

	final int prime = 31;
	int result = 1;
	result = prime * result + x;
	result = prime * result + y;
	return result;
}
/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {

	if ( this == obj )
		return true;
	if ( obj == null )
		return false;
	if ( getClass() != obj.getClass() )
		return false;
	Tuple other = (Tuple) obj;
	if ( x != other.x )
		return false;
	if ( y != other.y )
		return false;
	return true;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {

	return "( " + Integer.toString( x ) + " , " + Integer.toString( y ) + " )";
} 


} 

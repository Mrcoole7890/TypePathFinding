package ProtoPathing.cole;
//TODO look into the SERIALIZIBLE LONG ID warning I am getting...
/**
* 
* @author Cole A. Kendall
*
*/
public class CannotMovePlayerToCoordinateException extends Exception {

	public CannotMovePlayerToCoordinateException(int[] cords) {
		super("cannot move player to the cordinate (" 
				+ cords[0] + ", " + cords[1] + ")." );
	}
}

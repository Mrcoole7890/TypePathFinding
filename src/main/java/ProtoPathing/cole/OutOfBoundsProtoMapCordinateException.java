package ProtoPathing.cole;
//TODO look into the SERIALIZIBLE LONG ID warning I am getting...
/**
* 
* @author Cole A. Kendall
*
*/
public class OutOfBoundsProtoMapCordinateException extends Exception {

	public OutOfBoundsProtoMapCordinateException(int[] cords, int[] mapHeightLength) {
		super("Exception: coordiantes given are (" 
				+ cords[0] + ", " + cords[1] 
				+ ")[zero's-index]; however, there are " 
				+ mapHeightLength[0] + " rows and " + mapHeightLength[1] 
				+ " columns[one's-index].");
	}
}

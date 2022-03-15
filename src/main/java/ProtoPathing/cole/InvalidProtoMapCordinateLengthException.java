package ProtoPathing.cole;
//TODO look into the SERIALIZIBLE LONG ID warning I am getting...
/**
* 
* @author Cole A. Kendall
*
*/
public class InvalidProtoMapCordinateLengthException extends Exception {

	public InvalidProtoMapCordinateLengthException(int[] cord) {
		super("Exception: coordinates array length given is " + cord.length + " but should be 2");
	}
}

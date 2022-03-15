package ProtoPathing.cole;
//TODO look into the SERIALIZIBLE LONG ID warning I am getting...
/**
* 
* @author Cole A. Kendall
*
*/
public class ProtoMapGoalNotFoundException extends Exception {

	public ProtoMapGoalNotFoundException(ProtoMap mapWithoutGoalFlag) {
		super("Exception: Goal flag not found in map ->\n" + mapWithoutGoalFlag.toString());
	}
}

package ProtoPathing.cole;
// TODO look into the SERIALIZIBLE LONG ID warning I am getting...
/**
 * 
 * @author Cole A. Kendall
 *
 */
public class ProtoMapPlayerNotFoundException extends Exception {
	
	public ProtoMapPlayerNotFoundException(ProtoMap mapWithoutPlayerFlag) {
		super("Exception: Player flag not found in map ->\n" + mapWithoutPlayerFlag.toString());
	}
}

/*
 * TODO add testing for setGoalAndPlayer()
 * TODO add testing for setGoalAndPlayer()
 * TODO add testing for getValueAt(int[] cord)
 * TODO add testing for changeValueAt(int[] cord, MapFlags flag)
 * TODO add testing for	ProtoMap movePTo(int[] cord)
 * TODO add testing for moveDown()
 * TODO add testing for moveRight()
 * TODO add testing for moveLeft()
 * TODO add testing for moveUp()
 * TODO add testing for getPlayerPosition()
 * TODO add testing for getGoalPosition()
 * TODO add testing for toString()
 * TODO add testing for equals(ProtoMap other)
 */

package ProtoPathing.cole;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Cole A. Kendall
 *
 */
public class ProtoMapTest {
	
	/**
	 * Create an ArrayList of strings
	 * write it to a given file
	 * initialize the ProtoMap with file path parameters in the constructor
	 * assert no errors
	 */
	@Test
	public void testInitializeProtoMapWithOneFilePathParameters() {
		//Create the ArrayList Of Strings
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("XPXXXXXXXXX");
		testMap.add("X    X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//Write it to a given file
		
		String pathToTestFile = "src/test/resources/TestMap.txt";
		
	    try {
	        File myObj = new File(pathToTestFile);
	        myObj.createNewFile();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } //Stolen from w3c Schools
	    
	    try {
	        FileWriter myWriter = new FileWriter(pathToTestFile);
	        for(String s: testMap) myWriter.write(s + "\n");
	        myWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } //Stolen from w3c Schools
	    
	    // initialize the ProtoMap with file path parameters in the constructor
	    ProtoMap mapToTest = new ProtoMap(pathToTestFile);
	    
	    // assert no errors
	    
	    String mapToTestAsString = mapToTest.toString();
	    
	    String expected = "";
	    for(String s: testMap) expected += s + "\n";
	    
	    assertEquals(mapToTestAsString, expected);
	}
	
	
	/**
	 * Create an ArrayList of strings
	 * initialize the ProtoMap with one ArrayList of strings parameter in the constructor
	 * assert no errors
	 */
	@Test
	public void testInitializeProtoMapWithOneArrayListParameter() {
		// Create an ArrayList of strings
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("XPXXXXXXXXX");
		testMap.add("X    X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		// initialize the ProtoMap with one ArrayList of strings parameter in the constructor
		ProtoMap mapToTest = new ProtoMap(testMap);
		
		// assert no errors
		String mapToTestAsString = mapToTest.toString();
		    
		String expected = "";
		for(String s: testMap) expected += s + "\n";
		    
		assertEquals(mapToTestAsString, expected);
	}

}

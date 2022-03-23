/*
 * DONE add testing for setGoalAndPlayer()
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

import ProtoPathing.cole.ProtoMap.MapFlags;

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
	
	/**
	 * create an ArrayList of strings where the player AND goal flag are present
	 * initialize the ProtoMap with one ArrayList of strings parameter in the constructor
	 * call the setGoalAndPlayer method
	 * assert the goal and player flags are in the right place
	 */
	@Test
	public void testSetGoalAndPlayerWithBothPlayerAndGoalPresent() {
		
		//create an ArrayList of strings where the player AND goal flag are present
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X"+ MapFlags.PLAYER_FLAG.getValue() +"XXXXXXXXX");
		testMap.add("X    X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXX"+ MapFlags.GOAL_FLAG.getValue() +"XXXXXXX");
		
		//initialize the ProtoMap with one ArrayList of strings parameter in the constructor
		ProtoMap mapToTest = new ProtoMap(testMap);
		
		//call the setGoalAndPlayer method
		try {
			mapToTest.setGoalAndPlayer();
		} catch (ProtoMapPlayerNotFoundException e) {
			fail("Player flag not found where one dose exist");
		} catch (ProtoMapGoalNotFoundException e) {
			fail("Goal flag not found where one dose exist");
		}
		
		//assert the goal and player flags are in the right place
		int[] cordsOfPlayer = new int[2];
		cordsOfPlayer[0] = 0;
		cordsOfPlayer[1] = 1;
		
		int[] cordsOfGoal = new int[2];
		cordsOfGoal[0] = 10;
		cordsOfGoal[1] = 3;
		
		assertEquals(mapToTest.getPlayerPosition()[0], cordsOfPlayer[0]);
		assertEquals(mapToTest.getPlayerPosition()[1], cordsOfPlayer[1]);
		assertEquals(mapToTest.getGoalPosition()[0], cordsOfGoal[0]);
		assertEquals(mapToTest.getGoalPosition()[1], cordsOfGoal[1]);
	}
	
	/**
	 * create an ArrayList of strings where the player flag is not present but the goal flag is
	 * initialize the ProtoMap with one ArrayList of strings parameter in the constructor
	 * call the setGoalAndPlayer method
	 * assert the ProtoMapPlayerNotFoundException exception is thrown
	 */	
	@Test
	public void testSetGoalAndPlayerWithPlayerNotPresentButGoalIs() {
		
		//create an ArrayList of strings where the player flag is not present but the goal flag is
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X    X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXX"+ MapFlags.GOAL_FLAG.getValue() +"XXXXXXX");
		
		//initialize the ProtoMap with one ArrayList of strings parameter in the constructor
		ProtoMap mapToTest = new ProtoMap(testMap);
		
		//call the setGoalAndPlayer method
		try {
			mapToTest.setGoalAndPlayer();
		} catch (ProtoMapPlayerNotFoundException e) {
			return;
		} catch (ProtoMapGoalNotFoundException e) {
			fail("Goal flag not found where one dose exist");
		}
		
		fail("No exception was thrown where ProtoMapPlayerNotFoundException was expected to be thrown");
	}
	
	/**
	 * create an ArrayList of strings where the goal flag is not present but the player flag is
	 * initialize the ProtoMap with one ArrayList of strings parameter in the constructor
	 * call the setGoalAndPlayer method
	 * assert the ProtoMapGoalNotFoundException exception is thrown
	 */	
	@Test
	public void testSetGoalAndPlayerWithGoalNotPresentButPlayerIs() {
		
		//create an ArrayList of strings where the goal flag is not present but the player flag is
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X"+ MapFlags.PLAYER_FLAG.getValue() +"XXXXXXXXX");
		testMap.add("X    X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXX XXXXXXX");
		
		//initialize the ProtoMap with one ArrayList of strings parameter in the constructor
		ProtoMap mapToTest = new ProtoMap(testMap);
		
		//call the setGoalAndPlayer method
		try {
			mapToTest.setGoalAndPlayer();
		} catch (ProtoMapPlayerNotFoundException e) {
			fail("Player flag not found where one dose exist");
		} catch (ProtoMapGoalNotFoundException e) {
			return;
		}
		
		fail("No exception was thrown where ProtoMapGoalNotFoundException was expected to be thrown");
	}
	

}

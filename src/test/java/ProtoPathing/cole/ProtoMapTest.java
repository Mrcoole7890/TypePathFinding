/*
 * DONE add testing for setGoalAndPlayer()
 * DONE add testing for moveDown()
 * DONE add testing for moveRight()
 * DONE add testing for moveLeft()
 * DONE add testing for moveUp()
 * DONE add testing for getPlayerPosition()
 * TODO add testing for getGoalPosition()
 * TODO add testing for toString()
 * TODO add testing for equals(ProtoMap other)
 * TODO in all places where a ArrayList of strings is manually created
 *     and is passed into a ProtoMap constructor use a function call to handle the map creation
 * TODO go through and check Java Docs for inconsistencies within the moveLeft, moveRight, moveUp, and moveDown
 *     test cases (todo creation date 3/25/2022)
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
	
	/**
	 * create an ArrayList of strings where the player flag is above an open space
	 * initialize the ProtoMap
	 * create an expected ArrayList of Strings after the player is moved down
	 * call the moveDown() method and the expected new ProtoMap is present
	 */
	@Test
	public void testMoveDownWhereAnOpenSpaceIsBelow() {
	
		//create an ArrayList of strings where the player flag is above an open space
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
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				mapToTest = mapToTest.moveDown();
			} catch (CannotMovePlayerToCoordinateException e) {
				fail(e.getMessage());
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		

		
		//create an expected ArrayList of Strings after the player is moved down
		ArrayList<String> testMapExpected = new ArrayList<String>();
		testMapExpected.add("X XXXXXXXXX");
		testMapExpected.add("X"+MapFlags.PLAYER_FLAG.getValue()+"   X   XX");
		testMapExpected.add("X X X  X XX");
		testMapExpected.add("X X   XXXXX");
		testMapExpected.add("X XXXX   XX");
		testMapExpected.add("X      X XX");
		testMapExpected.add("XXXXXXXX XX");
		testMapExpected.add("XX       XX");
		testMapExpected.add("XXXXXX X XX");
		testMapExpected.add("XXX    XXXX");
		testMapExpected.add("XXXGXXXXXXX");
		
		//initializes expected ProtoMap
		ProtoMap mapTobeExpected = new ProtoMap(testMapExpected);
		
		assertEquals(mapToTest.toString(), mapTobeExpected.toString());
	}
	
	/**
	 * create an ArrayList of strings where the player flag is not above an open space
	 * initialize the ProtoMap
	 * create an expected ArrayList of Strings after the player is moved down
	 * call the moveDown() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveDownWhereNoOpenSpaceIsBelow() {
	
		//create an ArrayList of strings where the player flag is above an open space
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X "+MapFlags.PLAYER_FLAG.getValue()+"  X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				mapToTest = mapToTest.moveDown();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is above the out of bounds for the 2-d array
	 * initialize the ProtoMap
	 * create an expected ArrayList of Strings after the player is moved down
	 * call the moveDown() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveDownWhere2DArrayOutOfBoundsIsBelow() {
	
		//create an ArrayList of strings where the player flag is above an open space
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
		testMap.add("XXXGX"+ MapFlags.PLAYER_FLAG.getValue() +"XXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				mapToTest = mapToTest.moveDown();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is below an open space
	 * initialize the ProtoMap
	 * create an expected ArrayList of Strings after the player is moved up
	 * call the moveUp() method and the expected new ProtoMap is present
	 */
	@Test
	public void testMoveUpWhereAnOpenSpaceIsAbove() {
	
		//create an ArrayList of strings where the player flag is below an open space
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X"+ MapFlags.PLAYER_FLAG.getValue() +"   X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				mapToTest = mapToTest.moveUp();
			} catch (CannotMovePlayerToCoordinateException e) {
				fail(e.getMessage());
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		

		
		// create an expected ArrayList of Strings after the player is moved up
		ArrayList<String> testMapExpected = new ArrayList<String>();
		testMapExpected.add("X"+MapFlags.PLAYER_FLAG.getValue()+"XXXXXXXXX");
		testMapExpected.add("X    X   XX");
		testMapExpected.add("X X X  X XX");
		testMapExpected.add("X X   XXXXX");
		testMapExpected.add("X XXXX   XX");
		testMapExpected.add("X      X XX");
		testMapExpected.add("XXXXXXXX XX");
		testMapExpected.add("XX       XX");
		testMapExpected.add("XXXXXX X XX");
		testMapExpected.add("XXX    XXXX");
		testMapExpected.add("XXXGXXXXXXX");
		
		//initializes expected ProtoMap
		ProtoMap mapTobeExpected = new ProtoMap(testMapExpected);
		
		assertEquals(mapToTest.toString(), mapTobeExpected.toString());
	}
	
	/**
	 * create an ArrayList of strings where the player flag is not below an open space
	 * initialize the ProtoMap
	 * call the moveUp() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveUpWhereNoOpenSpaceIsAbove() {
	
		//create an ArrayList of strings where the player flag is above an open space
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X "+MapFlags.PLAYER_FLAG.getValue()+"  X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				// call the moveUp() method and the expected exception is to be thrown
				mapToTest = mapToTest.moveUp();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is below the out of bounds for the 2-d array
	 * initialize the ProtoMap
	 * call the moveUp() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveUpWhere2DArrayOutOfBoundsIsAbove() {
	
		//create an ArrayList of strings where the player flag is below the out of bounds for the 2-d array
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
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				//call the moveUp() method and the expected exception is to be thrown
				mapToTest = mapToTest.moveUp();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is right of an open space
	 * initialize the ProtoMap
	 * create an expected ArrayList of Strings after the player is moved left
	 * call the moveLeft() method and the expected new ProtoMap is present
	 */
	@Test
	public void testMoveLeftWhereAnOpenSpaceIsToTheLeft() {
	
		//create an ArrayList of strings where the player flag is right of an open space
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X "+ MapFlags.PLAYER_FLAG.getValue() +"  X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				mapToTest = mapToTest.moveLeft();
			} catch (CannotMovePlayerToCoordinateException e) {
				fail(e.getMessage());
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		

		
		// create an expected ArrayList of Strings after the player is moved left
		ArrayList<String> testMapExpected = new ArrayList<String>();
		testMapExpected.add("X XXXXXXXXX");
		testMapExpected.add("X"+ MapFlags.PLAYER_FLAG.getValue() +"   X   XX");
		testMapExpected.add("X X X  X XX");
		testMapExpected.add("X X   XXXXX");
		testMapExpected.add("X XXXX   XX");
		testMapExpected.add("X      X XX");
		testMapExpected.add("XXXXXXXX XX");
		testMapExpected.add("XX       XX");
		testMapExpected.add("XXXXXX X XX");
		testMapExpected.add("XXX    XXXX");
		testMapExpected.add("XXXGXXXXXXX");
		
		//initializes expected ProtoMap
		ProtoMap mapTobeExpected = new ProtoMap(testMapExpected);
		
		assertEquals(mapToTest.toString(), mapTobeExpected.toString());
	}
	
	/**
	 * create an ArrayList of strings where the player flag is not right of an open space
	 * initialize the ProtoMap
	 * call the moveLeft() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveLeftWhereNoOpenSpaceIsOnTheLeft() {
	
		//create an ArrayList of strings where the player flag is above an open space
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X"+MapFlags.PLAYER_FLAG.getValue()+"   X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				// call the moveLeft() method and the expected exception is to be thrown
				mapToTest = mapToTest.moveLeft();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is right of the out of bounds for the 2-d array
	 * initialize the ProtoMap
	 * call the moveLeft() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveLeftWhere2DArrayOutOfBoundsIsOnTheLeft() {
	
		//create an ArrayList of strings where the player flag is right of the out of bounds for the 2-d array
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add(""+ MapFlags.PLAYER_FLAG.getValue() +"XXXXXXXXXX");
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
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				//call the moveLeft() method and the expected exception is to be thrown
				mapToTest = mapToTest.moveLeft();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is left of an open space
	 * initialize the ProtoMap
	 * create an expected ArrayList of Strings after the player is moved right
	 * call the moveLeft() method and the expected new ProtoMap is present
	 */
	@Test
	public void testMoveRightWhereAnOpenSpaceIsToTheRight() {
	
		//create an ArrayList of strings where the player flag is left of an open space
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X "+ MapFlags.PLAYER_FLAG.getValue() +"  X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				mapToTest = mapToTest.moveRight();
			} catch (CannotMovePlayerToCoordinateException e) {
				fail(e.getMessage());
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		

		
		// create an expected ArrayList of Strings after the player is moved right
		ArrayList<String> testMapExpected = new ArrayList<String>();
		testMapExpected.add("X XXXXXXXXX");
		testMapExpected.add("X  "+ MapFlags.PLAYER_FLAG.getValue() +" X   XX");
		testMapExpected.add("X X X  X XX");
		testMapExpected.add("X X   XXXXX");
		testMapExpected.add("X XXXX   XX");
		testMapExpected.add("X      X XX");
		testMapExpected.add("XXXXXXXX XX");
		testMapExpected.add("XX       XX");
		testMapExpected.add("XXXXXX X XX");
		testMapExpected.add("XXX    XXXX");
		testMapExpected.add("XXXGXXXXXXX");
		
		//initializes expected ProtoMap
		ProtoMap mapTobeExpected = new ProtoMap(testMapExpected);
		
		assertEquals(mapToTest.toString(), mapTobeExpected.toString());
	}
	
	/**
	 * create an ArrayList of strings where the player flag is not left of an open space
	 * initialize the ProtoMap
	 * call the moveRight() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveRightWhereNoOpenSpaceIsOnTheRight() {
	
		//create an ArrayList of strings where the player flag is not left of an open space
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X   "+MapFlags.PLAYER_FLAG.getValue()+"X   XX");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				// call the moveRight() method and the expected exception is to be thrown
				mapToTest = mapToTest.moveRight();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is left of the out of bounds for the 2-d array
	 * initialize the ProtoMap
	 * call the moveRight() method and the expected exception is to be thrown 
	 */
	@Test
	public void testMoveRightWhere2DArrayOutOfBoundsIsOnTheRight() {
	
		//create an ArrayList of strings where the player flag is left of the out of bounds for the 2-d array
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X   X   XX"+MapFlags.PLAYER_FLAG.getValue()+"");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		try {
			mapToTest.setGoalAndPlayer();
			try {
				//call the moveRight() method and the expected exception is to be thrown
				mapToTest = mapToTest.moveRight();
			} catch (CannotMovePlayerToCoordinateException e) {
				return;
			}
		} catch (ProtoMapPlayerNotFoundException e1) {
			fail(e1.getMessage());
		} catch (ProtoMapGoalNotFoundException e1) {
			fail(e1.getMessage());
		}
		
		fail("CannotMovePlayerToCoordinateException was not thrown where one should have been thrown");
	}
	
	/**
	 * create an ArrayList of strings where the player flag is present
	 * initialize the ProtoMap
	 * call getPlayerPosition()
	 * assert the location is correct
	 */
	@Test
	public void testGetPlayerPositionWithMapThatHasPlayerFlag() {
		
		//create an ArrayList of strings where the player flag is present
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X   X   XX"+MapFlags.PLAYER_FLAG.getValue()+""); // player flag is at 1,11
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX");
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		
		try {
			mapToTest.setGoalAndPlayer();
		} catch (ProtoMapPlayerNotFoundException e) {
			fail("ProtoMapPlayerNotFoundException thrown where it should not have. Check the mock data.");
		} catch (ProtoMapGoalNotFoundException e) {
		}
		
		int[] cords = mapToTest.getPlayerPosition();
		
		assertEquals(cords[0], 1);
		assertEquals(cords[1], 10);
	}
	
	/**
	 * create an ArrayList of strings where the goal flag is present
	 * initialize the ProtoMap
	 * call getGoalPosition()
	 * assert the location is correct
	 */
	@Test
	public void testGetGoalPositionWithMapThatHasGoalFlag() {
		
		//create an ArrayList of strings where the goal flag is present
		ArrayList<String> testMap = new ArrayList<String>();
		testMap.add("X XXXXXXXXX");
		testMap.add("X   X   XX"+MapFlags.PLAYER_FLAG.getValue()+"");
		testMap.add("X X X  X XX");
		testMap.add("X X   XXXXX");
		testMap.add("X XXXX   XX");
		testMap.add("X      X XX");
		testMap.add("XXXXXXXX XX");
		testMap.add("XX       XX");
		testMap.add("XXXXXX X XX");
		testMap.add("XXX    XXXX");
		testMap.add("XXXGXXXXXXX"); // goal flag is at 10,3
		
		//initialize the ProtoMap
		ProtoMap mapToTest = new ProtoMap(testMap);
		
		try {
			mapToTest.setGoalAndPlayer();
		} catch (ProtoMapPlayerNotFoundException e) {

		} catch (ProtoMapGoalNotFoundException e) {
			fail("ProtoMapGoalNotFoundException thrown where it should not have. Check the mock data.");
		}
		
		int[] cords = mapToTest.getGoalPosition();
		
		assertEquals(cords[0], 10);
		assertEquals(cords[1], 3);
	}
}

/*
 * DONE comment expected functionality 
 * TODO test each function
 * DONE add exception to be thrown by the setGoalAndPlayer() method where the player cannot be found
 * DONE add exception to be thrown by the setGoalAndPlayer() method where the goal cannot be found
 * DONE change the value parameter in the changeValueAt to an enumerable rather than a char
 * DONE add exception to be thrown by the getValueAt() method where a Invalid coordinate is thrown
 * DONE add exception to be thrown by the movePTo() method where the given position is not empty or a goal flag
 * TODO for moveUp(), moveDown(), moveLeft(), and moveRight() determine if a throw is necessary
 * TODO add exception to be thrown by the changeValueAt() method where a Invalid coordinate is thrown
 */

package ProtoPathing.cole;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Cole A. Kendall
 *
 */
public class ProtoMap {

	private final String pathToDefaultFile = "src/main/resources/TestMap"; 
	
	private enum MapFlags{
		PLAYER_FLAG('P'), GOAL_FLAG('G'), EMPTY_SPACE(' ');
		
		private MapFlags(char value) {
			this.value = value;
		}
		
		private char value;
		
		public char getValue() {
			return this.value;
		}
	}
	
	private ArrayList<String> map; // array of strings that represents the rows of a 2-d rectangular map
	private int[] playerPosition; // 2-d array that indicates the cords of the player flag
	private int[] goalPosition; // 2-d array that indicates the cords of the goal flag
	public boolean goalFound; // this should be a false if a goal flag exists
	
	/**
	 *  When initialized a default file will be attempted to be retrieved from the default file location
	 *  if the retrieval fails an exception is to be handled and the program should crash
	 *  otherwise the file will be read and, per line, broken into substrings
	 *  each substring will be pushed on the map ArrayList
	 *  finally the playerPosition and goalPosition is to be located and set
	 *  if a player flag cannot be found an exception is to be handled
	 *  if a goal flag cannot be found an exception is to be handled  
	 *  
	 *  @return instance of a ProtoMap
	 */
	public ProtoMap() {
		super();
		map = new ArrayList<String>();
		File mapFile = new File(pathToDefaultFile);
		try {
			Scanner mapReader = new Scanner(mapFile);
			while(mapReader.hasNext()) {
				String line = mapReader.nextLine();
				map.add(line);
			}
			mapReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try { this.setGoalAndPlayer(); }
		catch (ProtoMapPlayerNotFoundException e) { e.printStackTrace(); }
	    catch (ProtoMapGoalNotFoundException e) { this.goalFound = true; }
	}
	
	/**
	 *  @param ArrayList of strings that represents the map
	 *  
	 *  for each string, in the initMap parameter, it will be pushed on the map ArrayList
	 *  the playerPosition and goalPosition is to be located and set
	 *  if a player flag cannot be found an exception is to be handled
	 *  if a goal flag cannot be found an exception is to be handled  
	 *  
	 *  @return instance of a ProtoMap
	 */
	public ProtoMap(ArrayList<String> initMap) {
		map = new ArrayList<String>();
		for(String s: initMap) map.add(s);
		
		try { this.setGoalAndPlayer(); }
		catch (ProtoMapPlayerNotFoundException e) { e.printStackTrace(); }
	    catch (ProtoMapGoalNotFoundException e) { this.goalFound = true; }
	}
	
	/**
	 * when called the method gets the map ArrayList
	 * each string in the ArrayList is traversed to find the player and goal flag
	 * 
	 * if no player is found, then an exception should be thrown
	 * if no goal is found, then an exception should be thrown
	 * 
	 * otherwise the cords of the player and goal are assigned to
	 * the playerPosition and goalPosition fields
	 * 
	 * the first cord is the row while the char in the row is the column 
	 * 
	 * @throws ProtoMapPlayerNotFoundException
	 * @throws ProtoMapGoalNotFoundException
	 */
	private void setGoalAndPlayer() throws ProtoMapPlayerNotFoundException, ProtoMapGoalNotFoundException {
		
		if (map == null) return;
		
		for(int i = 0; i < this.map.size(); i++) {
			for(int j = 0; j < this.map.get(i).length(); j++) {
				int[] temp = {i,j};
				if(this.map.get(i).charAt(j) == MapFlags.PLAYER_FLAG.getValue()) this.playerPosition = temp;
				else if(this.map.get(i).charAt(j) == MapFlags.GOAL_FLAG.getValue()) this.goalPosition = temp;
			}
		}
		if(this.goalPosition == null) throw new ProtoMapGoalNotFoundException(this);
		if(this.playerPosition == null) throw new ProtoMapPlayerNotFoundException(this);
	}
	
	/**
	 * 
	 * @param cord is a 2-d array that described the position of the desired value to identify
	 * 
	 * Takes the 2-d coordinate array and if the coordinate is valid returns the value
	 * if the coordinate is invalid an exception is thrown
	 * 
	 * @return char value at the described location
	 * 
	 * @throws InvalidProtoMapCordinateLengthException
	 * @throws OutOfBoundsProtoMapCordinateException
	 */
	private char getValueAt(int[] cord) throws InvalidProtoMapCordinateLengthException, OutOfBoundsProtoMapCordinateException {
		if (cord.length != 2) throw new InvalidProtoMapCordinateLengthException(cord);
		int[] mapHeightWidth =  {this.map.size(), this.map.get(0).length()};
		if (cord[0] < map.size() 
				&& cord[0] >= 0
				&& cord[1] < map.get(cord[0]).length()
				&& cord[1] >= 0) return map.get(cord[0]).charAt(cord[1]);
		else throw new OutOfBoundsProtoMapCordinateException(cord, mapHeightWidth);
	}
	
	/**
	 * 
	 * @param cord is a 2-d array that described the position of the desired value to be changed
	 * @param value is a char that the location in the map is to be set to
	 * 
	 * the coordinate that described the position of the char to be changed is set to the value parameter
	 * if the cord is invalid then the ProtoMap's initial state is to be returned unchanged
	 * 
	 * @return a new ProtoMap instance, based on the cord and value, of the desired location to be set to a given char 
	 */
	private ProtoMap changeValueAt(int[] cord, MapFlags flag) {
		ArrayList<String> tempMap = new ArrayList<String>();
		
		for(String s: this.map)
			tempMap.add(s);
		
		char[] rowToChars = tempMap.get(cord[0]).toCharArray();
		rowToChars[cord[1]] = flag.getValue();
		tempMap.set(cord[0], String.valueOf(rowToChars));
		
		return new ProtoMap(tempMap);
	}
	
	/**
	 * 
	 * @param cord is the coordinate of the location the player flag is to be moved to
	 * 
	 * a new ProtoMap, tempMap, instance is made out of the instance of this instance
	 * 
	 * if the value at the given coordinate is an empty space or a goal flag
	 *  a new instance is created with the given coordinate set to the player flag
	 *  the new instance is assigned to tempMap
	 *  a new instance is created with the initial player's location set to an empty space
	 *  the new instance is assigned to tempMap 
	 *  
	 * if the value at the given coordinate is not an empty space or a goal flag
	 *  throw a CannotMovePlayerToCoordinateException
	 * 
	 * @return a new protoMap with the player flag located at the given coordinate
	 * @throws CannotMovePlayerToCoordinateException
	 */
	private ProtoMap movePTo(int[] cord) throws CannotMovePlayerToCoordinateException{
		
		ProtoMap tempMap = new ProtoMap(this.map);
		
		try {
			if (tempMap.getValueAt(cord) != MapFlags.EMPTY_SPACE.getValue() && tempMap.getValueAt(cord) != MapFlags.GOAL_FLAG.getValue()) 
				throw new CannotMovePlayerToCoordinateException(cord);
			else {
				tempMap = tempMap.changeValueAt(tempMap.getPlayerPosition(), MapFlags.EMPTY_SPACE);
				tempMap = tempMap.changeValueAt(cord, MapFlags.PLAYER_FLAG);
				System.out.println(tempMap.toString());
				
			}
		} catch (InvalidProtoMapCordinateLengthException e) {
			e.printStackTrace();
		} catch (OutOfBoundsProtoMapCordinateException e) {
			throw new CannotMovePlayerToCoordinateException(cord);
		}
		
		return tempMap;
	}
	/**
	 * Attempts to move the player flag down one row in the map ArrayList
	 * 
	 * if the value at the row below is not a valid location
	 * 	throw a CannotMovePlayerToCoordinateException
	 * 
	 * @return new ProtoMap with the player flag one row down
	 * @throws CannotMovePlayerToCoordinateException
	 */
	public ProtoMap moveDown() throws CannotMovePlayerToCoordinateException{
		int[] currPos = this.getPlayerPosition();
		currPos[0] = currPos[0] + 1;
		return this.movePTo(currPos);
	}
	/**
	 * Attempts to move the player flag right one column in the map ArrayList
	 * 
	 * if the value in the right column is not a valid location
	 * 	throw a CannotMovePlayerToCoordinateException
	 * 
	 * @return new ProtoMap with the player flag one to the right
	 * @throws CannotMovePlayerToCoordinateException
	 */
	public ProtoMap moveRight() throws CannotMovePlayerToCoordinateException {
		int[] currPos = this.getPlayerPosition();
		currPos[1] = currPos[1] + 1;
		return this.movePTo(currPos);
	}
	/**
	 * Attempts to move the player flag left one column in the map ArrayList
	 * 
	 * if the value in the left column is not a valid location
	 * 	throw a CannotMovePlayerToCoordinateException
	 * 
	 * @return new ProtoMap with the player flag one to the left
	 * @throws CannotMovePlayerToCoordinateException
	 */
	public ProtoMap moveLeft() throws CannotMovePlayerToCoordinateException {
		int[] currPos = this.getPlayerPosition();
		currPos[1] = currPos[1] - 1;
		return this.movePTo(currPos);
	}
	/**
	 * Attempts to move the player flag up one row in the map ArrayList
	 * 
	 * if the value at the row above is not a valid location
	 * 	throw a CannotMovePlayerToCoordinateException
	 * 
	 * @return new ProtoMap with the player flag one row up
	 * @throws CannotMovePlayerToCoordinateException
	 */
	public ProtoMap moveUp() throws CannotMovePlayerToCoordinateException {
		int[] currPos = this.getPlayerPosition();
		currPos[0] = currPos[0] - 1;
		return this.movePTo(currPos);
	}
	/**
	 * getter for the playerPosition
	 * 
	 * @return 2-d array describing the location of the player flag in the map ArrayList
	 */
	public int[] getPlayerPosition() {
		return this.playerPosition;
	}
	 /**
	  * getter for the goalPosition
	  * 
	  * @return 2-d array describing the location of the goal flag in the map ArrayList
	  */
	public int[] getGoalPosition() {
		return this.goalPosition;
	}

	@Override
	public String toString() {
		String finalMap = "";
		for(String s: this.map) finalMap += s + "\n";
		return finalMap;
	}
	
	/**
	 * 	
	 * @param other is another ProtoMap object that is to be compared with this instance
	 * 
	 * if the player x and y coordinates are the same
	 *  return true
	 *  
	 * otherwise return false
	 * 
	 * @return boolean representing if the other ProtoMap is the same as the other ProtoMap
	 */
	public boolean equals(ProtoMap other) {
		System.out.println(this.getPlayerPosition()[0] + " : " + other.getPlayerPosition()[0]);
		System.out.println(this.getPlayerPosition()[1] + " : " + other.getPlayerPosition()[1]);
		if (this.getPlayerPosition()[0] == other.getPlayerPosition()[0]
				&& this.getPlayerPosition()[1] == other.getPlayerPosition()[1])
			return true;
		else return false;
	}
	
}

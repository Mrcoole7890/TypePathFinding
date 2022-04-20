package ProtoPathing.cole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

import ProtoPathing.cole.MyList.ListType;

public class ProtoSearchAgentAStar {
	private enum Directions{
		LEFT,RIGHT,UP,DOWN
	};

	private RankedState gameState;
	
	private PriorityQueue<RankedState> newSearchList;
	private HashMap<RankedState,RankedState> cameFrom;
	private HashMap<RankedState,Integer> gScore;
	private HashMap<RankedState,Integer> fScore;
	
	/**
	 * This object is effectively a copy of the ProtoSearchAgent class
	 */
	public ProtoSearchAgentAStar() {
		super();
		
		gameState = new RankedState(new ProtoMap());
		gameState.setRank(gameState.hCost());
		
		newSearchList = new PriorityQueue<RankedState>();
		gScore = new HashMap<RankedState,Integer>();
		fScore = new HashMap<RankedState,Integer>();
		cameFrom = new HashMap<RankedState,RankedState>();
		
		newSearchList.add(gameState);
		
		gScore.put(gameState, 0);
		fScore.put(gameState , gameState.hCost());
		
		while(!newSearchList.isEmpty()) {
			gameState = newSearchList.peek();
			if(gameState.state.goalFound) {
				System.out.println("LETS GOOO!"); 
				for(RankedState ls: getPathToNode(gameState)) System.out.println(ls.state);
				return;
			}
			
			newSearchList.poll();
			
			for(RankedState rs: generateNewStates()) {
				Integer tenativeGScore = gScore.get(gameState) + 1;
				
				if(tenativeGScore < gScore.getOrDefault(rs, Integer.MAX_VALUE)) {
					cameFrom.put(rs, gameState);
					gScore.put(rs, tenativeGScore);
					fScore.put(rs, tenativeGScore + rs.hCost());
					
					rs.setRank(tenativeGScore + rs.hCost());
					
					if(!newSearchList.contains(rs)) {
						newSearchList.add(rs);
						System.out.print(rs.state);
					}else {
						
					}
				}
			}
		}
	}
	
	private ArrayList<RankedState> getPathToNode (RankedState node) {
		ArrayList<RankedState> finalList = new ArrayList<RankedState>();
		finalList.add(node);
		RankedState curr = node;
		while(cameFrom.containsKey(curr)) {
			curr = cameFrom.get(curr);
			finalList.add(0,curr);
		}
		
		return finalList;
	}
	
	
	private ArrayList<RankedState> generateNewStates() {
		ArrayList<RankedState> returnList = new ArrayList<RankedState>();
		RankedState temp = this.gen(Directions.UP);
		if (temp != null) returnList.add(temp);
		temp = this.gen(Directions.DOWN);
		if (temp != null) returnList.add(temp);
		temp = this.gen(Directions.LEFT);
		if (temp != null) returnList.add(temp);
		temp = this.gen(Directions.RIGHT);
		if (temp != null) returnList.add(temp);
		return returnList;
	}
	
	
	private RankedState gen(Directions direction) {
		try {
			RankedState temp;
			switch(direction) {
			case UP: temp = new RankedState(this.gameState.state.moveUp()); break;
			case DOWN: temp = new RankedState(this.gameState.state.moveDown()); break;
			case LEFT: temp = new RankedState(this.gameState.state.moveLeft()); break;
			case RIGHT: temp = new RankedState(this.gameState.state.moveRight()); break;
			default: temp = null;
			}
			try {
				temp.state.setGoalAndPlayer();
				return temp;
			} catch (ProtoMapPlayerNotFoundException e) {
				return null;
			} catch (ProtoMapGoalNotFoundException e) {
				temp.state.goalFound = true;
				return temp;
			}
		} catch (CannotMovePlayerToCoordinateException e) {
			return null;
		}
	}
	private boolean isInQueue(RankedState item) {
		for (RankedState p: this.newSearchList) {
			if (p.state.equals(item.state)) return true;
		}
		return false;
	}
	

}

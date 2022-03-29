package ProtoPathing.cole;

import java.util.Stack;

public class ProtoSearchAgent {

	private ProtoMap gameState;
	
	private Stack<ProtoMap> searchList;
	private Stack<ProtoMap> newSearchList;
	private Stack<ProtoMap> deadEnds;
	
	public ProtoSearchAgent() {
		super();
		this.gameState = new ProtoMap();
		
		try {
			this.gameState.setGoalAndPlayer();
		} catch (ProtoMapPlayerNotFoundException e) {
			return;
		} catch (ProtoMapGoalNotFoundException e) {
			return;
		}
		
		this.searchList = new Stack<ProtoMap>();
		this.newSearchList = new Stack<ProtoMap>();
		this.deadEnds = new Stack<ProtoMap>();
		this.searchList.push(this.gameState);
		this.newSearchList.push(this.gameState);
		
		while(this.newSearchList.size() != 0) {
			
			if (this.isGoalState()) break;
			if (!this.generateNewStates()) {
				while(this.searchList.size() != 0 && this.gameState == this.searchList.peek()) {
					this.deadEnds.push(this.gameState);
					this.searchList.pop();
					this.gameState = this.newSearchList.pop();
				}
				this.searchList.push(this.gameState);
			}
			else {
				this.gameState = this.newSearchList.peek();
				this.searchList.push(this.gameState);
			}
		}
		
		for(ProtoMap p: this.searchList) {
			System.out.println(p.toString());
		}
	}
	
	private boolean isGoalState() {
		return this.gameState.goalFound;
	}
	
	private boolean generateNewStates() {
		boolean finalState = false;
		finalState = this.genUp() || finalState;
		finalState = this.genDown() || finalState;
		finalState = this.genLeft() || finalState;
		finalState = this.genRight() || finalState;
		return finalState;
	}
	
	private boolean genUp() {
		
		try {
			ProtoMap temp = this.gameState.moveUp();
			try {
				temp.setGoalAndPlayer();
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
			} catch (ProtoMapPlayerNotFoundException e) {
				return false;
			} catch (ProtoMapGoalNotFoundException e) {
				temp.goalFound = true;
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
				return true;
			}
		} catch (CannotMovePlayerToCoordinateException e) {
			return false;
		}
		return true;
	}
	
	private boolean genDown() {
		try {
			ProtoMap temp = this.gameState.moveDown();
			try {
				temp.setGoalAndPlayer();
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
			} catch (ProtoMapPlayerNotFoundException e) {
				return false;
			} catch (ProtoMapGoalNotFoundException e) {
				temp.goalFound = true;
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
				return true;
			}
		} catch (CannotMovePlayerToCoordinateException e) {
			return false;
		}
		return true;
	}
	
	private boolean genLeft() {
		
		try {
			ProtoMap temp = this.gameState.moveLeft();
			try {
				temp.setGoalAndPlayer();
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
			} catch (ProtoMapPlayerNotFoundException e) {
				return false;
			} catch (ProtoMapGoalNotFoundException e) {
				temp.goalFound = true;
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
				return true;
			}
		} catch (CannotMovePlayerToCoordinateException e) {
			return false;
		}
		return true;
	}
	
	private boolean genRight() {
		
		try {
			ProtoMap temp = this.gameState.moveRight();
			try {
				temp.setGoalAndPlayer();
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
			} catch (ProtoMapPlayerNotFoundException e) {
				return false;
			} catch (ProtoMapGoalNotFoundException e) {
				temp.goalFound = true;
				if (this.isInArray(this.deadEnds, temp) || this.isInArray(this.newSearchList, temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
				return true;
			}
		} catch (CannotMovePlayerToCoordinateException e) {
			return false;
		}
		return true;
	}
	
	private boolean isInArray(Stack<ProtoMap> list, ProtoMap item) {
		boolean finalBool = false;
		for (ProtoMap p: list) {
			if (p.equals(item)) return true;
		}
		return false;
	}
	
}

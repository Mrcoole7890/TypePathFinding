package ProtoPathing.cole;

import java.util.Stack;

import ProtoPathing.cole.MyList.ListType;

public class ProtoSearchAgent {
	
	private enum Directions{
		LEFT,RIGHT,UP,DOWN
	};

	private ProtoMap gameState;
	
	private Stack<ProtoMap> searchList;
	private MyList<ProtoMap> newSearchList;
	private Stack<ProtoMap> deadEnds;
	
	public ProtoSearchAgent(ListType lt) {
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
		this.newSearchList = new MyList<ProtoMap>(lt);
		this.deadEnds = new Stack<ProtoMap>();
		this.searchList.push(this.gameState);
		this.newSearchList.push(this.gameState);
		
		while(this.newSearchList.size() != 0) {
			
			if (this.isGoalState()) break;
			if (!this.generateNewStates()) {
				while(this.searchList.size() != 0 && this.gameState == this.searchList.peek()) {
					this.deadEnds.push(this.gameState);
					this.searchList.pop();
					this.newSearchList.pop();
					this.gameState = this.newSearchList.peek();
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
		finalState = this.gen(Directions.UP) || finalState;
		finalState = this.gen(Directions.DOWN) || finalState;
		finalState = this.gen(Directions.LEFT) || finalState;
		finalState = this.gen(Directions.RIGHT) || finalState;
		return finalState;
	}
	
	
	private boolean gen(Directions direction) {
		try {
			ProtoMap temp;
			switch(direction) {
			case UP: temp = this.gameState.moveUp(); break;
			case DOWN: temp = this.gameState.moveDown(); break;
			case LEFT: temp = this.gameState.moveLeft(); break;
			case RIGHT: temp = this.gameState.moveRight(); break;
			default: temp = null;
			}
			try {
				temp.setGoalAndPlayer();
				if (this.isInArray(this.deadEnds, temp) || this.newSearchList.inArray(temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
			} catch (ProtoMapPlayerNotFoundException e) {
				return false;
			} catch (ProtoMapGoalNotFoundException e) {
				temp.goalFound = true;
				if (this.isInArray(this.deadEnds, temp) || this.newSearchList.inArray(temp) || this.isInArray(this.searchList, temp)) return false;
				this.newSearchList.push(temp);
				return true;
			}
		} catch (CannotMovePlayerToCoordinateException e) {
			return false;
		}
		return true;
	}

	
	private boolean isInArray(Stack<ProtoMap> list, ProtoMap item) {
		for (ProtoMap p: list) {
			if (p.equals(item)) return true;
		}
		return false;
	}
	
}

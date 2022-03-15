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
			
		}
		
		for(ProtoMap p: this.searchList) {
			System.out.println(p.toString());
		}
	}
	
	private boolean isGoalState() {
		return this.gameState.goalFound;
	}
	
	public boolean generateNewStates() {
		return false;
		
	}
	
}

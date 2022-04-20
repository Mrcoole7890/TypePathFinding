package ProtoPathing.cole;

/**
 * 
 * @author mrcoole
 *
 * This object is a wrapper for the ProtoMap class so that it can be processed in a priority Queue
 */
public class RankedState implements Comparable<RankedState>{
	public ProtoMap state;
	public int rank;
	
	/**
	 * Initializes the wrapper class
	 * 
	 * @param newState the ProtoMap to be wrapped
	 * @param position the position in the state list to be tried
	 */
	public RankedState(ProtoMap newState) {
		state = newState;
		
		try {
			state.setGoalAndPlayer();
		} catch (ProtoMapPlayerNotFoundException e) {
			e.printStackTrace();
		} catch (ProtoMapGoalNotFoundException e) {
			this.state.goalFound = true;
		}
		
	}
	
	public void setRank(int newRank) {
		rank = newRank;
	}
	
	
	/**
	 * Returns the heuristic value
	 * 
	 * @param position the position of the state in the state list
	 */
	public int hCost() {
		if (this.state.goalFound) return 0;
		else
		return (Math.abs(state.getGoalPosition()[0] - state.getPlayerPosition()[0])) 
			 + (Math.abs(state.getGoalPosition()[1] - state.getPlayerPosition()[1]));
	}
	/**
	 * Compares two states to see which one has a higher ranks
	 * 
	 * @param arg0 is the other RankedState to be compared to
	 */
	public int compareTo(RankedState arg0) {
		if (rank > arg0.rank) return 1;
		else if(rank < arg0.rank) return -1;
		else return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RankedState other = (RankedState) obj;
		
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	
}

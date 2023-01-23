package ProtoPathing.cole;

import ProtoPathing.cole.MyList.ListType;

public class PathFindingProtoType {
	public static void main(String args[]) {
		ProtoSearchAgent p = null;
		switch (args[0]) {
			case "dfs":
				p = new ProtoSearchAgent(ListType.Stack, args[1]);
				break;
			case "bfs":
				p = new ProtoSearchAgent(ListType.Queue, args[1]);
				break;
			case "AStar":
				p = new ProtoSearchAgent(ListType.Priority, args[1]);
				break;
			default:
				System.out.println("first parameter should be : 'dfs', 'bfs', or 'AStar'");
				break;
		}
	}
}

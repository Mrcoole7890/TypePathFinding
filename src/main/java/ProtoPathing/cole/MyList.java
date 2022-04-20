package ProtoPathing.cole;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class MyList<h> {
	public enum ListType{Stack, Queue, Priority};
	
	private LinkedList<h> myList;
	private ListType myType;
	
	public MyList(ListType lt) {
		myType = lt;
		myList = new LinkedList<h>();
	}
	
	public void push(h e) {
		if (myType == ListType.Stack) myList.push(e);
		else myList.add(e);
	}
	
	public h pop() {
		if (myType == ListType.Stack) return myList.pop();
		else return myList.removeLast();
	}
	
	public h peek() {
		if (myType == ListType.Stack) return myList.peek();
		else return myList.peekLast();
	}
	
	public boolean inArray(h item) {
		for (h p: myList) {
			if (p.equals(item)) return true;
		}
		return false;
	}
	
	public int size() {
		return myList.size();
	}
}

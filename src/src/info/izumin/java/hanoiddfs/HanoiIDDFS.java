package info.izumin.java.hanoiddfs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HanoiIDDFS {

	private final int DISK_NUM;
	private StringBuilder result;

	public HanoiIDDFS(int num) {
		DISK_NUM = num;
	}

	public boolean search(int limit) {
		int depth = 1;
		Hanoi hanoi = new Hanoi(DISK_NUM);
		Set<Hanoi> open = new HashSet<Hanoi>(),
				closed = new HashSet<Hanoi>();
		
		open.add(hanoi);
		display("open:", open);
		return search(hanoi, limit, depth, open, closed);
	}

	public boolean search(Hanoi hanoi, int limit, int depth, Set<Hanoi> open, Set<Hanoi> closed) {
		
		System.out.println("// depth = " + String.valueOf(depth));
		
		close(hanoi, open, closed);
		if (depth == limit) return false;
		
		depth++;
		Set<Hanoi> nextNode = open(hanoi, open, closed);
		
		System.out.println("current:" + hanoi.toString());
		display("next:", nextNode);
		
		for (Hanoi h : nextNode) {
			if (h.isCompleted()) {
				result = new StringBuilder(h.toString());
				return true;
			}
		}
		
		for (Iterator<Hanoi> h = nextNode.iterator(); h.hasNext();) {
			Hanoi next = h.next();
			if (search(next, limit, depth, open, closed)) {
				addResult(next);
				return true;
			}
		}
		
		return false;
	}

	public Set<Hanoi> open(Hanoi hanoi, Set<Hanoi> open, Set<Hanoi> closed) {
		Set<Hanoi> nextNode = new HashSet<Hanoi>();
		for (Hanoi h : hanoi.getOpenableNode()) {
			if (!open.contains(h) && !closed.contains(h)) nextNode.add(h);
		}
		open.addAll(nextNode);
		display("open:", open);
		return nextNode;
	}

	public Hanoi close(Hanoi hanoi, Set<Hanoi> open, Set<Hanoi> closed) {
		closed.add(hanoi);
		open.remove(hanoi);
		display("closed:", closed);
		return hanoi;
	}

	public void display(String tag, Set<Hanoi> hanoiSet) {
		if (hanoiSet.isEmpty()) return;
		System.out.println(tag);
		for (Hanoi hanoi : hanoiSet) System.out.print("(" + hanoi.toString() + "),");
		System.out.println("");
	}

	public void addResult(Hanoi hanoi) { result.insert(0, "(" + hanoi.toString() + "), "); }
	public String getResult() { return result.toString(); }

}

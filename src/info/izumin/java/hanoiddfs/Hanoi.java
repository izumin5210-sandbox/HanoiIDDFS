package info.izumin.java.hanoiddfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Hanoi implements Cloneable {

	private final int DISK_NUM;
	private final static int TOWER_NUM = 3;
	private List<Stack<Integer>> towers = new ArrayList<Stack<Integer>>(TOWER_NUM);

	public Hanoi(int num) {
		DISK_NUM = num;
		initTower(towers);
		for (int i = DISK_NUM; i > 0; i--) towers.get(0).push(i);
	}

	public boolean canMove(int src, int dest) {
		if (src < 0 || src >= TOWER_NUM) return false;
		if (dest < 0 || dest >= TOWER_NUM) return false;
		if (towers.get(src).empty()) return false;
		if (towers.get(dest).empty()) return true;
		
		return towers.get(dest).peek() > towers.get(src).peek();
	}

	public Set<Hanoi> getOpenableNode() {
		Set<Hanoi> openableNode = new HashSet<Hanoi>();
		
		for (int i = 0; i < TOWER_NUM; i++) {
			for (int j = 0; j < TOWER_NUM; j++) {
				if (canMove(i,j)) openableNode.add(this.deepCopy().move(i, j));
			}
		}
		
		return openableNode;
	}

	public Hanoi move(int src, int dest) {
		towers.get(dest).push(towers.get(src).pop());
		return this;
	}

	public Stack<Integer> get(int num) {
		return towers.get(num);
	}

	public Hanoi deepCopy() {
		Hanoi clone = new Hanoi(TOWER_NUM);
		for (int i = 0; i < TOWER_NUM; i++) {
			clone.get(i).clear();
			for (int value : towers.get(i)) clone.get(i).push(value);
		}
		
		return clone;
	}

	public boolean isCompleted() { return towers.get(2).size() == DISK_NUM; }

	private void initTower(List<Stack<Integer>> towers) {
		for (int i = 0; i < TOWER_NUM; i++) towers.add(new Stack<Integer>());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (Stack<Integer> tower: towers)
			builder.append(tower.toString() + ",");
		
		builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		int hashcode = 33;
		for (Stack<Integer> stack : towers) {
			int sHashCode = 17;
			for (int i : stack) sHashCode = 31 * sHashCode + i;
			hashcode = 7 * hashcode + sHashCode;
		}
		return hashcode;
	}
}

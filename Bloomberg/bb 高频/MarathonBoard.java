package topKMarathon;

import java.util.*;
/**
 *  MarathonBoard --> Sensor[] sensors + update() + getTopK()
 *  Sensor --> DoubleLinkedList + removeRunnerWithID + addRunnerToTail + getRunnerList + getListSize
 *  DoubleLinkedList --> HashMap<id, Node> + head, tail + removeNodeWithID + appendTailWithID
 *  Node --> id + prev + next
 *  
 * @author Owen
 */
public class MarathonBoard {
	Sensor[] sensors;
	
	public MarathonBoard(int sensorNumber) {
		sensors = new Sensor[sensorNumber];
		for(int i = 0; i < sensorNumber; i++) {
			sensors[i] = new Sensor();
		}
	}
	
	public void update(int sensorIndex, int id) {
	    // if the first sensor
		if(sensorIndex == 0) {
			sensors[sensorIndex].addRunnerToTail(id);
		} else {  // if not the first
			//1. remove person from the previous sensor
			sensors[sensorIndex - 1].removeRunnerWithID(id);
			//2. add person to current sensor
			sensors[sensorIndex].addRunnerToTail(id);
		}
	}
	public List<Integer> getTopK(int k) {
		List<Integer> topK = new ArrayList<>();
		if(k <= 0) return topK;
		for(int i = sensors.length - 1; i >= 0; i--) {
			if(sensors[i].getListSize() == 0)
				continue;
			DoubleLinkedList list = sensors[i].getRunnerList();
			Node head = list.head.next;
			Node tail = list.tail;
			while(head != tail) {
				if(topK.size() >= k)
					return topK;
				topK.add(head.id);
				head = head.next;
			}
		}
		return topK;
	}
}

class Node {
	int id;
	Node prev;
	Node next;
	public Node(int id) {
		this.id = id;
	}
}

class DoubleLinkedList {
	HashMap<Integer, Node> map = new HashMap<>();
	Node head, tail;
	public DoubleLinkedList() {
		head = new Node(-1);
		tail = new Node(-1);
		head.next = tail;
		head.prev = null;
		tail.prev = head;
		tail.next = null;
	}
	public void removeNodeWithID(int id) {
		Node toBeRemoved = map.get(id);
		map.remove(id);
		if(toBeRemoved == null) {
			// throw exception
			return;
		}
		Node pre = toBeRemoved.prev;
		Node next = toBeRemoved.next;
		pre.next = next;
		next.prev = pre;
		toBeRemoved.next = null;
		toBeRemoved.prev = null;
	}
	public void appendTailWithID(int id) {
		Node newNode = new Node(id);
		map.put(id, newNode);
		Node preTail = tail.prev;
		preTail.next = newNode;
		newNode.prev = preTail;
		newNode.next = tail;
		tail.prev = newNode;
	}
}

class Sensor {
	DoubleLinkedList list;
	public Sensor() {
		list = new DoubleLinkedList();
	}
	public void removeRunnerWithID(int id) {
		list.removeNodeWithID(id);
	}
	public void addRunnerToTail(int id) {
		list.appendTailWithID(id);
	}
	public DoubleLinkedList getRunnerList() {
		return list;
	}
	public int getListSize() {
		return list.map.size();
	}
}

/*
	public static void main(String[] args) {
		MarathonBoard board = new MarathonBoard(3);
		// person: 0,1,2,3,4  sensor: 0,1,2
		board.update(0, 2);
		board.update(0, 1);
		board.update(0, 3);
		board.update(0, 4);
		board.update(0, 0);
		print(board.getTopK(5));
		board.update(1, 0);
		board.update(1, 3);
		board.update(1, 4);
		board.update(1, 2);
		board.update(1, 1);
		print(board.getTopK(5));
		board.update(2, 2);
		board.update(2, 1);
		board.update(2, 4);
		board.update(2, 3);
		board.update(2, 0);
		print(board.getTopK(5));
	}
	
	public static void print(List<Integer> list) {
		for(int i : list) {
			System.out.print(i + " --> ");
		}
		System.out.println();
	}
 */





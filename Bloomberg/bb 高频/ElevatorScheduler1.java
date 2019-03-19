package ElevatorSchedulers;

import java.util.*;

/**
 * Strategy: 
 * 	First come first serve
 * 		|-- equal come, shorter first
 * @author Owen
 *
 */
public class ElevatorScheduler1 {

	PriorityQueue<Request> queue = new PriorityQueue<>(new Comparator<Request>() {
		public int compare(Request a, Request b) {
			if(a.time - b.time == 0)
				return a.distance - b.distance;
			return (int) (a.time - b.time);
		}
	});
	
	public Request handleRequest(List<Request> requestList) {
		for(Request r : requestList) {
			queue.offer(r);
		}
		if(queue.isEmpty())
			return null;
		return queue.poll();
	}
	public Request handleRequest(Request request) {
		queue.offer(request);
		if(queue.isEmpty())
			return null;
		return queue.poll();
	}
	public Request handleRequest() {
		if(queue.isEmpty())
			return null;
		return queue.poll();
	}
}


class Request {
	int fromFloor;
	int toFloor;
	int distance;
	double time;
	int direction;  // up : 1, down : -1, stay : 0
	public Request(int fromFloor, int toFloor, double time, int direction) {
		this.fromFloor = fromFloor;
		this.toFloor = toFloor;
		this.distance = Math.abs(fromFloor - toFloor);
		this.time = time;
		this.direction = distance == 0 ? 0 : ((fromFloor - toFloor) / distance);
	}
}
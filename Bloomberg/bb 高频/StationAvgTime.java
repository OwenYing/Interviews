package SubwayStationAvgTime;

import java.util.*;

public class StationAvgTime {

	public HashMap<Integer, Node> record = new HashMap<>();
	public HashMap<String, List<Node>> stations = new HashMap<>();
	
	public void swipeIn(int userID, String stationIn, double timeIn) {
		Node r = new Node(userID, stationIn, null, timeIn, -1);
		record.put(userID, r);
	}
	public void swipeOut(int userID, String stationOut, double timeOut) {
		Node r = record.remove(userID);  // here need to remove the finished travel   
		r.stationOut = stationOut;
		r.timeOut = timeOut;
		String key = r.stationIn + "_" + r.stationOut;
		if(!stations.containsKey(key)) {
			List<Node> list = new ArrayList<>();
			stations.put(key, list);
		}
		stations.get(key).add(r);
	}
	
	public double getAvgTimeBetweenStations(String stationIn, String stationOut) {
		String key = stationIn + "_" + stationOut;
		if(!stations.containsKey(key))
			return 0;
		List<Node> list = stations.get(key);
		double totalTime = 0;
		for(Node travel : list) {
			totalTime += (travel.timeOut - travel.timeIn);
		}
		return totalTime / list.size();
	}
	
}


class Node {
	int userID;
	String stationIn;
	String stationOut;
	double timeIn;
	double timeOut;
	public Node(int user_id, String station_in, String station_out, double time_in, double time_out) {
		userID = user_id;
		stationIn = station_in;
		stationOut = station_out;
		timeIn = time_in;
		timeOut = time_out;
	}
}


/*
		StationAvgTime scheduler = new StationAvgTime();
		// User: 1, 2, 3 
		scheduler.swipeIn(1, "A", 0);
		scheduler.swipeIn(2, "A", 1);
		scheduler.swipeIn(3, "A", 3);
		
		scheduler.swipeOut(1, "B", 5);
		scheduler.swipeOut(2, "B", 5);
		scheduler.swipeOut(3, "C", 5);
		
		scheduler.swipeIn(1, "A", 0);
		scheduler.swipeIn(3, "A", 3);
		
		scheduler.swipeOut(1, "B", 5);
		scheduler.swipeOut(3, "C", 5);
		System.out.println(scheduler.getAvgTimeBetweenStations("A", "B"));
		System.out.println(scheduler.getAvgTimeBetweenStations("A", "C"));
 */


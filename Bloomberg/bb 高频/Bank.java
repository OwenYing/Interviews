package BankTransaction;

import java.util.*;

public class Bank {

	HashMap<String, Branch> branchMap = new HashMap<>(); 
	public Bank() {
		branchMap.put("A", new Branch());
		branchMap.put("B", new Branch());
	}
	
	public boolean handleTransaction(int userID, String location, double time, String branchName) {
		Request request = new Request(userID, location, time, branchName);
		Branch branch = branchMap.get(branchName);
		return branch.handleRequest(request);
	}
}


class Request {
	int userID;
	String location;
	double time;
	String branchName;
	public Request(int userID, String location, double time, String branchName) {
		this.userID = userID;
		this.location = location;
		this.time = time;
		this.branchName = branchName;
	}
}

class Branch {
	HashMap<Integer, Request> record = new HashMap<>();
	int transactionCount = 0;
	final int DAILY_TRANSACTION_TIMES = 3;
	
	public boolean handleRequest(Request request) {
		if(transactionCount >= DAILY_TRANSACTION_TIMES)
			return false;
		Request prevRequest = record.get(request.userID);
		if(prevRequest == null) {
			record.put(request.userID, request); 
			transactionCount ++;
			return true;
		} else {
			if((request.time - prevRequest.time) < 60) {  // in minutes
				return false;
			}
			record.put(request.userID, request);
			transactionCount ++;
			return true;
		}
	}
}

/*
 
 		Bank ICBC = new Bank();
		System.out.println(ICBC.handleTransaction(1, "Beijing", 0, "A"));
		System.out.println(ICBC.handleTransaction(1, "Beijing", 61, "A"));
		System.out.println(ICBC.handleTransaction(1, "Beijing", 150, "A"));
		System.out.println(ICBC.handleTransaction(1, "Beijing", 300, "A"));
		System.out.println(ICBC.handleTransaction(1, "Beijing", 400, "A"));
		
		System.out.println(ICBC.handleTransaction(1, "Beijing", 400, "B"));
		System.out.println(ICBC.handleTransaction(1, "Beijing", 500, "B"));
		System.out.println(ICBC.handleTransaction(1, "Beijing", 600, "B"));
		System.out.println(ICBC.handleTransaction(1, "Beijing", 700, "B"));
 
 */
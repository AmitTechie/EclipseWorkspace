package Simpl;

import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

class User{
	private String userId; //it can be email or mobile number
	private String userName;
	private LICENSE_TYPE license_type = LICENSE_TYPE.NONE; //default value
	
	public LICENSE_TYPE getLicense_type() {
		return license_type;
	}
	public void setLicense_type(LICENSE_TYPE license_type) {
		this.license_type = license_type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}

enum LICENSE_TYPE {
	NONE(0),
	LOW(10),
	MEDIUM(20),
	HIGH(50);
	
	private int licenseType;

    private LICENSE_TYPE(int licenseType) {
        this.licenseType = licenseType;
    }
    
    public int getRateLimitValue() {
    	return licenseType;
    }
}

class Request{
	private User user;
	private String apiToCall;
	private Time requestTime;

	Request(){
		this.requestTime = new Time(System.currentTimeMillis());
	}
	
	public Time getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Time requestTime) {
		this.requestTime = requestTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getApiToCall() {
		return apiToCall;
	}
	public void setApiToCall(String apiToCall) {
		this.apiToCall = apiToCall;
	}
}

class UserRequestRecord{
	private Queue<Request> userRequestQueue;
	
	public UserRequestRecord(){
		this.userRequestQueue = new LinkedList<>();
	}
	
	public Request getFrontRequest() {
		return userRequestQueue.peek();
	}

	public Request removeFrontRequest() {
		return userRequestQueue.poll();
	}
	
	public void addRequest(Request request) {
	
		this.userRequestQueue.add(request);
	}
	
	public int getUserRequestCount() {
		return userRequestQueue.size();
	}

}

class APIRateLimiter{
	private String api;
	private int rateLimitCount; //rate limit of the api, in req per second
	private Map<User, UserRequestRecord> userAPICallRecord;
	private Queue<Request> requestWindow; //assuming that this requestWindow will hold rateLimitCount requests.
	
	public APIRateLimiter(String api, int rateLimitCount) {
		this.api = api;
		this.rateLimitCount = rateLimitCount;
		this.requestWindow = new LinkedList<>();
		userAPICallRecord = new HashMap<>();
	}

	public int getRateLimitCount() {
		return rateLimitCount;
	}

	public void setRateLimitCount(int rateLimitCount) {
		this.rateLimitCount = rateLimitCount;
	}
	
	public void handleNewRequest(Request request) {
		
		if(request == null || !request.getApiToCall().equals(this.api) || request.getUser() == null || request.getUser().getLicense_type().equals(LICENSE_TYPE.NONE)) {
			return;
		}

		//check for user limit has exceed
		UserRequestRecord userRequestRecord = userAPICallRecord.get(request.getUser());
		if(userRequestRecord == null) {
			userRequestRecord = new UserRequestRecord();
			userRequestRecord.addRequest(request);
		}else {
			int timeDelay = (int)(System.currentTimeMillis() - userRequestRecord.getFrontRequest().getRequestTime().getTime())/1000; //in seconds
			while(timeDelay > rateLimitCount) {
				userRequestRecord.removeFrontRequest();
				timeDelay = (int)(System.currentTimeMillis() - userRequestRecord.getFrontRequest().getRequestTime().getTime())/1000; //in seconds
			}
			
			if(userRequestRecord.getUserRequestCount() >= request.getUser().getLicense_type().getRateLimitValue()) {
				//add the request to waiting queue..
				return;
			}
			
		}

		
		if(requestWindow.size() < rateLimitCount) {
			//ADD request ..
			userRequestRecord.addRequest(request);
			userAPICallRecord.put(request.getUser(), userRequestRecord);
			requestWindow.add(request);
		}else {
			//add the reuqest to waiting queue..
			
			//remove the time expire requests..
			int timeDelay = (int)(System.currentTimeMillis() - requestWindow.peek().getRequestTime().getTime())/1000; //in seconds
			while(timeDelay > rateLimitCount) {
				requestWindow.poll();
				timeDelay = (int)(System.currentTimeMillis() - requestWindow.peek().getRequestTime().getTime())/1000; //in seconds
			}
			
			//take request from waiting window and check it expiration and add to the requestWindow and update userAPICallRecord
			
		}
		
	}
	
	
	
}

//this will be the public class..
class RateLimiter{
	
	private Map<String, APIRateLimiter> apiMapper;
	private Map<String, Integer> apiLoadCount;
	
	public RateLimiter(){
		this.apiMapper = new HashMap<>();
		this.apiLoadCount = new HashMap<>();//this will be configurable..
	}
	
	public boolean isValidRequest(Request request) {
		if(request != null ) {
			
			//will check against user and api also..
			return false;
		}
		
		return true;
	}
	
	public void handleRequest(Request request) {
		if(!isValidRequest(request))
			return;
			
		APIRateLimiter apiRateLimiter = apiMapper.getOrDefault(request.getApiToCall(), new APIRateLimiter(request.getApiToCall(), getApiLoadCount(request.getApiToCall())));
		apiRateLimiter.handleNewRequest(request);
	}
	
	public int getApiLoadCount(String api) {
		return apiLoadCount.getOrDefault(api, 100);
	}
	
}

class CustomIterator<T> implements Iterator<T>{

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}



public class MainClass {
	

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		Iterator<Integer> iterator = list.iterator();
		
		Map<String, String> map = new HashMap<>();
		Iterator<Map.Entry<String, String>> mapIterator = map.entrySet().iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
			iterator.remove();
		}
		
		System.out.println(list.size());
		
		//Unit tests..comment the below line to stop unit tests
/*		UnitTestDriver.runUnitTests();

		Team team1 = new Team("Chennai");
		team1.addPlayer(new Player("Chennai_Player1"));
		team1.addPlayer(new Player("Chennai_Player2"));
		team1.addPlayer(new Player("Chennai_Player3"));
		team1.addPlayer(new Player("Chennai_Player4"));
		team1.addPlayer(new Player("Chennai_Player5"));
		team1.addPlayer(new Player("Chennai_Player6"));
		team1.addPlayer(new Player("Chennai_Player7"));
		team1.addPlayer(new Player("Chennai_Player8"));
		team1.addPlayer(new Player("Chennai_Player9"));
		team1.addPlayer(new Player("Chennai_Player10"));
		team1.addPlayer(new Player("Chennai_Player11"));

		Team team2 = new Team("Bangalore");
		team2.addPlayer(new Player("Bangalore_Player1"));
		team2.addPlayer(new Player("Bangalore_Player2"));
		team2.addPlayer(new Player("Bangalore_Player3"));
		team2.addPlayer(new Player("Bangalore_Player4"));
		team2.addPlayer(new Player("Bangalore_Player5"));
		team2.addPlayer(new Player("Bangalore_Player6"));
		team2.addPlayer(new Player("Bangalore_Player7"));
		team2.addPlayer(new Player("Kirat Boli"));
		team2.addPlayer(new Player("N.S Nodhi"));
		team2.addPlayer(new Player("R Rumrah"));
		team2.addPlayer(new Player("Shashi Henra"));
		
		Match match = new Match(team1, team2, MATCH_TYPE.T20, 20);
		
		System.out.println("\nMATCH is ready to play\n");
		
		// comment the below code to simulate entire T20 match.
		// setFirstInningMatchDataForSecondInningSimulation() is taking the match to the point where Bangalore team would required 40 runs from 
		// four hours. If you comment the below code then it would simulate the entire T20 match from starting and you will see each bowl update
		// each over update.
		//match.setFirstInningMatchDataForSecondInningSimulation();
		
		match.startMatch();
*/	}

}

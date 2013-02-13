package code;

import java.io.IOException;

import twitter4j.TwitterException;

public class Driver {
	public static void main(String[] args) {
		System.out.println("Preparing for Data Aggregation...");
		DataAggregate da = new DataAggregate();
		
		System.out.println("Beginning Data Aggregation...");
		da.start();
	}
		
}

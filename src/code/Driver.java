package code;

import java.io.IOException;

import twitter4j.TwitterException;

public class Driver {
	public static void main(String[] args) {
		System.out.println("Preparing for Data Aggregation...");
		DataAggregate da = new DataAggregate();
		
		System.out.println("Beginning Data Aggregation...");
		
		try {
			da.getData();
			
		} catch (TwitterException e) {
			System.err.println("TwitterException Thrown. Retrying in 5 seconds");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			System.err.println("IO Thrown. Retrying in 5 seconds");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

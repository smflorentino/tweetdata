package code;

import java.io.IOException;

import auth.ConfigBuilder;

import twitter4j.RawStreamListener;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class DataAggregate {
	
	public static final String UN_OPEN = "<un>";
	public static final String UN_CLOSE = "</un>";
	public static final String TT_OPEN = "<tt>";
	public static final String TT_CLOSE = "</tt>";
	public static final String DT_OPEN = "<dt>";
	public static final String DT_CLOSE = "</dt>";
	public static final String RT_OPEN = "<rt>";
	public static final String RT_CLOSE = "</rt>";
	
	private int failures = 0;
	public void start() {
			try {
				this.getData();
				
			} catch (TwitterException e) {
				System.err.println("TwitterException Generated. Retrying in 5 seconds...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(failures < 1000) {
					this.start();
				}
				e.printStackTrace();
			} catch (IOException e) {
				failures++;
				System.err.println("IO Generated. Retrying in 5 seconds...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(failures < 1000) {
					this.start();
				}
				e.printStackTrace();
			}
	}
	
	public void getData() throws TwitterException, IOException{
		//passes our app tokens to twitter to log in
		ConfigBuilder cb = new ConfigBuilder();

		//final FileWrite fw = new FileWrite();
		//pull tweets as objects
	    StatusListener listener = new StatusListener(){
	        public void onStatus(Status status) {
	            //fw.writeLine("<un>" + status.getUser().getName() + "</un> <tt> " + status.getText() + "</tt> <dt>" + status.getCreatedAt() + "</dt>");
	        	RollingDataFileAppender.writeEvent(UN_OPEN + status.getUser().getName() + UN_CLOSE + TT_OPEN + status.getText() + TT_CLOSE + DT_OPEN + status.getCreatedAt() + DT_CLOSE + RT_OPEN + status.getRetweetCount() + RT_CLOSE);
	        }
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub	
			}
	    };
	    
	    //write to a file called out.txt in the local file system (out.txt)
	    
	    
	    //pull raw tweet data (text only)
	    //dump it to a file called out.txt
	    RawStreamListener listenerraw = new RawStreamListener() {
	    	int tweets=0;
            @Override
            public void onMessage(String rawJSON) {
            	//fw.writeLine("Tweet: " + tweets + rawJSON + '\n');
            	tweets++;
            }
            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        
        //get the configuration
	    TwitterStream twitterStream = new TwitterStreamFactory(cb.getConfig().build()).getInstance();
	    
	    //pick our listener (raw or status)
	    twitterStream.addListener(listener);
	    
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
	    System.out.println("Beginning Tweet Dump");
	    twitterStream.sample();
	    

	  
	}
}

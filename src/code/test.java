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

public class test {
	public static void main(String[] args) throws TwitterException, IOException{
		//passes our app tokens to twitter to log in
		ConfigBuilder cb = new ConfigBuilder();

		
		//pull tweets as objects
	    StatusListener listener = new StatusListener(){
	        public void onStatus(Status status) {
	            System.out.println(status.getUser().getName() + " : " + status.getText());
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
	    final FileWrite fw = new FileWrite();
	    
	    //pull raw tweet data (text only)
	    //dump it to a file called out.txt
	    RawStreamListener listenerraw = new RawStreamListener() {
	    	int tweets=0;
            @Override
            public void onMessage(String rawJSON) {
            	fw.writeLine("Tweet: " + tweets + rawJSON + '\n');
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
	    twitterStream.addListener(listenerraw);
	    
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
	    twitterStream.sample();
	    try {
	    	//gather data from the simulation for 60 seconds
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //finish
	    fw.closeFile();
	    System.out.println("done. ");
	    System.exit(0);
	    
	}
}

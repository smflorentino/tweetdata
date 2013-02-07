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
		
		
		ConfigBuilder cb = new ConfigBuilder();

		
		
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
	    final FileWrite fw = new FileWrite();
	    
	    RawStreamListener listenerraw = new RawStreamListener() {
	    	int tweets=0;
            @Override
            public void onMessage(String rawJSON) {
                //System.out.println(rawJSON);
            	fw.writeLine(rawJSON);
            	tweets++;
            }
            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        
        //get the configuration
	    TwitterStream twitterStream = new TwitterStreamFactory(cb.getConfig().build()).getInstance();
	    twitterStream.addListener(listenerraw);
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
	    twitterStream.sample();
	    try {
			Thread.sleep(360000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    fw.closeFile();
	    System.out.println("done");
	    System.exit(0);
	    
	}
}

package auth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import twitter4j.conf.ConfigurationBuilder;

public class ConfigBuilder {
	private ConfigurationBuilder _cb;
	public ConfigBuilder() {
		_cb = new ConfigurationBuilder();
		_cb.setDebugEnabled(true);
		this.readKeys();
	}
	
	private void readKeys() {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("config/appkeys.key"));
			//read in keys and apply them
			_cb.setOAuthConsumerKey(br.readLine());
			_cb.setOAuthConsumerSecret(br.readLine());
			_cb.setOAuthAccessToken(br.readLine());
			_cb.setOAuthAccessTokenSecret(br.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public ConfigurationBuilder getConfig() {
		return _cb;
	}
}

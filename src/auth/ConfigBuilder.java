package auth;

import twitter4j.conf.ConfigurationBuilder;

public class ConfigBuilder {
	private ConfigurationBuilder _cb;
	public ConfigBuilder() {
		_cb = new ConfigurationBuilder();
		_cb.setDebugEnabled(true);
		
	}
	
	public ConfigurationBuilder getConfig() {
		return _cb;
	}
}

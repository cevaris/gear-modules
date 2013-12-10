package org.gears.web.webgear;

import org.gears.Configuration;
import org.gears.Instance;

public class ProductionConfig {
	
	private final static String SSH_KEY = "/Users/cevaris/Documents/workspace/gears/gears/keys/id_rsa";
	
	private Configuration config = Configuration.getInstance();
	
	public ProductionConfig() {
		Instance instance1 = new Instance("192.168.2.100", SSH_KEY);
		config.addInstance("web", instance1);
		Instance instance2 = new Instance("192.168.2.102", SSH_KEY);
		config.addInstance("web", instance2);
		
		Instance instance3 = new Instance("192.168.2.101", SSH_KEY);
		config.addInstance("db", instance3);
		
//			Instance instance4 = new Instance("192.168.2.103", SSH_KEY);
//			config.addInstance("lb", instance4);
	}

}

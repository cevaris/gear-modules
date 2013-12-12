package org.gears.web.webgear;

import org.apache.log4j.Logger;
import org.gears.Configuration;
import org.gears.Environment;
import org.gears.Instance;
import org.gears.System;
import org.gears.utils.ResourceUtil;

public class ProductionEnv extends Environment {
	
	private static final Logger LOG = Logger.getLogger(ProductionEnv.class);
	
	private final static String SSH_KEY = ResourceUtil.getHomeDirectoryPath(".ssh/vagrant-keys/id_rsa");
	
	private Configuration config = Configuration.getInstance();
	
	public ProductionEnv() {
		
		config.addInstance("web", new Instance("192.168.2.100", SSH_KEY, System.RED_HAT) );
		config.addInstance("web", new Instance("192.168.2.101", SSH_KEY, System.RED_HAT) );
		
		config.addInstance("db",  new Instance("192.168.2.102", SSH_KEY, System.RED_HAT) );
//		
//		config.addInstance("lb",  new Instance("192.168.2.103", SSH_KEY) );
//		
//		config.addInstance("cache",  new Instance("192.168.2.104", SSH_KEY) );
		
	}

}

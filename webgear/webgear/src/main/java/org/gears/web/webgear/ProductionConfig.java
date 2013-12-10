package org.gears.web.webgear;

import java.io.File;

import org.apache.log4j.Logger;
import org.gears.Configuration;
import org.gears.Instance;
import org.gears.utils.ResourceUtil;

public class ProductionConfig {
	
	private static final Logger LOG = Logger.getLogger(ProductionConfig.class);
	
	private final static String SSH_KEY = ResourceUtil.getResourcePath("id_rsa");
	
	private Configuration config = Configuration.getInstance();
	
	public ProductionConfig() {
		
		config.addInstance("web", new Instance("192.168.2.100", SSH_KEY) );
		config.addInstance("web", new Instance("192.168.2.101", SSH_KEY) );
		
//		Instance instance3 = new Instance("192.168.2.102", SSH_KEY);
//		config.addInstance("db", instance3);
		
//			Instance instance4 = new Instance("192.168.2.103", SSH_KEY);
//			config.addInstance("lb", instance4);
	}

}

package org.gears.web.webgear;

import org.gears.Configuration;
import org.gears.Instance;
import org.gears.haproxy.Balancer;
import org.gears.haproxy.HAProxy;
import org.gears.haproxy.Slave;

public class HAPRoxyApp extends HAProxy {

	public HAPRoxyApp() {
		Configuration config = Configuration.getInstance();
		
		this.balancer = Balancer.ROUNDROBIN;
		
		for(int i = 0; i < config.getInstances("web").size(); i++){
			Instance instance = config.getInstances().get(i);
			Slave slave = new Slave( String.format("slave%s",i), instance.getFQDN(), 80);
			slaves.add(slave);
		}
		
	}
}

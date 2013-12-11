package org.gears.memcached;

import org.gears.Configuration;
import org.gears.Application;
import org.gears.Instance;
import org.gears.utils.ResourceUtil;
import org.junit.Test;

public class MemcachedTest {
	
	private final static String SSH_KEY = ResourceUtil.getHomeDirectoryPath(".ssh/vagrant-keys/id_rsa");

	@Test
	public void test() {
		
		Configuration.getInstance().addInstance("cache", new Instance("192.168.2.104", SSH_KEY));
		
		Application memcached = new Memcached();
		memcached.execute();
		
		
	}

}

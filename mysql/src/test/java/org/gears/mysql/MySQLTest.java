package org.gears.mysql;

import static org.junit.Assert.*;

import org.gears.Configuration;
import org.gears.Application;
import org.gears.Instance;
import org.gears.utils.ResourceUtil;
import org.junit.Test;

public class MySQLTest {

	private final static String SSH_KEY = ResourceUtil.getHomeDirectoryPath(".ssh/vagrant-keys/id_rsa");
	
	@Test
	public void test() {
		
		Configuration.getInstance().addInstance("db", new Instance("192.168.2.102", SSH_KEY));
		
		Application mysql = new MySQL();
		mysql.execute();
	}

}

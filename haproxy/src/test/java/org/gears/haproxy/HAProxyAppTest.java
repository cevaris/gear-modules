package org.gears.haproxy;

import java.io.File;
import java.io.IOException;

import org.gears.utils.ResourceUtil;

public class HAProxyAppTest extends HAProxy {

	public HAProxyAppTest() {

		slaves.add(new Slave("slave0", "192.168.2.100", 80));
		slaves.add(new Slave("slave1", "192.168.2.101", 80));
		slaves.add(new Slave("slave2", "192.168.2.101", 80));
		
	}

}

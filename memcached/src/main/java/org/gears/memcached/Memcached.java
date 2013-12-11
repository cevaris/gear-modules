package org.gears.memcached;

import org.gears.Application;

public class Memcached extends Application {
	
	public String port = "11211";
	public String ipAddress = "127.0.0.1";

	@Override
	public void execute() {
		install("-y", "memcached php5-memcached");
		
		openPort(this.port);
		
		render("memcached.conf.vm", "/etc/memcached.conf");
		
		start(this);
		restart(this);
	}
	
	@Override
	public String toString() {
		return "memcached";
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public String getPort() {
		return port;
	}

}

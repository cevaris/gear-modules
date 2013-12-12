package org.gears.memcached;

import org.gears.Application;
import org.gears.GearApplication;
import org.gears.Service;
import org.gears.System;

public class Memcached extends GearApplication {
	
	public String port = "11211";
	public String ipAddress = "127.0.0.1";

	@Override
	public void execute() {
		install("memcached php5-memcached php php-pecl-memcache");
		
		openPort(this.port);
		
		if(isSystem(System.RED_HAT)){
			render("redhat.memcached.conf.vm", "/etc/sysconfig/memcached");
		} else if(isSystem(System.DEBIAN)){
			render("debian.memcached.conf.vm", "/etc/memcached.conf");
		}
		
		service("memcached", Service.RESTART);
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

package org.gears.haproxy;

public class Slave {

	int port;
	String ipAddress;
	String name;

	public Slave(String name, String ipAddress, int port) {
		this.name = name;
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	/**
	 * Returns in format needed for configuration file
	 * ex. "server lamp1 192.168.2.102:80 check"
	 */
	@Override
	public String toString() {
		return String.format("server %s %s:%d check",
				this.name, this.ipAddress, this.port);
	}

}

package org.gears.haproxy;

public enum Balancer {

	ROUNDROBIN,
	LEASTCONN,
	SOURCE,
	URI,
	URL_PARAM;
	
	
}

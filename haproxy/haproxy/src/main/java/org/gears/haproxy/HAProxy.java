package org.gears.haproxy;

import java.util.ArrayList;
import java.util.List;

import org.gears.Gear;


public class HAProxy extends Gear {
	
	public List<Slave> slaves = new ArrayList<Slave>();
	
	public String username = "root";
	public String password = "mypass";
	public Balancer balancer = Balancer.LEASTCONN;
	
	@Override
	public void execute() {
		// Update application repository
		update();
		
		// Install misc apps
		install( "-y", "haproxy" );

		// Restart Apache service, equals to "service apache2 restart"
		restart("apache2");
	}
	
	
	public String getBalancer() {
		return String.valueOf(this.balancer).toLowerCase();
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public List<Slave> getSlaves() {
		return slaves;
	}

}
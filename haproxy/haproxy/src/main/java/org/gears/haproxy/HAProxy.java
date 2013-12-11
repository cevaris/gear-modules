package org.gears.haproxy;

import java.util.ArrayList;
import java.util.List;

import org.gears.Gear;


public class HAProxy extends Gear {
	
	public List<Slave> slaves = new ArrayList<Slave>();
	
	public String username = "root";
	public String password = "mypass";
	public Balancer balancer = Balancer.ROUNDROBIN;
	
	@Override
	public void execute() {
		// Update application repository
//		update();
		
//		install( "-y", "haproxy" );
		
		renderConfig();

		start("haproxy");
		restart("haproxy");
	}
	

	private void renderConfig(){
		render("default.haproxy.vm", "/etc/default/haproxy");
		render("haproxy.cfg.vm", "/etc/haproxy/haproxy.cfg");
	}
	
	@Override
	public String toString() {
		return "haproxy";
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
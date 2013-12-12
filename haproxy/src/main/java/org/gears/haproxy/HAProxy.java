package org.gears.haproxy;

import java.util.ArrayList;
import java.util.List;

import org.gears.GearModule;
import org.gears.Service;


public class HAProxy extends GearModule {
	
	public List<Slave> slaves = new ArrayList<Slave>();
	
	public String username = "root";
	public String password = "mypass";
	public Balancer balancer = Balancer.ROUNDROBIN;
	
	@Override
	public void execute() {
		install( "haproxy" );
		
		renderConfig();

		service("haproxy", Service.RESTART);
	}

	private void renderConfig(){
		render("default.haproxy.vm", "/etc/default/haproxy");
		render("haproxy.cfg.vm",     "/etc/haproxy/haproxy.cfg");
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
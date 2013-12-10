package org.gears.haproxy;

import java.util.ArrayList;
import java.util.List;

import org.gears.Gear;

public class HAProxy extends Gear {
	
	protected List<Slave> slaves = new ArrayList<Slave>();
	
	protected static String USERNAME = "root";
	protected static String PASSWORD = "mypass";
	
	@Override
	public void execute() {
		// Update application repository
		update();
		
		// Install misc apps
		install( "-y", "haproxy" );

		// Restart Apache service, equals to "service apache2 restart"
		restart("apache2");
	}

}
package org.gears.haproxy;

import org.gears.Gear;

public class HAProxy extends Gear {
	
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
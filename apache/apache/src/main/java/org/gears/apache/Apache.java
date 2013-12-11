package org.gears.apache;

import org.gears.Gear;

public class Apache extends Gear {
	
		
	@Override
	public void execute() {
		// Update application repository
		update();
		
		// Install misc apps
		install( "-y", "apache2 libapache2-mod-php5 php5-mcrypt" );

		// Restart Apache service, equals to "service apache2 restart"
		restart("apache2");
	}
	
	@Override
	public String toString() {
		return "apache2";
	}

}

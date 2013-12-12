package org.gears.apache;

import org.gears.GearApplication;
import org.gears.Service;

public class Apache extends GearApplication {
	
		
	@Override
	public void execute() {
		
		switch (getSystem()) {
		
		case DEBIAN:
			install("apache2 libapache2-mod-php5 php5-mcrypt" );
			service("apache2", Service.RESTART);
			break;
			
		case RED_HAT:
			install("httpd");
			service("httpd", Service.RESTART);
			break;
		}
		
	}
}

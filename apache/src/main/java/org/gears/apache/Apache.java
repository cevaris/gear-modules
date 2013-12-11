package org.gears.apache;

import org.gears.Application;

public class Apache extends Application {
	
		
	@Override
	public void execute() {
		// Update application repository
//		update();
		
		switch (getSystem()) {
		
		case DEBIAN:
			install( "-y", "apache2 libapache2-mod-php5 php5-mcrypt" );
			restart("apache2");
			break;
			
		case RED_HAT:
			install("-y", "httpd");
			restart("httpd");
			break;
		}
		
	}
	
	@Override
	public String toString() {
		
		String result = null;
		
		switch (getSystem()) {
		case DEBIAN:  result = "apache2"; break;
		case RED_HAT: result = "httpd";   break;
		}
		
		return result;
		
	}

}

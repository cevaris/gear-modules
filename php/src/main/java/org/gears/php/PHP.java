package org.gears.php;

import org.gears.Gear;

public class PHP extends Gear {
	
		
	@Override
	public void execute() {
		// Update application repository
		update();

		// Install misc apps
		install("-y", "php5");
	}

}

package org.gears.php;

import org.gears.Application;
import org.gears.GearApplication;

public class PHP extends GearApplication {

	@Override
	public void execute() {
		// update();

		switch (getSystem()) {
		case DEBIAN:
			install("php5");
			break;
		case RED_HAT:
			install("php");
			break;
		}

	}

}

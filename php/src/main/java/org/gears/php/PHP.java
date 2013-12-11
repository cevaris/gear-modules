package org.gears.php;

import org.gears.Application;

public class PHP extends Application {

	@Override
	public void execute() {
		// update();

		switch (getSystem()) {
		case DEBIAN:
			install("-y", "php5");
			break;
		case RED_HAT:
			install("-y", "phpl");
			break;
		}

	}

}

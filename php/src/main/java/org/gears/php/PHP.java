package org.gears.php;

import org.gears.Gear;
import org.gears.System;

public class PHP extends Gear {

	@Override
	public void execute() {
		// update();

		switch (this.instance.getSystem()) {
		case DEBIAN:
			install("-y", "php5");
			break;
		case RED_HAT:
			install("-y", "php php-mysql");
			break;
		}

	}

}

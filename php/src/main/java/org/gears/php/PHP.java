package org.gears.php;

import org.gears.Gear;
import org.gears.GearModule;
import org.gears.System;

public class PHP extends GearModule {

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
	
	@Override
	public String toString() {
		if(isSystem(System.RED_HAT)){
			return "php";
		} else if(isSystem(System.DEBIAN)){
			return "php5";
		} else {
			return null;
		}
	}

}

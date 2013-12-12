package org.gears.vim;

import org.gears.Application;
import org.gears.GearApplication;

public class Vim extends GearApplication {
	
	@Override
	public void execute() {
		// Install misc apps
		install("vim");
	}
	
}

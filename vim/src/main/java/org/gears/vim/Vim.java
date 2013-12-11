package org.gears.vim;

import org.gears.Gear;

public class Vim extends Gear {
	
	@Override
	public void execute() {
		// Update application repository
		update();

		// Install misc apps
		install("vim");
	}
}

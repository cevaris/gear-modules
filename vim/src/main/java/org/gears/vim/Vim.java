package org.gears.vim;

import org.gears.Application;

public class Vim extends Application {
	
	@Override
	public void execute() {
		// Update application repository
//		update();

		// Install misc apps
		install("vim");
	}
}

package org.gears.vim;

import org.gears.Application;
import org.gears.GearApplication;
import org.gears.System;

public class Vim extends GearApplication {
	
	@Override
	public void execute() {
		// Install misc apps
		install("vim");
	}
	
	
	@Override
	public String toString() {
		return "vim";
	}
	
}

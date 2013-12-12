package org.gears.vim;

import org.gears.Gear;
import org.gears.GearModule;
import org.gears.System;

public class Vim extends GearModule {
	
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

package org.gears.web.webgear;

import org.gears.Application;
import org.junit.Test;

public class WebGearTest {

	@Test
	public void test() {
		Application webGear = new WebGear();
		webGear.execute();
	}

}


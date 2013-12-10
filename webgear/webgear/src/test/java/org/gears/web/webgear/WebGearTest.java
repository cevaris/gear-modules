package org.gears.web.webgear;

import static org.junit.Assert.*;

import org.gears.Gear;
import org.junit.Test;

public class WebGearTest {

	@Test
	public void test() {
		Gear webGear = new WebGear();
		webGear.execute();
	}

}

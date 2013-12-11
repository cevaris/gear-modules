package org.gears.web.webgear;


import org.gears.Configuration;
import org.gears.Instance;
import org.junit.Test;


import junit.framework.TestCase;

public class ProductionConfigTest extends TestCase {
	
	
	@Test
	public void testProductionConfig() {
		
		ProductionConfig proConfig = new ProductionConfig();
		Configuration config = Configuration.getInstance();
		
		for(Instance instance : config.getInstances()){
			assert(instance.connect());
		}
		
	}

}

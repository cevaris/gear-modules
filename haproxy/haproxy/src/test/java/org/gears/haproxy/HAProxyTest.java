package org.gears.haproxy;

import static org.junit.Assert.*;

import org.apache.velocity.context.Context;
import org.gears.Configuration;
import org.gears.Gear;
import org.gears.Instance;
import org.gears.template.Templaton;
import org.gears.utils.ResourceUtil;
import org.junit.Test;

public class HAProxyTest {
	
	String CONFIG = ResourceUtil.getResourcePath("haproxy.cfg.vm");
	
	
	@Test
	public void testDynamicContext() {
		
		HAProxyAppTest gear = new HAProxyAppTest();
		Templaton templaton = Templaton.getInstance();
		Context context = Templaton.getContext(gear);
		String document = templaton.render(CONFIG, context).toString();
		assertTrue(document != null);
		assertTrue(document.length() > 0);
		System.out.println(document);
	}
	
//	@Test
//	public void testDynamciRender() {
//		Gear nginx = new NginxGear();
//		nginx.execute();
//		boolean result = nginx.command("cat /tmp/nginx.conf");
//		assertTrue(result);
//	}

}

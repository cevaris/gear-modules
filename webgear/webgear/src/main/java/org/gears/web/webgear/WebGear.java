package org.gears.web.webgear;

import org.apache.log4j.Logger;
import org.gears.Configuration;
import org.gears.Gear;
import org.gears.apache.Apache;
import org.gears.haproxy.HAProxy;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
import org.gears.utils.ResourceUtil;
import org.gears.vim.Vim;


public class WebGear extends Gear {
	
	private static final Logger LOG = Logger.getLogger(WebGear.class);
	
//	public static String INFO = ResourceUtil.getResourcePath("info.php.vm");
	public static String INFO = "info.php.vm";
	
	Gear mysql   = new MySQL();
	Gear haproxy = new HAProxy();
	Gear vim     = new Vim();
	Gear php     = new PHP();
	Gear apache  = new Apache();
	
	public WebGear() {
		new ProductionConfig();
	}

	@Override
	public void execute() {
		
//		install(vim);
//		
		install("web", php);
		install("web", apache);
//		install("web", "-y", "mysql-client php5-mysql" );
		
		install("db", mysql );
		
		renderInfo();
	}
	
	private void renderInfo(){
    	render("web", INFO, "/var/www/info.php");	
	}
}

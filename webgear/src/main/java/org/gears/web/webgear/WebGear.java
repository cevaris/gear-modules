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
	
	ProductionConfig config = new ProductionConfig();
	
	Gear mysql   = new MySQL();
	Gear vim     = new Vim();
	Gear php     = new PHP();
	Gear apache  = new Apache();
	Gear haproxy = new HAPRoxyApp();

	@Override
	public void execute() {
		update();
		
		install(vim);
		
		install("web", php );
		install("web", apache );
		install("web", "-y", "mysql-client php5-mysql" );
		
		install("db", mysql );
		
		renderInfo();
		
		install("lb", haproxy);
		
		restart("web", apache);
	}
	

	private void renderInfo(){
    	render("web", "info.php", "/var/www/info.php");	
	}
	
}

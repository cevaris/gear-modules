package org.gears.web.webgear;

import org.apache.log4j.Logger;
import org.gears.Gear;
import org.gears.apache.Apache;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
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
		// Update all machines
		update();
		
		// Install Vim on all machines		
		install(vim);
		
		// Install web and mysql to web servers
		install("web", php );
		install("web", apache );
		install("web", "-y", "mysql-client php5-mysql" );
		renderInfo();
		
		// Install MySQL to db server
		install("db", mysql );
		
		// Setup load balancer server
		install("lb", haproxy);
		
		// Restart web servers
		restart("web", apache);
	}
	

	private void renderInfo(){
    	render("web", "info.php", "/var/www/info.php");	
	}
	
}

package org.gears.web.webgear;

import org.apache.log4j.Logger;
import org.gears.Configuration;
import org.gears.Application;
import org.gears.Instance;
import org.gears.apache.Apache;
import org.gears.memcached.Memcached;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
import org.gears.vim.Vim;


public class WebGear extends Application {
	
	private static final Logger LOG = Logger.getLogger(WebGear.class);
	
	ProductionConfig config = new ProductionConfig();
	
	Application mysql   = new MySQL();
	Application vim     = new Vim();
	Application php     = new PHP();
	Application apache  = new Apache();
	Application haproxy = new HAPRoxyApp();
	Application memcached = new MemcachedApp();

	@Override
	public void execute() {
		
		// Update all machines
//		update();
//		
//		// Install Vim on all machines		
//		install(vim);
//		
//		// Install web and mysql to web servers
//		install( "web", php );
//		install("web", apache );
//		install("web", "-y", "mysql-client php5-mysql" );
//		renderInfo();
//		
//		// Install MySQL to db server
		install("db", mysql );
//		
//		// Setup load balancer server
//		install("lb", haproxy);
		
//		install("cache", memcached);
//		install("web", "php5-memcached");
		
		// Restart web servers
//		restart("web", apache);
	}
	

	private void renderInfo(){
    	render("web", "info.php", "/var/www/html/info.php");	
	}
	
}

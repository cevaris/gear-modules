package org.gears.web.webgear;

import org.apache.log4j.Logger;
import org.gears.Configuration;
import org.gears.Application;
import org.gears.GearApplication;
import org.gears.Instance;
import org.gears.Gear;
import org.gears.Service;
import org.gears.apache.Apache;
import org.gears.memcached.Memcached;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
import org.gears.template.Templaton;
import org.gears.vim.Vim;


public class WebGear extends Gear {
	
	private static final Logger LOG = Logger.getLogger(WebGear.class);
	
	ProductionEnv env    = new ProductionEnv();
	
//	Application mysql   = new MySQL();
	Application vim     = new Vim();
	Application php     = new PHP();
	Application apache  = new Apache();
//	Application haproxy = new HAPRoxyApp();
//	Application memcached = new MemcachedApp();

	@Override
	public void execute() {
		
//		update("web");
		
		// Install Vim to all machines		
		install("web", vim );
		
		install("web", php );
		install("web", apache );
		
		render("info.php", "/var/www/html/info.php");
		
		
		// Install web and mysql to web servers
//		for(Instance instance : config.getInstances("web")){
//			instance.install( php );
//			instance.install( apache );
//			instance.install( "mysql-client php5-mysql" );
//			instance.render("info.php", "/var/www/html/info.php", Templaton.getContext(this));
//			instance.install("php5-memcached");
//		}
//		
//		for(Instance instance : config.getInstances("db")){
//			instance.install(mysql);
//		}
//		
//		for(Instance instance : config.getInstances("lb")){
//			instance.install(haproxy);
//		}
//		
//		for(Instance instance : config.getInstances("cache")){
//			instance.install(memcached);
//		}
//		
//		for(Instance instance : config.getInstances("web")){
//			instance.service(apache, Service.RESTART);
//		}
		
	}

	

	

	
}

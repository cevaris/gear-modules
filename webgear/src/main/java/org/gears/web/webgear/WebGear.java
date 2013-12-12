package org.gears.web.webgear;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.gears.Application;
import org.gears.Gear;
import org.gears.Service;
import org.gears.System;
import org.gears.apache.Apache;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
import org.gears.vim.Vim;


public class WebGear extends Gear {
	
	private static final Logger LOG = Logger.getLogger(WebGear.class);
	
	ProductionEnv env   = new ProductionEnv();
	
	Application mysql   = new MySQL();
	Application vim     = new Vim();
	Application php     = new PHP();
	Application apache  = new Apache();
	Application haproxy = new HAPRoxyApp();
	Application memcached = new MemcachedApp();

	@Override
	public void execute() {
		
//		update();
		
		// Install Vim to all machines		
//		install( vim );
//		
//		install("web", php );
//		install("web", apache );
//		install("web", getMySQLClientContext());
		render ("web", "info.php", getRenderInfoContext());
		
//		install("db", mysql);
//		
//		install("cache", memcached);
//
//		install("web", getMemcachedContext());
//		
		install("lb", haproxy);

		service("web", apache, Service.RESTART);
		
		
	}
	
	
	private HashMap<System, Object> getMemcachedContext() {
		HashMap<System, Object> context = new HashMap<System, Object>();
		
		context.put(System.DEBIAN,  "php5-memcached" );
		context.put(System.RED_HAT, "php-pecl-memcache" );
		
		return context;
	}
	
	private HashMap<System, Object> getRenderInfoContext() {
		HashMap<System, Object> context = new HashMap<System, Object>();
		
		context.put(System.DEBIAN,  "/var/www/info.php" );
		context.put(System.RED_HAT, "/var/www/html/info.php" );
		
		return context;
	}


	private HashMap<System, Object> getMySQLClientContext() {
		HashMap<System, Object> context = new HashMap<System, Object>();
		
		context.put(System.DEBIAN,  "mysql-client php5-mysql" );
		context.put(System.RED_HAT, "mysql php-mysql" );
		
		return context;
		
//		for(Instance instance : Configuration.getInstance().getInstances("web")){
//			switch(instance.getSystem()){
//			case RED_HAT:
//				instance.install("mysql php-mysql");
//			case DEBIAN:
//				instance.install("mysql-client php5-mysql");
//			}
//		}
	}


	

	
}

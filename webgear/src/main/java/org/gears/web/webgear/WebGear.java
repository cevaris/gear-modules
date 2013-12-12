package org.gears.web.webgear;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.gears.Gear;
import org.gears.GearApplication;
import org.gears.Service;
import org.gears.System;
import org.gears.apache.Apache;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
import org.gears.vim.Vim;


public class WebGear extends GearApplication {
	
	static final Logger LOG = Logger.getLogger(WebGear.class);
	
	ProductionEnv env   = new ProductionEnv();
	
	Gear mysql   = new MySQL();
	Gear vim     = new Vim();
	Gear php     = new PHP();
	Gear apache  = new Apache();
	Gear haproxy = new HAPRoxyApp();
	Gear memcached = new MemcachedApp();

	@Override
	public void execute() {
		
//		update();
		
		install( vim );
		
		install("web", php );
		install("web", apache );
		install("web", getMySQLClient());
		render ("web", "info.php", getRenderInfo());
		
		install("db", mysql);
		
		install("cache", memcached);
		install("web", getMemcached());
		
		install("lb", haproxy);

		service("web", apache, Service.RESTART);

	}
	
	

	private HashMap<System, Object> getMemcached() {
		HashMap<System, Object> context = new HashMap<System, Object>();
		
		context.put(System.DEBIAN,  "php5-memcached" );
		context.put(System.RED_HAT, "php-pecl-memcache" );
		
		return context;
	}
	
	private HashMap<System, Object> getRenderInfo() {
		HashMap<System, Object> context = new HashMap<System, Object>();
		
		context.put(System.DEBIAN,  "/var/www/info.php" );
		context.put(System.RED_HAT, "/var/www/html/info.php" );
		
		return context;
	}


	private HashMap<System, Object> getMySQLClient() {
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

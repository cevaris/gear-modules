package org.gears.web.webgear;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.gears.Configuration;
import org.gears.Application;
import org.gears.GearApplication;
import org.gears.Instance;
import org.gears.Gear;
import org.gears.Service;
import org.gears.System;
import org.gears.apache.Apache;
import org.gears.memcached.Memcached;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
import org.gears.template.Templaton;
import org.gears.vim.Vim;


public class WebGear extends Gear {
	
	private static final Logger LOG = Logger.getLogger(WebGear.class);
	
	ProductionEnv env    = new ProductionEnv();
	
	Application mysql   = new MySQL();
	Application vim     = new Vim();
	Application php     = new PHP();
	Application apache  = new Apache();
	Application haproxy = new HAPRoxyApp();
	Application memcached = new MemcachedApp();

	@Override
	public void execute() {
		
//		update("web");
		
		// Install Vim to all machines		
		install( vim );
		
		install("web", php );
		install("web", apache );
		install("web", getMySQLClientContext());
		render ("web", "info.php", "/var/www/html/info.php");
		
		install("db", mysql);
		
		install("cache", memcached);

		install("web", "php5-memcached php-pecl-memcache");
		
		install("lb", haproxy);
		
		service("web", apache, Service.RESTART);
		
		
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

package org.gears.mysql;

import org.apache.log4j.Logger;
import org.gears.Application;
import org.gears.System;

public class MySQL extends Application {
	
	private static final Logger LOG = Logger.getLogger(MySQL.class);
	
	/**
	 * For MySQL config file
	 * http://stackoverflow.com/questions/1167056/optimal-mysql-configuration-my-cnf
	 */
	public static String MYSQL_USER = "root";
	public static String MYSQL_PASS = "mypass";
	
	public static String port = "3306";
	public static String ipAddress = "127.0.0.1";
	
	public static String getPort() {
		return port;
	}
	public static String getIpAddress() {
		return ipAddress;
	}

	@Override
	public void execute() {
//		update();
		
		// Hack for automating Mysql install
		command(String.format("echo mysql-server-5.5 mysql-server/root_password password %s | debconf-set-selections", MYSQL_PASS));
		command(String.format("echo mysql-server-5.5 mysql-server/root_password_again password %s | debconf-set-selections", MYSQL_PASS));
		
		// Install misc apps
		install( "-y", "mysql-server php5-mysql php5 php5-mcrypt" );
		
		if(isSystem(System.RED_HAT)){
			install( "-y", "php-mysql php-pear" );
		}
		
		renderConfig();
		
		// Grant Remote access
		command("mysql -u root --password='mypass' -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.2.%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
		command("mysql -u root --password='mypass' -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'nod%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
		
		openPort("3306");
		
		restart(this);
	}
	
	@Override
	public String toString() {
		return "mysql";
	}
	
	private void renderConfig(){
		render("my.cnf.vm", "/etc/mysql/my.cnf");
	}
	
	

}

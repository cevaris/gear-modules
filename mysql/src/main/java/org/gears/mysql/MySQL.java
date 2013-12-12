package org.gears.mysql;

import org.apache.log4j.Logger;
import org.gears.GearModule;
import org.gears.Service;
import org.gears.System;

public class MySQL extends GearModule {
	
	public String ipAddress = "127.0.0.1";
	public String port 	    = "3306";
	public String dataDir   = "/var/lib/mysql";
	public String socket    = "/var/lib/mysql/mysql.sock";
	public String user      = "mysql";
	public String logError  = "/var/log/mysqld.log";
	public String pidFile   = "/var/run/mysqld/mysqld.pid";
	
	static final Logger LOG = Logger.getLogger(MySQL.class);
	
	public String MYSQL_USER = "root";
	public String MYSQL_PASS = "mypass";
	
	public String getPort() {
		return port;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public String getDataDir() {
		return dataDir;
	}
	public String getUser() {
		return user;
	}
	public String getLogError() {
		return logError;
	}
	public String getPidFile() {
		return pidFile;
	}

	@Override
	public void execute() {

		// Hack for automating Mysql install
		if(isSystem(System.DEBIAN)){
			command(String.format("echo mysql-server-5.5 mysql-server/root_password password %s | debconf-set-selections", MYSQL_PASS));
			command(String.format("echo mysql-server-5.5 mysql-server/root_password_again password %s | debconf-set-selections", MYSQL_PASS));
		}
		
		
		if(isSystem(System.RED_HAT)){
			install("mysql-server mysql php-mysql" );
		} else if(isSystem(System.DEBIAN)){
			install("mysql-server php5-mysql php5 php5-mcrypt" );
		}
		
		// Install misc apps
		install("mysql-server mysql php5-mysql php5 php5-mcrypt php-mysql" );
		
		if(isSystem(System.RED_HAT)){
			install( "php-mysql php-pear" );
		}
		
		renderConfig();
		
		// Grant Remote access
		
		openPort("3306");
		
		if(isSystem(System.RED_HAT)){
			service("mysqld", Service.RESTART);
			
		} else if(isSystem(System.DEBIAN)){
			service("mysql", Service.RESTART);
		}
		
		
		if(isSystem(System.RED_HAT)){
			command("mysql -u root -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.2.%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
			command("mysql -u root -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'node%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
		} else if(isSystem(System.DEBIAN)){
			command("mysql -u root --password='mypass' -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.2.%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
			command("mysql -u root --password='mypass' -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'node%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
		}
		
	}
	
	private void renderConfig(){
		if(isSystem(System.RED_HAT)){
			render("redhat.my.cnf.vm", "/etc/my.cnf");
		} else if(isSystem(System.DEBIAN)){
			render("debian.my.cnf.vm", "/etc/mysql/my.cnf");
		}
	}
	
	
	@Override
	public String toString() {
		if(isSystem(System.DEBIAN)){
			return "mysql";
		} else if(isSystem(System.RED_HAT)){
			return "mysqld";
		} else {
			return null;
		}
	}
	

}

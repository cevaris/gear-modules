package org.gears.mysql;

import org.gears.Gear;

public class MySQL extends Gear {
	
	public static String TEST_RESOURCES = "src/test/java/resources/";
	public static String MY_CNF = TEST_RESOURCES + "my.cnf.vm";
	
	/**
	 * For MySQL config file
	 * http://stackoverflow.com/questions/1167056/optimal-mysql-configuration-my-cnf
	 */
	public static final String MYSQL_PASS = "mypass";
	public static final String MYSQL_USER = "root";
	
	public static final String PORT = "3306";
	public static final String IP_ADDRESS = "192.168.2.101";

	@Override
	public void execute() {
		update();
		
		// Hack for automating Mysql install
		command(String.format("echo mysql-server-5.5 mysql-server/root_password password %s | debconf-set-selections", MYSQL_PASS));
		command(String.format("echo mysql-server-5.5 mysql-server/root_password_again password %s | debconf-set-selections", MYSQL_PASS));
		
		// Install misc apps
		install( "-y", "mysql-server php5-mysql php5 php5-mcrypt" );
		
		renderConfig();
		
		// Grant Remote access
		command("mysql -u root --password='mypass' -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.2.%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
		command("mysql -u root --password='mypass' -e \"GRANT ALL PRIVILEGES ON *.* TO 'root'@'nod%' IDENTIFIED BY 'mypass' WITH GRANT OPTION; FLUSH PRIVILEGES;\"");
		
		openPort("3306");
		
		restart("mysql");
		
	}

	private void renderConfig(){
		render(MY_CNF, "/etc/mysql/my.cnf");
	}

}

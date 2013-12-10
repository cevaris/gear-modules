package org.gears.web.webgear;

import org.gears.Gear;
import org.gears.apache.Apache;
import org.gears.haproxy.HAProxy;
import org.gears.mysql.MySQL;
import org.gears.php.PHP;
import org.gears.vim.Vim;


public class WebGear extends Gear {
	
	public static String TEST_RESOURCES = "src/test/java/resources/";
	public static String INFO = TEST_RESOURCES + "info.php.vm";
	
	Gear mysqlServer = new MySQL();
	Gear haproxy = new HAProxy();
	Gear vim    = new Vim();
	Gear php    = new PHP();
	Gear apache = new Apache();
	
	public WebGear() {
		new ProductionConfig();
	}

	@Override
	public void execute() {
		
		install(vim);
		
		install("web", php);
		install("web", apache);
		install("web", "-y", "mysql-client php5-mysql" );
		
		install("db", mysqlServer);
		
		renderInfo();
	}
	
	private void renderInfo(){
    	render("web", INFO, "/var/www/info.php");	
	}
}

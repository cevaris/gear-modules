<?php
mysql_connect("192.168.2.102:3306", "root", "mypass") or die(mysql_error()) ;
mysql_select_db("mysql") or die(mysql_error()) ;
echo "<h1>Got MySQL successful connection to 192.168.2.102:3306</h1>";

echo "<hr/>";

echo "Server IP: ".$_SERVER['SERVER_ADDR']."<br/>";
echo "Client IP: ".$_SERVER['REMOTE_ADDR']."<br/>";
echo "X-Forwarded-for: ".$_SERVER['HTTP_X_FORWARDED_FOR']."<br/>";


echo "<hr/>";

$m = new Memcached() or new Memcache();
$m->addServer('192.168.2.104', 11211);

$n = $m->get('counter');
if($n == false){
   $n = 0;
}
$m->set('counter', $n+1);
echo "Memached Counter: $n <br/>";
?>
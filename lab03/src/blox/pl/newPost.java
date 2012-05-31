/*
 * Napisz funkcjê, która dopisuje nowy wpis do bloga o identyfikatorze blogid.
 */

package blox.pl;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class newPost {
public static void main(String [] args) throws Exception {
		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://www.blox.pl/xmlrpc"));
		config.setEnabledForExceptions(true);
		config.setConnectionTimeout(60 * 1000);
		
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		Object[] os = new Object[] {
					  new String ( "" ),			//appkey - ignorowany
					  new String ( "818077" ),		//blogid - nazwa bloga, do którego bêdzie wys³ana nowa notka, np. dla bloga o adresie xyz.blox.pl bêdzie to xyz (wycofywane) lub numer bloga pobrany metod¹ getUsersBlogs (zalecane)
					  new String ( "psemek_pcz" ),	//username - login w³aœciciela/administratora/u¿ytkownika bloga (ale nie czytelnika)
					  new String ( "boleckolec" ),	//password - has³o u¿ytkownika
					  new String ( "818077" ),		//content - treœæ notki
					  new Boolean( true )			//publish - czy wpis ma byæ opublikowany (true) czy zapisany jako szkic (false)
		};
		
		/*
		 * zwraca identyfikator nowego wpisu lub b³¹d wywo³ania procedury zgodny ze specyfikacj¹ XML-RPC
		 */
		
		Object result = (Object) client.execute("blogger.newPost", os);
		System.out.println("Dodano post o identyfikatorze: " + result);
	}
}

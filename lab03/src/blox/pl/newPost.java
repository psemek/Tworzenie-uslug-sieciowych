/*
 * Napisz funkcj�, kt�ra dopisuje nowy wpis do bloga o identyfikatorze blogid.
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
					  new String ( "818077" ),		//blogid - nazwa bloga, do kt�rego b�dzie wys�ana nowa notka, np. dla bloga o adresie xyz.blox.pl b�dzie to xyz (wycofywane) lub numer bloga pobrany metod� getUsersBlogs (zalecane)
					  new String ( "psemek_pcz" ),	//username - login w�a�ciciela/administratora/u�ytkownika bloga (ale nie czytelnika)
					  new String ( "boleckolec" ),	//password - has�o u�ytkownika
					  new String ( "818077" ),		//content - tre�� notki
					  new Boolean( true )			//publish - czy wpis ma by� opublikowany (true) czy zapisany jako szkic (false)
		};
		
		/*
		 * zwraca identyfikator nowego wpisu lub b��d wywo�ania procedury zgodny ze specyfikacj� XML-RPC
		 */
		
		Object result = (Object) client.execute("blogger.newPost", os);
		System.out.println("Dodano post o identyfikatorze: " + result);
	}
}

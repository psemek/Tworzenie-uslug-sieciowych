/*
 * Spróbuj edytowaæ post.
 */

package blox.pl;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class editPost {
public static void main(String [] args) throws Exception {
		
		//pobieranie aktualnej daty
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://www.blox.pl/xmlrpc"));
		config.setEnabledForExceptions(true);
		config.setConnectionTimeout(60 * 1000);
		
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		Object[] os = new Object[] {
					  new String ( "" ),						//appkey - ignorowany
					  new String ( "7767545" ),					//postid - identyfikator liczbowy modyfikowanej notki
					  new String ( "psemek_pcz" ),				//username - login w³aœciciela/administratora bloga lub autora notki
					  new String ( "boleckolec" ),				//password - has³o u¿ytkownika
					  new String ( dateFormat.format(date) ),	//content - treœæ notki
					  new Boolean( true )						//publish - czy wpis ma byæ opublikowany (true) czy zapisany jako szkic (false)
		};
		
		/*
		 * zwraca true jeœli notka zosta³a pomyœlnie zmodyfikowana, false w przeciwnym wypadku lub b³¹d wywo³ania procedury zgodny ze specyfikacj¹ XML-RPC
		 */
		
		Object result = (Object) client.execute("blogger.editPost", os);
		result = result.toString().toUpperCase();
		System.out.println("Post o identyfikatorze 7767545 zosta³ edytowany z wynikiem: " + result);
		System.out.println(dateFormat.format(date));
	}
}

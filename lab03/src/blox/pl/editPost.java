/*
 * Spr�buj edytowa� post.
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
					  new String ( "psemek_pcz" ),				//username - login w�a�ciciela/administratora bloga lub autora notki
					  new String ( "boleckolec" ),				//password - has�o u�ytkownika
					  new String ( dateFormat.format(date) ),	//content - tre�� notki
					  new Boolean( true )						//publish - czy wpis ma by� opublikowany (true) czy zapisany jako szkic (false)
		};
		
		/*
		 * zwraca true je�li notka zosta�a pomy�lnie zmodyfikowana, false w przeciwnym wypadku lub b��d wywo�ania procedury zgodny ze specyfikacj� XML-RPC
		 */
		
		Object result = (Object) client.execute("blogger.editPost", os);
		result = result.toString().toUpperCase();
		System.out.println("Post o identyfikatorze 7767545 zosta� edytowany z wynikiem: " + result);
		System.out.println(dateFormat.format(date));
	}
}

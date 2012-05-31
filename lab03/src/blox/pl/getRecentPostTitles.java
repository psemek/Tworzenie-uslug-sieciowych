/*
 * Napisz funkcj�, kt�ra podaje wszystkie posty wpisane przez u�ytkownika
 */

package blox.pl;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class getRecentPostTitles {
public static void main(String [] args) throws Exception {
		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://www.blox.pl/xmlrpc"));
		config.setEnabledForExceptions(true);
		config.setConnectionTimeout(60 * 1000);
		
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		Object[] os = new Object[] {
					  new String ( "818077" ),		//blogid - nazwa bloga (wycofywane) lub identyfikator liczbowy bloga pobrany metod� getUsersBlogs (zalecane)
					  new String ( "psemek_pcz" ),	//username - login w�a�ciciela/administratora/u�ytkownika bloga (ale nie czytelnika)
					  new String ( "boleckolec" ),	//password - has�o u�ytkownika
					  new Integer( -1 )				//numberOfPosts - liczba notek, kt�re serwer ma zwr�ci�, w kolejno�ci od ostatnio dodanej. Dla warto�ci -1 zostan� zwr�cone tytu�y wszystkich notek bloga
		};
		
		Object[] result = (Object[]) client.execute("mt.getRecentPostTitles", os);
		
		/*
		 * zwraca list� notek jako list� tablic z warto�ciami przypisanymi do kluczy:
		 * dateCreated - data utworzenia notki
		 * userid - login autora
		 * postid - identyfikator liczbowy notki
		 * title - tytu� notki
		*/
		
		for(int i=0; i < result.length; i++) {
			HashMap<?, ?> listaNotatek = (HashMap<?, ?>) result[i];
			Iterator<?> iterator = listaNotatek.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				System.out.println(key + " - " + listaNotatek.get(key));
			}
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		}
		System.out.println("Liczba wszystkich post�w: " + result.length);
	}
}

/*
 * Napisz serwet, który generuje dynamicznie raty kredytu w poszczególnych miesi¹cach. Jako parametry przyjmowane s¹ dane:
 * • miesi¹ce – liczba miesiêcy, w trakcie których brany jest kredyt,
 * • kwota – kwota kredytu,
 * • oprocentowanie – oprocentowanie kredytu,
 * • rata – rodzaj raty.
 * Klasa, w której zapisany jest algorytm obliczaj¹cy ratê
 * 
 * Zadanie polega na napisaniu serwletu, który zwraca tablicê HTML w postaci:
 * <table>
 * <tr>
 * <th> Lp. </th>
 * <th> Data </th>
 * <th> Oprocentowanie </th>
 * <th> Kurs </th>
 * <th> Rata kapita³owa </th>
 * <th> Rata odsetki </th>
 * <th> Suma raty </th>
 * <th> Kapita³ do sp³aty </th>
 * </tr>
 * <tr>
 * <td> … </td>
 * …
 * </tr>
 * </table>
 * 
 * Do obliczeñ nale¿y skorzystaæ z klas Kalkulator oraz RatyKalendarz.
 * Kalkulator k1 = new Kalkulator(150000, 240, 6.00, 1, 0, 0, "PLN" );
 * RatyKalendarz rkMalejace = k1.getRataKalendarzRatyMalejace();
 * RatyKalendarz rkStale = k1.getRataKalendarzRatyStale();
 * Parametry przyjmowane przez konstruktor Kalkulator:
 * • 150000 - kwota kredytu,
 * • 240 - liczba miesiêcy,
 * • 6.00 - oprocentowanie,
 * • 1 - kurs waluty (nieu¿ywane – domyœlnie wstawiæ 1),
 * • 0 - spreadZakupowy (domyœlnie 0),
 * • 0 - spreadSprzeda¿owy (domyœlnie 0),
 * • "PLN" - kodWaluty (domyœlnie „PLN”).
 * Pliki *.java nale¿y dodaæ do katalogu src, a nastêpnie odœwie¿yæ zawartoœæ projektu (nazwa projektu, prawy przycisk myszy, Refresh).
 * Przyk³ad – fragment programu oblicza raty (pierwsze dwie linijki), a nastêpnie wyœwietla wszystkie raty.
 * 
 * Kalkulator k1 = new Kalkulator(150000, 240, 6.00, 1, 0, 0, "PLN" );
 * RatyKalendarz rk = k1.getRataKalendarzRatyMalejace();
 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 * NumberFormat format = NumberFormat.getNumberInstance();
 * format.setMinimumFractionDigits(2);
 * format.setMaximumFractionDigits(2);
 * System.out.println("Lp. Data Oprocentowanie Kurs Rata_kapitalowa Rata_odsetki Suma_raty Kapital_do_splaty ");
 * for (int i=0; i<rk.getRaty().size(); i++) {
 * Rata rata = rk.getRaty().get(i);
 * String s = i+" ";
 * s += dateFormat.format(rata.getData()) + " \t";
 * s += format.format(rata.getOprocentowanie())+"% \t";
 * s += format.format(rata.getKurs())+" \t";
 * s += format.format(rata.getRataKapitalowa())+"\t";
 * s += format.format(rata.getOdsetki())+" \t";
 * s += format.format(rata.getSumaRaty())+" \t";
 * s += format.format(rata.getKapitalDoSplaty());
 * System.out.println(s);
 * }
 * System.out.println("------------\r\n");
 * System.out.println("Suma rata kapitalowa: " + format.format(rk.getSumaRataKapitalowa()));
 * System.out.println("Suma odsetek: " + format.format(rk.getSumaOdsetki()));
 * System.out.println("Suma rat: "+format.format(rk.getSumaRat()));
 * System.out.println("Koszt kredytu: "+format.format(rk.getKosztKredytu()));
 */

package zad2;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletKalkulator")
public class servletKalkulator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Kalkulator k1 = new Kalkulator(150000, 240, 6.00, 1, 0, 0, "PLN" );
		Kalkulator k1 = new Kalkulator(Double.parseDouble(request.getParameter("param1")),
									   Integer.parseInt(request.getParameter("param2")),
									   Double.parseDouble(request.getParameter("param3")),
									   Double.parseDouble(request.getParameter("param4")),
									   Double.parseDouble(request.getParameter("param5")),
									   Double.parseDouble(request.getParameter("param6")),
									   					  request.getParameter("param7"));
		RatyKalendarz rk = k1.getRataKalendarzRatyMalejace();
		//RatyKalendarz rkStale = k1.getRataKalendarzRatyStale();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Zadanie 2</TITLE> </HEAD>");
		out.println("<H1 ALIGN=CENTER>Zadanie 2</H1>");	
		out.println("<TABLE BORDER=1 ALIGN=CENTER>");
		out.println("<TR BGCOLOR=\"#FFAD00\">");
		out.println("<TH>Lp.</TH><TH>Data</TH><TH>Oprocentowanie</TH><TH>Kurs</TH><TH>Rata kapitalowa</TH><TH>Rata odsetki</TH><TH>Suma raty</TH><TH>Kapital do splaty</TH></TR>");

		for (int i=0; i<rk.getRaty().size(); i++) {
			Rata rata = rk.getRaty().get(i);
			int k = i + 1;
			out.println("<TR>");
			out.println("<TD><CENTER>" + k + "</CENTER></TD>");
			out.println("<TD><CENTER>" + dateFormat.format(rata.getData()) + "</CENTER></TD>");
			out.println("<TD><CENTER>" + format.format(rata.getOprocentowanie()) + "</CENTER></TD>");
			out.println("<TD><CENTER>" + format.format(rata.getKurs()) + "</CENTER></TD>");
			out.println("<TD><CENTER>" + format.format(rata.getRataKapitalowa()) + "</CENTER></TD>");
			out.println("<TD><CENTER>" + format.format(rata.getOdsetki()) + "</CENTER></TD>");
			out.println("<TD><CENTER>" + format.format(rata.getSumaRaty()) + "</CENTER></TD>");
			out.println("<TD><CENTER>" + format.format(rata.getKapitalDoSplaty()) + "</CENTER></TD>");
			out.println("</TR>");
		}
		out.println("</TABLE></HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

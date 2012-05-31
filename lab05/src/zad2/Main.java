package zad2;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;





public class Main {

	
	static public void main(String [] args) {
		Kalkulator k1 = new Kalkulator(150000, 240, 6.00, 1, 0, 0, "PLN" );
		RatyKalendarz rk = k1.getRataKalendarzRatyMalejace();
		//RatyKalendarz rk2 = k1.getRataKalendarzRatyStale();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		System.out.println("Lp. Data Oprocentowanie Kurs Rata_kapitalowa Rata_odsetki Suma_raty Kapital_do_splaty ");
		for (int i=0; i<rk.getRaty().size(); i++) {
			Rata rata = rk.getRaty().get(i);
			String s = i+" ";
			s += dateFormat.format(rata.getData()) + " \t";
			s += format.format(rata.getOprocentowanie())+"% \t";
			s += format.format(rata.getKurs())+" \t";
			s += format.format(rata.getRataKapitalowa())+" \t"+format.format(rata.getOdsetki())+" \t";
			s += format.format(rata.getSumaRaty())+" \t";
			s += format.format(rata.getKapitalDoSplaty());
			System.out.println(s);
		}
		System.out.println("------------\r\n");
		System.out.println("Suma rata kapitalowa: " + format.format(rk.getSumaRataKapitalowa()));
		System.out.println("Suma odsetek: " + format.format(rk.getSumaOdsetki()));
		System.out.println("Suma rat: "+format.format(rk.getSumaRat()));
		System.out.println("Koszt kredytu: "+format.format(rk.getKosztKredytu()));
	}
}

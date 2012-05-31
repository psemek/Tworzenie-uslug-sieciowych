package zad2;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;




public class RatyKalendarz {

	private List<Rata> raty;
	private double kwotaKredytu;
		
	public RatyKalendarz() {
		raty = new LinkedList<Rata>();
	}
	
	
	public List<Rata> getRaty() {
		return raty;
	}

	public void setRaty(List<Rata> raty) {
		this.raty = raty;
	}
	
	public double getKwotaKredytu() {
		return kwotaKredytu;
	}


	public void setKwotaKredytu(double kwotaKredytu) {
		this.kwotaKredytu = kwotaKredytu;
	}


	public double getSumaRataKapitalowa() {
		double suma = 0;
		for (Rata rata : raty ) {
			suma += rata.getRataKapitalowa();
		}
		return suma;
	}
	
	public double getSumaOdsetki() {
		double suma = 0;
		for (Rata rata : raty  ) {
			suma += rata.getOdsetki();
		}
		return suma;
	}
	
	public double getSumaRat() {
		double suma = 0;
		for (Rata rata : raty  ) {
			suma += rata.getSumaRaty();
		}
		return suma;
	}
	
	public double getKosztKredytu() {
		return getSumaRat()-getKwotaKredytu();
	}
	
	
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		String s="Lp. Data Oprocentowanie Kurs Rata_kapitalowa Rata_odsetki Suma_raty Kapital_do_splaty ";
		for (int i=0; i<raty.size(); i++) {
			Rata rata = raty.get(i);
			s += i+" ";
			s += dateFormat.format(rata.getData()) + " \t";
			s += format.format(rata.getOprocentowanie())+"% \t";
			s += format.format(rata.getKurs())+" \t";
			s += format.format(rata.getRataKapitalowa())+" \t"+format.format(rata.getOdsetki())+" \t";
			s += format.format(rata.getSumaRaty())+" \t";
			s += format.format(rata.getKapitalDoSplaty())+" \t";
			s += rata.getDaysPeriod()+"\t"+rata.getDaysPerYear();
			s += "\r\n";
		}
		s += "------------\r\n";
		s += "\t\t" + format.format(getSumaRataKapitalowa())+ "\t" + format.format(getSumaOdsetki()) +"\t";
		s += format.format(getSumaRat())+"";
		s += "\r\n"+format.format(getKosztKredytu())+"\r\n";
		return s;
	}
}

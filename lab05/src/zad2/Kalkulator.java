package zad2;

import java.util.Calendar;




public class Kalkulator {

			
	private int month;
	//private boolean ubezpieczenie; 
	//private boolean nockout = true;
	private double kwotaKredytu;
	private double oprOprocentowanie = 4.54;
	//private double prowProwizja = 0.0;
	//private double ubezStawka = 0.0;
	private Double rata;
	private double kursWaluty = 2.26;
	private double spreadZakupowy = 0.02;
	private double spreadSprzedazowy = 0.02;
	private String waluta = "CHF";
	
	

	public Kalkulator(double kwotaKredytu, int month ) {
		this.kwotaKredytu = kwotaKredytu;
		this.month = month;
	}
	
	public Kalkulator(double kwotaKredytu, int month, double oprOprocentowanie, double kursWaluty, double spreadZakupowy, double spreadSprzedazowy, String waluta) {
		this.kwotaKredytu = kwotaKredytu;
		this.month = month;
		this.oprOprocentowanie = oprOprocentowanie;
		this.kursWaluty = kursWaluty;
		this.spreadSprzedazowy = spreadSprzedazowy;
		this.spreadZakupowy = spreadZakupowy;
		this.waluta = waluta;
	}
	
	
	public double getRata() {
		if (rata==null) {
			rata = takeRata();
		}
		return rata;
	}

	public void setRata(double rata) {
		this.rata = rata;
	}

	


	
	public RatyKalendarz getRataKalendarzRatyMalejace() {
		double wartoscKursuZakupowego = kursWaluty - spreadZakupowy*kursWaluty; 
		double wartoscKursuSprzedazowego = kursWaluty + spreadSprzedazowy*kursWaluty;
		
		RatyKalendarz ratyKalendarz = new RatyKalendarz();
		ratyKalendarz.setKwotaKredytu( kwotaKredytu );
		int okres = month;
		/////////////////////////////////////////double exchRate = 1;	//pobrac kurs z NBP! 
		
		double kwotaBruttoII = kwotaKredytu / wartoscKursuZakupowego;
		double oprocentowanie = oprOprocentowanie/100.0; // / wartoscKursuZakupowego;
		double kwotaDoSpalty = kwotaBruttoII;
		double rataKapitalowa = kwotaBruttoII/okres;
		
		for (int i=0; i<okres; i++) {
			Calendar payTime = Calendar.getInstance();
			Calendar last = Calendar.getInstance();
			payTime.add(Calendar.MONTH, i+1);
			last.add(Calendar.MONTH, i);
			long daysBetween = DateKalkulator.daysBetween(payTime, last);
			
			last.add(Calendar.MONTH, 1);
			last.set(Calendar.MONTH, 11);
			last.set(Calendar.DATE, 31);
			long dayPerYear = last.get(Calendar.DAY_OF_YEAR);
			
			
			double rataOdsetkowa = kwotaDoSpalty*daysBetween*oprocentowanie/dayPerYear;
			
			Rata r = new Rata();
			r.setRataKapitalowa(rataKapitalowa * wartoscKursuSprzedazowego);
			r.setOdsetki(rataOdsetkowa * wartoscKursuSprzedazowego);
			r.setKapitalDoSplaty(kwotaDoSpalty * wartoscKursuSprzedazowego);
			r.setData(payTime.getTime());
			r.setDaysPeriod(daysBetween);
			r.setDaysPerYear(dayPerYear);
			r.setCurrencyName( waluta );
			r.setOprocentowanie( oprocentowanie*100 );
			r.setKurs(kursWaluty);
			ratyKalendarz.getRaty().add(r);
			
			kwotaDoSpalty = kwotaDoSpalty - rataKapitalowa;
		}

		return ratyKalendarz;
	}


	
	
	
	public RatyKalendarz getRataKalendarzRatyStale() {
		double wartoscKursuZakupowego = kursWaluty - spreadZakupowy*kursWaluty; 
		double wartoscKursuSprzedazowego = kursWaluty + spreadSprzedazowy*kursWaluty;
		
		RatyKalendarz ratyKalendarz = new RatyKalendarz();
		ratyKalendarz.setKwotaKredytu( kwotaKredytu );
		
		int okres = month;
		////////////////////////////////////////double exchRate = 1;	//pobrac kurs z NBP! 

		double kwotaBruttoII = kwotaKredytu / wartoscKursuZakupowego;
		double rataRowna = getRata();
		double oprocentowanie = oprOprocentowanie/100.0; // / wartoscKursuZakupowego;
		double kwotaDoSpalty = kwotaBruttoII;
		
		for (int i=0; i<okres; i++) {
			Calendar payTime = Calendar.getInstance();
			Calendar last = Calendar.getInstance();
			payTime.add(Calendar.MONTH, i+1);
			last.add(Calendar.MONTH, i);
			long daysBetween = DateKalkulator.daysBetween(payTime, last);
			
			last.add(Calendar.MONTH, 1);
			last.set(Calendar.MONTH, 11);
			last.set(Calendar.DATE, 31);
			long dayPerYear = last.get(Calendar.DAY_OF_YEAR);
			
			
			double rataOdsetkowa = kwotaDoSpalty*daysBetween*oprocentowanie/dayPerYear;
			double rataKapitalowa = rataRowna - rataOdsetkowa;
			
			
			Rata r = new Rata();
			r.setRataKapitalowa(rataKapitalowa * wartoscKursuSprzedazowego);
			r.setOdsetki(rataOdsetkowa  * wartoscKursuSprzedazowego);
			r.setKapitalDoSplaty(kwotaDoSpalty * wartoscKursuSprzedazowego);
			r.setData(payTime.getTime());
			r.setDaysPeriod(daysBetween);
			r.setDaysPerYear(dayPerYear);
			r.setCurrencyName( waluta );
			ratyKalendarz.getRaty().add(r);
			
			kwotaDoSpalty = kwotaDoSpalty - rataKapitalowa;
		}

		return ratyKalendarz;
	}

	protected double takeRata() {		
		double wartoscKursuZakupowego = kursWaluty - spreadZakupowy*kursWaluty; 
		double wartoscKursuSprzedazowego = kursWaluty + spreadSprzedazowy*kursWaluty;
		
		int okres = month;
		/////////////////////////////////////////double exchRate = 1;	//pobrac kurs z NBP! 

		double kwotaBruttoII = kwotaKredytu / wartoscKursuZakupowego;
		
		//obliczenie raty miesiecznej
		double oprocentowanie = oprOprocentowanie/100.0; // / exchRate;
		
		double [] licznik_temp = new double[okres]; 

		double licznik = 1;
		for (int i=1; i<=okres; i++) {
			Calendar payTime = Calendar.getInstance();
			Calendar last = Calendar.getInstance();
			payTime.add(Calendar.MONTH, i);
			last.add(Calendar.MONTH, i-1);
			long daysBetween = DateKalkulator.daysBetween(payTime, last);
			long dayPerYear = payTime.get(Calendar.DAY_OF_YEAR);
			
			licznik *= 1+daysBetween/dayPerYear*oprocentowanie;
			licznik_temp[i-1] = licznik;
		}
		licznik *= kwotaBruttoII;


		double mianownik = 0;
		for (int j=2; j<=okres; j++) {
			/*
			double iloczyn = 1;
			for (int i=j; i<=okres; i++) {
				Calendar payTime = Calendar.getInstance();
				Calendar last = Calendar.getInstance();
				payTime.add(Calendar.MONTH, i);
				last.add(Calendar.MONTH, i-1);
				long daysBetween = DataKalkulator.daysBetween(payTime, last);
				long dayPerYear = payTime.get(Calendar.DAY_OF_YEAR);
				iloczyn *= 1+daysBetween/dayPerYear*oprocentowanie;
			}
			*/
			mianownik += licznik_temp[j-1]; //iloczyn;
		}
		mianownik += 1;
		
		return licznik/mianownik * wartoscKursuSprzedazowego;
	}
	
	public double getSplata() {
		return getRata()*month;
	}

	public double getKoszt() {
		return getSplata()-kwotaKredytu;
	}

	

}

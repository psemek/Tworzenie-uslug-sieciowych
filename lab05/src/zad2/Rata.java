package zad2;

import java.util.Date;



public class Rata {

	private Date data;
	private double rataKapitalowa;
	private double odsetki;
	private double kapitalDoSplaty;
	private long daysPeriod;
	private long daysPerYear;
	private String currencyName;
	private double oprocentowanie;
	private double kurs;
	
	public Rata() { }
	
	
	public double getSumaRaty() {
		return rataKapitalowa+odsetki;
	}

	 
	
	
	public double getKurs() {
		return kurs;
	}


	public void setKurs(double kurs) {
		this.kurs = kurs;
	}


	public double getOprocentowanie() {
		return oprocentowanie;
	}


	public void setOprocentowanie(double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}


	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public long getDaysPeriod() {
		return daysPeriod;
	}
	public void setDaysPeriod(long daysPeriod) {
		this.daysPeriod = daysPeriod;
	}
	public long getDaysPerYear() {
		return daysPerYear;
	}
	public void setDaysPerYear(long daysPerYear) {
		this.daysPerYear = daysPerYear;
	}
	public double getKapitalDoSplaty() {
		return kapitalDoSplaty;
	}
	public void setKapitalDoSplaty(double kapitalDoSplaty) {
		this.kapitalDoSplaty = kapitalDoSplaty;
	}
	public double getOdsetki() {
		return odsetki;
	}
	public void setOdsetki(double odsetki) {
		this.odsetki = odsetki;
	}
	public double getRataKapitalowa() {
		return rataKapitalowa;
	}
	public void setRataKapitalowa(double rataKapitalowa) {
		this.rataKapitalowa = rataKapitalowa;
	}
	
	
	
	
	
}

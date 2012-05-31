package zad2;

import java.util.Calendar;


public class DateKalkulator {

	static final long milPerDay = 1000*60*60*24; 
	
	
	/*not work*/
	static public long daysBetween24h(Calendar d1, Calendar d2 ) {
		return (d1.getTimeInMillis()-d2.getTimeInMillis())/milPerDay;
	}
	
	/* work super! */
	static public long daysBetween(Calendar d1, Calendar d2 ) {
        long endL   =  d2.getTimeInMillis() +  d2.getTimeZone().getOffset(  d2.getTimeInMillis() );
        long startL =  d1.getTimeInMillis() + d1.getTimeZone().getOffset( d1.getTimeInMillis() );
        return (startL-endL) / milPerDay;
	}
	
	
	/*
	 * zwraca liste lat wstecz od obecnego_roku do obecnego_roku-period 
	 */
	static public int [] yearsBefore(int period) {
		int [] result = new int[period];
		Calendar calendar = Calendar.getInstance();
		int now = calendar.get(Calendar.YEAR);
		for (int i=now; i>now-period; i--) {
			result[now-i] = i;
		}
		return result;
	}
	
	/*
	static public List years(int zakresMin, int zakresMax) {
		List<MapValue> l = new LinkedList<MapValue>();
		for (int i=zakresMin; i<=zakresMax; i++) {
			Calendar now = Calendar.getInstance();
			now.roll(Calendar.YEAR, -i);
			l.add( new MapValue( new Integer(now.get(Calendar.YEAR)).toString(), new Integer(i) ) );
		}
		return l;
	}
	*/
	
}

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Main {
	
	static DateFormat dateFormat=new SimpleDateFormat("mm");
	static String date=dateFormat.format(Calendar.getInstance().getTime());
	static int cosa=Integer.parseInt(date);
	
	static DateFormat dateHour=new SimpleDateFormat("hh");
	static String dateh=dateHour.format(Calendar.getInstance().getTime());
	static int hour=Integer.parseInt(dateh);
	
	static DateFormat dateSec=new SimpleDateFormat("ss");
	static String secs=dateSec.format(Calendar.getInstance().getTime());
	static int sec=Integer.parseInt(secs);

	public static void main(String[] args)  {
		//while(true) {
		String horita = getTopHours(hour)+getBottomHours(hour)+getTopMin(cosa)+getBottomMin(cosa)+getSeconds(sec);
		System.out.println(getTopHours(hour)+getBottomHours(hour)+getTopMin(cosa)+getBottomMin(cosa)+getSeconds(sec));
		System.out.println(revHour(horita)+":"+revMin(horita)+":"+revSec(horita));
		//Thread.sleep(1000);
		}
	
	public static int top(int number) {
		return number /5;
	}
	
	public static int bottom(int number) {
		return number %5;
	}
	
	public static String getOnOff(int lamps, int onSigns) {
		return getOnOff (lamps, onSigns, "R");
	}
	
	public static String getOnOff(int lamps, int onSigns, String onSign) {
		String out = "";
		for (int i=0; i<onSigns; i++) {
			out+=onSign;
		}
		for (int i=0; i<(lamps-onSigns); i++) {
			out+="O";
		}
		return out;
	}
	
	public static String getTopHours(int numm) {
		return getOnOff(4,top(numm), "Y");
	}
	
	public static String getBottomHours(int numm) {
		return getOnOff(4,bottom(numm), "Y");
	}
	
	public static String getTopMin(int numm) {
		return getOnOff(11,top(numm), "Y").replaceAll("YYY", "YYR");
	}
	
	public static String getBottomMin(int numm) {
		return getOnOff(4,bottom(numm), "Y");
	}
	
	public static String getSeconds(int numm) {
		if (numm%2==0) {
			return "Y";
		}
			return "O";
	}
	
	public static int revHour(String berlinTime) {
		String bertopHour=berlinTime.substring(0,3);
		char[] charbertopHour =bertopHour.toCharArray();
		String berbotHour=berlinTime.substring(4,7);
		char[] charberbotHour =berbotHour.toCharArray();
		int digtophour=0;
		int digbothour=0;
		
		for(int i=0;i<charbertopHour.length;i++) {
			if (charbertopHour[i]=='Y') {
				digtophour = digtophour + 5;
			}
		}
		for(int i=0;i<charberbotHour.length;i++) {
			if (charberbotHour[i]=='Y') {
				digbothour = digbothour + 1;
			}
		}

		return digtophour+digbothour;
	}
	
	public static int revMin(String berlinTime) {
		String bertopmin=berlinTime.substring(8,18);
		char[] charbertopMin =bertopmin.toCharArray();
		String berbotmin=berlinTime.substring(19,22);
		char[] charberbotMin =berbotmin.toCharArray();
		int digtopmin=0;
		int digbotmin=0;
		
		for(int i=0;i<charbertopMin.length;i++) {
			if (charbertopMin[i]=='Y') {
				digtopmin = digtopmin + 5;
			}
		}
		for(int i=0;i<charberbotMin.length;i++) {
			if (charberbotMin[i]=='Y') {
				digbotmin = digbotmin + 1;
			}
		}

		return digtopmin+digbotmin;
	}

	public static int revSec(String berlinTime) {
		String bertosec=berlinTime.substring(23);
		char[] charbersec =bertosec.toCharArray();
		int digsec=0;
		if (charbersec[0]=='Y')
		{
				digsec=59;
		}
		else {
			digsec=0;
		}
			return digsec;
		}
		
}
	

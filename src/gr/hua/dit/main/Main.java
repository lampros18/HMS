package gr.hua.dit.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import gr.hua.dit.security.Crypto;

public class Main {
	
	public static void main(String[] args) {
		
		
		
		
		
	

		//Now

	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	System.out.println(dateFormatGmt.format(new Date()) + "    now");

//		//Local time zone   
//	SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//              dateFormatLocal.setTimeZone(TimeZone.getTimeZone("GMT"));
//         try {
//               Date date = dateFormatLocal.parse("2018-01-01 11:11:11");
//                
//                SimpleDateFormat dateFormatLocal2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//               dateFormatLocal2.setTimeZone(TimeZone.getDefault());
//               Date date2 = dateFormatLocal.parse(dateFormatLocal2.format(date));
//              System.out.println(dateFormatLocal.format(date2) + "    database");
//           }catch(java.text.ParseException e) {
//            	e.printStackTrace();
//          }


	

	}

}

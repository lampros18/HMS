package gr.hua.dit.main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import gr.hua.dit.mail.MailService;

public class Main {
	
	static MailService mm;
	public static void main(String[] args) {
//		//Now
		
//		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
//		System.out.println(dateFormatGmt.format(new Date()) + "    now");
//
//		//Local time zone   
//		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                dateFormatLocal.setTimeZone(TimeZone.getTimeZone("GMT"));
//            try {
//                Date date = dateFormatLocal.parse("2018-01-01 11:11:11");
//                
//                SimpleDateFormat dateFormatLocal2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                dateFormatLocal2.setTimeZone(TimeZone.getDefault());
//                Date date2 = dateFormatLocal.parse(dateFormatLocal2.format(date));
//                System.out.println(dateFormatLocal.format(date2) + "    database");
//            }catch(java.text.ParseException e) {
//            	e.printStackTrace();
//            }


//		mm = (MailService) context.getBean("MailService");
		mm.sendMail("housingapplication123@gmail.com", "it21622@hua.gr", "Subject->Test2", "2");

	}

}

package gr.hua.dit.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailServiceProvider {
	public void sendEmail(String receiver, String subject, String username, String password) {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("mail.xml");
		MailService mm = (MailService) context.getBean("MailService");
        String message = "username: " + username + "\npassword: " + password;
		mm.sendMail("housingapplication123@gmail.com",
    		   receiver,
    		   subject, 
    		   message);
	}
}

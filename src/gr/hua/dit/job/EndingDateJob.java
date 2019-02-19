package gr.hua.dit.job;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import gr.hua.dit.service.HousingApplicationService;

public class EndingDateJob implements Job {

	@Autowired
	HousingApplicationService housingApplicationService;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("execute : in");
		String url = "http://127.0.0.1:8080/HMS/mail/sendMailEmployee";
		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// optional default is GET
		try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		// add request header
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36");

		try {
			System.out.println(con.getResponseCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


}

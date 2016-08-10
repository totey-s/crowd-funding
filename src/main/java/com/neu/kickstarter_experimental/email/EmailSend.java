package com.neu.kickstarter_experimental.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailSend {

	public static void emailSend(String from,String to, String subject,
			String message, String htmlMmessage){
		HtmlEmail email = new HtmlEmail();
		try {
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
//			email.setAuthenticator(new DefaultAuthenticator("swaraj.totey", "swaraj89"));
			email.setAuthentication("swaraj.totey@gmail.com", "swaraj89");
//			email.setTLS(true);
			email.setStartTLSEnabled(true);
//			email.setSSLOnConnect(true);
//			email.setFrom("swaraj@kickstarter.org");
			email.setFrom(from);
			email.setSubject(subject);
			email.setHtmlMsg(htmlMmessage);
			email.setTextMsg(message);
			email.addTo(to);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		EmailSend.emailSend("we@kickstarter.org", "swaraj.totey@gmail.com", "Hi", "We are You", "We Love you");
//	}
	
}

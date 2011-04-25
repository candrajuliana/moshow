package com.bigjava.util;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Email {
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "nenpenthes@gmail.com";
    private static final String SMTP_AUTH_PWD  = "Rteque155697";

	
	    public static void main(String[] args) throws Exception{
	    	new Email().test();
	    }
	    
	    public void sendMail()
	    {
	    	try{
	    		test();
	    	} catch (Exception e) {
			      e.printStackTrace();
		    }
	    }
	    
	    public void test() throws Exception{
	        Properties props = new Properties();

	        props.put("mail.transport.protocol", "smtps");
	        props.put("mail.smtps.host", SMTP_HOST_NAME);
	        props.put("mail.smtps.auth", "true");
	        // props.put("mail.smtps.quitwait", "false");

	        Session mailSession = Session.getDefaultInstance(props);
	        mailSession.setDebug(true);
	        Transport transport = mailSession.getTransport();

	        MimeMessage message = new MimeMessage(mailSession);
	        message.setSubject("Subjek nya neh");
	        MimeBodyPart textPart = new MimeBodyPart();
	        textPart.setContent("<h1>Check attachment</h1> Testing send now", "text/html");
	        	
	        //c:/csv/Traffic.pdf
	        ///Users/randikajonan/Documents/workspace/export_data
	        //attach file
//	        MimeBodyPart attachFilePart = new MimeBodyPart();
//	        FileDataSource fds = 
//	            new FileDataSource("/Users/randikajonan/Documents/workspace/export_data/Traffic.pdf");
//	        attachFilePart.setDataHandler(new DataHandler(fds));
//	        attachFilePart.setFileName(fds.getName());
//	        
//	        MimeBodyPart attachFilePart2 = new MimeBodyPart();
//	        FileDataSource fdc = 
//	            new FileDataSource("/Users/randikajonan/Documents/workspace/export_data/HTTP.pdf");
//	        attachFilePart2.setDataHandler(new DataHandler(fdc));
//	        attachFilePart2.setFileName(fdc.getName());

	        //all email
	        Multipart mp = new MimeMultipart();
	        mp.addBodyPart(textPart);
//	        mp.addBodyPart(attachFilePart);
//	        mp.addBodyPart(attachFilePart2);
	        
	        message.setContent(mp);
	        //add reciept
	        message.addRecipient(Message.RecipientType.TO,
		             new InternetAddress("randika.jonan@bigjava.com"));
	       
	        
	        transport.connect
	          (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

	        transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	        transport.close();
	    }
	
	
}

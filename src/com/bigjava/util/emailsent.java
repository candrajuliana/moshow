package com.bigjava.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class emailsent {
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "poc@bigjava.com";
    private static final String SMTP_AUTH_PWD  = "B16java123";

	
	    public emailsent(){
	    	super();
	    }
	    
	    public void sentmail(String[] attach, String[] recipients, int TotalReciept, String Subject) throws Exception{
	        Properties props = new Properties();

	        props.put("mail.transport.protocol", "smtps");
	        props.put("mail.smtps.host", SMTP_HOST_NAME);
	        props.put("mail.smtps.auth", "true");
	        // props.put("mail.smtps.quitwait", "false");

	        Session mailSession = Session.getDefaultInstance(props);
	        mailSession.setDebug(true);
	        Transport transport = mailSession.getTransport();
	        
	        Multipart mp = new MimeMultipart();
	        
	        MimeMessage message = new MimeMessage(mailSession);
	        message.setSubject(Subject+" Report");
	        MimeBodyPart textPart = new MimeBodyPart();
	        textPart.setContent("<h1>Files attached</h1>", "text/html");
	        
	        mp.addBodyPart(textPart);
	        //attach file
	        for (int i =0; i <7; i++){
	        	if(attach[i].length() > 0){
	        		addAttachment(mp, "/tmp/"+attach[i]+".csv");
//
	        		///Users/randikajonan/Documents/workspace/export_data
	        		//System.out.println(attach[i]);
	        	}
	        }
	       /* MimeBodyPart attachFilePart2 = new MimeBodyPart();
	        FileDataSource fdc = 
	            new FileDataSource("c:/csv/HTTP.pdf");
	        attachFilePart2.setDataHandler(new DataHandler(fdc));
	        attachFilePart2.setFileName(fdc.getName());*/

	        //all email
	        
	        
	       /* mp.addBodyPart(attachFilePart);
	        mp.addBodyPart(attachFilePart2);
	        */
	        message.setContent(mp);
	        //add reciept
	        for (int r = 0; r < TotalReciept; r++){
	        	message.addRecipient(Message.RecipientType.TO,
		             new InternetAddress(recipients[r]));
	        }
	        
	        transport.connect
	          (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

	        transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	        transport.close();
	    }
	
	    private static void addAttachment(Multipart mp, String filename) throws MessagingException
	    {
	    	MimeBodyPart attachFilePart = new MimeBodyPart();
	    	DataSource source = new FileDataSource(filename);
	    	attachFilePart.setDataHandler(new DataHandler(source));
	    	attachFilePart.setFileName(filename);
	        mp.addBodyPart(attachFilePart);
	    }
	
}

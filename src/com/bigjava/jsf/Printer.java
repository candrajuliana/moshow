package com.bigjava.jsf;
 
/*import java.awt.FileDialog;
import java.awt.Frame;*/
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.sql.DataSource;
/**
 * Print Grid query use PDF File
 * @author yulius eka 13 oktober 2010
 */

public class Printer {

	FileInputStream fis = null;

public Printer(String filenames) throws Exception {
	 PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();  
	        DocPrintJob printerJob = defaultPrintService.createPrintJob();  
//	        File pdfFile = new File("/home/yulius/Desktop/csv/"+filenames+".pdf"); 
	        File pdfFile = new File("c:/csv/"+filenames+".pdf"); 
	        DocFlavor myFormat =DocFlavor.URL.AUTOSENSE;
	        SimpleDoc simpleDoc = null;  
         
	        try { 
	                    simpleDoc = new SimpleDoc(pdfFile.toURL(),myFormat , null);  
	        	        } catch (MalformedURLException ex) {  
	        	            ex.printStackTrace();  
	        	         }  
	        	         try {  
	        	            printerJob.print(simpleDoc, null);  
	        	         } catch (PrintException ex) {  
	        	             ex.printStackTrace();  
	        	         }  
	
	        
}}
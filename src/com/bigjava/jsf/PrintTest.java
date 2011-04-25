package com.bigjava.jsf;
//untuk print
import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.*;

public class PrintTest {
	
    public static void main(String[] args) {
        try {
            // Open the image file
            String  testData = "Hello World !";
            InputStream is = new ByteArrayInputStream(testData.getBytes());
            DocFlavor flavor =  DocFlavor.INPUT_STREAM.AUTOSENSE   ;

            // Find the default service
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            System.out.println(service);

            // Create the print job
            DocPrintJob job = service.createPrintJob();
            Doc doc= new SimpleDoc(is, flavor, null);

            // Monitor print job events; for the implementation of PrintJobWatcher,
            // see e702 Determining When a Print Job Has Finished
            PrintJobWatcher pjDone = new PrintJobWatcher(job);

            // Print it
            job.print(doc, null);

            // Wait for the print job to be done
            pjDone.waitForDone();

            // It is now safe to close the input stream
            is.close();
        } catch (PrintException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class PrintJobWatcher {
        // true iff it is safe to close the print job's input stream
        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            // Add a listener to the print job
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }
                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }
                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }
                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }
                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }
        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

}
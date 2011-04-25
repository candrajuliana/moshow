package com.bigjava.util;

import java.util.TimerTask;

//import com.bigjava.dpi.gui.MoretoReportingCenter;

/**
 * Schedule task periodically 
 * @author randikajonan
 * 14feb2011
 */
public class Task extends TimerTask 
{
  
	  private int timerInterval;
//	  MoretoReportingCenter pnlMoretoReportingCenter =null;

	  public Task(int timeInterval){
	    this.timerInterval=timeInterval;
	  }

//	  public void setObject(MoretoReportingCenter pnl)
//	  {
//		  pnlMoretoReportingCenter=pnl;
//	  }
	  
	  public void run() {
//		  System.out.println("Task running");
//		  pnlMoretoReportingCenter.testTask();
	  }

}

/**
 * gridPanelAllDeviceCenter = new GridPanel2(jxe.getObjectPool());
			gridPanelAllDeviceCenter.setJsonStore(storeAllDevice);
			storeReporting = new JsonStore(jxe.getObjectPool());
			gridPanelReportingCenter = new GridPanel2(jxe.getObjectPool());
			gridPanelReportingCenter.setJsonStore(storeReporting);
 */

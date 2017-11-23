/**
 * 
 */
package org.workflowsim.scheduling;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.lists.VmList;
import org.workflowsim.CondorVM;
import org.workflowsim.Task;
import org.workflowsim.WorkflowSimTags;
import org.workflowsim.Job;
import org.workflowsim.WorkflowESParser;
import org.workflowsim.WorkflowEngine;
import sun.misc.VM;


/**
 * @author LLWAAH
 *
 */
public class LIBRARYSchedulingAlgorithm extends BaseSchedulingAlgorithm {
	
	
      

	@SuppressWarnings("unchecked")
	public void run() {
		
	
		
		 int size = getCloudletList().size();
		 int k=0;
		 for (int i = 0; i < size; i++) {
	            Cloudlet cloudlet = (Cloudlet) getCloudletList().get(i);
	            Job  job = (Job) getCloudletList().get(i);
	                      
	            int vmSize = getVmList().size();
	            CondorVM firstIdleVm = null;
	            for (int j = k; j < vmSize; j++) {
	                CondorVM vm = (CondorVM) getVmList().get(j);
	               // System.out.println("XXXXXXXXXX"+vm.getState());
	                if ((vm.getState() == WorkflowSimTags.VM_STATUS_IDLE || vm.getState() == WorkflowSimTags.VM_STATUS_LIBRARY) && ! (vm.getState() == WorkflowSimTags.VM_STATUS_BEENUSED))  {
	                    firstIdleVm = vm;
	                    break;
	                }
	            }
	            if (firstIdleVm == null) {
	                break;
	            }

	      
	      if  (cloudlet.getItemType().equals("library")) {
              if (!(firstIdleVm.getlibraryStoreinvm().contains(cloudlet))) {
            	  
	    		  firstIdleVm.setState(WorkflowSimTags.VM_STATUS_LIBRARY);
	    	      cloudlet.setVmId(firstIdleVm.getId());
	              firstIdleVm.setlibraryStoreinvm(cloudlet);
	              getScheduledList().add(cloudlet);
	              Log.printLine("Schedules " + cloudlet.getItemName() + " with "
	                   + cloudlet.getCloudletLength() + " to VM " + firstIdleVm.getId()
	                  + " with " + firstIdleVm.getCurrentRequestedTotalMips()); 
	      }} else if (cloudlet.getItemType().equals("task")) {
	    	        int libsize = job.getChildList().size();
	    	      List<Task> lib1 = new ArrayList<Task>();
	    	    
	    	        for ( int n=0 ; n< libsize ; n++){
	    	        	Job job1 = (Job) job.getChildList().get(n); 
	    	        	if (job1.getItemType().equals("library")){
	    	        		lib1.add(job1);
	    	        	};
	    	        	
	    	        }
	    	         if  (!(lib1 == null  ) & (firstIdleVm.getlibraryStoreinvm().containsAll(lib1))){
	    	        	// List<Task> lib = job.getChildList();
	    	        	// int length = lib.size();
	    	        	// for ( int l=0; l< length ; l++){
	    	        	//	 if ((lib.get(l).getItemType().equals("library")) && (!(firstIdleVm.getlibraryStoreinvm().contains(lib.get(l))))){ firstIdleVm.setlibraryStoreinvm(lib.get(l));}}
	    	        	    		firstIdleVm.setState(WorkflowSimTags.VM_STATUS_LIBRARY);
	   	    	                    cloudlet.setVmId(firstIdleVm.getId());
	   	    	               //  firstIdleVm.getlibraryStoreinvm().
	   	    	                //    firstIdleVm.setlibraryStoreinvm(cloudlet);
	   	                            getScheduledList().add(cloudlet);
	   	                           Log.printLine("Schedules " + cloudlet.getItemName() + " with "
	   	                   + cloudlet.getCloudletLength() + " to VM " + firstIdleVm.getId()
	   	                  + " with " + firstIdleVm.getCurrentRequestedTotalMips()); 
	    	         } else {
	            
	           firstIdleVm.setState(WorkflowSimTags.VM_STATUS_BUSY);
	           getScheduledList().add(cloudlet);
	           Log.printLine("Schedules " + cloudlet.getItemName() + " with "
	                    + cloudlet.getCloudletLength() + " to VM " + firstIdleVm.getId()
	                    + " with " + firstIdleVm.getCurrentRequestedTotalMips());}}



	        }
		 
		
	    }
	
	
}




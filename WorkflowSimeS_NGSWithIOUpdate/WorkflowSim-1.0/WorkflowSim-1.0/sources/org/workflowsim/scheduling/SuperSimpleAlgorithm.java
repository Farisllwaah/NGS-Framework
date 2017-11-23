package org.workflowsim.scheduling;



import java.util.Iterator;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.File;
import org.cloudbus.cloudsim.Log;
import org.workflowsim.CondorVM;
import org.workflowsim.Job;
import org.workflowsim.WorkflowSimTags;
import org.workflowsim.utils.Parameters.FileType;
import org.workflowsim.utils.ReplicaCatalog;

/**
 * Data aware algorithm. Schedule a job to a vm that has most input data it requires. 
 * It only works for a local environment. 
 *
 * @Faris LLWAAH
 * @since WorkflowSim Toolkit 1.0
 * @date Apr 9, 2016
 */
public class SuperSimpleAlgorithm  extends BaseSchedulingAlgorithm {

    public SuperSimpleAlgorithm() {
        super();
    }

    @Override
    public void run() {
    	
      
        int size = getCloudletList().size();
        for (int i = 0; i < size; i++) {

            Cloudlet cloudlet = (Cloudlet) getCloudletList().get(i);
                   
            int vmSize = getVmList().size();
            CondorVM closestVm = null;//(CondorVM)getVmList().get(0);
           
            for (int j = 0; j < vmSize; j++) {
                CondorVM vm = (CondorVM) getVmList().get(j);
                closestVm=vm;
                if (vm.getState() == WorkflowSimTags.VM_STATUS_IDLE) {
                	                        
                	closestVm.setState(WorkflowSimTags.VM_STATUS_BUSY);
                          cloudlet.setVmId(vm.getId());
                         getScheduledList().add(cloudlet);
                   

      
                  
            /*
            Log.printLine("Schedules " + cloudlet.getItemName() + " with "
                    + cloudlet.getCloudletLength() + " to VM " + closestVm.getId() 
                    +" with " + closestVm.getCurrentRequestedTotalMips() );
           */
            break;
                }
                  
                          
            }
        }   
            

            }
    
    }

 
   
   

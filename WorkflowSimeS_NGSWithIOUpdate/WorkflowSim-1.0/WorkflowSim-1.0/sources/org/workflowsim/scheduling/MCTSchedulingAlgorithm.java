/**
 * Copyright 2012-2013 University Of Southern California
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.workflowsim.scheduling;

import java.util.jar.Attributes.Name;
import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;
import org.jdom2.Element;
import org.workflowsim.CondorVM;
import org.workflowsim.WorkflowSimTags;


/**
 * MCT algorithm
 *
 * @author Weiwei Chen
 * @since WorkflowSim Toolkit 1.0
 * @date Apr 9, 2013
 */
public class MCTSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    public MCTSchedulingAlgorithm() {
        super();
       
    }

    @Override
    public void run() {

       
        int size = getCloudletList().size();
         
       /* for( int i = 0; i < size; i++) {
        	
     	 System.out.println("@@@@@@@@@@"+getCloudletList().get(i));
     	Cloudlet cloudlet1 = (Cloudlet) getCloudletList().get(i);
     	System.out.println("XXXXXXXXXX"+cloudlet1.getItemType());
        }
        */
        for (int i = 0; i < size; i++) {
            Cloudlet cloudlet = (Cloudlet) getCloudletList().get(i);
            int vmSize = getVmList().size();
            CondorVM firstIdleVm = null;
            for (int j = 0; j < vmSize; j++) {
                CondorVM vm = (CondorVM) getVmList().get(j);
                System.out.println("XXXXXXXXXX"+vm.getState());
                if (vm.getState() == WorkflowSimTags.VM_STATUS_IDLE || vm.getState() == WorkflowSimTags.VM_STATUS_LIBRARY) {
                    firstIdleVm = vm;
                    break;
                }
            }
            if (firstIdleVm == null) {
                break;
            }

            for (int j = 0; j < vmSize; j++) {
                CondorVM vm = (CondorVM) getVmList().get(j);
                if ((vm.getState() == WorkflowSimTags.VM_STATUS_IDLE || vm.getState() == WorkflowSimTags.VM_STATUS_LIBRARY)
                        && (vm.getCurrentRequestedTotalMips() > firstIdleVm.getCurrentRequestedTotalMips())) {
                    firstIdleVm = vm;

                }
            }
            if (cloudlet.getItemType().equals("library")){firstIdleVm.setState(WorkflowSimTags.VM_STATUS_LIBRARY);
                        
            cloudlet.setVmId(firstIdleVm.getId());
          //  getScheduledList().add(cloudlet);
            Log.printLine("Schedules " + cloudlet.getItemType() + " with "
                    + cloudlet.getCloudletLength() + " to VM " + firstIdleVm.getId()
                    + " with " + firstIdleVm.getCurrentRequestedTotalMips());};
            
            firstIdleVm.setState(WorkflowSimTags.VM_STATUS_BUSY);
            cloudlet.setVmId(firstIdleVm.getId());
            getScheduledList().add(cloudlet);
            Log.printLine("Schedules " + cloudlet.getCloudletId() + " with "
                    + cloudlet.getCloudletLength() + " to VM " + firstIdleVm.getId()
                    + " with " + firstIdleVm.getCurrentRequestedTotalMips());



        }
    }
}

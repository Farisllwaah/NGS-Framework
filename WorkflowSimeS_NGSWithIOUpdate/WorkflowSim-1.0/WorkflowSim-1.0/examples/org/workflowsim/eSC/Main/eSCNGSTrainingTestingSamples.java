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
package org.workflowsim.eSC.Main;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
 //import java.io.FileWriter;







import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerDynamicWorkload;
import org.cloudbus.cloudsim.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.HarddriveStorage;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.VmSchedulerSpaceShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.workflowsim.ClusterStorage;
import org.workflowsim.CondorVM;
import org.workflowsim.WorkflowDatacenter;
import org.workflowsim.Job;
import org.workflowsim.Task;
import org.workflowsim.Library;
import org.workflowsim.SharedStorage;
import org.workflowsim.WorkflowESParser;
import org.workflowsim.WorkflowEngine;
import org.workflowsim.WorkflowPlanner;
import org.workflowsim.WorkflowSimTags;
import org.workflowsim.utils.ClusteringParameters;
import org.workflowsim.utils.OverheadParameters;
import org.workflowsim.utils.Parameters;
import org.workflowsim.utils.ReplicaCatalog;
import org.workflowsim.utils.PrintSpace;
import org.workflowsim.utils.Parameters.ClassType;
import org.eScworkflowsim.GetSampleInput;
import org.eScworkflowsim.GetRunTime;

/**
 * This class is used to find the WorkflowSim parameters such as MIPs, and BW of the training set.
 * i,e. building the Training set specifications and then using them to fix both MIPs and BW for testing larger input samples.
 * when MIP value, and BW value are available we can test larger sample with different VMs. 
 * You may change other parameters as well.
 * Based on minimum error finding this class will find an optimal values for both MIPs and BW.
 * For training, first set BW range by BWb=minimum value and BWe= maximum value, where BWs= skip value each runs.
 * Second, set MIPs range by MIPsb= minimum value and  MIPse= maximum value, where MIPss= skip value each runn.
 * Third the variabl parameters= value that defines the number of XML files which refering to invocation number, for example if the training set 
 * was three invocation, so we have set the variable paranmeters =3.
 * Fourth- type
 *  
 * @author Faris Llwaah
 * @since WorkflowSim Toolkit 1.0
 * @date July 1, 2017
 * 
 * 
 * 
 */




  class eSCNGSTrainingTestingSamples  {
//	  public class WorkflowSimBasicExample1 (int Mips, int Bw) { 
	  
	  private static int Mips;
	  private static int BW;
	  public static List<String> result = new ArrayList();
	  private static PrintStream tempFile;
	  private static FileInputStream f;
	  public static   String TrainingSet;
	  
	    protected static List<CondorVM> createVM(int userId, int vms) {
	 		 
	 		   
        //Creates a container to store VMs. This list is passed to the broker later
        LinkedList<CondorVM> list = new LinkedList<CondorVM>();
        //VM Parameters
        long size = 1000; //image size (MB)
        int ram =512; //vm memory (MB)
        int mips =Mips;
        long bw =1000;
        int pesNumber = 4; //number of cpus
        String vmm =  "e-SCIENCE"; //VMM name
        //TODO 
        
        //create VMs
        CondorVM[] vm = new CondorVM[vms];
       
         for (int i = 0; i < vms; i++) {
            double ratio = 1.0;
            vm[i] = new CondorVM(i, userId, mips * ratio, pesNumber, ram, bw, size, vmm, new   CloudletSchedulerSpaceShared());
            list.add(vm[i]);
        }

         return list;
    }
      
    ////////////////////////// STATIC METHODS ///////////////////////
    /**
     * Creates main() to run this example
     * This example has only one datacenter and one storage
     * @throws FileNotFoundException 
     */

    @SuppressWarnings("static-access")
	public static void main(String[] args)  {
    	int BWb=985;
		int BWe=985;
		int BWs=5;
		int MIPsb=1305;
		int MIPse=1305;
		int MIPss=5;
		int Parameter=3;
	    String Type="312-24Core-AzureNew";
	    GetRunTime set= new GetRunTime();
	    GetRunTime type= new GetRunTime();
	    type.setTypeString("312-24Core-AzureNew");
	    set.setTrainingSet("36LS-12Core-AzureNew");
  	
    	try {
    		
    		
    		try {
    			tempFile = new   PrintStream(  "out2.txt"  );
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	  
          /**
             * However, the exact number of vms may not necessarily be vmNum If
             * the data center or the host doesn't have sufficient resources the
             * exact vmNum would be smaller than that. Take care.
             */
             int vmNum  =24;//number  of vms;
           
            /**
             * Should change this based on real physical path
             */
             
             
             GetSampleInput Pathx= new GetSampleInput();
            
             for (int b=BWb  ; b<=BWe; b+=BWs){
             BW=b; 
             System.out.print(b);
             for (int k=MIPsb; k<=MIPse; k+=MIPss){
        	 Mips=k;
        	 System.out.println(k);
             for (int i = 0; i < Parameter; i++) { String  daxPath= Pathx.getSampleInput(Type)[i] ;              
                              
                                
                                if(daxPath == null){
                Log.printLine("Warning: Please replace daxPath with the physical path in your working environment!"); 
                return;
            }
            File daxFile = new File(daxPath);
            if(!daxFile.exists()){
                Log.printLine("Warning: Please replace daxPath with the physical path in your working environment!");
                return;
            }
            
            /**
             * Since we are using MINMIN scheduling algorithm, the planning algorithm should be INVALID 
             * such that the planner would not override the result of the scheduler
             */
           
           
                       
            Parameters.SchedulingAlgorithm sch_method = Parameters.SchedulingAlgorithm.SUPERSIMPLE;
            Parameters.PlanningAlgorithm pln_method = Parameters.PlanningAlgorithm.INVALID;
            ReplicaCatalog.FileSystem file_system = ReplicaCatalog.FileSystem.SHARED;

            /**
             * No overheads 
             */
            OverheadParameters op = new OverheadParameters(100, null, null, null, null, BW);
            
            /**
             * No Clustering
             */
             ClusteringParameters.ClusteringMethod method = ClusteringParameters.ClusteringMethod.NONE ;
          
             ClusteringParameters cp = new ClusteringParameters(0, 0 , method,null);

            /**
             * Initialize static parameters
             */
          
            Parameters.init(vmNum, daxPath , null,
                    null, op, cp, sch_method, pln_method,
                    null, 0);
            ReplicaCatalog.init(file_system);

            // before creating any entities.
            int num_user = 1;   // number of grid users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;  // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);
            
            
            
             WorkflowDatacenter datacenter0 = createDatacenter("Datacenter_0");
           

            /**
             * Create a WorkflowPlanner with one schedulers.
             */
            WorkflowPlanner wfPlanner = new WorkflowPlanner("planner_0",1);
                
            
            /**
             * Create a WorkflowEngine.
             */
            WorkflowEngine wfEngine = wfPlanner.getWorkflowEngine();
            //WorkflowEngine wfEngine1 = wfPlanner.getWorkflowEngine();
            /**
             * Create a list of VMs.The userId of a vm is basically the id of the scheduler
             * that controls this vm. 
             */
            List<CondorVM> vmlist0 = createVM(wfEngine.getSchedulerId(0), Parameters.getVmNum());
        //    List<CondorVM> vmlist1 = createVM(wfEngine1.getSchedulerId(0), Parameters.getVmNum());
            /**
             * Submits this list of vms to this WorkflowEngine.
             */
            wfEngine.submitVmList(vmlist0, 0);
         
            /** 
             * Binds the data centers with the scheduler.
             */
            wfEngine.bindSchedulerDatacenter(datacenter0.getId(), 0);
            
         //   wfEngine1.bindSchedulerDatacenter(datacenter0.getId(), 0);
             CloudSim.startSimulation();
               
              List<Job> outputList0= wfEngine.getJobsReceivedList(); //Original Statement 
           // List<Job> outputList1 = wfEngine.getJobsReceivedList(); // Not  Original Statement 
                 printJobListFinal(outputList0); //Original Statement 
             
               
                 CloudSim.stopSimulation(); 
            
              
            // Log.setOutput(tempFile); // Not  Original Statement
            // printJobList(outputList1); // Not  Original Statement 
             }
     
       }
             }
        } catch (Exception e) {
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
       
 
        
    
          GetSampleInput r= new GetSampleInput();
          r.getResultarry(result,BWb,BWe,BWs,MIPsb,MIPse,MIPss,Parameter, Type);
        	
  }   
    
    protected static WorkflowDatacenter createDatacenter(String name) {
 
        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store one or more
        //    Machines
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
        //    create a list to store these PEs before creating
        //    a Machine.
        for (int i = 1; i <= 100; i++) {
            List<Pe> peList1 = new ArrayList<Pe>();
            int mips = 10000;
            // 3. Create PEs and add these into the list.
            //for a quad-core machine, a list of 4 PEs is required:
            peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
            peList1.add(new Pe(1, new PeProvisionerSimple(mips)));
            peList1.add(new Pe(2, new PeProvisionerSimple(mips)));
            peList1.add(new Pe(3, new PeProvisionerSimple(mips)));
            int hostId = 0;
            int ram = 4096; //host memory (MB)
            long storage = 100000; //host storage
            int bw =1000000000;
            hostList.add(
                    new Host(
                    hostId,
                    new RamProvisionerSimple(ram),
                    new BwProvisionerSimple(bw),
                    storage,
                    peList1,
                    new VmSchedulerTimeShared(peList1))); // This is our first machine
                 
                    hostId++;

        }

        // 5. Create a DatacenterCharacteristics object that stores the
        //    properties of a data center: architecture, OS, list of
        //    Machines, allocation policy: time- or space-shared, time zone
        //    and its price (G$/Pe time unit).
        String arch = "x86";      // system architecture
        String os = "Linux";          // operating system
        String vmm = "Xen";
        double time_zone = 10.0;         // time zone this resource located
        double cost = 3.0;              // the cost of using processing in this resource
        double costPerMem = 0.05;		// the cost of using memory in this resource
        double costPerStorage = 0.1;	// the cost of using storage in this resource
        double costPerBw = 0.1;			// the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<>();	//we are not adding SAN devices by now
        WorkflowDatacenter datacenter = null;


        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);


        // 6. Finally, we need to create a storage object.
        /**
         * The bandwidth within a data center in MB/s.
         */
              
        int maxTransferRate =BW; // the number comes from the futuregrid site, you can specify your bw
        
        try {
            HarddriveStorage s1 = new HarddriveStorage(name, 1e12);
            s1.setMaxTransferRate(maxTransferRate);
            storageList.add(s1);
            datacenter = new WorkflowDatacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
            new SharedStorage("sharedstore", storageList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter; 
    }
    
    /**
     * Prints the job objects
     *
     * @param list list of jobs
     */
    protected static void printJobList(List<Job> list) {
        int size = list.size();
        Job job;
        double costngs=0.0;
        String indent = "     ";
        
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Block/Lib Name" + indent + indent+ indent +  "STATUS" + indent
                + "Data center ID" + indent + "VM ID" + indent + indent + "Time" + indent + "Start Time" + indent + "Finish Time" + indent + "Depth");
        Log.printLine("----------------------------------------------------------------------------------------------------------------------");
        DecimalFormat dft = new DecimalFormat("###.##");
        int count0=0,count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0,count9=0,count10=0,count11=0;
      
        
        for (int i=0; i<size; i++) {
        job = list.get(i);
        
        switch (job.getVmId()){
        
        case 0:count0++;
        break;
        case 1:count1++;
        break;
        case 2:count2++;
        break;
        case 3:count3++;
        break;
        case 4:count4++;
        break;
        case 5:count5++;
        break;
        case 6:count6++;
        break;
        case 7:count7++;
        break;
        case 8:count8++;
        break;
        case 9:count9++;
        break;
        case 10:count10++;
        break;
        case 11:count11++;
    
        
        	
        }
        }

          
        
        
        
        
        System.out.println(" The utilisation "+count0+" "+count1+" "+count2+" "+count3+" "+count4+" "+count5+" "+count6+" "+count7+" "+count8+" " +count9+" "+count10+" "+count11);
        for (int i = 0; i < size; i++) {
        	
        	job = list.get(i);
        	Log.print(indent + job.getItemName());
        	PrintSpace.print(job.getItemName(),25);
        	//costngs+= job.getProcessingCost();
        	costngs+= job.getProcessingCost();
            if (job.getCloudletStatus() == Cloudlet.SUCCESS) {
                Log.print("SUCCESS");
               
                       
                Log.printLine(indent + indent + job.getResourceId()+ indent + indent + indent + job.getVmId()
                        + indent + indent +  dft.format(job.getActualCPUTime())
                        + indent + indent + dft.format(job.getExecStartTime()) + indent +  indent
                        + dft.format(job.getFinishTime()/60) + indent + indent +  job.getDepth()+indent+indent+dft.format(job.getTransformationCost()));
                
            } else if (job.getCloudletStatus() == Cloudlet.FAILED) {
                Log.print("FAILED");

                Log.printLine(indent + indent + job.getResourceId() + indent + indent + indent + job.getVmId()
                        + indent +  indent + dft.format(job.getActualCPUTime())
                        + indent + indent + dft.format(job.getExecStartTime()) + indent + indent + indent
                        + dft.format(job.getFinishTime()) + indent + indent + indent + job.getDepth());
            }
        }
    
  
        Log.printLine("============================================================================================================");
        Log.printLine("||    The processing Cost = Input Data transfer cost + processing cost (CPU) + output Data transfer cost  ||");
        Log.printLine("============================================================================================================");
        Log.printLine("The Total cost to run NGS pipeline is ::"+costngs);
        Log.printLine();
    	
    }
    
    protected static void printJobListFinal(List<Job> list) {
        
    	
    	
    	int size = list.size();
        Job job;
        double costngs=0.0;
        String indent = "     ";
      
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Block/Lib Name" + indent + indent+ indent +  "STATUS" + indent
                + "Data center ID" + indent + "VM ID" + indent + indent + "Time" + indent + "Start Time" + indent + "Finish Time" + indent + "Depth");
        Log.printLine("----------------------------------------------------------------------------------------------------------------------");
        DecimalFormat dft = new DecimalFormat("###.##");
             
        job = list.get(size-1);
        
        //tempFile.println(dft.format(job.getFinishTime()/60));//  Not  Original Statement 
        
		result.add(dft.format(job.getFinishTime()/60));
       
        Log.printLine(dft.format(job.getFinishTime()/60) );
        for (int i = 0; i < size; i++) {
        	
        	job = list.get(i);
        	Log.print(indent + job.getItemName());
        	PrintSpace.print(job.getItemName(),25);
        	//costngs+= job.getProcessingCost();
        	costngs+= job.getProcessingCost();
            if (job.getCloudletStatus() == Cloudlet.SUCCESS) {
                Log.print("SUCCESS");
               
                       
                Log.printLine(indent + indent + job.getResourceId()+ indent + indent + indent + job.getVmId()
                        + indent + indent +  dft.format(job.getActualCPUTime())
                        + indent + indent + dft.format(job.getExecStartTime()) + indent +  indent
                        + dft.format(job.getFinishTime()/60) + indent + indent +  job.getDepth()+indent+indent+dft.format(job.getTransformationCost()));
                
            } else if (job.getCloudletStatus() == Cloudlet.FAILED) {
                Log.print("FAILED");

                Log.printLine(indent + indent + job.getResourceId() + indent + indent + indent + job.getVmId()
                        + indent +  indent + dft.format(job.getActualCPUTime())
                        + indent + indent + dft.format(job.getExecStartTime()) + indent + indent + indent
                        + dft.format(job.getFinishTime()) + indent + indent + indent + job.getDepth());
            }
        }
    
           	
    }

	
}
 
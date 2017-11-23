/*******************************************************************************
 * Title: Shared Storage Module
 * Description: A module for simulating I/O contention of the shared storage in WorkflowSim
 * @author : Faris Llwaah
 * Date: June 2017
 *
 * Address: f.llwaah@ncl.ac.uk
 * Source: https://github.com/Farisllwaah/NGS-Framework
 *
 * Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 * Copyright (c) 2017, Newcastle University, UK.
 *******************************************************************************/

package org.workflowsim;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Consts;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEntity;
import org.cloudbus.cloudsim.core.SimEvent;
import org.workflowsim.Job;
import org.workflowsim.Task;
import org.workflowsim.WorkflowSimTags;
import org.workflowsim.utils.Parameters;
import org.workflowsim.utils.ReplicaCatalog;
import org.workflowsim.utils.Parameters.FileType;

import sun.misc.VM;
	
/**
 * This class organises all read and write operations that will be requested from the VMs to access the sahred storage. 
 * We have considered a single I/O Queue to oreder these requesting and take into account a waiting time in the queue,
 * it is imporatmt of Big data application to s I/O contention
 * 
 * @author Faris Llwaah
 */


	public class SharedStorage extends SimEntity {

		public SharedStorage(String name,LinkedList<Storage> storageList) {
			super(name);
 		double maxRate = Double.MIN_VALUE;
 		for (Storage storage : storageList) {
 			double rate = storage.getMaxTransferRate();
 			if (rate > maxRate) {
 				maxRate = rate;
 			}
 
 			bandwidth= (int) maxRate ;
 
 		}
		}
//		    
		protected Map<Integer, Integer> vmsToDatacentersMap;
		private double lastProcessTime;
		private boolean haswritten=false;
		protected static int bandwidth;
		protected  double  CurrentAllocatedSize = 0;
		private static final     List<Cloudlet> CloudletList = new  LinkedList<Cloudlet>();   

		/** The user id. */
		protected int userId;

		/**the unique id of the file*/
		protected String uniId;
		protected static double maxTime;

		public static double QueueTime=0.0;
		protected static double QueueTimeStart=0.0;

		@Override
		public void startEntity() {
		
			Log.printLine(getName() + " is starting...");
			schedule(getId(), 0, WorkflowSimTags.START_SIMULATION);
		}

		@Override
		public void processEvent(SimEvent ev) {
			 
	        switch (ev.getTag()) {
          // See cloudsim.core.SimEntity#startEntity()
          case  WorkflowSimTags.START_SIMULATION:
          	break;
          //This call is from workflow Datacentre when a job has finished Process
          case CloudSimTags.CLOUDLET_WRITE:
          	haswritten=false;
          	processCloudletWrite(ev);
          break;
 
          default:
              processOtherEvent(ev);
              break;
      }
		
	         
		
		}
		

		protected void processCloudletWrite(SimEvent ev) {
			
		Cloudlet cl = (Cloudlet) ev.getData();
      	@SuppressWarnings("unused")
		int vmId = cl.getVmId();
       	double time = cl.getEndTowrite() ;
      	double delay= cl.getEndTowrite()-cl.getArrivalTowrite();
      	double time1= cl.getEndTowrite()-cl.getEndTowrite();
 
          
          if(cl.isFinished()&& (!haswritten)){
 
      
      	send(cl.getUserId(), delay, CloudSimTags.CLOUDLET_RETURN ,cl);
       
          haswritten = true; 
			

          } 
	    }
		
		@Override
		public void shutdownEntity() {
			  
			        Log.printLine(getName() + " is shutting down...");
				
		}
	
	    
	    public void processOtherEvent(SimEvent ev) {
	        if (ev == null) {
	            Log.printLine(getName() + ".processOtherEvent(): " + "Error - an event is null.");
	            return;
	        }
	        Log.printLine(getName() + ".processOtherEvent(): "
	                + "Error - event unknown by this DatacenterBroker.");
	    }
	    
		public static void addRequest(Cloudlet c) {
			 
			double eventTime = CloudSim.clock();
			if (eventTime >= maxTime) {
				CloudletList.add(c);
				maxTime = eventTime;
				return;
			}

			ListIterator<Cloudlet> iterator = CloudletList.listIterator();
			Cloudlet event;
			while (iterator.hasNext()) {
				//			event = iterator.next();
				if (CloudletList.size()  > CloudSim.clock()) {
					iterator.previous();
					iterator.add(c);
					return;
				}
			}

			CloudletList.add(c);
		}
		
		public static  void addRequestToQueue(Cloudlet c) {
			CloudletList.add(c);
			return;
		}
		public static  Cloudlet getFirstElementFromQueue() {

			return CloudletList.get(0);
		}

		public static   Cloudlet RemoveFirstelementFromQueue() {
			return CloudletList.remove(0);
		}

		public static void processRequest(Job c, String direction ) throws Exception {
		
			double jobArrivalTime= CloudSim.clock();
			double transferTime;
			Path filePath = Paths.get("printlenQueue.CSV");
	 		if (!Files.exists(filePath)) {
	 		    Files.createFile(filePath);
	 		}
			DecimalFormat dft = new DecimalFormat("##.##");
			
			addRequestToQueue(c);
			String total1 = Double.toString(CloudletList.size());
			Files.write(filePath,total1.getBytes(),StandardOpenOption.APPEND );
 			while (CloudletList.size()>0) {
				

				if (jobArrivalTime>QueueTime){QueueTime=jobArrivalTime;}
				QueueTimeStart=QueueTime;
				Job c1= (Job) getFirstElementFromQueue();
				if (direction=="R")  transferTime= processEventDataStageIO(c1.getFileList(),c1 );
				else  transferTime=processEventDataStageWrite(c1 .getFileList(),c1 );
				QueueTime=QueueTime+transferTime;
				RemoveFirstelementFromQueue();
				if (direction=="R"){
					c.setArrivalToread(jobArrivalTime);
					c.setStartToread(QueueTimeStart);
					c.setEndToread(QueueTime);
					c.setWaitToread(QueueTimeStart-jobArrivalTime);
				}
				else                {
					c.setArrivalTowrite(jobArrivalTime);
					c.setStartTowrite(QueueTimeStart);
					c.setEndTowrite(QueueTime);
					c.setWaitTowrite(QueueTimeStart-jobArrivalTime);
				}


 			}	                          



		}

		public Iterator<Cloudlet> iterator() {
			return CloudletList.iterator();
		}

		public String getUniId() {
			return uniId;
		}


		public void setUniId(String uniId) {
			this.uniId = uniId;
		}

			
		public static       void addToQueue( Job job){

			CloudletList.add(job);; 

			if (CloudletList.isEmpty()) System.out.println("HHHHHHHHHHHHHHHHHHHHHHH");      	  
		}
		public  int getQueueLenght(){
			return CloudletList.size();


		}
		
		public static   void getQueue (){


			ListIterator<Cloudlet> iterator = CloudletList.listIterator();

			while (iterator.hasNext()) {

				Cloudlet obj = (Cloudlet)iterator.next();	 
			 

				System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDD"+" "+obj.getCloudletId()+"  "+obj.getExecStartTime()+ "  "+ CloudletList.size());
			 
				iterator.remove();
			}

			System.out.println("+++++++++++++++++is empty"+CloudletList.size() );
		}
		public static int QueueLenght(){
			int Lenght=0;
			ListIterator<Cloudlet> iterator = CloudletList.listIterator();
			while (iterator.hasNext()) {
				Cloudlet	event = iterator.next();
				iterator.remove();

				Lenght++;
			}
			return Lenght;
		}

		public static void RemoveFirtQueue(int i){

			CloudletList.remove(i);
		}

		 
		public static String getUniqueId(int userId, int Id) {
			return userId + "-" + Id;
		}

		/**
		 * Gets the id.
		 * 
		 * @return the id
		 */
		
		public void setUserId(int userId) {
			this.userId = userId;
		}

		
		public int getUserId() {
			return userId;
		}
	 


		public static double processEventDataStageIO(List<FileItem> requiredFiles, Job c) throws Exception {
			double transferTimeRead = 0.0;
			
			for (FileItem  file : requiredFiles ) {
				 

				if (file.isRealInputFile(requiredFiles)) {
					double maxBwth = 0.0;
					List siteList = ReplicaCatalog.getStorageList(file.getName());
					if (siteList.isEmpty()) {
						throw new Exception(file.getName() + " does not exist");
					}
 
					
					transferTimeRead += file.getDatasize()/ Consts.MILLION / bandwidth;
					 

				}

			}

		
			/**
			 * Picks up the site that is closest
			 */
			
			return transferTimeRead ;

		}
		protected static double processEventDataStageWrite(List<FileItem> requiredFiles, Job c) throws Exception {
			double transferTimeWrite = 0.0;
			

			//  File files=(File) c.getRequiredFiles();

			for (FileItem  file : requiredFiles ) {
				//The input file is not an output File 

				if (file.getType() == FileType.OUTPUT) {
					double maxBwth = 0.0;
				
					/**
					 * Picks up the site that is closest
					 */

					transferTimeWrite += file.getDatasize()/ Consts.MILLION / bandwidth;
					//   System.out.println(" The file name of task "+" "+file.getName()+" ");


				}

			}


			return transferTimeWrite;

		}	
   

	}

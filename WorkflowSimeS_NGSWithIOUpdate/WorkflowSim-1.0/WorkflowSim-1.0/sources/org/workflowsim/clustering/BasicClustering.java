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
package org.workflowsim.clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
// Change Here ################################
import org.workflowsim.FileItem;
import org.workflowsim.Job;
import org.workflowsim.Library;
import org.workflowsim.Task;
import org.workflowsim.utils.Parameters;
import org.workflowsim.utils.Parameters.ClassType;
import org.workflowsim.utils.Parameters.FileType;

/**
 * The default clustering does no clustering at all, just map a task to a job
 *
 * @author Weiwei Chen
 * @since WorkflowSim Toolkit 1.0
 * @date Apr 9, 2013
 */
public class BasicClustering implements ClusteringInterface {

    /**
     * The task list.
     */
    private List<Task> taskList;
    private List<Task> libList;
    /**
     * The job list.
     */
// Change Here #####################
//    private List<Job> jobList;
    private final List<Job> jobList;
    /**
     * The library list.
     */
    private List<Job> libraryList;
    /**
     * maps from task to its job.
     */
// Change Here #########################
//    private Map mTask2Job;
    private final Map mTask2Job;
    /**
     * maps from task to its library.
     */
    private Map mTask2Library;
    /**
     * All the files.
     */
// Change Here ###################################
//    private List<org.cloudbus.cloudsim.File> allFileList;
    private final List<FileItem> allFileList;
    /**
     * The root task.
     */
    private Task root;
    /**
     * the id index.
     */
    private int idIndex;

    /**
     * Gets the files
     *
     * @return files
     */
    @Override
// Change Here #############################
//    public final List<org.cloudbus.cloudsim.File> getTaskFiles() {
//        return this.allFileList;
//    }
    
    public final List<FileItem> getTaskFiles() {
        return this.allFileList;
    }

    /**
     * Initialize a BasicClustering object
     */
    public BasicClustering() {
// Change Here ####################################
//       this.jobList = new ArrayList<Job>();
        this.jobList = new ArrayList<>();
        this.libraryList = new ArrayList<Job>();
 //     this.taskList = new ArrayList<Task>();
        this.taskList = new ArrayList<>();
        this.libList = new ArrayList<Task>();
 //     this.mTask2Job = new HashMap<Task, Job>();
        this.mTask2Job = new HashMap<>();
        this.mTask2Library = new HashMap<Task, Job>();
//        this.allFileList = new ArrayList<org.cloudbus.cloudsim.File>();
        this.allFileList = new ArrayList<>();
        this.idIndex = 0;
        this.root = null;
    }

    /**
     * Sets the task list
     *
     * @param list task list
     */
    @Override
    public final void setTaskList(List<Task> list) {
        this.taskList = list;
    }
    public final void setLibList(List<Task> list) {
        this.libList = list;
    }
    /**
     * Gets the job list
     *
     * @return job list
     */
    @Override
    public final List<Job> getJobList() {
        return this.jobList;
    }
   
    /**
     * Gets the library list
     *
     * @return library list
     */
    @Override
    public final List<Job> getLibraryList() {
        return this.libraryList;
    }
    /**
     * Gets the task list
     *
     * @return task list
     */
    @Override
    public final List<Task> getTaskList() {
        return this.taskList;
    }
    public final List<Task> getLibList() {
        return this.libList;
    }
    
    /**
     * Gets the map from task to job
     *
     * @return map
     */
    public final Map getTask2Job() {
        return this.mTask2Job;
    }
    /**
     * Gets the map from task to library
     *
     * @return map
     */
    public final Map getTask2Library() {
        return this.mTask2Library;
    }
    /**
     * The main function of BasicClustering
     */
    @Override
    public void run() {
    	int x=0;
    	String taskname;
        getTask2Job().clear();
        getTask2Library().clear();
        for (Iterator it = getTaskList().iterator(); it.hasNext();) {
            Task task = (Task) it.next();
            taskname = task.getItemName();
            if (task.getItemType().equals("library")){x=1;
            List libList = new ArrayList();
            libList.add(task);
            Job library = addTasks2Library(libList);
            library.setVmId(task.getVmId());
            library.setItemType(x);
            library.setItemName(taskname);
            getTask2Library().put(task, library);
            
            
                                                          }
                                                          else {                                             
           
            List taskList = new ArrayList();
            taskList.add(task);
            Job job = addTasks2Job(taskList);
            job.setVmId(task.getVmId());
           
            job.setItemType(x);
            job.setItemName(taskname);
            getTask2Job().put(task, job);}

        }
        /**
         * Handle the dependencies issue.
         */
        updateDependencies();

    }

    /**
     * Add a task to a new job
     *
     * @param task the task
     * @return job the newly created job
     */
// Change Here ##############################
//    protected final Job addTasks2Job(Task task) {
//        ArrayList job = new ArrayList();
//        job.add(task);
//        return addTasks2Job(job);
//    }
    
    protected final Job addTasks2Job(Task task) {
       List<Task> tasks = new ArrayList<>();
       tasks.add(task);
       return addTasks2Job(tasks);
    }
    /**
     * Add a task to a new library
     *
     * @param task the task
     * @return library the newly created job
     */
    protected final Job addTasks2Library(Task task) {
        ArrayList library = new ArrayList();
        library.add(task);
        return addTasks2Library(library);
    }
    /**
     * Add a list of task to a new job
     *
     * @param taskList the task list
     * @return job the newly created job
     */
// Change Here #############################################
//    protected final Job addTasks2Job(List taskList) { 
    protected final Job addTasks2Job(List <Task> taskList) {
        if (taskList != null && !taskList.isEmpty()) {
            int length = 0;
            int itemType =0;
            int userId = 0;
            int priority = 0;
            int depth = 0;
            String taskname="";
            /// a bug of cloudsim makes it final of input file size and output file size
            Job job = new Job(idIndex, length/*, inputFileSize, outputFileSize*/);
            job.setClassType(ClassType.COMPUTE.value);
            for ( Task task : taskList){
          //  for (Iterator it = taskList.iterator(); it.hasNext();) {
           //     Task task = (Task) it.next();
                length += task.getCloudletLength();
                if (task.getItemType().equals("library"))itemType=1;
                userId = task.getUserId();
                priority = task.getPriority();
                depth = task.getDepth();
                taskname = task.getItemName();
               // List fileList = task.getFileList();
                List <FileItem> fileList = task.getFileList();
                job.getTaskList().add(task);
                getTask2Job().put(task, job);
               // for (Iterator itc = fileList.iterator(); itc.hasNext();) {
               //     org.cloudbus.cloudsim.File file = (org.cloudbus.cloudsim.File) itc.next();
                    for (FileItem file : fileList){
                //    boolean hasFile = false;
                    	boolean hasFile = job.getFileList().contains(file);
                    if (!hasFile)	{
                   // hasFile = job.getFileList().contains(file);

                 

                        job.getFileList().add(file);
                        if (file.getType() == FileType.INPUT) {
                            //for stag-in jobs to be used
                            if (!this.allFileList.contains(file)) {
                                this.allFileList.add(file);
                            }
                        } else if (file.getType() == FileType.OUTPUT) {
                            this.allFileList.add(file);
                        }
                    }
                }
                //for (Iterator itc = task.getRequiredFiles().iterator(); itc.hasNext();) {
                 //   String fileName = (String) itc.next();
                 for ( String fileName : task.getRequiredFiles()){
                    if (!job.getRequiredFiles().contains(fileName)) {
                        job.getRequiredFiles().add(fileName);
                    }
                }

            }


            job.setCloudletLength(length);
            job.setUserId(userId);
            job.setDepth(depth);
            job.setPriority(priority);
            job.setItemType(itemType);
            job.setItemName(taskname);
            idIndex++;
            getJobList().add(job);
            return job;
            
            	
            
        }

        return null;
    }
/******************************************************************/
    protected final Job addTasks2Library(List libList) {
        if (libList != null && !libList.isEmpty()) {
            int length = 0;
            int itemType =0;
            int userId = 0;
            int priority = 0;
            int depth = 0;
            String taskname="";
            /// a bug of cloudsim makes it final of input file size and output file size
            Job library = new Job(idIndex, length/*, inputFileSize, outputFileSize*/);
            library.setClassType(ClassType.COMPUTE.value);
            for (Iterator it = libList.iterator(); it.hasNext();) {
                Task task = (Task) it.next();
                length += task.getCloudletLength();
                if (task.getItemType().equals("library"))itemType=1;
                userId = task.getUserId();
                priority = task.getPriority();
                depth = task.getDepth();
                taskname = task.getItemName();
                List fileList = task.getFileList();
                library.getTaskList().add(task);
                getTask2Job().put(task, library);
                for (Iterator itc = fileList.iterator(); itc.hasNext();) {
                    org.cloudbus.cloudsim.File file = (org.cloudbus.cloudsim.File) itc.next();

                    boolean hasFile = false;

                    hasFile = library.getFileList().contains(file);

                    if (!hasFile) {

                     //   library.getFileList().add(file);
                        if (file.getType() == FileType.INPUT.value) {
                            //for stag-in jobs to be used
                            if (!this.allFileList.contains(file)) {
                          //      this.allFileList.add(file);
                            }
                        } else if (file.getType() == FileType.OUTPUT.value) {
                   //         this.allFileList.add(file);
                        }
                    }
                }
                for (Iterator itc = task.getRequiredFiles().iterator(); itc.hasNext();) {
                    String fileName = (String) itc.next();

                    if (!library.getRequiredFiles().contains(fileName)) {
                        library.getRequiredFiles().add(fileName);
                    }
                }

            }


            library.setCloudletLength(length);
            library.setUserId(userId);
            library.setDepth(depth);
            library.setPriority(priority);
            library.setItemType(itemType);
            library.setItemName(taskname);
            idIndex++;
            getLibraryList().add(library);
            return library;
            
            	
            
        }

        return null;
    }
 
    
    
    
    
    
    
    
    
 /********************************************************************/
    /**
     * For a clustered job, we should add clustering delay (by default it is
     * zero)
     */
    public void addClustDelay() {

        for (Iterator it = getJobList().iterator(); it.hasNext();) {
            Job job = (Job) it.next();


            double delay = Parameters.getOverheadParams().getClustDelay(job);
            delay *= 1000; // the same ratio used when you parse a workflow
            long length = job.getCloudletLength();
            length += (long) delay;
            job.setCloudletLength(length);

        }
    }

    /**
     * Update the dependency issues between tasks/jobs
     */
    protected final void updateDependencies() {
        for (Iterator it = getTaskList().iterator(); it.hasNext();) {
            Task task = (Task) it.next();

            Job job = (Job) getTask2Job().get(task);
            for (Iterator itp = task.getParentList().iterator(); itp.hasNext();) {
                Task parentTask = (Task) itp.next();
                Job parentJob = (Job) getTask2Job().get(parentTask);
                if (!job.getParentList().contains(parentJob) && parentJob != job) {//avoid dublicate
                    job.addParent(parentJob);
                }
            }
            for (Iterator itc = task.getChildList().iterator(); itc.hasNext();) {
                Task childTask = (Task) itc.next();
                Job childJob = (Job) getTask2Job().get(childTask);
                if (!job.getChildList().contains(childJob) && childJob != job) {//avoid dublicate
                    job.addChild(childJob);
                }
            }

        }
        getTask2Job().clear();
        getTask2Library().clear();
        getTaskList().clear();
    }
    /*
     * Add a fake root task
     * If you have used addRoot, please use clean() after that
     */

    public Task addRoot() {

        if (root == null) {
            //bug maybe
            root = new Task(taskList.size() + 1, 0/*,0,0*/);
            for (Iterator it = taskList.iterator(); it.hasNext();) {
                Task node = (Task) it.next();
                if (node.getParentList().isEmpty()) {
                    node.addParent(root);
                    root.addChild(node);
                }
            }
            taskList.add(root);

        }
        return root;

    }

    /**
     * Delete the root task
     */
    public void clean() {
        if (root != null) {
            for (int i = 0; i < root.getChildList().size(); i++) {
                Task node = (Task) root.getChildList().get(i);
                node.getParentList().remove(root);
                root.getChildList().remove(node);
                i--;
            }
            taskList.remove(root);
        }
    }
}

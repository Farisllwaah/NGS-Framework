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
import org.workflowsim.Task;

/**
 * BlockClustering groups tasks in both horizontal and vertical direction
 *
 * @author Weiwei Chen
 * @since WorkflowSim Toolkit 1.0
 * @date Apr 9, 2013
 */
public class BlockClustering extends BasicClustering {

    /**
     * The number of clustered jobs per level.
     */
// Change Here ###################################
 //   private int clusterNum;
    private final int clusterNum;
    /**
     * The size of a clustered job (number of tasks in a job).
     */
// Change Here ######################
  //  private int clusterSize;
    private final int clusterSize;
    /**
     * The map stores whether a task has checked.
     */
//  Change Here ####################################
//    private Map mHasChecked;
    private final Map< Integer, Boolean> mHasChecked;
    /**
     * The map stores tasks per level.
     */
// Change Here #########################
//    private Map mDepth2Task;
    private final Map <Integer, List>mDepth2Task;

    /**
     * Initialize a BlockClustering object
     *
     * @param cNum clusters.num
     * @param cSize clusters.size
     */
    public BlockClustering(int cNum, int cSize) {
        super();
        clusterNum = cNum;
        clusterSize = cSize;
// Change Here ###############################################
//        this.mHasChecked = new HashMap<Integer, Boolean>();
//        this.mDepth2Task = new HashMap<Integer, Map>();
        this.mHasChecked = new HashMap<>();
        this.mDepth2Task = new HashMap<>();

    }

    /**
     * Set the check point of a task.
     *
     * @param index id of a task
     */
    private void setCheck(int index) {

        if (mHasChecked.containsKey(index)) {
            mHasChecked.remove(index);
        }
        mHasChecked.put(index, true);


    }

    /**
     * Gets the check point of a task
     *
     * @param index id of a task
     * @return
     */
    private boolean getCheck(int index) {

        if (mHasChecked.containsKey(index)) {
// Change Here #############################################
            return  mHasChecked.get(index);
        }
        return false;
    }

    /**
     * The main function
     */
    @Override
    public void run() {

        // level by level
        if (clusterNum > 0 || clusterSize > 0) {
 
//            for (Iterator it = getTaskList().iterator(); it.hasNext();) {
//                Task task = (Task) it.next();
        	for (Task task : getTaskList()){
                int depth = task.getDepth();
                if (!mDepth2Task.containsKey(depth)) {
             //       mDepth2Task.put(depth, new ArrayList<Task>());
                    mDepth2Task.put(depth, new ArrayList<>());
                }
 
 //               ArrayList list = (ArrayList) mDepth2Task.get(depth);
                List list = (ArrayList) mDepth2Task.get(depth);
                if (!list.contains(task)) {
                    list.add(task);
                }

            }
        }


        if (clusterNum > 0) {
            bundleClustering();
        } else if (clusterSize > 0) {
            collapseClustering();
        }

        mHasChecked.clear();
        super.clean();

        updateDependencies();
        addClustDelay();
    }

    /**
     * Provides the potential candidate to merge
     *
     * @param taskList the seed tasks
     * @return candidate tasks
     */
// Change Here ########################################
//    private List searchList(List taskList) {
//        ArrayList sucList = new ArrayList<Task>();
//        for (Iterator it = taskList.iterator(); it.hasNext();) {
//            Task task = (Task) it.next();
//            if (getCheck(task.getCloudletId())) {
//                //do not process this task it's been checked
//            } else {
    private List searchList (List<Task> taskList){
    	List<Task> sucList = new ArrayList<>();
    	for (Task task : taskList){
    		if (!getCheck(task.getCloudletId())){
                setCheck(task.getCloudletId());
                sucList.add(task);
                //add all of its successors
                Task node = task;
                while (node != null) {
// Change Here #####################################################
//                    int pNum = node.getParentList().size();
//                    int cNum = node.getChildList().size();
//                    if (cNum == 1) {
//                        Task child = (Task) node.getChildList().get(0);
                	  if (node.getChildList().size()==1){
                		  Task child = node.getChildList().get(0);
                        if (!getCheck(child.getCloudletId()) && child.getParentList().size() == 1) {
                            setCheck(child.getCloudletId());
                            sucList.add(child);
                            node = child;
                        } else {
                            node = null;
                        }

                    } else {
                        node = null;
                        //cNum == 0, doesn't have to add
                        //cNum > 0 might be a cross dependency issue
                    }
                }
            }
        }

        return sucList;
    }

    /**
     * Merges tasks into a fixed number of jobs.
     */
    private void bundleClustering() {
// Change Here ###################################

//        for (Iterator it = mDepth2Task.entrySet().iterator(); it.hasNext();) {
//            Map.Entry pairs = (Map.Entry<Integer, ArrayList>) it.next();
//            ArrayList list = (ArrayList) pairs.getValue();
         	for (Map.Entry<Integer, List> pairs : mDepth2Task.entrySet()) {
            List list = pairs.getValue();
            int num = list.size();
            int avg_a = num / this.clusterNum;
            int avg_b = avg_a;
            if (avg_a * this.clusterNum < num) {
                avg_b++;
            }

            int mid = num - this.clusterNum * avg_a;
            if (avg_a <= 0) {
                avg_a = 1;
            }
            if (avg_b <= 0) {
                avg_b = 1;
            }
            int start = 0, end = -1;
            for (int i = 0; i < this.clusterNum; i++) {
                start = end + 1;
                if (i < mid) {
                    //use avg_b
                    end = start + avg_b - 1;
                } else {
                    //use avg_a
                    end = start + avg_a - 1;
                }

                if (end >= num) {
                    end = num - 1;
                }
                if (end < start) {
                    break;
                }

                addTasks2Job(searchList(list.subList(start, end + 1)));
            }

        }

    }

    /**
     * Merges a fixed number of tasks into a job
     */
    private void collapseClustering() {
// Change Here ############################################
//        for (Iterator it = mDepth2Task.entrySet().iterator(); it.hasNext();) {
//            Map.Entry pairs = (Map.Entry<Integer, ArrayList>) it.next();
//            ArrayList list = (ArrayList) pairs.getValue();
    	    for (Map.Entry<Integer, List> pairs : mDepth2Task.entrySet()) {
            List list = pairs.getValue();
            int num = list.size();
            int avg = this.clusterSize;

            int start = 0;
            int end = 0;
            int i = 0;
            do {
                start = i * avg;
                end = start + avg - 1;
                i++;
                if (end >= num) {
                    end = num - 1;
                }
                addTasks2Job(searchList(list.subList(start, end + 1)));
            } while (end < num - 1);

        }
    }
}

/**
 * 
 */
package org.workflowsim;
import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.UtilizationModelFull;

/**
 * @author LLWAAH
 *
 */
public class Library  extends Task {
	 /*
     * The list of tasks a job has. It is the only difference between Job and Task. 
     */
    private List<Task> libList;
    
    private Double storage;
    
   
    
    /*
     * The list of parent library. 
     */
    private List<Library> parentListlibrary;
    /*
     * The list of child library. 
     */
    private List<Library> childListlibrary;
    private double library_finish_time;
	
   
    
    
    /**
     * Allocates a new Job object. The job length should be greater than or
     * equal to 1.
     *
     * @param jobId the unique ID of this job
     * @param jobLength the length or size (in MI) of this task to be executed
     * in a PowerDatacenter
     * @pre jobId >= 0
     * @pre jobLength >= 0.0
     * @post $none
     */
    
    /**
     * Gets the storage of the library
     *
     * @return the storage of the library
     * @pre $none
     * @post $none
     */
    public Double getLibraryStorage() {
        return storage;
    } 
    public String setisLibrary() {
         return "library";
    } 
   
    public Library(
            final int libraryId,
            final long libraryLength) {

        super(libraryId, libraryLength);
        this.libList = new ArrayList<Task>();

    }

    /**
     * Gets the list of tasks in this job
     *
     * @return the list of the tasks
     * @pre $none
     * @post $none
     */
    public List<Task> getLibraryList() {
      return this.libList;
   }
    /**
     * Gets the child list of the task
     *
     * @return the list of the children
     * @pre $none
     * @post $none
     */
    public List<Task> getChildListlibrary() {
        return this.libList;
    }

    /**
     * Sets the list of the tasks
     *
     * @param list, list of the tasks
     * @return $none
     */
    public void setLibraryList(List list) {
        this.libList = list;
    }

    /**
     * Adds a task list to the existing task list
     *
     * @param list, task list to be added
     * @return $none
     */
    public void addLibraryList(List list) {
        this.libList.addAll(list);
    }

    /**
     * Gets the list of the parent tasks and override its super function
     *
     * @return the list of the parents
     * @pre $none
     * @post $none
     */
    @Override
    public List getParentList() {

        return super.getParentList();
    }
    
    public void setStorage(Double storage) {
        this.storage = storage;
    }
    /**
     * Adds a library to existing child list
     *
     * @param library, the child library to be added
     * @return $none
     */ 
    public void addChildlibrary(Library library) {
        this.childListlibrary.add(library);
    }
    /**
     * Adds a library to existing parent list
     *
     * @param library, the parent task to be added
     * @return $none
     */
    public void addParentlibrary(Library library) {
        this.parentListlibrary.add(library);
    }
    
    
    /**
     * Adds the list to existing child list
     *
     * @param list, the child list to be added
     * @return $none
     */
    public void addChildlibraryList(List list) {
        this.childListlibrary.addAll(list);
    }

    /**
     * Adds the list to existing parent list
     *
     * @param list, the parent list to be added
     * @return $none
     */
    public void addParentlibraryList(List list) {
        this.parentListlibrary.addAll(list);
    }
    public List<Library> getParentlibrarList() {
        return this.parentListlibrary;
    }

}
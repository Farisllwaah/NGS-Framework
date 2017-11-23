/**
 * 
 */
package org.cloudbus.cloudsim.lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.workflowsim.Library;
/**
 * @author LLWAAH
 *
 */
public class LibraryList {
	/**
	 * Gets the by id.
	 * 
	 * @param cloudletList the cloudlet list
	 * @param id the id
	 * @return the by id
	 */
	public static <T extends Cloudlet> T getById(List<T> libraryList, int id) {
		for (T cloudlet : libraryList) {
			if (cloudlet.getCloudletId() == id) {
				return cloudlet;
			}
		}
		return null;
	}
	/**
	 * Returns the position of the cloudlet with that id, if it exists. Otherwise -1.
	 * @param cloudletList - the list of cloudlets.
	 * @param id - the id we search for.
	 * @return - the position of the cloudlet with that id, or -1 otherwise.
	 */
	public static <T extends Cloudlet> int getPositionById(List<T> libraryList, int id) {
		int i = 0 ;
	        for (T cloudlet : libraryList) {
			if (cloudlet.getCloudletId() == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

}

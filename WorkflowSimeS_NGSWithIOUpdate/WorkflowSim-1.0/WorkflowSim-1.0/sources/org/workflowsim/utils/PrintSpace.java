/**
 * 
 */
package org.workflowsim.utils;
import java.io.IOException;
import java.io.OutputStream;
/**
 * @author LLWAAH
 *
 */
public class  PrintSpace {
	
	public static void print(String message, int space) {
		String temp = message;
   	    int size1= 4;     
        if (temp != null ) { size1 = temp.length();}
      	for (int j = 1 ; j < space - size1 ; j++){
   		System.out.print(" ");
   	};
	}


}

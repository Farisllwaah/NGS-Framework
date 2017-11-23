 
package org.workflowsim;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException; //New statement
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;  // New statement
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList; // New Statement
import java.util.Map;
import org.cloudbus.cloudsim.Log;
import org.jdom2.Document;
import org.jdom2.Attribute; //New statement
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter; // New statement
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.workflowsim.failure.FailureParameters;
import org.workflowsim.utils.Parameters;
import org.workflowsim.utils.Parameters.FileType;
import org.workflowsim.utils.ReplicaCatalog;
import org.eScworkflowsim.*;
import org.workflowsim.FileItem;


/**
 * eSC NGS pipeline Parser which creats the XML file of NGS pipeline file and then parse
 * a DAX into tasks so that can be accepted by WorkflowSim and predict the execution time
 * Function: It is function to genrate an appropriate XML file of the NGS pipeline
 *  based on the number of input Smaple. However, it would be done by expected time and output 
 *  data size for each task and save them in the XML file.
 *            
 * @author Faris Llwaah
 * @date May, 2016
 */
public class WorkflowESParser {

	/**
     * The path to data size file.
     */
    private String fileSizePath;
    /**
     * The path to runtime file.
     */
    private String runtimePath;
    /**
     * The path to DAX file.
     */
    private String daxPath;
    /**
     * The path to DAX files.
     */
    private final List<String> daxPaths;
    
    /**
     * All tasks.
     */
    private List<Task> taskList;
    
   
    /**
     * All library.
     */
    private List<Task> libraryList;
    
    /**
     * User id. used to create a new task.
     */
    private int userId;
    /**
     * current job id. In case multiple workflow submission
     */
    private int jobIdStartsFrom;
  
    /**
     * Gets the task list
     *
     * @return the task list
     */
    @SuppressWarnings("unchecked")
   public List<Task> getTaskList() {
       return  taskList;
    }
   
    /**
     * Gets the library list
     *
     * @return the library list
     */
    
    public List<Task> getLibraryList() {
        return (List<Task>) libraryList;
    }
    
    
    
    /**
     * Sets the task list
     *
     * @param taskList the task list
     */
    protected void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
   
    /**
     * Sets the library list
     *
     * @param libraryList the library list
     */
    protected void setLibraryList(List<Task> libraryList) {
        this.libraryList = libraryList;
    }
    
    
    
    /**
     * Map from task name to task.
     */
    protected Map<String, Task> mName2Task;
    /**
     * Map from library name to library.
     */  
    protected Map<String, Library> mName2Library;
    /**
     * Map from library storage to storage.
     */
    protected Map<String, Double> mName2Storage;
    /**
     * Map from task name to task runtime.
     */
    protected Map<String, Double> mName2Runtime;
    
    
    /**
     * Map from file name (data) to its size.
     */
    protected Map<String, Double> mName2Size;

    /**
     * Initialize a WorkflowParser
     *
     * @param userId the user id. Currently we have just checked single user
     * mode
     */
    public WorkflowESParser(int userId) {
        this.userId = userId;
        this.mName2Task = new HashMap<>();
        this.mName2Library = new HashMap< >();
        this.mName2Storage = new HashMap<>();
        this.mName2Runtime = new HashMap<String, Double>();
        this.mName2Size = new HashMap<String, Double>();
        this.fileSizePath = Parameters.getDatasizePath();
        this.daxPath = Parameters.getDaxPath();
        this.daxPaths = Parameters.getDAXPaths();
        this.runtimePath = Parameters.getRuntimePath();

        setTaskList(new ArrayList<>());
        setLibraryList(new ArrayList<Task>());


    }

    /**
     * Start to parse a workflow which includes text files and xml files.
     */
    public void parse() {
        if (this.daxPath != null) {
         parseXmlFile(this.daxPath);
        } else if (this.daxPaths != null) {
            for (String path : this.daxPaths) {
                parseXmlFile(path);
            }
        }
    }

    /**
     * Parse a text file (file size and runtime). Add them to mName2Size and
     * mName2Runtime
     */


    /**
     * Sets the depth of a task
     *
     * @param task the task
     * @param depth the depth
     */
    private void setDepth(Task task, int depth) {
        if (depth > task.getDepth()) {
            task.setDepth(depth);
        }
        
              
        for (Iterator it = task.getChildList().iterator(); it.hasNext();) {
            Task cTask = (Task) it.next();
            setDepth(cTask, task.getDepth() + 1);
        }
        
    }

    /**
     * Sets the depth of a library
     *
     * @param library the library
     * @param depth the depth
     */
    private void setDepth(Library library, int depth) {
        if (depth > library.getDepth()) {
            library.setDepth(depth);
        }
        
              
        for (Iterator it = library.getChildListlibrary().iterator(); it.hasNext();) {
            Library cLibrary = (Library) it.next();
            setDepth(cLibrary, library.getDepth() + 1);
        }
        
        
        /*
        if (FailureParameters.getAlpha()!=null && ! FailureParameters.getAlpha().containsKey(task.getDepth() )) {
            FailureParameters.getAlpha().put(task.getDepth(), 0.0);
        }
        */ 
    }

    
    /**
     * Parse a DAX file with jdom
     */
    private void  parseXmlFile(String path) {
 //**************************************//
// The new update to get the output size //
//***************************************//
    	 PrintStream ps = null;
 		try {
 			ps = new   PrintStream(  "out3.txt"  );
 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
		} 

 		
 		
        try {
        	
        	 String fileSize1=null ,fileSize2=null; 
        	 LinkedList <String> BWA_FEL_common = new LinkedList <String>()  ;
        	 LinkedList <String>PICARD_common= new LinkedList <String>()  ;
        	 LinkedList <String>GATKP1_common= new LinkedList <String>()  ;
        	 LinkedList <String>COVERAGE_common= new LinkedList <String>()  ;
        	 LinkedList <String>VCF_common= new LinkedList <String>()  ;
        	 LinkedList <String>ANNOTATE_common= new LinkedList <String>()  ;
        	 LinkedList <String>GATKP3_common= new LinkedList <String>()  ;
        	  int BWA_FEL_count=0;
        	  int PICARD_count=0;
        	  int GATKP1_count=0;
        	  int COVERAGE_count=0;
        	  int VCF_count=0;
        	  int ANNTATE_count=0;
        	 double size1, size0= 0.0;
        	 GetOutputData data1 = new GetOutputData();
        	 SAXBuilder builder1 = new SAXBuilder();
             Document dom1 = builder1.build(new File(daxPath));
             Element root1 = dom1.getRootElement();           
             List list1 = root1.getChildren();
           
             for (Iterator it = list1.iterator(); it.hasNext();) {
            	 
                 Element node1 = (Element) it.next();
                 if ((node1.getName().toLowerCase().equals("task"))){
                	 String nodeName = node1.getAttributeValue("id"); 
                	 if(GetBlockName.BWAB_AL.contains(nodeName)){
                		   		 
                		 // NodeList list =  (NodeList) node1.getChildren();
                		 List list = node1.getChildren();
                		     Element file0 = (Element) list.get(0);
                             Element file1 = (Element) list.get(1);
                                          String inout = file0.getAttributeValue("link");
                                		  fileSize1 = file0.getAttributeValue("size");
                                	      size1 = Double.parseDouble(fileSize1);
                                	      size1 = data1.getOutputData(nodeName,inout ,size1);
                                	      NumberFormat formatter = new DecimalFormat("##############");  
                                          String estimatedValue = formatter.format(size1);  
                                	      Attribute attribute = file1.getAttribute("size");
                                	      attribute.setValue(estimatedValue); 
                                	                     		 
                 	  // System.out.println(fileSize1+"  "+ size1);
                	 }
                	 
                	 if(GetBlockName.BWA_FEL.contains(nodeName)){
                		 
                		 List list = node1.getChildren();
                		 
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            		  fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble(fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute = file1.getAttribute("size");
                            	      attribute.setValue(estimatedValue);
                            	      BWA_FEL_common.add(estimatedValue);
                            	      
                		            
              
                	 }
                	
                	 
                	 if(GetBlockName.PICARD.contains(nodeName)){
                		 
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            	      
                            	      Attribute attribute = file0.getAttribute("size");
                                      attribute.setValue(BWA_FEL_common.get(BWA_FEL_count));
                            	      BWA_FEL_count++;
                            	      fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble( fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute1 = file1.getAttribute("size");
                            	      attribute1.setValue(estimatedValue);
                            	      PICARD_common.add(estimatedValue);
                            	  
                	  
                	    
                	 }
                	 if(GetBlockName.GATKP1_N.contains(nodeName)){
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            	    
                            	      Attribute attribute = file0.getAttribute("size");
                                      attribute.setValue(PICARD_common.get(PICARD_count));
                            	      PICARD_count++;
                            	      fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble( fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute1 = file1.getAttribute("size");
                            	      attribute1.setValue(estimatedValue);
                            	      GATKP1_common.add(estimatedValue);
                		 
                		 
                	  
                	 }
                	 if(GetBlockName.COVERAGE_N.contains(nodeName)){
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            	    
                            	      Attribute attribute = file0.getAttribute("size");
                                      attribute.setValue(GATKP1_common.get(GATKP1_count));
                                      GATKP1_count++;
                                      fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble( fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute1 = file1.getAttribute("size");
                            	      attribute1.setValue(estimatedValue);
                            	      COVERAGE_common.add(estimatedValue);
                	   
                	 }
                	 if(GetBlockName.VARIANTA.contains(nodeName)){
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            		  fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble(fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute = file1.getAttribute("size");
                            	      attribute.setValue(estimatedValue); 
                	    
                	  
                	 }
                	 
                	 if(GetBlockName.HAPLOTYPECALLER_N.contains(nodeName)){
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            		  fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble(fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute = file1.getAttribute("size");
                            	      attribute.setValue(estimatedValue); 
                	    
                	 }
                	 if(GetBlockName.VARIANTB.contains(nodeName)){
                         
                	     System.out.println("VARIANTB      "+nodeName);
                	 }
                	 if(GetBlockName.VCF_N.contains(nodeName)){
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            		  fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble(fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute = file1.getAttribute("size");
                            	      attribute.setValue(estimatedValue); 
                            	      VCF_common.add(estimatedValue);
                	     
                	 }
                	 if(GetBlockName.GATKP3.contains(nodeName)){
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            		  fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble(fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute = file1.getAttribute("size");
                            	      attribute.setValue(estimatedValue); 
                            	      GATKP3_common.add(estimatedValue);
                	     
                	 }
                	 if(GetBlockName.ANNOTATE_N.contains(nodeName)){
                		 List list = node1.getChildren();
            		     Element file0 = (Element) list.get(0);
                         Element file1 = (Element) list.get(1);
                                      String inout = file0.getAttributeValue("link");
                            	      
                            	      Attribute attribute = file0.getAttribute("size");
                                      attribute.setValue(VCF_common.get(VCF_count));
                            	      VCF_count++;
                            	      fileSize1 = file0.getAttributeValue("size");
                            	      size1 = Double.parseDouble( fileSize1);
                            	      size1 = data1.getOutputData(nodeName,inout ,size1);
                            	      NumberFormat formatter = new DecimalFormat("##############");  
                                      String estimatedValue = formatter.format(size1);  
                            	      Attribute attribute1 = file1.getAttribute("size");
                            	      attribute1.setValue(estimatedValue);
                            	      ANNOTATE_common.add(estimatedValue);
                	     
                	 }
                	 
                	
                	 }
                
               
                 
                 }

                 XMLOutputter xmlOutput = new XMLOutputter();
                 xmlOutput.setFormat(Format.getPrettyFormat());
                 xmlOutput.output(dom1,ps);
                                  
        	
           // SAXBuilder builder = new SAXBuilder();
            //parse using builder to get DOM representation of the XML file
          //  Document dom = builder.build(new File(path));
            Element root = dom1.getRootElement();           
            List list = root.getChildren();
       //     System.out.println("The list"+ list);
            int idIndex = 1;
            int idIndexlib = 1;
            int itemType;
            String itemName;
            for (Iterator it = list.iterator(); it.hasNext();) {
                Element node = (Element) it.next();
                if ((node.getName().toLowerCase().equals("job")) || (node.getName().toLowerCase().equals("task"))){
                   	String fileName1;
                	long length = 0;
                    String nodeName = node.getAttributeValue("id");                    
                    String nodeType = node.getAttributeValue("name");
                    String islibrary = node.getAttributeValue("islibrary");
                    String storage = node.getAttributeValue("storage");
                    itemName = nodeName;
                    if (islibrary.equals("yes")){itemType=1;}
                    else{ itemType=0;};
                  
                    /**
                     * capture runtime. If not exist, by default the runtime is
                     * 0
                     */
                    double runtime = 0.0;
                     if (this.mName2Runtime.containsKey(nodeName)) {
                         runtime = 1000 * (Double) this.mName2Runtime.get(nodeName);
                    	 //runtime =   this.mName2Runtime.get(nodeName); 
                        length = (long) runtime;
                    } else if (node.getAttributeValue("runtime") != null) {
                        String nodeTime = node.getAttributeValue("runtime");
                        runtime = 1000*Double.parseDouble(nodeTime);
                        length = (long) runtime;
                     
                                         
                    } else {
                        Log.printLine("Cannot find runtime for " + nodeName + ",set it to be 0");
                    }
                    //multiple the scale, by default it is 1.0
                    length *= Parameters.getRuntimeScale();

                    List fileList = node.getChildren();

                    List mFileList = new ArrayList<>(); // Check here
                   
                    
                    /**
                     * capture file.
                     */
                    
                    for (Iterator itf = fileList.iterator(); itf.hasNext();) {
                        Element file = (Element) itf.next();
                           if (file.getName().toLowerCase().equals("uses")) {
                        
                        	String fileName = file.getAttributeValue("name");//DAX version 3.3
                        	
                            if (fileName == null) {
                                fileName = file.getAttributeValue("file");//DAX version 3.0
                               
                               
                            }   
                            
                                          
                            if (fileName == null) {
                                Log.print("Error in parsing xml");
                            }

                            String inout = file.getAttributeValue("link");
                            double size = 0.0;
                            if (this.mName2Size.containsKey(fileName)) {
                                size = (Double) this.mName2Size.get(fileName) /*/ 1024*/;//now it is KB
                               
                            } else {
                            
                                String fileSize = file.getAttributeValue("size");
                                                       	
                              //  GetOutputData data = new GetOutputData();
                              //  double fileSize1 = data.getOutputData(nodeName,inout ,size);
                                
                                if (fileSize != null) {
                                	/*/ 1024 (Convert the size to scientific number e.g. 2.13E+08*/
                                    size = Double.parseDouble(fileSize); 
                                    
                                             
                                } else {
                                    Log.printLine("File Size not found for " + fileName);
                                }
                            }
                            /**
                             * a bug of cloudsim, size 0 causes a problem. 1 is
                             * ok.
                             */
                            if (size == 0) {
                                size++;
                            }
                            double runtime2=runtime;
                          
                            //System.out.println("The  output size   >>>"+size+nodeName);
                            //if (nodeName.equals("BWA1_FEL")){ runtime =(size-(2E+09))/(829972);System.out.println("rrrrrrrrr"+  runtime+"KKKKKKKK"+runtime2+"zzzzzzzzzzz"+size);length= (long) runtime;}
                            GetRunTime one= new GetRunTime();
                            GetOutputData data = new GetOutputData();
                           

                           // System.out.println("The class output of RUN TIME of node "+nodeName+"  "+(long) one.getRunTime(nodeName,inout ,size)+ "  "+runtime);
                     if (inout.equals("input")){length = (long) one.getRunTime(nodeName,inout ,size);
                                                };
             
                     //  if (inout.equals("input")){System.out.println("The  Data output of task  "+nodeName+" = "+ runtime);};
                            /**
                             * Sets the file type 1 is input 2 is output
                             */
                           FileType type =FileType.NONE;
                           
                           switch (inout) {
                           case "input":
                               type = FileType.INPUT;
                               break;
                           case "output":
                               type = FileType.OUTPUT;
                               break;
                           default:
                               Log.printLine("Parsing Error");
                               break;
                       }
                            FileItem tFile = new FileItem (fileName, (int) size,size);
                            /*
                             * Already exists an input file (forget output file)
                             */
                            if (size < 0) {
                                /*
                                 * Assuming it is a parsing error
                                 */
                                size = 0 - size;
                                Log.printLine("Size is negative, I assume it is a parser error");
                            }
                            if (type == FileType.OUTPUT) {
                                /**
                                 * It is good that CloudSim does tell whether a
                                 * size is zero
                                 */
                            	//FileItem tFile = new FileItem (fileName, (int) size,size);
                                
                               
                            } else if (ReplicaCatalog.containsFile(fileName)) {
                                tFile = ReplicaCatalog.getFile(fileName);
                            } else {

                              
                              
                                ReplicaCatalog.setFile(fileName, tFile);
                            }
                        //    System.out.println("The Data size is:"+fileName+tFile.getDatasize());
                             tFile.setType(type);
                             mFileList.add(tFile);
                            
                            

                        }
                               
                    }
 
                  
                   	Task task = new Task(idIndex, length);
                    synchronized (this) {
                        task = new Task(this.jobIdStartsFrom, length);
                        this.jobIdStartsFrom++;
                    }
                   	
                    task.setType(nodeType);
                    task.setUserId(userId); 
                    task.setItemName(itemName);
                    task.setItemType(itemType);
                    
                    
                   
                    idIndex++;
                    mName2Task.put(nodeName, task);
                    if (  !(storage  == null)) {
                    	          Double x=0.0;
                                  x = Double.parseDouble(storage);
                                   task.setStorage(x);}
                       	
                                     
                     
                    for (Iterator itm = mFileList.iterator(); itm.hasNext();) {
                        FileItem file = (FileItem) itm.next();
                        task.addRequiredFile(file.getName());
                       
                        
                     }
                    task.setFileList(mFileList);
                    this.getTaskList().add(task);
                    if(islibrary.equals("yes")) {this.getLibraryList().add(task);}
                    }
                    
                    
                    
                    /**
                     * Add dependencies info.
                     */
                  
            
                else if (node.getName().toLowerCase().equals("child")) {
               	    List pList = node.getChildren();
               	   
                    String childName = node.getAttributeValue("ref");
                    if (mName2Task.containsKey(childName)) {
                    	
                        Task childTask = (Task) mName2Task.get(childName);
                        
                        for (Iterator itc = pList.iterator(); itc.hasNext();) {
                            Element parent = (Element) itc.next();
                            String parentName = parent.getAttributeValue("ref");
                            if (mName2Task.containsKey(parentName)) {
                            	
                               	Task parentTask = (Task) mName2Task.get(parentName);
                                parentTask.addChild(childTask);
                                childTask.addParent(parentTask);
                          //      System.out.println("The output"+childTask.getType()+parentTask.getType()) ;                            
                            }

                        }
                        
                    }   
                    
                        if (mName2Library.containsKey(childName)) {
                        Library childLibrary = (Library) mName2Library.get(childName);
                      
                        for (Iterator itc1 = pList.iterator(); itc1.hasNext();) {
                            Element parent = (Element) itc1.next();
                            String parentName = parent.getAttributeValue("ref");
                                                 
                            if ( mName2Task.containsKey(parentName) ) {
                               	Task parentTask = (Task) mName2Task.get(parentName);
                                parentTask.addChild(childLibrary);
                                childLibrary.addParent(parentTask);
                         //       System.out.println("The output"+childLibrary.getType()+parentTask.getType()) ;                       
                            }

                        }
                        
                    }  
                               
                }
                
                
                
              /** else if (node.getName().toLowerCase().equals("lib")){
                
                	
                	String taskName = node.getAttributeValue("task");
                	 
                	 List fileList = node.getChildren();
                	for (Iterator itf = fileList.iterator(); itf.hasNext();){
                		Element file = (Element) itf.next();
                        if (file.getName().toLowerCase().equals("service")) {
                        	String instanceName = file.getAttributeValue("instance");
                      	                         	
                	              System.out.println("Task  "+taskName+" needs service instance " +instanceName);}}
                }*/
            }
          //   getTaskList().addAll(getLibraryList());
             
            /**
             * If a task has no parent, then it is root task.
             */
            ArrayList roots = new ArrayList<Task>();
            for (Iterator it = mName2Task.values().iterator(); it.hasNext();) {
                Task task = (Task) it.next();
                task.setDepth(0);
                if (task.getParentList().isEmpty()) {
                    roots.add(task);
                }
            }
            
            ArrayList roots1 = new ArrayList<Library>();
            for (Iterator it = mName2Library.values().iterator(); it.hasNext();) {
                Library library = (Library) it.next();
                library.setDepth(0);
                if (library.getParentList().isEmpty()) {
                    roots1.add(library);
                }
            }
            
            /**
             * Add depth from top to bottom.
             */
            for (Iterator it = roots.iterator(); it.hasNext();) {
                Task task = (Task) it.next();
                setDepth(task, 1);
            }
            
            for (Iterator it = roots1.iterator(); it.hasNext();) {
                Library librray = (Library) it.next();
                setDepth(librray, 1);
            }
          
          //  for(int i=0;i<getTaskList().size();i++){
          //      System.out.println("Type>>>>>>>>>>"+getTaskList().get(i).getTaskStorage()+getTaskList().get(i).getType()+getTaskList().get(i).getItemType());	
          //     
           // }
            
            /**
             * Clean them so as to save memory. Parsing workflow may take much memory
             */
            this.mName2Runtime.clear();
            this.mName2Size.clear();
            this.mName2Task.clear();
            this.mName2Storage.clear(); //?
            

        } catch (JDOMException jde) {
            Log.printLine("JDOM Exception;Please make sure your dax file is valid");

        } catch (IOException ioe) {
            Log.printLine("IO Exception;Please make sure dax.path is correctly set in your config file");

        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("Parsing Exception");
        
        }
            
    }

	private void getRunTime(String nodeName, double size) {
		// TODO Auto-generated method stub
		
	}
    
}

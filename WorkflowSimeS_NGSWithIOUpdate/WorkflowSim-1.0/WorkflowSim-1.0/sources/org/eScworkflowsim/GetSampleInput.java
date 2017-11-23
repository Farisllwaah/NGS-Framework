/*******************************************************************************
 * Title: The class defines the path of the NGS pipeline workflow
 * Description: This class specify the path of the NGS pipeline 
 * as well provides real execution time
 * 
 * @author : Faris Llwaah
 * Date: May 2016
 *
 * Address: f.llwaah@ncl.ac.uk
 * Source: https://github.com/Farisllwaah/NGS-Framework
 *
 * Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 * Copyright (c) 2016, Newcastle University, UK.
 *******************************************************************************/

package org.eScworkflowsim;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/******************************************88
 * This calss classify the input string to pick up the type of NGS pipeline workflow
 * As well it will specify the acutall execution time of that NGS pipeline which is
 * extrxcted from analysining provenance file.
 * 
 * @author Faris Lwaah
 *
 */
public class GetSampleInput {
    private String[] path;
//	public GetSampleInput() {
//		this.path[0]=null;
//	}
    public String[] getSampleInput( String type){
     
    	switch(type){
    	
    	 case "All":
    		 		 
        	 String  pathAll[]=  {"C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing531881-6SAT.xml",
        	                      "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing325899-6SAT.xml",
        	                      "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing334414-6SAT.xml", 
                                  "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing342940-10SATtest.xml",
                                  "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing354599-10SATtest.xml", 
                                  "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing379482-12SAT.xml",
                                  "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing366259-12SAT.xml"};
        	   path=pathAll;
        	  break;
    	 case "26":
        	String path26[]={ "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing325899-6SAT.xml",
                              "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing334414-6SAT.xml"};
        	
        	path=path26;
        	break;
    	 case "36":
        	String path36[]={"C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing531881-6SAT.xml", 
                             "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing325899-6SAT.xml",
                             "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing334414-6SAT.xml"};
        	path=path36;
        	break;
        	
    	  case "36210":
        	String path36210[]={"C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing531881-6SAT.xml", 
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing325899-6SAT.xml",
                   "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing334414-6SAT.xml", 
                   "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing342940-10SATtest.xml",
                  "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing354599-10SATtest.xml"};
        	path=path36210;
        	
        	break;
        	
    	  case "210":
        	String path210[]={"C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing342940-10SATtest.xml",
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing354599-10SATtest.xml"};
        	path=path210;
        	break;
        	
    	  case "212":
        	
        	String path212[]={"C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing379482-12SAT.xml",
            "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing366259-12SAT.xml"};
           path=path212;
           break;
           
    	  case "112-24Core":
    		String path112[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing91896n-12S-24Core.XML"};
            path=path112;
            
            break;
    	  case "124-24Core":
      		  String path124_24Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing105239n-24S-24Core.XML"};
              path=path124_24Core;
              
              break;
    	  case "124-24CoreBroke-VCF":
      		  String path124_24CoreBroke[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-24CoreBroke-VCF/NGS processing105239n-24S-24CoreBroke-VCF.XML"};
              path=path124_24CoreBroke;
              
              break;   
    	  case "124-24CoreBroke-COVERAGE":
      		  String path124_24CoreBrokeCOVERAGE[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-24CoreBroke-COVERAGE/NGS processing105239n-24S-24Core-COVERAGE.XML"};
              path=path124_24CoreBrokeCOVERAGE;
              
              break;         
    	  case "124-48Core":
      		  String path124_48Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing14861n-24S-48Core.XML"};
              path=path124_48Core;
              
              break;    
    	  case "56-24Core":
      		String path56_24Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing23820n-6S-24Core-Not complete.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing47025n-6S-24Core-Not complete.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing54668n-6S-24Core-Not complete.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing62061n-6S-24Core-Not complete.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing69927n-6S-24Core.XML"
      				 				
      				
      		};
              path=path56_24Core;
              
              break;
    	  case "56-24CoreBroke-VCF":
        		String path56_24CoreBroke[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-VCF/NGS processing23820n-6S-24CoreBroke-VCF.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-VCF/NGS processing47025n-6S-24CoreBroke-VCF.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-VCF/NGS processing54668n-6S-24CoreBroke-VCF.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-VCF/NGS processing62061n-6S-24CoreBroke-VCF.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-VCF/NGS processing69927n-6S-24CoreBroke-VCF.XML"
        				 				
        				
        		};
                path=path56_24CoreBroke;
                
                break;   
    	  case "56-24CoreBroke-COVERAGE":
      		String path56_24CoreBrokeCOVERAGE[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-COVERAGE/NGS processing23820n-6S-24CoreBroke-COVERAGE.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-COVERAGE/NGS processing47025n-6S-24CoreBroke-COVERAGE.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-COVERAGE/NGS processing54668n-6S-24CoreBroke-COVERAGE.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-COVERAGE/NGS processing62061n-6S-24CoreBroke-COVERAGE.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-COVERAGE/NGS processing69927n-6S-24CoreBroke-COVERAGE.XML"
      				 				
      				
      		};
              path=path56_24CoreBrokeCOVERAGE;
              
              break;        
                
    	  case "26-24CoreBroke-VCF":
      		String path26_24CoreBroke[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-VCF/NGS processing23820n-6S-24CoreBroke-VCF.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/6Samples Broken/6Sample-24CoreBroke-VCF/NGS processing47025n-6S-24CoreBroke-VCF.XML",
      				
      				
      		};
              path=path26_24CoreBroke;
              
              break;         
    	  case "312-24Core":
        		String path312_24Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing34721n-12S-24Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing78086n-12S-24Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing91896n-12S-24Core.XML",
        				         			       				
        		};
                path=path312_24Core;
                
                break; 
    	  case "312-24CoreBroke-LOWANNOTATE":
      		String path312_24Core_LOWANNOTATE[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing34721n-12S-24Core.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing78086n-12S-24Core.XML",
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing91896n-12S-24Core.XML",
      				         			       				
      		};
              path=path312_24Core_LOWANNOTATE;
              
              break; 
    	  case "312-24CoreBroke-HIGHANNOTATE":
        		String path312_24Core_HIGHANNOTATE[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing34721n-12S-24Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing78086n-12S-24Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing91896n-12S-24Core.XML",
        				         			       				
        		};
                path=path312_24Core_HIGHANNOTATE;
                
                break;    
       	  case "312-96Core":
           		String path312_96Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing215882n-12S-96Core.XML",
           				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing229282n-12S-96Core.XML",
           				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing242680n-12S-96Core.XML",
           				         			       				
           		};
                   path=path312_96Core;
                   
                   break;      
                
    	  case "26-12Core":
        		String path26_12Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing325899n-6S-12Core.XML",
        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing334414n-6S-12Core.XML",
        				 
        				         			       				
        		};
                path=path26_12Core;
                
                break;   
                
    	  case "36-12Core":
      		String path36_12Core[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing325899n-6S-12Core.XML",
      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing334414n-6S-12Core.XML",
      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing531881n-6S-12Core.XML",
      				         			       				
      		};
              path=path36_12Core;
              
              break;    
              
              
              
              
              
    	  case "36-12Core-AzureNew":
//        		String path36_12Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing12372n-6S-12Core(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing21058n-6S-12Core(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing54689n-6S-12Core(AzureNew).XML",
//        				         			       				
//        		};
        		
        		
        		String path36_12Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing531881n-6S-12Core-(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing21058n-6S-12Core(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing54689n-6S-12Core(AzureNewH).XML",
       				         			       				
       		};
    		  
    		  
//      		String path36_12Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing62061n-6S-12Core(AzureNewH).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing69927n-6S-12Core(AzureNewH).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing54689n-6S-12Core(AzureNewH).XML",
//      				         			       				
//      		};       		
        		
                path=path36_12Core_AzureNew;
                
                break;     
                
    	  case "56-12Core-AzureNew":           
    		  String path56_12Core_AzureNew[]={
    				  "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing21058n-6S-12Core(AzureNewH).XML",
    				  "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing54689n-6S-12Core(AzureNewH).XML",  
    				  "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing54668n-6S-12Core(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing62061n-6S-12Core(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing69927n-6S-12Core(AzureNewH).XML",
       				         			       				
       		};       		
         		
                 path=path56_12Core_AzureNew;
                 
                 break;     
                
                
              
    	  case "312-12Core-AzureNew":
//      		String path312_12Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing40541n-12S-12Core-(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing63392n-12S-12Core-(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing87903n-12S-12Core-(AzureNew).XML",
//      				         			       				
//      		};
//              path=path312_12Core_AzureNew;
              
              
              String path312_12Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing40541n-12S-12Core-(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing63392n-12S-12Core-(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing87903n-12S-12Core-(AzureNewH).XML",
       				         			       				
       		};
               path=path312_12Core_AzureNew;
              break; 
    	  case "324-12Core-AzureNew":
//        		String path324_12Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing101386n-24S-12Core -(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing124420n-24S-12Core -(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing147454n-24S-12Core -(AzureNew).XML",
//        				         			       				
//        		};
//                path=path324_12Core_AzureNew;
//                
                
                String path324_12Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing101386n-24S-12Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing124420n-24S-12Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing147454n-24S-12Core -(AzureNewH).XML",
       				         			       				
       		};
               path=path324_12Core_AzureNew;
                break;    
                
    	  case "36-24Core-AzureNew":
//      		String path36_24Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing489142n-6S-24Core -(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing505245n-6S-24Core -(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing528536n-6S-24Core -(AzureNew).XML",
//      				         			       				
//      		};
//              path=path36_24Core_AzureNew;
              
              
              String path36_24Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing489142n-6S-24Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing505245n-6S-24Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing528536n-6S-24Core -(AzureNewH).XML",
       				         			       				
       		};
               path=path36_24Core_AzureNew;  
              break;
              
    	  case "312-24Core-AzureNew":
//        		String path312_24Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing395210n-12S-24Core -(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing408696n-12S-24Core -(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing422178n-12S-24Core -(AzureNew).XML",
//        				
//        		};
//                path=path312_24Core_AzureNew;
    		  String path312_24Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing395210n-12S-24Core -(AzureNewH).XML",
     				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing408696n-12S-24Core -(AzureNewH).XML",
     				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing422178n-12S-24Core -(AzureNewH).XML",
     				
     		};
             path=path312_24Core_AzureNew; 
                
                
                break;   
    	  case "324-24Core-AzureNew":
//      		String path324_24Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing297004n-24S-24Core -(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing443071n-24S-24Core -(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing466109n-24S-24Core -(AzureNew).XML",
//      				
//      		};
//              path=path324_24Core_AzureNew;
              
              String path324_24Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing297004n-24S-24Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing443071n-24S-24Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing466109n-24S-24Core -(AzureNewH).XML",
       				
       		};
               path=path324_24Core_AzureNew;   
              
              break;   
    	  case "36-48Core-AzureNew":
//      		String path36_48Core_AzureNew[]={
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing331472n-6S-48Core - (AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing340186n-6S-48Core -(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing348896n-6S-48Core -(AzureNew).XML",
//      		};
//              path=path36_48Core_AzureNew;
            
              
              String path36_48Core_AzureNew[]={
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing331472n-6S-48Core - (AzureNew).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing340186n-6S-48Core -(AzureNew).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing348896n-6S-48Core -(AzureNew).XML",
       		};
               path=path36_48Core_AzureNew;   
              break;      
              
//    	  case "46-48Core-AzureNew":
//        		String path46_48Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing322770n-6S-48Core-(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing331472n-6S-48Core - (AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing340186n-6S-48Core -(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing348896n-6S-48Core -(AzureNew).XML",
//        		};
//                path=path46_48Core_AzureNew;
                
                
                
    	  case "46-48Core-AzureNew":
      		String path46_48Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing322770n-6S-48Core-(AzureNewH).XML",
      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing331472n-6S-48Core - (AzureNewH).XML",
      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing340186n-6S-48Core -(AzureNewH).XML",
      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing348896n-6S-48Core -(AzureNewH).XML",
      		};
              path=path46_48Core_AzureNew;    
                
                break;     
    	  case "312-48Core-AzureNew":
//      		String path312_48Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing271276n-12S-48Core -(AzureNew).XML",
//      				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing284786n-12S-48Core -(AzureNew).XML",
//      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing309283n-12S-48Core -(AzureNew).XML",
//      				
//      		};
//              path=path312_48Core_AzureNew;
              
              String path312_48Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing271276n-12S-48Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing284786n-12S-48Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing309283n-12S-48Core -(AzureNewH).XML",
       				
       		};
               path=path312_48Core_AzureNew;
              
              break;  
      /*******************************************************************************/        
    	  case "312-48Core-AzureNew1":
//    		String path312_48Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing271276n-12S-48Core -(AzureNew).XML",
//    				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/NGS processing284786n-12S-48Core -(AzureNew).XML",
//    				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing309283n-12S-48Core -(AzureNew).XML",
//    				
//    		};
//            path=path312_48Core_AzureNew;
            
            String path312_48Core_AzureNew1[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing309283n-12S-48Core -(AzureNewH).XML",
     				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing229282n-12S-48Core(AzureNewH).XML",
     				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 12S files/12SamplesHaplotype/NGS processing242680n-12S-48Core(AzureNewH).XML",
     				
     		};
             path=path312_48Core_AzureNew1;
            
            break;              
              
      /********************************************************************************/        
              
              
              
              
    	  case "324-48Core-AzureNew":
//        		String path324_48Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing188803n-24S-48Core -(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing211863n-24S-48Core -(AzureNew).XML",
//        				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/NGS processing357603n-24S-48Core -(AzureNew).XML",
//        				
//        		};
//                path=path324_48Core_AzureNew;
                
                
                String path324_48Core_AzureNew[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing188803n-24S-48Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing211863n-24S-48Core -(AzureNewH).XML",
       				 "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 24S files/24SamplesHaplotype/NGS processing357603n-24S-48Core -(AzureNewH).XML",
       				
       		};
               path=path324_48Core_AzureNew;
                break;    
    	  case "16-12Core-AzureNew":
        		String path112_24Core_AzureNew[]={   "E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS processing531881n-6S-12Core-(AzureNewH).XML",
        				         			       				
        		};
                path=path112_24Core_AzureNew;
                
                break;   
              
    	  case "36+36-12+24Core":
        		String path3636_12Core[]={"E:/WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/NGS processing325899n-6S-12Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing334414n-6S-12Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing531881n-6S-12Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing54668n-6S-24Core-Not complete.XML",
          				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing62061n-6S-24Core-Not complete.XML",
          				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing69927n-6S-24Core.XML"
          				 				
          				        			       				
        		};
                path=path3636_12Core;
                
                break;     
    	  case "212-48Core":
      		String path212_48Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing319871n-12S-48Core-Not complete.XML",
      			 	 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing330890n-12S-48Core-Not complete.XML",
      				 
      				         			       				
      		};
              path=path212_48Core;
              
           break;  
    	  case "212-48CoreBroke-VCF":
        		String path212_48CoreBrokeVCF[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/12SampleBroken/12Sample-48CoreBroke-VCF/NGS processing319871n-12S-48CoreBroke-VCF.XML",
        			 	                         "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/12SampleBroken/12Sample-48CoreBroke-VCF/NGS processing330890n-12S-48CoreBroke-VCF.XML",
        				 
        				         			       				
        		};
                path=path212_48CoreBrokeVCF;
                
             break;  
    	  case "212-48CoreBroke-COVERAGE":
      		String path212_48CoreBrokeCOVERAGE[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/12SampleBroken/12Sample-48CoreBroke-COVERAGE/NGS processing319871n-12S-48CoreBroke-COVERAGE.XML",
      			 	                              "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/12SampleBroken/12Sample-48CoreBroke-COVERAGE/NGS processing330890n-12S-48CoreBroke-COVERAGE.XML",
      				 
      				         			       				
      		};
              path=path212_48CoreBrokeCOVERAGE;
              
           break;      
    	  case "224-48Core":
      		String path224_48Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/12SampleBroken/NGS processing14861n-24S-48Core.XML",
      				
      				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing16863n-24S-48Core.XML",
      				 
      				         			       				
      		};
              path=path224_48Core;
              
           break; 
           
    	  case "324-48Core":
        		String path324_48Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing14861n-24S-48Core.XML",
        				"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing16011n-24S-48Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing16863n-24S-48Core.XML",
        				 
        				         			       				
        		};
                path=path324_48Core;
                
             break; 
    	  case "324-48CoreBroke-VCF":
      		String path324_48CoreBrokeVCF[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-48CoreBroke-VCF/NGS processing14861n-24S-48Core-VCF.XML",
      			                           	"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-48CoreBroke-VCF/NGS processing16011n-24S-48Core-VCF.XML",
      			                           	"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-48CoreBroke-VCF/NGS processing16863n-24S-48Core-VCF.XML",
      				 
      				         			       				
      		};
              path=path324_48CoreBrokeVCF;
              
           break; 
    	  case "324-48CoreBroke-COVERAGE":
        		String path324_48CoreBrokeCOVERAGE[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-48CoreBroke-COVERAGE/NGS processing14861n-24S-48Core-COVERAGE.XML",
        			                             	"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-48CoreBroke-COVERAGE/NGS processing16011n-24S-48Core-COVERAGE.XML",
        			                            	"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-48CoreBroke-COVERAGE/NGS processing16863n-24S-48Core-COVERAGE.XML",
        				 
        				         			       				
        		};
                path=path324_48CoreBrokeCOVERAGE;
                
             break; 
    	  case "224-48CoreBroke-GATK3":
        		String path224_48CoreBroke[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/With Break-VCF/NGS processing14861n-24S-48Core-Broke.XML",
        			 
        				"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/With Break-VCF/NGS processing16863n-24S-48Core-Broke.XML",
        				 
        				         			       				
        		};
                path=path224_48CoreBroke;
                
             break;  
    	  case "324-96Core":
      		String path324_96Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing129227n-24S-96Core.XML",
      				                 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing152109n-24S-96Core.XML",
      				                 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/NGS processing193015n-24S-96Core.XML",
      				 
      				         			       				
      		};
              path=path324_96Core;
              
           break;    
    	  case "324-96CoreBroke-VCF":
        		String path324_96CoreBrokeVCF[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-96CoreBroke-VCF/NGS processing129227n-24S-96Core-VCF.XML",
        				                         "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-96CoreBroke-VCF/NGS processing152109n-24S-96Core-VCF.XML",
        				                         "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-96CoreBroke-VCF/NGS processing193015n-24S-96Core-VCF.XML",
        				 
        				         			       				
        		};
                path=path324_96CoreBrokeVCF;
                
             break;  
    	  case "324-96CoreBroke-COVERAGE":
      		String path324_96CoreBrokeCOVERAGE[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-96Broke-COVERAGE/NGS processing129227n-24S-96Core-COVERAGE.XML",
      				                              "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-96Broke-COVERAGE/NGS processing152109n-24S-96Core-COVERAGE.XML",
      				                              "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/24Sample Broken/24Sample-96Broke-COVERAGE/NGS processing193015n-24S-96Core-COVERAGE.XML",
      				 
      				         			       				
      		};
              path=path324_96CoreBrokeCOVERAGE;
              
           break;    
    	  case "324-96CoreBroke-GATK3":
        		String path324_96CoreBroke[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/With Break-VCF/NGS processing129227n-24S-96Core-Broke.XML",
        				                      "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/With Break-VCF/NGS processing152109n-24S-96Core-Broke.XML",
        				                      "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 24S files/With Break-VCF/NGS processing193015n-24S-96Core-Broke.XML",
        				 
        				         			       				
        		};
                path=path324_96CoreBroke;
                
             break;   
    	  case "212-12Core":
        		String path212_12Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing379482n-12S-12Core.XML",
        				          				  "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing366259n-12S-12Core.XML",
        				         			       				
        		};
                path=path212_12Core;
                
             break;    
    	  case "512-12+24Core":
      		String path512_12_24Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing379482n-12S-12Core.XML",
      				          				"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing366259n-12S-12Core.XML",
      				          				"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing34721n-12S-24Core.XML",
      				        				"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing78086n-12S-24Core.XML",
      				        				"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing91896n-12S-24Core.XML",
      				        		 			
      		};
              path=path512_12_24Core;
              
           break; 
                
    	  case "56+312-24Core":
        		String path56312_24Core[]={"C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing23820n-6S-24Core-Not complete.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing47025n-6S-24Core-Not complete.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing54668n-6S-24Core-Not complete.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing62061n-6S-24Core-Not complete.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 6S files/NGS processing69927n-6S-24Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing34721n-12S-24Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing78086n-12S-24Core.XML",
        				 "C:/Users/LLWAAH/Desktop/DAX files with cores/All- actual 12S files/NGS processing91896n-12S-24Core.XML",				
        				
        		};
                path=path56312_24Core;
                
                break;    
                
                
            
    	  case "26210212":
        	String path26210212[]={ 
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing325899-6SAT.xml",
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing334414-6SAT.xml", 
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing342940-10SATtest.xml",
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing354599-10SATtest.xml", 
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing379482-12SAT.xml",
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing366259-12SAT.xml"};
        	path=path26210212;
        	break;
        	
    	  case "26210":
        	String path26210[]={ 
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing325899-6SAT.xml",
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing334414-6SAT.xml", 
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing342940-10SATtest.xml",
                    "C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/NGS processing354599-10SATtest.xml", 
                     };
    	
    	   path=path26210;
    	   break;
    	
    	  case "Test":
    		  String pathTest[]={"C:/Users/LLWAAH/Desktop/Azure/WorkflowSim-1.0/WorkflowSim-1.0/config/dax/Test-data.xml"};
    	   path=pathTest;
    	   break;
    	
    	}
    	
    	
		return path;
		 
    }
    @SuppressWarnings("null")
	public void getResultarry( List<String> result,int t1,int t2,int t3,int t4,int t5,int t6, int t7, String t8){
    
    	
    	 PrintStream ps = null;
 		try {
 			ps = new   PrintStream(  "out1.txt"  );
 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
 		
 	        
       
       int elementCount=0;
       double minimumError=1E10;
       int MinMips=0;
       int MinBw=0;
       double error = 0;
       double Temvalue1=0.0;
       double Temvalue2=0.0;
       double Temvalue3=0.0;
       double Temvalue4=0.0;
       double Temvalue5=0.0;
       double Temvalue6=0.0;
       double Temvalue7=0.0;
       double Temvalue8=0.0;
      
       DecimalFormat df = new DecimalFormat("#.####");
      // System.out.println(" The output number ="+size);
          
           for (int b=t1 ; b<=t2; b+=t3){
           ps.print(" BW="+b+"|") ;        
                 
           for (int k=t4; k<=t5; k+=t6){  
            double value1=0.0;
            double value2=0.0;
            double value3=0.0;
            double value4=0.0;
            double value5=0.0;
            double value6=0.0;
            double value7=0.0;
            double value8=0.0;
            switch(t7){
            case 1: 
            	 value1 = Double.parseDouble(result.get(elementCount));
            break;
            case 2:
             	   value1 = Double.parseDouble(result.get(elementCount));
         	       value2 = Double.parseDouble(result.get(elementCount+1));
            break;
            case 3:
            	value1 = Double.parseDouble(result.get(elementCount));
      	        value2 = Double.parseDouble(result.get(elementCount+1));
      	        value3 = Double.parseDouble(result.get(elementCount+2));
      	    break;
            case 4:
            	 value1 = Double.parseDouble(result.get(elementCount));
                 value2 = Double.parseDouble(result.get(elementCount+1));
        	     value3 = Double.parseDouble(result.get(elementCount+2));
        	     value4 = Double.parseDouble(result.get(elementCount+3));
            break;
            case 5:
            	value1 = Double.parseDouble(result.get(elementCount));
                value2 = Double.parseDouble(result.get(elementCount+1));
       	        value3 = Double.parseDouble(result.get(elementCount+2));
       	        value4 = Double.parseDouble(result.get(elementCount+3));
       	        value5 = Double.parseDouble(result.get(elementCount+4));
       	     break;
            case 6:
            	value1 = Double.parseDouble(result.get(elementCount));
                value2 = Double.parseDouble(result.get(elementCount+1));
       	        value3 = Double.parseDouble(result.get(elementCount+2));
       	        value4 = Double.parseDouble(result.get(elementCount+3));
       	        value5 = Double.parseDouble(result.get(elementCount+4));
       	        value6 = Double.parseDouble(result.get(elementCount+5));
       	    break;
            case 7:
            	value1 = Double.parseDouble(result.get(elementCount));
                value2 = Double.parseDouble(result.get(elementCount+1));
       	        value3 = Double.parseDouble(result.get(elementCount+2));
       	        value4 = Double.parseDouble(result.get(elementCount+3));
       	        value5 = Double.parseDouble(result.get(elementCount+4));
       	        value6 = Double.parseDouble(result.get(elementCount+5));
       	        value7 = Double.parseDouble(result.get(elementCount+6));
       	     break;
            case 8:
            	value1 = Double.parseDouble(result.get(elementCount));
                value2 = Double.parseDouble(result.get(elementCount+1));
       	        value3 = Double.parseDouble(result.get(elementCount+2));
       	        value4 = Double.parseDouble(result.get(elementCount+3));
       	        value5 = Double.parseDouble(result.get(elementCount+4));
       	        value6 = Double.parseDouble(result.get(elementCount+5));
       	        value7 = Double.parseDouble(result.get(elementCount+6));
       	        value8 = Double.parseDouble(result.get(elementCount+7));
       	     break; 
       	     
            default: 
             }
         
        	   
        	   

     	     
     	     //   	 double  error= (java.lang.Math.pow((1060.56-value1),2));
  //********************************************************************//
  //   Equations to find error power two for All Samples                //
  //********************************************************************//   	       
     	   
  //********************************************************************************//
  //   Equations to find error power two for 2 6-Samples & 2 10-Samples 2 12-Samples//
  //********************************************************************************//  
      	 
  //********************************************************************//
  //   Equations to find error power two for 2 6-Samples & 2 10-Samples //
  //********************************************************************//
     	       

                      	 
            
            
     	     
     	    
           switch(t8){
           case "112-12Core":
     	      error= (java.lang.Math.pow((2183.10-value1),2));
           break;
           case "112-24Core":
      	      error= (java.lang.Math.pow((1471.10-value1),2));
            break;
            
           case "124-24Core":
       	      error= (java.lang.Math.pow((3101.55-value1),2));
             break;
           case "124-48Core":
        	      error= (java.lang.Math.pow((1725.09-value1),2));
              break;  
             
           case "56-24Core":
        	   error= (java.lang.Math.pow((1085.19-value1),2))+
        	          (java.lang.Math.pow((1125.44-value2),2))+  
        	          (java.lang.Math.pow((901.26-value3),2))+
  	                  (java.lang.Math.pow((907.2-value4),2))+
                      (java.lang.Math.pow((919.14-value5),2));
             break; 
           case "56-24CoreBroke-VCF":
        	   error= (java.lang.Math.pow((1084.09-value1),2))+
        	          (java.lang.Math.pow((1127.1-value2),2))+  
        	          (java.lang.Math.pow((911.66-value3),2))+
  	                  (java.lang.Math.pow((907.02-value4),2))+
                      (java.lang.Math.pow((913.28-value5),2));
             break; 
           case "56-24CoreBroke-COVERAGE":
        	   error= (java.lang.Math.pow((1085.41-value1),2))+
        	          (java.lang.Math.pow((1128.42-value2),2))+  
        	          (java.lang.Math.pow((912.98-value3),2))+
  	                  (java.lang.Math.pow((908.34-value4),2))+
                      (java.lang.Math.pow((914.6-value5),2));
             break;
           case "26-24CoreBroke-VCF":
        	   error= (java.lang.Math.pow((1084.09-value1),2))+
        	          (java.lang.Math.pow((1125.44-value2),2)); 
        	          
             break; 
            case "312-24Core":
      	     error=   (java.lang.Math.pow((1503.37-value1),2))+
                      (java.lang.Math.pow((1571.5-value2),2))+
                      (java.lang.Math.pow((1471.1-value3),2));
            break;
            case "312-24CoreBroke-LOWANNOTATE":
         	     error=  (java.lang.Math.pow((1517.0-value1),2))+
                         (java.lang.Math.pow((1571.5-value2),2))+
                         (java.lang.Math.pow((1471.1-value3),2));
               break;
            case "312-24CoreBroke-HIGHANNOTATE":
        	     error=  (java.lang.Math.pow((1548.22-value1),2))+
                        (java.lang.Math.pow((1571.5-value2),2))+
                        (java.lang.Math.pow((1471.1-value3),2));
              break;  
            case "312-96Core":
         	     error= (java.lang.Math.pow((962.02-value1),2))+
                         (java.lang.Math.pow((905.57-value2),2))+
                         (java.lang.Math.pow((910.49-value3),2));
               break;
            case "26-12Core":
          	     error= (java.lang.Math.pow((1291.49-value1),2))+
                          (java.lang.Math.pow((1331.3-value2),2));
                         
                break;
            
            case "36-12Core":
       	     error= (java.lang.Math.pow((1291.49-value1),2))+
                       (java.lang.Math.pow((1331.3-value2),2))+
                       (java.lang.Math.pow((1060.56-value3),2));
             break;
            case "36-12Core-AzureNew":
          	       error= (java.lang.Math.pow((1060.56-value1),2))+
          	    	//	 error= (java.lang.Math.pow((960.56-value1),2))+
                          (java.lang.Math.pow((964.56-value2),2))+
                          (java.lang.Math.pow((889.39-value3),2));
//                break; 
//            case "36-12Core-AzureNew":
         	     
//         	    		 error= (java.lang.Math.pow((907.02-value1),2))+
//                         (java.lang.Math.pow((919.14-value2),2))+
//                         (java.lang.Math.pow((889.39-value3),2));
         	    		 
         	    		 
//         	    		error= (java.lang.Math.pow((1003.02-value1),2))+
//                                (java.lang.Math.pow((1015.14-value2),2))+
//                                (java.lang.Math.pow((985.39-value3),2));
               break;                 
                
               
               
            case "56-12Core-AzureNew":
        	     
	    		 error= (java.lang.Math.pow((964.56-value1),2))+
                (java.lang.Math.pow((889.39-value2),2))+
                (java.lang.Math.pow((901.26-value3),2))+
                (java.lang.Math.pow((907.02-value4),2))+
                (java.lang.Math.pow((919.14-value5),2));
	    		 
             break;   
                
            case "312-12Core-AzureNew":
//       	       error= (java.lang.Math.pow((1531.26-value1),2))+
//                       (java.lang.Math.pow((1470.17-value2),2))+
//                       (java.lang.Math.pow((1518.42-value3),2));
       	       
       	    error= (java.lang.Math.pow((1666.26-value1),2))+
                    (java.lang.Math.pow((1605.17-value2),2))+
                    (java.lang.Math.pow(( 1653.42-value3),2));
       	       
             break;
            case "324-12Core-AzureNew":
//        	       error= (java.lang.Math.pow((2866.54-value1),2))+
//                        (java.lang.Math.pow((3010.27-value2),2))+
//                        (java.lang.Math.pow((2901.27-value3),2));
        	       error= (java.lang.Math.pow((2986.54-value1),2))+
                           (java.lang.Math.pow((3130.27-value2),2))+
                           (java.lang.Math.pow((3021.27-value3),2));
              break;    
            case "36-24Core-AzureNew":
//     	      error= (java.lang.Math.pow((781.18-value1),2))+
//                     (java.lang.Math.pow((792.51-value2),2))+
//                     (java.lang.Math.pow((776.23-value3),2));
     	      
     	     error= (java.lang.Math.pow((877.18-value1),2))+
                     (java.lang.Math.pow((888.51-value2),2))+
                     (java.lang.Math.pow((872.23-value3),2));
           break;   
            case "312-24Core-AzureNew":
//       	      error= (java.lang.Math.pow((1014.17-value1),2))+
//                       (java.lang.Math.pow((980.55-value2),2))+
//                       (java.lang.Math.pow((972.12-value3),2));
       	   error= (java.lang.Math.pow((1149.17-value1),2))+
                   (java.lang.Math.pow((1115.55-value2),2))+
                   (java.lang.Math.pow((1107.12-value3),2));
       	                
             break; 
            case "324-24Core-AzureNew":
//         	      error= (java.lang.Math.pow((1713.33-value1),2))+
//                         (java.lang.Math.pow((1585.51-value2),2))+
//                         (java.lang.Math.pow((1666.57-value3),2));
         	      
         	     error= (java.lang.Math.pow((1993.33-value1),2))+
                         (java.lang.Math.pow((1865.51-value2),2))+
                         (java.lang.Math.pow((1946.57-value3),2));
         	                
               break; 
            case "36-48Core-AzureNew":
         	      error= (java.lang.Math.pow((764.12-value1),2))+
                         (java.lang.Math.pow((769.54-value2),2))+
                         (java.lang.Math.pow((764.40-value3),2));
               break;   
               
            case "46-48Core-AzureNew":
       	      error= (java.lang.Math.pow((743.55-value1),2))+
                       (java.lang.Math.pow((764.12-value2),2))+
                       (java.lang.Math.pow((769.54-value3),2))+
                       (java.lang.Math.pow((764.40-value4),2));
       	      
//       	   error= (java.lang.Math.pow((1058.55-value1),2))+
//                   (java.lang.Math.pow((1079.12-value2),2))+
//                   (java.lang.Math.pow((1084.54-value3),2))+
//                   (java.lang.Math.pow((1079.40-value4),2));
             break;  
            case "312-48Core-AzureNew":
         	      error= (java.lang.Math.pow((836.56-value1),2))+
                         (java.lang.Math.pow((884.27-value2),2))+
                         (java.lang.Math.pow((890.36-value3),2));
//         	     error= (java.lang.Math.pow((1186.56-value1),2))+
//                         (java.lang.Math.pow((1234.27-value2),2))+
//                         (java.lang.Math.pow((1240.36-value3),2));
                    
               break; 
 /******************************************************************/
            case "312-48Core-AzureNew1":
       	      error= (java.lang.Math.pow((890.36-value1),2))+
                       (java.lang.Math.pow((905.57-value2),2))+
                       (java.lang.Math.pow((910.49-value3),2));
                  
             break;              
 /******************************************************************/              
            case "324-48Core-AzureNew":
       	      error= (java.lang.Math.pow((1144.50-value1),2))+
                       (java.lang.Math.pow((1155.46-value2),2))+
                       (java.lang.Math.pow((1164.0-value3),2));
//       	   error= (java.lang.Math.pow((1564.50-value1),2))+
//                   (java.lang.Math.pow((1575.46-value2),2))+
//                   (java.lang.Math.pow((1584.0-value3),2));
                  
             break;    
            case "16-12Core-AzureNew":
       	      error=  (java.lang.Math.pow((1060.56-value1),2));
                        
                        
             break;  
              
            case "212-48Core":
         	     error= (java.lang.Math.pow((1012.22-value1),2))+
                         (java.lang.Math.pow((758.35-value2),2));
                          
            break;
            case "212-48CoreBroke-GATK3":
        	     error= (java.lang.Math.pow((1012.22-value1),2))+
                        (java.lang.Math.pow((1020-value2),2));
                         
           break;
            case "324-48Core":
        	     error= (java.lang.Math.pow((1725.09-value1),2))+
        	    		(java.lang.Math.pow((1120.48-value1),2))+
                        (java.lang.Math.pow((1713.33-value2),2));
                         
           break;
            case "224-48Core":
       	     error= (java.lang.Math.pow((1725.09-value1),2))+
       	    		 
                       (java.lang.Math.pow((1713.33-value2),2));
                        
          break;
            case "224-48CoreBroke-VCF":
          	     error= (java.lang.Math.pow((1689.55-value1),2))+
          	    		(java.lang.Math.pow((1700.59-value2),2));
                           
             break;
            case "324-48CoreBroke-VCF":
       	     error= (java.lang.Math.pow((1689.55-value1),2))+
       	    		(java.lang.Math.pow((1120.48-value1),2))+
                       (java.lang.Math.pow((1700.59-value2),2));
                        
            break;
            case "224-48CoreBroke-GATK3":
          	     error= (java.lang.Math.pow((1689.55-value1),2))+
          	    		(java.lang.Math.pow((1700.59-value2),2));
                           
               break;
            case "324-96Core":
       	     error= (java.lang.Math.pow((1412.22-value1),2))+
       	    		(java.lang.Math.pow((1207.44-value1),2))+
                       (java.lang.Math.pow((1186.18-value2),2));
                        
          break;
            case "324-96CoreBroke-GATK3":
          	     error= (java.lang.Math.pow((1360.40-value1),2))+
          	    		(java.lang.Math.pow((1171.37-value1),2))+
                        (java.lang.Math.pow((1151.15-value2),2));
                           
             break;
            case "212-12Core":
        	     error= (java.lang.Math.pow((2183.10-value1),2))+
                        (java.lang.Math.pow((2198.20-value2),2));
                         
           break;
            case "512-12+24Core":
       	     error=    (java.lang.Math.pow((2183.10-value1),2))+
                       (java.lang.Math.pow((2198.20-value2),2))+
       	               (java.lang.Math.pow((1503.37-value3),2))+
                       (java.lang.Math.pow((1571.5-value4),2))+
                       (java.lang.Math.pow((1471.1-value5),2));
                        
          break;
            case "56+312-24Core":
       	     error=  (java.lang.Math.pow((1085.19-value1),2))+
       	             (java.lang.Math.pow((1125.44-value2),2))+  
       	             (java.lang.Math.pow((901.26-value3),2))+
 	                 (java.lang.Math.pow((907.2-value4),2))+
                     (java.lang.Math.pow((919.14-value5),2))+
       	             (java.lang.Math.pow((1503.37-value6),2))+
       	     	     (java.lang.Math.pow((1571.5-value7),2))+
       	     	     (java.lang.Math.pow((1471.1-value8),2));
         	break;  
            
            case "36+36-12+24Core":
          	     error=  (java.lang.Math.pow((1291.49-value1),2))+
                         (java.lang.Math.pow((1331.3-value2),2))+
                         (java.lang.Math.pow((1060.56-value3),2))+
          	             (java.lang.Math.pow((901.26-value3),2))+
    	                 (java.lang.Math.pow((907.2-value4),2))+
                         (java.lang.Math.pow((919.14-value5),2));
          	            
            	break;  
             
           case "16":
        	      error= (java.lang.Math.pow((1060.56-value1),2));
           break;
           
           case "26":
        	      error= (java.lang.Math.pow((1291.49-value1),2))+
        	             (java.lang.Math.pow((1331.3-value2),2));
           break;
           case "210":
        	      error= (java.lang.Math.pow((1958.12-value1),2))+ 
        	             (java.lang.Math.pow((1976.25-value2),2));
           break;
           case "212":
        	     error= (java.lang.Math.pow((2183.1-value1),2))+
	     	            (java.lang.Math.pow((2198.2-value2),2));
           break;
           case "26210":
        	      error= (java.lang.Math.pow((1291.49-value1),2))+
                         (java.lang.Math.pow((1331.3-value2),2))+
     		             (java.lang.Math.pow((1958.12-value3),2))+
    		             (java.lang.Math.pow((1976.25-value4),2));
           break;
           case "26210212":
        	     error=  (java.lang.Math.pow((1291.49-value1),2))+
        	  		     (java.lang.Math.pow((1331.3-value2),2))+
        	             (java.lang.Math.pow((1958.12-value3),2))+
        	   	         (java.lang.Math.pow((1976.25-value4),2))+
        	     	     (java.lang.Math.pow((2183.1-value5),2))+
        	      	     (java.lang.Math.pow((2198.2-value6),2));
            break;
           case "36210":
        	     error= (java.lang.Math.pow((1060.56-value1),2))+
	                    (java.lang.Math.pow((1291.49-value2),2))+
	                    (java.lang.Math.pow((1331.3-value3),2))+
	       	            (java.lang.Math.pow((1958.12-value4),2))+
	                    (java.lang.Math.pow((1976.25-value5),2));
            break;
           case "36":
        	     error= (java.lang.Math.pow((1060.56-value1),2))+
                        (java.lang.Math.pow((1291.49-value2),2))+
                        (java.lang.Math.pow((1331.3-value3),2));
           break;
           case "All":
        	     error= (java.lang.Math.pow((1060.56-value1),2))+
        	            (java.lang.Math.pow((1291.49-value2),2))+
        	            (java.lang.Math.pow((1331.3-value3),2))+
        	       	    (java.lang.Math.pow((1958.12-value4),2))+
        	            (java.lang.Math.pow((1976.25-value5),2))+
        	   	        (java.lang.Math.pow((2183.1-value6),2))+
        	     	    (java.lang.Math.pow((2198.2-value7),2));
        	break;     	    
           };
            
     	    //  double  error= (java.lang.Math.pow((2183.1-value1),2))+ (java.lang.Math.pow((2198.2-value2),2));
     	     
     	      ps.print(df.format(error)+"       ");
     	 
     	     
     	  if (error<minimumError){ minimumError=error;
                                          MinMips=k;
                                          MinBw=b;
                                          Temvalue1=value1;
                                          Temvalue2=value2;
                                          Temvalue3=value3;
                                          Temvalue4=value4;
                                          Temvalue5=value5;
                                          Temvalue6=value6;
                                          Temvalue7=value7;
                                          Temvalue8=value8;
     	                                 }
     	 
     	    
         	    elementCount+=t7;
 
             
        }
          
           ps.println();
           }
           System.out.println(" The mimumm error= "+  minimumError+ " Mips="+MinMips+ "BW="+MinBw+ " Sampe1 ="+Temvalue1+" Sample2="+Temvalue2+" Sample3="+Temvalue3);
           System.out.println(" Sampe4 ="+Temvalue4+" Sample5="+Temvalue5);
           System.out.println(" Sampe6 ="+Temvalue6+" Sample7="+Temvalue7+" Sample8="+Temvalue8);
        
           ps.flush();
           ps.close();
    	
    }
    
}

/*******************************************************************************
 * Title: The class of predicting the execution time  
 * Description: This class predicts the time for each workflow 
 * in the pipeline that will be completed fields on the XML file. 
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.workflowsim.Task;


/**
 * Based on prediction eqautions this calss can predict the proper execution time for each workflow 
 * in the pipeline, where it is called by eSC parser calss to fill missing field On the XML file of the task of the workflow 
 * For example, <task id="BWA_B1_AL" namespace="NGSprocessingPipeline" name="BWA (Align lane)" runtime="------". The replacement of "------" to value can be acheived 
 * by calling this class.
 * 
 * @author LLWAAH
 *
 */
public class GetRunTime{
	private double runtime;
	private double output;
	
	
	public GetRunTime(){ 
	this.runtime=0.0;
	this.output=0.0;
	}
	
	public static String TrainingSet;
	public static String TypeSet;
	public static void setTypeString(String type){TypeSet=type;}
	public static void setTrainingSet(String Set){TrainingSet=Set;}
	public double getRunTime(String name, String link, double size) {
		   double factor[]={ 1776.363963,12.24960144,26.63091844,15.03022393,
		    		17.8086583,	23.19461851,18.5995536,25.93760552,18.44274163,
		    		18.07799442,22.59724359,18.70283214,16.87081368,22.12907902,
		    		25.22528283,25.93084038,30.22786728,22.68173568,18.40071058,
		    		30.77086464,22.41526078,19.8170976,	16.44891154,21.03805563,
		    		15.73615502,20.74457629,15.50103714,12.16963109,13.76350168,
		    		12.91709786,10.6608867,14.72193992,10.84530918,	15.13407334,
		    		21.14752119,30.6466691,29.41781827,15.17897878,	17.87335999,
		    		13.14111667,8.208907382,5.925234643,14.04055914,6.02605409,
		    		10.92489553,14.84786825,12.64601187,30.11532969,29.18158894,
		    		0.020288925,

		    };
		   double regin[]={
				   90354753,
				   77635279,
				   77635279,
				   73182010,
				   73182010,
				   70606715,
				   70606714,
				   81195210,
				   66007476,
				   66007476,
				   66007475,
				   62312656,
				   62312654,
				   62312654,
				   62312654,
				   67767373,
				   67767372,
				   78077248,
				   63718091,
				   63718091,
				   63718091,
				   67503257,
				   67503257,
				   66925947,
				   66925946,
				   60799843,
				   60799842,
				   60799842,
				   60799842,
				   60305086,
				   60305086,
				   60305085,
				   57038355,
				   57038355,
				   57038354,
				   57584938,
				   57584938,
				   53046220,
				   53046220,
				   53046220,
				   63025520,
				   53674769,
				   53674769,
				   59373566,
				   51265695,
				   51265695,
				   59128983,
				   51304566,
				   48129895,
				   16571,

		   };
		 
		   
	        if (link.equals("input")){
		     switch (name) {
		     
		        case "BWA1_FEL":
		        case "BWA2_FEL":
		        case "BWA3_FEL":
		        case "BWA4_FEL":
		        case "BWA5_FEL":
		        case "BWA6_FEL":
		       // runtime = 2e-06*size-13677;
		       // runtime= 1E-06*size+2542.1;
		       // runtime= (size-8E08)/827070;
		       // runtime = 1E-06*size-388.31;
		       // runtime = (1E-06)*size-648.05;
		       // runtime = (size-1E09)/763203;  // It is applied in last time
		       
		        //	break;
		        case "BWA7_FEL":
		        case "BWA8_FEL":
		        case "BWA9_FEL":
		        case "BWA10_FEL":    
		        case "BWA11_FEL":
		        case "BWA12_FEL": 	
		        case "BWA13_FEL":
		        case "BWA14_FEL":
		        case "BWA15_FEL":
		        case "BWA16_FEL":    
		        case "BWA17_FEL":
		        case "BWA18_FEL":
		        case "BWA19_FEL":
		        case "BWA20_FEL":
		        case "BWA21_FEL":
		        case "BWA22_FEL":    
		        case "BWA23_FEL":
		        case "BWA24_FEL":	
		        	
		        	
	            	//runtime =(size-(2E+09))/(830087);
		        	//runtime = 3E-06*size+2299.1;
		        	//runtime = 2E-7*size+28062;
		        	//runtime = 7E-7*size+19855;
	            	
		           //   runtime= 2E-6*size-11364; // It is applied in last Time
		           //	runtime = (size-5E09)/616863;
		           //   runtime = 1.20224E-06*size-1192.808849; //Least square
		        	 if  (TrainingSet=="36LR" )  runtime = (size-5E09)/616863; // Linear equation  (Training set 6)
		        	  // runtime = (size-4814543828.7)/616863.5; // Linear equation Using R (Training set 6)
		        	 if  (TrainingSet=="6+10LR" )  runtime = (size-1E10)/202770; // Linear equation (Training set 6+10)
		        	 if  (TrainingSet=="6+10+12LR" ) runtime = (size-1E10)/156321; // Linear equation (Training set 6+10+12)
		        	 if  (TrainingSet=="26LS" )  runtime= 1.21167E-6*size-518.1802668; // TWO SAMLPE Least square (Training set 6)
		        	   if  (TrainingSet=="26LR" )      runtime= (size-2E09)/763802;   // TWO SAMLE Linear equation (Training set 6)
		        	   if  (TrainingSet=="36LS" )  runtime= 1.20224E-06*size-1192.81	; // Three SAMLE Least square (Training set 6)
		        	   if  (TrainingSet=="6+10LS" )  runtime= 2.19715E-06*size-13574.12284; // Five SAMLE Least square (Training set 6+10)
		        	   if  (TrainingSet=="6+10+12LS" )  runtime= 2.348E-06*size-14108.41351; // Seven SAMLE Least square (Training set 6+10+12)
		        	   if (TrainingSet.equals("10LR"))   runtime = (size-1E+10)/223949;  // Linear equation  (Training set 10)
		        	   if (TrainingSet.equals("12LR"))  runtime = (size-1E+10)/137214 ; // Linear equation  (Training set 12)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24Core" ) runtime= 1.786E-07 *size +15132.96 ; // Five SAMLE Least square (Training set 6 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at BVCF)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-VCF" ) runtime= 1.786E-07 *size +15132.96 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-VCF)
		        	   if  (TrainingSet=="26LS-24CoreBroke-VCF" ) runtime=-8.39479E-07 *size +36054.98  ;
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at COVERAGE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-COVERAGE" ) runtime= 1.786E-07 *size +15132.96 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-COVERAGE)
		        	  // if  (TrainingSet=="56LS-24Core" )  runtime= (size-(2E+10))/24904 	;
		        	  //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core" )  runtime= 1.008E-06 *size+6588.7916  	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	   
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-LOWANNOTATE" )  runtime= 1.008E-06 *size+6588.7916  	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-HIGHANNOTATE" )  runtime= 1.008E-06 *size+6588.7916  	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	  
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Sample + 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56+312LS-24Core" )  runtime= 6.83803E-07 *size+9636.619   	; // Five 6-Samples + Three 12-SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-48Core" )  runtime= 6.20065E-07 *size + 5200.45492    	; // TWO 12-SAMPLES Least square (Training set 12 with 48 Cores) 
		        
		        	   //  Linear equation of the training New-data set (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-12Core" )  runtime= 2.40199E-06 *size -11006.8     	; // TWO 12-SAMPLES Least square (Training set 12 with 12 Cores) 
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="124LS-24Core" )  runtime= 2.21192E-06 *size -2441.12981      	; // One 24-SAMPLE Least square (Training set 24 with 24 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="224LS-48Core" )  runtime= 1.45933E-06 *size -3679.275484       	; // TWO 24-SAMPLE Least square (Training set 24 with 48 Cores) 
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core" )  runtime= 1.20224E-06 *size -1192.81         	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="26LS-12Core" )  runtime= 1.786E-07 *size +15132.96        	; // TWO 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		           
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="512LS-12+24Core" )  runtime= 1.64087E-06 *size -1810.24         	; // Five 12-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		           
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36+36LS-12+24Core" )  runtime= 1.02978E-06 *size -3.93461         	;  //Eight 6-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		        	   
		        	   //  Linear equation of the training New-data set ( 12-Samples with 96 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-96Core" )  runtime= 8.64303E-07  *size + 27.20906   	; // Three SAMPLES Least square (Training set 12 with 96 Cores)
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNew" )  runtime= 7.75415E-07  *size +3120.422         	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	 
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (4-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewLib" )  runtime= 7.98621E-07  *size +2132.267    ; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	   
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewMix" )  runtime= 7.98621E-07   *size +2132.267         	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-12Core-AzureNew" ) runtime= 2.76508E-07  *size +16875.66;          	; // Three 12-SAMPLE Least square (Training set 12 with 12 Cores) 
		        	
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-12Core-AzureNew" )  runtime= 1.45933E-06 *size -3679.275484           	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-24Core-AzureNew" )  runtime= 9.15552E-07   *size -1688.846          	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores)    
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core-AzureNew" )  runtime= 7.25924E-07 *size +2575.656          	; // Three 12-SAMPLE Least square (Training set 12 with 24 Cores)      
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-24Core-AzureNew" )  runtime= 1.50997E-06 *size -4660.672771           	; // Three 24-SAMPLE Least square (Training set 24 with 24 Cores)         
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime= 8.59428E-07   *size -2045.59               	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)            
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-48Core-AzureNew" )  runtime= 9.44781E-07 *size -2112.24                	; // Three 12-SAMPLE Least square (Training set 12 with 48 Cores)             
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-48Core-AzureNew" )  runtime= 8.10431E-07 *size -1441.150426              ; // Three 24-SAMPLE Least square (Training set 24 with 48 Cores)                
		        
		        	   
		        	//  Linear equation of the training AzureNew-data set (2-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-12Core-AzureNew" )  runtime= 7.7E-07  *size +2669.256                ; // Mixed Five 6-SAMPLE Least square (Training set 6 with 12 Cores)                   
		       
		        	   
		        break;
		        case "BWA_A1_AL":
		        case "BWA_A2_AL":
		        case "BWA_A3_AL":
		        case "BWA_A4_AL":
		        case "BWA_A5_AL":
		        case "BWA_A6_AL":
		        case "BWA_B1_AL":
		        case "BWA_B2_AL":
		        case "BWA_B3_AL":
		        case "BWA_B4_AL":
		        case "BWA_B5_AL":
		        case "BWA_B6_AL":
		         //	runtime = 1E-06*size+930.29;
		         //	runtime =(size-(1E+09))/(775245);	
		         // runtime = (size+7E08)/786558;	// It is applied in last time
			     //  break;	
		        case "BWA_A7_AL":
		        case "BWA_A8_AL":
		        case "BWA_A9_AL":
		        case "BWA_A10_AL":	
		        case "BWA_A11_AL":
		        case "BWA_A12_AL":	
		        case "BWA_A13_AL":
		        case "BWA_A14_AL":
		        case "BWA_A15_AL":
		        case "BWA_A16_AL":	
		        case "BWA_A17_AL":
		        case "BWA_A18_AL":
		        case "BWA_A19_AL":
		        case "BWA_A20_AL":
		        case "BWA_A21_AL":
		        case "BWA_A22_AL":	
		        case "BWA_A23_AL":
		        case "BWA_A24_AL":	
		        case "BWA_B7_AL":
		        case "BWA_B8_AL":
		        case "BWA_B9_AL":
		        case "BWA_B10_AL":
		        case "BWA_B11_AL":
		        case "BWA_B12_AL":
		        case "BWA_B13_AL":
		        case "BWA_B14_AL":
		        case "BWA_B15_AL":
		        case "BWA_B16_AL":
		        case "BWA_B17_AL":
		        case "BWA_B18_AL":
		        case "BWA_B19_AL":
		        case "BWA_B20_AL":
		        case "BWA_B21_AL":
		        case "BWA_B22_AL":
		        case "BWA_B23_AL":
		        case "BWA_B24_AL":	
		        	//runtime =(size-(1E+09))/(775245);
		        //	runtime = 1E-06*size+930.29+2000;
		        	
		        // runtime= (size+7E08)/786558;	
		       	//runtime = (size-4E09)/341562;   // It is applied in last time
		        	if  (TrainingSet=="36LR" )   runtime = (size- 3E09)/443745; // Linear equation (Training set 6)
		               // runtime = (size- 3190610947.1)/443745.2 ; // Linear equation Using R(Training set 6)
		        	//runtime = 1.31681E-06 *size -208.7270757; //Least square
		        	 if  (TrainingSet=="6+10LR" ) runtime = (size- (3E+09))/485440; // Linear equation (Training set 6+10)
		        	 if  (TrainingSet=="6+10+12LR" ) runtime = (size- (2E+09))/531064; // Linear equation (Training set 6+10+12) 
		        	 if  (TrainingSet=="26LS" ) runtime = 1.36858E-6*size+30.06853723; // TWO SAMLE Least square (Training set 6) 
		        	if  (TrainingSet=="26LR")   runtime =(size-8E+08)/645898; // TWO SAMLE Linear equation (Training set 6) 
		        	if  (TrainingSet=="36LS" )  runtime =  1.31681E-06*size-208.727; // Three SAMLE Least square (Training set 6) 
		        	if  (TrainingSet=="6+10LS" ) runtime= 1.37746E-06*size-323.8928112; // Five SAMLE Least square (Training set 6+10)
		        	if  (TrainingSet=="6+10+12LS" )  runtime= 1.33132E-06*size+83.91426685; // Seven SAMLE Least square (Training set 6+10+12) 
		        	if (TrainingSet=="10LR")   runtime = (size-2E+09)/567535	; // Linear equation (Training set 10)
		        	 if (TrainingSet.equals("12LR")) runtime =(size-9E+08)/654625; // Linear equation (Training set 12)
		        	 //******************************************************************//
		        	 //  Linear equation of the training New-data set ( 6-Samples with 24 core)//
		        	 //******************************************************************//
	        	     if  (TrainingSet=="56LS-24Core" )  runtime= 4.10831E-07 *size +7299.708  	; // Five SAMLE Least square (Training set 6 with 24 Cores)
	        	      //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at BVCF)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-VCF" ) runtime= 4.10831E-07 *size +7299.708 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-VCF)
		        	   if  (TrainingSet=="26LS-24CoreBroke-VCF" ) runtime=-4.16529E-07 *size+16853.07 ;
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at COVERAGE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-COVERAGE" ) runtime= 4.10831E-07 *size +7299.708 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-COVERAGE)
		        	   
		        	   // if  (TrainingSet=="56LS-24Core" )  runtime=  (size-(7E+09))/16173	;
	        	       //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core" )  runtime= 9.33684E-07 *size + 1545.1332   	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-LOWANNOTATE" )  runtime= 9.33684E-07 *size + 1545.1332   	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-HIGHANNOTATE" )  runtime= 9.33684E-07 *size + 1545.1332   	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Sample + 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56+312LS-24Core" )  runtime= 4.25183E-07 *size +6298.579    	; // Five 6-Samples + Three 12-SAMPLES Least square (Training set 12 with 24 Cores)
		        	   
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-48Core" )  runtime= 1.02632E-06 *size + 356.9557942     	; // TWO 12-SAMPLES Least square (Training set 12 with 48 Cores) 
		        	   //  Linear equation of the training New-data set (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-12Core" )  runtime= 1.23198E-06 * size +948.287      	; // TWO 12-SAMPLES Least square (Training set 12 with 12 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="124LS-24Core" )  runtime= 1.45834E-06 *size -2234.032763      	; // One 24-SAMPLE Least square (Training set 24 with 24 Cores) 
		        
		        	  //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="224LS-48Core" )  runtime= 1.0322E-06 *size +160.5445056        	; // TWO 24-SAMPLE Least square (Training set 24 with 48 Cores) 
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core" )  runtime= 1.31681E-06 *size -208.727         	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		  
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="26LS-12Core" )  runtime= 1.36858E-06 + 30.06854         	; // TWO 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		           
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="512LS-12+24Core" )  runtime= 1.08038E-06 *size + 1063.774         	; // Five 12-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36+36LS-12+24Core" )  runtime=1E-06*size + 361.04        	;  //Eight 6-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		        	   //  Linear equation of the training New-data set ( 12-Samples with 96 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-96Core" )  runtime= 9.12997E-07 *size + 477.6986   	; // Three SAMPLES Least square (Training set 12 with 96 Cores)
		        	   
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNew" )  runtime= 1.14689E-06 *size -388.631          	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	   
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (4-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewLib" )  runtime= 1.06626E-06  *size -182.6894     ; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	   
		        	   
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-12Core-AzureNew" )  runtime= 1.13233E-06  * size -717.067           	; // Three 12-SAMPLE Least square (Training set 12 with 12 Cores) 
		        	 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-12Core-AzureNew" )  runtime= 1.11146E-06  *size -710.4137043            	; // Three 24-SAMPLE Least square (Training set 24 with 12 Cores) 
		        	 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-24Core-AzureNew" )  runtime= 1.07586E-06  *size -1249.358           	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores)     
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core-AzureNew" )  runtime= 9.18955E-07 * size +887.3255           	; // Three 12-SAMPLE Least square (Training set 12 with 24 Cores)        
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-24Core-AzureNew" )  runtime= 9.96435E-07 *size +277.2481282            	; // Three 24-SAMPLE Least square (Training set 24 with 24 Cores)         
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime= 9.24565E-07 *size -1235.878               	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)            
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-48Core-AzureNew" )  runtime= 1.10968E-06 * size -1445.17                 	; // Three 12-SAMPLE Least square (Training set 12 with 48 Cores)                
		        	   
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-48Core-AzureNew" )  runtime= 1.01504E-06 *size +444.2400868                 	; // Three 24-SAMPLE Least square (Training set 24 with 48 Cores)                
		        	   
		        	   //******************************************************************//
				        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples)//
				        	 //******************************************************************//
				        	    //        runtime= (size-1E9)/594276; // TWO 6-SAMPLE & TWO 10-SAMPLES Linear equation (Training set 26+210-Samples) 
				        	           
				        	           
		                     //******************************************************************//
				        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples & 2 12-Samples)//
				        	 //******************************************************************//
				        	          // runtime= (size-1E9)/616445; // TWO 6-SAMPLE & TWO 10-SAMPLES & TWO 12-SAMPLES Linear equation (Training set 26+210+212-Samples)
		       
		        	   
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewMix" )  runtime= 1.06626E-06    *size -182.6894         	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		                  
		        	
		        	   
		        	   //  Linear equation of the training AzureNew-data set (2-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-12Core-AzureNew" )  runtime= 1.01E-06   *size +540.7434                 ; // Mixed Five 6-SAMPLE Least square (Training set 6 with 12 Cores)                   
		        	   
		        	   
		        	   
		        break;
		        case "PICARD1":
		        case "PICARD2":
		        case "PICARD3":
		        case "PICARD4":
		        case "PICARD5":
		        case "PICARD6":
		        //	runtime =(size-(5E+09))/(829515);
		       //	runtime = 8E-7*size-446.89; // It is applied in last time
		        	
		       // break;
		        case "PICARD7":
		        case "PICARD8":
		        case "PICARD9":
		        case "PICARD10":  
		        case "PICARD11":
		        case "PICARD12":  
		        case "PICARD13":
		        case "PICARD14":
		        case "PICARD15":
		        case "PICARD16":  
		        case "PICARD17":
		        case "PICARD18":  
		        case "PICARD19":
		        case "PICARD20":
		        case "PICARD21":
		        case "PICARD22":  
		        case "PICARD23":
		        case "PICARD24":  	
		        	
	           //	runtime =(size-(5E+09))/(829515);
		       // runtime = 7E-7*size+2159.4; // It is applied in last time
		        	
	          
		        	//runtime = size*8.95734E-07-1840.055393; ( With M And B driven from x~,Y~)
		        	// runtime = 8.95734E-07 *size -1840.055393; //Least square
		        	if  (TrainingSet=="36LR" )runtime = 9E-07*size-1840.1; // Linear equation (Training set 6)
		        	//  runtime =  8.957337e-07*size-1.840055e+03; // Linear equation Using R (Training set 6)
		        	 
		        	 if  (TrainingSet=="6+10LR" ) runtime = 1E-6*size-2130.6; // Linear equation (Training set 6+10)
		        	 if  (TrainingSet=="6+10+12LR" ) runtime = 1E-6*size-2130.6; // Linear equation (Training set 6+10+12)
		        	 if  (TrainingSet=="26LS" ) runtime = 9.26058E-07*size-2073.69466; // TWO SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="26LR")   runtime = 9.26058E-07*size-2073.69466; // TWO SAMLE Linear equation (Training set 6)
		        	if  (TrainingSet=="36LS" )   runtime = 8.95734E-07*size-1840.06 ; // Three SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="6+10LS" )	runtime= 9.6544E-07*size-2130.603423; // Five SAMLE Least square (Training set 6+10)
		        	if  (TrainingSet=="6+10+12LS" )  runtime= 9.86343E-07*size-2243.022394; // Seven SAMLE Least square (Training set 6+10+12)  
		        	if  (TrainingSet=="10LR")   runtime = 9E-07*size-1013.4; // Linear equation (Training set 10)	
		        	 if (TrainingSet.equals("12LR")) runtime = 1E-06*size-2164.2; // Linear equation (Training set 12)	
		        	//******************************************************************//
		        	 //  Linear equation of the training New-data set ( 6-Samples with 24 core)//
		        	 //******************************************************************//
	        	    if  (TrainingSet=="56LS-24Core" )  runtime= 6.80812E-07 *size -1709.23  	; // Five SAMPLES Least square (Training set 6 with 24 Cores)
	        	       //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at BVCF)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-VCF" ) runtime= 6.80812E-07 *size -1709.23 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-VCF)
		        	   if  (TrainingSet=="26LS-24CoreBroke-VCF" ) runtime=7.1512E-07 *size -2094.52  ;
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at COVERAGE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-COVERAGE" ) runtime= 6.80812E-07 *size -1709.23 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-COVERAGE)
		        	  
		        	   
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core" )  runtime= 6.83759E-07 *size - 578.3237021    	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-LOWANNOTATE" )  runtime= 6.83759E-07 *size - 578.3237021    	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-HIGHANNOTATE" )  runtime= 6.83759E-07 *size - 578.3237021    	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Sample + 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56+312LS-24Core" )  runtime= 6.70994E-07 *size -963.417     	; // Five 6-Samples + Three 12-SAMPLES Least square (Training set 12 with 24 Cores)
		        	   
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-48Core" )  runtime= 1.2463E-06 *size -2482.62078      	; // TWO 12-SAMPLES Least square (Training set 12 with 48 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-12Core" )  runtime= 9.97056E-07 *size -2164.23       	; // TWO 12-SAMPLES Least square (Training set 12 with 12 Cores) 
		        
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="124LS-24Core" )  runtime= 7.81466E-07 *size -1367.22741      	; // One 24-SAMPLE Least square (Training set 24 with 24 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="224LS-48Core" )  runtime= 7.15887E-07 *size -1030.304911         	; // TWO 24-SAMPLE Least square (Training set 24 with 48 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core" )  runtime= 8.95734E-07 *size -1840.06          	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		  
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="26LS-12Core" )  runtime= 9.26058E-07 *size -2073.69           	; // TWO 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		           
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="512LS-12+24Core" )  runtime= 9.20134E-07 *size -2446.79          	; // Five 12-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		           
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36+36LS-12+24Core" )  runtime=8E-07*size - 1646.2        	;  //Eight 6-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		           
		        	   //  Linear equation of the training New-data set ( 12-Samples with 96 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-96Core" )  runtime= 6.27566E-07 *size  -883.27   	; // Three SAMPLES Least square (Training set 12 with 96 Cores)
		        	   
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNew" )  runtime= 6.56195E-07   *size-590.6429            	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	   
		        	 
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (4-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewLib" )  runtime= 5.94484E-07   *size -886.4458     ; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	   
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-12Core-AzureNew" )  runtime= 5.75637E-07  *size +237.187            	; // Three 12-SAMPLE Least square (Training set 12 with 12 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-12Core-AzureNew" )  runtime= 6.7952E-07 *size -936.9455089             	; // Three 24-SAMPLE Least square (Training set 24 with 12 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-24Core-AzureNew" )  runtime= 5.96536E-07 *size-787.9019            	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores)       
		        	 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core-AzureNew" )  runtime= 6.54482E-07 *size -1352            	; // Three 12-SAMPLE Least square (Training set 12 with 24 Cores)        
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-24Core-AzureNew" )  runtime= 6.59346E-07 *size -595.0718325             	; // Three 24-SAMPLE Least square (Training set 24 with 24 Cores)           
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime= 8.59428E-07   *size -2045.59               	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)            
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime=6.16986E-07 *size  -1360.78                 	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)             
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-48Core-AzureNew" )  runtime= 6.39486E-07 *size -1505.62                  	; // Three 12-SAMPLE Least square (Training set 12 with 48 Cores)                
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-48Core-AzureNew" )  runtime= 6.67983E-07 *size -1596.021516                  	; // Three 24-SAMPLE Least square (Training set 24 with 48 Cores)                   
		        	   
		        	   
		        	   //******************************************************************//
		        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples)//
		        	 //******************************************************************//
		        	    //  runtime= 1E-06*size-1984.2; // TWO 6-SAMPLE & TWO 10-SAMPLES Linear equation (Training set 26+210-Samples)   
		        	      
		             //******************************************************************//
		        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples & 2 12-Samples)//
		        	 //******************************************************************//
		        	      //runtime= 1E-06*size-2101.8; // TWO 6-SAMPLE & TWO 10-SAMPLES & TWO 12-SAMPLES Linear equation (Training set 26+210+212-Samples)
                 
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewMix" )  runtime= 5.94484E-07   *size -886.4458       	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	     	   
		          
		        	   //  Linear equation of the training AzureNew-data set (2-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-12Core-AzureNew" )  runtime=6.71E-07  *size -1516.09                  ; // Mixed Five 6-SAMPLE Least square (Training set 6 with 12 Cores)                    
		        	   
		        break;
		        case "GATKP1_1":
		        case "GATKP1_2":
		        case "GATKP1_3":
		        case "GATKP1_4":
		        case "GATKP1_5":
		        case "GATKP1_6":
		        	//runtime =(size-(3E+08))/(470780);
		        	//runtime= 3E-6*size+342.37;
		        	//runtime = (size-3E08)/ 355800;
		        	//runtime =2E-06*size-406.51;
		        	//runtime = (size-3E08)/355800; // It is applied in last time
		        	
		      //  break;
		        case "GATKP1_7":
		        case "GATKP1_8":
		        case "GATKP1_9":
		        case "GATKP1_10": 
		        case "GATKP1_11":
		        case "GATKP1_12":
		        case "GATKP1_13":
		        case "GATKP1_14":
		        case "GATKP1_15":
		        case "GATKP1_16": 
		        case "GATKP1_17":
		        case "GATKP1_18":
		        case "GATKP1_19":
		        case "GATKP1_20":
		        case "GATKP1_21":
		        case "GATKP1_22": 
		        case "GATKP1_23":
		        case "GATKP1_24":	
		        	
	            //	runtime =(size-(3E+08))/(470780);
		       	//runtime = 3E-6*size+1457.5;
		       //  runtime = (size-6E08)/285310;	// It is applied in last time
	              
		        //	runtime = 2.51717E-06*size-210.1149081;
                 //    runtime = (size-3E09)/263572;
                //runtime = 3E-6*size-3500.2;
		        	if  (TrainingSet=="36LR" )  runtime =3E-06*size-210.11; // Linear equation (Training set 6) ( The pointer was here)
		        // runtime = (size-3E09)/263572;
		        //	runtime = 2.51717E-06 *size -210.1149081; // Least square
		        	 if  (TrainingSet=="6+10LR" )  runtime =3E-06*size-3500.2; // Linear equation (Training set 6+10)
		        	 if  (TrainingSet=="6+10+12LR" ) runtime =4E-06*size-3021.8; // Linear equation (Training set 6+10+12)
		        	 if  (TrainingSet=="26LS" ) runtime = 2.72729E-06*size-113.240463; // TWO SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="26LR")    runtime = 2.72729E-06*size-113.240463; // TWO SAMLE Linear equation (Training set 6)
		        	if  (TrainingSet=="36LS" )  runtime =  2.51717E-06*size-210.115; // Three SAMLE Linear equation (Training set 6)
		        	if  (TrainingSet=="6+10LS" ) runtime= 3.35886E-06*size-3500.231384; // Five SAMLE Least square (Training set 6+10)
		        	if  (TrainingSet=="6+10+12LS" )  runtime= 3.63652E-06*size-3021.768715; // Seven SAMLE Least square (Training set 6+10+12)  
		        	if  (TrainingSet=="10LR")  runtime = 3E-06*size+5995.5	; // Linear equation (Training set 10)
		        	 if (TrainingSet.equals("12LR"))	runtime =3E-06*size+7256; // Linear equation (Training set 12)
		        	//******************************************************************//
		        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples)//
		        	 //******************************************************************//
		        	        // runtime= 3E-06*size-773.86; // TWO 6-SAMPLE & TWO 10-SAMPLES Linear equation (Training set 26+210-Samples)   	
		             //******************************************************************//
		        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples & 2 12-Samples)//
		        	 //******************************************************************//
		        	      // runtime= 3E-06*size+43.494; // TWO 6-SAMPLE & TWO 10-SAMPLES & TWO 12-SAMPLES Linear equation (Training set 26+210+212-Samples)
		        	//******************************************************************//
		        	 //  Linear equation of the training New-data set ( 6-Samples with 24 core)//
		        	 //******************************************************************//
	        	    if  (TrainingSet=="56LS-24Core" )  runtime=-2.77239E-07 *size +18041.11  	; // Five SAMLE Least square (Training set 6 with 24 Cores)
	        	       //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at BVCF)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-VCF" ) runtime= -2.77239E-07 *size +18041.11 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-VCF)
		        	   if  (TrainingSet=="26LS-24CoreBroke-VCF" ) runtime= 1.57768E-06 *size +1856.643  ;
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at COVERAGE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-COVERAGE" ) runtime= -2.77239E-07 *size +18041.11 ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-COVERAGE)
		        	
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core" )  runtime= 1.91921E-06 *size + 4114.485923     	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-LOWANNOTATE" )  runtime= 1.91921E-06 *size + 4114.485923     	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-HIGHANNOTATE" )  runtime= 1.91921E-06 *size + 4114.485923     	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Sample + 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56+312LS-24Core" )  runtime= 2.2825E-06 *size -1775.63      	; // Five 6-Samples + Three 12-SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-48Core" )  runtime= 1.71862E-06 *size  -338.058196      	; // TWO 12-SAMPLES Least square (Training set 12 with 48 Cores) 
		   
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-12Core" )  runtime= 3.10616E-06 *size +7256.049        	; // TWO 12-SAMPLES Least square (Training set 12 with 12 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="124LS-24Core" )  runtime= 2.13192E-06 *size +2415.578953      	; // One 24-SAMPLE Least square (Training set 24 with 24 Cores) 
		        	  //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="224LS-48Core" )  runtime= 1.93356E-06 *size +3802.509097          	; // TWO 24-SAMPLE Least square (Training set 24 with 48 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core" )  runtime= 2.51717E-06 *size -210.115          	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		  
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="26LS-12Core" )  runtime= 2.72729E-06 *size -113.24            	; // TWO 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		           
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="512LS-12+24Core" )  runtime= 2.82803E-06 *size + 1135.092           	; // Five 12-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		           
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36+36LS-12+24Core" )  runtime=2E-06*size + 312.63       	;  //Eight 6-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		           
		            	//  Linear equation of the training New-data set ( 12-Samples with 96 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-96Core" )  runtime= 1.4406E-06 * size + 101.974    	; // Three SAMPLES Least square (Training set 12 with 96 Cores)
		        	   
		        	   
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNew" )  runtime= 1.69034E-06  *size +1887.45              	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	
		        	   
		        	  //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (4-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewLib" )  runtime= 1.61873E-06    *size +1260.57       ; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-12Core-AzureNew" )  runtime= 5.75637E-07  *size +237.187              	; // Three 12-SAMPLE Least square (Training set 12 with 12 Cores) 
		        	  
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-12Core-AzureNew" )  runtime= 2.04946E-06 *size + 3345.487432             	; // Three 24-SAMPLE Least square (Training set 24 with 12 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-24Core-AzureNew" )  runtime= 1.66801E-06 *size -2668.219             	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores)       
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core-AzureNew" )  runtime= 1.54494E-06 *size +1300.816             	; // Three 12-SAMPLE Least square (Training set 12 with 24 Cores)         
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-24Core-AzureNew" )  runtime= 1.9472E-06 *size + 3653.385886              	; // Three 24-SAMPLE Least square (Training set 24 with 24 Cores)           
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime= 1.60038E-06 *size -2096.9                	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)              
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-48Core-AzureNew" )  runtime= 1.30824E-06 *size +558.3693                  	; // Three 12-SAMPLE Least square (Training set 12 with 48 Cores)                  
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-48Core-AzureNew" )  runtime= 1.51581E-06 *size + 640.8976164                  	; // Three 24-SAMPLE Least square (Training set 24 with 48 Cores)                   
		        	   
		       
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewMix" )  runtime= 1.61873E-06   *size +1260.57       	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	     	      
		        	   
		        	   
		        	   //  Linear equation of the training AzureNew-data set (2-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-12Core-AzureNew" )  runtime=1.76E-06   *size +140.2394                   ; // Mixed Five 6-SAMPLE Least square (Training set 6 with 12 Cores)                    
		        	   
		        	   
		       break;
		        case "COVERAGE1":
		        case "COVERAGE2":
		        case "COVERAGE3":
		        case "COVERAGE4":
		        case "COVERAGE5":
		        case "COVERAGE6":
		        //	runtime= 6E-8*size+98.288;// It is applied in last time
		       // break;
		        case "COVERAGE7":
		        case "COVERAGE8":
		        case "COVERAGE9":
		        case "COVERAGE10": 
		        case "COVERAGE11":
		        case "COVERAGE12": 
		        case "COVERAGE13":
		        case "COVERAGE14":
		        case "COVERAGE15":
		        case "COVERAGE16": 
		        case "COVERAGE17":
		        case "COVERAGE18": 	
		        case "COVERAGE19":
		        case "COVERAGE20":
		        case "COVERAGE21":
		        case "COVERAGE22": 
		        case "COVERAGE23":
		        case "COVERAGE24": 	
		        	
		        	
	            	//runtime =(size+(7E+08))/(1E+07);
		        	 //runtime= 6E-8*size+98.288;
	            	//runtime =(size+(9E+08))/(1E+07);
		        	// runtime= 7E-8*size+65.168; // It is applied in last time
		        	if  (TrainingSet=="36LR" )  runtime= 7E-8*size+92.806;// Linear equation (Training set 6) ( the pointer was here)
		        	//runtime = 6.55241E-08 *size + 92.80629651; // Least square
		            if  (TrainingSet=="6+10LR" ) runtime= 7E-8*size+71.303;// Linear equation (Training set 6+10)
		            if  (TrainingSet=="6+10+12LR" ) runtime= 8E-8*size+49.754;// Linear equation (Training set 6+10+12)
		            if  (TrainingSet=="26LS" ) runtime = 6.5808E-08*size+81.67031386	; // TWO SAMLE Least square (Training set 6) 
		        	if  (TrainingSet=="26LR")   runtime = 6.5808E-08*size+81.67031386	; // TWO SAMLE Linear equation (Training set 6) 
		        	if  (TrainingSet=="36LS" )  runtime =   6.55241E-8*size+92.80629682; // Three SAMLE Least square (Training set 6) 
		        	if  (TrainingSet=="6+10LS" )  runtime= 7.19093E-08	*size+	71.30295818; // Five SAMLE Least square (Training set 6+10)
		        	if  (TrainingSet=="6+10+12LS" )  runtime= 7.81901E-08*size+49.75430219; // Seven SAMLE Least square (Training set 6+10+12)  
		        	if  (TrainingSet=="10LR")   runtime=8E-08*size+48.042;// Linear equation (Training set 10)
		        	 if (TrainingSet.equals("12LR"))runtime=9E-8*size+4.5292 ;// Linear equation (Training set 12)
		             
		        	     //******************************************************************//
			        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples)//
			        	 //******************************************************************//
			        	        //  runtime= 7E-08*size-58.609; // TWO 6-SAMPLE & TWO 10-SAMPLES Linear equation (Training set 26+210-Samples) 
			        	        
		        	     //******************************************************************//
			        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples & 2 12-Samples)//
			        	 //******************************************************************//
			        	         // runtime= 8E-08*size+38.346; // TWO 6-SAMPLE & TWO 10-SAMPLES & TWO 12-SAMPLES Linear equation (Training set 26+210+212-Samples)
		        	     
		        	//******************************************************************//
		        	 //  Linear equation of the training New-data set ( 6-Samples with 24 core)//
		        	 //******************************************************************//
	        	    // if  (TrainingSet=="56LS-24Core" )  runtime=-2.77239E-07 *size +18041.11  	; // Five SAMLE Least square (Training set 6 with 24 Cores)
	        	     if  (TrainingSet=="56LS-24Core" )  runtime=5.50853E-08 *size +41.0974919   	;
	        	    
	        	     
	        	     //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core" )  runtime=5.66997E-08 *size + 40.34180602      	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-LOWANNOTATE" )  runtime=5.66997E-08 *size + 40.34180602      	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-HIGHANNOTATE" )  runtime=5.66997E-08 *size + 40.34180602      	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Sample + 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56+312LS-24Core" )  runtime= 5.65853E-08 *size +40.08936       	; // Five 6-Samples + Three 12-SAMPLES Least square (Training set 12 with 24 Cores)
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at COVERAGE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-COVERAGE" ) runtime= 5.50853E-08 *size +41.09749  ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-COVERAGE)
		        	
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-48Core" )  runtime= 5.66997E-08 *size + 40.34180602       	; // TWO 12-SAMPLES Least square (Training set 12 with 48 Cores) 
		            	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-12Core" )  runtime= 9.13316E-08 *size + 5.170579         	; // TWO 12-SAMPLES Least square (Training set 12 with 12 Cores) 
		        
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="124LS-24Core" )  runtime= 5.44368E-08 *size  +48.29535098      	; // One 24-SAMPLE Least square (Training set 24 with 24 Cores) 
		        
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="224LS-48Core" )  runtime= 5.46227E-08 *size+45.33477377          	; // TWO 24-SAMPLE Least square (Training set 24 with 48 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core" )  runtime= 6.55242E-08 *size +92.80628           	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		  
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="26LS-12Core" )  runtime= 6.67493E-08 *size +90.06583            	; // TWO 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		           
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="512LS-12+24Core" )  runtime= 7.42337E-08 *size + 14.98355            	; // Five 12-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36+36LS-12+24Core" )  runtime=7E-08*size+ 92.806      	;  //Eight 6-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		           
		        	 	//  Linear equation of the training New-data set ( 12-Samples with 96 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-96Core" )  runtime= 5.0573E-08 * size + 46.44695     	; // Three SAMPLES Least square (Training set 12 with 96 Cores)
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNew" )  runtime= 2.40744E-08 *size +145.5443              	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	   
		        	
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (4-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewLib" )  runtime= 5.30626E-08    *size +37.4649        ; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-12Core-AzureNew" )  runtime= 4.77627E-08  *size + 49.63473               	; // Three 12-SAMPLE Least square (Training set 12 with 12 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-12Core-AzureNew" )  runtime= 5.05885E-08 *size +38.91103601              	; // Three 24-SAMPLE Least square (Training set 24 with 12 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-24Core-AzureNew" )  runtime= 15.40236E-08 *size +10.82872              	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores)       
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core-AzureNew" )  runtime= 4.77627E-08  *size + 49.63473              	; // Three 12-SAMPLE Least square (Training set 12 with 24 Cores)          
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-24Core-AzureNew" )  runtime= 5.32783E-08 *size +37.54294466               	; // Three 24-SAMPLE Least square (Training set 24 with 24 Cores)              
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime= 5.10014E-08 *size +12.4381                 	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)                
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-48Core-AzureNew" )  runtime= 4.39835E-08 *size + 35.09305                   	; // Three 12-SAMPLE Least square (Training set 12 with 48 Cores)                  
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-48Core-AzureNew" )  runtime= 4.83657E-08 *size +22.98020583                   	; // Three 24-SAMPLE Least square (Training set 24 with 48 Cores)                      
		        	
		        	   
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewMix" )  runtime= 5.30626E-08   *size +37.4649        	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)     
		        	   
		        	   
		        	//  Linear equation of the training AzureNew-data set (2-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-12Core-AzureNew" )  runtime= 3.92E-08     *size +78.03156                     ; // Mixed Five 6-SAMPLE Least square (Training set 6 with 12 Cores)                    
		        	   
		        break;
		        case "VARIANTA":
		        //	if (size == 186485610){runtime =	843.6;};
		        //	if (size == 186484721) {runtime =	12722.4;};
		        //	if (size == 186466770) {runtime =	11489.4;};
		        //	if (size == 344368545) { runtime =	23298;};
		        //	if (size == 344359621 ) { runtime =	23792;};

	            	//runtime =(size+(2E+08))/(2E+08);
	            	//runtime=10;
	            	//runtime = 23792;
		        	// runtime =	843.6;
		        	//runtime= 8E-5*size-5209.7;
		        	//runtime= 8E-5*size-5132.7;
		        	 // runtime = 9E-05*size-5648.2;
		        	
		        	// if( TypeSet.contains("-24Core") )  runtime=5.63516E-05 *size -1406.52   	; // Five SAMLE Least square (Training set 6 with 24 Cores)
		        	//*********************************************************************//
		        	//****              New variant                                       *//
		        	//*********************************************************************//
		        	if( TypeSet.contains("-12Core") )  runtime= 6E-05*size- 2728.9      ;
		        	if( TypeSet.contains("-24Core") )  runtime= 3E-05*size - 981.89 ;
		        	if( TypeSet.contains("-48Core") )  runtime= 1.5E-05*size + 86.098 ; 
		        	  
		        	//************************************************************************//
		        	 if( TypeSet.contains("-12core") )  runtime=5.56004E-05  *size -2728.87       ;
		         	 if( TypeSet.contains("-24core") )  runtime=5.80261E-05 *size -13730 ;
		         	 if( TypeSet.contains("-48core") )  runtime=6.50986E-05 *size -22243.6 ; 
		         	 
		        	//************************************************************************//
		          //	if( TypeSet.contains("-12core") )  runtime=8.63376E-05 *size -5703.47     	;
		         //	if( TypeSet.contains("-24core") )  runtime=5.63516E-05 *size -1406.52   	;
		         //	if( TypeSet.contains("-48core") )  runtime=7.90813E-05 *size -27646.6     	;
		         	//************************************************************************//
		        	
		       //old 	if( TypeSet.contains("-24Core") ) { runtime=5.80261E-05 *size -13730 ;System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ  24 Core");}
		   
		        	 // if( TypeSet.contains("-12Core") )  runtime=8.63376E-05 *size -5703.47     	; // Five SAMLE Least square (Training set 6 with 24 Cores)
		      // old  	 if( TypeSet.contains("-12Core") ){  runtime=5.56004E-05  *size -2728.87       ;;System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ  12 Core");}
		        	
		        	 
		        	 if( TypeSet.contains("-96Core") )  runtime=1.87323E-05 *size + 1693.929     	; // Five SAMLE Least square (Training set 6 with 24 Cores)
                     
		        	// if( TypeSet.contains("-48Core") )  runtime=7.90813E-05 *size -27646.6     	; // Five SAMLE Least square (Training set 6 with 24 Cores)
		      // old  	 if( TypeSet.contains("-48Core") )  runtime=6.50986E-05 *size -22243.6 ; 
		        	 if( TypeSet.contains("-12+24Core") )  runtime=5.78336E-05 *size -441.978  ; // Five SAMLE Least square (Training set 6 with 24 Cores)
	                    
		        //	 else runtime = 8.56875E-05*size-5648.167008;// Least square ( the pointer was here)
                     
		        //	 else runtime=1;
		        	// runtime= 3.40809E-05 *size+3770.613 ; // The prediction equation for general types.


	            break;
		        case "VARIANTB":
		        	 runtime=0.1;
		        break;
		        case "VCF1":
		        case "VCF2":
		        case "VCF3":
		        case "VCF4":
		        case "VCF5":
		        case "VCF6":
		        case "VCF7":
		        case "VCF8":
		        case "VCF9":
		        case "VCF10": 
		        case "VCF11":
		        case "VCF12": 	
		        case "VCF13":
		        case "VCF14":
		        case "VCF15":
		        case "VCF16":
		        case "VCF17":
		        case "VCF18":
		        case "VCF19":
		        case "VCF20":
		        case "VCF21":
		        case "VCF22": 
		        case "VCF23":
		        case "VCF24": 		
		        	
		        	
		        	
		        	
	            	//runtime =(size)/(2E06)+34.98571138;
		        	//runtime = 3E-7*size+153.8; // It is appiled in last time
	                 // runtime = 2.65537E-06*size+0.139686077;
		        	
		        	if  (TrainingSet=="36LR" )  runtime = 5.51291E-08* size+134.0509379;  // Least square  (Training set 6)  
		        	if  (TrainingSet=="6+10LR" )  runtime = 1.42691E-06* size+75.10018895;  // Least square  (Training set 6+10)
		        	if  (TrainingSet=="6+10+12LR" )  runtime = 1.58258E-06* size+74.10514847;  // Least square  (Training set 6+10+12)
		        	if  (TrainingSet=="26LR")      runtime =-2.84683E-07	*size+153.9927591; // TWO SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="26LS")      runtime =-2.84683E-07	*size+153.9927591; // TWO SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="36LS" )  runtime =  6.08199E-8*size+133.7024332; // Three SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="6+10LS" )   runtime= 1.42691E-06	*size+75.10018895; // Five SAMLE Least square (Training set 6+10)
		        	if  (TrainingSet=="6+10+12LS" )  runtime= 1.58258E-06*size+74.10514847; // Seven SAMLE Least square (Training set 6+10+12)
		        	if  (TrainingSet=="10LR")   runtime = 2.83944E-07	*size+	161.7857036;  // Least square  (Training set 10)
		        	 if (TrainingSet.equals("12LR"))runtime = 5.35387E-07	*size+	159.6830832	;  // Least square  (Training set 12)
		              
		        	     //******************************************************************//
			        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples)//
			        	 //******************************************************************//
			        	       //   runtime= 1.23902E-06*size-89.67763; // TWO 6-SAMPLE & TWO 10-SAMPLES Linear equation (Training set 26+210-Samples)
			        	        
		             //******************************************************************//
		        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples & 2 12-Samples)//
		        	 //******************************************************************//
		        	         // runtime= 1.35758E-06*size+91.15523; // TWO 6-SAMPLE & TWO 10-SAMPLES & TWO 12-SAMPLES Linear equation (Training set 26+210+212-Samples)
		        	//******************************************************************//
		        	 //  Linear equation of the training New-data set ( 6-Samples with 24 core)//
		        	 //******************************************************************//
	        	    if  (TrainingSet=="56LS-24Core" )  runtime=-2.23545E-08 *size +72.46372  	; // Five SAMLE Least square (Training set 6 with 24 Cores)
	        	      
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at BVCF)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-VCF" ) runtime= -7.42019E-08 *size + 72.35352                             ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-VCF)
		        	  
		        	   if  (TrainingSet=="26LS-24CoreBroke-VCF" ) runtime=-2.44562E-08 *size +68.9039   ;
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Samples with 24 core Broken at COVERAGE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56LS-24CoreBroke-COVERAGE" ) runtime= -4.07392E-08 *size +73.19338  ; // Five SAMLE Least square (Training set 6 with 24 CoresBroke-COVERAGE)
		        	     	   
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core" )  runtime= 3.3987E-08 *size + 133.3275177       	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-LOWANNOTATE" )  runtime= 3.3987E-08 *size + 133.3275177       	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-HIGHANNOTATE" )  runtime= 3.3987E-08 *size + 133.3275177       	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		       
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Sample + 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56+312LS-24Core" )  runtime= 1.67861E-07 *size +105.6257       	; // Five 6-Samples + Three 12-SAMPLES Least square (Training set 12 with 24 Cores)
		        	  
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-48Core" )  runtime= 3.3987E-08 *size + 133.3275177       	; // TWO 12-SAMPLES Least square (Training set 12 with 48 Cores) 
		        	   //  Linear equation of the training New-data set (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-12Core" )  runtime= 5.35387E-07 *size + 159.6831          	; // TWO 12-SAMPLES Least square (Training set 12 with 12 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="124LS-24Core" )  runtime= 2.66124E-07 *size + 163.0817692      	; // One 24-SAMPLE Least square (Training set 24 with 24 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="224LS-48Core" )  runtime= -1.31407E-08 *size +203.964114          	; // TWO 24-SAMPLE Least square (Training set 24 with 48 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core" )  runtime= 5.51291E-08 *size + 134.0509            	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		  
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="26LS-12Core" )  runtime= -2.84683E-07 *size +153.9928             	; // TWO 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		           
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="512LS-12+24Core" )  runtime= 4.59279E-07 *size +127.9125             	; // Five 12-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36+36LS-12+24Core" )  runtime=3E-07*size + 87.027      	;  //Eight 6-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		        		//  Linear equation of the training New-data set ( 12-Samples with 96 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-96Core" )  runtime=2.71989E-07 *size + 95.86627      	; // Three SAMPLES Least square (Training set 12 with 96 Cores)
		        	
		        	   
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNew" )  runtime= -3.82895E-07  *size + 122.491                	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	 
		        	   
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (4-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewLib" )  runtime= 6.3051E-10    *size + 76.02355       ; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-12Core-AzureNew" )  runtime= 5.35387E-07 *size + 159.6831                	; // Three 12-SAMPLE Least square (Training set 12 with 12 Cores) 
		        	   
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-12Core-AzureNew" )  runtime= 2.22548E-07  *size +145.7126734               	; // Three 24-SAMPLE Least square (Training set 24 with 12 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-24Core-AzureNew" )  runtime= 1.09163E-08 *size + 80.49987               	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores)       
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core-AzureNew" )  runtime= 7.49737E-08 *size + 96.35724               	; // Three 12-SAMPLE Least square (Training set 12 with 24 Cores)            
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-24Core-AzureNew" )  runtime= 3.87688E-07 *size +140.0395399                	; // Three 24-SAMPLE Least square (Training set 24 with 24 Cores)              
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime= 2.4327E-07 *size + 64.17494                    	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)                   
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-48Core-AzureNew" )  runtime= 9.69603E-08 *size + 92.12137                    	; // Three 12-SAMPLE Least square (Training set 12 with 48 Cores)                  
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-48Core-AzureNew" )  runtime= 1.33876E-07 *size +129.0405594                   	; // Three 24-SAMPLE Least square (Training set 24 with 48 Cores)        
		          	
		        	   
		        	   
			        	 //******************************************************************//
			        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
				           //******************************************************************//
			        	   if  (TrainingSet=="36LS-12Core-AzureNewMix" )  runtime= 6.3051E-10    *size +76.02355        	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)        
		        	   
		        	
			        	   
			        	//  Linear equation of the training AzureNew-data set (2-8-2017) (  6-Samples with 12 core)//
				           //******************************************************************//
			        	   if  (TrainingSet=="56LS-12Core-AzureNew" )  runtime= 4.57E-07    *size +51.20585                      ; // Mixed Five 6-SAMPLE Least square (Training set 6 with 12 Cores)                      	   
		        	   
		        	   break;
		        case "ANNOTATE1":
		        case "ANNOTATE2":
		        case "ANNOTATE3":
		        case "ANNOTATE4":
		        case "ANNOTATE5":
		        case "ANNOTATE6":
		        case "ANNOTATE7":
		        case "ANNOTATE8":
		        case "ANNOTATE9":
		        case "ANNOTATE10":   
		        case "ANNOTATE11":
		        case "ANNOTATE12":  
		        case "ANNOTATE13":
		        case "ANNOTATE14":
		        case "ANNOTATE15":
		        case "ANNOTATE16":
		        case "ANNOTATE17":
		        case "ANNOTATE18":
		        case "ANNOTATE19":
		        case "ANNOTATE20":
		        case "ANNOTATE21":
		        case "ANNOTATE22":   
		        case "ANNOTATE23":
		        case "ANNOTATE24":  	
		        	
		        	
		        	
	         	//runtime =(size-3E07)/(3937.6); // It is applied in last time
		       	//runtime = 0.000130622*size-3503.933446;
		        	if  (TrainingSet=="36LR" )   runtime = 4.62731E-18 *size+1402.666667; // Least square (Training set 6)
		        	if  (TrainingSet=="6+10LR" ) runtime = -2.48815E-05 *size+2320.239608; // Least square (Training set 6+10)
		        	if  (TrainingSet=="6+10+12LR" ) runtime = -2.18639E-05 *size+2204.0033; // Least square (Training set 6+10+12)
		        	if  (TrainingSet=="26LR")      runtime =  9.22184E-06*size+855.7763895; // TWO SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="26LS")      runtime =  9.22184E-06*size+855.7763895; // TWO SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="36LS" )  runtime =   0.000130622*size-3503.93344; // Three SAMLE Least square (Training set 6)
		        	if  (TrainingSet=="6+10LS" ) runtime= -2.48815E-05	*size+	2320.239608; // Five SAMLE Least square (Training set 6+10) 
		        	if  (TrainingSet=="6+10+12LS" )  runtime= -2.18639E-05*size+2204.0033; // Seven SAMLE Least square (Training set 6+10+12)
		        	if  (TrainingSet=="10LR")    runtime = 1.02473E-05*size+772.4216494; // Least square (Training set 10)
		        	 if (TrainingSet.equals("12LR"))runtime = -1.4469E-05	*size+	1885.019098	; // Least square (Training set 12)
                     
		        	//******************************************************************//
		        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples)//
		        	 //******************************************************************//
		        	     //   runtime= 3.38599E-06*size-1072.758; // TWO 6-SAMPLE & TWO 10-SAMPLES Linear equation (Training set 26+210-Samples) 
		        	       
                     //******************************************************************//
		        	 //  Linear equation of the training set (2 6-Samples & 2 10-Samples & 2 12-Samples)//
		        	 //******************************************************************//
		        	        // runtime= 4.24E-06*size+1043.294358; // TWO 6-SAMPLE & TWO 10-SAMPLES & TWO 12-SAMPLES Linear equation (Training set 26+210+212-Samples)
		        	//******************************************************************//
		        	 //  Linear equation of the training New-data set ( 6-Samples with 24 core)//
		        	 //******************************************************************//
	        	   if  (TrainingSet=="56LS-24Core" )  runtime= 0.000130622*size-3503.93344;  	; // Five SAMLE Least square (Training set 6 with 24 Cores)
	        	    
	        	    //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core" )  runtime= 0.000180871 *size -6706.28567 	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	  
		        	 //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core Break at ANNOTATE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-LOWANNOTATE" )  runtime= 0.000110182 *size -3752.74828 	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 12-Samples with 24 core Break at ANNOTATE)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24CoreBroke-HIGHANNOTATE" )  runtime= 0.000261797 *size -10111.57625 	; // Three SAMPLES Least square (Training set 12 with 24 Cores)
		        	
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set ( 6-Sample + 12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="56+312LS-24Core" )  runtime= 0.000180871 *size -6706.28567        	; // Five 6-Samples + Three 12-SAMPLES Least square (Training set 12 with 24 Cores)
		        	   
		        	   //******************************************************************//
			           //  Linear equation of the training New-data set (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-48Core" )  runtime= 0.000180871 *size -6706.28567      	; // TWO 12-SAMPLES Least square (Training set 12 with 48 Cores) 
		            	//  Linear equation of the training New-data set (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="212LS-12Core" )  runtime= -1.4469E-05 *size + 1885.019           	; // TWO 12-SAMPLES Least square (Training set 12 with 12 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="124LS-24Core" )  runtime=4.84784E-05 *size +3543.3265      	; // One 24-SAMPLE Least square (Training set 24 with 24 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="224LS-48Core" )  runtime= 4.88526E-06 *size +550.4917459           	; // TWO 24-SAMPLE Least square (Training set 24 with 48 Cores) 
		        
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core" )  runtime= 0.000130622 *size -3503.93             	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	   //******************************************************************//
		        	   //  Linear equation of the training New-data set (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="26LS-12Core" )  runtime= 0.000130622 *size -3503.93             	; // TWO 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		           
		        	 //******************************************************************//
		        	   //  Linear equation of the training New-data set (  12-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="512LS-12+24Core" )  runtime= 8.21536E-05 *size -2376.88              	; // Five 12-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		        	            
		        	   //  Linear equation of the training New-data set (  6-Samples with 12+24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36+36LS-12+24Core" )  runtime=0.0001*size - 3503.9     	;  //Eight 6-SAMPLE Least square (Training set 12 with 12+24 Cores) 
		            	//  Linear equation of the training New-data set ( 12-Samples with 96 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-96Core" )  runtime=-7.58237E-06 * size + 1090.109       	; // Three SAMPLES Least square (Training set 12 with 96 Cores)
		        	
		        	 //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNew" )  runtime= 8.39724E-05  *size -2054.377                 	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores) 
		        	 
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (4-8-2017) (  6-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-12Core-AzureNewLib" )  runtime= 8.39724E-05     *size -2054.377        ; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)  
		        	   
		        	   
		        	   
		        	   //******************************************************************//
		        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-12Core-AzureNew" )  runtime= 1.03391E-05 *size + 327.0436                 	; // Three 12-SAMPLE Least square (Training set 12 with 12 Cores)    
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 12 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-12Core-AzureNew" )  runtime= 9.43547E-06  *size +341.7104675                	; // Three 24-SAMPLE Least square (Training set 24 with 12 Cores) 
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="36LS-24Core-AzureNew" )  runtime= 5.94784E-06 *size +517.5135                	; // Three 6-SAMPLE Least square (Training set 6 with 24 Cores)       
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-24Core-AzureNew" )  runtime= 1.03391E-05 *size + 327.0436                	; // Three 12-SAMPLE Least square (Training set 12 with 24 Cores)               
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 24 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-24Core-AzureNew" )  runtime= 8.12211E-06 *size +396.4565472                 	; // Three 24-SAMPLE Least square (Training set 24 with 24 Cores)              
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="46LS-48Core-AzureNew" )  runtime= 1.13177E-05 *size +284.2718                     	; // Three 6-SAMPLE Least square (Training set 6 with 48 Cores)                      
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  12-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="312LS-48Core-AzureNew" )  runtime= 6.80328E-06 *size + 519.9803                     	; // Three 12-SAMPLE Least square (Training set 12 with 48 Cores)                     
		        	//  Linear equation of the training AzureNew-data set (25-1-2017) (  24-Samples with 48 core)//
			           //******************************************************************//
		        	   if  (TrainingSet=="324LS-48Core-AzureNew" )  runtime=  -1.39973E-05 *size +1553.353807 
                   	; // Three 24-SAMPLE Least square (Training set 24 with 48 Cores)         
		        	   
		        	

			        	 //******************************************************************//
			        	   //  Linear equation of the training AzureNew-data set (25-1-2017) (  6-Samples with 12 core)//
				           //******************************************************************//
			        	   if  (TrainingSet=="36LS-12Core-AzureNewMix" )   runtime= 8.39724E-05  *size -2054.377        	; // Three 6-SAMPLE Least square (Training set 6 with 12 Cores)         
		        	   
			        	//  Linear equation of the training AzureNew-data set (2-8-2017) (  6-Samples with 12 core)//
				           //******************************************************************//
			        	   if  (TrainingSet=="56LS-12Core-AzureNew" )  runtime= 9E-06 *size + 855.78                      ; // Mixed Five 6-SAMPLE Least square (Training set 6 with 12 Cores)                      	   
		        	   
		        	   break;
		        case "GATKP3":
		        	
		             runtime= 1.26067E-07 *size +666.6469; // Five SAMLE Least square (Training set 6 with 24 Cores)
	            	
	            	  //runtime=850; //The pointer was here
		        	
		        	//runtime = 0.000887119 *size -164050.7299; // Least square
               

	            break;
		        case "HAPLOTYPECALLER1":
		        	
		        	    runtime= 0.001*(size/factor[0])+1102; // Pointer here
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	// runtime= 1370;
		        	//runtime= 5700;
		        	// runtime=2600;
		        	// New version
		        
		          //   runtime= 1E-07*size - 600.06;
		             runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER2":
		        	     runtime=2E-10*Math.pow(size/factor[1], 2)-0.0012*(size/factor[1])+3572.7; //pointer here
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//    runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	//   runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	 // New version
				        
		         // 	 runtime= 1E-07*size - 600.06;  
		            	 runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER3":
		            	runtime=2E-10*Math.pow(size/factor[2], 2)-0.0012*(size/factor[2])+3572.7;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		            // runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		          //   runtime=5.40891E-05 *size +2975.286 ;
		        	
		        	// New version
			        
		        	//  runtime= 1E-07*size - 600.06;
		            	 runtime= 1E-07*size - 388.19;
		             break;
		       
		        case "HAPLOTYPECALLER4":
		        	    runtime=-4E-10*Math.pow(size/factor[3], 2)+0.0032*(size/factor[3])-1707.4;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ; 
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	 //  runtime=5.40891E-05 *size +2975.286 ;
		        	
		        	// New version
			        
		        	//  runtime= 1E-07*size - 600.06; 
		           	 runtime= 1E-07*size - 388.19;
		        	   break;
		        case "HAPLOTYPECALLER5":
		        	  runtime=2E-10*Math.pow(size/factor[4], 2)-0.0012*(size/factor[4])+3572.7;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	 //  runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		         	 //  runtime= 1E-07*size - 600.06;
		         	    runtime= 1E-07*size - 388.19;
		        	   break;
		        case "HAPLOTYPECALLER6":
		        	   runtime=-4E-10*Math.pow(size/factor[5], 2)+0.0032*(size/factor[5])-1707.4;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        //	runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		         	//    runtime= 1E-07*size - 600.06; 
		              runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER7":
		        	  runtime=-4E-10*Math.pow(size/factor[6], 2)+0.0032*(size/factor[6])-1707.4;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//   runtime=5.40891E-05 *size +2975.286 ; 
		        	  
		        	// New version
				        
		         	//   runtime= 1E-07*size - 600.06; 
		          	   runtime= 1E-07*size - 388.19;
		        	   break;
		        case "HAPLOTYPECALLER8":
		        	   runtime= 0.001*(size/factor[7])+1102;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	  //   runtime= 1E-07*size - 600.06;
		         	      runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER9":
		        	   runtime=6E-10*Math.pow(size/factor[8], 2)-0.0054*(size/factor[8])+15505;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	   
		        	// New version
				        
		        	 //    runtime= 1E-07*size - 600.06;
		         	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER10":
		        	   runtime=6E-10*Math.pow(size/factor[9], 2)-0.0054*(size/factor[9])+15505;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	   
		        	// New version
				        
		         	 //   runtime= 1E-07*size - 600.06;
		         	      runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER11":
		        	   runtime=6E-10*Math.pow(size/factor[10], 2)-0.0054*(size/factor[10])+15505;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		         	//    runtime= 1E-07*size - 600.06;  
		        	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER12":	
		        	   runtime=-6E-11*Math.pow(size/factor[11], 2)+0.001*(size/factor[11])+593.32;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	 //runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	 //    runtime= 1E-07*size - 600.06; 
		        	      runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER13":
		              runtime=-6E-11*Math.pow(size/factor[12], 2)+0.001*(size/factor[12])+593.32;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	 //runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		              
		           // New version
				        
		             //   runtime= 1E-07*size - 600.06;
		                 runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER14":
		        	  runtime=-6E-11*Math.pow(size/factor[13], 2)+0.001*(size/factor[13])+593.32;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	//    runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER15":
		        	  runtime=-6E-11*Math.pow(size/factor[14], 2)+0.001*(size/factor[14])+593.32;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ; 
		        	  
		        	// New version
				        
		        	//    runtime= 1E-07*size - 600.06;  
		         	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER16":
		        	   runtime=2E-9*Math.pow(size/factor[15], 2)-0.0092*(size/factor[15])+14272;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	 //    runtime= 1E-07*size - 600.06;
		         	    runtime= 1E-07*size - 388.19;
		        	break;
		        	
		        case "HAPLOTYPECALLER17":
		        	 runtime=2E-9*Math.pow(size/factor[16], 2)-0.0092*(size/factor[16])+14272;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	 
		        	// New version
				        
		        	//   runtime= 1E-07*size - 600.06;
		        	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER18":
		        	   runtime= 0.001*(size/factor[17])+1102;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	   //  runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER19":
		        
		          	 runtime = -0.0009*(size/factor[18])+6567.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		          	 
		          // New version
				        
		           //	  runtime= 1E-07*size - 600.06;
		            	runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER20":
		        	
		        	   runtime = -0.0009*(size/factor[19])+6567.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	 //    runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER21":
		        	
		            runtime = -0.0009*(size/factor[20])+6567.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		            
		         // New version
			        
		             // runtime= 1E-07*size - 600.06; 
		              runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER22":
		        	
		        	   runtime= 0.0023*(size/factor[21])-2969.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	//     runtime= 1E-07*size - 600.06; 
		         	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER23":


		        	  runtime= 0.0023*(size/factor[22])-2969.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	 //   runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER24":
		        	  runtime=2E-9*Math.pow(size/factor[23], 2)-0.0092*(size/factor[23])+14272;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	//    runtime= 1E-07*size - 600.06;  
		         	   runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER25":
		              runtime=6E-10*Math.pow(size/factor[24], 2)-0.0054*(size/factor[24])+15505;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		              
		           // New version
				        
		              //  runtime= 1E-07*size - 600.06;
		                  runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER26":
		         	  runtime=-6E-11*Math.pow(size/factor[25], 2)+0.001*(size/factor[25])+593.32;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 // runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	 //runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		         	  
		         	// New version
				        
		         	 //  runtime= 1E-07*size - 600.06; 
		           	runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER27":
		        	
		        	   runtime= -0.0006*(size/factor[26])+5907;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	  //  runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER28":
		        	
		        	   runtime= -0.0006*(size/factor[27])+5907;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	 //    runtime= 1E-07*size - 600.06;
		               runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER29":
		        
		        	   runtime= -0.0006*(size/factor[28])+5907;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	  //   runtime= 1E-07*size - 600.06;
		         	      runtime= 1E-07*size - 388.19;
		        	break;
		        	
		        case "HAPLOTYPECALLER30":
		        	  runtime=7E-10*Math.pow(size/factor[29], 2)-0.0077*(size/factor[29])+24880;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	 //   runtime= 1E-07*size - 600.06;
		        	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER31":
		         	  runtime=7E-10*Math.pow(size/factor[30], 2)-0.0077*(size/factor[30])+24880;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		         	  
		         	// New version
				        
		         	 //  runtime= 1E-07*size - 600.06;
		         	   runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER32":
		        	   runtime=7E-10*Math.pow(size/factor[31], 2)-0.0077*(size/factor[31])+24880;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	  //   runtime= 1E-07*size - 600.06; 
		         	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER33":
		        	   runtime=5E-10*(Math.pow(size/10.8, 2))-0.0045*(size/10.8)+14235;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	  //   runtime= 1E-07*size - 600.06;
		        	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER34":
		         	  runtime=5E-10*(Math.pow(size/factor[33], 2))-0.0045*(size/factor[33])+14235;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ; 
		         	  
		         	// New version
				        
		           //	 runtime= 1E-07*size - 600.06; 
		           	 runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER35":
		         	  runtime=5E-10*(Math.pow(size/factor[34], 2))-0.0045*(size/factor[34])+14235;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		         	  
		         	// New version
				        
		          	 // runtime= 1E-07*size - 600.06;
		           	  runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER36":
		           runtime=-2E-10*(Math.pow(size/factor[35], 2))+0.0022*(size/factor[35])-1064.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		           
		        // New version
			        
		           //     runtime= 1E-07*size - 600.06;
		             runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER37":
		        	   runtime=-2E-10*(Math.pow(size/factor[36], 2))+0.0022*(size/factor[36])-1064.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ; 
		        	   
		        	// New version
				        
		        	//    runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER38":
		             runtime=4E-11*Math.pow(size/factor[37], 2)+4E-5*(size/factor[37])+2172.5;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		             
		          // New version
				        
		            //  runtime= 1E-07*size - 600.06;
		                 runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER39":
		        	  runtime=4E-11*Math.pow(size/factor[38], 2)+4E-5*(size/factor[38])+2172.5;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	 //  runtime= 1E-07*size - 600.06;
		          	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER40":
		             runtime=4E-11*Math.pow(size/factor[39], 2)+4E-5*(size/factor[39])+2172.5;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		             
		          // New version
				        
		             //   runtime= 1E-07*size - 600.06;
		                runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER41":
		        	   runtime=-6E-11*Math.pow(size/factor[40], 2)+0.001*(size/factor[40])+593.32;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	 // runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	 //    runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER42":
		        	  runtime=4E-11*Math.pow(size/factor[41], 2)+4E-5*(size/factor[41])+2172.5;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	 //   runtime= 1E-07*size - 600.06;
		               runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER43":
		        	  runtime=5E-10*(Math.pow(size/factor[42], 2))-0.0045*(size/factor[42])+14235;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	 // runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	//   runtime= 1E-07*size - 600.06; 
		       	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER44":
		        	   runtime=-2E-10*(Math.pow(size/factor[43], 2))+0.0022*(size/factor[43])-1064.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	 //runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	  //   runtime= 1E-07*size - 600.06;
		        	     runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER45":	
		        	   runtime=(-6E-10)*Math.pow(size/factor[44], 2)+0.0042*(size/factor[44])-2937.4;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50; 
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	//     runtime= 1E-07*size - 600.06; 
		               runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER46":
		        	 runtime=(-6E-10)*Math.pow(size/factor[45], 2)+0.0042*(size/factor[45])-2937.4;
		        	// runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//  runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	 
		        	// New version
				        
		        	  // runtime= 1E-07*size - 600.06;
		         	    runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER47":
		        	  runtime=-2E-10*(Math.pow(size/factor[46], 2))+0.0022*(size/factor[46])-1064.8;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	// runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	  
		        	// New version
				        
		        	//    runtime= 1E-07*size - 600.06;
		       	    runtime= 1E-07*size - 388.19;
		        	break;
		       case "HAPLOTYPECALLER48":
		        	
		    	  runtime=(-6E-10)*Math.pow(size/factor[47], 2)+0.0042*(size/factor[47])-2937.4;
		    	 //  runtime=	(8.1035E-06 *size  -31975.596 )/50;
		    	  //  runtime= 2.92243E-05 *size+ 809.9542347 ;
		    	   //runtime=-9.52401E-07 *size +11539.65 ;
		    	  // runtime=5.40891E-05 *size +2975.286 ;
		    	  
		    	// New version
			        
		    	   //  runtime= 1E-07*size - 600.06;
		              runtime= 1E-07*size - 388.19;
		    	   
		    	   break;
		        case "HAPLOTYPECALLER49":
		        	
		        	   runtime=(-6E-10)*Math.pow(size/factor[48], 2)+0.0042*(size/factor[48])-2937.4;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	//   runtime= 2.92243E-05 *size+ 809.9542347 ;
		        	 //runtime=-9.52401E-07 *size +11539.65 ;
		        	//runtime=5.40891E-05 *size +2975.286 ;
		        	   
		        	// New version
				        
		        	//     runtime= 1E-07*size - 600.06;
		               runtime= 1E-07*size - 388.19;
		        	break;
		        case "HAPLOTYPECALLER50":
		        	
		        	   runtime=1250;
		        	// runtime=5000;
		        	//runtime=  3200;
		        	//runtime=	(8.1035E-06 *size  -31975.596 )/50;
		        	   
		        	// New version
				        
		         	 //   runtime= 1E-07*size - 600.06;
		         	     runtime= 1E-07*size - 388.19;
		        break;
	            default:
	            	

	               break;
	            
	        }}
	       
		   return runtime*1000;
		    
	    }
	  


	


	
	
}


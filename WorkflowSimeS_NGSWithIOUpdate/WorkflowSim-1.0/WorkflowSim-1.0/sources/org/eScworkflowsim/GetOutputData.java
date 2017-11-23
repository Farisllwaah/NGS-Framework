/*******************************************************************************
 * Title: The class of predicting an output data size  
 * Description: This class predicts the output data size for each workflow 
 * in the pipeline that will be input for next one, 
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

/**
 * Based on prediction eqautions this calss can predict an output data size for each workflow 
 * in the pipeline, where it is called by eSC parser calss to fill missing field On the XML file of the task of the workflow 
 * For example, <uses file="BWA_B1_AL_out.dat" link="output" size="????????????"/>. The replacement of "????????????" to value can be acheived 
 * by calling this class.
 * 
 * @author LLWAAH
 *
 */

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.workflowsim.FileItem;


/**
 * @author LLWAAH
 *
 */
public class GetOutputData{
	
	private double output;
	
	
	public GetOutputData(){ 
	
	this.output=0.0;
	}

	public double getOutputData(String name, String link, double size) {
		int factor=0;
		double factorHaplo[]={ 1776.363963,12.24960144,26.63091844,15.03022393,
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
	        if (link.equals("input")){
		     switch (name) {
		     
		        case "BWA1_FEL":
		        case "BWA2_FEL":
		        case "BWA3_FEL":
		        case "BWA4_FEL":
		        case "BWA5_FEL":
		        case "BWA6_FEL":
		             
	            	
	            	
		          	// output = 0.6745*size+7E08;   
		           	
	            //break;
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
		        	
		        	//output = 0.7115*size- 3E07;
		        	//output = 0.6815*size + 5E08;
		        	//output = 0.6815*size+5E08;
		        	//output = (size+7E08)/1.4618;
		         	//output = (size+9E08)/1.4749;// The pointer was here
		        	//  output=   0.674458948*size+677893007.2; // Three SAMLE Least square (Training set 6)				

		             output = 0.681489744 *size+509743555 ;// Least square (12,24,48,96 Cores)
                   //     output=   0.682958628 *size + 4.8E+08   ;
		        	
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
		        	//runtime =(size-(1E+09))/(775245);
		        	//output=1.0387*size+2E08;
		        	// output = 1.0387*size+2E08;// The pointer was here
		        // break;
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
		        	
		         	//output = 1.0294*size+2E08; // The pointer was here
		        	//  output= 1.038655247*size+151858333.8; //Three SAMLE Least square (Training set 6)			
			        output = 1.02577041 *size + 204587078 ; // Least square (12,24,48,96 Cores)
		       
			     // output= 1.015443 *size + 3E+08  ; 
			    break;
		        
		        case "PICARD1":
		        case "PICARD2":
		        case "PICARD3":
		        case "PICARD4":
		        case "PICARD5":
		        case "PICARD6":
		             
	            	//runtime =(size-(5E+09))/(829515);
		        	//  output=0.8435*size-7E08; // The pointer was here
		        	
		        	
	           //  break;
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
		        	
		        	// output = 0.9156 * size+ 2E08; // The pointer was here
		        	//  output= 0.843485359	*size-733042837.5 ; //Three SAMLE Least square (Training set 6)
		             output=0.981865508 *size -1220190052 ; // Least square (12,24,48,96 Cores)
                    //    output= 0.592304 *size+ 3.46E+09 ;
		        	
		        	
		        break;	
		        case "GATKP1_1":
		        case "GATKP1_2":
		        case "GATKP1_3":
		        case "GATKP1_4":
		        case "GATKP1_5":
		        case "GATKP1_6":
		             
	            
		        	//output= 1E-05*size+2E07;
		        	//output = 0.2023*size+1E09;
		        	// output= 0.353264426*size+1.02E+07; // The pointer was here


	            // break;
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
		        	
		        	
		        	
		        	//  output = 0.2296*size+6E08;// The pointer was here
		        	//  output= 0.202361206	*size+1334355119 ; // Three SAMLE Least square (Training set 6)
		        	   output= 0.16984334 *size+1536114084; // Least square (12,24,48,96 Cores)
		        	 

		        	  
		        break;
		        case "COVERAGE1": 
		        case "COVERAGE2":
		        case "COVERAGE3":
		        case "COVERAGE4":
		        case "COVERAGE5":
		        case "COVERAGE6":
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
		        	
		           if (name.equals("COVERAGE1") ) {factor= 171;}
		           if (name.equals("COVERAGE2") ) {factor= 175;}
		           if (name.equals("COVERAGE3") ) {factor= 212;}
		           if (name.equals("COVERAGE4") ) {factor= 172;}
		           if (name.equals("COVERAGE5") ) {factor= 204;}
		           if (name.equals("COVERAGE6") ) {factor= 225;}
		           if (name.equals("COVERAGE7") ) {factor= 229;}
		           if (name.equals("COVERAGE8") ) {factor= 178;}
		           if (name.equals("COVERAGE9") ) {factor= 205;}
		           if (name.equals("COVERAGE10") ) {factor= 199;}
		           if (name.equals("COVERAGE11") ) {factor= 198;}
		           if (name.equals("COVERAGE12") ) {factor= 199;}
		        //	output= size/factor;
		        	   output=-8.05569E-06 *size+16130540.9; // Least square (12,24,48,96 Cores)
	            break;
		        case "VARIANTA":
		        	//output= 0.9999*size- 579378; // The pointer was here
	            	//runtime =(size+(2E+08))/(2E+08);
	            	//runtime=846;
		        	output= 0.998907146 *size  -51507.88 ; // Least square (12,24,48,96 Cores)

		        	
	            break;
		        case "VCF1":
		        case "VCF2":
		        case "VCF3":
		        case "VCF4":
		        case "VCF5":
		        case "VCF6":
		             
	            	//output= 0.0903*size+3E07;
		        	//output = 0.0827*size+4E07;
		         

	           // break;
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
		        	
		        	
		        	
		        	
		        	
		        	//output = 0.1297*size+3E07;
		        	// output = 0.1346*size+3E07;// The pointer was here
		        	 // output= 0.090211761	*size+32919449.65; // Three SAMLE Least square (Training set 6)
		        
		        	  output =0.00553647 *size +44440974.4; // Least square (12,24,48,96 Cores)

		        break;
		        case "ANNOTATE1":
		        case "ANNOTATE2":
		        case "ANNOTATE3":
		        case "ANNOTATE4":
		        case "ANNOTATE5":
		        case "ANNOTATE6":
		             
	          // output= 5.6141*size-4E07;// 10 samples
		       //  output = (size-5E06)/0.1666;	// The pointer was here
	          //   break;
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
		        	
		        //output = 5.4929*size-3E07;// 10 samples
		         	//output = 5.2821*size-2E07;// The pointer was here
		        	 // output= 5.742605341	*size+	-18232286.84; // Three SAMLE Least square (Training set 6)
		        	   output= 2.344435421 *size + 107767724.4 ; // Least square (12,24,48,96 Cores)

		        break;
	            case "GATKP3":
		          //   output = 210410534;
		             output= 1.051582502 *size +16021113.68 ; // Least square (12,24,48,96 Cores)


	            	break;
		        case "HAPLOTYPECALLER1":
		             output = size/factorHaplo[0];
		        	 
		        	break;
		        case "HAPLOTYPECALLER2":
		        	 output =size/factorHaplo[1];
		        	 
		        	break;
		        case "HAPLOTYPECALLER3":
		        	 output =size/factorHaplo[2];
		        	 
		        	break;
		       
		        case "HAPLOTYPECALLER4":
		        	 output =size/factorHaplo[3];
		        	 
		        	break;
		        case "HAPLOTYPECALLER5":
		        	 output =size/factorHaplo[4];
		        	 
		        	break;
		        case "HAPLOTYPECALLER6":
		        	 output =size/factorHaplo[5];
		        	 
		        	break;
		        case "HAPLOTYPECALLER7":
		        	 output =size/factorHaplo[6];
		        	 
		        	break;
		        case "HAPLOTYPECALLER8":
		        	 output =size/factorHaplo[7];
		        	 
		        	break;
		        case "HAPLOTYPECALLER9":
		        	 output =size/factorHaplo[8];
		        	 
		        	break;
		        case "HAPLOTYPECALLER10":
		        	 output =size/factorHaplo[9];
		        	 
		        	break;
		        case "HAPLOTYPECALLER11":
		        	 output =size/factorHaplo[10];
		        	 
		        	break;
		        case "HAPLOTYPECALLER12":	
		        	 output =size/factorHaplo[11];
		        	 
		        	break;
		        case "HAPLOTYPECALLER13":
		        	 output =size/factorHaplo[12];
		        	 
		        	break;
		        case "HAPLOTYPECALLER14":
		             output =size/factorHaplo[13];
		        	 
		        	break;
		        case "HAPLOTYPECALLER15":
		        	 output =size/factorHaplo[14];
		        	 
		        	break;
		        case "HAPLOTYPECALLER16":
		        	 output =size/factorHaplo[15];
		        	 
		        	break;
		        case "HAPLOTYPECALLER17":
		        	 output =size/factorHaplo[16];
		        	 
		        	break;
		        case "HAPLOTYPECALLER18":
		        	 output =size/factorHaplo[17];
		        	 
		        	break;
		        case "HAPLOTYPECALLER19":
		        	 output =size/factorHaplo[18];
		        	 
		        	break;
		        case "HAPLOTYPECALLER20":
		        	 output =size/factorHaplo[19];
		        	 
		        	break;
		        case "HAPLOTYPECALLER21":
		        	 output =size/factorHaplo[20];
		        	 
		        	break;
		        case "HAPLOTYPECALLER22":
		        	 output =size/factorHaplo[21];
		        	 
		        	break;
		        case "HAPLOTYPECALLER23":
		         	output =size/factorHaplo[22];
		        	 
		        	break;
		        case "HAPLOTYPECALLER24":
		         	output =size/factorHaplo[23];
		        	 
		        	break;
		        case "HAPLOTYPECALLER25":
		         	output =size/factorHaplo[24];
		        	 
		        	break;
		        case "HAPLOTYPECALLER26":
		        	 output =size/factorHaplo[25];
		        	 
		        	break;
		        case "HAPLOTYPECALLER27":
		        	 output =size/factorHaplo[26];
		        	 
		        	break;
		        case "HAPLOTYPECALLER28":
		        	output =size/factorHaplo[27];
		        	 
		        	break;
		        case "HAPLOTYPECALLER29":
		        	 output =size/factorHaplo[28];
		        	 
		        	break;
		        case "HAPLOTYPECALLER30":
		        	 output =size/factorHaplo[29];
		        	 
		        	break;
		        case "HAPLOTYPECALLER31":
		        	 output =size/factorHaplo[30];
		        	 
		        	break;
		        case "HAPLOTYPECALLER32":
		            output =size/factorHaplo[31];
		         
		        	break;
		        case "HAPLOTYPECALLER33":
		        	 output =size/factorHaplo[32];
		        	 
		        	break;
		        case "HAPLOTYPECALLER34":
		        	 output =size/factorHaplo[33];
		        	 
		        	break;
		        case "HAPLOTYPECALLER35":
		        	 output =size/factorHaplo[34];
		        	 
		        	break;
		        case "HAPLOTYPECALLER36":
		        	 output =size/factorHaplo[35];
		        	 
		        	break;
		        case "HAPLOTYPECALLER37":
		        	 output =size/factorHaplo[36];
		        	 
		        	break;
		        case "HAPLOTYPECALLER38":
		         	output =size/factorHaplo[37];
		        	 
		        	break;
		        case "HAPLOTYPECALLER39":
		        	 output =size/factorHaplo[38];
		        	 
		        	break;
		        case "HAPLOTYPECALLER40":
		         	output =size/factorHaplo[39];
		         
		        	break;
		        case "HAPLOTYPECALLER41":
		           output =size/factorHaplo[40];
		        	 
		        	break;
		        case "HAPLOTYPECALLER42":
		        	output =size/factorHaplo[41];
		        	 
		        	break;
		        case "HAPLOTYPECALLER43":
		        	 output =size/factorHaplo[42];
		        	 
		        	break;
		        case "HAPLOTYPECALLER44":
		         	output =size/factorHaplo[43];
		        	 
		        	break;
		        case "HAPLOTYPECALLER45":	
		         	output =size/factorHaplo[44];
		        	 
		        	break;
		        case "HAPLOTYPECALLER46":
		         	output =size/factorHaplo[45];
		        	 
		        	break;
		        case "HAPLOTYPECALLER47":
		           output =size/factorHaplo[46];
		        	 
		        	break;
		       case "HAPLOTYPECALLER48":
		    	    output =size/factorHaplo[47];
		    	   
		        	break;
		        case "HAPLOTYPECALLER49":
		        	
		         	output =size/factorHaplo[48];
		        	 
		        	break;
		        case "HAPLOTYPECALLER50":
		        	 output =size/factorHaplo[49];
		        	 
		        	
		        break;
	            default:
	            	
	               break;
		     
	        }
	        }
		   return output;
		   
	    }
}

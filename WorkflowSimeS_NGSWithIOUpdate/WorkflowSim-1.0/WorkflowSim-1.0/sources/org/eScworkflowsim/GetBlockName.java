/*******************************************************************************
 * Title: The interface class
 * Description: This class implements the Set interface of the workflows' names of the pipeline, 
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/******************************************************
 * In particular, this class offers constant time performance for the basic operation such as, contains. So, when the workflow name needs to be checked
 * by another class, it returns either True or False. The eSC parser has been used this class to check a specific workflow's name existing on the XML block constructing and filling with input data and execution time. 
 * 
 * @author Faris Llwaah
 *
 */

public enum GetBlockName {;
	

	public static Set<String>  BWAB_AL = new HashSet<String>(Arrays.asList(new String[] {
	        "BWA_A1_AL","BWA_B1_AL",
	        "BWA_A2_AL","BWA_B2_AL",
	        "BWA_A3_AL","BWA_B3_AL",
	        "BWA_A4_AL","BWA_B4_AL",
	        "BWA_A5_AL","BWA_B5_AL",
	        "BWA_A6_AL","BWA_B6_AL",
	        "BWA_A7_AL","BWA_B7_AL",
	        "BWA_A8_AL","BWA_B8_AL",
	        "BWA_A9_AL","BWA_B9_AL",
	        "BWA_A10_AL","BWA_B10_AL",
	        "BWA_A11_AL","BWA_B11_AL",
	        "BWA_A12_AL","BWA_B12_AL",
	        "BWA_A13_AL","BWA_B13_AL",
	        "BWA_A14_AL","BWA_B14_AL",
	        "BWA_A15_AL","BWA_B15_AL",
	        "BWA_A16_AL","BWA_B16_AL",
	        "BWA_A17_AL","BWA_B17_AL",
	        "BWA_A18_AL","BWA_B18_AL",
	        "BWA_A19_AL","BWA_B19_AL",
	        "BWA_A20_AL","BWA_B20_AL",
	        "BWA_A21_AL","BWA_B21_AL",
	        "BWA_A22_AL","BWA_B22_AL",
	        "BWA_A23_AL","BWA_B23_AL",
	        "BWA_A24_AL","BWA_B24_AL",
	 }));
	public static Set<String> BWA_FEL = new HashSet<String>(Arrays.asList(new String[] {
        "BWA1_FEL", "BWA2_FEL","BWA3_FEL",
        "BWA4_FEL", "BWA5_FEL","BWA6_FEL",
        "BWA7_FEL", "BWA8_FEL","BWA9_FEL",
        "BWA10_FEL", "BWA11_FEL","BWA12_FEL",
        "BWA13_FEL", "BWA14_FEL","BWA15_FEL",
        "BWA16_FEL", "BWA17_FEL","BWA18_FEL",
        "BWA19_FEL", "BWA20_FEL","BWA21_FEL",
        "BWA22_FEL", "BWA23_FEL","BWA24_FEL",
        
        
 }));
	public static Set<String> PICARD = new HashSet<String>(Arrays.asList(new String[] {
        "PICARD1", "PICARD2","PICARD3",
        "PICARD4", "PICARD5","PICARD6",
        "PICARD7", "PICARD8","PICARD9",
        "PICARD10", "PICARD11","PICARD12",
        "PICARD13", "PICARD14","PICARD15",
        "PICARD16", "PICARD17","PICARD18",
        "PICARD19", "PICARD20","PICARD21",
        "PICARD22", "PICARD23","PICARD24",
        
        
 }));
	public static Set<String> GATKP1_N = new HashSet<String>(Arrays.asList(new String[] {
        "GATKP1_1", "GATKP1_2","GATKP1_3",
        "GATKP1_4", "GATKP1_5","GATKP1_6",
        "GATKP1_7", "GATKP1_8","GATKP1_9",
        "GATKP1_10", "GATKP1_11","GATKP1_12",
        "GATKP1_13", "GATKP1_14","GATKP1_15",
        "GATKP1_16", "GATKP1_17","GATKP1_18",
        "GATKP1_19", "GATKP1_20","GATKP1_21",
        "GATKP1_22", "GATKP1_23","GATKP1_24",
        
        
 }));
	public static Set<String> COVERAGE_N = new HashSet<String>(Arrays.asList(new String[] {
	        "COVERAGE1", "COVERAGE2","COVERAGE3",
	        "COVERAGE4", "COVERAGE5","COVERAGE6",
	        "COVERAGE7", "COVERAGE8","COVERAGE9",
	        "COVERAGE10", "COVERAGE11","COVERAGE12",
	        "COVERAGE13", "COVERAGE14","COVERAGE15",
	        "COVERAGE16", "COVERAGE17","COVERAGE18",
	        "COVERAGE19", "COVERAGE20","COVERAGE21",
	        "COVERAGE22", "COVERAGE23","COVERAGE24",
	        
	        
	 }));
	
	public static Set<String> VARIANTA = new HashSet<String>(Arrays.asList(new String[] {
	        "VARIANTA"
	        
	 }));
	public static Set<String> HAPLOTYPECALLER_N = new HashSet<String>(Arrays.asList(new String[] {
	        "HAPLOTYPECALLER1", "HAPLOTYPECALLER2","HAPLOTYPECALLER3",
	        "HAPLOTYPECALLER4", "HAPLOTYPECALLER5","HAPLOTYPECALLER6",
	        "HAPLOTYPECALLER7", "HAPLOTYPECALLER8","HAPLOTYPECALLER9",
	        "HAPLOTYPECALLER10", "HAPLOTYPECALLER11","HAPLOTYPECALLER12",
	        "HAPLOTYPECALLER13", "HAPLOTYPECALLER14","HAPLOTYPECALLER15",
	        "HAPLOTYPECALLER16", "HAPLOTYPECALLER17","HAPLOTYPECALLER18",
	        "HAPLOTYPECALLER19", "HAPLOTYPECALLER20","HAPLOTYPECALLER21",
	        "HAPLOTYPECALLER22", "HAPLOTYPECALLER23","HAPLOTYPECALLER24",
	        "HAPLOTYPECALLER25", "HAPLOTYPECALLER26","HAPLOTYPECALLER27",
	        "HAPLOTYPECALLER28", "HAPLOTYPECALLER29","HAPLOTYPECALLER30",
	        "HAPLOTYPECALLER31", "HAPLOTYPECALLER32","HAPLOTYPECALLER33",
	        "HAPLOTYPECALLER34", "HAPLOTYPECALLER35","HAPLOTYPECALLER36",
	        "HAPLOTYPECALLER37", "HAPLOTYPECALLER38","HAPLOTYPECALLER39",
	        "HAPLOTYPECALLER40", "HAPLOTYPECALLER41","HAPLOTYPECALLER42",
	        "HAPLOTYPECALLER43", "HAPLOTYPECALLER44","HAPLOTYPECALLER45",
	        "HAPLOTYPECALLER46", "HAPLOTYPECALLER47","HAPLOTYPECALLER48",
	        "HAPLOTYPECALLER49", "HAPLOTYPECALLER50",
	        
	        
	 }));
	public static Set<String> VARIANTB = new HashSet<String>(Arrays.asList(new String[] {
	        "VARIANTB"
	        
	 }));
	
	public static Set<String> VCF_N = new HashSet<String>(Arrays.asList(new String[] {
	        "VCF1", "VCF2","VCF3",
	        "VCF4", "VCF5","VCF6",
	        "VCF7", "VCF8","VCF9",
	        "VCF10", "VCF11","VCF12",
	        "VCF13", "VCF14","VCF15",
	        "VCF16", "VCF17","VCF18",
	        "VCF19", "VCF20","VCF21",
	        "VCF22", "VCF23","VCF24",
	        
	        
	 }));
	public static Set<String> GATKP3 = new HashSet<String>(Arrays.asList(new String[] {
	        "GATKP3"
	        
	 }));
	public static Set<String> ANNOTATE_N = new HashSet<String>(Arrays.asList(new String[] {
	        "ANNOTATE1", "ANNOTATE2","ANNOTATE3",
	        "ANNOTATE4", "ANNOTATE5","ANNOTATE6",
	        "ANNOTATE7", "ANNOTATE8","ANNOTATE9",
	        "ANNOTATE10", "ANNOTATE11","ANNOTATE12",
	        "ANNOTATE13", "ANNOTATE14","ANNOTATE15",
	        "ANNOTATE16", "ANNOTATE17","ANNOTATE18",
	        "ANNOTATE19", "ANNOTATE20","ANNOTATE21",
	        "ANNOTATE22", "ANNOTATE23","ANNOTATE24",
	        
	        
	 }));
	
	
	
	
}

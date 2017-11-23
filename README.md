# NGS-Framework
A new simulation framework for the well-known CloudSim &amp; WorkflowSim simulators to predict a Runtime for Big Data workflow-NGS pipeline

# Project Overview
This version is up-to-date after getting a ValueTools-2017 acceptance of the paper.
It includes the real Data set (Input Data size & execution time) of running NGS pipeline. However, this new data set has been collected 
from Microsoft Azure Cloud of to improve the estimation of our Framework after adding a new component to   the WorkflowSim & CloudSim.
The development is achieved by adding a specific unit of the I/O contentions solution.

# Software Requirements
Either Java version 1.6 or newer 

1- Installation and running the Simulator

This software is built on to of WorkflowSim, which is an extension of the CloudSim simulator. Where we have adapted it to simulate the
execution behavior of the NGS pipeline as implemented in e-Science Central (e-SC). So, for more information you can visit the link
https://github.com/WorkflowSim/WorkflowSim-1.0/blob/master/readme.txt. 

# Running the Framework for parameters

Initially, The framework needs input parameters such as MIPs and BandWidth that should be set before you are starting a test of a large
input sample. So, you can run the Framework to work in a loop to find these parameters and then use them to configure input parameters. 
For more details, you can see an example that is in the PDF file 
https://github.com/Farisllwaah/NGS-Framework/blob/master/How%20to%20create%20MIPs%20%26%20BW.pdf

# How to use the Input parameters  

First if you are using Eclipse, open your Eclipse, create a new java project called "Any name". Right click your project, choose
'Import'. Now, the code is ready to run, so you have to start with initial step which is getting an optimal values for both MIPs & BW.

# Run The Main class

1- Replace the string daxPath = " /WorkflowSim-NGSIOWithlastUpdate/DAX files with cores/All- actual 6S files/6SamplesHaplotype/NGS
processing531881n-6S-12Core-(AzureNewH).XML"; to your working directory path. Go to,  org.eScworkflowsim, open class GetSampleInput.java

2- Run the main class i.e., org.workflowsim.eSC.Main Open eSCNGSTrainingTestingSamples.java, but to complete this class and make it run,
you will need to set all variables on, raed through the text so that you can see the following link:
https://github.com/Farisllwaah/NGS-Framework/blob/master/Thedescription%20of%20input%20parameters.pdf

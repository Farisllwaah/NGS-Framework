# NGS-Framework
A new simulation framework for the well-known CloudSim &amp; WorkflowSim simulators to predict a Runtime for Big Data workflow-NGS pipeline

# Project Overview
This version is up-to-date after getting a ValueTools-2017 acceptance of the paper.
It includes the real Data set (Input Data size & execution time) of running NGS pipeline. However, this new data set has been collected 
from Microsoft Azure Cloud of to improve the estimation of our Framework after adding a newly component to   the WorkflowSim & CloudSim.
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
For more details, you can see an example that is in the PDF file How to create MIPs & BW. 

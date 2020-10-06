# General info
This document is intended on how to execute Weather Reporting Comparator Framework for comparing weather through UI
 and API

## Note :

1.To Execute the TestCases,Run TestExecution.xml file.

2.TestExecution Report can be be found within Reports folder in WeatherReport.html File.

## Problem Statement:
The detailed problem statement can be found in ProblemStatement.pdf



## To run our project using Jar files present in Target Folder,We can use

Ensure that you ran this command successfully. mvn clean package -DskipTests

Go to the target directory.

Ensure that you see 2 jars and libs directory with all other supporting jars as shown in the previous lecture.

Most of the students make this mistake! Windows users:  use semicolon  ;

java -cp selenium-docker.jar;selenium-docker-tests.jar;libs/* org.testng.TestNG ../search-module.xml

Mac/Linux users: use colon :

java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* org.testng.TestNG ../TestExecution.xml

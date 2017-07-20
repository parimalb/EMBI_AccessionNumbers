# Accession Numbers Range Calculator

This is a java based application which can be used to sort a list of accession numbers and then combine them in ranges. This application is designed to accept comma separated values. For e.g if you pass input list as ERR000121,ERR000115,ERR000116. The output received would be {ERR000115-ERR000116,ERR000121}. You can import this project as gradle project into your preffered IDE eclipse/intellij.


### Prerequisites

This project is a gradle based project so you need to have gradle instaled in your system. To execute the application you will use gradle task named "run". This project uses java 8 features so it is compatible for compilation with java versions 8 and above. This project is built using java version "1.8.0_73" and with gradle version of "2.1.0".
Java Version: Java 8 and above
Gradle Version: Gardle 2.1.0 and above

### Installing

You can install gradle from gradle home page by following instriction stated in [Wiki](https://gradle.org/install/)


## Running the Application

Extract the zip file on your system and browse to root of the root project folder i.e. folder named AccessionNumbers via command line or powershell in windows machine. On your command prompt type command 
```
gradle run
```
It will wait for your input. Please input a comma separated accession number list in the console and press enter key when you are done with input numbers. The program will take your input list and provide a sorted range list.
Below is an snippet of sample run of application.

```
PS C:\Users\pbatr3\workspace\AccessionNumbers> gradle run
:compileJava
:processResources UP-TO-DATE
:classes
:run
2017-07-20 15:03:54 INFO  AccessioNumbersMain:14 - Please input list a comma seprated list of Accession Numbers. For e.g
. A00000,A0001,ERR000111,ERR000112 and so on.
log4j:ERROR Could not find value for key log4j.appender.
log4j:ERROR Could not instantiate appender named "".
> Building 75% > :runA00000,A0001,ABCDEFG1,DRR2110012,ERR000111,ERR000112,ERR000113,ERR000115,ERR000116,ERR100114,ERR200
000001,ERR200000002,ERR200000003,SRR211001
2017-07-20 15:04:13 INFO  AccessioNumbersMain:19 - The sorted list is as follow:
2017-07-20 15:04:13 INFO  AccessioNumbersMain:20 - [A00000, A0001, ABCDEFG1, DRR2110012, ERR000111-ERR000113, ERR000115-
ERR000116, ERR100114, ERR200000001-ERR200000003, SRR211001]
``` 


## Built With

* [Gradle](https://gradle.org/) - Dependency Management


## Authors

* **Parimal Batra** 




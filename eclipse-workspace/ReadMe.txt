Name
Dog Competition App

Purpose
This document contains details about the Dog Competition App
and instructions on how to run and use the App. 

Description
The app is designed for a Dog Competition where the dogs compete 
in different courses.  The app will allow the user to print a 
report of all the dogs that competited in the user's choice of 
course.  The report will also summarize the winning dog and the
dog that got the most penalties.  The app will also allow a 
user to add a dog and the dog's information to the competition.  
Lastely, it will allow the user to exit the program.  

Installation
There are two methods for running the app.  The first is to click
on the RUN.bat file included with this zip file under assignment1b.  
The second is to go into command prompt and from the file location 
type the following "java -jar JWegen1.jar". Please note, the jar file
is located in the assignment1b folder.

Usage
Once the app is running it will display the app's menu. Please see
example below:

C:\Users\783661\eclipse-workspace>java -jar JWegen1.jar
Welcome to the Dog Competition App!
Please make your selection.
[1] Create Report
[2] Add Dog
[3] Exit Program
Your Selection:

A selection can be made of either 1 to Create report, 2 to Add Dog, or
3 to Exit Program. 

Selecting 1 will bring up another menu to select which course the user
wants the report on.  See example below:

What course would you like the report on?
[J] for Jumpers
[G] for Gamblers
[T] for Titling
Your Selection:

A selection can be made of either J for Jumpers, G for Gamblers, or
T for Titling.  

Once the course has been selected the report will be generated and can be 
accessed under assignment1b/res/report.txt.  

After the report has been created it will display the main menu once again.

Selecting 2 will ask the user to enter all the dog's information in order to 
add the dog to the competition.  It will ask for the dog's ID, name, 
total running time, penalties, and the course code the
dog competed in.  It will not allow a user to continue on if they enter
the information incorrectly. After the course code has been entered the dog will
be added to the competition.

After the dog has been added it will display the main menu once again. 

Selecting 3 will exit the program. 

Support
Email jocelyn.wegen@edu.sait.ca for any questions or comments. Further information, 
such as javadoc html files and a word document containing the Class UML diagrams, 
can be found in assignment1b/doc. 

Author
Jocelyn Wegen



 
  
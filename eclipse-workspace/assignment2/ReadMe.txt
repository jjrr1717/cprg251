WKRP Playlist App

Purpose
This document contains detials about the App for WKRP Radio Station.
It also provides instructions on how to run and use the App. 

Description 
The app is designed for WKRP radion station. It's purpose is to 
manage their playlist.  The playlist contains Talk Shows, Songs, 
and Commericals.  The playlist is contained in a txt file called
database.txt located in the res folder.  The app allows the user
to add an item, remove an item, search for an item, create a
random playlist (prompting user with time of the randomized 
playlist), print reports of the playlist (e.g. print all items
within certain a certain category), and the user can exit 
the program.  All the changes the user made will be automatically 
saved upon exiting the app.  

Installation
There are two methods for running the app.  The first is to click
on the RUN.bat file included with this zip file under assignment2.  
The second is to go into command prompt and from the file location 
type the following "java -jar JWegen2.jar". Please note, the jar file
is located in the assignment2 folder.

Usage
Once the app is running it will display the app's main menu. Please 
see example below:

C:\Users\783661\eclipse-workspace\assignment2>java -jar JWegen2.jar
Welcome to WKRP Radio Station's Playlist App!
Please make a selection.
[1]Add an item
[2]Search for an item by id
[3]Remove an item by id
[4]Create a random playlist
[5]Print
[6]Exit
Selection:

The user can make their selection. 
Selecting 1 will allow the user to add an item.  It will ask for all
the requriments to add an item.  If the user enters an invalid id, it
will give a warning and ask them to enter another id.  

Selecting 2 will allow the user to search the playlist by id.  If the 
user enters an id that does not exist it will ask the user to enter again. 
If the user enters an existing id it will show the detials of that item. 
See example below:

Welcome to WKRP Radio Station's Playlist App!
Please make a selection.
[1]Add an item
[2]Search for an item by id
[3]Remove an item by id
[4]Create a random playlist
[5]Print
[6]Exit
Selection: 2

Please enter the Id number you want to search for: 1003
ID: 1003
Category: R
Play Time: 3:0
Audio File: T1.MP3
Song's Title: (I Can't Get No) Satisfaction
Song's Artist or Group: The Rolling Stones

Selecting 3 will allow the user to delete an item. It will ask for the 
items id. If the user enters and id that does not exist it will give
a warning and ask them to enter another id.  It it does exit, it will
remove that item.  

selecting 4 will allow the user to create a playlist of random items within
the time specified by the user.  It has a grace time of + or - 30 seconds. It 
will display the playlist in the console, but also save it to a file 
for convience.  Please see example below: 

Welcome to WKRP Radio Station's Playlist App!
Please make a selection.
[1]Add an item
[2]Search for an item by id
[3]Remove an item by id
[4]Create a random playlist
[5]Print
[6]Exit
Selection: 4

Please enter the time for the randomly generated playlist.
Enter the minutes: 20
Enter the seconds: 30
[ID: 1053
Category: R
Play Time: 3:0
Audio File: T1.MP3tones
Song's Title: Sympathy for the Devil
Song's Artist or Group: The Rolling Stones

, ID: 1018
Category: C
Play Time: 0:30
Audio File: comp4less1.mp3
Company: Computers '4 Less

, ID: 1084
Category: P
Play Time: 3:0
Audio File: T1.MP3
Song's Title: Every Breath You Take
Song's Artist or Group: The Police

, ID: 1009
Category: V
Play Time: 1:30
Audio File: subaru.mp3
Company: Subarus For Less

, ID: 1017
Category: R
Play Time: 3:0
Audio File: T1.MP3
Song's Title: Imagine
Song's Artist or Group: John Lennon

, ID: 1035
Category: P
Play Time: 3:0
Audio File: T1.MP3
Song's Title: Jailhouse Rock
Song's Artist or Group: Elvis Presley

, ID: 1093
Category: R
Play Time: 3:0
Audio File: T1.MP3
Song's Title: Whole Lotta Love
Song's Artist or Group: Led Zeppelin

, ID: 1063
Category: R
Play Time: 3:0
Audio File: T1.MP3
Song's Title: Hound Dog
Song's Artist or Group: Elvis Presley

, ID: 1028
Category: H
Play Time: 0:45
Audio File: johnson1.mp3
Company: Johnson & Johnson

]

Selecting 5 will allow the user to print reports of the playlist.  The user
can print the whole list, or they may print a filtered report by Item type 
(Song, Talk Show, Commercial) and a category.  
See example below:

Please select what you want to print.
[1]Complete list
[2]Songs
[3]Talk Shows
[4]Commercials
[5]Return to main menu
Selection: 4

Please select what commercial category  you want to print.
[V]Vehicle dealers
[H]Household products
[C]Computers
[M]Miscellaneous
[E]Return to print menu
Selection: v

List of filtered playList:
ID: 1008
Category: V
Play Time: 0:45
Audio File: T4.mp3
Company: Stoney Trail Ford


ID: 1009
Category: V
Play Time: 1:30
Audio File: subaru.mp3
Company: Subarus For Less

Selecting 6 will exit the program.  Upon exiting the program will automatically
save all the changes the user made to the playlist.  

Support
Email jocelyn.wegen@edu.sait.ca for any questions or comments. Further information, 
such as javadoc html files and a visio document containing the Class UML diagrams, 
can be found in assignment2/doc. 

Author
Jocelyn Wegen


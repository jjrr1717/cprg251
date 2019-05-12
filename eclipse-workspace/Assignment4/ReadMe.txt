SAIT MLS App

Purpose
This document contains detials about the App for SAIT MLS App.
It also provides instructions on how to run and use the App.

Description
The app is designed for SAIT MLS. It's purpose is to manage their Clients, their 
Residential properties, and commercial properties. It is an interactive GUI where
a user can search, add, remove, or modify an entry, whether that is a client, 
residential property, or commercial property. All modifications are changed in 
when the user closes the app.  

Installation
To run the app, double click on the jar file called JWegen4.jar  

Usage

When the app is opened it defaults to the client panel. 
From the client panel a search can be completed by Id, Firstname, Lastname, Phone Number, 
Postal Code and Type. Clicking search will display the results. To see more details of an item
select it from the JList to populate the fields in the right panel with
the item's information. An item can be modified by just typing or selecting new 
information. A new item can also be added by entering new information.  The
save button will either save a new item or save a modified item. The delete 
button will remove an item.  The clear button will clear the screen. 
Firstname and Lastname can only support 20 characters.  Address can only support 
50 characters.  Phone number must be in the format xxx-xxx-xxxx and postal code
must be in the format A1A 1A1.  Client type can either be R for residential, C
for commercial, or B for Both. 

At the top of the GUI there are two additional buttons, one for Residential and one for 
Commercial.  Clicking on these will take the user to each respective panel. 
Both of these panels contain the same functionality as the client panel, with
a few and minor differences. 

The residential panel allows a user to search by Id, Asking Price, Quadrant, and Legal Description.  
Searching by asking price will show results that greater than the value entered.  
Legal Description must be in the format similar to this: 1981J9359-31.  Bathrooms 
must be entred in decimal format with a .0 or .5.  Quadrant can be SW, SE, NW, or NE. 
Zone can be R1,R2, R3, R4, I1, I2, I3, or I4. Garage type can be either A for 
attached, D for detached, or n for none.  

The commercial panel allows a user to search by Id, Asking Price, Quadrant, or Legal Description.  
It has similar properties as the client and residential panels with few differences.
Type can be either O for office or M for manufacturing.   

Clicking the x on the top right will close the app.  This will also permanently save
all the changes made during the session. 

Support
Email jocelyn.wegen@edu.sait.ca for any questions or comments. Further information, 
such as javadoc html files can be found in assignment4/doc. 

Author
Jocelyn Wegen  



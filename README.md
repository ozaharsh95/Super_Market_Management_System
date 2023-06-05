
# Super Market Management System



## DATABASE CONECTIVITY :


In Mysql DBMS, 
- create database named as "supermarketmanagementsystem".
+ create tables named as "login" ,"employee" and "product".

<img src="screenshots/s1.png" >

Structure of "login" table

<img src="screenshots/s2.png" >


Structure of "employee" table

<img src="screenshots/s3.png" >

Structure of "product" table
## How to run an application

+ make database connectivity
+ add external jar files to project directory
+ run the file "src/Splash.java"
## Working of Application


<img src="screenshots/s4.png" >

+ run src/Splash.java


<img src="screenshots/s5.png" >

+ Signup module for new employee
+ Here are 3 types of users(Admin,Manager,Cashier)
+ Input validation :
    + Password and Confirm-Password should be same
    + Username and Password have to be more 0 length and less than size of VARCHAR specified in DB.
    + Password must contain atleast one Uppercase [A-Z] ,Lowercase [a-z], Number [0-9] and Special Char(@#$%) 

<img src="screenshots/s6.png" >

+ Added detailes for one employee


<img src="screenshots/s7.png" >

+ DETAILS also added successfully to "login" table


<img src="screenshots/s8.png" >


+ login as "Navin"

<img src="screenshots/s9.png" >

+ Since "Navin" is cashier, he is able to see only options for logout and billing System


<img src="screenshots/s10.png" >

+ after enter detailes of cutomer we can add items by thier Id or Name using add1 button or add2 button 


<img src="screenshots/s11.png" >


+ after pessing bill you will bill in your textarea


<img src="screenshots/s12.png" >

+ save as will create txt file for bill and refresh button reset input fields



![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

+ when you logged in as "admin" you will get additionally two more functionality for employee management and product management



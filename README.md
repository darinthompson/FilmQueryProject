### DESCRIPTION
- This is a basic application that lays the foundation for accessing a mySQL database with Java. Prepared Statements are used to prevent SQL Injection attacks even at such a beginning level using best secutrity practices is vital. 

### TECHNOLOGIES USED
- Java
- Eclipse
- VIM
- mySQL Database
- SQL
- Pendleton Canadian Whiskey 12 year
- George Duke Trio (For that Jazz Groove)

### LESSONS LEARNED
-  This biggest thing I learned is how to handle the different default values for fields between Java and mySQL, specifically NULL. NULL sets or values don't throw errors in mySQL, but when parsing data out of the database and trying to display it Java will throw an exception and crash the program. So it was a bear to find out all the possibilites of where a null value could be in the database and make sure and handle it on the java side. Tracking down NULLPOINTEREXCEPIONS was a bit of a nightmare, but it forced me to really see how the code is pieced together.
- One other thing that I found important is how to build my query statements to get the data that I need. It's not earth shattering, but just a focus organizing my thoughts and building in small increments.  

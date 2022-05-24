# P2_ZooApp

REVATURE PROJECT 2 

CONTRIBUTORS:<BR>
DRUV PATEL (TEAM LEAD)<BR>
SANTIAGO GARCIA II <BR>
JORGE AYESTAS<BR>
ENOCK LEE<BR>
PATRICK MILLER<BR>

TECH STACK

PostGreSQL for persistence<BR>
API built with Java 8 and Spring 5<BR>
UI built with HTML, CSS, and JavaScript<BR>
  
FRAMEWORKS<BR>

Java API will leverage the Spring Framework<BR>
Java API will use Spring Data JPA to communicate with the DB<BR>
Java API will be RESTful (though HttpSession will be permitted)<BR>
Java API will be unit tested using JUnit and Mockito, with coverage reports generated using Jacoco<BR>
Complete CI/CD pipelines will use AWS (CodePipeline, CodeBuild, Elastic Beanstalk, and S3)<BR>

Online Zoo Web Application Summary <BR>
-	All will allow visitors to create account and view animals virtually. <BR>
-	Manager can login and can be able to do CRUD operations in the zoo. <BR>
-	Manager can view all visitor that have access to the zoo. <BR>

Manager User Story <BR>
-	A Manager can login<BR>
-	A Manager can logout<BR>
-	A Manager can view the Manager Homepage<BR>
-	A Manager can update animal information in zoo<BR>
-	A Manager can add animal in zoo<BR>
-	A Manager can delete animal in zoo<BR>
-	A Manager can view All animals in zoo<BR>
-	A Manager can view animal by name in zoo<BR>
-	A Manager will be able to view all visitors<BR>


Visitors User Story
-	A Visitor can login
-	A Visitor can logout
-	A Visitor can edit their profile
-	A Visitor can view all animals in zoo
-	A Visitor can view animal by name

Tables
-	Users
o	User Id
o	Username
o	Password
o	First Name
o	Last Name
o	Email
o	User role

-	User Role
o	User Role Id
o	User role

-	Animals
o	Animal Id
o	Name
o	Habitat Type Id
o	Lifespan
o	Diet

-	Habitat Type 
o	Habitat Type Id
o	Habitat Name



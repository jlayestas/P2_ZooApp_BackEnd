# P2_ZooApp_BackEnd


CONTRIBUTORS:<BR>
DRUV PATEL (TEAM LEAD)<BR>
SANTIAGO GARCIA II <BR>
JORGE AYESTAS<BR>

TECH STACK

PostGreSQL for persistence<BR>
API built with Java 8 and Spring 5<BR>
UI built with HTML, CSS, and JavaScript<BR>
  
FRAMEWORKS<BR>

Java API will leverage the Spring Framework<BR>
Java API will use Spring Data JPA to communicate with the DB<BR>
Java API will be RESTful<BR>
Java API will be unit tested using JUnit and Mockito, with coverage reports generated using Jacoco<BR>
Complete CI/CD pipelines will use AWS (CodePipeline, CodeBuild, Elastic Beanstalk, and S3)<BR>
<BR><BR>
Online Zoo Web Application Summary <BR>
-	All will allow visitors to create account and view animals virtually. <BR>
-	Manager can login and can be able to do CRUD operations in the zoo. <BR>
-	Manager can view all visitor that have access to the zoo. <BR>
<BR>
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

<BR>
Visitors User Story<BR>
-	A Visitor can login<BR>
-	A Visitor can logout<BR>
-	A Visitor can edit their profile<BR>
-	A Visitor can view all animals in zoo<BR>
-	A Visitor can view animal by name<BR>
<BR>
Tables<BR><BR>
Users<BR>
o	User Id<BR>
o	Username<BR>
o	Password<BR>
o	First Name<BR>
o	Last Name<BR>
o	Email<BR>
o	User role<BR>
<BR>
User Role<BR>
o	User Role Id<BR>
o	User role<BR>
<BR>
Animals<BR>
o	Animal Id<BR>
o	Name<BR>
o	Habitat Type Id<BR>
o	Lifespan<BR>
o	Diet<BR>
<BR>
Habitat Type<BR>
o	Habitat Type Id<BR>
o	Habitat Name<BR>



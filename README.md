## Open Bank Application :

It was developed on Java and Spring MVC with Apache Camel Spring DSL. 
 
### Features:

-	Used jetty embedded web servlet container to startup the web application in maven   
-	Apache camel to route web service call
-	Configured Logback for logging
-	Tested code on QAPlugin.


#### Used Technologies

* Java 8
* Lombok
* Spring
* Jackson API for JSON/XML
* Log4j
* Junit
* Jetty
* Apache Camel


**Deployment Steps:**

Step 1: checkout the project using below command 
```
git clone https://github.com/felipebizz/open-bank.git
```
    
    cd open-bank
   
Step 2: run on embedded jetty 
 
 	mvn clean install jetty:run

Step 3: open browser and hit the below url :
	
	http://localhost:8080/open-bank/rest/transactions

Step 4: use username:admin and password:admin
	
### Exposed three endpoints:

```
/open-bank/rest/transactions/
```

```
/open-bank/rest/transactions/{typeTransaction}
```

```
/open-bank/rest/transaction-total-amount/{typeTransaction}
```



### Environment Test:

- Ubuntu/ Linux
- Server –  Tomcat/jetty
- Java 8
- IntelliJ  	


	
### Code to Improve
- create a personalized treating exception
- Documentation of classes/code/packaging using swagger
- validation parameters endpoints and returns
- create new tests scenarios
- improve log visualization
- improve security authentication

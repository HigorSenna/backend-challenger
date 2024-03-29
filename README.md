The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker
* AWS
* Security
* Swagger
* Clean Code

## Stack
- Java 11

>WARNING: To start anyone enviroments, the database backend_challenger must be created

## How to start DEVELOPMENT enviroment
 Export the enviroment variable: 
 *-Dspring.profiles.active=development*
 
## If you don't have a local mysql server, execute:
```
mkdir /home/${USER}/database_mysql
docker run --net host --name mysql -v /home/${USER}/database_mysql:/var/lib/mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=backend_challenger -d mysql:8.0
```

>WARNING: if you execute the command in the top, in the next times, you should execute the command bellow:
```
docker start mysql
```
 
## How to start PRODUCTION enviroment
 Run the command in server:
 *java -jar -Dspring.profiles.active=production {package}.jar*

## API DOCUMENTATION:

Afer you start the project, access [Documentation Link](http://localhost:8082/api/swagger-ui.html) 

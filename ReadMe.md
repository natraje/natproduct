## **Product Management Project**

### Scope and Assumptions:
 - Managing customer details is out of scope
 - Only one offer can exist at any given point of time and offer is in %
 - Validation of the entries is out of scope 
 - Product id and product name validation is skipped for the backend checking
 - Basket Id or temp order number is generation is out of scope
 - Partial removal of product is out of scope

#### This project is implemented with limited scope assuming that not considering the below ones
 - Performance 
 - Paging and Sorting of products and items
 - Scalability and HA
 - Caching
 - Logging and Metrix
 - Security
 - Due to the limied scope, project id is marked as auto generated and this can be designed to scale if needed
 
####  High Level Architecture

Controller <-> Service <-> DAO <-> DB

####  Tech Stack: 

Since there is no java tech stack and limitations provided, this project is implemented using the below technologies
  - Java 1.8 and above
  - SpringBoot
  - JPA and Hibernate
  - H2 (In memory)
  - Junit and Mocito
  - Rest API
  - JSON
  - Swagger for Open API  (yaml file)
  - Postman

Commands to Start the application: mvn clean spring-boot:run  or java ProductApplication (from the home path)
-Database UI: http://localhost:8080/h2-ui/
http://localhost:8080/swagger-ui/index.html

#### Test Sample:
Product:
Post Method:  http://localhost:8080/api/v0/products
`{
    "productName": "BUYONEGETONE2",
    "description": "TEEST",
    "price": 50,
    "discount": "BUYONEGETONE"
}`

`{
    "productName": "TWENTYFIVE",
    "description": "TWENTYFIVE",
    "price": 100.0,
    "discount": "TWENTYFIVE"
}`

Get products result:
`[
    {
        "id": 1,
        "productName": "BUYONEGETONE2",
        "description": "TEEST",
        "price": 50.0,
        "discount": "BUYONEGETONE"
    },
    {
        "id": 2,
        "productName": "TWENTYFIVE",
        "description": "TWENTYFIVE",
        "price": 100.0,
        "discount": "TWENTYFIVE"
    }
]`

Basket:
Post: http://localhost:8080/api/basket/1
`[
    {
    "productId": 1,
    "quantity":10,
    "discount": "BUYONEGETONE"
    },
    {
    "productId": 2,
    "quantity":20,
    "discount": "TWENTYFIVE"
    }
]`

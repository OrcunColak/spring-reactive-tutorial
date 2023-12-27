# Declarative HTTP Client
Go to  
http://localhost:8080/v1/employees
1. The declarative HTTP Client gets data from an internal REST endpoint  
2. Test of declarative HTTP Client uses Mock Server to mock the internal REST endpoint

# Internal REST Endpoing
findAll : http://localhost:8080/internal/employees  
findById : GET request to http://localhost:8080/internal/employees/1  
deleteById : DELETE request to http://localhost:8080/internal/employees/1


# Reactive Redis
1. Crud for RedisUser
2. Publish RedisUser

# API Documentation
For JSON go to  
http://localhost:8080/v3/api-docs

For Swagger go to  
http://localhost:8080/swagger-ui.html   
or  
http://localhost:8080/webjars/swagger-ui/index.html

# Unit testing in Spring WebFlux  
See EmployeeControllerTest how to use WebTestClient


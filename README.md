# 1. JPA
## Declarative HTTP Client
Go to  
http://localhost:8080/v1/employees
1. The declarative HTTP Client gets data from an internal REST endpoint  
2. Test of declarative HTTP Client uses Mock Server to mock the internal REST endpoint

## Internal REST Endpoint
Internal REST Endpoint uses Postgres to store data
findAll : http://localhost:8080/internal/employees  
findById : GET request to http://localhost:8080/internal/employees/1  
deleteById : DELETE request to http://localhost:8080/internal/employees/1

# 2. File Server
See https://medium.com/deno-the-complete-reference/spring-boot-3-2-how-fast-are-virtual-thread-for-static-file-server-case-916a23709cb0  

To download a file for example go to
http://localhost:8080/static/file1.txt

# 3. Reactive Redis
1. Crud for RedisUser
2. Publish RedisUser
   http://localhost:8080/api/v1/redisusers/1

# 4. API Documentation
For JSON go to  
http://localhost:8080/v3/api-docs

For Swagger go to  
http://localhost:8080/swagger-ui.html   
or  
http://localhost:8080/webjars/swagger-ui/index.html

# Unit testing in Spring WebFlux  
See EmployeeControllerTest how to use WebTestClient



# Cz-Admin-Front

This project provides cz-admin's backend services<br />
The front page is in here [Cz-Admin-Front](https://github.com/jomalonejia/Cz-Admin-Front)


## Technology

* framework: Spring + SpringMVC 
* security: SpringSecurity + JWT
* quartz: Spring Quartz
* persistence :mybatis + mybatis-plus
* session: Redis Redission
* search: ElasticSearch

## Characteristic









*  RestfulApi with spring security and jwt
*  No session and cookie application
*  Separate the db read and write operation whe AOP
*  service's Communication with dubbo 
*  make the queue with quartz and ActiveMq(uploading)
*  Add Transaction to project the db's consistency
*  Add Swagger in controller
*  Encode the db properties 
*  Upload the file with QiNiu
*  Accelerate the base CRUD with mybatis-plus
*  make the search function with ElasticSearch(uploading)
```
 examples
```

## Deployment
> pre
-   load the sql file in you mysql
-   modify the properties
-   open your zookeeper redis activeMq elasticSearch
> open
-   run com.cz.ServiceMain.java to open the services
-   run the cz-web --> clean package tomcat7:run

### default account  
    admin@gmail.com:123456/<br />
    user1@qq.com:123456/<br />
    user2@qq.com:123456/<br />
## Running the tests

...


## Built With

* [Spring](http://www.spring.io/) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* ...


## Authors

* **Jomalone Jia** - *Initial work* - [jomalonejia](https://github.com/jomalonejia)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
    


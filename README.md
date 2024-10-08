# Backend Technology:    
SpringBoot: Simplify the initial construction and development process of Spring applications  
SpringCloud: A cloud-native application development tool based on Spring Boot. The technologies used by SpringCloud include (Spring Cloud Gateway, Spring Cloud Task, and Spring Cloud Feign, etc.)  
SpringBoot+SpringCloudAlibaba(Nacos, Sentinel) + OpenFeign + Gateway  
MyBatis-Plus: Persistence layer framework, also relying on mybatis  
Redis: Memory cache  
Redisson: Java in-memory data grid based on redis - framework; framework for operating redis  
MongoDB: Database for distributed file storage  
RabbitMQ: Message middleware; standard for large distributed projects; eventual consistency of distributed transactions  
Seata: Distributed transactions  
Drools: Rule engine, calculates estimated costs, cancellation costs, etc.  
GEO: GPS partition positioning calculation  
ThreadPoolExecutor+CompletableFuture: Asynchronous orchestration, thread pool to implement asynchronous operations and improve efficiency  
XXL-JOB: Distributed scheduled task call center  
Knife4J/YAPI: API interface documentation tool  
MinIO (privatized object storage cluster): distributed file storage similar to OSS (public)  
WeChat Pay: WeChat payment and WeChat account split  
MySQL: relational database {shardingSphere-jdbc for read-write separation; library and table split}  
Lombok: jar package generated by get/set in entity class  
Natapp: intranet penetration  
Spring Admin: Monitor applications and view logs   
Docker: containerization technology; production environment Redis (operation and maintenance personnel); quick environment Docker run  
Git: code management tool; Git use, pull code, commit, push, merge, conflict resolution  
Swagger: Create API Document and Testing API.

# Because we are developing a WeChat mini program project for taxi-hailing, we need to use a lot of cloud services (Tencent Cloud or other clouds) and WeChat mini program plug-ins, which are listed below:
1. Object storage service (COS) Stores private pictures such as driver's ID card and driver's license photos for real-name authentication
2. Face recognition (AiFace) Identity verification before the driver accepts the order every day, and has static liveness detection function
3. Personnel library management (face-lib) Cloud storage of driver registration face model, used for identity comparison
4. Data Vientiane (ci) Used to monitor the content of everyone's recording text to determine whether it contains violence and pornography
5. OCR document recognition Used for OCR recognition and scanning of ID card and driver's license information
6. WeChat simultaneous interpretation plug-in Synthesize text into speech and broadcast orders; convert recordings into text for security monitoring
7. Route planning plug-in Used to plan the driver's route home after work, or the route displayed in the mini program order
8. Map point selection plug-in Used for map point selection operations on the mini program
9. Tencent location service Route planning, positioning navigation, mileage and time estimation

# Project module

Final server-side architecture module

daijia-parent: root directory, management submodule:

​ common: public class parent module

​ common-log: system log management

​ common-util: core tool class

​ rabbit-util: service module tool class

​ service-util: service module tool class

​ spring-security: spring-security business module

​ model: entity class module

​ server-gateway: gateway

​ service: service service parent module

​ service-coupon: coupon service module

​ service-customer: passenger service module

​ service-dispatch: dispatch service module

​ service-driver: driver service module

​ service-map: map service module

​ service-mq: mq test service module

​ service-order: order service module

​ service-payment: payment service module

​ service-rules: rule service module

​ service-system: system service module

​ service-client: remote call service parent module

​service-coupon-client: coupon service remote interface

​ service-customer-client: customer service remote interface

​ service-dispatch-client: dispatch service remote interface

​ service-driver-client: driver service remote interface

​ service-map-client: map service remote interface

​ service-order-client: order service remote interface

​ service-payment-client: payment service remote interface

​ service-rules-client: rule service remote interface

​ service-system-client: system service remote interface

​ web: front-end system parent module

​ web-customer: passenger-side web system

​ web-driver: driver-side web system

​ web-mgr: management-side web system

# e.g.
![image](https://github.com/user-attachments/assets/fc482d0b-aad2-4d12-902c-61c5e438f388)
![image](https://github.com/user-attachments/assets/128dff35-f55d-4a3c-931b-48a1516b0d23)
![image](https://github.com/user-attachments/assets/8dddfe03-677b-481a-8aa3-86dc9333f40a)



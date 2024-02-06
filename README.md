# React-Springboot-ECommerce
Verilerin ilişkisel yapısını saklamak için PostgreSQL veritabanı tercih edilmiş, resimlerin depolanması içinse MongoDB kullanılmıştır. Map struct kullanılarak DTO dönüşümleri daha düzenli bir şekilde gerçekleştirilmiştir. Veritabanı tablolarında ilişkiler oluşturulmuş ve kurumsal mimari standartlarına uygun olarak yazılmıştır. Sipariş oluşturma işlemi Event Driven Architecture prensipleri doğrultusunda gerçekleştirilmiş; Kafka mesajlaşma sistemi kullanılarak sipariş oluşturma isteği gönderilmiş ve ilgili Kafka topiği dinlenerek sipariş işlemi gerçekleştirilmiştir. React uygulaması backend tarafında çalıştırılmıştır, bu da arama motorlarının indexlemesini kolaylaştırır. Frontend tarafında sadece işlevselliğe odaklanılarak CSS'e önem verilmemiştir. 


## Teknolojiler

- Spring Boot
- JPA Hibernate
- MongoDB
- PostgreSQL
- Kafka
- Thymeleaf
- Validation
- MapStruct
- Lombok
- Frontend Maven Plugin
- Spring Boot Maven Plugin
- Server Side React
  
## Kurulum

 Proje dizininde aşağıdaki komutları çalıştırarak projeyi başlatabilirsiniz:
```ruby
   git clone https://github.com/ugurilgin/Nextjs-Springboot-ECommerce
```

   Proje dizinini açın:
   ```ruby
    cd React-Springboot-ECommerce
  ```
 Bağımlılıkları yüklemek ve projeyi derlemek için;
 
  ```ruby
 mvn clean install
 ```

Projeyi çalıştırın

 ```ruby
java -jar target/CommerceApplication.jar
```

Proje linki http://localhost:8080.

- Postman dosyasını kullanarak apileri test edebilirsiniz
# React-Springboot-ECommerce

PostgreSQL database is preferred to store the relational structure of data, and MongoDB is used for storing images. Map struct is used for more organized DTO transformations. Relationships are established in the database tables and written according to corporate architectural standards. The order creation process is carried out in accordance with Event Driven Architecture principles; a Kafka messaging system is used to send the order creation request, and the order process is completed by listening to the relevant Kafka topic. The React application is run on the backend, which facilitates indexing by search engines. CSS is not emphasized on the frontend side, focusing only on functionality. 
## Tech Stack

- Spring Boot
- JPA Hibernate
- MongoDB
- PostgreSQL
- Kafka
- Thymeleaf
- Validation
- MapStruct
- Lombok
- Frontend Maven Plugin
- Spring Boot Maven Plugin
- Server Side React

## Installation

 Proje dizininde aşağıdaki komutları çalıştırarak projeyi başlatabilirsiniz:
To run the project, follow these steps:
```ruby
   git clone https://github.com/ugurilgin/Nextjs-Springboot-ECommerce
```

   Navigate to the project directory:
   ```ruby
    cd React-Springboot-ECommerce
  ```
 Install dependencies and build the project:
 
  ```ruby
 mvn clean install
 ```

Run the application

 ```ruby
java -jar target/CommerceApplication.jar
```
Access the application at http://localhost:8080.
 
- You can test apis using postman


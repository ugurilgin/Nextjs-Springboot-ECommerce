# React-Springboot-ECommerce
*Verilerin ilişkisel yapısını saklamak için PostgreSQL veritabanı tercih edilmiş, resimlerin depolanması içinse MongoDB kullanılmıştır. Map struct kullanılarak DTO dönüşümleri daha düzenli bir şekilde gerçekleştirilmiştir. Veritabanı tablolarında ilişkiler oluşturulmuş ve kurumsal mimari standartlarına uygun olarak yazılmıştır. Sipariş oluşturma işlemi Event Driven Architecture prensipleri doğrultusunda gerçekleştirilmiş; Kafka mesajlaşma sistemi kullanılarak sipariş oluşturma isteği gönderilmiş ve ilgili Kafka topiği dinlenerek sipariş işlemi gerçekleştirilmiştir. React uygulaması backend tarafında çalıştırılmıştır, bu da arama motorlarının indexlemesini kolaylaştırır. Frontend tarafında sadece işlevselliğe odaklanılarak CSS'e önem verilmemiştir. Jenkins kullanılarak sürekli entegrasyon ve dağıtım işlemleri otomatize edilmiştir


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
- 
## Kurulum

* Proje dizininde aşağıdaki komutları çalıştırarak projeyi başlatabilirsiniz:
- mvn clean install
- run CommerceApplication



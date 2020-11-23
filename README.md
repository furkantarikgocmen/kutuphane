# KutuphaneApplication

## Java Spring Boot Based Library Otomation System

Spring Boot - Thymeleaf - JPA based example infrastructure.

### How To Run :

```
$ git clone github.com/furkantarikgocmen/kutuphane.git
$ mvn clean install
```

### Dependencies

- **Java JDK 1.8**
- **Maven**
- **Postgres (Optional, because h2DB already exists)**

### How To Use:

```
1- http://localhost:8080/
2- Login

Default Users
1-admin-12345 : ADMIN (read,update,delete)
2-moderator-12345 : MODERATOR (read,update)
3-user-12345 : USER (read)
```
#### Before Production
* Change all TimeUnit.MILLISECONDS to TimeUnit.DAYS in WebMvcConfig.java
```java
@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("dist/**").addResourceLocations("classpath:/static/dist/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.DAYS));
        registry.addResourceHandler("plugins/**").addResourceLocations("classpath:/static/plugins/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.DAYS));
    }
```
* #### Comment line ITemplateResolver And Thymleaf Properties in KutuphaneApplication.java (Main)
```java
/*@Autowired
    private ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root:}")
    private String templatesRoot;*/

   /* @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        return resolver;
    }*/
```
* #### Comment line all h2 database props and uncomment Postgres props in application.properties
```yaml
# ==============================================================
# = Postgres Database Props.
# ==============================================================
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url=jdbc:postgresql://localhost:5432/kutuphanedb?useSSL=false
spring.datasource.username=pdbuser
spring.datasource.password=123
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=create-drop #this line is (already create-drop) for development. dont forget make a update!
# ==============================================================
# = H2 Database Props.
# ==============================================================
#spring.datasource.url=jdbc:h2:mem:MemDB;DATABASE_TO_UPPER=false;
#spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.initialization-mode=always
#spring.jpa.hibernate.ddl-auto=none
#spring.datasource.schema=classpath:h2db/schema.sql
#spring.datasource.data=classpath:h2db/data.sql
#spring.datasource.username=sa
#spring.datasource.password=
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
#spring.jpa.properties.hibernate.format_sql=true
#spring.h2.console.enabled=true
#spring.h2.console.path=/h2-console
#spring.h2.console.settings.trace=false
#spring.h2.console.settings.web-allow-others=false
```

---
## Nginx & Systemd Configs
* #### Copy kutuphane.conf to nginx
- *important: kutuphane-0.0.1-SNAPSHOT.jar file must be in home directory*
```bash
sudo cp kutuphane.conf /etc/nginx/conf.d/
nginx -s reload
```
* #### Copy kutuphane.service to systemd
```bash
sudo cp kutuphane.service /etc/systemd/system/
sudo systemctl enable kutuphane.service
sudo systemctl start kutuphane.service
```



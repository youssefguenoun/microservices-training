FROM openjdk:alpine
# Spring Boot application creates working directories for Tomcat by default
VOLUME /tmp
ADD store-service.jar store-service.jar
# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/store-service.jar", "--product-service.name=${PRODUCT_URL}", "--sales-service.name=${SALES_URL}","--stock-service.name=${STOCK_URL}","--server.port=${PORT}"]
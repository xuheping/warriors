eureka:
  client:
    serviceUrl:
      defaultZone: http://127.0.0.1:8761/eureka/
spring:
  application:
    name: WARRIORS-WEB-BOSTON
  cloud:
    config:
      #uri: http://127.0.0.1:8091
      fail-fast: true
      profile: config
      discovery:
        service-id: WARRIORS-CONFIG
        enabled: true

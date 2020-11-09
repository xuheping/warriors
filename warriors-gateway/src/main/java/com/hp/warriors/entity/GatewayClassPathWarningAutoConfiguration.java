package com.hp.warriors.entity;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
//执行顺序注解
//当前注解标识需要在GatewayAutoConfiguration前加载此配置
@AutoConfigureBefore(GatewayAutoConfiguration.class)
public class GatewayClassPathWarningAutoConfiguration {
    @Configuration
    //条件判断注解
    //classpath中存在org.springframework.web.servlet.DispatcherServlet时起效，标识项目导入了spring-boot-starter-web包
    @ConditionalOnClass(name = "org.springframework.web.servlet.DispatcherServlet")
    protected static class SpringMvcFoundOnClasspathConfiguration {

        public SpringMvcFoundOnClasspathConfiguration() {
            //当前项目导入了spring-boot-starter-web依赖时，打印警告日志

        }

    }
    @Configuration
    //条件判断注解
    //classpath中不存在org.springframework.web.reactive.DispatcherHandler时起效，标识项目未导入了spring-boot-starter-webflux包
    @ConditionalOnMissingClass("org.springframework.web.reactive.DispatcherHandler")
    protected static class WebfluxMissingFromClasspathConfiguration {
        public WebfluxMissingFromClasspathConfiguration() {

        }

    }
}

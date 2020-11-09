package com.hp.warriors.entity;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;

@Configuration
//条件判断注解
//classpath中存在LoadBalancerClient和RibbonAutoConfiguration和DispatcherHandler时此配置起效
@ConditionalOnClass({LoadBalancerClient.class, RibbonAutoConfiguration.class, DispatcherHandler.class})
//执行顺序注解
@AutoConfigureAfter(RibbonAutoConfiguration.class)
public class GatewayLoadBalancerClientAutoConfiguration {
    // GlobalFilter beans
    @Bean
    //条件判断注解
    //DI容器中存在LoadBalancerClient类型Bean时起效
    @ConditionalOnBean(LoadBalancerClient.class)
    public LoadBalancerClientFilter loadBalancerClientFilter(LoadBalancerClient client) {
        return new LoadBalancerClientFilter(client);
    }
}

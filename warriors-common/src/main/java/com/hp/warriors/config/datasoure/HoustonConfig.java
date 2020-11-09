package com.hp.warriors.config.datasoure;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.hp.warriors.mapper.houston", sqlSessionFactoryRef = "houstonSqlSessionFactory")
public class HoustonConfig {

    @Bean(name = "houstonDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.houston")
    public DataSource houstonDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("houstonSqlSessionTemplate")
    public SqlSessionTemplate houstonSqlSessionTemplate(@Qualifier("houstonSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("houstonSqlSessionFactory")
    public SqlSessionFactory houstonSqlSessionFactory(@Qualifier("houstonDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.hp.warriors.entity.houston");
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mapper/mybatis-config.xml"));

        //设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperConfigXmls = resolver.getResources("classpath*:mapper/houston/*.xml");
        factoryBean.setMapperLocations(mapperConfigXmls);
        return factoryBean.getObject();
    }
}

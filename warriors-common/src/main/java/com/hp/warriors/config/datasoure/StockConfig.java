package com.hp.warriors.config.datasoure;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.hp.warriors.mapper.stock", sqlSessionFactoryRef = "stockSqlSessionFactory")
public class StockConfig {

    @Autowired
    private SeattleProperty stockProperty;

    @Bean(name = "stockDataSource")
    public DataSource stockDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(stockProperty.getJdbcUrl());
        dataSource.setUsername(stockProperty.getUsername());
        dataSource.setPassword(stockProperty.getPassword());
        dataSource.setDriverClassName(stockProperty.getDriverClassName());
        return dataSource;
    }

    @Bean("stockSqlSessionTemplate")
    public SqlSessionTemplate stockSqlSessionTemplate(@Qualifier("stockSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("stockSqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("stockDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.hp.warriors.entity.stock");
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mapper/mybatis-config.xml"));

        //设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperConfigXmls = resolver.getResources("classpath*:mapper/stock/*.xml");
        factoryBean.setMapperLocations(mapperConfigXmls);
        return factoryBean.getObject();
    }
}

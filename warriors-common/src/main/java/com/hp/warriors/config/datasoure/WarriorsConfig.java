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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.hp.warriors.mapper.warriors", sqlSessionFactoryRef = "warriorsSqlSessionFactory")
public class WarriorsConfig {

    @Autowired
    private WarriorsProperty warriorsProperty;

    @Primary
    @Bean(name = "warriorsDataSource")
    public DataSource warriorsDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(warriorsProperty.getJdbcUrl());
        dataSource.setUsername(warriorsProperty.getUsername());
        dataSource.setPassword(warriorsProperty.getPassword());
        dataSource.setDriverClassName(warriorsProperty.getDriverClassName());
        return dataSource;
    }

    @Primary
    @Bean("warriorsSqlSessionTemplate")
    public SqlSessionTemplate warriorsSqlSessionTemplate(@Qualifier("warriorsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean("warriorsSqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("warriorsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.hp.warriors.entity");

        //设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperConfigXmls = resolver.getResources("classpath*:mapper/warriors/*.xml");
        factoryBean.setMapperLocations(mapperConfigXmls);
        return factoryBean.getObject();
    }
}

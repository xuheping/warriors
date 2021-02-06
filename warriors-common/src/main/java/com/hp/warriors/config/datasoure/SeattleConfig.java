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
@MapperScan(basePackages = "com.hp.warriors.mapper.seattle", sqlSessionFactoryRef = "seattleSqlSessionFactory")
public class SeattleConfig {

    @Autowired
    private SeattleProperty seattleProperty;

    @Primary
    @Bean(name = "seattleDataSource")
    public DataSource seattleDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(seattleProperty.getJdbcUrl());
        dataSource.setUsername(seattleProperty.getUsername());
        dataSource.setPassword(seattleProperty.getPassword());
        dataSource.setDriverClassName(seattleProperty.getDriverClassName());
        return dataSource;
    }

    @Primary
    @Bean("seattleSqlSessionTemplate")
    public SqlSessionTemplate seattleSqlSessionTemplate(@Qualifier("seattleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean("seattleSqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("seattleDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.hp.warriors.entity.seattle");
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mapper/mybatis-config.xml"));

        //设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperConfigXmls = resolver.getResources("classpath*:mapper/seattle/*.xml");
        factoryBean.setMapperLocations(mapperConfigXmls);
        return factoryBean.getObject();
    }
}

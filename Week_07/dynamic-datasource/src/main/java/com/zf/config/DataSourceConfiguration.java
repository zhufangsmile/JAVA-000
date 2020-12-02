package com.zf.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zhufang
 * @date 2020/11/30 1:39 下午
 */
@Configuration
public class DataSourceConfiguration {


    @Bean(name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DruidDataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DruidDataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DruidDataSource masterDataSource, @Qualifier("slaveDataSource") DruidDataSource slaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource(masterDataSource, slaveDataSource);
        return dynamicDataSource;
    }

}

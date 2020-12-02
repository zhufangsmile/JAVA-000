package com.zf.config;

import com.zf.annotaion.DataSourceSelector;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
/**
 * @author zhufang
 * @date 2020/11/30 10:41 上午
 */
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSource extends AbstractRoutingDataSource {

    private DataSource masterDataSource;
    private DataSource slaveDataSource;

    public DynamicDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        this.masterDataSource = masterDataSource;
        this.slaveDataSource = slaveDataSource;
    }
    @Override
    public void afterPropertiesSet() {
        super.setTargetDataSources(initTargetDataSources());
        super.setDefaultTargetDataSource(masterDataSource);
        super.afterPropertiesSet();
    }


    private Map<Object, Object> initTargetDataSources() {
        Map targetDataSources = new HashMap();
        targetDataSources.put(DataSourceSelector.MASTER, masterDataSource);
        targetDataSources.put(DataSourceSelector.SLAVE, slaveDataSource);
        return targetDataSources;
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }



}

package com.zf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author zhufang
 * @date 2020/11/30 10:32 上午
 */
@ConfigurationProperties(prefix = "datasource")
@Data
public class DynamicDataSourceProperties {
    private List<Map<String, String>> dblist;
}

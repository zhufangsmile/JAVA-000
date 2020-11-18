package com.zf.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhufang
 * @date 2020/11/16 10:18 上午
 */
@Data
@ConfigurationProperties(prefix = "student")
public class SchoolProperties {

    private int id;
    private String name;
}

package com.zf.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhufang
 * @date 2020/11/16 9:58 上午
 */
@Data
@Configuration
@EnableConfigurationProperties({SchoolProperties.class})
public class SchoolAutoConfiguration {

    @Autowired
    private SchoolProperties schoolProperties;

    @Bean
    public Student student() {
        Student student = new Student();
        student.setId(schoolProperties.getId());
        student.setName(schoolProperties.getName());
        return student;
    }

//. （必做）研究一下JDBC接口和数据库连接池，掌握它们的设计和用法： 1）使用JDBC原生接口，实现数据库的增删改查操作。 2）使用事务，PrepareStatement方式，批处理方式，改进上述操作。 3）配置Hikari连接池，改进上述操作。提交代码到Github。

//    @Bean
//    public Klass klass(List<Student> students) {
//        Klass klass = new Klass();
//        klass.setStudents(students);
//        return klass;
//    }


}

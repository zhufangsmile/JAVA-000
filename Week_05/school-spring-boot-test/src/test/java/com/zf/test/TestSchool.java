package com.zf.test;

import com.zf.spring.boot.autoconfigure.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhufang
 * @date 2020/11/16 4:13 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSchool {


    @Autowired
    private Student student;

    @Test
    public void test() {
        System.out.println(student.getId());
    }

}

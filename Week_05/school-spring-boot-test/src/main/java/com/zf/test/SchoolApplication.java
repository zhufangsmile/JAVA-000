package com.zf.test;


import com.zf.spring.boot.autoconfigure.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	@Autowired
	private Student student;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(student.getId());
	}

	public static void main(String args[]) {
		new SpringApplication(SchoolApplication.class).run(args);
	}

}
package com.myboard.learning2;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.myboard.learning2.entities.employee.EmployeeTest;
import com.myboard.learning2.repositories.EmployeeRepository;
import com.myboard.learning2.services.EmployeeServiceTest1;
import com.myboard.learning2.services.EmployeeServiceTest2;
import com.myboard.learning2.services.EmployeeServiceTest3;
import com.myboard.learning2.services.MallService1;
import com.myboard.learning2.services.MallService2;

import jakarta.persistence.Persistence;


@SpringBootApplication
public class Learning2Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Learning2Application.class, args);
		System.out.println("스프링부트 시작... ddl-auto=create 설정됨");
		

        // EmployeeServiceTest3 svc1 = ctx.getBean("employeeServiceTest3", EmployeeServiceTest3.class);
        // svc1.test1();
        // svc1.test5();

        MallService2 svc1 = ctx.getBean("mallService2", MallService2.class);
        svc1.test1();
        svc1.test3();
	}

}
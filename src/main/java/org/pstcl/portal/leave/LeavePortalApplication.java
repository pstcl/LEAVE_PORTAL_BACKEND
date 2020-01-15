package org.pstcl.portal.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LeavePortalApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(LeavePortalApplication.class, args);
	}
}

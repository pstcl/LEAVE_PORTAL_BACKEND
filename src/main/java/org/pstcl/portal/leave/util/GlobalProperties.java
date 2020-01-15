package org.pstcl.portal.leave.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@PropertySource("file:external.properties")
public class GlobalProperties {
	@Autowired
	private Environment environment;

	@Getter
	@Setter
	@Value("${employee.service.uri.server}")
	private String server;

	@Getter
	@Setter
	@Value("${employee.service.uri.authenticate}")
	private String authenticationUrl;


	@Getter
	@Setter
	@Value("${employee.service.username}")
	private String apiUsername;

	@Getter
	@Setter
	@Value("${employee.service.password}")
	private String apiPassword;

	@Getter
	@Setter
	@Value("${employee.service.uri.employee.details}")
	private String employeeDetailsUrl;
	
	@Getter
	@Setter
	@Value("${employee.service.uri.ddo.employees}")
	private String employeesByDDOUrl;

	
	@Getter
	@Setter
	@Value("${employee.service.authorization.header.name}")
	private String authorizationHeaderName;
	

}



package org.pstcl.portal.leave.mvc.service;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.repository.EmployeeRepository;
import org.pstcl.portal.leave.util.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service(value = "HTTPService")
public class HTTPServiceLocalTesting implements HTTPService{

	//	1. Employee:                          https://hrapipstcl.pspcl.in/api/employee/504002
	//	2. DDO:                                  https://hrapipstcl.pspcl.in/api/ddo/202
	//	3. EmployeeAuthenticate:      https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate


	@Autowired
	private GlobalProperties globalProperties;

	private HttpEntity<String> setHeader() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String apiCredential = "Basic " + globalProperties.getApiUsername() + ":" + globalProperties.getApiPassword() + ":" + auth.getPrincipal() + ":" +  auth.getCredentials();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(globalProperties.getAuthorizationHeaderName(), apiCredential);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		return entity;
	}


	//	public ResponseEntity<String> authorizeEmployee()
	//	{
	//
	//		String url = globalProperties.getServer()+globalProperties.getAuthenticationUrl(); 
	//
	//		HttpEntity<String> entity = setHeader();
	//		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,0);
	//	
	//		return response;
	//	}


	private RestTemplate restTemplate;

	public HTTPServiceLocalTesting(	RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate = restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500))
				.build();
	}



	org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(HTTPServiceLocalTesting.class);



	//	public ResponseEntity<String>  employeeDetailsString(String employeCode)
	//	{
	//		String url = globalProperties.getServer()+globalProperties.getEmployeeDetailsUrl(); 
	//
	//		//	String url = "https://hrapipstcl.pspcl.in/api/employee/{empid}";
	//		HttpEntity<String> entity = setHeader();
	//		Map<String, String> params = new HashMap<String, String>();
	//		params.put("empid", employeCode);
	//		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,params);
	//		
	//		//ResponseEntity<Employee> employee= restTemplate.fo(url,HttpMethod.GET,entity,String.class,params);
	//		
	//		
	//		return response;
	//	}

	public Employee getLoggedInEmployee()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return employeeDetails(auth.getName());

	}


	@Autowired
	EmployeeRepository employeeRepository;

	public Employee  employeeDetails(String employeCode)
	{
		Employee  employee=null;
		Optional<Employee> optional= employeeRepository.findById(employeCode);

		if(optional.isPresent())
		{
			employee=optional.get();
		}
		else
		{
			employee = getEmployeeFromServerAndSaveToLocal(employeCode);
		}
		return employee;
	}


	private Employee getEmployeeFromServerAndSaveToLocal(String employeCode) {
		Employee employee;
		String url = globalProperties.getServer()+globalProperties.getEmployeeDetailsUrl(); 
		HttpEntity<String> entity = setHeader();
		Map<String, String> params = new HashMap<String, String>();
		params.put("empid", employeCode);
		ResponseEntity<Employee[]> response= restTemplate.exchange(url,HttpMethod.GET,entity,Employee[].class,params);
		employee= response.getBody()[0];

		if(employee!=null)
		{
			employeeRepository.save(employee);
		}
		return employee;
	}


	public 	ResponseEntity<String> ddoDetails(String ddocode)
	{
		String url = globalProperties.getServer()+globalProperties.getEmployeesByDDOUrl(); 
		HttpEntity<String> entity = setHeader();
		Map<String, String> params = new HashMap<String, String>();
		params.put("ddocode", ddocode);
		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,params);
		return response;
	}


}

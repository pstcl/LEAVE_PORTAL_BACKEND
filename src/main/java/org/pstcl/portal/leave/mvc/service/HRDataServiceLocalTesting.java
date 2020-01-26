package org.pstcl.portal.leave.mvc.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.mvc.model.User;
import org.pstcl.portal.leave.repository.EmployeeRepository;
import org.pstcl.portal.leave.util.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service(value = "HTTPService")
public class HRDataServiceLocalTesting implements HRDataService{

	//	1. Employee:                          https://hrapipstcl.pspcl.in/api/employee/504002
	//	2. DDO:                                  https://hrapipstcl.pspcl.in/api/ddo/202
	//	3. EmployeeAuthenticate:      https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate


	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Scheduled(fixedDelay = 10*1000)
	void getLoggedInUSer()
	{

		List<Object> principals = sessionRegistry.getAllPrincipals();

		List<String> usersNamesList = new ArrayList();
		
		
		for (Object principal: principals) {
			 System.out.println( principal);
		    if (principal instanceof User) {
		        usersNamesList.add(((User) principal).getUsername());
		       

		    }
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null)
		{
			String apiCredential =  auth.getPrincipal() + ":" +  auth.getCredentials();
			System.out.println(apiCredential);

		}

	}
	
	
	@Autowired
	private GlobalProperties globalProperties;

	private HttpEntity<String> setHeader() {
		User user = getLoggedInUser();

		String apiCredential = "Basic " + globalProperties.getApiUsername() + ":" + globalProperties.getApiPassword() + ":" + user.getUsername() + ":" +  user.getPassword();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(globalProperties.getAuthorizationHeaderName(), apiCredential);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		return entity;
	}


	private User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user=(User)auth.getPrincipal();
		return user;
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

	public HRDataServiceLocalTesting(	RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate = restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500))
				.build();
	}



	org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(HRDataServiceLocalTesting.class);



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
		
		return employeeDetails(getLoggedInUser().getUsername());

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


	


	@Override
	public List<Employee> getDDOForEmployee(String employeCode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Employee> getControllingOfficerForEmployee(String employeCode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Employee> getApprovingAuthorityForEmployee(String employeCode) {
		// TODO Auto-generated method stub
		return null;
	}




}

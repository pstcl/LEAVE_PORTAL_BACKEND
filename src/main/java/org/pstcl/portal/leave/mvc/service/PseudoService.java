package org.pstcl.portal.leave.mvc.service;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PseudoService {



	private RestTemplate restTemplate;

	public PseudoService(	RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate = restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500))
				.build();
	}

	//	1. Employee:                          https://hrapipstcl.pspcl.in/api/employee/504002
	//	2. DDO:                                  https://hrapipstcl.pspcl.in/api/ddo/202
	//	3. EmployeeAuthenticate:      https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate


	org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(PseudoService.class);


	
	private HttpEntity<String> prepareHeaders(HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", request.getHeader("ServerAuthorization"));
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		return entity;
	}
	


	public ResponseEntity<String> authorizeEmployee(HttpServletRequest request)
	{


		String url = "https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate";
		HttpEntity<String> entity = prepareHeaders(request);
		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,0);
		return response;
	}

	public ResponseEntity<String>  employeeDetails(String employeCode, HttpServletRequest request)
	{
		String url = "https://hrapipstcl.pspcl.in/api/employee/{empid}";
		HttpEntity<String> entity = prepareHeaders(request);
		Map<String, String> params = new HashMap<String, String>();
		params.put("empid", employeCode);
		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,params);
		logger.error("LOGGER STARTED");
		return response;
	}

	public ResponseEntity<String>  ddoDetails(String ddoCode, HttpServletRequest request)
	{
		String url = "https://hrapipstcl.pspcl.in/api/ddo/{ddoCode}";
		HttpEntity<String> entity = prepareHeaders(request);
		Map<String, String> params = new HashMap<String, String>();
		params.put("ddocode", ddoCode);
		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,params);
		logger.error("LOGGER STARTED");
		return response;
	}


}

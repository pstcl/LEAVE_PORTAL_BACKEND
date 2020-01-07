package org.pstcl.portal.leave.httpclient;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HTTPService {



	private RestTemplate restTemplate;

	public HTTPService(	RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate = restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500))
				.build();
	}

	//	1. Employee:                          https://hrapipstcl.pspcl.in/api/employee/504002
	//	2. DDO:                                  https://hrapipstcl.pspcl.in/api/ddo/202
	//	3. EmployeeAuthenticate:      https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate


	org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(HTTPService.class);


	public ResponseEntity<String> authorizeEmployee()
	{
		String username = "api_pstcl";
		String password = "amritsarpatiala";
		String empid = "504003";
		String pswd = "504003";

		String url = "https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate";
		String apiCredential = "Basic " + username + ":" + password + ":" + empid + ":" + pswd;
		//    

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// set custom header
		headers.set("Authorization", apiCredential);

		// build the request
		HttpEntity<String> entity = new HttpEntity<>("body", headers);


		//		 ResponseEntity<Post> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, Post.class, 1);
		//	        if (response.getStatusCode() == HttpStatus.OK) {
		//	            return response.getBody();
		//	        } else {
		//	            return null;
		//	        }

		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,0);


		logger.error("LOGGER STARTED");
		System.out.println(response);




		return response;
	}
	public ResponseEntity<String>  employeeDetails(String employeCode)
	{
		String username = "api_pstcl";
		String password = "amritsarpatiala";
		String empid = "504003";
		String pswd = "504003";

		String url = "https://hrapipstcl.pspcl.in/api/employee/{empid}";
		String apiCredential = "Basic " + username + ":" + password + ":" + empid + ":" + pswd;
		//    

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// set custom header
		headers.set("Authorization", apiCredential);

		// build the request
		HttpEntity<String> entity = new HttpEntity<>("body", headers);


		Map<String, String> params = new HashMap<String, String>();
		params.put("empid", employeCode);

		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,params);


		logger.error("LOGGER STARTED");
		System.out.println(response);




		return response;
	}


	public 	ResponseEntity<String> ddoDetails(String ddocode)
	{
		String username = "api_pstcl";
		String password = "amritsarpatiala";
		String empid = "504003";
		String pswd = "504003";

		String url = "https://hrapipstcl.pspcl.in/api/ddo/{ddocode}";
		String apiCredential = "Basic " + username + ":" + password + ":" + empid + ":" + pswd;
		//    

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// set custom header
		headers.set("Authorization", apiCredential);

		// build the request
		HttpEntity<String> entity = new HttpEntity<>("body", headers);


		Map<String, String> params = new HashMap<String, String>();
		params.put("ddocode", ddocode);

		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,params);


		logger.error("LOGGER STARTED");
		System.out.println(response);




		return response;
	}


	//	string username = "api_pstcl";
	//    string password = "amritsarpatiala";
	//    string empid = Eid;
	//    string pswd = Epwd;
	//    string apiCredential = "Basic " + username + ":" + password + ":" + empid + ":" + pswd;
	//    const string WEBSERVICE_URL = "https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate/";
	//    try
	//    {
	//        var webRequest = System.Net.WebRequest.Create(WEBSERVICE_URL);
	//        
	//        if (webRequest != null)
	//        {
	//            webRequest.Method = "GET";
	//            webRequest.ContentType = "application/json";
	//            webRequest.Headers.Add("Authorization", apiCredential);
	//            using (System.IO.Stream s = webRequest.GetResponse().GetResponseStream())
	//            {
	//                using (System.IO.StreamReader sr = new System.IO.StreamReader(s))
	//                {
	//                    jsonResponse = sr.ReadToEnd();
	//                    Console.WriteLine(String.Format("Response: {0}", jsonResponse));
	//                }
	//            }
	//            
	//        }

}

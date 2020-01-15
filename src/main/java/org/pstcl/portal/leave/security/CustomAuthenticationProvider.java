package org.pstcl.portal.leave.security;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.PreDestroy;

import org.pstcl.portal.leave.util.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private GlobalProperties globalProperties;

	private RestTemplate restTemplate;

	public CustomAuthenticationProvider(	RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate = restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500))
				.build();
	}

	@Override
	public Authentication authenticate(Authentication authentication) 
			throws AuthenticationException {



		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (authorizeEmployee(username,password)) {

			// use the credentials
			// and authenticate against the third-party system
			return new UsernamePasswordAuthenticationToken(
					username, password, new ArrayList<>());
		} else {
			logger.error("USER Authentication failed");
			return null;
		}
	}


	org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(CustomAuthenticationProvider.class);


	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}





	public Boolean authorizeEmployee(String empid, String employeePassword)
	{
		Boolean authenticated=false;

		String url = globalProperties.getServer()+globalProperties.getAuthenticationUrl(); 
		logger.info("Accessing URL    "+ url);
		String apiCredential = "Basic " + globalProperties.getApiUsername() + ":" + globalProperties.getApiPassword() + ":" + empid + ":" + employeePassword;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(globalProperties.getAuthorizationHeaderName(), apiCredential);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,0);
		if(response.getBody().toUpperCase().equalsIgnoreCase("TRUE"))
		{
			authenticated=true;
			logger.info("USER authenticated");
		}
		return authenticated;
	}
	@PreDestroy
	public void preDestroy() {
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);

	}

}
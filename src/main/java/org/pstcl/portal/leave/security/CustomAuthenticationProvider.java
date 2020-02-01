package org.pstcl.portal.leave.security;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.annotation.PreDestroy;

import org.pstcl.portal.leave.mvc.model.User;
import org.pstcl.portal.leave.repository.UserMongoRepository;
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
					new User(	username, password), password, new ArrayList<>());
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


	@Autowired
	private UserMongoRepository userMongoRepository;

	public Boolean authorizeEmployee(String empid, String employeePassword)
	{
		Boolean authenticated=false;


		//logger.info("Basic Token    "+ apiCredential);


		//DELETE before DEPloying
		//DELETE before DEPloying
		//DELETE before DEPloying
		//DELETE before DEPloying
		//DELETE before DEPloying
		//DELETE before DEPloying
		//DELETE before DEPloying STARTS
		Optional<User> user= userMongoRepository.findById(empid);
		if(user.isPresent())
		{

			authenticated=(user.get().getPassword().compareTo(employeePassword)==0);
			logger.info("USER authenticated locally");


		}
		//DELETE before DEPloying ENDS
		//DELETE before DEPloying ENDS		//DELETE before DEPloying ENDS		//DELETE before DEPloying ENDS


		else {
			HttpHeaders headers = new HttpHeaders();
			String url = globalProperties.getServer()+globalProperties.getAuthenticationUrl(); 
			logger.info("Accessing URL    "+ url);
			
			String apiCredential = "Basic " + globalProperties.getApiUsername() + ":" + globalProperties.getApiPassword() + ":" + empid + ":" + employeePassword;
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set(globalProperties.getAuthorizationHeaderName(), apiCredential);
			HttpEntity<String> entity = new HttpEntity<>("body", headers);
			ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class,0);
			if(response.getBody().toUpperCase().equalsIgnoreCase("TRUE"))
			{
				authenticated=true;
				logger.info("USER authenticated");
				//DELETE before DEPloying
				//DELETE before DEPloying
				//DELETE before DEPloying STARTS
				userMongoRepository.save(new User(empid,employeePassword));	
				//DELETE before DEPloying ENDS
				//DELETE before DEPloying ENDS		
				//DELETE before DEPloying ENDS		
				//DELETE before DEPloying ENDS

			}
		}

		return authenticated;
	}
	@PreDestroy
	public void preDestroy() {
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);

	}

}
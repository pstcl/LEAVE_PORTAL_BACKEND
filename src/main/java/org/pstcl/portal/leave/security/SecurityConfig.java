
package org.pstcl.portal.leave.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	   @Autowired
	    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

	   private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false);
		auth.authenticationProvider(authProvider);
	}
	//	
	//	  @Override
	//	    protected void configure(HttpSecurity http) throws Exception {
	//	        http
	//	        .csrf().disable()   
	//	        .authorizeRequests()
	//	        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	//	                .anyRequest().authenticated()
	//	                .and()
	//	            //.formLogin().and()
	//	            .httpBasic();
	//	    }
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()   
		.authorizeRequests()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(restAuthenticationEntryPoint)
		.and().authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS,"**").permitAll()
		.antMatchers("/login/**").permitAll()
		.anyRequest().authenticated()
		.and()
        .formLogin()
        .successHandler(mySuccessHandler)
        .failureHandler(myFailureHandler)
		.and()
		//.formLogin().and()
		.httpBasic()
		.and()
		.logout() // This is missing and is important
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID");
	}


	//	@Override
	//	protected void configure(HttpSecurity http) throws Exception {
	//		http
	//		.csrf().disable()  
	//		.authorizeRequests().anyRequest().authenticated()
	//		.and().httpBasic()
	//		.and()
	//		.logout() // This is missing and is important
	//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	//		.logoutSuccessUrl("/login");
	//
	//	}


}

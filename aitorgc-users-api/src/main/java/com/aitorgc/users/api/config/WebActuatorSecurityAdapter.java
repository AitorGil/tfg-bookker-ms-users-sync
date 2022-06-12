package com.aitorgc.users.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebActuatorSecurityAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private WebEndpointProperties webEndpointProperties;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatcher(new OrRequestMatcher(new AntPathRequestMatcher(webEndpointProperties.getBasePath()),
				EndpointRequest.toAnyEndpoint())).httpBasic().and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.requestMatchers(EndpointRequest.to(HealthEndpoint.class)) // InfoEndpoint.class
				.permitAll().anyRequest().hasRole("ACTUATOR_USER");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = new BCryptPasswordEncoder(11);
		auth.inMemoryAuthentication().withUser("client").password(encoder.encode("client_54re_20"))
				.roles("ACTUATOR_USER");
	}
}

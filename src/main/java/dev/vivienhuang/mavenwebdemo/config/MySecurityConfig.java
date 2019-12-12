package dev.vivienhuang.mavenwebdemo.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication()
//        .dataSource(securityDataSource)
//        .usersByUsernameQuery("select Account,Password, State from Member where Account=?")
//        .authoritiesByUsernameQuery("select Account, Permission from MemberPermission where Account=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		http.addFilterBefore(characterEncodingFilter, CsrfFilter.class);
		
		/*
		 * http
  .httpBasic().and()
  .authorizeRequests()
    .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
    .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
    .antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN");
		 * */
		// 使用的ROLE DB裡面一定要存成ROLE_ROLENAME
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/guest", "/test", "/api", "/home", "/uploadImageFile").permitAll()
			.antMatchers("/createMember", "/keyword").hasRole("EMPLOYEE")
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
				.logout().permitAll()
			.and()
				.exceptionHandling()
				.accessDeniedPage("/access_denied");	
	}
}

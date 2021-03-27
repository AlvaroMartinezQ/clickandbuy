package es.urjc.etsii.co.clickandbuyweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import es.urjc.etsii.co.clickandbuyweb.authenticationProvider.UserRepositoryAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepositoryAuthenticationProvider authProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * Public pages
		 */
		
		// Home
		http.authorizeRequests().antMatchers("/").permitAll();
		
		// User end-points
		http.authorizeRequests().antMatchers("/user/singUp").permitAll();
		http.authorizeRequests().antMatchers("/user/singUpForm").permitAll();
		http.authorizeRequests().antMatchers("/user/singIn").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		
		// Custom Style Sheets and Images
		http.authorizeRequests().antMatchers("/css/**", "/imgs/**", "/favicon.ico").permitAll();
		
		// Private pages
		http.authorizeRequests().antMatchers("/admin/register").hasRole("MANAGER").and().exceptionHandling().accessDeniedPage("/admin/denied");
		http.authorizeRequests().antMatchers("/admin/userList").hasRole("MANAGER").and().exceptionHandling().accessDeniedPage("/admin/denied");
		http.authorizeRequests().antMatchers("/admin/productsView").hasAnyRole("MANAGER","STAFF").and().exceptionHandling().accessDeniedPage("/admin/denied");
		http.authorizeRequests().anyRequest().authenticated();
		
		//http.authorizeRequests().antMatchers("/product/upload").hasAnyRole("SUPPLIER_ROLE");
		
		/*
		 * DO NOT put any http.authorizeRequests() after anyRequest() it won't work
		 */
		
		/*
		 * Login form
		 */
		
		http.formLogin().loginPage("/user/singIn");
		http.formLogin().loginProcessingUrl("/user/singInForm");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/marketplace");
		http.formLogin().failureUrl("/user/singIn");
		
		/*
		 * Logout
		 */
		
		http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
		
		// Disable CSRF at the moment
		// http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
}

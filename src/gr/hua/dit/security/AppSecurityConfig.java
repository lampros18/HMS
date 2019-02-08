package gr.hua.dit.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, authority from authorities where username=?");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/**").hasIpAddress("185.31.40.17/16")
		.antMatchers("/").hasAnyRole("ADMIN","EMPLOYEE","FOREMAN","STUDENT")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/employee/**").hasRole("EMPLOYEE")
		.antMatchers("/foreman/**").hasRole("FOREMAN")
		.antMatchers("/student/**").hasRole("STUDENT")
			.anyRequest().authenticated()
		.and().csrf().ignoringAntMatchers("/api/**").and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authUser")
			.permitAll()
			.defaultSuccessUrl("/")
		.and()
		.logout().permitAll();
	}
	
	/**
	 * Στην κρυπτογράφηση κάπως θα πρέπει να μπαίνει ο χρήστης.
	 * Δεν μπορώ να κάνω intersept την μέθοδο αναγνώρισης , οποτε θα του 
	 * στέλνω πίσω το username του κρυπτογραφημένο
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/login/**");
	}


}
package gr.hua.dit.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
		http.authorizeRequests()
		.antMatchers("/").hasAnyRole("ADMIN","EMPLOYEE","FOREMAN","STUDENT")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/employee/**").hasRole("EMPLOYEE")
		.antMatchers("/foreman/**").hasRole("FOREMAN")
		.antMatchers("/student/**").hasRole("STUDENT")
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/commonLogin")
			.loginProcessingUrl("/authUser")
			.permitAll()
		.and()
		.logout().permitAll();

	}

	

//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		 Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//		 
//	        if (roles.contains("ROLE_ADMIN")) {
//	        	System.out.println("fdsfds");
//	            response.sendRedirect("/admin");
//	        } else {
//	            response.sendRedirect("/index");
//	        }
//	    }

}

/*
 * Major updates by Lampros
 * 
 * @Configuration
 * 
 * @Order(1) public static class AdminConfigurationAdapter extends
 * WebSecurityConfigurerAdapter { public AdminConfigurationAdapter() { super();
 * }
 * 
 * @Autowired private DataSource dataSource;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(
 * passwordEncoder())
 * .usersByUsernameQuery("select username,password, enabled from superuser where username=?"
 * )
 * .authoritiesByUsernameQuery("select username, authority from superuser_authorities where username=?"
 * );
 * 
 * }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { //
 * http.authorizeRequests().anyRequest().authenticated().and().formLogin().
 * loginPage("/login") //
 * .loginProcessingUrl("/authUser").permitAll().and().and().logout().permitAll()
 * ; // // http.formLogin().loginPage("/login").loginProcessingUrl("/authUser").
 * permitAll().and().authorizeRequests() //
 * .antMatchers("/about").permitAll().antMatchers("/admin/**").hasRole("ADMIN").
 * anyRequest() // .authenticated().and().logout().permitAll();
 * http.antMatcher("/admin/**") .authorizeRequests() .anyRequest()
 * .hasRole("ADMIN")
 * 
 * .and() .formLogin() .loginPage("/admin_login")
 * .loginProcessingUrl("/admin/login").permitAll()
 * .failureUrl("/loginAdmin?error=loginError") .defaultSuccessUrl("/admin/home")
 * 
 * .and() .logout().permitAll() .logoutUrl("/admin_logout")
 * .logoutSuccessUrl("/logout") .deleteCookies("JSESSIONID")
 * 
 * .and() .exceptionHandling() .accessDeniedPage("/403") //csrf() .and()
 * .csrf().disable();
 * 
 * }
 * 
 * @Override public void configure(WebSecurity web) throws Exception {
 * web.ignoring().antMatchers("/resources/**"); } }
 * 
 * @Bean public static BCryptPasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Configuration
 * 
 * @Order(2) public static class StudentConfigurationAdapter extends
 * WebSecurityConfigurerAdapter { public StudentConfigurationAdapter() {
 * super(); }
 * 
 * @Autowired private DataSource dataSource;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(
 * passwordEncoder())
 * .usersByUsernameQuery("select email,password, enabled from student where email=?"
 * )
 * .authoritiesByUsernameQuery("select email, authority from student_authorities where email=?"
 * );
 * 
 * }
 * 
 * protected void configure(HttpSecurity http) throws Exception {
 * http.antMatcher("/student/**") .authorizeRequests() .anyRequest()
 * .hasRole("STUDENT")
 * 
 * .and() .formLogin() .loginPage("/student_login")
 * .loginProcessingUrl("/student/login").permitAll()
 * .failureUrl("/loginUser?error=loginError") .defaultSuccessUrl("/students")
 * 
 * .and() .logout() .logoutUrl("/student_logout")
 * .logoutSuccessUrl("/protectedLinks") .deleteCookies("JSESSIONID")
 * 
 * .and() .exceptionHandling() .accessDeniedPage("/403")
 * 
 * .and() .csrf().disable(); }
 * 
 * }
 * 
 * @Configuration
 * 
 * @Order(3) public static class EmployeeConfigurationAdapter extends
 * WebSecurityConfigurerAdapter { public EmployeeConfigurationAdapter() {
 * super(); }
 * 
 * @Autowired private DataSource dataSource;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(
 * passwordEncoder())
 * .usersByUsernameQuery("select email,password, enabled from employee where email=?"
 * )
 * .authoritiesByUsernameQuery("select email, authority from employee_authorities where email=?"
 * );
 * 
 * }
 * 
 * protected void configure(HttpSecurity http) throws Exception {
 * http.antMatcher("/employee/**") .authorizeRequests() .anyRequest()
 * .hasRole("EMPLOYEE")
 * 
 * .and() .formLogin() .loginPage("/employee_login")
 * .loginProcessingUrl("/employee/login").permitAll()
 * .failureUrl("/loginUser?error=loginError")
 * .defaultSuccessUrl("/employee/home")
 * 
 * .and() .logout() .logoutUrl("/employee_logout") .logoutSuccessUrl("/logout")
 * .deleteCookies("JSESSIONID")
 * 
 * .and() .exceptionHandling() .accessDeniedPage("/403")
 * 
 * .and() .csrf().disable(); } }
 */

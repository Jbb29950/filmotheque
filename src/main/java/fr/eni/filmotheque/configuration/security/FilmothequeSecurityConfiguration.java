package fr.eni.filmotheque.configuration.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class FilmothequeSecurityConfiguration {
	@Bean
	UserDetailsManager userDetailsManager(DataSource datasSource) {
		
		JdbcUserDetailsManager jdbcDetailsManager = new JdbcUserDetailsManager(datasSource);
		jdbcDetailsManager.setUsersByUsernameQuery("select email,password, 1 from Membre where email=?");
		jdbcDetailsManager.setAuthoritiesByUsernameQuery("select membre.email, ROLE from ROLES join membre on membre.admin= roles.IS_ADMIN  where email=?");
		return jdbcDetailsManager;
	}
	//routes du controller
		//@GetMapping("/films")
		//@GetMapping("/film")
		//@GetMapping("/films/creer")
		//@PostMapping("/films/creer")
		
		
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
			
			httpSecurity.authorizeHttpRequests((auth)->
			auth.requestMatchers(HttpMethod.GET,"/films").permitAll()
			.requestMatchers(HttpMethod.GET,"/films/creer").hasRole("ADMIN")
			.requestMatchers(HttpMethod.POST,"/films/creer").hasRole("ADMIN")
			
			.requestMatchers(HttpMethod.GET,"/film").hasAnyRole("MEMBRE","ADMIN")
			
			.requestMatchers(HttpMethod.GET,"/css/*").permitAll()
			.anyRequest().authenticated());
			
			
			//parametrage de la page de login
			httpSecurity.formLogin(form->{
				form.loginPage("/login").permitAll();
				form.defaultSuccessUrl("/films");
		});
		httpSecurity.logout(logout->
			logout.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			);
			
			
		
		return httpSecurity.build();
		
		}
	
	
	
}

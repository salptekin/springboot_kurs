package springboot_kurs_controller_service_repository_basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			csrf().disable().
			authorizeRequests().
			antMatchers("/", "index", "/css/*", "/js/*").permitAll().
			//antMatchers("/**").hasRole(ApplicationUserRoles.ADMIN.name()).
			anyRequest().
			authenticated().
			and().
			httpBasic();//Basic authentication istiyorum
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails student = User.
								builder().
								username("techproed").
								password(passwordEncoder.encode("12345")).
								//roles("STUDENT").
								authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities()).
								build();
		
		UserDetails admin = User.
								builder().
								username("admin").
								password(passwordEncoder.encode("nimda")).
								//roles("ADMIN").
								authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities()).
								build();
		
		return new InMemoryUserDetailsManager(student, admin);
	}

}

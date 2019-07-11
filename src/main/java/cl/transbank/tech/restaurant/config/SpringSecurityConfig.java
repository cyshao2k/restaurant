package cl.transbank.tech.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import cl.transbank.tech.restaurant.util.RestaurantUtil;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.inMemoryAuthentication()
    			.withUser("user").password(passwordEncoder.encode("password")).roles(RestaurantUtil.USER_ROLE);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
        		.authorizeRequests()
        		.antMatchers("/**").authenticated()
        		.and()
        		.logout() // Method get then I have disabled CSRF
        		.permitAll();
    }
    
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	

    @Bean
    @Override
    public UserDetailsService userDetailsService () {
	    UserDetails user = User.builder().username("user")
	    		.password(passwordEncoder.encode("secret"))
	    		.roles(RestaurantUtil.USER_ROLE).build();

        return new InMemoryUserDetailsManager (user);
    }
    
}

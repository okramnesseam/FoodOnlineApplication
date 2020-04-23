package hh.maessen.FoodOnline.FoodOnline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.maessen.FoodOnline.FoodOnline.service.*;

// This class is annotated with @EnableWebSecurity to enable Spring Security's web security support
// and provide the Spring MVC integration.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserService userDetailsService;	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/css/*", "/img/*").permitAll() // Enables css when logged out
            	.anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login") // If the user hasn't logged in, it will bring him to the login-page from other addresses
                .defaultSuccessUrl("/foodlist", true) // this is the page to redirect after login succesfully
                .permitAll()
                .and()
                .httpBasic()
    			.and()
    			.csrf().disable()
    		.logout()
    			.logoutSuccessUrl("/login") // after logout it redirects back to login-page
    			.invalidateHttpSession(true)
    			.deleteCookies("JSESSIONID");
    }

    // Bcrypts the password of the user

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
} 
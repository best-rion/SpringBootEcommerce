package site.hossainrion.Ecommerce.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        //declares which Page(URL) will have What access type
        http
        	.csrf( Customizer.withDefaults() )
        	.authorizeHttpRequests((authReq) ->
	        	authReq
						.requestMatchers("/admin").hasAuthority("ADMIN")
						.requestMatchers("/cart", "/addToCart", "/increaseQty", "/decreaseQty").hasAnyAuthority("CUSTOMER","ADMIN")
	        			.requestMatchers("/", "/home/page-*", "/about", "/signup", "/css/**", "/js/**", "/images/**").permitAll()
	        			.anyRequest().authenticated()
        	)
        	.formLogin((loginCustomizer) ->
        		loginCustomizer
	        		.loginPage("/ecommerce/login")
						.loginProcessingUrl("/login")
	        		.defaultSuccessUrl("/ecommerce", true)
	        		.permitAll()
        	)
        	.logout((logout) -> 
	        	logout
	        		.logoutSuccessUrl("/ecommerce")
	        		.permitAll()
	        );
        
    	return http.build();
    }
    
    
    
    @Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}
    
	
    
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
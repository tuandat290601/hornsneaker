package vn.edu.hcmus.hornsneaker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/product/**","/assets/**", "/", "/home", "/register", "/register_success", "/login_success", "/category").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                    .and()
                .formLogin() // Cho phép người dùng xác thực bằng form login
                	.loginPage("/login")
                	.usernameParameter("email")
                	.permitAll()
                	.and()
                .logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                	.logoutSuccessUrl("/")// Cho phép logout
                    .permitAll();
        
    }
}
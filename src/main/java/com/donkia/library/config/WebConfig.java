package com.donkia.library.config;

import com.donkia.library.jwt.JwtAuthenticationFilter;
import com.donkia.library.jwt.JwtAuthorizationFilter;
import com.donkia.library.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //20220424 KBH 세션 방식은 사용 안함
                .and()
                .addFilter(corsFilter) //20220424 KBH @CrossOrigin 인증X, 시큐리티 필더에 등록 인증 O
                .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // 20220424 KBH login 전에 token을 확인하는 필터 생성
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .httpBasic().disable() //http basic은 헤더의 authorization영역에 id,pw를 담아서 서버에 보내는 방식(https 가 아니면 노출될 가능성이 있음). 그에 비해 bearer 방식은 token을 가지고 보냄
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()


        ;

    }
}

// security session 안에는 authentication타입의 객체가 들어가고, 그 안에는 user정보가 있는 userdetails 이 있음


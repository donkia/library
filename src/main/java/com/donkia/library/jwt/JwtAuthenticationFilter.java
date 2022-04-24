package com.donkia.library.jwt;

import com.donkia.library.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(request.getInputStream(), User.class);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);


        return super.attemptAuthentication(request, response);
    }

    /**
     * 1.username, passwor를 받아 login 시도
     * 2.정상인지 로그인 시도. authenticationManager로 로그인 시도를 하면, principalDetailsService가 호출 loadUserByUsername() 함수 실행됨
     * 3. principalDetails를 세션에 담고(권한 관리를 위해서)
     * 4. JWT 토큰을 만들어서 응답해주면 됨
     * */
}

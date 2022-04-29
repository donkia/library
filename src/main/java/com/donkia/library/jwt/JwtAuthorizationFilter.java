package com.donkia.library.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.donkia.library.auth.PrincipalDetails;
import com.donkia.library.user.User;
import com.donkia.library.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 시
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    //인증이나 권한이 필요한 주소 요청이 있을때 해당 필터를 타게 됨
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //super.doFilterInternal(request, response, chain);
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨");

        String jwtHeader = request.getHeader("Authorization");

        System.out.println("jwtHeader : " + jwtHeader);
        //header 가 있는지 확인
        if(jwtHeader == null || jwtHeader.startsWith("Bearer")){
            chain.doFilter(request, response);
            return ;
        }

        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
        String userName = JWT.require(Algorithm.HMAC512("donkia")).build().verify(jwtToken).getClaim("username").asString();

        System.out.println("jwtHeader : " + jwtHeader + " , userName : " + userName);
        //서명이 정상적으로 됨
        if(userName != null){
            User user = userRepository.findByEmail(userName);

            PrincipalDetails principalDetails = new PrincipalDetails(user);
            // Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            
            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }

    }
}

package com.donkia.library.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.donkia.library.auth.PrincipalDetails;
import com.donkia.library.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper om = new ObjectMapper(); //json 데이터를 파싱해줌
        User user = om.readValue(request.getInputStream(), User.class);
        System.out.println("user : " + user);

        //토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        // principalDetailservice의 loadUserByUserName()함수가 실행된 후 정상이면 authentication이 리턴됨
        // 내 로그인한 정보가 담김(DB에 있는 username과 password가 일치
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //  => 로그인이 되었다는 뜻
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("principalDetails.getUsername() : "+ principalDetails.getUsername());

        //authenticaion 객체가 session 영역에 저장을 해야하고 그 방법이 return 해주면 됨
        // 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는 것
        // jwt토큰을 사용할때 굳이 세션을 만들 필요X -> 권한 관리 때문에 session 사용
        return authentication;
    }

    /**
     * 1.username, passwor를 받아 login 시도
     * 2.정상인지 로그인 시도. authenticationManager로 로그인 시도를 하면, principalDetailsService가 호출 loadUserByUsername() 함수 실행됨
     * 3. principalDetails를 세션에 담고(권한 관리를 위해서)
     * 4. JWT 토큰을 만들어서 응답해주면 됨
     * */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 호출 - 인증이 완료");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        //RSA방식 말고 Hash암호방식
        String jwtToken = JWT.create()
                .withSubject("token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000*10))) //만료시간
                .withClaim("username", principalDetails.getUsername())
                .sign(Algorithm.HMAC512("donkia"));

        response.addHeader("Authorization", "Bearer " + jwtToken);
        System.out.println("jwtToken : " + jwtToken);
        System.out.println("response : " + response.toString());
        //super.successfulAuthentication(request, response, chain, authResult);
    }
}

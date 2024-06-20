package com.donggyu.dginside.filter;

import com.donggyu.dginside.user.CustomUserDetails;
import com.donggyu.dginside.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;


/*
* 기존의 UsernamePasswordAuthenticationFilter 는 폼 로그인 인증방식을 진행한다.
*
* JWT를 사용하면 폼 로그인 방식을 사용하지않으므로 해당 방식을 비활성화하였고, 해당 필터를 상속하여 대체할 필터를 생성한다.
* */
@AllArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        String username = obtainUsername(httpServletRequest); // 요청에서 username 인터셉트
        String password = obtainPassword(httpServletRequest); // 요청에서 password 인터셉트

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain, Authentication authentication) {
        // TODO: 왜 클래스 캐스팅을 해야 하는지?
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority authority = iterator.next();

        String role = authority.getAuthority();
        String token = jwtUtil.createJwt(username, role, 60 * 60 * 10L);

        httpServletResponse.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException) {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}

package com.kakao.clone.kakao.security.provider;

import com.kakao.clone.kakao.model.User;
import com.kakao.clone.kakao.repository.UserRepository;
import com.kakao.clone.kakao.security.UserDetailsImpl;
import com.kakao.clone.kakao.security.jwt.JwtDecoder;
import com.kakao.clone.kakao.security.jwt.JwtPreProcessingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String username = jwtDecoder.decodeUsername(token); //복호화

        //ODO: API 사용시마다 매번 User DB 조회 필요
        //-> 해결을 위해서는 UserDetailsImpl 에 User 객체를 저장하지 않도록 수정
        //ex) UserDetailsImpl 에 userId, username, role 만 저장
//JWT 에 userId, username, role 정보를 암호화/복호화하여 사용

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
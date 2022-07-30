package com.kakao.clone.kakao.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.clone.kakao.dto.responseDto.LoginResponseDto;
import com.kakao.clone.kakao.model.User;
import com.kakao.clone.kakao.security.jwt.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        System.out.println("FormLoginSuccessHandler token = " + token);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
        response.addHeader("nickname", userDetails.getUser().getNickname());
        response.addHeader("profileImage", userDetails.getUser().getProfileImage());
        response.addHeader("profileBgImage", userDetails.getUser().getProfileBgImage());




        //UserId 내려주기
        response.setContentType("application/json; charset=utf-8");
        User user = userDetails.getUser();
        LoginResponseDto loginResponseDto = new LoginResponseDto(user.getNickname(), user.getProfileImage(),user.getProfileBgImage(), "Success Login!!!",user.getUsername());
        String result = mapper.writeValueAsString(loginResponseDto);
        response.getWriter().write(result);
    }

}

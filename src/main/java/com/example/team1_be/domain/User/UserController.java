package com.example.team1_be.domain.User;

import com.example.team1_be.utils.security.auth.UserDetails.CustomUserDetails;
import com.example.team1_be.utils.security.auth.kakao.KakaoOAuth;
import com.example.team1_be.utils.security.auth.kakao.KakaoOAuthToken;
import com.example.team1_be.utils.security.auth.kakao.KakaoUserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final KakaoOAuth kakaoOAuth;

    @GetMapping("/login/kakao")
    public @ResponseBody ResponseEntity<String> kakaoCallback(String code) throws JsonProcessingException {
        System.out.println("로그인 처리중");
        System.out.println("code : "+code);
        KakaoOAuthToken kakaoOAuthToken = kakaoOAuth.getToken(code);
        System.out.println("code : "+kakaoOAuthToken);
        KakaoUserProfile kakaoOAuthProfile = kakaoOAuth.getProfile(kakaoOAuthToken);

        String login = userService.login(kakaoOAuthProfile);
        System.out.println("login : "+login);
        System.out.println("Bearer "+login);
        return ResponseEntity.ok().header("Authorization", login).body(login);
    }


}
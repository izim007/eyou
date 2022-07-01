package kr.momjobgo.eyou.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.momjobgo.eyou.web.dto.Token;
import kr.momjobgo.eyou.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(AuthController.PREFIX)
public class AuthController {

    public static final String PREFIX = "/user";

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid Token.TokenRequest request) throws JsonProcessingException {
        System.out.println("AuthController login");
        return ResponseEntity.ok().body(userService.login(request));
    }

    @PostMapping("/login/kakao")
    public ResponseEntity<?> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) throws Exception {
        //api 인증을 통해 얻어온 code값 받아오기
        String username = userService.kakaoLogin(socialLoginDto.getToken());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }

}

package kr.momjobgo.eyou.web.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.momjobgo.eyou.config.security.JwtTokenProvider;
import kr.momjobgo.eyou.web.dto.Token;
import kr.momjobgo.eyou.web.jpa.entity.UserEntity;
import kr.momjobgo.eyou.web.jpa.repository.UserRepository;
import kr.momjobgo.eyou.web.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUser(){
        List<UserEntity> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public Token.TokenResponse login(Token.TokenRequest request) throws JsonProcessingException {

        Optional<UserEntity> entity = userRepository.findBySnsId(request.getSnsId());

        Token.TokenResponse tokenResponse = new Token.TokenResponse();

        if(entity.isPresent()){
            tokenResponse.setToken(JwtTokenProvider.generateToken(entity.get()));
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setSnsId(request.getSnsId());
            newUser.setNickname(request.getNickname());
            tokenResponse.setToken(JwtTokenProvider.generateToken(userRepository.save(newUser)));
        }

        Optional<UserEntity> entity2 = userRepository.findBySnsId(request.getSnsId());
        tokenResponse.setId(entity2.get().getId());

        return tokenResponse;
    }

}

package com.example.sale3.service;

import com.example.sale3.base.Base;
import com.example.sale3.base.Check;
import com.example.sale3.base.Error;
import com.example.sale3.base.Success;
import com.example.sale3.entity.UserEntity;
import com.example.sale3.repository.UserRepository;
import com.example.sale3.request.UserRequest;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Check check;
    @Autowired
    private Error error;
    @Autowired
    private Success success;
    @Autowired
    private Base base;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Response<Status, UserResponse> register(UserRequest userRequest) {
        if(check.check(userRequest.getUsername())) return error.register();
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = new UserEntity(userRequest);
        userEntity = userRepository.save(userEntity);
        UserResponse userResponse = new UserResponse(userEntity);
        return success.register(userResponse);
    }
    public Response<Status, UserResponse> show() {
        UserEntity userEntity = userRepository.findByUsername(base.currentUser());
        UserResponse userResponse = new UserResponse(userEntity);
        return success.show(userResponse);
    }
}



























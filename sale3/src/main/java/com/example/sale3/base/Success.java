package com.example.sale3.base;

import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class Success {
    public Response<Status, UserResponse> register(UserResponse userResponse) {
        Status status = new Status("000", "register successful!");
        return new Response<>(status, userResponse);
    }
    public Response<Status, UserResponse> show(UserResponse userResponse) {
        Status status = new Status("000", "show user successful!");
        return new Response<>(status, userResponse);
    }
    public Status success(String message) {
        return new Status("000", message);
    }
}

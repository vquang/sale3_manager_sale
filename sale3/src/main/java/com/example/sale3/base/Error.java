package com.example.sale3.base;

import com.example.sale3.repository.UserRepository;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Error {
    public Response<Status, UserResponse> register() {
        Status status = new Status("111", "username has already existed!");
        return new Response<>(status, (UserResponse)null);
    }
    public Status page() {
        return new Status("111", "page out of range");
    }
    public Status notExist() {
        return new Status("111", "entity is not exist!");
    }
    public Status cannotDelete() {
        return new Status("111", "this entity is still have elements!");
    }
}

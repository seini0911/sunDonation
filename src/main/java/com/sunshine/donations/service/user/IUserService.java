package com.sunshine.donations.service.user;

import com.sunshine.donations.model.User;
import com.sunshine.donations.requests.RegisterUserRequest;

import java.util.List;

public interface IUserService {
    User registerNewUser(RegisterUserRequest userRequest);
    User findUserByName(String name);
    User findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByPhone(String phone);
    List<User> getAllUsers();
}

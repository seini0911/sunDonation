package com.sunshine.donations.service.user;

import com.sunshine.donations.exceptions.ResourceAlreadyExistException;
import com.sunshine.donations.model.User;
import com.sunshine.donations.repository.user.UserRepository;
import com.sunshine.donations.requests.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public User registerNewUser(RegisterUserRequest userRequest) {
        User newUser = User.builder()
                .name(userRequest.getName())
                .phone(userRequest.getPhone())
                .email(userRequest.getEmail())
                .location(userRequest.getLocation())
                .donations(new ArrayList<>())
                .build();
        log.info(" ****** Saving a new user with email :   "+ userRequest.getEmail());
        return Optional.of(newUser)
                .filter(user-> !userRepository.existsByEmail(newUser.getEmail()))
                .map(userRepository::save)
                .orElseThrow(
                    ()-> new ResourceAlreadyExistException(
                            "User with email : "+userRequest.getEmail()+" Already exist"
                    )
                );
    }

    @Override
    public User findUserByName(String name) {
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByPhone(String phone) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("*** Getting all users list : "+ userRepository.findAll());
        return userRepository.findAll();
    }
}

package com.phissy.blog.service.user;

import com.phissy.blog.dto.UserModel;
import com.phissy.blog.entity.User;
import com.phissy.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserModel userModel) {
        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new RuntimeException("user with email: "+userModel.getEmail()+" already exists");
        }
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setName(userModel.getName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole(userModel.getRole());
        return userRepository.save(user);
    }

    @Override
    public User readUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user with email: "+email+" does not exist"));
    }

    @Override
    public User updateUser(String email, UserModel userModel) {
        User user = readUser(email);
        user.setName(userModel.getName() != null ? userModel.getName() : user.getName());
        user.setEmail(userModel.getEmail() != null ? userModel.getEmail() : user.getEmail());
        user.setPassword(userModel.getPassword() != null ? passwordEncoder.encode(userModel.getPassword()) : user.getPassword());
        user.setRole(userModel.getRole() != null ? userModel.getRole() : user.getRole());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        User user = readUser(email);
        userRepository.delete(user);
    }
}

package com.phissy.blog.service.user;

import com.phissy.blog.entity.User;
import com.phissy.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existingUser = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found for email "+email));
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());

    }
}

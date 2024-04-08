package com.study.testsecurity.service;

import com.study.testsecurity.dto.CustomUserDetails;
import com.study.testsecurity.entity.UserEntity;
import com.study.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);
        if(userData != null){

            return new CustomUserDetails(userData);
        }

        return null;
    }
}

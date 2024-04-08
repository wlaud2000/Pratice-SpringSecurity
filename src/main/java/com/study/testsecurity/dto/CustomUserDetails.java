package com.study.testsecurity.dto;

import com.study.testsecurity.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //사용자의 특정한 권한을 리턴해주는 메소드

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return userEntity.getRole(); //Role 값 반환

            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //계정이 만료되었는지 여부를 나타내는 메서드
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠겨 있는지 여부를 나타내는 메서드
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 자격 증명이 만료되었는지 여부를 나타내는 메서드
        return true;
    }

    @Override
    public boolean isEnabled() { // 사용자가 활성화되었는지 여부를 나타내는 메서드
        return true;
    }
}

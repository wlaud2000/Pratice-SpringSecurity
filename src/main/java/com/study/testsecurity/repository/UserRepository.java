package com.study.testsecurity.repository;

import com.study.testsecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);
         //username이 데이터베이스에 존재하는지 여부를 확인하고, 존재하면 true를 반환하고, 그렇지 않으면 false를 반환. 주로 사용자 이름이 고유한지 확인하는 데 사용
}

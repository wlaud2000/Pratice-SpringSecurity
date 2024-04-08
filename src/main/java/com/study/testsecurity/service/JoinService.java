package com.study.testsecurity.service;

import com.study.testsecurity.dto.JoinDTO;
import com.study.testsecurity.entity.UserEntity;
import com.study.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinProcess(JoinDTO joinDTO){

        //DB에 이미 동일한 username을 가진 회원이 존재하는지 검증
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser) {
            return ; //참이라면(존재한다면) 회원가입 로직을 진행하지 않고 함수를 리턴
        }

        //앞단에서 받은 DTO의 userdata를 Entity로 변환
        UserEntity data = new UserEntity(); //빈 객체 생성
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); //비밀번호는 암호화시켜서 DB에 넣어줘야 함.
        data.setRole("ROLE_ADMIN"); //회원 자신이 특정한  Role값을 설정할 수 없기때문에 Service단에서 일시적으로 강제로 넣어줌


        userRepository.save(data); //DB에 data를 insert할 수 있는 save 메소드
    }

}

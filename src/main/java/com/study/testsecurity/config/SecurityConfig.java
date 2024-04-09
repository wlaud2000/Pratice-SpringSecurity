package com.study.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ //암호화를 진행할 메소드 생성 단방향(해독불가)

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth.loginPage("/login") //loginPage메소드 안에 로그인 페이지 경로를 설정해주면 /admin으로 갔을 때 오류페이지가 아니라 자동으로 /login으로 리다이렉션 해준다.
                        .loginProcessingUrl("/loginProc") //프론트단에서 loginPrecessingUrl에 설정해준 /loginProc로 login데이터를 넘기면 /loginProc로 특정한 Security가 받아서 로그인처리를 진행한다.
                        .permitAll() //이 경로는 아무나 들어올 수 있다.
                );
        http
                .csrf((auth) -> auth.disable()); //csrf : 사이트 간 위조 요청 -> Spring Security는 기본 설정 되어있음
                                                //csrf설정이 동작되면 post요청을 보낼 때 csrf 토큰도 보내줘야 login이 진행된다.
                                                //근데 csrf를 켜놓으면 개발 환경에서 토큰을 보내지 않으면 login이 진행되지 않기 때문에 잠시 disable시켜줌.

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) //하나의 아이디에 대한 다중 로그인 허용 개수, 만약 3이면 로그인이 유지된 상태로 최대 3개까지 로그인 진행 가능
                        .maxSessionsPreventsLogin(true)); //다중 로그인 개수를 초과하였을 경우 처리 방법
                                                          //true : 새로운 로그인 차단 false : 기존 로그인 세션 삭제

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId()); //로그인 시 동일한 세션에 대한 id 변경

        return http.build();
    }
}
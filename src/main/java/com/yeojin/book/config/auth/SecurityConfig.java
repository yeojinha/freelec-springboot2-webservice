package com.yeojin.book.config.auth;

import com.yeojin.book.domain.user.Role;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//1 Spring Security 설정들을 활성화시켜 준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                //2).headers().frameOptions().disable()  h2-console 화면을 사용하기 위해서, 해당 옵션들을 disable한다
                .and()
                    .authorizeRequests()//3) URL별 권한 관리를 설정하는 옵션의 시작점
                //authorizeRequests가 선언되어야만 antMatcher사용 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.
                                            USER.name())//4) .antMatchers
                /*권한 관리 대상을 지정하는 옵션 URL, HTTP 메소드별로 관리가 가능
                *"/"등 지정된 URL들을 permitAll() 옵션을 통해 전체 열람 권한을 주었다.
                * "/api/v1/**" 주소를 가진 API는 USER권한을 가진 사람만 가능하도록 했다.(hasRole(Role.USER.name())*/
                    .anyRequest().authenticated()//5 인증된 사용자들(로그인한)에게만 허용.
                //anyRequest 설정된 값들 이외 나머지 URL들을 나타낸다.
                .and()
                    .logout()//로그아웃 진입점
                        .logoutSuccessUrl("/")//6 로그아웃 성공 시 / 주소로 이동
                .and()
                    .oauth2Login()//OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint()//OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customOAuth2UserService);//7 로그인 이후 후속 조치를 진행할 인터페이스를 등록
        /*로그인 이후의  후속 조치는 customOAuth2UserService에서 담당한다. */
    }
}

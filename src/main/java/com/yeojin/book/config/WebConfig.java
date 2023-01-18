package com.yeojin.book.config;

import com.yeojin.book.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {//WebMvcConfigurer을 구현
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolver)
    {
        argumentResolver.add(loginUserArgumentResolver);
    }//새로운 세팅을 위 메소드 add 하는 것만으로 ok
}
//https://starkying.tistory.com/entry/Spring-MVC-%E2%80%94-HandlerMethodArgumentResolver-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
//위 링크들어가면 해설
//addArgumentResolvers 메소드로 파라미터 리스트에 추가하는 것으로 세팅 추가된다.

package com.yeojin.book.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)//1) 이 어노테이션이 생성될 수 있는 위치를 지정, parameter로 선언되었으니, 파리미터로 선언된 객체에만 사용가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {//2) @interface 이 파일을 어노테이션으로 지정, 이제 LoginUser라는 어노테이션 생성
}

package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//이 어노테이션에 의해 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정, 이 위치부터 읽이에 항상 최상위 위치
public class Application {//Application은 앞으로 만들 프로젝트의 메인 클래스가 된다.
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        /*SpringApplication.run으로 인해 내장 WAS(Web Application Server)를 실행
        * 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar파일(실행 가능한 Java 패키징 파일)로 실행하면 된다.*/

        /*내장 WAS를 사용하는 이유는 외장의 경우, 모든 서버는 WAS의 종류와 버전, 설정을 일치시켜야만 한다. 30대면 30대 모두
        * 그러나 내장의 경우 그러지 않아도 된다.*/

    }
}

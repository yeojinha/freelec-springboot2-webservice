package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)// (1) // 테스트시 JUnit에 내장된 실행자 외에 다른 실행자를 실행
//여기선 SpringRunner라는 스프링 실행자를 사용. 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@WebMvcTest//(controllers = HelloController.class)// (2) //Spring MVC에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired//(3) 스프링이 관리하는 Bean을 주입받음.
    private MockMvc mvc; //(4) 웹 API 테스트시 사용
    /*스프링 MVC테스트의 시작점
    * 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.*/

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello="hello";

        mvc.perform(get("/hello"))// "/hello"주소로 HTTP GET을 쵸헝
                .andExpect(status().isOk()) //(6) 200,404,500등의 상태를 검증 ok 즉, 200인지 아닌지 검증.
                .andExpect(content().string(hello)); //(7) return하는 값이 hello인지 아닌지 검증
        //content().string(hello)는 response body에(return하는 값이) "hello"가 있는지 확인 
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{

        String name="hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                                    .param("name",name)
                                    .param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$.amount", is(amount)));
    }
}

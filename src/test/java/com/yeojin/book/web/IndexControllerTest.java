package com.yeojin.book.web;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void mainPage_loading(){
        //when 
        String body = this.restTemplate.getForObject("/",String.class);
        /**getForObject에서 해당하는 url ="/"에 대해서 내용이 있는지 아래 assertThat을 이용하셔 Test*/
        
        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

    }

}
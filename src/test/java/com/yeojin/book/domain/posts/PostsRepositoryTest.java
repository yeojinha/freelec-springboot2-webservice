package com.yeojin.book.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest//H2 database 설정
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After//단위 테스트 끝날 때마다 수행되는 메소드를 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void load_saved() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "yeojin@gmail.com";

        postsRepository.save(Posts.builder()//save 테이블 쿼리에 insert/update 쿼리실행, id 값 있다면 update 없다면 insert 쿼리 실행
                .title(title)
                .content(content)
                .author(author)
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    }
}
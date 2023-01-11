package com.yeojin.book.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/** BaseTimeEntity를 상속하여, 객체 생성.수정etc에 시간기록 자동*/
@Getter
@NoArgsConstructor//기본 생성자 자동추가 public Posts() {}와 같은 효과
@Entity//테이블과 링크될 클래스임을 명시
public class Posts extends BaseTimeEntity{

    @Id//Primary Key를 나타낸다.(pk)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pk생성 규칙 springboot 2.0에서는 GenerationType.IDENTITY 추가해야 auto_increment
    private Long id;

    @Column(length = 500, nullable = false)//테이블 Column 
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}

package com.yeojin.book.domain.user;

import com.yeojin.book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//로그인한 사용자의 정보를 담는 class 
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {//로그인시간에 대한 정보 기록
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
/**
 * https://hyeon9mak.github.io/not-null-vs-column-nullable-false/
 * @NotNull과 @Column(nullable = false)의 차이
 */

//    @Column(nullable = false)
    @NotNull
    private String email;

    @Column(nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)//JPA로 데이터베이스로 저장할 때, Enum 값을 어떤 형태로 저장할지 결정(기본은 init, 그러나
    //숫자로 저장할 시에, 그 값이 무슨 코드인지 알 수 없다 그러니 문자열로 하자.
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name=name;
        this.email=email;
        this.picture=picture;
        this.role=role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}

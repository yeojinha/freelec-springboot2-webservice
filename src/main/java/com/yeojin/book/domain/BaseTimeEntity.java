package com.yeojin.book.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createDate, modifiedDate)도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)//본 클래스에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {//모든 Entity 클래스의 상위 클래스가 되어, createDate, modifiedDate를 자동 관리하는 역할

    @CreatedDate//Entity 생성 시에 자동 시간 기록
    private LocalDateTime createdDate;

    @LastModifiedDate//Entity 수정 시에 자동 시간 기록
    private LocalDateTime modifiedDate;

}

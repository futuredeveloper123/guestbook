package kr.co.dajsoft.guestbook.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//상속을 위한 Entity Class
@MappedSuperclass

//entity에 변화가 생기는 것을 감지하도록 설정
@EntityListeners(value = {AuditingEntityListener.class})

//get 메서드 생성
@Getter

//abstract를 붙이면 인스턴스를 가지지 않음
abstract class BaseEntity {
    @CreatedDate //만들어진 날짜 저장
    @Column//컬럼이름은 regdate가 되고 수정은 안됨
    (name="regdate", updatable = false)
    private LocalDateTime regDate;


    @LastModifiedDate //만들어진 날짜 저장
    @Column //컬럼이름은 moddate
     (name="moddate")
    private LocalDateTime modDate;
}

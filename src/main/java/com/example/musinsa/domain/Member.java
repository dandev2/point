package com.example.musinsa.domain;

import com.example.musinsa.exception.NotEnoughPointException;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String name; // 회원이름

    @Column(name="pointname")
    private String pointName; //포인트 이름

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;

    @Column(nullable = false, length = 10)
    private int point; // 포인트

    @Column(name="totalpoint", length = 10)
    private int totalPoint; // 총액

    // 관리자가 포인트 금액 수정할 수 있게 설정
    public void changePoint(int point) {
        this.point = point;
    }

    //== 비즈니스 로직==//
    /**
     * point 증가
     */
    public void addPoint(int point) {
        this.totalPoint += point;
    }

    /**
     * point 감소
     */
    public void removePoint(int point) {
        int restPoint = this.totalPoint - point;
        if(restPoint < 0) {
            throw new NotEnoughPointException("need more point");
        }
        this.totalPoint = restPoint;
    }

}

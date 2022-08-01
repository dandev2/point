package com.example.musinsa.data;

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

    @Column(name="balancingpoint", length=10)
    private int balancingPoint; // 잔액

    @Column(name="totalpoint", length = 10)
    private int totalPoint; // 총액

    // 관리자가 포인트 금액 수정할 수 있게 설정
    public void changePoint(int point) {
        this.point = point;
    }

}

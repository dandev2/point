package com.example.musinsa.dto;

import jdk.vm.ci.meta.Local;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO { // 포인트 적립 조회
    private Long memberId;
    private String pointName;
    private LocalDateTime regDate;
    private int point;
}

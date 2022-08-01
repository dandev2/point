package com.example.musinsa.data;

import javax.persistence.Column;
import javax.persistence.Id;

public class Member {
    @Id
    private Long memberId;
    @Column
    private int point;

}

package com.example.musinsa.repository;

import com.example.musinsa.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Member member = Member.builder()
                    .memberId((long) i)
                    .name("name" + i)
                    .pointName("회원가입축하포인트" + i)
                    .regDate(LocalDateTime.now())
                    .modDate(LocalDateTime.now())
                    .point((int) (Math.random() * i))
                    .balancingPoint(0)
                    .totalPoint(0)
                    .build();
            System.out.println(memberRepository.save(member));
        });
    }

    @Test
    public void updateTest() {
        Optional<Member> result = memberRepository.findById(300L); // 존재하는 번호로 test
        if(result.isPresent()) {
            Member member = result.get();
            member.changePoint(3333);
            memberRepository.save(member);
        }
    }
}


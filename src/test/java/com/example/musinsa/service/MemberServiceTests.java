package com.example.musinsa.service;

import com.example.musinsa.domain.Member;
import com.example.musinsa.dto.MemberDTO;
import com.example.musinsa.dto.PageRequestDTO;
import com.example.musinsa.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Test
    public void testRegister() { // 포인트 등록 테스트
        IntStream.rangeClosed(1, 300).forEach(i -> {
            MemberDTO memberDTO = MemberDTO.builder()
                    .memberId((long) i)
                    .pointName("회원가입 축하 포인트")
                    .regDate(LocalDateTime.now())
                    .point(i)
                    .build();
            System.out.println(memberService.register(memberDTO));
        });
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<MemberDTO, Member> resultDTO = memberService.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());
        System.out.println("----------------------");
        for(MemberDTO memberDTO : resultDTO.getDtoList()) {
            System.out.println(memberDTO);
        }

        System.out.println("===============");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }


}

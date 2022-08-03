package com.example.musinsa.service;

import com.example.musinsa.domain.Member;
import com.example.musinsa.dto.MemberDTO;
import com.example.musinsa.dto.PageRequestDTO;
import com.example.musinsa.dto.PageResultDTO;

public interface MemberService {
    Long register(MemberDTO memberdto);

    PageResultDTO<MemberDTO, Member> getList(PageRequestDTO requestDTO);

    MemberDTO read(Long memberId);

    void remove(Long memberId);

    void modify(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO) {
        Member memberEntity = Member.builder()
                .memberId(memberDTO.getMemberId())
                .pointName(memberDTO.getPointName())
                .regDate(memberDTO.getRegDate())
                .point(memberDTO.getPoint())
                .build();
        return memberEntity;
    }

    default MemberDTO entityToDto(Member memberEntity) {
        MemberDTO memberDto = MemberDTO.builder()
                .memberId(memberEntity.getMemberId())
                .pointName(memberEntity.getPointName())
                .regDate(memberEntity.getRegDate())
                .point(memberEntity.getPoint())
                .build();
        return memberDto;
    }
}

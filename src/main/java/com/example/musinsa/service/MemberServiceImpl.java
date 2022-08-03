package com.example.musinsa.service;

import com.example.musinsa.domain.Member;
import com.example.musinsa.dto.MemberDTO;
import com.example.musinsa.dto.PageRequestDTO;
import com.example.musinsa.dto.PageResultDTO;
import com.example.musinsa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long register(MemberDTO memberdto) {
        log.info("DTO--");
        log.info(memberdto);

        Member memberEntity = dtoToEntity(memberdto);
        log.info(memberEntity);
        memberRepository.save(memberEntity);

        return memberEntity.getMemberId();
    }

    @Override
    public PageResultDTO<MemberDTO, Member> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("memberId").descending());
        Page<Member> result = memberRepository.findAll(pageable);

        Function<Member, MemberDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public MemberDTO read(Long memberId) {
        Optional<Member> result = memberRepository.findById(memberId);
        return result.isPresent()? entityToDto(result.get()) : null;
    }

    @Override
    public void remove(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        Optional<Member> result = memberRepository.findById(memberDTO.getMemberId());
        validateMinusPoint(memberDTO);
        if(result.isPresent()) {
            Member entity = result.get();
            entity.changePoint(memberDTO.getPoint());
            memberRepository.save(entity);
        }
    }

    private void validateMinusPoint(MemberDTO memberDTO) {
        if(memberDTO.getPoint()<0) {
            throw new IllegalStateException("포인트는 0원이상이어야 합니다.");
        }
    }
}

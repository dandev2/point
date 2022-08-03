package com.example.musinsa.controller;

import com.example.musinsa.dto.MemberDTO;
import com.example.musinsa.dto.PageRequestDTO;
import com.example.musinsa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list..." + pageRequestDTO);
        model.addAttribute("result", memberService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get");
    }

    @PostMapping("/register")
    public String registerPost(MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
        log.info("memberDTO : " + memberDTO);

        Long memberId = memberService.register(memberDTO);
        redirectAttributes.addFlashAttribute("msg", memberId);
        return "redirect:/member/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long memberId, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("memberId : " + memberId);
        MemberDTO memberDTO = memberService.read(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @PostMapping("/remove")
    public String remove(long memberId, RedirectAttributes redirectAttributes) {
        log.info("memberId : " + memberId);

        memberService.remove(memberId);
        redirectAttributes.addFlashAttribute("msg", memberId);
        return "redirect:/member/list";
    }

    @PostMapping("/modify")
    public String modify(MemberDTO memberDTO, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("post modify");
        log.info("memberDTO : " + memberDTO);

        memberService.modify(memberDTO);
        redirectAttributes.addFlashAttribute("page", requestDTO.getPage());
        redirectAttributes.addFlashAttribute("memberId", memberDTO.getMemberId());

        return "redirect:/member/read";
    }
}

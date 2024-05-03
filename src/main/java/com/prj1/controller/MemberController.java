package com.prj1.controller;

import com.prj1.domain.Member;
import com.prj1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService service;

    @GetMapping("signup")
    public String signupForm() {

        return "member/signup";
    }

    @PostMapping("signup")
    public String signup(Member member) {
        service.signup(member); // request parameter 받은 것을 넘겨준다 -> Member DTO를 사용 (singup 메소드의 파라미터로 String email, Integer password, String nickName 을 넣는 대신에 사용)

        return "redirect:/";
    }

    @GetMapping("list")
    public String list(Model model) {
        // 멤버 리스트를 조회하여 model 객체에 저장
        model.addAttribute("memberList", service.list());
        // service에서 list() 메소드를 호출해서 회원 정보 목록을 가져온다
        // 반환된 회원 정보 목록을 model 객체에 memberList라는 이름으로 저장
        return "member/list";
    }

    @GetMapping("")
    public String info(Integer id, Model model) {
        model.addAttribute("member", service.get(id));

        return "member/info";
    }

    @PostMapping("remove")
    public String remove(Integer id, Authentication authentication) {
        if (service.hasAccess(id, authentication)) {
            // 접근 가능할때만 삭제
            service.remove(id);
        }

        return "redirect:/member/logout";
    }

    @GetMapping("modify")
    public String modifyForm(Integer id, Model model) {
        model.addAttribute("member", service.get(id));

        return "member/modify";
    }

    @PostMapping("modify")
    public String modify(Member member, Authentication authentication, RedirectAttributes rttr) {
        if (service.hasAccess(member.getId(), authentication)) {
            // 자신의 정보만 삭제
            service.modify(member);
        }

        rttr.addAttribute("id", member.getId());
        return "redirect:/member";
    }

    @GetMapping("email")
    @ResponseBody /*응답한 데이터를 view로 해석하지 않는다*/
    public String emailCheck(String email) {
//        System.out.println("email = " + email);
        String message = service.emailCheck(email);
        // ajax는 일한 결과(message)가 바로 응답됨 -> model에 저장하지 않는다

        return message; // response가 온다
    }

    @GetMapping("login")
    public String loginForm() {
        return "member/login";
    }
}

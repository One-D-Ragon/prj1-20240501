package com.prj1.controller;

import com.prj1.domain.Board;
import com.prj1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/add")
    public String add() {
        return "board/add";
    }

    @PostMapping("/add")
    public String addPost(Board board, Authentication authentication, RedirectAttributes rttr) {
        // Authentication -> 스프링 시큐리티에게 현재 로그인해있는 사람의 정보를 얻음
        // 로그인해 있는 사람의 정보가 들어있음
        // username, password -> UserDetails(customUser) username, password
        service.add(board, authentication);

        rttr.addAttribute("id", board.getId());
        return "redirect:/board";
//        return "redirect:/board?id=";
    }

    // /board?id=3
    @GetMapping("/board")
    public String view(Integer id, Model model) {
        // 게시물 조회(select)
        Board board = service.get(id);

        // 모델에 넣고
        model.addAttribute("board", board);
        // jsp 로 포워드
        return "board/view";
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       Model model) {
        // 게시물 목록 조회(select)
        // 모델에 넣고
//        model.addAttribute("boardList", service.list(page));

        // 페이징용
        // 모델 어트리뷰트에 게시물 목록, 페이지에 관련한 정보들을 모아서 건내줘야함 -> 서비스가 일할 때 한꺼번에 넣어준다
        model.addAllAttributes(service.list(page));
        // service가 맵을 리턴

        // jsp로 포워드
        return "board/home";
    }

    @PostMapping("/delete")
    public String delete(Integer id, Authentication authentication) {
        // id는 게시물의 정보, authentication은 작성한 사람의 정보
        if (service.hasAccess(id, authentication)) {
            service.remove(id);
        }

        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer id, Model model) {
        // 조회 해서
        // 모델에 넣고
        model.addAttribute("board", service.get(id));
        // view로 포워드
        return "board/modify";
    }

    @PostMapping("/modify")
    public String modifyPost(Board board, RedirectAttributes rttr) {
        service.modify(board);

        rttr.addAttribute("id", board.getId());
        return "redirect:/board";
    }
}

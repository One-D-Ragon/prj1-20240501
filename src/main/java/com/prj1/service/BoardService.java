package com.prj1.service;

import com.prj1.domain.Board;
import com.prj1.domain.CustomUser;
import com.prj1.domain.Member;
import com.prj1.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BoardService {

    private final BoardMapper mapper;

    public void add(Board board, Authentication authentication) {
        Object principal = authentication.getPrincipal(); /*멤버를 꺼낸다*/
        /*autentication 객체 안에 우리가 만든 CustomUser 객체가 들어가있다*/
        if (principal instanceof CustomUser user) {
            Member member = user.getMember();
            board.setMemberId(member.getId());
            mapper.insert(board);
        }
    }

    public Board get(Integer id) {
        return mapper.selectById(id);
    }

    public List<Board> list() {

        return mapper.selectAll();
    }

    public void remove(Integer id) {
        mapper.deleteById(id);
    }

    public void modify(Board board) {
        mapper.update(board);
    }

    /*    public List<Board> list(Integer page) {
     *//* page가 아니라 offset을 계산해서 넣어줌*//*
        int offset = (page - 1) * 10;
        return mapper.selectAllByPage(offset);
    }  */

    /*페이징용*/
    public Map<String, Object> list(Integer page) {
        /* page가 아니라 offset을 계산해서 넣어줌*/
        int offset = (page - 1) * 10;
        /*총 게시물 수*/
        int numberOfBoard = mapper.countAll();
        /*마지막 페이지 번호*/
        int lastPageNumber = (numberOfBoard - 1) / 10 + 1;

        /*시작 번호와 끝 번호*/
        int endPageNumber = (page - 1) / 10 * 10 + 10;
        int beginPageNumber = endPageNumber - 9;

        /*끝 번호는 마지막 페이지 번호보다 클 수 없음*/
        endPageNumber = Math.min(endPageNumber, lastPageNumber);

        /*이전, 다음 버튼*/
        int prevPageNumber = beginPageNumber - 10;
        int nextPageNumber = beginPageNumber + 10;

        return Map.of("boardList", mapper.selectAllByPage(offset)
                , "pageInfo", Map.of("lastPageNumber", lastPageNumber,
                        "endPageNumber", endPageNumber,
                        "beginPageNumber", beginPageNumber,
                        "prevPageNumber", prevPageNumber,
                        "nextPageNumber", nextPageNumber,
                        "currentPageNumber", page));
    }

    public boolean hasAccess(Integer id, Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        Board board = mapper.selectById(id);
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUser user) {
            Member member = user.getMember();

            return board.getMemberId().equals(member.getId());
        }

        return false;
    }
}

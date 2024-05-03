package com.prj1.service;

import com.prj1.domain.Member;
import com.prj1.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public void signup(Member member) {
        // 패스워드를 인코딩
        // String password = member.getPassword();
        // String encodedPassword = encoder.encode(password);
        // member.setPassword(encodedPassword);

        member.setPassword(encoder.encode(member.getPassword())); // 3줄짜리 코드를 1줄로 줄임

        mapper.insert(member); // mapper한테 insert를 시킴
    }

    public List<Member> list() {
        return mapper.selectAll(); // mapper의 selectAll() 쿼리를 실행하여 member 테이블의 모든 데이터를 조회
    }

    public Member get(Integer id) {
        return mapper.selectById(id);
    }

    public void remove(Integer id) {
        mapper.deleteById(id);
    }

    public void modify(Member member) {
        mapper.update(member);
    }

    public String emailCheck(String email) {
        Member member = mapper.selectByEmail(email);
        if (member == null) {
            // 사용 가능한 이메일
            return "사용 가능한 이메일입니다.";
        } else {
            // 이미 존재하는 이메일
            return "사용 불가능한 이메일입니다.";
        }
    }
}

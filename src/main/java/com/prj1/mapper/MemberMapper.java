package com.prj1.mapper;

import com.prj1.domain.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member
            (email, password, nick_name)
            VALUES (#{email}, #{password}, #{nickName})
            """)
    int insert(Member member);

    @Select("""
            SELECT *
            FROM member
            ORDER BY id DESC
            """)
    List<Member> selectAll();
    // select 쿼리를 실행해서 member 테이블의 모든 데이터를 조회하고 List<Member> 객체에 저장한다

    @Select("""
            SELECT *
            FROM member
            WHERE id = #{id}
            """)
    Member selectById(Integer id);

    @Delete("""
            DELETE FROM member
            WHERE id = #{id}
            """)
    int deleteById(Integer id);
}

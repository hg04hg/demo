package spring.basic.demo.repository;

import spring.basic.demo.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberRepository implements MemberRepositoryInterface{

    public static Map<Integer, Member> dbMap = new HashMap<>();

    public static int index = 1;


    @Override
    public void saveMember(Member member) {
        member.setId(index++);
        dbMap.put(member.getId(), member);
    }

    @Override
    public Member findById(int id) {
        return dbMap.get(id);
    }

}

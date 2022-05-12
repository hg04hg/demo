package spring.basic.demo.repository;

import spring.basic.demo.domain.Member;

public interface MemberRepositoryInterface {

    void saveMember(Member member);

    Member findById(int id);
}

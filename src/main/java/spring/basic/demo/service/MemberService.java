package spring.basic.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.basic.demo.domain.Member;
import spring.basic.demo.repository.MemberRepositoryInterface;

public class MemberService {

    private MemberRepositoryInterface repository;

    @Autowired
    public MemberService(MemberRepositoryInterface repository){
        this.repository = repository;
    }

    public void join(Member member){
        repository.saveMember(member);

    }

    public Member findMemberById(int id){
        return repository.findById(id);
    }


}

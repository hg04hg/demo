package spring.basic.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.basic.demo.domain.Member;
import spring.basic.demo.service.MemberService;

@Controller
public class MemberController {

    MemberService service;

    @Autowired
    public MemberController(MemberService service){
        this.service = service;
    }

    @GetMapping("/members/new")
    public String createMember(){
        return "members/createForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm){

        Member member = new Member();

        member.setName(memberForm.getName());

        // DB 에 넣기 (db에 넣어줄 때, Member 객체의 id 값을 시스템에서 생성해줌
        service.join(member);

        return "redirect:/"; // 제일 첫 페이지로 돌아감
    }

    @GetMapping("/members/find")
    public String findMember(){
        return "members/findForm";
    }

    @PostMapping("/members/find")
    public String find(@RequestParam("id") int id, Model model){
        // service 를 통해서 id로 member를 찾아서
        Member member = service.findMemberById(id);

        // 찾은 객체를 통째로 다음 페이지에 넘기기
        model.addAttribute("member", member);

        // 다음 페이지 이동
        return "members/findMember";
    }

}

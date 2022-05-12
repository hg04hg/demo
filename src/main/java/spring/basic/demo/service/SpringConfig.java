package spring.basic.demo.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.basic.demo.repository.JDBCMemberRepository;
import spring.basic.demo.repository.MemberRepositoryInterface;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    /*
        Controller 는 이 페이지에 모으지 않습니다!
        왜냐하면, 원래부터 스프링 관할
     */
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepositoryInterface());
    }

    @Bean
    public MemberRepositoryInterface memberRepositoryInterface(){
        return new JDBCMemberRepository(dataSource);
    }





}

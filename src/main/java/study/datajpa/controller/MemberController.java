package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    //도메인 클래스 컨버터
    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    //page, size, sort 변수로 자동 매핑
    //http://localhost:8080/members?page=0&size=1&sort=id,desc&sort=username,desc
    //디폴트 값 설정은 yml
    //여기에만 특별하게 사용하고 싶다면
    //@PageableDefault(size = 10, page = 1) Pageable pageable
    @GetMapping("/members")
    public Page<Member> list(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }

    @GetMapping("/members2")
    public Page<MemberDto> listDto(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(member -> new MemberDto(member.getId(), member.getUsername(), null));
    }


    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}

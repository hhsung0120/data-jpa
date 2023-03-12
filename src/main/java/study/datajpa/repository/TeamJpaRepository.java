package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;


//@Repository 생략 가능, 스프링에서 상속된 인터페이스만 보고 알아서 주입
public interface TeamJpaRepository extends JpaRepository<Team, Long> {
}

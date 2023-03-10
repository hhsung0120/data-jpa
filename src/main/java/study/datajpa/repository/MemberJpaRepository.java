package study.datajpa.repository;


import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        System.out.println(member.toString());
        em.persist(em);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}

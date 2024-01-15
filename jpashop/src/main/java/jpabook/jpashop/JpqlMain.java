package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jpabook.jpashop.domain.Member;

import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            List<Member> result = em.createQuery("select m from Member m where m.name like '%kim'", Member.class)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member = "+ member);
//            }

            // Criteria 사용 -> 유지보수도 어렵고 너무 어려워.. 쓰지않는걸 추천
            // 그럼 뭘?? --> QueryDSL 사용 권장
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m);
            String name = "abcd";
            if (name != null) {
                cq.where(cb.equal(m.get("name"),"kim"));
            }
            List<Member> resultList = em.createQuery(cq)
                            .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package hellojpa.jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            // TypeQuery: 반환 타입이 명확할 때
//            // Query: 반환 타입이 명확하지 않을 때
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
////            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
////            Query query3 = em.createQuery("select m.username, m.age from Member m");
//
//            List<Member> resultList = query1.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }
//
//            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.id = 1", Member.class);
//            // SingleResult의 경우 결과가 없으면: NoResultException
//            //                    결과가 둘 이상이면: NonUniqueResultException
//            Member result = query2.getSingleResult();
//
//            TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.username = :username", Member.class);
//            query3.setParameter("username", "member1");
//            Member singleResult = query3.getSingleResult();
//            System.out.println("singleResult = " + singleResult.getUsername());

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
            Member member1 = result.get(0);
            member1.setAge(20);
            em.createQuery("select t from Member m join m.team t", Team.class).getResultList();
            em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            List<MemberDTO> result2 = em.createQuery("select new hellojpa.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            MemberDTO memberDTO = result2.get(0);
            System.out.println("memberDTO. = " + memberDTO.getUsername());
            System.out.println("memberDTO. = " + memberDTO.getAge());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

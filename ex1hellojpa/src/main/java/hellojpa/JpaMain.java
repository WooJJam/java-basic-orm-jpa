package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.awt.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
<<<<<<< HEAD
////            Member findMember = em.find(Member.class, 2);
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1) // 1 번부터
//                    .setMaxResults(8) // 8개
//                    .getResultList();
////            findMember.setName("HelloJPA");
//            for (Member member: result) {
////                System.out.println("member.getName() = " + member.getName());
//            }
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

=======
>>>>>>> section6
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);
            em.persist(team);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

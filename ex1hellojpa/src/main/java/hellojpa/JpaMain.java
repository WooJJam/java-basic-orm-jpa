package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();
            
            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());
            System.out.println("m1, m2 = " + (m1 instanceof Member));
            System.out.println("m1, m2 = " + (m2 instanceof Member));

            Member m3 = em.find(Member.class, member1.getId());
            System.out.println("m3.getClass() = " + m3.getClass());

            em.flush();
            em.clear();
            
            Member m4 = em.getReference(Member.class, member1.getId());
            System.out.println("m4.getClass() = " + m4.getClass());
            System.out.println("m4.getId() = " + m4.getId());
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(m4));
//            Member findMember = em.getReference(Member.class, member.getId());
//            System.out.println(findMember.getId());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

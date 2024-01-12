package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//           Team team = new Team();
//           team.setName("teamA");
//           em.persist(team);
//
//           Team team2 = new Team();
//           team.setName("teamB");
//           em.persist(team2);
//
//           Member member1 = new Member();
//           member1.setUsername("member1");
//           member1.setTeam(team);
//           em.persist(member1);
//
//           Member member2 = new Member();
//           member2.setUsername("member2");
//           member2.setTeam(team2);
//           em.persist(member2);
//
//           em.flush();
//           em.clear();
//
//            N+1 문제 발생 (조회 목록이 N개 일때 1개를 조회했을때 N개가 더 select 됨)
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                    .getResultList();

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(1);
//            em.remove(findParent);
//            em.persist(child1);
//            em.persist(child2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}

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
            Address address = new Address("city", "street", "192-1");

            Member member = new Member();
            member.setUsername("우재민");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address("newCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(copyAddress); // setter를 private로 바꾸거나 삭제하여 side effect(부작용) 방지!

//            em.persist(member);
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

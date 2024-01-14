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
//            Address address = new Address("city", "street", "192-1");
//
//            Member member = new Member();
//            member.setUsername("우재민");
//            member.setHomeAddress(address);
//            em.persist(member);
//
//            Address copyAddress = new Address("newCity", address.getStreet(), address.getZipcode());
//            member.setHomeAddress(copyAddress); // setter를 private로 바꾸거나 삭제하여 side effect(부작용) 방지!
////            em.persist(member);

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(new Address("homeCity2","street","10000"));
            member2.getFavoriteFoods().add("피자");

//            member.getAddressHistory().add(new Address("old1","street","10000"));
//            member.getAddressHistory().add(new Address("old2","street","10000"));
            em.persist(member);
            em.persist(member2);
            em.flush();
            em.clear();

            System.out.println("================== START ==================");
            Member findMember = em.find(Member.class, member.getId());
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            /*
             * /* insert for
             *         hellojpa.Member.addressHistory insert
                    * into
                    * ADDRESS(MEMBER_ID, city, street, zipcode)
                    * values
                    * ( ?, ?, ?, ?)
             *Hibernate:
             *      insert for
             *         hellojpa.Member.addressHistory
            * insert
                    * into
                    * ADDRESS(MEMBER_ID, city, street, zipcode)
                    * values
                    * ( ?, ?, ?, ?)
                    insert  query가 2개 나간다.. 왜냐면 주인 엔디티와 관련된 정보를 다 삭제하고 남아있는 정보만큼 다시 Insert 하기 때
             */
//            findMember.getAddressHistory().remove(new Address("old1","street","10000"));
//            findMember.getAddressHistory().add(new Address("newCity","street","10000"));

            findMember.getAddressHistory().remove(new AddressEntity("old1","street","10000"));
            findMember.getAddressHistory().add(new AddressEntity("newCity","street","10000"));

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

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

//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
//            Member member1 = result.get(0);
//            member1.setAge(20);
//            em.createQuery("select t from Member m join m.team t", Team.class).getResultList();
//            em.createQuery("select distinct m.username, m.age from Member m").getResultList();
//            List<MemberDTO> result2 = em.createQuery("select new hellojpa.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
//
//            MemberDTO memberDTO = result2.get(0);
//            System.out.println("memberDTO. = " + memberDTO.getUsername());
//            System.out.println("memberDTO. = " + memberDTO.getAge());

//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("member"+ i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//            em.flush();
//            em.clear();
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            System.out.println("result.size() = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }

            Team team = new Team();
            team.setUsername("TEAM!");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            String query = "select m from Member m left join m.team t";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            String query = "select m from Member m , Team t where m.username = t.username";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            String query = "select m from Member m left join m.team t on t.username = 'teamA'";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            String query = "select m from Member m left join Team t on m.username = t.username";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();

            // 서브쿼리
//            String query = "select (select avg(m1.age) from Member m1) as avgAge from Member m join Team t on m.username = t.username";
            // From 절의 서브쿼리는 안됨.. -> 1. Join으로 해결, 2. 쿼리 2번 날리기, 3. native
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            String query = "select mm.age, mm.username + " +
//                    "from (select m.age, m.username from Member m) as mm";

            /* JPQL 타입 표현과 기타식
            *
            String query = "select m.username, 'HELLO', TRUE from Member m "+
                    "where m.type = :userType";
            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType",MemberType.ADMIN)
                    .getResultList();

            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[0] = " + objects[1]);
                System.out.println("objects[0] = " + objects[2]);
            }

            String query = "select i from Item i where type(i) = Book";
            em.createQuery(query,Item).clsss
            *
            *
            em.flush();
            em.clear();

            * 조건식(CASE 등등)
            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금'" +
                            "     when m.age >= 60 then '경로요금'" +
                            "     else '일반요금' end "+
                    "from Member m";
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }

            query = "select coalesce(m.username, '이름 없는 회원') from Member m ";
            List<String> result2 = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result2) {
                System.out.println("s = " + s);
            }

            query = "select nullif(m.username, '관리자') as username " +
                    "from Member m";
            List<String> result3 = em.createQuery(query, String.class)
                    .getResultList();
            for (String s : result3) {
                System.out.println("s = " + s);
            }
            * JQPL 기본 함수
             */

            String query = "select concat('a', 'b') from Member m";
//            String query = "select 'a' || 'b' from Member m";
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }

            query = "select function('group_concat', m.username) from Member m";
            List<String> result2 = em.createQuery(query, String.class).getResultList();
            for (String s : result2) {
                System.out.println("s = " + s);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package hellojpa.jpql;

import jakarta.persistence.*;

import java.util.Collection;
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

            Team teamA = new Team();
            teamA.setUsername("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setUsername("팀B");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("회원1");
            member.setAge(10);
            member.setTeam(teamA);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member.setAge(20);
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member.setAge(20);
            member3.setTeam(teamB);
            em.persist(member3);

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

//            String query = "select concat('a', 'b') from Member m";
////            String query = "select 'a' || 'b' from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }
//
//            query = "select function('group_concat', m.username) from Member m";
//            List<String> result2 = em.createQuery(query, String.class).getResultList();
//            for (String s : result2) {
//                System.out.println("s = " + s);
//            }

            // 묵시적 조인이 발생하지 않게 명시적 조인을 해야함
//            String query = "select m from Team t join t.members m";
//            List<Collection> result = em.createQuery(query, Collection.class).getResultList();
//            System.out.println("result = " + result);
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//            String query = "select m from Member m";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1.getUsername() + ", "+ member1.getTeam().getUsername());
//            }

//            query = "select m from Member m join fetch m.team";
//            List<Member> result2 = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            for (Member member22 : result2) {
//                System.out.println("member22 = " + member22.getUsername()+ ", "+ member22.getTeam().getUsername());
//            }

//            query = "select t from Team t join fetch t.members";
//            List<Team> result3 = em.createQuery(query, Team.class)
//                    .getResultList();
//            for (Team team : result3) {
//                System.out.println("team = " + team.getUsername()+ " | members="+ team.getMembers().size());
//            }
            // collection fetch join -> 일대일, 다대일은 가능
            // but) N+1 문제가 발생.. batch size를 선언하자!!!
//            String query = "select t from Team t";
//            List<Team> result4 = em.createQuery(query, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(2)
//                    .getResultList();
//
//            System.out.println("result4.size() = " + result4.size());
//            for (Team team : result4) {
//                System.out.println("team = " + team.getUsername()+ " | members="+ team.getMembers().size());
//                for (Member teamMember : team.getMembers()) {
//                    System.out.println("--> teamMember = " + teamMember);
//                }
//            }

            // select count(m.id) from Member m
            // select count(m) from Member m
            // => 두개는 같은 쿼리문이 나감, JPQL에서 엔티티를 직접 사용하면 SQL에서 해당 엔티티의 기본 키 값을 사용
            
            String query = "select m from Member m where m.team = :team";
            List<Member> members = em.createQuery(query, Member.class).setParameter("team", teamB).getResultList();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1);
            }
            tx.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            emf.close();
        }
    }
}

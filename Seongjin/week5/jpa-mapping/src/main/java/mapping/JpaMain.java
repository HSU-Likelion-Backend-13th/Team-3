package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;


public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team = new Team();
//            team.setName("Team 4");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("Park");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear(); // 1차 캐시 초기화 -> 프록시 테스트 가능
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            // Team은 아직 프록시 상태
//            System.out.println("team 클래스 : " + findMember.getTeam().getClass());
//
//            //쿼리가 나갈 것
//            System.out.println("team 이름 : " + findMember.getTeam().getName());

            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}

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
//            team.setName("team2");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member2");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            // 1차 캐시를 초기화 해야 Proxy 테스트 가능
//            // 안 하면, 실제 객체가 조회되기 때문에
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("------Proxy");
//            // Team은 아직 프록시 상태
//            System.out.println("team 클래스: " + findMember.getTeam().getClass());
//            System.out.println("------Query");
//            // 여기서 쿼리 나갈 것.
//            System.out.println("team 이름: " + findMember.getTeam().getName());

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

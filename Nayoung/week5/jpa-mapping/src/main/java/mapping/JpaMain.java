package mapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//           Team team = new Team();
//           team.setName("Team 3");
//           em.persist(team);
//
//           Member member = new Member();
//           member.setName("nayoung");
//           member.setTeam(team);
//           em.persist(member);
//
//           em.flush();
//           em.clear(); // 1차 캐시 초기화 -> 프록시 테스트 가능
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            // Team은 아직 프록시 상태
//            System.out.println("team 클래스 : " + findMember.getTeam().getClass());
//
//            // 쿼리가 나갈 것
//            System.out.println("team 이름 : "+findMember.getTeam().getName());


            // 멤버 테이블에 있는 멤버 목록 가져옴
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

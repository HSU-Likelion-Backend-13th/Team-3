package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 멤버 생성
            Member member = new Member("나영", "성북구", "한성대", "공학관 코딩라운지");
            em.persist(member);

            // 아이템 생성
            Item item = new Item("감자빵", 5000, 5);
            em.persist(item);

            // OrderItem
            OrderItem orderItem  = new OrderItem();
            orderItem.setCount(3);
            orderItem.setOrderPrice(3*item.getPrice());
            orderItem.setItem(item);
            em.persist(orderItem);

            // Order
            Order order = new Order();
            order.setMember(member);
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.addOrderItem(orderItem);
            em.persist(order);

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}

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
            Member member = new Member("건우", "부천시", "상오정로", "내 집");
            em.persist(member);

            Item item = new Item("페퍼로니 피자", 25000, 5);
            em.persist(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setCount(3);
            orderItem.setOrderPrice(3 * item.getPrice());
            orderItem.setItem(item);
            em.persist(orderItem);

            Order order = new Order();
            order.setMember(member);
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.addOrderItem(orderItem); // 연관관계 편의 메서드를 통해 orderItems.setOrder(this) 안 해도 됨
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

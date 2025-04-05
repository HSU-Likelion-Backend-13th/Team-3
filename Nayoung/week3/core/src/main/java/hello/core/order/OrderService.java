package hello.core.order;

public interface OrderService {
    // 최종 주문 결과를 반환 (원래는 DB에 저장해야 함 -> 복잡해지므로 생략)
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
